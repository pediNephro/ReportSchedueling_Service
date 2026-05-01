package tn.esprit.microservice4.Controllers;

import org.springframework.web.bind.annotation.*;
import tn.esprit.microservice4.DTO.ReportTemplateDTO;
import tn.esprit.microservice4.Entities.ReportTemplate;
import tn.esprit.microservice4.Services.Interfaces.ReportTemplateService;

import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/schedules/templates")
public class ReportTemplateController {

    private final ReportTemplateService service;

    public ReportTemplateController(ReportTemplateService service) {
        this.service = service;
    }

    @PostMapping
    public ReportTemplateDTO create(@RequestBody ReportTemplateDTO dto) {
        return service.create(dto);
    }

    @PutMapping("/{id}")
    public ReportTemplate update(@PathVariable Long id, @RequestBody ReportTemplate template) {
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

    @GetMapping("/active")
    public ReportTemplate getActiveTemplate() {
        return service.getActiveTemplate().orElse(null);
    }

    @GetMapping("/by-name/{name}")
    public ReportTemplate getByName(@PathVariable String name) {
        return service.getByTemplateName(name).orElse(null);
    }
}
