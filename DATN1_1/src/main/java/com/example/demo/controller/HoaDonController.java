package com.example.demo.controller;

import com.example.demo.dtos.*;
import com.example.demo.entities.ChiTietHoaDon;
import com.example.demo.entities.HoaDon;
import com.example.demo.entities.KhachHang;
import com.example.demo.entities.NhanVien;
import com.example.demo.repository.ChiTietHoaDonRepo;
import com.example.demo.repository.HoaDonRepo;
import com.example.demo.repository.KhachHangRepo;
import com.example.demo.repository.NhanVienRepo;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/hoadon")
public class HoaDonController {

    @Autowired
    private HoaDonRepo hoaDonRepo;
    @Autowired
    private NhanVienRepo nhanVienRepo;
    @Autowired
    private KhachHangRepo khachHangRepo;

    @Autowired
    private ChiTietHoaDonRepo chiTietHoaDonRepo;

    @PostMapping("/add")
    public ResponseEntity<?> createHoaDon(@RequestBody HoaDonReq req) {
        // Kiểm tra ID nhân viên
        if (req.getIdNV() == null || req.getIdNV().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("ID nhân viên không được để trống.");
        }

        // Tạo mới hóa đơn
        HoaDon hoaDon = new HoaDon();

        // Sao chép các thuộc tính khác từ req
        BeanUtils.copyProperties(req, hoaDon);
        hoaDon.setNgayTao(LocalDateTime.now());
        hoaDon.setNgaySua(LocalDateTime.now());
        hoaDon.setTrangThai(1);
        hoaDon.setLoaiHD(req.getLoaiHD());
        hoaDon.setMaVoucher(null); // Không gán giá trị cho maVoucher
        hoaDon.setNgayThanhToan(null); // Không gán giá trị cho ngayThanhToan
        hoaDon.setNgayNhanHang(null); // Không gán giá trị cho ngayNhanHang
        hoaDon.setThanhTien(null);
        hoaDon.setPhiVanChuyen("30000");
        hoaDon.setThongTinGiaoHang("Vận chuyển nhanh");

        // Xử lý khách hàng
        if (req.getIdKH() != null && !req.getIdKH().trim().isEmpty()) {
            Optional<KhachHang> khachHangOptional = khachHangRepo.findById(req.getIdKH());
            if (khachHangOptional.isPresent()) {
                hoaDon.setKhachHang(khachHangOptional.get());
            } else {
                return ResponseEntity.badRequest().body("Không tìm thấy khách hàng với ID: " + req.getIdKH());
            }
        } else {
            return ResponseEntity.badRequest().body("ID khách hàng không được để trống.");
        }

        // Xử lý nhân viên
        Optional<NhanVien> nhanVienOptional = nhanVienRepo.findById(req.getIdNV());
        if (nhanVienOptional.isPresent()) {
            hoaDon.setNhanVien(nhanVienOptional.get());
        } else {
            return ResponseEntity.badRequest().body("Không tìm thấy nhân viên với ID: " + req.getIdNV());
        }

        // Trước khi lưu hóa đơn
        System.out.println("Mã hóa đơn: " + hoaDon.getMaHD());
        System.out.println("Ngày tạo: " + hoaDon.getNgayTao());
        System.out.println("Ngày sửa: " + hoaDon.getNgaySua());
        System.out.println("Ngày thanh toán: " + hoaDon.getNgayThanhToan());
        System.out.println("Ngày nhận hàng: " + hoaDon.getNgayNhanHang());
        System.out.println("Mã voucher: " + hoaDon.getMaVoucher());
        System.out.println("Thành tiền: " + hoaDon.getThanhTien());
        System.out.println("Phí vận chuyển: " + hoaDon.getPhiVanChuyen());
        System.out.println("Thông tin giao hàng: " + hoaDon.getThongTinGiaoHang());
        System.out.println("Trạng thái: " + hoaDon.getTrangThai());

        // Lưu hóa đơn
        // Lưu hóa đơn
        try {
            hoaDonRepo.save(hoaDon);
            return ResponseEntity.ok().body(java.util.Map.of("message", "Thêm hóa đơn thành công!", "hoaDon", hoaDon));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Có lỗi xảy ra khi lưu hóa đơn: " + e.getMessage()));
        }

    }




    @GetMapping("/listNV")
    public List<NhanVienReponse> getAllNhanVien() {
        NhanVien nhanVien=new NhanVien();
        nhanVien.setNgayTao(LocalDateTime.now());
        List<NhanVien> nhanVienList = nhanVienRepo.findAll();
        return nhanVienList.stream()
                .map(NhanVien::toResponse)
                .collect(Collectors.toList());
    }

