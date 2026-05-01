package tn.esprit.microservice4.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import tn.esprit.microservice4.Enum.ScheduleType;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class ReportSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String scheduleName;

    @Enumerated(EnumType.STRING)
    private ScheduleType scheduleType;

    private LocalDateTime nextExecutionDate;

    private boolean active;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "template_id", nullable = false)
    private ReportTemplate template;

    @ManyToOne
    @JoinColumn(name = "doctor_id", nullable = false)
    private User doctor;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getScheduleName() { return scheduleName; }
    public void setScheduleName(String scheduleName) { this.scheduleName = scheduleName; }
    public ScheduleType getScheduleType() { return scheduleType; }
    public void setScheduleType(ScheduleType scheduleType) { this.scheduleType = scheduleType; }
    public LocalDateTime getNextExecutionDate() { return nextExecutionDate; }
    public void setNextExecutionDate(LocalDateTime nextExecutionDate) { this.nextExecutionDate = nextExecutionDate; }
    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    public ReportTemplate getTemplate() { return template; }
    public void setTemplate(ReportTemplate template) { this.template = template; }
    public User getDoctor() { return doctor; }
    public void setDoctor(User doctor) { this.doctor = doctor; }
}
