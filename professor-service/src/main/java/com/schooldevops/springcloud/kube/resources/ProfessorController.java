package com.schooldevops.springcloud.kube.resources;

import com.schooldevops.springcloud.kube.models.Professor;
import com.schooldevops.springcloud.kube.repositories.ProfessorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@RestController
@RequestMapping("/professors")
public class ProfessorController {

    private final ProfessorRepository professorRepository;

    public ProfessorController(ProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
    }

    @GetMapping
    public List<Professor> findAll() {
        Iterable<Professor> all = professorRepository.findAll();
        return StreamSupport.stream(all.spliterator(), false).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public Professor findById(@PathVariable("id") Long id) {
        return professorRepository.findById(id).orElse(null);
    }

    @GetMapping("/subjects/{subjectName}")
    public List<Professor> findBySubjectName(@PathVariable("subjectName") String subjectName) {
        log.info(String.format("Resource Call by Subject %s", subjectName));
        return professorRepository.findByMajorSubject(subjectName);
    }

    @PostMapping
    public Professor join(@RequestBody Professor professor) {
        return professorRepository.save(professor);
    }

    @PutMapping("/{id}")
    public Professor modify(@PathVariable("id") Long id, @RequestBody Professor professor) {
        Optional<Professor> byId = professorRepository.findById(id);
        Professor professorFromDB = byId.orElseThrow(() -> new NoSuchElementException("There is not entity by id " + id));

        if (professor.getName() != null) {
            professorFromDB.setName(professor.getName());
        }

        if (professor.getGrade() != null) {
            professorFromDB.setGrade(professor.getGrade());
        }

        if (professor.getMajorSubject() != null) {
            professorFromDB.setMajorSubject(professor.getMajorSubject());
        }
        return professorRepository.save(professorFromDB);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        professorRepository.deleteById(id);
    }
}

