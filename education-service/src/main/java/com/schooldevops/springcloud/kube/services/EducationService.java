package com.schooldevops.springcloud.kube.services;

import com.schooldevops.springcloud.kube.models.Education;
import com.schooldevops.springcloud.kube.models.Professor;
import com.schooldevops.springcloud.kube.models.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Slf4j
@Service
public class EducationService {

    private final RestTemplate restTemplate;
    private final DiscoveryClient discoveryClient;

    public EducationService(RestTemplate restTemplate, DiscoveryClient discoveryClient) {
        this.restTemplate = restTemplate;
        this.discoveryClient = discoveryClient;
    }

    public List<String> getServerList() {
        return discoveryClient.getServices();
    }

    public List<Student> getAllStudents() {
        ResponseEntity<List<Student>> exchange = this.restTemplate.exchange("http://student-service:8081/students", HttpMethod.GET, null, new ParameterizedTypeReference<List<Student>>() {
        });

        log.info("Eschange Infos: " + exchange.getBody());
        exchange.getBody().stream().forEach(item -> log.info(item.toString()));
        return exchange.getBody();
    }

    public Education getEducationByMajorSubject(String majorSubject) {
        List<Student> students = getStudentsByMajorSubject(majorSubject);
        List<Professor> professors = getProfessorsByMajorSubject(majorSubject);

        return Education.builder()
                .majorSubject(majorSubject)
                .professors(professors)
                .students(students)
                .build();
    }

    private List<Student> getStudentsByMajorSubject(String majorSubject) {
        ResponseEntity<List<Student>> exchange = this.restTemplate.exchange("http://student-service:8081/students/subjects/" + majorSubject, HttpMethod.GET, null, new ParameterizedTypeReference<List<Student>>() {
        });

        log.info("Eschange Student Infos: " + exchange.getBody());
        exchange.getBody().stream().forEach(item -> log.info(item.toString()));
        return exchange.getBody();
    }


    private List<Professor> getProfessorsByMajorSubject(String majorSubject) {
        ResponseEntity<List<Professor>> exchange = this.restTemplate.exchange("http://professor-service:8181/professors/subjects/" + majorSubject, HttpMethod.GET, null, new ParameterizedTypeReference<List<Professor>>() {
        });

        log.info("Eschange Professor Infos: " + exchange.getBody());
        exchange.getBody().stream().forEach(item -> log.info(item.toString()));
        return exchange.getBody();
    }

}
