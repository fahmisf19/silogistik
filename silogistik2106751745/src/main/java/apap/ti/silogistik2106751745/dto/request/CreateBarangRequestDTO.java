package apap.ti.silogistik2106751745.dto.request;

import apap.ti.silogistik2106751745.model.GudangBarang;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateBarangRequestDTO {
    private String sku;
    @NotNull
    private Integer tipeBarang;
    @NotBlank(message = "Merk barang tidak boleh kosong!")
    private String merk;
    @Min(value = 1, message = "Harga harus positif!")
    private BigDecimal hargaBarang;
    private List<GudangBarang> listGudangBarang;
}
