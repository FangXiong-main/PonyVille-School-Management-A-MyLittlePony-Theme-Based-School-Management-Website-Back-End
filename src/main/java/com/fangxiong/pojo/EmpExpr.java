package com.fangxiong.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmpExpr {
    private Integer id;
    private LocalDate begin;
    private LocalDate end;
    private String company;
    private String job;
    private Integer empId;
}
