package com.example.java20.week4.demo.university.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CommonResponse {
    private int code;
    private Date date;
    private Object content;
}
