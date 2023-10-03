package apap.ti.silogistik2106751745.repository;

import apap.ti.silogistik2106751745.model.Barang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BarangDb extends JpaRepository<Barang, String> {
}
