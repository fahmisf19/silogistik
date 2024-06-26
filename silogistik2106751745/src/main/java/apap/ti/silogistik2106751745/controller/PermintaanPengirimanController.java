package apap.ti.silogistik2106751745.controller;

import apap.ti.silogistik2106751745.dto.PermintaanPengirimanMapper;
import apap.ti.silogistik2106751745.dto.request.CreatePermintaanPengirimanRequestDTO;
import apap.ti.silogistik2106751745.model.GudangBarang;
import apap.ti.silogistik2106751745.model.PermintaanPengirimanBarang;
import apap.ti.silogistik2106751745.service.*;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Controller
public class PermintaanPengirimanController {
    @Autowired
    private PermintaanPengirimanMapper permintaanPengirimanMapper;
    @Autowired
    private PermintaanPengirimanService permintaanPengirimanService;
    @Autowired
    private PermintaanPengirimanBarangService permintaanPengirimanBarangService;
    @Autowired
    private BarangService barangService;
    @Autowired
    private KaryawanService karyawanService;
    @Autowired
    private GudangBarangService gudangBarangService;

    @GetMapping("/permintaan-pengiriman")
    public String listPermintaanPengiriman(Model model) {
        var listPermintaanPengiriman = permintaanPengirimanService.getAllPermintaanPengiriman();

        SimpleDateFormat dateOnlyFormat = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat timeOnlyFormat = new SimpleDateFormat("HH:mm:ss");

        var listWaktuPermintaan = new ArrayList<>();
        var listTanggalPengiriman = new ArrayList<>();

        for (int i = 0; i < listPermintaanPengiriman.size(); i++) {
            String datePartWaktuPermintaan = dateOnlyFormat.format(listPermintaanPengiriman.get(i).getWaktuPermintaan());
            String timePartWaktuPermintaan = timeOnlyFormat.format(listPermintaanPengiriman.get(i).getWaktuPermintaan());
            String datePartTanggalPengiriman = dateOnlyFormat.format(listPermintaanPengiriman.get(i).getTanggalPengiriman());
            listWaktuPermintaan.add(datePartWaktuPermintaan + ", " + timePartWaktuPermintaan);
            listTanggalPengiriman.add(datePartTanggalPengiriman);
        }
        model.addAttribute("listWaktuPermintaan", listWaktuPermintaan);
        model.addAttribute("listTanggalPengiriman", listTanggalPengiriman);

        model.addAttribute("listPermintaanPengiriman", listPermintaanPengiriman);
        model.addAttribute("page", "permintaan-pengiriman");

        return "daftar-permintaan-pengiriman";
    }

    @GetMapping("permintaan-pengiriman/{idPermintaanPengiriman}")
    public String detailPermintaanPengiriman(@PathVariable("idPermintaanPengiriman") Long idPermintaanPengiriman, Model model) {
        var permintaanPengiriman = permintaanPengirimanService.getPermintaanPengirimanById(idPermintaanPengiriman);
        var listPermintaanPengirimanBarang = permintaanPengiriman.getListPermintaanPengirimanBarang();

        List<String> listJenisLayanan = Arrays.asList(
                "Same Day",
                "Kilat",
                "Reguler",
                "Hemat"
        );
        // Define the date format for parsing

        SimpleDateFormat dateOnlyFormat = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat timeOnlyFormat = new SimpleDateFormat("HH:mm:ss");

        String datePartWaktuPermintaan = dateOnlyFormat.format(permintaanPengiriman.getWaktuPermintaan());
        String timePartWaktuPermintaan = timeOnlyFormat.format(permintaanPengiriman.getWaktuPermintaan());
        String datePartTanggalPengiriman = dateOnlyFormat.format(permintaanPengiriman.getTanggalPengiriman());

        model.addAttribute("tanggalPengiriman", datePartTanggalPengiriman);
        model.addAttribute("waktuPermintaan", datePartWaktuPermintaan+", "+timePartWaktuPermintaan);
        model.addAttribute("type", listJenisLayanan.get(permintaanPengiriman.getJenisLayanan()-1));
        model.addAttribute("permintaanPengiriman", permintaanPengiriman);
        model.addAttribute("listPermintaanPengirimanBarang", listPermintaanPengirimanBarang);

        model.addAttribute("page", "permintaan-pengiriman");

        return "detail-permintaan-pengiriman";
    }

