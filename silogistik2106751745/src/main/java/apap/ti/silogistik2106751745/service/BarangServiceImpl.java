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

    public void saveBarang(Barang barang) {
        Integer tipe = barang.getTipeBarang();
        Integer count = barangDb.countByTipeBarang(tipe) + 1;
        String tipeBarangString = "";
        switch (tipe) {
            case 1:
                tipeBarangString = "ELEC";
                break;
            case 2:
                tipeBarangString = "CLOT";
                break;
            case 3:
                tipeBarangString = "FOOD";
                break;
            case 4:
                tipeBarangString = "COSM";
                break;
            case 5:
                tipeBarangString = "TOOL";
        }
        String sku = tipeBarangString + String.format("%03d", count);
        barang.setSku(sku);
        barangDb.save(barang);
    }
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

    public String tipeBarangToString(Barang barang) {
        var tipe = barang.getTipeBarang();
        String tipeBarangString = "";
        switch (tipe) {
            case 1:
                tipeBarangString = "Produk Elektronik";
                break;
            case 2:
                tipeBarangString = "Pakaian & Aksesoris";
                break;
            case 3:
                tipeBarangString = "Makanan & Minuman";
                break;
            case 4:
                tipeBarangString = "Kosmetik";
                break;
            case 5:
                tipeBarangString = "Perlengkapan Rumah";
                break;
        }
        return tipeBarangString;
    }
}
