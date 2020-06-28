package com.schooldevops.springcloud.kube.models;

import lombok.*;

import java.math.BigInteger;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Student {

    private BigInteger id;

    private String name;

    private Integer age;

    private String address;

    private String majorSubject;

}