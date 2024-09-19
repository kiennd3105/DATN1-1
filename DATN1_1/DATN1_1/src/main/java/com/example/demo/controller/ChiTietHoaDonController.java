package com.example.demo.controller;

import com.example.demo.dto.ChiTietHoaDonRequest;
import com.example.demo.entities.ChiTietHoaDon;
import com.example.demo.repository.ChiTietHoaDonRepo;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.Optional;

@RestController
@RequestMapping("/chi-tiet-hoa-don")
public class ChiTietHoaDonController {

    @Autowired
    private ChiTietHoaDonRepo chiTietHoaDonRepo;

    // Thêm mới
    @PostMapping("/add")
    public ResponseEntity<String> addChiTietHoaDon(@Valid @RequestBody ChiTietHoaDonRequest request) {
        try {
            ChiTietHoaDon entity = new ChiTietHoaDon();
            entity.setMaCTHD(request.getMaCTHD());
            entity.setTongTien(request.getTongTien());
            entity.setSoLuong(request.getSoLuong());
            entity.setGiaBan(request.getGiaBan());
            entity.setTrangThai(request.getTrangThai());
            entity.setNgayTao(request.getNgayTao() != null ? String.valueOf(Date.valueOf(request.getNgayTao())) : null);
            entity.setNgaySua(request.getNgaySua() != null ? String.valueOf(Date.valueOf(request.getNgaySua())) : null);
            entity.setIdCTSP(request.getIdCTSP());

            // Xử lý ID hóa đơn
            if (request.getIdHD()!= null) {
                // Tìm và thiết lập hóa đơn nếu cần
                // entity.setHoaDon(hdRepository.findById(request.getIdHoaDon()).orElse(null));
            }

            chiTietHoaDonRepo.save(entity);
            return ResponseEntity.status(HttpStatus.CREATED).body("Thêm mới thành công");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Thêm mới thất bại: " + e.getMessage());
        }
    }

    // Sửa
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateChiTietHoaDon(@PathVariable String id, @Valid @RequestBody ChiTietHoaDonRequest request) {
        Optional<ChiTietHoaDon> optionalChiTietHoaDon = chiTietHoaDonRepo.findById(id);

        if (optionalChiTietHoaDon.isPresent()) {
            try {
                ChiTietHoaDon entity = optionalChiTietHoaDon.get();
                entity.setMaCTHD(request.getMaCTHD());
                entity.setTongTien(request.getTongTien());
                entity.setSoLuong(request.getSoLuong());
                entity.setGiaBan(request.getGiaBan());
                entity.setTrangThai(request.getTrangThai());
                entity.setNgayTao(request.getNgayTao() != null ? String.valueOf(Date.valueOf(request.getNgayTao())) : null);
                entity.setNgaySua(request.getNgaySua() != null ? String.valueOf(Date.valueOf(request.getNgaySua())) : null);
                entity.setIdCTSP(request.getIdCTSP());

                // Xử lý ID hóa đơn
                if (request.getIdHD() != null) {

                }

                chiTietHoaDonRepo.save(entity);
                return ResponseEntity.ok("Cập nhật thành công");
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Cập nhật thất bại: " + e.getMessage());
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Không tìm thấy chi tiết hóa đơn");
        }
    }

    // Xóa
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteChiTietHoaDon(@PathVariable String id) {
        try {
            if (chiTietHoaDonRepo.existsById(id)) {
                chiTietHoaDonRepo.deleteById(id);
                return ResponseEntity.ok("Xóa thành công");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Không tìm thấy chi tiết hóa đơn");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Xóa thất bại: " + e.getMessage());
        }
    }

    // Lấy danh sách
    @GetMapping("/list")
    public ResponseEntity<?> getChiTietHoaDonList() {
        try {
            return ResponseEntity.ok(chiTietHoaDonRepo.findAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Lấy danh sách thất bại: " + e.getMessage());
        }
    }

    // Lấy chi tiết
    @GetMapping("/detail/{id}")
    public ResponseEntity<?> getChiTietHoaDonById(@PathVariable String id) {
        Optional<ChiTietHoaDon> optionalChiTietHoaDon = chiTietHoaDonRepo.findById(id);

        if (optionalChiTietHoaDon.isPresent()) {
            return ResponseEntity.ok(optionalChiTietHoaDon.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Không tìm thấy chi tiết hóa đơn");
        }
    }
}
