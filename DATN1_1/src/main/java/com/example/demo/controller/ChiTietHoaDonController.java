package com.example.demo.controller;

import com.example.demo.dto.ChiTietHoaDonRequest;
import com.example.demo.entities.ChiTietHoaDon;
import com.example.demo.entities.HoaDon;
import com.example.demo.repository.ChiTietHoaDonRepo;
import com.example.demo.repository.HoaDonRepo;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/chi-tiet-hoa-don")
public class ChiTietHoaDonController {

    @Autowired
    private ChiTietHoaDonRepo chiTietHoaDonRepo;

    @Autowired
    private HoaDonRepo hoaDonRepo;

    // Thêm mới
    @PostMapping("/add")
    public ResponseEntity<String> addChiTietHoaDon(@Valid @RequestBody ChiTietHoaDonRequest request) {
        try {
            Optional<HoaDon> hoaDonOpt = hoaDonRepo.findById(request.getIDHOADON());
            if (!hoaDonOpt.isPresent()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Hóa đơn không tồn tại");
            }

            ChiTietHoaDon entity = new ChiTietHoaDon();
            entity.setMACTHD(request.getMACTHD());
            entity.setTONGTIEN(request.getTONGTIEN());
            entity.setSOLUONG(request.getSOLUONG());
            entity.setGIABAN(request.getGIABAN());
            entity.setTRANGTHAI(request.getTRANGTHAI());
            entity.setNGAYTAO(request.getNGAYTAO());
            entity.setNGAYSUA(request.getNGAYSUA());
            entity.setHoaDon(hoaDonOpt.get());
            entity.setIDCTSP(request.getIDCTSP());
            entity.setGHICHU(request.getGHICHU());

            chiTietHoaDonRepo.save(entity);
            return ResponseEntity.status(HttpStatus.CREATED).body("Thêm mới thành công");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Thêm mới thất bại");
        }
    }

    // Sửa
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateChiTietHoaDon(@PathVariable Integer id, @Valid @RequestBody ChiTietHoaDonRequest request) {
        Optional<ChiTietHoaDon> optionalChiTietHoaDon = chiTietHoaDonRepo.findById(id);

        if (optionalChiTietHoaDon.isPresent()) {
            try {
                ChiTietHoaDon entity = optionalChiTietHoaDon.get();

                Optional<HoaDon> hoaDonOpt = hoaDonRepo.findById(request.getIDHOADON());
                if (!hoaDonOpt.isPresent()) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Hóa đơn không tồn tại");
                }

                entity.setMACTHD(request.getMACTHD());
                entity.setTONGTIEN(request.getTONGTIEN());
                entity.setSOLUONG(request.getSOLUONG());
                entity.setGIABAN(request.getGIABAN());
                entity.setTRANGTHAI(request.getTRANGTHAI());
                entity.setNGAYTAO(request.getNGAYTAO());
                entity.setNGAYSUA(request.getNGAYSUA());
                entity.setHoaDon(hoaDonOpt.get());
                entity.setIDCTSP(request.getIDCTSP());
                entity.setGHICHU(request.getGHICHU());

                chiTietHoaDonRepo.save(entity);
                return ResponseEntity.ok("Cập nhật thành công");
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Cập nhật thất bại");
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Không tìm thấy chi tiết hóa đơn");
        }
    }

    // Xóa
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteChiTietHoaDon(@PathVariable Integer id) {
        try {
            if (chiTietHoaDonRepo.existsById(id)) {
                chiTietHoaDonRepo.deleteById(id);
                return ResponseEntity.ok("Xóa thành công");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Không tìm thấy chi tiết hóa đơn");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Xóa thất bại");
        }
    }

    // Lấy danh sách
    @GetMapping("/list")
    public ResponseEntity<?> getChiTietHoaDonList() {
        try {
            return ResponseEntity.ok(chiTietHoaDonRepo.findAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Lấy danh sách thất bại");
        }
    }

    // Lấy chi tiết
    @GetMapping("/detail/{id}")
    public ResponseEntity<?> getChiTietHoaDonById(@PathVariable Integer id) {
        Optional<ChiTietHoaDon> optionalChiTietHoaDon = chiTietHoaDonRepo.findById(id);

        if (optionalChiTietHoaDon.isPresent()) {
            return ResponseEntity.ok(optionalChiTietHoaDon.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Không tìm thấy chi tiết hóa đơn");
        }
    }
}
