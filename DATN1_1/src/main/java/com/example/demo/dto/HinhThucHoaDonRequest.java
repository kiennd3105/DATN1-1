package com.example.demo.dto;

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
    @NotEmpty(message = "Mã giao dịch không được để trống")
    private String MAGIAODICH;

    @NotNull(message = "Ngày tạo không được để trống")
    private Date NGAYTAO;

    @NotNull(message = "Ngày thanh toán không được để trống")
    private Date NGAYTHANHTOAN;

    @NotEmpty(message = "Số tiền trả không được để trống")
    private String SOTIENTRA;

    @NotNull(message = "Ngày cập nhật không được để trống")
    private Date NGAYCAPNHAT;

    @NotEmpty(message = "Ghi chú không được để trống")
    private String GHICHU;

    @NotNull(message = "Trạng thái không được để trống")
    private int TRANGTHAI;

    @NotNull(message = "Hình thức thanh toán không được để trống")
    private int HINHTHUCTHANHTOAN;

    @NotNull(message = "ID hóa đơn không được để trống")
    private Integer IDHD;
}
