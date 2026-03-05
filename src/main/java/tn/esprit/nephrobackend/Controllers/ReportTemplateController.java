package tn.esprit.nephrobackend.Controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.nephrobackend.DTO.ReportTemplateDTO;
import tn.esprit.nephrobackend.Entities.ReportTemplate;
import tn.esprit.nephrobackend.Services.Interfaces.ReportTemplateService;

import java.util.List;

@RestController
@RequestMapping("/api/templates")
public class ReportTemplateController {
    public ReportTemplateController(ReportTemplateService service) {
        this.service = service;
    }

    private final ReportTemplateService service;

    @PostMapping
    public ReportTemplateDTO create(@RequestBody ReportTemplateDTO dto) {
        return service.create(dto);
    }

    @PutMapping("/{id}")
    public ReportTemplate update(@PathVariable Long id,
                                 @RequestBody ReportTemplate template) {
        return service.update(id, template);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @GetMapping("/{id}")
    public ReportTemplate getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping
    public List<ReportTemplate> getAll() {
        return service.getAll();
    }

    // Specific endpoints
    @GetMapping("/active")
    public ReportTemplate getActiveTemplate() {
        return service.getActiveTemplate().orElse(null);
    }

    @GetMapping("/by-name/{name}")
    public ReportTemplate getByName(@PathVariable String name) {
        return service.getByTemplateName(name).orElse(null);
    }
}