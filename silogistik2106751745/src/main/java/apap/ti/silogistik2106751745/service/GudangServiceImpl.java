package apap.ti.silogistik2106751745.service;

import apap.ti.silogistik2106751745.model.Gudang;
import apap.ti.silogistik2106751745.model.GudangBarang;
import apap.ti.silogistik2106751745.repository.GudangBarangDb;
import apap.ti.silogistik2106751745.repository.GudangDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GudangServiceImpl implements GudangService {
    @Autowired
    GudangDb gudangDb;

    @Autowired
    GudangBarangDb gudangBarangDb;

    @Autowired
    GudangBarangService gudangBarangService;

    @Override
    public void saveGudang(Gudang gudang) { gudangDb.save(gudang); }

    @Override
    public List<Gudang> getAllGudang() { return gudangDb.findAll(); }

    public Gudang getGudangById(Long id) {
        for (Gudang gudang : getAllGudang()) {
            if (gudang.getId().equals(id)) {
                return gudang;
            }
        }
        return null;
    }

    public long getCount() {
        return gudangDb.count();
    }

    public Gudang updateGudang(Gudang gudangFromDto) {
        Gudang gudang = getGudangById(gudangFromDto.getId());

        if (gudang != null) {
            for (GudangBarang gudangBarangFromDto : gudangFromDto.getListGudangBarang()) {
                // Mencari GudangBarang dengan sku yang sama
                Optional<GudangBarang> existingGudangBarang = gudang.getListGudangBarang().stream()
                        .filter(gb -> gb.getBarang().getSku().equals(gudangBarangFromDto.getBarang().getSku()) &&
                                gb.getGudang().getId().equals(gudangFromDto.getId()))
                        .findFirst();


                if (existingGudangBarang.isPresent()) {
                    // Jika ada update stok
                    GudangBarang existing = existingGudangBarang.get();
                    existing.setStok(gudangBarangFromDto.getStok());
                } else {
                    // jika tidak ada buat gudangBarang baru
                    gudangBarangFromDto.setGudang(gudang);
                    gudang.getListGudangBarang().add(gudangBarangFromDto);
                }
            }

            gudang.setNama(gudangFromDto.getNama());
            gudang.setAlamatGudang(gudangFromDto.getAlamatGudang());

            gudangDb.save(gudang);

            return gudang;
        }

        return null;
    }

}
