package com.example.demo.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class HoaDonReques {

    private String id;

    @NotEmpty(message = "Mã hóa đơn không được để trống")
    private String maHD;

    private String maVoucher;

    @NotNull(message = "Ngày tạo không được để trống")
    private LocalDate ngayTao;

    private LocalDate ngaySua;

    @NotEmpty(message = "Thanh tiền không được để trống")
    private String thanhTien;

    private LocalDate ngayThanhToan;

    private LocalDate ngayNhanHang;

    @Min(value = 0, message = "Trạng thái phải lớn hơn hoặc bằng 0")
    private int trangThai;

    @Min(value = 0, message = "Loại hóa đơn phải lớn hơn hoặc bằng 0")
    private int loaiHD;

    private String phiVanChuyen;

    private String thongTinGiaoHang;

}
