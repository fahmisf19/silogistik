package apap.ti.silogistik2106751745.service;

import apap.ti.silogistik2106751745.model.Gudang;
import apap.ti.silogistik2106751745.model.PermintaanPengiriman;
import apap.ti.silogistik2106751745.repository.PermintaanPengirimanDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PermintaanPengirimanServiceImpl implements PermintaanPengirimanService {
    @Autowired
    PermintaanPengirimanDb permintaanPengirimanDb;

    public List<PermintaanPengiriman> getAllPermintaanPengiriman() { return permintaanPengirimanDb.findByIsCancelledFalse(); }

    @Override
    public PermintaanPengiriman getPermintaanPengirimanById(Long id) {
        for (PermintaanPengiriman permintaanPengiriman : getAllPermintaanPengiriman()) {
            if (permintaanPengiriman.getId().equals(id)) {
                return permintaanPengiriman;
            }
        }
        return null;
    }

    @Override
    public void savePermintaanPengiriman(PermintaanPengiriman permintaanPengiriman) { permintaanPengirimanDb.save(permintaanPengiriman); }

    @Override
    public Long getCount() {
        return permintaanPengirimanDb.countByIsCancelledFalse();
    }

    @Override
    public void cancelPermintaanPengiriman(PermintaanPengiriman permintaanPengiriman) {
        permintaanPengiriman.setIsCancelled(true);
        permintaanPengirimanDb.save(permintaanPengiriman);
    }
}