    @GetMapping("/permintaan-pengiriman/tambah")
    public String formTambahPermintaanPengiriman(Model model) {
        //Membuat DTO baru sebagai isian form pengguna
        var permintaanPengirimanDTO = new CreatePermintaanPengirimanRequestDTO();

        List<String> listJenisLayanan = Arrays.asList(
                "Same Day",
                "Kilat",
                "Reguler",
                "Hemat"
        );
        permintaanPengirimanDTO.setListPermintaanPengirimanBarang(new ArrayList<>());

        var listBarang = barangService.getAllBarang();
        var listKaryawan = karyawanService.getAllKaryawan();
        var listPermintaanPengirimanBarang = permintaanPengirimanDTO.getListPermintaanPengirimanBarang();

        model.addAttribute("listJenisLayanan", listJenisLayanan);
        model.addAttribute("listBarang", listBarang);
        model.addAttribute("listKaryawan", listKaryawan);
        model.addAttribute("permintaanPengirimanDTO", permintaanPengirimanDTO);
        model.addAttribute("listPermintaanPengirimanBarang", listPermintaanPengirimanBarang);

        // Atur parameter page ke 'permintaan-pengiriman' untuk menunjukkan bahwa halaman ini adalah halaman permintaan-pengiriman
        model.addAttribute("page", "permintaan-pengiriman");

        return "form-tambah-permintaan-pengiriman";
    }

    @PostMapping(value = "/permintaan-pengiriman/tambah", params = {"addRow"})
    public String addRowTambahPermintaanPengiriman (
            @ModelAttribute CreatePermintaanPengirimanRequestDTO permintaanPengirimanDTO,
            Model model
    ) {
        if (permintaanPengirimanDTO.getListPermintaanPengirimanBarang() == null || permintaanPengirimanDTO.getListPermintaanPengirimanBarang().size() == 0) {
            permintaanPengirimanDTO.setListPermintaanPengirimanBarang(new ArrayList<>());
        }
        permintaanPengirimanDTO.getListPermintaanPengirimanBarang().add(new PermintaanPengirimanBarang());
        // Kirim list barang untuk menjadi pilihan pada dropdown
        List<String> listJenisLayanan = Arrays.asList(
                "Same Day",
                "Kilat",
                "Reguler",
                "Hemat"
        );
        model.addAttribute("listPermintaanPengirimanBarang", permintaanPengirimanDTO.getListPermintaanPengirimanBarang());
        model.addAttribute("listJenisLayanan", listJenisLayanan);
        model.addAttribute("listBarang", barangService.getAllBarang());
        model.addAttribute("listKaryawan", karyawanService.getAllKaryawan());

        model.addAttribute("permintaanPengirimanDTO", permintaanPengirimanDTO);

        // Atur parameter page ke 'gudang' untuk menunjukkan bahwa halaman ini adalah halaman gudang
        model.addAttribute("page", "permintaan-pengiriman");

        return "form-tambah-permintaan-pengiriman";
    }
    @PostMapping(value = "/permintaan-pengiriman/tambah", params = {"deleteRow"})
    public String deleteRow(
            @ModelAttribute CreatePermintaanPengirimanRequestDTO permintaanPengirimanDTO,
            @RequestParam("deleteRow") int row,
            Model model
    ) {
        permintaanPengirimanDTO.getListPermintaanPengirimanBarang().remove(row);
        List<String> listJenisLayanan = Arrays.asList(
                "Same Day",
                "Kilat",
                "Reguler",
                "Hemat"
        );
        model.addAttribute("listPermintaanPengirimanBarang", permintaanPengirimanDTO.getListPermintaanPengirimanBarang());
        model.addAttribute("listJenisLayanan", listJenisLayanan);
        model.addAttribute("listBarang", barangService.getAllBarang());
        model.addAttribute("listKaryawan", karyawanService.getAllKaryawan());

        model.addAttribute("permintaanPengirimanDTO", permintaanPengirimanDTO);

        model.addAttribute("page", "permintaan-pengiriman");

        return "form-tambah-permintaan-pengiriman";
    }

