package apap.ti.silogistik2106751745.service;

import apap.ti.silogistik2106751745.model.Barang;

import java.util.List;

public interface BarangService {
    void saveBarang(Barang barang);
    List<Barang> getAllBarang();
    List<Barang> getAllBarangSort();
    Barang getBarangBySku(String sku);
    Barang updateBarang(Barang barang);
    long getCount();
    String tipeBarangToString(Barang barang);
}
