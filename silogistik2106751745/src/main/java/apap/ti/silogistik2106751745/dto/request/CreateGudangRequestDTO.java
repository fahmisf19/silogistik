package apap.ti.silogistik2106751745.dto.request;

import apap.ti.silogistik2106751745.model.GudangBarang;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateGudangRequestDTO {
    @NotNull
    @Size(max = 255)
    private String nama;

    @NotNull
    @Size(max = 255)
    private String alamatGudang;

    @NotNull(message = "Barang tidak boleh kosong!")
    private List<GudangBarang> listGudangBarang;
}
