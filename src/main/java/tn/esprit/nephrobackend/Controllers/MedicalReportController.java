package tn.esprit.nephrobackend.Controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.nephrobackend.Entities.MedicalReport;
import tn.esprit.nephrobackend.Enum.ReportStatus;
import tn.esprit.nephrobackend.Services.Interfaces.MedicalReportService;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/reports")
public class MedicalReportController {
    public MedicalReportController(MedicalReportService service) {
        this.service = service;
    }

    private final MedicalReportService service;

//    @PostMapping
//    public MedicalReport create(@RequestBody MedicalReport report) {
//        return service.create(report);
//    }

    @GetMapping
    public List<MedicalReport> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public MedicalReport getById(@PathVariable Long id) {
        return service.getById(id);
    }

//    @PutMapping("/{id}")
//    public MedicalReport update(@PathVariable Long id,
//                                @RequestBody MedicalReport report) {
//        return service.update(id, report);
//    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @GetMapping("/patient/{patientId}")
    public List<MedicalReport> getByPatient(@PathVariable Long patientId) {
        return service.getByPatient(patientId);
    }

    @GetMapping("/doctor/{doctorId}")
    public List<MedicalReport> getByDoctor(@PathVariable Long doctorId) {
        return service.getByDoctor(doctorId);
    }

    @GetMapping("/status/{status}")
    public List<MedicalReport> getByStatus(@PathVariable ReportStatus status) {
        return service.getByStatus(status);
    }

    @GetMapping("/not-archived")
    public List<MedicalReport> getNotArchived() {
        return service.getNotArchived();
    }

    @GetMapping("/between")
    public List<MedicalReport> getBetween(
            @RequestParam LocalDateTime start,
            @RequestParam LocalDateTime end) {
        return service.getBetween(start, end);
    }
}