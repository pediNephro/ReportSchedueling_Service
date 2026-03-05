package tn.esprit.nephrobackend.Services.Interfaces;

import tn.esprit.nephrobackend.DTO.ReportTemplateDTO;
import tn.esprit.nephrobackend.Entities.ReportTemplate;

import java.util.List;
import java.util.Optional;

public interface ReportTemplateService {

    ReportTemplateDTO create(ReportTemplateDTO dto);

    ReportTemplate update(Long id, ReportTemplate template);

    void delete(Long id);

    ReportTemplate getById(Long id);

    List<ReportTemplate> getAll();

    Optional<ReportTemplate> getActiveTemplate();

    Optional<ReportTemplate> getByTemplateName(String name);
}