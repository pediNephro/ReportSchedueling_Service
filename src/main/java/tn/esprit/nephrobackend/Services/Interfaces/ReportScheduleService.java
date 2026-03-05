package tn.esprit.nephrobackend.Services.Interfaces;

import tn.esprit.nephrobackend.DTO.CreateReportScheduleDTO;
import tn.esprit.nephrobackend.Entities.ReportSchedule;

import java.util.List;

public interface ReportScheduleService {

    ReportSchedule create(CreateReportScheduleDTO schedule);

    ReportSchedule update(Long id, ReportSchedule schedule);

    void delete(Long id);

    ReportSchedule getById(Long id);

    List<ReportSchedule> getAll();

    List<ReportSchedule> getActiveSchedules();

    List<ReportSchedule> getByUserId(Long userId);

    List<ReportSchedule> getDueSchedules();
}
