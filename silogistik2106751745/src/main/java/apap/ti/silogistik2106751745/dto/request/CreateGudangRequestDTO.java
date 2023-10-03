package apap.ti.silogistik2106751745.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
}
