package apap.ti.silogistik2106751745.service;

import apap.ti.silogistik2106751745.model.Gudang;
import apap.ti.silogistik2106751745.model.PermintaanPengiriman;
import apap.ti.silogistik2106751745.model.PermintaanPengirimanBarang;
import apap.ti.silogistik2106751745.repository.PermintaanPengirimanBarangDb;
import apap.ti.silogistik2106751745.repository.PermintaanPengirimanDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PermintaanPengirimanBarangServiceImpl implements PermintaanPengirimanBarangService {
    @Autowired
    PermintaanPengirimanBarangDb permintaanPengirimanBarangDb;

    @Override
    public void savePermintaanPengirimanBarang(PermintaanPengirimanBarang permintaanPengirimanBarang) { permintaanPengirimanBarangDb.save(permintaanPengirimanBarang); }

    @Override
    public Integer getTotalKuantitasByPermintaanPengirimanId(Long id) {
        return permintaanPengirimanBarangDb.findTotalKuantitasByPermintaanPengirimanId(id);
    }
    @Override
    public List<PermintaanPengirimanBarang> getFilterPermintaanPengiriman(Date startDate, Date endDate, String sku) {
        return permintaanPengirimanBarangDb.findByWaktuPermintaanBetweenAndSkuContaining(startDate, endDate, sku);
    }

}
