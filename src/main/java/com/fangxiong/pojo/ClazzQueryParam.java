package com.fangxiong.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClazzQueryParam {
    private String name;
    @DateTimeFormat(pattern = "yyyy-MM-dd")//配置日期格式
    private LocalDate begin;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate end;
    private Integer page = 1; //设置默认页
    private Integer pageSize = 5; //设置默认每页显示条数
}
