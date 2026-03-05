package tn.esprit.nephrobackend.Services.Implementations;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.nephrobackend.Entities.EmailLog;
import tn.esprit.nephrobackend.Repositories.EmailLogRepository;
import tn.esprit.nephrobackend.Services.Interfaces.EmailLogService;

import java.util.List;

@Service
public class EmailLogServiceImpl implements EmailLogService {
    public EmailLogServiceImpl(EmailLogRepository repository) {
        this.repository = repository;
    }

    private final EmailLogRepository repository;

    @Override
    public EmailLog create(EmailLog emailLog) {
        return repository.save(emailLog);
    }

    @Override
    public EmailLog update(Long id, EmailLog emailLog) {
        EmailLog existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Email log not found"));

        existing.setRecipientEmail(emailLog.getRecipientEmail());
        existing.setSubject(emailLog.getSubject());
        existing.setContent(emailLog.getContent());
        existing.setSuccess(emailLog.isSuccess());
        existing.setErrorMessage(emailLog.getErrorMessage());

        return repository.save(existing);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public EmailLog getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Email log not found"));
    }

    @Override
    public List<EmailLog> getAll() {
        return repository.findAll();
    }

    @Override
    public List<EmailLog> getFailedEmails() {
        return repository.findBySuccessFalse();
    }

    @Override
    public List<EmailLog> getByReportId(Long reportId) {
        return repository.findByReportId(reportId);
    }

    @Override
    public List<EmailLog> getByRecipient(String email) {
        return repository.findByRecipientEmail(email);
    }
}