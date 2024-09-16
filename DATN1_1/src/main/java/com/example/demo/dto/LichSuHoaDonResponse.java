package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LichSuHoaDonResponse {
    private Integer ID;
    private Date NGAYTAO;
    private int TRANGTHAI;
    private String NGUOITHAOTAC;
    private Integer IDHD;
}
