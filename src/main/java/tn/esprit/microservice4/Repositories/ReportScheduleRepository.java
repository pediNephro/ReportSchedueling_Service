package tn.esprit.microservice4.Repositories;

import tn.esprit.microservice4.Entities.ReportSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportScheduleRepository extends JpaRepository<ReportSchedule, Long> {
    List<ReportSchedule> findByActiveTrue();
    List<ReportSchedule> findByUserId(Long userId);

    @Query("""
            SELECT r FROM ReportSchedule r
            WHERE r.active = true
            AND r.nextExecutionDate <= CURRENT_TIMESTAMP
            """)
    List<ReportSchedule> findDueSchedules();
}
