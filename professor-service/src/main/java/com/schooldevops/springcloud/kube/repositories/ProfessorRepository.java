package com.schooldevops.springcloud.kube.repositories;

import com.schooldevops.springcloud.kube.models.Professor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProfessorRepository extends CrudRepository<Professor, Long> {

    List<Professor> findByMajorSubject(String majorSubject);
}
