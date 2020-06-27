package com.schooldevops.springcloud.kube.repositories;

import com.schooldevops.springcloud.kube.models.Student;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.math.BigInteger;
import java.util.List;

public interface StudentRepository extends MongoRepository<Student, BigInteger> {

    List<Student> findByMajorSubject(String majorSubject);
}