    @PostMapping("/permintaan-pengiriman/tambah")
    public String tambahPermintaanPengiriman(@Valid @ModelAttribute CreatePermintaanPengirimanRequestDTO permintaanPengirimanDTO,
                            BindingResult bindingResult,
                            Model model) {
        try {
            if (bindingResult.hasErrors()) {
                String error = bindingResult.getFieldError().getDefaultMessage();

                model.addAttribute("errorMessage", error);
                List<String> listJenisLayanan = Arrays.asList(
                        "Same Day",
                        "Kilat",
                        "Reguler",
                        "Hemat"
                );
                model.addAttribute("listPermintaanPengirimanBarang", permintaanPengirimanDTO.getListPermintaanPengirimanBarang());
                model.addAttribute("listJenisLayanan", listJenisLayanan);
                model.addAttribute("listBarang", barangService.getAllBarang());
                model.addAttribute("listKaryawan", karyawanService.getAllKaryawan());

                model.addAttribute("permintaanPengirimanDTO", permintaanPengirimanDTO);

                model.addAttribute("page", "permintaan-pengiriman");

                return "form-tambah-permintaan-pengiriman";
            }
            List<String> listJenisLayanan = Arrays.asList(
                    "SAM",
                    "KIL",
                    "REG",
                    "HEM"
            );
            // Set waktuPermintaan, nomorPengiriman, dan IsCancelled pada DTO
            permintaanPengirimanDTO.setWaktuPermintaan(new Date());
            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
            Date waktuPermintaan = permintaanPengirimanDTO.getWaktuPermintaan();
            String kodeWaktu = timeFormat.format(waktuPermintaan);
            String shipmentNumber = "REQXX"  + listJenisLayanan.get(permintaanPengirimanDTO.getJenisLayanan()-1) + kodeWaktu;
            permintaanPengirimanDTO.setNomorPengiriman(shipmentNumber);
            permintaanPengirimanDTO.setIsCancelled(false);

            for (PermintaanPengirimanBarang permintaanPengirimanBarang : permintaanPengirimanDTO.getListPermintaanPengirimanBarang()) {
                Integer totalStok = gudangBarangService.getTotalStok(permintaanPengirimanBarang.getBarang().getSku());
                if (totalStok == null) {
                    model.addAttribute("errorMessage", "Stok barang tidak ditemukan.");
                    model.addAttribute("listPermintaanPengirimanBarang", permintaanPengirimanDTO.getListPermintaanPengirimanBarang());
                    model.addAttribute("listJenisLayanan", listJenisLayanan);
                    model.addAttribute("listBarang", barangService.getAllBarang());
                    model.addAttribute("listKaryawan", karyawanService.getAllKaryawan());
                    model.addAttribute("permintaanPengirimanDTO", permintaanPengirimanDTO);
                    model.addAttribute("page", "permintaan-pengiriman");
                    return "form-tambah-permintaan-pengiriman";
                }

                if (permintaanPengirimanBarang.getKuantitasPengiriman() > totalStok) {
                    model.addAttribute("errorMessage", "Kuantitas " + gudangBarangService.getBarangMerk(permintaanPengirimanBarang.getBarang().getSku()) + " melebihi stok barang (max = " + totalStok + ").");
                    model.addAttribute("listPermintaanPengirimanBarang", permintaanPengirimanDTO.getListPermintaanPengirimanBarang());
                    model.addAttribute("listJenisLayanan", listJenisLayanan);
                    model.addAttribute("listBarang", barangService.getAllBarang());
                    model.addAttribute("listKaryawan", karyawanService.getAllKaryawan());
                    model.addAttribute("permintaanPengirimanDTO", permintaanPengirimanDTO);
                    model.addAttribute("page", "permintaan-pengiriman");
                    return "form-tambah-permintaan-pengiriman";
                }
            }

            // Melakukan Mapping
            var permintaanPengiriman = permintaanPengirimanMapper.createPermintaanPengirimanRequestDTOToPermintaanPengiriman(permintaanPengirimanDTO);

            // Set listPermintaanPengirimanBarang dengan id permintaanPengiriman
            var listBarangTemp = permintaanPengiriman.getListPermintaanPengirimanBarang();
            for (int i = 0; i < listBarangTemp.size(); i++) {
                listBarangTemp.get(i).setPermintaanPengiriman(permintaanPengiriman);
            }
            permintaanPengiriman.setListPermintaanPengirimanBarang(listBarangTemp);
            //Memanggil Service savePermintaanPengiriman
            permintaanPengirimanService.savePermintaanPengiriman(permintaanPengiriman);

            // Set kembali nomorPengiriman dengan total kuantitas permintaanPengirimanBarang
            permintaanPengiriman = permintaanPengirimanService.getPermintaanPengirimanById(permintaanPengiriman.getId());
            permintaanPengiriman.setNomorPengiriman(permintaanPengiriman.getNomorPengiriman().replace("XX", String.format("%02d", permintaanPengirimanBarangService.getTotalKuantitasByPermintaanPengirimanId(permintaanPengiriman.getId()))));
            permintaanPengirimanService.savePermintaanPengiriman(permintaanPengiriman);

            //Add variabel sku buku ke 'nomorPengiriman' untuk dirender di thymeleaf
            model.addAttribute("nomorPengiriman", permintaanPengiriman.getNomorPengiriman());

            // Atur parameter page ke 'permintaan-pengiriman' untuk menunjukkan bahwa halaman ini adalah halaman permintaan-pengiriman
            model.addAttribute("page", "permintaan-pengiriman");

            return "success-tambah-permintaan-pengiriman";

        } catch (ConstraintViolationException | DataIntegrityViolationException e) {
            if (e instanceof DataIntegrityViolationException) {
                model.addAttribute("errorMessage", "Barang dengan SKU yang sama telah dimasukkan.");
            } else {
                model.addAttribute("errorMessage", "Kuantitas minimal 1!");
            }
            List<String> listJenisLayanan = Arrays.asList(
                    "Same Day",
                    "Kilat",
                    "Reguler",
                    "Hemat"
            );
            model.addAttribute("listPermintaanPengirimanBarang", permintaanPengirimanDTO.getListPermintaanPengirimanBarang());
            model.addAttribute("listJenisLayanan", listJenisLayanan);
            model.addAttribute("listBarang", barangService.getAllBarang());
            model.addAttribute("listKaryawan", karyawanService.getAllKaryawan());
            model.addAttribute("permintaanPengirimanDTO", permintaanPengirimanDTO);
            model.addAttribute("page", "permintaan-pengiriman");
            return "form-tambah-permintaan-pengiriman";
        }

    }

