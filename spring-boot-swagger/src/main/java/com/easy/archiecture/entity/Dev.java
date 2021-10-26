package com.easy.archiecture.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Dev {
    private int id;//唯一id
    private String name;//设备名
    private float temperature;//温度
    private String city_name;//区域名
    private String city_code;//区域代号

    public Dev(String name, float temperature, String city_name) {
        this.name = name;
        this.temperature = temperature;
        this.city_name = city_name;
    }

    @Override
    public String toString() {
        return "Dev{" +
                "设备唯一id=" + id +
                ", 设备名='" + name + '\'' +
                ", 设备温度=" + temperature +
                ", 设备所在区域='" + city_name + '\'' +
                ", 设备所在区域代号='" + city_code + '\'' +
                '}';
    }
}