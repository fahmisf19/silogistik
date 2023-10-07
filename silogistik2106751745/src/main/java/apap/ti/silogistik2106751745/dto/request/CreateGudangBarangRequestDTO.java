package apap.ti.silogistik2106751745.dto.request;

import apap.ti.silogistik2106751745.model.Barang;
import apap.ti.silogistik2106751745.model.Gudang;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateGudangBarangRequestDTO {
    private Gudang gudang;
    private Barang barang;
    @NotNull
    private Integer stok;
}
