package apap.ti.silogistik2106751745.service;

import apap.ti.silogistik2106751745.model.Karyawan;
import apap.ti.silogistik2106751745.model.PermintaanPengiriman;
import apap.ti.silogistik2106751745.model.PermintaanPengirimanBarang;

import java.util.Date;
import java.util.List;

public interface PermintaanPengirimanBarangService {
    void savePermintaanPengirimanBarang(PermintaanPengirimanBarang permintaanPengirimanBarang);
    Integer getTotalKuantitasByPermintaanPengirimanId(Long id);
    List<PermintaanPengirimanBarang> getFilterPermintaanPengiriman(Date startDate, Date endDate, String sku);
}
