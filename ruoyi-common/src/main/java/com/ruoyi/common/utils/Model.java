package com.ruoyi.common.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @program: ruoyi
 * @description:
 * @author: zhaozh
 * @create: 2021/5/13
 **/
@Data
@AllArgsConstructor
@ToString
@NoArgsConstructor
public class Model {
    private Integer id;

    private String name;

    private String author;

    private Integer type;

    public Model(String name, String author, Integer type) {
        this.name = name;
        this.author = author;
        this.type = type;
    }
}
