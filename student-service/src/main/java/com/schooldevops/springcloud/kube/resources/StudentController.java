package com.schooldevops.springcloud.kube.resources;

import com.schooldevops.springcloud.kube.models.Student;
import com.schooldevops.springcloud.kube.repositories.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @GetMapping
    public List<Student> findAll() {
        log.info("Find All");
        List<Student> all = studentRepository.findAll();
        return all;
    }

    @PostMapping
    public Student saveStudent(@RequestBody Student student) {
        log.info("Save Student " + student);
        Student savedStudent = studentRepository.save(student);
        return savedStudent;
    }

    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable("id") BigInteger id, @RequestBody Student student) {
        Optional<Student> studentInfo = studentRepository.findById(id);
        Student findStudent = studentInfo.orElseThrow(() -> new NoSuchElementException("There is not any resource by id: " + id));

        if (student.getAge() != null) {
            findStudent.setAge(student.getAge());
        }

        if (student.getAddress() != null) {
            findStudent.setAddress(student.getAddress());
        }

        if (student.getMajorSubject() != null) {
            findStudent.setMajorSubject(student.getMajorSubject());
        }

        if (student.getName() != null) {
            findStudent.setName(student.getName());
        }

        return studentRepository.save(findStudent);
    }

    @GetMapping("/subjects/{subjectName}")
    public List<Student> findBySubjectName(@PathVariable("subjectName") String subjectName) {
        List<Student> byMajorSubject = studentRepository.findByMajorSubject(subjectName);
        return byMajorSubject;
    }

    @GetMapping("/{id}")
    public Student findById(@PathVariable("id") BigInteger id) {
        Optional<Student> studentInfo = studentRepository.findById(id);
        Student findStudent = studentInfo.orElseThrow(() -> new NoSuchElementException("There is not any resource by id: " + id));

        return findStudent;
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") BigInteger id) {
        studentRepository.deleteById(id);
    }
}
