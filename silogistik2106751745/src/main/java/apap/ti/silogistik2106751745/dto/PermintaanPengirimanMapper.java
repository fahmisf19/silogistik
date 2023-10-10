package apap.ti.silogistik2106751745.dto;

import apap.ti.silogistik2106751745.dto.request.CreatePermintaanPengirimanRequestDTO;
import apap.ti.silogistik2106751745.model.PermintaanPengiriman;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PermintaanPengirimanMapper {
    PermintaanPengiriman createPermintaanPengirimanRequestDTOToPermintaanPengiriman(CreatePermintaanPengirimanRequestDTO createPermintaanPengirimanRequestDTO);
}
