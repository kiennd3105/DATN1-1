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

    @Autowired
    private HoaDonRepo hoaDonRepo;

    // Tạo mới
    @PostMapping("/create")
    public ResponseEntity<LichSuHoaDonResponse> createLichSuHoaDon(@Valid @RequestBody LichSuHoaDonRequest request) {
        Optional<HoaDon> hoaDonOptional = hoaDonRepo.findById(request.getIDHD());
        if (hoaDonOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        LichSuHoaDon lichSuHoaDon = new LichSuHoaDon();
        lichSuHoaDon.setNGAYTAO(request.getNGAYTAO());
        lichSuHoaDon.setTRANGTHAI(request.getTRANGTHAI());
        lichSuHoaDon.setNGUOITHAOTAC(request.getNGUOITHAOTAC());
        lichSuHoaDon.setHoaDon(hoaDonOptional.get());

        LichSuHoaDon savedLichSuHoaDon = lichSuHoaDonRepo.save(lichSuHoaDon);

        LichSuHoaDonResponse response = new LichSuHoaDonResponse(
                savedLichSuHoaDon.getID(),
                savedLichSuHoaDon.getNGAYTAO(),
                savedLichSuHoaDon.getTRANGTHAI(),
                savedLichSuHoaDon.getNGUOITHAOTAC(),
                savedLichSuHoaDon.getHoaDon().getID()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // Cập nhật
    @PutMapping("/update/{id}")
    public ResponseEntity<LichSuHoaDonResponse> updateLichSuHoaDon(
            @PathVariable Integer id,
            @Valid @RequestBody LichSuHoaDonRequest request) {

        Optional<LichSuHoaDon> lichSuHoaDonOptional = lichSuHoaDonRepo.findById(id);
        if (lichSuHoaDonOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        Optional<HoaDon> hoaDonOptional = hoaDonRepo.findById(request.getIDHD());
        if (hoaDonOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        LichSuHoaDon lichSuHoaDon = lichSuHoaDonOptional.get();
        lichSuHoaDon.setNGAYTAO(request.getNGAYTAO());
        lichSuHoaDon.setTRANGTHAI(request.getTRANGTHAI());
        lichSuHoaDon.setNGUOITHAOTAC(request.getNGUOITHAOTAC());
        lichSuHoaDon.setHoaDon(hoaDonOptional.get());

        LichSuHoaDon updatedLichSuHoaDon = lichSuHoaDonRepo.save(lichSuHoaDon);

        LichSuHoaDonResponse response = new LichSuHoaDonResponse(
                updatedLichSuHoaDon.getID(),
                updatedLichSuHoaDon.getNGAYTAO(),
                updatedLichSuHoaDon.getTRANGTHAI(),
                updatedLichSuHoaDon.getNGUOITHAOTAC(),
                updatedLichSuHoaDon.getHoaDon().getID()
        );

        return ResponseEntity.ok(response);
    }

    // Xóa
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteLichSuHoaDon(@PathVariable Integer id) {
        if (lichSuHoaDonRepo.existsById(id)) {
            lichSuHoaDonRepo.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // Lấy danh sách lịch sử hóa đơn
    @GetMapping("/list")
    public ResponseEntity<List<LichSuHoaDonResponse>> getLichSuHoaDonList() {
        List<LichSuHoaDon> lichSuHoaDonList = lichSuHoaDonRepo.findAll();
        List<LichSuHoaDonResponse> responseList = lichSuHoaDonList.stream().map(lichSuHoaDon -> new LichSuHoaDonResponse(
                lichSuHoaDon.getID(),
                lichSuHoaDon.getNGAYTAO(),
                lichSuHoaDon.getTRANGTHAI(),
                lichSuHoaDon.getNGUOITHAOTAC(),
                lichSuHoaDon.getHoaDon().getID()
        )).toList();

        return ResponseEntity.ok(responseList);
    }
}