    @GetMapping("/permintaan-pengiriman/{idPermintaanPengiriman}/cancel")
    public String cancelPermintaanPengiriman(@PathVariable("idPermintaanPengiriman") Long idPermintaanPengiriman, Model model) {
        var permintaanPengiriman = permintaanPengirimanService.getPermintaanPengirimanById(idPermintaanPengiriman);
        var nomorPengiriman = permintaanPengiriman.getNomorPengiriman();

        Date waktuPembuatan = permintaanPengiriman.getWaktuPermintaan();
        Date waktuSekarang = new Date();
        long selisihMillis = waktuSekarang.getTime() - waktuPembuatan.getTime();
        long selisihJam = TimeUnit.MILLISECONDS.toHours(selisihMillis);

        // Atur parameter page ke 'permintaan-pengiriman' untuk menunjukkan bahwa halaman ini adalah halaman permintaan-pengiriman
        model.addAttribute("page", "permintaan-pengiriman");

        model.addAttribute("nomorPengiriman", nomorPengiriman);

        if (selisihJam <= 24) {
            permintaanPengirimanService.cancelPermintaanPengiriman(permintaanPengiriman);
            return "success-cancel-permintaan-pengiriman";
        } else {
            return "fail-cancel-permintaan-pengiriman";
        }
    }

