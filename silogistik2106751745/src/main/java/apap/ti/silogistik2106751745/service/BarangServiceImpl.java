package apap.ti.silogistik2106751745.service;

import apap.ti.silogistik2106751745.model.Barang;
import apap.ti.silogistik2106751745.repository.BarangDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BarangServiceImpl implements BarangService {
    @Autowired
    BarangDb barangDb;

    public void saveBarang(Barang barang) { barangDb.save(barang); }
    public List<Barang> getAllBarang() { return barangDb.findAll(); }
    public Barang getBarangBySku(String sku) {
        for (Barang barang : getAllBarang()) {
            if (barang.getSku().equals(sku)) {
                return barang;
            }
        }
        return null;
    }
    public Barang updateBarang(Barang barangFromDto) {
        Barang barang = getBarangBySku(barangFromDto.getSku());
        if (barang != null) {
            barang.setTipeBarang(barangFromDto.getTipeBarang());
            barang.setMerk(barangFromDto.getMerk());
            barang.setHargaBarang(barangFromDto.getHargaBarang());
            barangDb.save(barang);
        }
        return barang;
    }

    public long getCount() {
        return barangDb.count();
    }
}
