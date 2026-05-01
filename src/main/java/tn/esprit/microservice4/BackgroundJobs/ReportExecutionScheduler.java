package tn.esprit.microservice4.BackgroundJobs;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.microservice4.Entities.ReportSchedule;
import tn.esprit.microservice4.Repositories.ReportScheduleRepository;
import tn.esprit.microservice4.Services.Interfaces.ReportScheduleService;
import tn.esprit.microservice4.Services.ReportGenerator.ReportGeneratorService;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReportExecutionScheduler {

    private final ReportScheduleService scheduleService;
    private final ReportScheduleRepository repository;
    private final ReportGeneratorService reportGeneratorService;

    public ReportExecutionScheduler(ReportScheduleService scheduleService,
                                    ReportScheduleRepository repository,
                                    ReportGeneratorService reportGeneratorService) {
        this.scheduleService = scheduleService;
        this.repository = repository;
        this.reportGeneratorService = reportGeneratorService;
    }

    @Scheduled(fixedRate = 60000)
    @Transactional
    public void executeDueReports() {
        List<ReportSchedule> dueSchedules = scheduleService.getDueSchedules();
        System.out.println("NOW: " + LocalDateTime.now());
        System.out.println("Due schedules: " + dueSchedules.size());

        if (dueSchedules.isEmpty()) return;

        for (ReportSchedule schedule : dueSchedules) {
            if (!schedule.isActive()) continue;
            try {
                reportGeneratorService.generateReportFromSchedule(schedule);
                updateNextExecution(schedule);
            } catch (Exception e) {
                System.err.println("Failed to execute schedule: " + e.getMessage());
            }
        }
    }

    private void updateNextExecution(ReportSchedule schedule) {
        LocalDateTime next = schedule.getNextExecutionDate();
        switch (schedule.getScheduleType()) {
            case DAILY  -> next = next.plusDays(1);
            case WEEKLY -> next = next.plusWeeks(1);
            case MONTHLY -> next = next.plusMonths(1);
        }
        schedule.setNextExecutionDate(next);
        repository.save(schedule);
    }
}
