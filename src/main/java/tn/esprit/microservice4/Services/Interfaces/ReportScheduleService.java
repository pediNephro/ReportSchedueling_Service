package tn.esprit.microservice4.Services.Interfaces;

import tn.esprit.microservice4.DTO.CreateReportScheduleDTO;
import tn.esprit.microservice4.Entities.ReportSchedule;

import java.util.List;

public interface ReportScheduleService {
    ReportSchedule create(CreateReportScheduleDTO dto);
    ReportSchedule update(Long id, ReportSchedule schedule);
    void delete(Long id);
    ReportSchedule getById(Long id);
    List<ReportSchedule> getAll();
    List<ReportSchedule> getActiveSchedules();
    List<ReportSchedule> getByUserId(Long userId);
    List<ReportSchedule> getDueSchedules();
}
