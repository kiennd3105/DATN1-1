package com.example.demo.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class HinhThucHoaDonRequest {

    private String id;

    @NotEmpty(message = "Mã giao dịch không được để trống")
    @Pattern(regexp = "^[A-Za-z0-9]+$", message = "Mã giao dịch chỉ được chứa chữ cái và số")
    private String maGiaoDich;

    @NotNull(message = "Ngày tạo không được để trống")
    private String ngayTao;

    private String ngayThanhToan;

    @NotEmpty(message = "Số tiền trả không được để trống")
    @Pattern(regexp = "^\\d+(\\.\\d{2})?$", message = "Số tiền trả phải là số hợp lệ")
    private String soTienTra;

    private String ngayCapNhat;

    private String ghiChu;

    @NotNull(message = "Trạng thái không được để trống")
    private int trangThai;

    @NotNull(message = "Hình thức thanh toán không được để trống")
    private int hinhThucThanhToan;

    @NotEmpty(message = "ID hóa đơn không được để trống")
    private String idHD;
}
