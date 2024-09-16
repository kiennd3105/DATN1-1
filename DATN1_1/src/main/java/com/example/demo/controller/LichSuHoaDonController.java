package com.example.demo.controller;

import com.example.demo.dto.LichSuHoaDonRequest;
import com.example.demo.dto.LichSuHoaDonResponse;
import com.example.demo.entities.LichSuHoaDon;
import com.example.demo.entities.HoaDon;
import com.example.demo.repository.LichSuHoaDonRepo;
import com.example.demo.repository.HoaDonRepo;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/lichsuhoadon")
public class LichSuHoaDonController {

    @Autowired
    private LichSuHoaDonRepo lichSuHoaDonRepo;

    // Tạo mới
    @PostMapping("/create")
    public ResponseEntity<LichSuHoaDon> createLichSuHoaDon(@RequestBody LichSuHoaDon lichSuHoaDon) {
        LichSuHoaDon savedLichSuHoaDon = lichSuHoaDonRepo.save(lichSuHoaDon);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedLichSuHoaDon);
    }

    // Lấy một LichSuHoaDon theo ID
    @GetMapping("/get/{id}")
    public ResponseEntity<LichSuHoaDon> getLichSuHoaDonById(@PathVariable Integer id) {
        Optional<LichSuHoaDon> lichSuHoaDonOptional = lichSuHoaDonRepo.findById(id);
        if (lichSuHoaDonOptional.isPresent()) {
            return ResponseEntity.ok(lichSuHoaDonOptional.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // list
    @GetMapping("/list")
    public ResponseEntity<List<LichSuHoaDon>> getAllLichSuHoaDon() {
        List<LichSuHoaDon> lichSuHoaDonList = lichSuHoaDonRepo.findAll();
        return ResponseEntity.ok(lichSuHoaDonList);
    }

    // Cập nhật
    @PutMapping("/update/{id}")
    public ResponseEntity<LichSuHoaDon> updateLichSuHoaDon(
            @PathVariable Integer id,
            @RequestBody LichSuHoaDon lichSuHoaDonDetails) {

        Optional<LichSuHoaDon> lichSuHoaDonOptional = lichSuHoaDonRepo.findById(id);
        if (lichSuHoaDonOptional.isPresent()) {
            LichSuHoaDon lichSuHoaDon = lichSuHoaDonOptional.get();
            lichSuHoaDon.setNGAYTAO(lichSuHoaDonDetails.getNGAYTAO());
            lichSuHoaDon.setTRANGTHAI(lichSuHoaDonDetails.getTRANGTHAI());
            lichSuHoaDon.setNGUOITHAOTAC(lichSuHoaDonDetails.getNGUOITHAOTAC());
            lichSuHoaDon.setIDHD(lichSuHoaDonDetails.getIDHD());

            LichSuHoaDon updatedLichSuHoaDon = lichSuHoaDonRepo.save(lichSuHoaDon);
            return ResponseEntity.ok(updatedLichSuHoaDon);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // Xóa một
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteLichSuHoaDon(@PathVariable Integer id) {
        if (lichSuHoaDonRepo.existsById(id)) {
            lichSuHoaDonRepo.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}




