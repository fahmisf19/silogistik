package apap.ti.silogistik2106751745.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "permintaan_pengiriman_barang", uniqueConstraints = {
        @UniqueConstraint(columnNames = { "id_permintaan_pengiriman", "sku_barang" })
})
public class PermintaanPengirimanBarang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_permintaan_pengiriman", referencedColumnName = "id")
    private PermintaanPengiriman permintaanPengiriman;

    @ManyToOne
    @JoinColumn(name = "sku_barang", referencedColumnName = "sku")
    private Barang barang;

    @NotNull(message = "Kuantitas tidak boleh kosong!")
    @Min(value = 1, message = "Kuantitas harus lebih dari 0!")
    @Column(name = "kuantitas_pengiriman")
    private Integer kuantitasPengiriman;
}