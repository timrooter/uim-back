//package com.tgroup.internmanager.config;
//
//import com.tgroup.internmanager.model.entity.*;
//import com.tgroup.internmanager.repository.*;
//import lombok.RequiredArgsConstructor;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.util.*;
//
//@Component
//@RequiredArgsConstructor
//public class DataSeeder implements CommandLineRunner {
//
//    private final UniversityRepository universityRepo;
//    private final CompanyRepository companyRepo;
//    private final StudentRepository studentRepo;
//    private final ResumeRepository resumeRepo;
//    private final VacancyRepository vacancyRepo;
//    private final ApplicationRepository applicationRepo;
//
//    @Override
//    public void run(String... args) {
//        if (!universityRepo.findAll().isEmpty()) return; // чтобы не дублировалось
//
//        // === Университеты ===
//        University u1 = universityRepo.save(
//                University.builder()
//                        .universityName("KazNU")
//                        .email("kaznu@uni.kz")
//                        .password("1234")
//                        .build()
//        );
//
//        University u2 = universityRepo.save(
//                University.builder()
//                        .universityName("AITU")
//                        .email("aitu@uni.kz")
//                        .password("1234")
//                        .build()
//        );
//
//        University u3 = universityRepo.save(
//                University.builder()
//                        .universityName("ENU")
//                        .email("enu@uni.kz")
//                        .password("1234")
//                        .build()
//        );
//
//
//        // === Компании и вакансии ===
//        List<Company> companies = new ArrayList<>();
//        for (int i = 1; i <= 3; i++) {
//            Company company = companyRepo.save(
//                    Company.builder()
//                            .companyName("Company " + i)
//                            .email("c" + i + "@mail.com")
//                            .password("1234")
//                            .build()
//            );
//
//            companies.add(company);
//            for (int j = 1; j <= 3; j++) {
//                Vacancy v = new Vacancy();
//                v.setCompany(company);
//                v.setCompanyName(company.getCompanyName());
//                v.setTitle("Vacancy " + j + " at " + company.getCompanyName());
//                v.setCategory("IT");
//                v.setLocation("Almaty");
//                v.setWorkType("Онлайн");
//                v.setDescription("Описание вакансии " + j);
//                v.setRequirements("Требования " + j);
//                v.setCreatedAt(LocalDateTime.now());
//                v.setUpdatedAt(LocalDateTime.now());
//                vacancyRepo.save(v);
//            }
//        }
//
//        // === Студенты и резюме ===
//        List<Student> students = new ArrayList<>();
//        for (int i = 1; i <= 15; i++) {
//            University uni = i <= 5 ? u1 : i <= 10 ? u2 : u3;
//            Student s = new Student();
//            s.setFullName("Student " + i);
//            s.setEmail("s" + i + "@mail.com");
//            s.setPassword("1234");
//            s.setUniversity(uni);
//            studentRepo.save(s);
//            students.add(s);
//        }
//
//        // === Резюме для 7 студентов ===
//        List<Resume> resumes = new ArrayList<>();
//        for (int i = 0; i < 7; i++) {
//            Student s = students.get(i);
//            Resume r = new Resume();
//            r.setStudent(s);
//            r.setTitle("Resume " + s.getFullName());
//            r.setSummary("Кратко о себе");
//            r.setSkills("Java, SQL");
//            r.setEducation("Bachelor");
//            r.setGender("Мужчина");
//            r.setPhone("+7701" + i + i + i + i + i + i + i);
//            r.setLocation("Astana");
//            r.setBirthday("2000-01-01");
//            r.setSocialMedia("vk.com/student" + i);
//            r.setPortfolioLink("github.com/student" + i);
//            r.setCreatedAt(LocalDateTime.now());
//            r.setUpdatedAt(LocalDateTime.now());
//            resumeRepo.save(r);
//            resumes.add(r);
//        }
//
//        // === Отклики ===
//        List<Vacancy> allVacancies = vacancyRepo.findAll();
//
//        Application a1 = new Application(null, students.get(0), allVacancies.get(0), resumes.get(0),
//                ApplicationStatus.PENDING, null, now(), now());
//        Application a2 = new Application(null, students.get(1), allVacancies.get(1), resumes.get(1),
//                ApplicationStatus.ACCEPTED, "Отличный кандидат", now(), now());
//        Application a3 = new Application(null, students.get(2), allVacancies.get(2), resumes.get(2),
//                ApplicationStatus.ACCEPTED, "Принят в отдел", now(), now());
//        Application a4 = new Application(null, students.get(3), allVacancies.get(3), resumes.get(3),
//                ApplicationStatus.REJECTED, "Недостаточно опыта", now(), now());
//        Application a5 = new Application(null, students.get(4), allVacancies.get(4), resumes.get(4),
//                ApplicationStatus.REJECTED, "Не подошёл по профилю", now(), now());
//
//        applicationRepo.saveAll(List.of(a1, a2, a3, a4, a5));
//    }
//
//    private LocalDateTime now() {
//        return LocalDateTime.now();
//    }
//}
