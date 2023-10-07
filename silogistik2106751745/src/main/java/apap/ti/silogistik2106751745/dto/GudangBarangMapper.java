package apap.ti.silogistik2106751745.dto;

import apap.ti.silogistik2106751745.dto.request.CreateGudangBarangRequestDTO;
import apap.ti.silogistik2106751745.model.GudangBarang;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GudangBarangMapper {

    GudangBarang createGudangBarangRequestDTOToGudangBarang(CreateGudangBarangRequestDTO createGudangBarangRequestDTO);
}