    @GetMapping("/filter-permintaan-pengiriman")
    public String filterPermintaanPengiriman(
            @RequestParam(name = "startDate", required = false) String startDate,
            @RequestParam(name = "endDate", required = false) String endDate,
            @RequestParam(name = "sku", required = false) String sku,
            Model model
    ) {
        var listBarang = barangService.getAllBarangSort();
        model.addAttribute("listBarang", listBarang);
        String errorMessage = null;

        if (sku != null && !sku.isEmpty() && startDate != null && endDate != null) {
            var selected = barangService.getBarangBySku(sku);
            var selectedMerk= selected.getMerk();
            var selectedStart = startDate;
            var selectedEnd = endDate;

            model.addAttribute("selectedStart", selectedStart);
            model.addAttribute("selectedEnd", selectedEnd);
            model.addAttribute("selected", selected);
            model.addAttribute("selectedMerk", selectedMerk);
            model.addAttribute("listBarang", listBarang);

            SimpleDateFormat dateFormatStart = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat dateFormatEnd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date parsedStartDate;
            Date parsedEndDate;
            try {
                parsedStartDate = dateFormatStart.parse(startDate);
                parsedEndDate = dateFormatEnd.parse(endDate+" 23:59:59");
            } catch (ParseException e) {
                model.addAttribute("page", "bonus");
                return "filter-permintaan-pengiriman";
            }

            List<PermintaanPengirimanBarang> listFilter = permintaanPengirimanBarangService.getFilterPermintaanPengiriman(parsedStartDate, parsedEndDate, sku);
            model.addAttribute("listFilter", listFilter);

            if (listFilter.isEmpty()){
                errorMessage = "Tidak ditemukan permintaan pengiriman dengan barang " + selectedMerk + " pada rentang waktu tersebut!";
            }
            model.addAttribute("errorMessage", errorMessage);

            SimpleDateFormat dateOnlyFormat = new SimpleDateFormat("dd-MM-yyyy");
            SimpleDateFormat timeOnlyFormat = new SimpleDateFormat("HH:mm:ss");

            var listWaktuPermintaan = new ArrayList<>();
            var listTanggalPengiriman = new ArrayList<>();

            for (int i = 0; i < listFilter.size(); i++) {
                String datePartWaktuPermintaan = dateOnlyFormat.format(listFilter.get(i).getPermintaanPengiriman().getWaktuPermintaan());
                String timePartWaktuPermintaan = timeOnlyFormat.format(listFilter.get(i).getPermintaanPengiriman().getWaktuPermintaan());
                String datePartTanggalPengiriman = dateOnlyFormat.format(listFilter.get(i).getPermintaanPengiriman().getTanggalPengiriman());
                listWaktuPermintaan.add(datePartWaktuPermintaan + ", " + timePartWaktuPermintaan);
                listTanggalPengiriman.add(datePartTanggalPengiriman);
            }
            model.addAttribute("listWaktuPermintaan", listWaktuPermintaan);
            model.addAttribute("listTanggalPengiriman", listTanggalPengiriman);
        }
        model.addAttribute("page", "bonus");
        return "filter-permintaan-pengiriman";
    }
}
