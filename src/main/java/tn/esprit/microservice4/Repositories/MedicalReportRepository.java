package tn.esprit.microservice4.Repositories;

import tn.esprit.microservice4.Entities.MedicalReport;
import tn.esprit.microservice4.Enum.ReportStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MedicalReportRepository extends JpaRepository<MedicalReport, Long> {
    List<MedicalReport> findByPatientId(Long patientId);
    List<MedicalReport> findByDoctorId(Long doctorId);
    List<MedicalReport> findByStatus(ReportStatus status);
    List<MedicalReport> findByArchivedFalse();
    List<MedicalReport> findByGenerationDateBetween(LocalDateTime start, LocalDateTime end);
}
