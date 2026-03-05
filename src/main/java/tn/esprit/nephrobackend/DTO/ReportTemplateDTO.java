package tn.esprit.nephrobackend.DTO;

public class ReportTemplateDTO {

    private Long id;
    private String templateName;
    private String templateContent;
    private boolean active;

    public ReportTemplateDTO() {
    }

    public ReportTemplateDTO(Long id, String templateName, String templateContent, boolean active) {
        this.id = id;
        this.templateName = templateName;
        this.templateContent = templateContent;
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getTemplateContent() {
        return templateContent;
    }

    public void setTemplateContent(String templateContent) {
        this.templateContent = templateContent;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
