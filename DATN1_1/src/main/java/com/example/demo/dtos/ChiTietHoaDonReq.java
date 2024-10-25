package com.example.demo.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ChiTietHoaDonReq {

    private String id;

    private String maCTHD;

    private String tongTien;

    private int soLuong;

    private String giaBan;

    private int trangThai;
    private LocalDateTime ngayTao;
    private LocalDateTime ngaySua;
    private String ghiChu;
    @NotNull(message = "ID hóa đơn không được để trống")
    private String idHD;

    private String idCTSP;

}
