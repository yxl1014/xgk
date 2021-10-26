package com.easy.archiecture.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@Api(tags = "用户模块")
@RequestMapping("/user")
public class UserController {

    private Map<Integer, String> map = new HashMap<>();

    public UserController() {
        map.put(1, "张三");
        map.put(2, "李四");
        map.put(3, "王五");
        map.put(4, "赵六");
    }


    @ApiOperation(value = "获取某个用户详细信息", notes = "通过id查询用户")
    @ApiImplicitParam(name = "id", value = "用户的唯一标识", required = true, dataType = "int")
    @GetMapping(value = "/{id}", produces = "text/plain;charset=utf-8")
    public String getUserById(@PathVariable int id) {
        return map.get(id);
    }

    @ApiOperation(value = "修改用户信息", notes = "通过id修改用户")
    @PutMapping("/{id}")
    public String updateById(@PathVariable int id) {
        return map.replace(id, "秦始皇");
    }

    @ApiOperation(value = "添加用户", notes = "添加用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户的唯一标识", required = true, dataType = "int"),
            @ApiImplicitParam(name = "name", value = "用户名称", required = true, dataType = "String")
    })
    @PostMapping
    public void insert(int id, String name) {
        map.put(id, name);
    }

    @ApiOperation(value = "删除用户信息", notes = "通过id删除用户")
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable int id) {
        map.remove(id);
    }

    @ApiOperation(value = "查询所有用户", notes = "查询所有用户")
    @GetMapping("/all")
    public Map<Integer, String> all() {
        return map;
    }

}
