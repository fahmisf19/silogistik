package apap.ti.silogistik2106751745.service;

import apap.ti.silogistik2106751745.model.Barang;
import apap.ti.silogistik2106751745.model.Gudang;
import apap.ti.silogistik2106751745.model.GudangBarang;

import java.util.List;

public interface GudangBarangService {

    void saveGudangBarang(GudangBarang gudangBarang);

    Integer getTotalStok(String skuBarang);
    GudangBarang getGudangBarangByIdGudangBarangSku(Long idGudang, String barangSku);
}
