package com.easy.archiecture.entity;

import com.easy.archiecture.entity.WenduChild.CityInfo;
import com.easy.archiecture.entity.WenduChild.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Date;

@lombok.Data
@NoArgsConstructor
@AllArgsConstructor
public class Wendu {

    private String message;
    private int status;
    private String date;
    private Date time;
    private CityInfo cityInfo;
    private Data data;
}