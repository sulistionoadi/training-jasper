/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.artivisi.virtualaccount.domain;

import java.util.List;

/**
 *
 * @author endy
 */
public interface VirtualAccountService {
    public List<Nasabah> cariNasabah();

    public void simpan(Nasabah n);

    public Nasabah cariNasabahById(String id);

    public void hapus(Nasabah n);
}
