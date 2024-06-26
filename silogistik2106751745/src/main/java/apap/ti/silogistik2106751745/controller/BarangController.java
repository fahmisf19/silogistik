package apap.ti.silogistik2106751745.controller;

import apap.ti.silogistik2106751745.dto.BarangMapper;
import apap.ti.silogistik2106751745.dto.request.CreateBarangRequestDTO;
import apap.ti.silogistik2106751745.dto.request.UpdateBarangRequestDTO;
import apap.ti.silogistik2106751745.service.BarangService;
import apap.ti.silogistik2106751745.service.GudangBarangService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class BarangController {

    @Autowired
    private BarangService barangService;

    @Autowired
    private BarangMapper barangMapper;

    @Autowired
    GudangBarangService gudangBarangService;

    @GetMapping("/barang")
    public String listBarang(Model model) {
        var listBarang = barangService.getAllBarang();
        List<Integer> totalStok = new ArrayList<>();

        for (int i = 0; i < listBarang.size(); i++) {
            Integer temp =  gudangBarangService.getTotalStok(listBarang.get(i).getSku());
            if (temp == null) { temp = 0; }
            totalStok.add(temp);
        }

        model.addAttribute("listBarang", listBarang);
        model.addAttribute("totalStok", totalStok);
        model.addAttribute("page", "barang");

        return "daftar-barang";
    }

    @GetMapping("/barang/tambah")
    public String formAddBarang(Model model) {
        //Membuat DTO baru sebagai isian form pengguna
        var barangDTO = new CreateBarangRequestDTO();

        List<String> listTipeBarang = Arrays.asList(
                "Produk Elektronik",
                "Pakaian & Aksesoris",
                "Makanan & Minuman",
                "Kosmetik",
                "Perlengkapan Rumah"
        );
        model.addAttribute("listTipeBarang", listTipeBarang);
        model.addAttribute("barangDTO", barangDTO);

        // Atur parameter page ke 'barang' untuk menunjukkan bahwa halaman ini adalah halaman barang
        model.addAttribute("page", "barang");

        return "form-tambah-barang";
    }

    @PostMapping("/barang/tambah")
    public String addBarang(@Valid @ModelAttribute CreateBarangRequestDTO barangDTO,
                          BindingResult bindingResult,
                          Model model) {

        if (bindingResult.hasErrors()) {
            String error = bindingResult.getFieldError().getDefaultMessage();

            model.addAttribute("errorMessage", error);
            List<String> listTipeBarang = Arrays.asList(
                    "Produk Elektronik",
                    "Pakaian & Aksesoris",
                    "Makanan & Minuman",
                    "Kosmetik",
                    "Perlengkapan Rumah"
            );
            model.addAttribute("listTipeBarang", listTipeBarang);

            model.addAttribute("barangDTO", barangDTO);

            // Atur parameter page ke 'barang' untuk menunjukkan bahwa halaman ini adalah halaman barang
            model.addAttribute("page", "barang");

            return "form-tambah-barang";
        }
        var barang = barangMapper.createBarangRequestDTOToBarang(barangDTO);
        //Memanggil Service addBarang
        barangService.saveBarang(barang);

        //Add variabel sku buku ke 'sku' untuk dirender di thymeleaf
        model.addAttribute("sku", barang.getSku());

        //Add variabel merk ke 'merk' untuk dirender di thymeleaf
        model.addAttribute("merk", barang.getMerk());

        // Atur parameter page ke 'barang' untuk menunjukkan bahwa halaman ini adalah halaman barang
        model.addAttribute("page", "barang");

        return "success-tambah-barang";
    }

    @GetMapping("/barang/{sku}")
    public String detailBarang(@PathVariable("sku") String sku, Model model) {
        var barang = barangService.getBarangBySku(sku);
        var listGudang = barang.getListGudangBarang();
        var tipeBarang = barangService.tipeBarangToString(barang);
        var totalStok = gudangBarangService.getTotalStok(sku);

        model.addAttribute("barang", barang);
        model.addAttribute("listGudang", listGudang);
        model.addAttribute("tipeBarang", tipeBarang);
        model.addAttribute("totalStok", totalStok);

        model.addAttribute("page", "barang");

        return "detail-barang";
    }

    @GetMapping("/barang/ubah/{sku}")
    public String formUpdateBarang(@PathVariable("sku") String sku, Model model) {

        // Mendapatkan barang melalui ID
        var barang = barangService.getBarangBySku(sku);
        String tipeBarangString = barangService.tipeBarangToString(barang);

        //Memindahkan data barang ke DTO untuk selanjutnya diubah di form
        var barangDTO = barangMapper.barangToUpdateBarangRequestDTO(barang);

        // Menambahkan objek barangDTO ke model untuk digunakan dalam formulir
        model.addAttribute("barangDTO", barangDTO);
        model.addAttribute("tipeBarangString", tipeBarangString);

        // Atur parameter page ke 'barang' untuk menunjukkan bahwa halaman ini adalah halaman barang
        model.addAttribute("page", "barang");

        return "form-ubah-barang";
    }

    @PostMapping("/barang/ubah/{sku}")
    public String updateBarang(@Valid @ModelAttribute UpdateBarangRequestDTO barangDTO,
                             @PathVariable("sku") String sku,
                             BindingResult bindingResult,
                             Model model) {

        if (bindingResult.hasErrors()) {
            String error = bindingResult.getFieldError().getDefaultMessage();

            model.addAttribute("errorMessage", error);
            model.addAttribute("barangDTO", barangDTO);

            // Atur parameter page ke 'barang' untuk menunjukkan bahwa halaman ini adalah halaman barang
            model.addAttribute("page", "barang");

            return "form-ubah-barang";
        }

        var barangFromDto = barangMapper.updateBarangRequestDTOToBarang(barangDTO);

        //Memanggil Service updateBarang
        var barang = barangService.updateBarang(barangFromDto);

        //Add variabel sku ke 'sku' untuk dirender di thymeleaf
        model.addAttribute("sku", barang.getSku());

        //Add variabel merk ke 'merk' untuk dirender di thymeleaf
        model.addAttribute("merk", barang.getMerk());

        // Atur parameter page ke 'barang' untuk menunjukkan bahwa halaman ini adalah halaman barang
        model.addAttribute("page", "barang");

        return "success-ubah-barang";
    }
}
