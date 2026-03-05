package tn.esprit.nephrobackend.Services.Interfaces;

import tn.esprit.nephrobackend.Entities.EmailLog;

import java.util.List;

public interface EmailLogService {

    EmailLog create(EmailLog emailLog);

    EmailLog update(Long id, EmailLog emailLog);

    void delete(Long id);

    EmailLog getById(Long id);

    List<EmailLog> getAll();

    List<EmailLog> getFailedEmails();

    List<EmailLog> getByReportId(Long reportId);

    List<EmailLog> getByRecipient(String email);
}