package apap.ti.silogistik2106751745.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "gudang_barang", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"id_gudang","sku_barang"})
})
public class GudangBarang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_gudang", referencedColumnName = "id")
    private Gudang gudang;

    @ManyToOne
    @JoinColumn(name = "sku_barang", referencedColumnName = "sku")
    private Barang barang;

    @NotNull(message = "Stok tidak boleh kosong!")
    @Min(value = 1, message = "Stok harus lebih dari 0!")
    private Integer stok;
}