package tn.esprit.nephrobackend.Services.Implementations;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.nephrobackend.DTO.ReportTemplateDTO;
import tn.esprit.nephrobackend.Entities.ReportTemplate;
import tn.esprit.nephrobackend.Repositories.ReportTemplateRepository;
import tn.esprit.nephrobackend.Services.Interfaces.ReportTemplateService;

import java.util.List;
import java.util.Optional;

@Service
public class ReportTemplateServiceImpl implements ReportTemplateService {
    public ReportTemplateServiceImpl(ReportTemplateRepository repository) {
        this.repository = repository;
    }

    private final ReportTemplateRepository repository;

    @Override
    public ReportTemplateDTO create(ReportTemplateDTO dto) {

        ReportTemplate template = new ReportTemplate();

        template.setTemplateName(dto.getTemplateName());
        template.setTemplateContent(dto.getTemplateContent());
        template.setActive(dto.isActive());

        ReportTemplate saved = repository.save(template);

        ReportTemplateDTO response = new ReportTemplateDTO();

        response.setId(saved.getId());
        response.setTemplateName(saved.getTemplateName());
        response.setTemplateContent(saved.getTemplateContent());
        response.setActive(saved.isActive());

        return response;
    }

    @Override
    public ReportTemplate update(Long id, ReportTemplate template) {
        ReportTemplate existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Template not found"));

        existing.setTemplateName(template.getTemplateName());
        existing.setTemplateContent(template.getTemplateContent());
        existing.setActive(template.isActive());

        return repository.save(existing);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public ReportTemplate getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Template not found"));
    }

    @Override
    public List<ReportTemplate> getAll() {
        return repository.findAll();
    }

    @Override
    public Optional<ReportTemplate> getActiveTemplate() {
        return repository.findByActiveTrue();
    }

    @Override
    public Optional<ReportTemplate> getByTemplateName(String name) {
        return repository.findByTemplateName(name);
    }
}