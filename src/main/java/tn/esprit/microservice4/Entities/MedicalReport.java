package tn.esprit.microservice4.Entities;

import jakarta.persistence.*;
import lombok.*;
import tn.esprit.microservice4.Enum.ReportStatus;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MedicalReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String reportName;

    @Enumerated(EnumType.STRING)
    private ReportStatus status;

    private LocalDateTime generationDate;
    private LocalDateTime sentDate;
    private String filePath;
    private boolean archived;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private User patient;

    @ManyToOne
    @JoinColumn(name = "generated_by")
    private User doctor;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getReportName() { return reportName; }
    public void setReportName(String reportName) { this.reportName = reportName; }
    public ReportStatus getStatus() { return status; }
    public void setStatus(ReportStatus status) { this.status = status; }
    public LocalDateTime getGenerationDate() { return generationDate; }
    public void setGenerationDate(LocalDateTime generationDate) { this.generationDate = generationDate; }
    public LocalDateTime getSentDate() { return sentDate; }
    public void setSentDate(LocalDateTime sentDate) { this.sentDate = sentDate; }
    public String getFilePath() { return filePath; }
    public void setFilePath(String filePath) { this.filePath = filePath; }
    public boolean isArchived() { return archived; }
    public void setArchived(boolean archived) { this.archived = archived; }
    public User getPatient() { return patient; }
    public void setPatient(User patient) { this.patient = patient; }
    public User getDoctor() { return doctor; }
    public void setDoctor(User doctor) { this.doctor = doctor; }
}
