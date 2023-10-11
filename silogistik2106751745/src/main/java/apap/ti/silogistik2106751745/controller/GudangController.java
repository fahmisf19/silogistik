package apap.ti.silogistik2106751745.controller;

import apap.ti.silogistik2106751745.dto.GudangMapper;
import apap.ti.silogistik2106751745.dto.request.UpdateGudangRequestDTO;
import apap.ti.silogistik2106751745.model.GudangBarang;
import apap.ti.silogistik2106751745.service.*;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
public class GudangController {
    @Autowired
    GudangService gudangService;
    @Autowired
    BarangService barangService;
    @Autowired
    PermintaanPengirimanService permintaanPengirimanService;
    @Autowired
    KaryawanService karyawanService;
    @Autowired
    GudangMapper gudangMapper;

    @GetMapping("/")
    public String beranda(Model model) {
        var gudangCount = gudangService.getCount();
        var barangCount = barangService.getCount();
        var permintaanPengirimanCount = permintaanPengirimanService.getCount();
        var karyawanCount = karyawanService.getCount();

        // Add counts to the model
        model.addAttribute("gudangCount", gudangCount);
        model.addAttribute("barangCount", barangCount);
        model.addAttribute("permintaanPengirimanCount", permintaanPengirimanCount);
        model.addAttribute("karyawanCount", karyawanCount);

        return "beranda";
    }

    @GetMapping("/gudang")
    public String listGudang(Model model) {
        var listGudang = gudangService.getAllGudang();

        model.addAttribute("listGudang", listGudang);
        model.addAttribute("page", "gudang");

        return "daftar-gudang";
    }

    @GetMapping("/gudang/{idGudang}")
    public String detailGudang(@PathVariable("idGudang") Long idGudang, Model model) {
        var gudang = gudangService.getGudangById(idGudang);
        var listBarang = gudang.getListGudangBarang();

        model.addAttribute("listBarang", listBarang);
        model.addAttribute("gudang", gudang);

        model.addAttribute("page", "gudang");

        return "detail-gudang";
    }

    @GetMapping("/gudang/cari-barang")
    public String searchBarang(@RequestParam(name = "sku", required = false) String sku, Model model) {
        var listBarang = barangService.getAllBarangSort();
        model.addAttribute("listBarang", listBarang);

        if (sku != null && !sku.isEmpty()) {
            var selected = barangService.getBarangBySku(sku);
            model.addAttribute("selected", selected);
            model.addAttribute("listBarang", listBarang);

            var listGudangBarang = barangService.getBarangBySku(sku).getListGudangBarang();
            model.addAttribute("listGudangBarang", listGudangBarang);
        }

        // Atur parameter page ke 'barang' untuk menunjukkan bahwa halaman ini adalah halaman barang
        model.addAttribute("page", "gudang");

        return "cari-barang";
    }

    @GetMapping("/gudang/{idGudang}/restock-barang")
    public String formRestockBarang(@PathVariable("idGudang") Long idGudang, Model model) {
        //Membuat DTO baru sebagai isian form pengguna
        var gudang = gudangService.getGudangById(idGudang);
        model.addAttribute("gudang", gudang);

        var gudangDTO = gudangMapper.gudangToUpdateGudangRequestDTO(gudang);

        model.addAttribute("gudangDTO", gudangDTO);
        model.addAttribute("idGudang", idGudang);

        // Kirim list barang untuk menjadi pilihan pada dropdown
        var listBarangExisting = barangService.getAllBarang();
        model.addAttribute("listBarangExisting", listBarangExisting);

        // Atur parameter page ke 'gudang' untuk menunjukkan bahwa halaman ini adalah halaman gudang
        model.addAttribute("page", "gudang");

        return "form-restock-barang";
    }

