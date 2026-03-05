package tn.esprit.nephrobackend.Controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.nephrobackend.DTO.CreateReportScheduleDTO;
import tn.esprit.nephrobackend.Entities.ReportSchedule;
import tn.esprit.nephrobackend.Services.Interfaces.ReportScheduleService;

import java.util.List;

@RestController
@RequestMapping("/api/schedules")
public class ReportScheduleController {
    public ReportScheduleController(ReportScheduleService service) {
        this.service = service;
    }

    private final ReportScheduleService service;

    @PostMapping
    public ReportSchedule create(@RequestBody CreateReportScheduleDTO dto) {
        return service.create(dto);
    }

    @GetMapping
    public List<ReportSchedule> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ReportSchedule getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public ReportSchedule update(@PathVariable Long id,
                                 @RequestBody ReportSchedule schedule) {
        return service.update(id, schedule);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @GetMapping("/active")
    public List<ReportSchedule> getActiveSchedules() {
        return service.getActiveSchedules();
    }

    @GetMapping("/user/{userId}")
    public List<ReportSchedule> getByUser(@PathVariable Long userId) {
        return service.getByUserId(userId);
    }

    @GetMapping("/due")
    public List<ReportSchedule> getDueSchedules() {
        return service.getDueSchedules();
    }
}