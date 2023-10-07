package apap.ti.silogistik2106751745.dto.request;

import apap.ti.silogistik2106751745.model.GudangBarang;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
    @NotNull
    private String merk;
    @NotNull
    private BigDecimal hargaBarang;
    private List<GudangBarang> listGudangBarang;
}
