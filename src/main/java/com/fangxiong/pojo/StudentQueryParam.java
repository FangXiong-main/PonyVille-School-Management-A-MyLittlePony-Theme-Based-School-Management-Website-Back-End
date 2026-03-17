package com.fangxiong.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentQueryParam {
    private String name;
    private Integer degree;
    private Integer clazzId;
    private Integer page = 1;
    private Integer pageSize = 5;
}
