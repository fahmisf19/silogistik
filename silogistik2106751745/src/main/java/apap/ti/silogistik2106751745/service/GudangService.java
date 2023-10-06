package apap.ti.silogistik2106751745.service;

import apap.ti.silogistik2106751745.model.Gudang;

import java.util.List;

public interface GudangService {
    void saveGudang(Gudang gudang);
    List<Gudang> getAllGudang();
    Gudang getGudangById(Long id);
    long getCount();
}
