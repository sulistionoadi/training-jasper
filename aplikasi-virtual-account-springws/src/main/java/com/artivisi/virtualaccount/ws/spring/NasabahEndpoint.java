/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.artivisi.virtualaccount.ws.spring;

import com.artivisi.virtualaccount.CariNasabahRequest;
import com.artivisi.virtualaccount.CariNasabahResponse;
import com.artivisi.virtualaccount.domain.Nasabah;
import com.artivisi.virtualaccount.domain.VirtualAccountService;
import java.math.BigInteger;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

/**
 *
 * @author endy
 */
@Endpoint
public class NasabahEndpoint {
    @Autowired private VirtualAccountService virtualAccountService;
    
    @PayloadRoot(localPart="CariNasabahRequest", 
			namespace="http://artivisi.com/virtualaccount")
    @ResponsePayload
    public CariNasabahResponse cariNasabah(@RequestPayload CariNasabahRequest req){
        // cari di database
        List<Nasabah> hasil = virtualAccountService.cariNasabah();
        
        CariNasabahResponse resp = new CariNasabahResponse();
        resp.setJumlahData(new BigInteger(String.valueOf(hasil.size())));
        resp.setMulai(BigInteger.ZERO);
        resp.setSampai(new BigInteger(String.valueOf(hasil.size())));
        
        CariNasabahResponse.DaftarNasabah daftarNasabah = new CariNasabahResponse.DaftarNasabah();
        for (Nasabah nasabah : hasil) {
            com.artivisi.virtualaccount.Nasabah n = new com.artivisi.virtualaccount.Nasabah();
            n.setEmail(nasabah.getEmail());
            n.setNama(nasabah.getNama());
            n.setNomor(nasabah.getNomer());
            daftarNasabah.getNasabah().add(n);
        }
        resp.setDaftarNasabah(daftarNasabah);
        return resp;
    }
}
