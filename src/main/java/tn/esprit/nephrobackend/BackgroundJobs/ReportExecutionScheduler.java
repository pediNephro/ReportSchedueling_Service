package tn.esprit.nephrobackend.BackgroundJobs;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.nephrobackend.Entities.ReportSchedule;
import tn.esprit.nephrobackend.Repositories.ReportScheduleRepository;
import tn.esprit.nephrobackend.Services.Interfaces.ReportScheduleService;
import tn.esprit.nephrobackend.Services.ReportGenerator.ReportGeneratorService;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class ReportExecutionScheduler {

    private final ReportScheduleService scheduleService;
    private final ReportScheduleRepository repository;
    private final ReportGeneratorService reportGeneratorService;

    public ReportExecutionScheduler(ReportScheduleService scheduleService, ReportScheduleRepository repository, ReportGeneratorService reportGeneratorService) {
        this.scheduleService = scheduleService;
        this.repository = repository;
        this.reportGeneratorService = reportGeneratorService;
    }

    /**
     * Runs every minute
     */
    @Scheduled(fixedRate = 60000)
    @Transactional
    public void executeDueReports() {


        List<ReportSchedule> dueSchedules = scheduleService.getDueSchedules();
        System.out.println("NOW: " + LocalDateTime.now());
        System.out.println("dueSchedules :" + dueSchedules.stream().count());

        if (dueSchedules.isEmpty()) {
            return;
        }

        for (ReportSchedule schedule : dueSchedules) {

            if (!schedule.isActive()) {
                continue;
            }

            try {


                // 🔥 Generate Report + Save + Send Email
                reportGeneratorService.generateReportFromSchedule(schedule);

                // 🔄 Update next execution date
                updateNextExecution(schedule);


            } catch (Exception e) {

                System.out.println(e.getMessage());
                // Optional: deactivate schedule on repeated failure
                // schedule.setActive(false);
                // repository.save(schedule);
            }
        }
    }

    private void updateNextExecution(ReportSchedule schedule) {

        LocalDateTime next = schedule.getNextExecutionDate();

        switch (schedule.getScheduleType()) {

            case DAILY:
                next = next.plusDays(1);
                break;

            case WEEKLY:
                next = next.plusWeeks(1);
                break;

            case MONTHLY:
                next = next.plusMonths(1);
                break;
        }

        schedule.setNextExecutionDate(next);
        repository.save(schedule);
    }
}