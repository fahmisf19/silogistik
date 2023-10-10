package apap.ti.silogistik2106751745.dto.request;

import apap.ti.silogistik2106751745.model.Karyawan;
import apap.ti.silogistik2106751745.model.PermintaanPengirimanBarang;
import jakarta.validation.constraints.NotNull;
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
    @NotNull
    private Boolean isCancelled;
    @NotNull
    private String namaPenerima;
    @NotNull
    private String alamatPenerima;
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date tanggalPengiriman;
    @NotNull
    private Integer biayaPengiriman;
    @NotNull
    private Integer jenisLayanan;
    @NotNull
    private Karyawan karyawan;
    @NotNull
    private Date waktuPermintaan;
    private List<PermintaanPengirimanBarang> listPermintaanPengirimanBarang;
}
