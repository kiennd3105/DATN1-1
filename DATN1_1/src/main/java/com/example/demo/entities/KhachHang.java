package com.example.demo.entities;

import com.example.demo.dtos.KhachHangReponse;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "KHACHHANG")
public class KhachHang {
    @Id
    @Column(name = "ID")
    private String id ;

    @Column(name = "MA", unique = true)
    private String ma;

    @Column(name = "Ten")
    private String tenKH;

    @Column(name = "EMAIL", unique = true)
    private String email;

    @Column(name = "PASSW")
    private String passw;

    @Column(name = "GIOITINH")
    private String gioiTinh;

    @Column(name = "SDT")
    private String sdt;

    @Column(name = "DIACHI")
    private String diaChi;

    @Column(name = "TRANGTHAI")
    private Integer trangThai;

    @Column(name = "NGAYTAO")
    private LocalDateTime ngayTao;

    @Column(name = "NGAYSUA")
    private LocalDateTime ngaySua;

    @PrePersist
    public void prePersist() {
        if (this.id == null) {
            this.id = UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        }
    }
    public KhachHangReponse toResponse() {
        return new KhachHangReponse(
                this.id,
                this.ma,
                this.tenKH,
                this.email,
                this.passw,
                this.gioiTinh,
                this.sdt,
                this.diaChi,
                this.trangThai,
                this.ngayTao,
                this.ngaySua
        );
    }}
