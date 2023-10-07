package apap.ti.silogistik2106751745.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateGudangRequestDTO extends CreateGudangRequestDTO{
    @NotNull
    private Long id;
}
