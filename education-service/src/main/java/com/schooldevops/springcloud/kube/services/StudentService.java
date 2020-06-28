package com.schooldevops.springcloud.kube.services;

import com.schooldevops.springcloud.kube.models.Student;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name="student-service", url = "http://student-service:8081")
public interface StudentService {

    @RequestMapping(value = "/api/students", method = RequestMethod.GET)
    List<Student> getAllStudents();

}
