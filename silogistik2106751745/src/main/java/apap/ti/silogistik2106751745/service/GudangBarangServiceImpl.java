package apap.ti.silogistik2106751745.service;

import apap.ti.silogistik2106751745.model.Barang;
import apap.ti.silogistik2106751745.model.Gudang;
import apap.ti.silogistik2106751745.model.GudangBarang;
import apap.ti.silogistik2106751745.repository.GudangBarangDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GudangBarangServiceImpl implements GudangBarangService {
    @Autowired
    GudangBarangDb gudangBarangDb;

    @Override
    public void saveGudangBarang(GudangBarang gudangBarang) { gudangBarangDb.save(gudangBarang); }

    @Override
    public Integer getTotalStok(String skuBarang) { return gudangBarangDb.findTotalStokByBarangSku(skuBarang); }

    @Override
    public GudangBarang getGudangBarangByIdGudangBarangSku(Long idGudang, String barangSku) {
        return gudangBarangDb.findGudangBarangByGudangIdBarangSku(idGudang, barangSku);
    }
}
