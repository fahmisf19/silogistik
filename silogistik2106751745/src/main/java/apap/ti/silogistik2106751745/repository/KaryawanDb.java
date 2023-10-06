package apap.ti.silogistik2106751745.repository;

import apap.ti.silogistik2106751745.model.Gudang;
import apap.ti.silogistik2106751745.model.Karyawan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KaryawanDb extends JpaRepository<Karyawan, Long> {
}
