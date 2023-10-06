package apap.ti.silogistik2106751745.service;

import apap.ti.silogistik2106751745.model.Gudang;
import apap.ti.silogistik2106751745.repository.GudangDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GudangServiceImpl implements GudangService {
    @Autowired
    GudangDb gudangDb;

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
}
