package apap.ti.silogistik2106751745.service;

import apap.ti.silogistik2106751745.model.Karyawan;
import apap.ti.silogistik2106751745.model.PermintaanPengiriman;
import apap.ti.silogistik2106751745.model.PermintaanPengirimanBarang;

import java.util.Date;
import java.util.List;

public interface PermintaanPengirimanService {
    List<PermintaanPengiriman> getAllPermintaanPengiriman();
    void savePermintaanPengiriman(PermintaanPengiriman permintaanPengiriman);
    PermintaanPengiriman getPermintaanPengirimanById(Long id);
    Long getCount();
    void cancelPermintaanPengiriman(PermintaanPengiriman permintaanPengiriman);
}
