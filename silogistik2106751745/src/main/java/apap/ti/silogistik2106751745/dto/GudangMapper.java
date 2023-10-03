package apap.ti.silogistik2106751745.dto;

import apap.ti.silogistik2106751745.dto.request.CreateGudangRequestDTO;
import apap.ti.silogistik2106751745.dto.request.UpdateBarangRequestDTO;
import apap.ti.silogistik2106751745.model.Barang;
import apap.ti.silogistik2106751745.model.Gudang;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GudangMapper {

    Gudang createGudangRequestDTOToGudang(CreateGudangRequestDTO createGudangRequestDTO);
}
