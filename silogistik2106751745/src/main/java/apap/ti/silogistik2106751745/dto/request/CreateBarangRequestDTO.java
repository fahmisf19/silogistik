package apap.ti.silogistik2106751745.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateBarangRequestDTO {
    @NotNull
    @Size(max = 16)
    private String sku;
    @NotNull
    private Integer tipeBarang;
    @NotNull
    @Size(max = 1)
    private String merk;
    @NotNull
    private BigDecimal hargaBarang;
}