    @GetMapping("/listKH")
    public ResponseEntity<List<KhachHangReponse>> getAllKhachHang() {
        KhachHang khachHang=new KhachHang();
        khachHang.setNgayTao(LocalDateTime.now());
        List<KhachHang> khachHangList = khachHangRepo.findAll();
        List<KhachHangReponse> responseList = khachHangList.stream()
                .map(KhachHang::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responseList);
    }

    @GetMapping("/list")
    public ResponseEntity<List<HoaDonRep>> getAllHoaDon() {
        Sort sort = Sort.by(Sort.Direction.DESC, "ngayTao"); // Sắp xếp theo ngày tạo giảm dần
        List<HoaDon> hoaDonList = hoaDonRepo.findAll(sort); // Lấy danh sách hóa đơn đã sắp xếp

        List<HoaDonRep> hoaDons = hoaDonList.stream()
                .map(hoaDon -> hoaDon.toResponse(List.of())) // Truyền danh sách chi tiết hóa đơn rỗng hoặc thực tế nếu có
                .collect(Collectors.toList());

        return ResponseEntity.ok(hoaDons); // Trả về danh sách DTO
    }


    // Read by ID
    @GetMapping("/detail/{idHD}")
    public ResponseEntity<HoaDonRep> getHoaDonById(@PathVariable String idHD) {
        return hoaDonRepo.findById(idHD).map(hoaDon -> {
            List<ChiTietHoaDon> chiTietHoaDons = chiTietHoaDonRepo.findByHoaDon_Id(idHD);

            List<ChiTietHoaDonRep> chiTietRepList = chiTietHoaDons.stream().map(chiTiet -> {
                ChiTietHoaDonRep chiTietRep = new ChiTietHoaDonRep();
                chiTietRep.setId(chiTiet.getId());
                chiTietRep.setMaCTHD(chiTiet.getMaCTHD());
                chiTietRep.setTongTien(chiTiet.getTongTien());
                chiTietRep.setSoLuong(chiTiet.getSoLuong());
                chiTietRep.setGiaBan(chiTiet.getGiaBan());
                chiTietRep.setTrangThai(chiTiet.getTrangThai());
                chiTietRep.setGhiChu(chiTiet.getGhiChu());
                return chiTietRep;
            }).collect(Collectors.toList());

            // Chuyển đổi hoaDon thành DTO và thiết lập danh sách chi tiết
            HoaDonRep rep = hoaDon.toResponse(chiTietRepList);
            return ResponseEntity.ok(rep);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Update
    // Update
    @PutMapping("/update/{id}")
    public ResponseEntity<Void> updateHoaDon(@PathVariable String id, @Validated @RequestBody HoaDonReq req) {
        Optional<HoaDon> hoaDonOptional = hoaDonRepo.findById(id);
        if (hoaDonOptional.isPresent()) {
            HoaDon hoaDon = hoaDonOptional.get();

            // Chỉ cập nhật idNV nếu không null
            if (req.getIdNV() != null && !req.getIdNV().trim().isEmpty()) {
                Optional<NhanVien> nhanVienOptional = nhanVienRepo.findById(req.getIdNV());
                if (nhanVienOptional.isPresent()) {
                    hoaDon.setNhanVien(nhanVienOptional.get());
                } else {
                    return ResponseEntity.badRequest().body(null);
                }
            }

            // Chỉ cập nhật idKH nếu không null
            if (req.getIdKH() != null && !req.getIdKH().trim().isEmpty()) {
                Optional<KhachHang> khachHangOptional = khachHangRepo.findById(req.getIdKH());
                if (khachHangOptional.isPresent()) {
                    hoaDon.setKhachHang(khachHangOptional.get());
                } else {
                    return ResponseEntity.badRequest().body(null);
                }
            }

            // Cập nhật các thông tin khác
            hoaDon.setMaHD(req.getMaHD());
            hoaDon.setMaVoucher(req.getMaVoucher());
            hoaDon.setThanhTien(req.getThanhTien());
            hoaDon.setNgayThanhToan(req.getNgayThanhToan());
            hoaDon.setNgayNhanHang(req.getNgayNhanHang());
            hoaDon.setTrangThai(req.getTrangThai());
            hoaDon.setLoaiHD(req.getLoaiHD());
            hoaDon.setPhiVanChuyen(req.getPhiVanChuyen());
            hoaDon.setThongTinGiaoHang(req.getThongTinGiaoHang());
            hoaDon.setNgaySua(LocalDateTime.now());

            try {
                hoaDonRepo.save(hoaDon);
                return ResponseEntity.ok().build();
            } catch (Exception e) {
                // Xử lý lỗi lưu vào cơ sở dữ liệu
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteHoaDon(@PathVariable String id) {
        if (hoaDonRepo.existsById(id)) {
            hoaDonRepo.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
