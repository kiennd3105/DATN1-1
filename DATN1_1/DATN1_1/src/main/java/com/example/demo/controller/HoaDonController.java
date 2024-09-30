package com.example.demo.controller;

import com.example.demo.dto.HoaDonRepons;
import com.example.demo.dto.HoaDonReques;
import com.example.demo.entities.HoaDon;
import com.example.demo.entities.KhachHang;
import com.example.demo.entities.NhanVien;
import com.example.demo.repository.HoaDonRepo;
import com.example.demo.repository.KhachHangRepo;
import com.example.demo.repository.NhanVienRepo;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/hoadon")
public class HoaDonController {

    @Autowired
    private HoaDonRepo hoaDonRepo;

    @Autowired
    private KhachHangRepo khachHangRepo;

    @Autowired
    private NhanVienRepo nhanVienRepo;

    // Thêm hóa đơn mới
    @PostMapping("/add")
    public ResponseEntity<String> addHoaDon(@Valid @RequestBody HoaDonReques hoaDonRequest, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getFieldError().getDefaultMessage());
        }
        try {
            HoaDon hoaDon = new HoaDon();
            hoaDon.setMaHD(hoaDonRequest.getMaHD());
            hoaDon.setMaVoucher(hoaDonRequest.getMaVoucher());
            hoaDon.setNgayTao(String.valueOf(hoaDonRequest.getNgayTao()));
            hoaDon.setNgaySua(String.valueOf(hoaDonRequest.getNgaySua()));
            hoaDon.setThanhTien(hoaDonRequest.getThanhTien());
            hoaDon.setNgayThanhToan(String.valueOf(hoaDonRequest.getNgayThanhToan()));
            hoaDon.setNgayNhanHang(String.valueOf(hoaDonRequest.getNgayNhanHang()));
            hoaDon.setTrangThai(hoaDonRequest.getTrangThai());
            hoaDon.setLoaiHD(hoaDonRequest.getLoaiHD());
            hoaDon.setPhiVanChuyen(hoaDonRequest.getPhiVanChuyen());
            hoaDon.setThongTinGiaoHang(hoaDonRequest.getThongTinGiaoHang());

            Optional<KhachHang> optionalKhachHang = khachHangRepo.findById(hoaDonRequest.getId());
            if (optionalKhachHang.isEmpty()) {
                return ResponseEntity.badRequest().body("Không tìm thấy khách hàng với ID: " + hoaDonRequest.getId());
            }
            hoaDon.setKhachHang(optionalKhachHang.get());

            Optional<NhanVien> optionalNhanVien = nhanVienRepo.findById(hoaDonRequest.getId());
            if (optionalNhanVien.isEmpty()) {
                return ResponseEntity.badRequest().body("Không tìm thấy nhân viên với ID: " + hoaDonRequest.getId());
            }
            hoaDon.setNhanVien(optionalNhanVien.get());

            hoaDonRepo.save(hoaDon);
            return ResponseEntity.ok("Thêm hóa đơn thành công!");

        } catch (Exception e) {
            return ResponseEntity.status(500).body("Thêm hóa đơn thất bại: " + e.getMessage());
        }
    }

    // Cập nhật hóa đơn
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateHoaDon(@PathVariable String id, @Valid @RequestBody HoaDonReques hoaDonRequest, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getFieldError().getDefaultMessage());
        }
        Optional<HoaDon> optionalHoaDon = hoaDonRepo.findById(id);
        if (optionalHoaDon.isPresent()) {
            try {
                HoaDon hoaDon = optionalHoaDon.get();
                hoaDon.setMaHD(hoaDonRequest.getMaHD());
                hoaDon.setMaVoucher(hoaDonRequest.getMaVoucher());
                hoaDon.setNgayTao(String.valueOf(hoaDonRequest.getNgayTao()));
                hoaDon.setNgaySua(String.valueOf(hoaDonRequest.getNgaySua()));
                hoaDon.setThanhTien(hoaDonRequest.getThanhTien());
                hoaDon.setNgayThanhToan(String.valueOf(hoaDonRequest.getNgayThanhToan()));
                hoaDon.setNgayNhanHang(String.valueOf(hoaDonRequest.getNgayNhanHang()));
                hoaDon.setTrangThai(hoaDonRequest.getTrangThai());
                hoaDon.setLoaiHD(hoaDonRequest.getLoaiHD());
                hoaDon.setPhiVanChuyen(hoaDonRequest.getPhiVanChuyen());
                hoaDon.setThongTinGiaoHang(hoaDonRequest.getThongTinGiaoHang());

                Optional<KhachHang> optionalKhachHang = khachHangRepo.findById(hoaDonRequest.getId());
                if (optionalKhachHang.isEmpty()) {
                    return ResponseEntity.badRequest().body("Không tìm thấy khách hàng với ID: " + hoaDonRequest.getId());
                }
                hoaDon.setKhachHang(optionalKhachHang.get());

                Optional<NhanVien> optionalNhanVien = nhanVienRepo.findById(hoaDonRequest.getId());
                if (optionalNhanVien.isEmpty()) {
                    return ResponseEntity.badRequest().body("Không tìm thấy nhân viên với ID: " + hoaDonRequest.getId());
                }
                hoaDon.setNhanVien(optionalNhanVien.get());

                hoaDonRepo.save(hoaDon);
                return ResponseEntity.ok("Sửa hóa đơn thành công!");

            } catch (Exception e) {
                return ResponseEntity.status(500).body("Sửa hóa đơn thất bại: " + e.getMessage());
            }
        } else {
            return ResponseEntity.badRequest().body("Không tìm thấy hóa đơn với ID: " + id);
        }
    }

    // Xóa hóa đơn
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteHoaDon(@PathVariable String id) {
        Optional<HoaDon> optionalHoaDon = hoaDonRepo.findById(id);
        if (optionalHoaDon.isPresent()) {
            try {
                hoaDonRepo.deleteById(id);
                return ResponseEntity.ok("Xóa hóa đơn thành công!");

            } catch (Exception e) {
                return ResponseEntity.status(500).body("Xóa hóa đơn thất bại: " + e.getMessage());
            }
        } else {
            return ResponseEntity.badRequest().body("Không tìm thấy hóa đơn với ID: " + id);
        }
    }

    // Lấy danh sách hóa đơn
    @GetMapping("/list")
    public ResponseEntity<List<HoaDonRepons>> getHoaDonList() {
        try {
            List<HoaDon> hoaDonList = hoaDonRepo.findAll();
            List<HoaDonRepons> hoaDonResponses = hoaDonList.stream().map(HoaDon::toResponse).toList();
            return ResponseEntity.ok(hoaDonResponses);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    // Lấy chi tiết hóa đơn
    @GetMapping("/detail/{id}")
    public ResponseEntity<HoaDonRepons> getHoaDonById(@PathVariable String id) {
        Optional<HoaDon> optionalHoaDon = hoaDonRepo.findById(id);
        if (optionalHoaDon.isPresent()) {
            return ResponseEntity.ok(optionalHoaDon.get().toResponse());
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
