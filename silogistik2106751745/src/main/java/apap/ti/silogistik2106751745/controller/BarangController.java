package apap.ti.silogistik2106751745.controller;

import apap.ti.silogistik2106751745.dto.BarangMapper;
import apap.ti.silogistik2106751745.dto.request.CreateBarangRequestDTO;
import apap.ti.silogistik2106751745.dto.request.UpdateBarangRequestDTO;
import apap.ti.silogistik2106751745.service.BarangService;
import apap.ti.silogistik2106751745.service.GudangService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BarangController {

    @Autowired
    private BarangService barangService;

    @Autowired
    private BarangMapper barangMapper;

    @Autowired
    private GudangService gudangService;

    @GetMapping("/barang")
    public String listBarang(Model model) {
        var listBarang = barangService.getAllBarang();

        model.addAttribute("listBarang", listBarang);
        model.addAttribute("page", "barang");

        return "daftar-barang";
    }

    @GetMapping("barang/tambah")
    public String formAddBarang(Model model) {
        //Membuat DTO baru sebagai isian form pengguna
        var barangDTO = new CreateBarangRequestDTO();
        model.addAttribute("barangDTO", barangDTO);

        // Atur parameter page ke 'barang' untuk menunjukkan bahwa halaman ini adalah halaman barang
        model.addAttribute("page", "barang");

        return "form-tambah-barang";
    }

    @PostMapping("barang/tambah")
    public String addBuku(@Valid @ModelAttribute CreateBarangRequestDTO barangDTO,
                          BindingResult bindingResult,
                          Model model) {

        if (bindingResult.hasErrors()) {
            String error = bindingResult.getFieldError().getDefaultMessage();

            model.addAttribute("errorMessage", error);
            model.addAttribute("barangDTO", barangDTO);

            // Atur parameter page ke 'buku' untuk menunjukkan bahwa halaman ini adalah halaman buku
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

    @GetMapping("barang/{sku}")
    public String detailBarang(@PathVariable("sku") String sku, Model model) {
        var barang = barangService.getBarangBySku(sku);

        model.addAttribute("barang", barang);

        model.addAttribute("page", "barang");

        return "detail-barang";
    }

    @GetMapping("/barang/ubah/{sku}")
    public String formUpdateBuku(@PathVariable("sku") String sku, Model model) {

        // Mendapatkan barang melalui ID
        var barang = barangService.getBarangBySku(sku);

        //Memindahkan data barang ke DTO untuk selanjutnya diubah di form
        var barangDTO = barangMapper.barangToUpdateBarangRequestDTO(barang);

        // Menambahkan objek barangDTO ke model untuk digunakan dalam formulir
        model.addAttribute("barangDTO", barangDTO);

        // Atur parameter page ke 'barang' untuk menunjukkan bahwa halaman ini adalah halaman barang
        model.addAttribute("page", "barang");

        return "form-update-barang";
    }

    @PostMapping("/buku/ubah/{sku}")
    public String updateBuku(@Valid @ModelAttribute UpdateBarangRequestDTO barangDTO,
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
