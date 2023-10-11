package apap.ti.silogistik2106751745.repository;

import apap.ti.silogistik2106751745.model.Gudang;
import apap.ti.silogistik2106751745.model.Barang;
import apap.ti.silogistik2106751745.model.GudangBarang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface GudangBarangDb extends JpaRepository<GudangBarang, Long> {

    @Query("SELECT gb.stok FROM GudangBarang gb WHERE gb.barang.sku = :barangSku")
    List<Integer> getAllBarangStokByBarangSku(@Param("barangSku") String barangSku);

    @Query("SELECT SUM(gb.stok) FROM GudangBarang gb WHERE gb.barang.sku = :barangSku")
    Integer findTotalStokByBarangSku(@Param("barangSku") String barangSku);

    @Query("SELECT gb FROM GudangBarang gb WHERE gb.gudang.id = :gudangId AND gb.barang.sku = :barangSku")
    GudangBarang findGudangBarangByGudangIdBarangSku(
            @Param("gudangId") Long gudangId,
            @Param("barangSku") String barangSku);
}
