/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.artivisi.virtualaccount.controller;

import com.artivisi.virtualaccount.domain.Nasabah;
import com.artivisi.virtualaccount.domain.VirtualAccountService;
import com.artivisi.virtualaccount.dto.NasabahDto;
import com.artivisi.virtualaccount.helper.DirectPrintHelper;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;

/**
 *
 * @author endy
 */
@Controller
public class NasabahController {

    private Logger logger = Logger.getLogger(NasabahController.class.getName());
    @Autowired
    private VirtualAccountService virtualAccountService;

    @InitBinder
    public void inisialisasiKonverter(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class,
                new CustomDateEditor(new SimpleDateFormat("dd-MM-yyyy"), true));
    }

    @RequestMapping("/nasabah/list")
    public ModelMap daftarNasabah() {
        ModelMap mm = new ModelMap();

        List<Nasabah> hasilQuery = virtualAccountService.cariNasabah();

        mm.addAttribute("daftarNasabah", hasilQuery);

        return mm;
    }

    @RequestMapping(value = "/nasabah/form", method = RequestMethod.GET)
    public ModelMap tampilkanForm(@RequestParam(required = false) String id) {
        ModelMap mm = new ModelMap();

        Nasabah n = virtualAccountService.cariNasabahById(id);
        if (n == null) {
            n = new Nasabah();
        }

        mm.addAttribute("nasabah", n);
        return mm;
    }

    @RequestMapping(value = "/nasabah/form", method = RequestMethod.POST)
    public String prosesForm(@ModelAttribute @Valid Nasabah n, BindingResult error, SessionStatus ss) {

        if (error.hasErrors()) {
            logger.log(Level.WARNING, "Jumlah error : " + error.getAllErrors().size());
            return "/nasabah/form";
        }

        virtualAccountService.simpan(n);
        logger.log(Level.INFO, "Data nasabah berhasil disimpan");
        ss.setComplete();
        return "redirect:view?id=" + n.getId();
    }

    @RequestMapping("/nasabah/view")
    public ModelMap detailNasabah(@RequestParam String id) {
        ModelMap mm = new ModelMap();

        Nasabah n = virtualAccountService.cariNasabahById(id);
        if (n == null) {
            n = new Nasabah();
        }

        mm.addAttribute("nasabah", n);
        return mm;
    }

    @RequestMapping("/nasabah/delete")
    public String hapusNasabah(@RequestParam String id) {
        Nasabah n = virtualAccountService.cariNasabahById(id);
        if (n != null) {
            virtualAccountService.hapus(n);
        }
        return "redirect:list";
    }

    @RequestMapping(value = "/nasabah/list/cetak", method = RequestMethod.GET)
    public ModelMap printResult(
            @RequestParam(value = "format", required = true) String format) {

        ModelMap mm = new ModelMap();
        List<Nasabah> listData = virtualAccountService.cariNasabah();
        List<NasabahDto> listDto = new ArrayList<NasabahDto>();
        
        int i = 0;
        for(Nasabah n : listData){
            for(String s : n.getDaftarNomerTelepon()){
                i++;

                NasabahDto dto = new NasabahDto();
                dto.setNo(i);
                dto.setNomer(n.getNomer());
                dto.setNama(n.getNama());
                dto.setEmail(n.getEmail());
                dto.setTanggalLahir(n.getTanggalLahir());
                dto.setJumlah(new BigDecimal(i)
                        .multiply(new BigDecimal(1000)));
                dto.setNoTelepon(s);
                
                listDto.add(dto);
            }
        }
        
        mm.put("format", format);
        mm.put("tanggalCetak", new Date());
        mm.put("nasabahDataSource", listDto);
        return mm;
    }

    @RequestMapping("/nasabah/list/direct")
    public void printDirect(HttpServletResponse response) throws Exception {

        List<Nasabah> listData = virtualAccountService.cariNasabah();
        StringWriter printout = new StringWriter();

        try {
            DirectPrintHelper.printNasabah(listData,printout);
        } catch (Exception err) {
            logger.info(err.getMessage());
        }

        response.setContentType("application/vnd.directprint");

        PrintWriter output = response.getWriter();
        output.print(printout.toString());
        output.close();

    }
}
