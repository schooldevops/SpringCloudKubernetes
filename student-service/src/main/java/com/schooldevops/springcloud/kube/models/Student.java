package com.schooldevops.springcloud.kube.models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigInteger;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Document(collection = "students")
public class Student {

    @Id
    private BigInteger id;

    private String name;

    private Integer age;

    private String address;

    @Field("major_subject")
    private String majorSubject;

}