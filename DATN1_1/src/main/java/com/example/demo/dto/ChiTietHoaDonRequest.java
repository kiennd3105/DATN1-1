package com.example.demo.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChiTietHoaDonRequest {

    @NotNull(message = "ID không được để trống.")
    private String id;

    @NotNull(message = "Mã CTHD không được để trống.")
    @Size(max = 50, message = "Mã CTHD không được vượt quá 50 ký tự.")
    private String maCTHD;

    @NotNull(message = "Tổng tiền không được để trống.")
    @Pattern(regexp = "^\\d+(\\.\\d{1,2})?$", message = "Tổng tiền phải là số hợp lệ với tối đa 2 chữ số thập phân.")
    private String tongTien;

    @Positive(message = "Số lượng phải lớn hơn 0.")
    private int soLuong;

    @NotNull(message = "Giá bán không được để trống.")
    @Pattern(regexp = "^\\d+(\\.\\d{1,2})?$", message = "Giá bán phải là số hợp lệ với tối đa 2 chữ số thập phân.")
    private String giaBan;

    @Positive(message = "Trạng thái phải là số nguyên dương.")
    private int trangThai;

    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "Ngày tạo phải theo định dạng yyyy-MM-dd.")
    private String ngayTao;

    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "Ngày sửa phải theo định dạng yyyy-MM-dd.")
    private String ngaySua;

    @Size(max = 255, message = "Ghi chú không được vượt quá 255 ký tự.")
    private String ghiChu;

    @NotNull(message = "ID CTSP không được để trống.")
    private String idCTSP;
    @NotNull(message = "ID HD không được để trống.")
    private String idHD;
}
