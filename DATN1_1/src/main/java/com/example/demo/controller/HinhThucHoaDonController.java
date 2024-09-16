package com.example.demo.controller;

import com.example.demo.dto.HinhThucHoaDonRequest;
import com.example.demo.entities.HinhThucHoaDon;
import com.example.demo.entities.HoaDon;
import com.example.demo.repository.HinhThucHoaDonRepo;
import com.example.demo.repository.HoaDonRepo;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/hinh-thuc-hoa-don")
public class HinhThucHoaDonController {

    @Autowired
    private HinhThucHoaDonRepo hinhThucHoaDonRepository;

    @Autowired
    private HoaDonRepo hoaDonRepository;

    // Thêm mới
    @PostMapping("/add")
    public ResponseEntity<String> addHinhThucHoaDon(@Valid @RequestBody HinhThucHoaDonRequest request) {
        try {
            // Kiểm tra xem HoaDon có tồn tại không
            Optional<HoaDon> optionalHoaDon = hoaDonRepository.findById(request.getIDHD());
            if (optionalHoaDon.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Hóa đơn không tồn tại");
            }

            HinhThucHoaDon entity = new HinhThucHoaDon();
            entity.setMAGIAODICH(request.getMAGIAODICH());
            entity.setNGAYTAO(request.getNGAYTAO());
            entity.setNGAYTHANHTOAN(request.getNGAYTHANHTOAN());
            entity.setSOTIENTRA(request.getSOTIENTRA());
            entity.setNGAYCAPNHAT(request.getNGAYCAPNHAT());
            entity.setGHICHU(request.getGHICHU());
            entity.setTRANGTHAI(request.getTRANGTHAI());
            entity.setHINHTHUCTHANHTOAN(request.getHINHTHUCTHANHTOAN());
            entity.setHoaDon(optionalHoaDon.get());

            hinhThucHoaDonRepository.save(entity);
            return ResponseEntity.status(HttpStatus.CREATED).body("Thêm mới thành công");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Thêm mới thất bại");
        }
    }

    // Sửa
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateHinhThucHoaDon(@PathVariable Integer id, @Valid @RequestBody HinhThucHoaDonRequest request) {
        Optional<HinhThucHoaDon> optionalHinhThucHoaDon = hinhThucHoaDonRepository.findById(id);

        if (optionalHinhThucHoaDon.isPresent()) {
            try {
                // Kiểm tra xem HoaDon có tồn tại không
                Optional<HoaDon> optionalHoaDon = hoaDonRepository.findById(request.getIDHD());
                if (optionalHoaDon.isEmpty()) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Hóa đơn không tồn tại");
                }

                HinhThucHoaDon entity = optionalHinhThucHoaDon.get();
                entity.setMAGIAODICH(request.getMAGIAODICH());
                entity.setNGAYTAO(request.getNGAYTAO());
                entity.setNGAYTHANHTOAN(request.getNGAYTHANHTOAN());
                entity.setSOTIENTRA(request.getSOTIENTRA());
                entity.setNGAYCAPNHAT(request.getNGAYCAPNHAT());
                entity.setGHICHU(request.getGHICHU());
                entity.setTRANGTHAI(request.getTRANGTHAI());
                entity.setHINHTHUCTHANHTOAN(request.getHINHTHUCTHANHTOAN());
                entity.setHoaDon(optionalHoaDon.get());

                hinhThucHoaDonRepository.save(entity);
                return ResponseEntity.ok("Cập nhật thành công");
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Cập nhật thất bại");
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Không tìm thấy hình thức hóa đơn");
        }
    }

    // Xóa
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteHinhThucHoaDon(@PathVariable Integer id) {
        try {
            if (hinhThucHoaDonRepository.existsById(id)) {
                hinhThucHoaDonRepository.deleteById(id);
                return ResponseEntity.ok("Xóa thành công");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Không tìm thấy hình thức hóa đơn");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Xóa thất bại");
        }
    }

    // Lấy danh sách
    @GetMapping("/list")
    public ResponseEntity<?> getHinhThucHoaDonList() {
        try {
            return ResponseEntity.ok(hinhThucHoaDonRepository.findAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Lấy danh sách thất bại");
        }
    }

    // Lấy chi tiết
    @GetMapping("/detail/{id}")
    public ResponseEntity<?> getHinhThucHoaDonById(@PathVariable Integer id) {
        Optional<HinhThucHoaDon> optionalHinhThucHoaDon = hinhThucHoaDonRepository.findById(id);

        if (optionalHinhThucHoaDon.isPresent()) {
            return ResponseEntity.ok(optionalHinhThucHoaDon.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Không tìm thấy hình thức hóa đơn");
        }
    }
}
