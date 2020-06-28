package com.schooldevops.springcloud.kube.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Professor {

    private Long id;

    private String name;

    private String majorSubject;

    private String grade;

    @Override
    public String toString() {
        return "Professor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", majorSubject='" + majorSubject + '\'' +
                ", grade='" + grade + '\'' +
                '}';
    }
}
