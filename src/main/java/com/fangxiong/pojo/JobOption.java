package com.fangxiong.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobOption {  // 饼图数据
    private List jobList;
    private List dataList;
}
