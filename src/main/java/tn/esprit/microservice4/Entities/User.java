package tn.esprit.microservice4.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

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
    private boolean assurance;

    @JsonIgnore
    @OneToMany(mappedBy = "patient")
    private List<MedicalReport> medicalReports;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<ReportSchedule> schedules;
}
