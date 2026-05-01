package tn.esprit.microservice4.Services.Implementations;

import org.springframework.stereotype.Service;
import tn.esprit.microservice4.DTO.CreateReportScheduleDTO;
import tn.esprit.microservice4.Entities.ReportSchedule;
import tn.esprit.microservice4.Entities.ReportTemplate;
import tn.esprit.microservice4.Entities.User;
import tn.esprit.microservice4.Repositories.ReportScheduleRepository;
import tn.esprit.microservice4.Repositories.ReportTemplateRepository;
import tn.esprit.microservice4.Repositories.UserRepository;
import tn.esprit.microservice4.Services.Interfaces.ReportScheduleService;

import java.util.List;

@Service
public class ReportScheduleServiceImpl implements ReportScheduleService {

    private final ReportScheduleRepository repository;
    private final UserRepository userRepository;
    private final ReportTemplateRepository templateRepository;

    public ReportScheduleServiceImpl(ReportScheduleRepository repository,
                                     UserRepository userRepository,
                                     ReportTemplateRepository templateRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
        this.templateRepository = templateRepository;
    }

    @Override
    public ReportSchedule create(CreateReportScheduleDTO dto) {
        User patient = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        User doctor = userRepository.findById(dto.getDoctorId())
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        if (!"DOCTOR".equals(doctor.getRole())) {
            throw new RuntimeException("Selected user is not a doctor");
        }

        ReportTemplate template = templateRepository.findById(dto.getTemplateId())
                .orElseThrow(() -> new RuntimeException("Template not found"));

        ReportSchedule schedule = new ReportSchedule();
        schedule.setScheduleName(dto.getScheduleName());
        schedule.setScheduleType(dto.getScheduleType());
        schedule.setNextExecutionDate(dto.getNextExecutionDate());
        schedule.setActive(dto.isActive());
        schedule.setUser(patient);
        schedule.setDoctor(doctor);
        schedule.setTemplate(template);

        return repository.save(schedule);
    }

    @Override
    public ReportSchedule update(Long id, ReportSchedule schedule) {
        ReportSchedule existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Schedule not found"));
        existing.setScheduleName(schedule.getScheduleName());
        existing.setScheduleType(schedule.getScheduleType());
        existing.setNextExecutionDate(schedule.getNextExecutionDate());
        existing.setActive(schedule.isActive());
        return repository.save(existing);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public ReportSchedule getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Schedule not found"));
    }

    @Override
    public List<ReportSchedule> getAll() {
        return repository.findAll();
    }

    @Override
    public List<ReportSchedule> getActiveSchedules() {
        return repository.findByActiveTrue();
    }

    @Override
    public List<ReportSchedule> getByUserId(Long userId) {
        return repository.findByUserId(userId);
    }

    @Override
    public List<ReportSchedule> getDueSchedules() {
        return repository.findDueSchedules();
    }
}
