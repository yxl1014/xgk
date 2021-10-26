package com.easy.archiecture.entity.WenduChild;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Forecast {

    private String date;
    private String high;
    private String low;
    private Date ymd;
    private String week;
    private String sunrise;
    private String sunset;
    private int aqi;
    private String fx;
    private String fl;
    private String type;
    private String notice;
}
