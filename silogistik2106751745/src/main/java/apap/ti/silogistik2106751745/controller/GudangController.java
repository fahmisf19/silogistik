package apap.ti.silogistik2106751745.controller;

import apap.ti.silogistik2106751745.dto.GudangMapper;
import apap.ti.silogistik2106751745.model.PermintaanPengiriman;
import apap.ti.silogistik2106751745.service.BarangService;
import apap.ti.silogistik2106751745.service.GudangService;
import apap.ti.silogistik2106751745.service.KaryawanService;
import apap.ti.silogistik2106751745.service.PermintaanPengirimanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class GudangController {
    @Autowired
    GudangService gudangService;
    @Autowired
    BarangService barangService;
//    @Autowired
//    PermintaanPengirimanService permintaanPengirimanService;
    @Autowired
    KaryawanService karyawanService;
    @Autowired
    GudangMapper gudangMapper;

    @GetMapping("/")
    public String beranda(Model model) {
        var gudangCount = gudangService.getCount();
        var barangCount = barangService.getCount();
//        var permintaanPengirimanCount = permintaanPengirimanService.getCount();
        var karyawanCount = karyawanService.getCount();

        // Add counts to the model
        model.addAttribute("gudangCount", gudangCount);
        model.addAttribute("barangCount", barangCount);
//        model.addAttribute("permintaanPengirimanCount", permintaanPengirimanCount);
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

    @GetMapping("gudang/{idGudang}")
    public String detailGudang(@PathVariable("idGudang") Long idGudang, Model model) {
        var gudang = gudangService.getGudangById(idGudang);

        model.addAttribute("gudang", gudang);

        model.addAttribute("page", "gudang");

        return "detail-gudang";
    }
}
