package tn.esprit.microservice4.DTO;

import tn.esprit.microservice4.Enum.ScheduleType;

import java.time.LocalDateTime;

public class CreateReportScheduleDTO {

    private String scheduleName;
    private ScheduleType scheduleType;
    private LocalDateTime nextExecutionDate;
    private boolean active;
    private Long userId;
    private Long templateId;
    private Long doctorId;

    public String getScheduleName() { return scheduleName; }
    public void setScheduleName(String s) { this.scheduleName = s; }
    public ScheduleType getScheduleType() { return scheduleType; }
    public void setScheduleType(ScheduleType t) { this.scheduleType = t; }
    public LocalDateTime getNextExecutionDate() { return nextExecutionDate; }
    public void setNextExecutionDate(LocalDateTime d) { this.nextExecutionDate = d; }
    public boolean isActive() { return active; }
    public void setActive(boolean a) { this.active = a; }
    public Long getUserId() { return userId; }
    public void setUserId(Long id) { this.userId = id; }
    public Long getTemplateId() { return templateId; }
    public void setTemplateId(Long id) { this.templateId = id; }
    public Long getDoctorId() { return doctorId; }
    public void setDoctorId(Long id) { this.doctorId = id; }
}
