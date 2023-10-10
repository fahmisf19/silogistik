package apap.ti.silogistik2106751745.service;

import apap.ti.silogistik2106751745.model.Karyawan;

import java.util.List;

public interface KaryawanService {
    void saveKaryawan(Karyawan karyawan);
    long getCount();
    List<Karyawan> getAllKaryawan();
}
