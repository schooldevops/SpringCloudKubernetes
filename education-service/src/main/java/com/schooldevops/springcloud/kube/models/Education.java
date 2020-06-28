package com.schooldevops.springcloud.kube.models;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Education {

    private String majorSubject;
    private List<Professor> professors = new ArrayList<>();
    private List<Student> students = new ArrayList<>();

}
