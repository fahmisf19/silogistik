package apap.ti.silogistik2106751745.repository;

import apap.ti.silogistik2106751745.model.PermintaanPengiriman;
import apap.ti.silogistik2106751745.model.PermintaanPengirimanBarang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PermintaanPengirimanBarangDb extends JpaRepository<PermintaanPengirimanBarang, Long> {
    @Query("SELECT SUM(ppb.kuantitasPengiriman) FROM PermintaanPengirimanBarang ppb WHERE ppb.permintaanPengiriman.id = :id")
    Integer findTotalKuantitasByPermintaanPengirimanId(@Param("id") Long id);

    @Query("SELECT ppb FROM PermintaanPengirimanBarang ppb " +
            "WHERE ppb.permintaanPengiriman.waktuPermintaan BETWEEN :startDate AND :endDate " +
            "AND ppb.barang.sku = :sku")
    List<PermintaanPengirimanBarang> findByWaktuPermintaanBetweenAndSkuContaining(
            @Param("startDate") Date startDate,
            @Param("endDate") Date endDate,
            @Param("sku") String sku
    );
}
