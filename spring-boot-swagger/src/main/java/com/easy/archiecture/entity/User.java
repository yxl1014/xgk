package com.easy.archiecture.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "用户信息",description = "用户信息")
public class User {
    @ApiModelProperty(value = "用户ID", name = "id", example = "abc", required = true)
    private String id;

    @ApiModelProperty(value = "用户年龄", name = "age", example = "12", required = true)
    private int age;

    @ApiModelProperty(value = "用户姓名", name = "name", example = "名字", required = true)
    private String name;

    public User(String id, int age, String name) {
        this.id = id;
        this.age = age;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
