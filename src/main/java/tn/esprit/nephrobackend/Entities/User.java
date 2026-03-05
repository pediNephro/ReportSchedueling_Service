package tn.esprit.nephrobackend.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getBloodPressure() {
        return bloodPressure;
    }

    public void setBloodPressure(String bloodPressure) {
        this.bloodPressure = bloodPressure;
    }

    public Double getCreatinineLevel() {
        return creatinineLevel;
    }

    public void setCreatinineLevel(Double creatinineLevel) {
        this.creatinineLevel = creatinineLevel;
    }

    public Integer getDialysisSessionsPerWeek() {
        return dialysisSessionsPerWeek;
    }

    public void setDialysisSessionsPerWeek(Integer dialysisSessionsPerWeek) {
        this.dialysisSessionsPerWeek = dialysisSessionsPerWeek;
    }

    public String getDoctorNotes() {
        return doctorNotes;
    }

    public void setDoctorNotes(String doctorNotes) {
        this.doctorNotes = doctorNotes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public List<MedicalReport> getMedicalReports() {
        return medicalReports;
    }

    public void setMedicalReports(List<MedicalReport> medicalReports) {
        this.medicalReports = medicalReports;
    }

    public List<ReportSchedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<ReportSchedule> schedules) {
        this.schedules = schedules;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;

    @Column(unique = true)
    private String email;

    private String role; // ADMIN, DOCTOR, PATIENT

    private String bloodType;
    private Double weight;
    private Double height;
    private String diagnosis;

    private String bloodPressure;
    private Double creatinineLevel;
    private Integer dialysisSessionsPerWeek;
    private String doctorNotes;

    private boolean active;

    @JsonIgnore
    @OneToMany(mappedBy = "patient")
    private List<MedicalReport> medicalReports;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<ReportSchedule> schedules;
}
