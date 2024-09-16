package com.example.demo.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LichSuHoaDonRequest {
    @NotNull(message = "Ngày tạo không được để trống")
    private Date NGAYTAO;

    @NotNull(message = "Trạng thái không được để trống")
    private int TRANGTHAI;

    @NotNull(message = "Người thao tác không được để trống")
    private String NGUOITHAOTAC;

    @NotNull(message = "ID hóa đơn không được để trống")
    private Integer IDHD;
}
