package tn.esprit.nephrobackend.Services.Interfaces;

import tn.esprit.nephrobackend.Entities.MedicalReport;
import tn.esprit.nephrobackend.Enum.ReportStatus;

import java.time.LocalDateTime;
import java.util.List;

public interface MedicalReportService {

    MedicalReport create(MedicalReport report);

    MedicalReport update(Long id, MedicalReport report);

    void delete(Long id);

    MedicalReport getById(Long id);

    List<MedicalReport> getAll();

    List<MedicalReport> getByPatient(Long patientId);

    List<MedicalReport> getByDoctor(Long doctorId);

    List<MedicalReport> getByStatus(ReportStatus status);

    List<MedicalReport> getNotArchived();

    List<MedicalReport> getBetween(LocalDateTime start, LocalDateTime end);
}