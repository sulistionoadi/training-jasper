/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.artivisi.virtualaccount.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author endy
 */
@Entity @Table(name="t_rekening")
public class Rekening {
    @Id @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid2")
    private String id;
    
    @ManyToOne
    @JoinColumn(name="id_nasabah", nullable=false)
    private Nasabah nasabah;
    
    @Column(nullable=false, unique=true)
    private String nomer;
    
    @Column(name="saldo_minimum")
    private BigDecimal saldoMinimum = BigDecimal.ZERO;
    
    @Temporal(TemporalType.DATE)
    @Column(name="tanggal_pembukaan", nullable=false)
    private Date tanggalPembukaan;
    
    @ElementCollection
    @JoinTable(name="konfigurasi_account", joinColumns=@JoinColumn(name="id_rekening"))
    @MapKeyColumn(name="konfigurasi_key")
    @Column(name="konfigurasi_value")
    private Map<String, String> konfigurasi = new HashMap<String, String>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Map<String, String> getKonfigurasi() {
        return konfigurasi;
    }

    public void setKonfigurasi(Map<String, String> konfigurasi) {
        this.konfigurasi = konfigurasi;
    }

    public Nasabah getNasabah() {
        return nasabah;
    }

    public void setNasabah(Nasabah nasabah) {
        this.nasabah = nasabah;
    }

    public String getNomer() {
        return nomer;
    }

    public void setNomer(String nomer) {
        this.nomer = nomer;
    }

    public BigDecimal getSaldoMinimum() {
        return saldoMinimum;
    }

    public void setSaldoMinimum(BigDecimal saldoMinimum) {
        this.saldoMinimum = saldoMinimum;
    }

    public Date getTanggalPembukaan() {
        return tanggalPembukaan;
    }

    public void setTanggalPembukaan(Date tanggalPembukaan) {
        this.tanggalPembukaan = tanggalPembukaan;
    }
}
