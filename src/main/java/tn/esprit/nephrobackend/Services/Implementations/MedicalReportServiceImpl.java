package tn.esprit.nephrobackend.Services.Implementations;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.nephrobackend.Entities.MedicalReport;
import tn.esprit.nephrobackend.Enum.ReportStatus;
import tn.esprit.nephrobackend.Repositories.MedicalReportRepository;
import tn.esprit.nephrobackend.Services.Interfaces.MedicalReportService;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MedicalReportServiceImpl implements MedicalReportService {
    public MedicalReportServiceImpl(MedicalReportRepository repository) {
        this.repository = repository;
    }

    private final MedicalReportRepository repository;

    @Override
    public MedicalReport create(MedicalReport report) {
        return repository.save(report);
    }

    @Override
    public MedicalReport update(Long id, MedicalReport report) {
        MedicalReport existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Report not found"));

        existing.setReportName(report.getReportName());
        existing.setStatus(report.getStatus());
        existing.setArchived(report.isArchived());

        return repository.save(existing);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public MedicalReport getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Report not found"));
    }

    @Override
    public List<MedicalReport> getAll() {
        return repository.findAll();
    }

    @Override
    public List<MedicalReport> getByPatient(Long patientId) {
        return repository.findByPatientId(patientId);
    }

    @Override
    public List<MedicalReport> getByDoctor(Long doctorId) {
        return repository.findByDoctorId(doctorId);
    }

    @Override
    public List<MedicalReport> getByStatus(ReportStatus status) {
        return repository.findByStatus(status);
    }

    @Override
    public List<MedicalReport> getNotArchived() {
        return repository.findByArchivedFalse();
    }

    @Override
    public List<MedicalReport> getBetween(LocalDateTime start, LocalDateTime end) {
        return repository.findByGenerationDateBetween(start, end);
    }
}