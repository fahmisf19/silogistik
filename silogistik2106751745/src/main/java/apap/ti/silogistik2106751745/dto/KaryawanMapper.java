package apap.ti.silogistik2106751745.dto;

import apap.ti.silogistik2106751745.dto.request.CreateGudangRequestDTO;
import apap.ti.silogistik2106751745.dto.request.CreateKaryawanRequestDTO;
import apap.ti.silogistik2106751745.model.Gudang;
import apap.ti.silogistik2106751745.model.Karyawan;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface KaryawanMapper {

    Karyawan createKaryawanRequestDTOToKaryawan(CreateKaryawanRequestDTO createKaryawanRequestDTO);
}
