package apap.ti.silogistik2106751745.dto.request;

import apap.ti.silogistik2106751745.model.Karyawan;
import apap.ti.silogistik2106751745.model.PermintaanPengirimanBarang;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreatePermintaanPengirimanRequestDTO {
    private String nomorPengiriman;

    private Boolean isCancelled;

    @NotBlank(message = "Nama penerima tidak boleh kosong!")
    private String namaPenerima;

    @NotBlank(message = "Alamat penerima tidak boleh kosong!")
    private String alamatPenerima;

    @NotNull(message = "Tanggal pengiriman tidak boleh kosong!")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date tanggalPengiriman;

    @NotNull(message = "Biaya pengiriman tidak boleh kosong!")
    @Positive(message = "Biaya pengiriman harus lebih dari 0")
    private Integer biayaPengiriman;

    @NotNull(message = "Jenis layanan tidak boleh kosong!")
    private Integer jenisLayanan;

    @NotNull(message = "Karyawan tidak boleh kosong!")
    private Karyawan karyawan;

    private Date waktuPermintaan;

    @NotNull(message = "Barang tidak boleh kosong!")
    private List<PermintaanPengirimanBarang> listPermintaanPengirimanBarang;
}
