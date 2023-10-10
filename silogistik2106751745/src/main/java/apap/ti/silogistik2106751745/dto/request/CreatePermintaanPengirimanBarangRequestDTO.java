package apap.ti.silogistik2106751745.dto.request;

import apap.ti.silogistik2106751745.model.Barang;
import apap.ti.silogistik2106751745.model.Gudang;
import apap.ti.silogistik2106751745.model.PermintaanPengiriman;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreatePermintaanPengirimanBarangRequestDTO {
    private PermintaanPengiriman permintaanPengiriman;

    private Barang barang;

    @NotNull
    private Integer kuantitasPengiriman;
}
