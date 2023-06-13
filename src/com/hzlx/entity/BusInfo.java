package com.hzlx.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BusInfo implements Serializable {
    private Integer id;
    private String shopName;
    private String bossName;
    private String password;
    private String tel;
    private String address;
    private Double score;
    private Integer status;
}
