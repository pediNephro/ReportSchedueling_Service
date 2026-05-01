package tn.esprit.microservice4.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmailLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String recipientEmail;
    private String subject;

    @Lob
    private String content;

    private LocalDateTime sentDate;
    private boolean success;
    private String errorMessage;

    @ManyToOne
    @JoinColumn(name = "report_id")
    private MedicalReport report;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getRecipientEmail() { return recipientEmail; }
    public void setRecipientEmail(String e) { this.recipientEmail = e; }
    public String getSubject() { return subject; }
    public void setSubject(String s) { this.subject = s; }
    public String getContent() { return content; }
    public void setContent(String c) { this.content = c; }
    public LocalDateTime getSentDate() { return sentDate; }
    public void setSentDate(LocalDateTime d) { this.sentDate = d; }
    public boolean isSuccess() { return success; }
    public void setSuccess(boolean s) { this.success = s; }
    public String getErrorMessage() { return errorMessage; }
    public void setErrorMessage(String e) { this.errorMessage = e; }
    public MedicalReport getReport() { return report; }
    public void setReport(MedicalReport r) { this.report = r; }
}
