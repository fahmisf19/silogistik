package apap.ti.silogistik2106751745.dto;

import apap.ti.silogistik2106751745.dto.request.CreatePermintaanPengirimanBarangRequestDTO;
import apap.ti.silogistik2106751745.dto.request.CreatePermintaanPengirimanRequestDTO;
import apap.ti.silogistik2106751745.model.PermintaanPengiriman;
import apap.ti.silogistik2106751745.model.PermintaanPengirimanBarang;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PermintaanPengirimanBarangMapper {
    PermintaanPengirimanBarang createPermintaanPengirimanBarangRequestDTOToPermintaanPengirimanBarang(CreatePermintaanPengirimanBarangRequestDTO createPermintaanPengirimanBarangRequestDTO);
}
