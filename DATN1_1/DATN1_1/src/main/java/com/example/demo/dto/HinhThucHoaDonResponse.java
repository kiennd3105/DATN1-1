package com.example.demo.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class HinhThucHoaDonResponse {
    private String id ;
    private String maGiaoDich ;
    private String ngayTao ;
    private String  ngayThanhToan ;
    private String soTienTra ;
    private String  ngayCapNhat ;
    private String ghiChu ;
    private int trangThai ;
    private int hinhThucThanhToan ;
    private String hoaDonId;


}
