package com.schooldevops.springcloud.kube.resources;

import com.schooldevops.springcloud.kube.models.Education;
import com.schooldevops.springcloud.kube.models.Student;
import com.schooldevops.springcloud.kube.services.EducationService;
import com.schooldevops.springcloud.kube.services.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class EducationController {

    private final EducationService educationService;
    private final StudentService studentService;

    public EducationController(EducationService educationService, StudentService studentService) {
        this.educationService = educationService;
        this.studentService = studentService;
    }

    @GetMapping("/servers")
    public List<String> getServerList() {
        log.info("---------- getServerList");
        return educationService.getServerList();
    }

    @GetMapping("/students")
    public List<Student> getAllStudents() {
        log.info("---------- getAllStudents");
        return educationService.getAllStudents();
    }

    @GetMapping("/education/{majorSubject}")
    public Education getEducationByMajorSubject(@PathVariable String majorSubject) {
        log.info("---------- getEducationByMajorSubject");
        return educationService.getEducationByMajorSubject(majorSubject);
    }

    @GetMapping("/feign/students")
    public List<Student> getAllStudentsByFeign() {
        log.info("---------- fetchAll Student By Feign");
        return studentService.getAllStudents();
    }


}
