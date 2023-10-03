package apap.ti.silogistik2106751745.dto;

import apap.ti.silogistik2106751745.dto.request.CreateBarangRequestDTO;
import apap.ti.silogistik2106751745.dto.request.UpdateBarangRequestDTO;
import apap.ti.silogistik2106751745.model.Barang;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BarangMapper {

    Barang createBarangRequestDTOToBarang(CreateBarangRequestDTO createBarangRequestDTO);

    Barang updateBarangRequestDTOToBarang(UpdateBarangRequestDTO updateBarangRequestDTO);

    UpdateBarangRequestDTO barangToUpdateBarangRequestDTO(Barang barang);
}
