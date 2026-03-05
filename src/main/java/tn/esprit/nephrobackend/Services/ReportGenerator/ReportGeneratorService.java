package tn.esprit.nephrobackend.Services.ReportGenerator;

import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import tn.esprit.nephrobackend.Entities.MedicalReport;
import tn.esprit.nephrobackend.Entities.ReportSchedule;
import tn.esprit.nephrobackend.Entities.User;
import tn.esprit.nephrobackend.Enum.ReportStatus;
import tn.esprit.nephrobackend.Repositories.MedicalReportRepository;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.time.LocalDateTime;

@Service
public class ReportGeneratorService {

    private final MedicalReportRepository medicalReportRepository;
    private final JavaMailSender mailSender;

    public ReportGeneratorService(MedicalReportRepository medicalReportRepository, JavaMailSender mailSender) {
        this.medicalReportRepository = medicalReportRepository;
        this.mailSender = mailSender;
    }
    private void createDebugPdf(File file, String templateContent) {

        try {

            String debugHtml =
                    "<html>" +
                            "<body>" +
                            "<h2>PDF Generation Failed</h2>" +
                            "<p>Raw Template Content:</p>" +
                            "<pre>" + templateContent
                            .replace("<", "&lt;")
                            .replace(">", "&gt;") +
                            "</pre>" +
                            "</body>" +
                            "</html>";

            OutputStream os = new FileOutputStream(file);

            PdfRendererBuilder builder = new PdfRendererBuilder();
            builder.withHtmlContent(debugHtml, null);
            builder.toStream(os);
            builder.run();

            os.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void generateReportFromSchedule(ReportSchedule schedule) {

        User patient = schedule.getUser();
        User doctor = schedule.getDoctor();
        String template = schedule.getTemplate().getTemplateContent();
        String filledHtml = fillTemplateVariables(template, schedule);

        String fileName = "report_" + System.currentTimeMillis() + ".pdf";
        String filePath = "reports/" + fileName;

        File file = new File(filePath);
        file.getParentFile().mkdirs();

        try {

            OutputStream os = new FileOutputStream(file);

            PdfRendererBuilder builder = new PdfRendererBuilder();
            builder.withHtmlContent(filledHtml, null);
            builder.toStream(os);
            builder.run();

            os.close();

        } catch (Exception e) {

            System.err.println("PDF generation failed. Creating debug PDF...");
            e.printStackTrace();

            createDebugPdf(file, template);

        }

        try {

            // Save report
            MedicalReport report = new MedicalReport();
            report.setReportName(schedule.getScheduleName());
            report.setStatus(ReportStatus.GENERATED);
            report.setGenerationDate(LocalDateTime.now());
            report.setFilePath(filePath);
            report.setArchived(false);
            report.setPatient(patient);
            report.setDoctor(doctor);

            medicalReportRepository.save(report);

            // Send email
            sendEmailWithAttachment(doctor.getEmail(), file);

            report.setStatus(ReportStatus.SENT);
            report.setSentDate(LocalDateTime.now());
            medicalReportRepository.save(report);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void sendEmailWithAttachment(String to, File file) throws Exception {

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper =
                new MimeMessageHelper(message, true);

        helper.setTo(to);
        helper.setSubject("New Medical Report Generated");
        helper.setText("Dear Doctor,\n\nA new medical report has been generated.\nPlease find the attachment.\n\nRegards,\nNephro System");

        helper.addAttachment(file.getName(), file);

        mailSender.send(message);
    }

    private String fillTemplateVariables(String templateContent, ReportSchedule schedule) {

        User patient = schedule.getUser();

        String result = templateContent;

        // Variables communes
        result = result.replace("{{name}}",
                patient.getFirstName() + " " + patient.getLastName());

        // Template 1 → Weekly Dialysis Summary
        if (templateContent.contains("{{sessions}}")) {
            result = result.replace("{{sessions}}",
                    String.valueOf(patient.getDialysisSessionsPerWeek()));
        }

        if (templateContent.contains("{{notes}}")) {
            result = result.replace("{{notes}}",
                    patient.getDoctorNotes());
        }

        // Template 2 → Patient Report
        if (templateContent.contains("{{date}}")) {
            result = result.replace("{{date}}",
                    LocalDateTime.now().toLocalDate().toString());
        }

        if (templateContent.contains("{{bp}}")) {
            result = result.replace("{{bp}}",
                    patient.getBloodPressure()); // assure que ce champ existe
        }

        return result;
    }
}
