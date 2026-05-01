package tn.esprit.microservice4.DTO;

public class ReportTemplateDTO {

    private Long id;
    private String templateName;
    private String templateContent;
    private boolean active;

    public ReportTemplateDTO() {}

    public ReportTemplateDTO(Long id, String templateName, String templateContent, boolean active) {
        this.id = id;
        this.templateName = templateName;
        this.templateContent = templateContent;
        this.active = active;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTemplateName() { return templateName; }
    public void setTemplateName(String n) { this.templateName = n; }
    public String getTemplateContent() { return templateContent; }
    public void setTemplateContent(String c) { this.templateContent = c; }
    public boolean isActive() { return active; }
    public void setActive(boolean a) { this.active = a; }
}
