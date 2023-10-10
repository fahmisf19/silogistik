package apap.ti.silogistik2106751745.repository;

import apap.ti.silogistik2106751745.model.PermintaanPengiriman;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PermintaanPengirimanDb extends JpaRepository<PermintaanPengiriman, Long> {
    List<PermintaanPengiriman> findByIsCancelledFalse();
    Long countByIsCancelledFalse();
}
