package com.easy.archiecture.controller;

import com.easy.archiecture.entity.Dev;
import com.easy.archiecture.service.DevServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@Api(tags = "设备管理")
@RequestMapping("/devs")
public class DevController {

    @Autowired
    private DevServiceImpl devservice;

    @PostMapping
    @ResponseBody
    @ApiOperation(value = "向平台添加一个设备", notes = "添加设备")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "设备名", required = true, dataType = "String"),
            @ApiImplicitParam(name = "temp", value = "当前温度", required = true, dataType = "float"),
            @ApiImplicitParam(name = "city_name", value = "所在地区", required = true, dataType = "String")
    })
    public String addDev(String name, float temp, String city_name) {
        int id = devservice.addservice(new Dev(name, temp, city_name));
        return id > 0 ? String.valueOf(id) : id == -1 ? "错误" : "该用户已存在";
    }

    @ApiOperation(value = "删除设备", notes = "通过id删除设备")
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable int id) {
        devservice.delete(id);
    }

    @ApiOperation(value = "修改设备信息", notes = "通过id修改设备温度")
    @PutMapping
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "设备的唯一标识", required = true, dataType = "int"),
            @ApiImplicitParam(name = "temp", value = "修改的温度", required = true, dataType = "float")
    })
    public void updateById(int id, float temp) {
        devservice.replace(id, temp);
    }

    @ApiOperation(value = "修改温度", notes = "通过id自动修改设备温度")
    @PutMapping("updateAuto")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "设备的唯一标识", required = true, dataType = "int"),
    })
    public void updateByIdAuto(int id) {
        devservice.updateAuto(id);
    }

    @ApiOperation(value = "刷新城市", notes = "刷新城市")
    @GetMapping("/flush")
    public void flushAll() {
        devservice.flush();
    }


    @ApiOperation(value = "获取某个设备详细信息", notes = "通过id查询设备")
    @ApiImplicitParam(name = "id", value = "设备的唯一标识", required = true, dataType = "int")
    @GetMapping(value = "/{id}", produces = "text/plain;charset=utf-8")
    public String getUserById(@PathVariable int id) {
        Dev dev = devservice.getDev(id);
        return dev.toString();
    }

    @ApiOperation(value = "查询所有设备", notes = "查询所有设备")
    @GetMapping("/all")
    public List<String> all() {
        return devservice.getAll();
    }

}
