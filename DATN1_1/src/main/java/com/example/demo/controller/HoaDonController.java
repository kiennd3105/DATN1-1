package com.example.demo.controller;

import com.example.demo.dto.HoaDonRepues;
import com.example.demo.dto.HoaDonRepons;
import com.example.demo.entities.HoaDon;
import com.example.demo.repository.HoaDonRepo;
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

    // Thêm hóa đơn mới
    @PostMapping("/add")
    public ResponseEntity<String> addHoaDon(@Valid @RequestBody HoaDonRepues hoaDonRequest, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getFieldError().getDefaultMessage());
        }
        try {
            HoaDon hoaDon = new HoaDon();
            hoaDon.setMAHD(hoaDonRequest.getMAHD());
            hoaDon.setMAVOUNCHER(hoaDonRequest.getMAVOUNCHER());
            hoaDon.setNGAYTAO(hoaDonRequest.getNGAYTAO());
            hoaDon.setNGAYSUA(hoaDonRequest.getNGAYSUA());
            hoaDon.setTHANHTIEN(hoaDonRequest.getTHANHTIEN());
            hoaDon.setNGAYTHANHTOAN(hoaDonRequest.getNGAYTHANHTOAN());
            hoaDon.setNGAYNHANHANG(hoaDonRequest.getNGAYNHANHANG());
            hoaDon.setTRANGTHAI(hoaDonRequest.getTRANGTHAI());
            hoaDon.setLOAIHD(hoaDonRequest.getLOAIHD());
            hoaDon.setPHIVANCHUYEN(hoaDonRequest.getPHIVANCHUYEN());
            hoaDon.setTHONGTINGIAOHANG(hoaDonRequest.getTHONGTINGIAOHANG());
            hoaDon.setIDNV((long) hoaDonRequest.getIDNV());
            hoaDon.setIDKH((long) hoaDonRequest.getIDKH());

            hoaDonRepo.save(hoaDon);
            return ResponseEntity.ok("Thêm hóa đơn thành công!");

        } catch (Exception e) {
            return ResponseEntity.status(500).body("Thêm hóa đơn thất bại: " + e.getMessage());
        }
    }

    // Cập nhật hóa đơn
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateHoaDon(@PathVariable Long id, @Valid @RequestBody HoaDonRepues hoaDonRequest, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getFieldError().getDefaultMessage());
        }
        Optional<HoaDon> optionalHoaDon = hoaDonRepo.findById(id);
        if (optionalHoaDon.isPresent()) {
            try {
                HoaDon hoaDon = optionalHoaDon.get();
                hoaDon.setMAHD(hoaDonRequest.getMAHD());
                hoaDon.setMAVOUNCHER(hoaDonRequest.getMAVOUNCHER());
                hoaDon.setNGAYTAO(hoaDonRequest.getNGAYTAO());
                hoaDon.setNGAYSUA(hoaDonRequest.getNGAYSUA());
                hoaDon.setTHANHTIEN(hoaDonRequest.getTHANHTIEN());
                hoaDon.setNGAYTHANHTOAN(hoaDonRequest.getNGAYTHANHTOAN());
                hoaDon.setNGAYNHANHANG(hoaDonRequest.getNGAYNHANHANG());
                hoaDon.setTRANGTHAI(hoaDonRequest.getTRANGTHAI());
                hoaDon.setLOAIHD(hoaDonRequest.getLOAIHD());
                hoaDon.setPHIVANCHUYEN(hoaDonRequest.getPHIVANCHUYEN());
                hoaDon.setTHONGTINGIAOHANG(hoaDonRequest.getTHONGTINGIAOHANG());
                hoaDon.setIDNV((long) hoaDonRequest.getIDNV());
                hoaDon.setIDKH((long) hoaDonRequest.getIDKH());

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
    public ResponseEntity<String> deleteHoaDon(@PathVariable Long id) {
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
    public ResponseEntity<List<HoaDon>> getHoaDonList() {
        try {
            List<HoaDon> hoaDonList = hoaDonRepo.findAll();
            return ResponseEntity.ok(hoaDonList);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }
}
