package com.example.demo.controller;

import com.example.demo.dto.HinhThucHoaDonRequest;
import com.example.demo.dto.HinhThucHoaDonResponse;
import com.example.demo.entities.HinhThucHoaDon;
import com.example.demo.entities.HoaDon;
import com.example.demo.repository.HinhThucHoaDonRepo;
import com.example.demo.repository.HoaDonRepo;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Optional;

@RestController
@RequestMapping("/hinh-thuc-hoa-don")
public class HinhThucHoaDonController {

    @Autowired
    private HinhThucHoaDonRepo hinhThucHoaDonRepo;

    @Autowired
    private HoaDonRepo hoaDonRepo;

    // Thêm mới
    @PostMapping("/add")
    public ResponseEntity<String> addHinhThucHoaDon(@Valid @RequestBody HinhThucHoaDonRequest request) {
        try {
            HinhThucHoaDon entity = new HinhThucHoaDon();
            entity.setMaGiaoDich(request.getMaGiaoDich());
            entity.setNgayTao(request.getNgayTao() != null ? String.valueOf(LocalDate.parse(request.getNgayTao())) : null);
            entity.setNgayThanhToan(request.getNgayThanhToan() != null ? String.valueOf(LocalDate.parse(request.getNgayThanhToan())) : null);
            entity.setSoTienTra(request.getSoTienTra());
            entity.setNgayCapNhat(request.getNgayCapNhat() != null ? String.valueOf(LocalDate.parse(request.getNgayCapNhat())) : null);
            entity.setGhiChu(request.getGhiChu());
            entity.setTrangThai(request.getTrangThai());
            entity.setHinhThucThanhToan(request.getHinhThucThanhToan());

            Optional<HoaDon> hoaDonOpt = hoaDonRepo.findById(request.getHoaDonId());
            if (hoaDonOpt.isPresent()) {
                entity.setHoaDon(hoaDonOpt.get());
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Hóa đơn không tồn tại");
            }

            hinhThucHoaDonRepo.save(entity);
            return ResponseEntity.status(HttpStatus.CREATED).body("Thêm mới thành công");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Thêm mới thất bại: " + e.getMessage());
        }
    }

    // Sửa
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateHinhThucHoaDon(@PathVariable String id, @Valid @RequestBody HinhThucHoaDonRequest request) {
        Optional<HinhThucHoaDon> optionalHinhThucHoaDon = hinhThucHoaDonRepo.findById(id);

        if (optionalHinhThucHoaDon.isPresent()) {
            try {
                HinhThucHoaDon entity = optionalHinhThucHoaDon.get();
                entity.setMaGiaoDich(request.getMaGiaoDich());
                entity.setNgayTao(request.getNgayTao() != null ? String.valueOf(LocalDate.parse(request.getNgayTao())) : null);
                entity.setNgayThanhToan(request.getNgayThanhToan() != null ? String.valueOf(LocalDate.parse(request.getNgayThanhToan())) : null);
                entity.setSoTienTra(request.getSoTienTra());
                entity.setNgayCapNhat(request.getNgayCapNhat() != null ? String.valueOf(LocalDate.parse(request.getNgayCapNhat())) : null);
                entity.setGhiChu(request.getGhiChu());
                entity.setTrangThai(request.getTrangThai());
                entity.setHinhThucThanhToan(request.getHinhThucThanhToan());

                Optional<HoaDon> hoaDonOpt = hoaDonRepo.findById(request.getHoaDonId());
                if (hoaDonOpt.isPresent()) {
                    entity.setHoaDon(hoaDonOpt.get());
                } else {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Hóa đơn không tồn tại");
                }

                hinhThucHoaDonRepo.save(entity);
                return ResponseEntity.ok("Cập nhật thành công");
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Cập nhật thất bại: " + e.getMessage());
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Không tìm thấy hình thức hóa đơn");
        }
    }

    // Xóa
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteHinhThucHoaDon(@PathVariable String id) {
        try {
            if (hinhThucHoaDonRepo.existsById(id)) {
                hinhThucHoaDonRepo.deleteById(id);
                return ResponseEntity.ok("Xóa thành công");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Không tìm thấy hình thức hóa đơn");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Xóa thất bại: " + e.getMessage());
        }
    }

    // Lấy danh sách
    @GetMapping("/list")
    public ResponseEntity<?> getHinhThucHoaDonList() {
        try {
            return ResponseEntity.ok(hinhThucHoaDonRepo.findAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Lấy danh sách thất bại: " + e.getMessage());
        }
    }

    // Lấy chi tiết
    @GetMapping("/detail/{id}")
    public ResponseEntity<?> getHinhThucHoaDonById(@PathVariable String id) {
        Optional<HinhThucHoaDon> optionalHinhThucHoaDon = hinhThucHoaDonRepo.findById(id);

        if (optionalHinhThucHoaDon.isPresent()) {
            return ResponseEntity.ok(optionalHinhThucHoaDon.get().toResponse());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Không tìm thấy hình thức hóa đơn");
        }
    }
}
