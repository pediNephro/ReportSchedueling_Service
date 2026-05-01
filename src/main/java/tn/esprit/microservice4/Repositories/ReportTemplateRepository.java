package tn.esprit.microservice4.Repositories;

import tn.esprit.microservice4.Entities.ReportTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReportTemplateRepository extends JpaRepository<ReportTemplate, Long> {
    Optional<ReportTemplate> findByActiveTrue();
    Optional<ReportTemplate> findByTemplateName(String templateName);
    boolean existsByTemplateName(String templateName);
}