    @PostMapping(value = "/gudang/{idGudang}/restock-barang", params = {"addRow"})
    public String addRowRestockBarang (
            @PathVariable("idGudang") Long idGudang,
            @ModelAttribute UpdateGudangRequestDTO gudangDTO,
            Model model
    ) {
        var gudang = gudangService.getGudangById(idGudang);
        model.addAttribute("gudang", gudang);
        if (gudangDTO.getListGudangBarang() == null || gudangDTO.getListGudangBarang().size() == 0) {
            gudangDTO.setListGudangBarang(new ArrayList<>());
        }
        gudangDTO.getListGudangBarang().add(new GudangBarang());
        // Kirim list barang untuk menjadi pilihan pada dropdown
        model.addAttribute("listGudangBarang", gudangDTO.getListGudangBarang());
        model.addAttribute("listBarangExisting", barangService.getAllBarang());

        model.addAttribute("gudangDTO", gudangDTO);
        model.addAttribute("idGudang", idGudang);

        // Atur parameter page ke 'gudang' untuk menunjukkan bahwa halaman ini adalah halaman gudang
        model.addAttribute("page", "gudang");

        return "form-restock-barang";
    }

    @PostMapping(value = "/gudang/{idGudang}/restock-barang", params = {"deleteRow"})
    public String deleteRow(
            @PathVariable("idGudang") Long idGudang,
            @ModelAttribute UpdateGudangRequestDTO gudangDTO,
            @RequestParam("deleteRow") int row,
            Model model
    ) {
        var gudang = gudangService.getGudangById(idGudang);
        model.addAttribute("gudang", gudang);
        gudangDTO.getListGudangBarang().remove(row);
        model.addAttribute("gudangDTO", gudangDTO);
        model.addAttribute("idGudang", idGudang);

        // Kirim list barang untuk menjadi pilihan pada dropdown
        var listBarangExisting = barangService.getAllBarang();
        model.addAttribute("listBarangExisting", listBarangExisting);

        // Atur parameter page ke 'gudang' untuk menunjukkan bahwa halaman ini adalah halaman gudang
        model.addAttribute("page", "gudang");

        return "form-restock-barang";
    }

    @PostMapping("/gudang/{idGudang}/restock-barang")
    public String restockBarang(@PathVariable("idGudang") Long idGudang,
                                @Valid @ModelAttribute UpdateGudangRequestDTO gudangDTO,
                                BindingResult bindingResult,
                                Model model) {
        var gudang = gudangService.getGudangById(idGudang);
        model.addAttribute("gudang", gudang);
        if (bindingResult.hasErrors()) {
            String error = bindingResult.getFieldError().getDefaultMessage();
            model.addAttribute("errorMessage", error);
            model.addAttribute("gudangDTO", gudangDTO);
            model.addAttribute("idGudang", idGudang);

            // Kirim list barang untuk menjadi pilihan pada dropdown
            var listBarangExisting = barangService.getAllBarang();
            model.addAttribute("listBarangExisting", listBarangExisting);

            // Atur parameter page ke 'gudang' untuk menunjukkan bahwa halaman ini adalah halaman gudang
            model.addAttribute("page", "gudang");

            return "form-restock-barang";
        }
        try {
            var gudangFromDTO = gudangMapper.updateGudangRequestDTOToGudang(gudangDTO);

            gudang = gudangService.updateGudang(gudangFromDTO);

            model.addAttribute("idGudang", idGudang);
            model.addAttribute("gudang", gudang);
            model.addAttribute("page", "gudang");

            return "success-restock-barang";
        } catch (ConstraintViolationException | DataIntegrityViolationException e) {
            if (e instanceof DataIntegrityViolationException) {
                model.addAttribute("errorMessage", "Barang dengan SKU yang sama telah dimasukkan.");
            } else {
                model.addAttribute("errorMessage", "Stok minimal 1!");
            }
            model.addAttribute("gudangDTO", gudangDTO);
            model.addAttribute("idGudang", idGudang);

            // Kirim list barang untuk menjadi pilihan pada dropdown
            var listBarangExisting = barangService.getAllBarang();
            model.addAttribute("listBarangExisting", listBarangExisting);

            // Atur parameter page ke 'gudang' untuk menunjukkan bahwa halaman ini adalah halaman gudang
            model.addAttribute("page", "gudang");

            return "form-restock-barang";
        }
    }
}
