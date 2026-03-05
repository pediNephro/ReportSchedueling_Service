package tn.esprit.nephrobackend.Repositories;

import tn.esprit.nephrobackend.Entities.EmailLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmailLogRepository extends JpaRepository<EmailLog, Long> {

    List<EmailLog> findBySuccessFalse();

    List<EmailLog> findByReportId(Long reportId);

    List<EmailLog> findByRecipientEmail(String email);
}
