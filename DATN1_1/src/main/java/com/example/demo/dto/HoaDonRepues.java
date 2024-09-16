package com.example.demo.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class HoaDonRepues {
    @NotNull(message = "Mã hóa đơn không được để trống")
    @Size(min = 1, max = 255, message = "Mã hóa đơn phải có độ dài từ 1 đến 255 ký tự")
    private String MAHD;

    @Size(max = 255, message = "Mã voucher không được vượt quá 255 ký tự")
    private String MAVOUNCHER;

    @NotNull(message = "Ngày tạo không được để trống")
    private Date NGAYTAO;

    private Date NGAYSUA;

    @NotNull(message = "Thành tiền không được để trống")
    @Pattern(regexp = "^[0-9]+$", message = "Thành tiền phải là số")
    private String THANHTIEN;

    private Date NGAYTHANHTOAN;

    private Date NGAYNHANHANG;

    @Min(value = 0, message = "Trạng thái phải là số dương")
    private int TRANGTHAI;

    @Min(value = 0, message = "Loại hóa đơn phải là số dương")
    private int LOAIHD;

    @NotNull(message = "Phí vận chuyển không được để trống")
    @Pattern(regexp = "^[0-9]+$", message = "Phí vận chuyển phải là số")
    private String PHIVANCHUYEN;

    @NotNull(message = "Thông tin giao hàng không được để trống")
    private String THONGTINGIAOHANG;

    @Min(value = 1, message = "ID nhân viên phải là số dương")
    private int IDNV;

    @Min(value = 1, message = "ID khách hàng phải là số dương")
    private int IDKH;
}
