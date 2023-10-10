package apap.ti.silogistik2106751745.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateKaryawanRequestDTO {
    @NotNull
    @Size(max = 30)
    private String nama;

    @NotNull
    private Integer jenisKelamin;

    @NotNull
    private Date tanggalLahir;
}
