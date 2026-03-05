package tn.esprit.nephrobackend.Controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.nephrobackend.Entities.EmailLog;
import tn.esprit.nephrobackend.Services.Interfaces.EmailLogService;

import java.util.List;

@RestController
@RequestMapping("/api/email-logs")
public class EmailLogController {
    public EmailLogController(EmailLogService service) {
        this.service = service;
    }

    private final EmailLogService service;

    @PostMapping
    public EmailLog create(@RequestBody EmailLog emailLog) {
        return service.create(emailLog);
    }

    @GetMapping
    public List<EmailLog> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public EmailLog getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public EmailLog update(@PathVariable Long id,
                           @RequestBody EmailLog emailLog) {
        return service.update(id, emailLog);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @GetMapping("/report/{reportId}")
    public List<EmailLog> getByReportId(@PathVariable Long reportId) {
        return service.getByReportId(reportId);
    }

    @GetMapping("/failed")
    public List<EmailLog> getFailed() {
        return service.getFailedEmails();
    }

    @GetMapping("/recipient/{email}")
    public List<EmailLog> getByRecipient(@PathVariable String email) {
        return service.getByRecipient(email);
    }
}