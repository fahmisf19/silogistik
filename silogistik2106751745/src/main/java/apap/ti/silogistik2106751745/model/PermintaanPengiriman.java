package apap.ti.silogistik2106751745.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "permintaan_pengiriman")
public class PermintaanPengiriman {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Size(max =16)
    @Column(name = "nomor_pengiriman")
    private String nomorPengiriman;

    @NotNull
    @Column(name = "is_cancelled")
    private Boolean isCancelled;

    @NotNull
    @Size(max = 255)
    @Column(name = "nama_penerima")
    private String namaPenerima;

    @NotNull
    @Size(max = 255)
    @Column(name = "alamat_penerima")
    private String alamatPenerima;

    @NotNull
    @Column(name = "tanggal_pengiriman")
    private Date tanggalPengiriman;

    @NotNull
    @Column(name = "biaya_pengiriman")
    private Integer biayaPengiriman;

    @NotNull
    @Size(max = 1)
    @Column(name = "jenis_layanan")
    private Integer jenisLayanan;

    @NotNull
    @Column(name = "waktu_permintaan")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime waktuPermintaan;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_karyawan", referencedColumnName = "id")
    private Karyawan karyawan;
}