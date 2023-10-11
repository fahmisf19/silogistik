package apap.ti.silogistik2106751745;

import apap.ti.silogistik2106751745.dto.*;
import apap.ti.silogistik2106751745.dto.request.*;
import apap.ti.silogistik2106751745.repository.BarangDb;
import apap.ti.silogistik2106751745.service.*;
import com.github.javafaker.Faker;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@SpringBootApplication
public class Silogistik2106751745Application {

	public static void main(String[] args) {
		SpringApplication.run(Silogistik2106751745Application.class, args);
	}

	@Bean
	@Transactional
	CommandLineRunner run(GudangService gudangService,
						  KaryawanService karyawanService,
						  BarangService barangService,
						  GudangMapper gudangMapper,
						  KaryawanMapper karyawanMapper,
						  BarangMapper barangMapper,
						  BarangDb barangDb,
						  PermintaanPengirimanMapper permintaanPengirimanMapper,
						  PermintaanPengirimanService permintaanPengirimanService,
						  PermintaanPengirimanBarangMapper permintaanPengirimanBarangMapper,
						  PermintaanPengirimanBarangService permintaanPengirimanBarangService) {
		return args -> {
			var faker = new Faker(new Locale("in-ID"));

			var gudangDTO = new CreateGudangRequestDTO();
			var fakeAlamat = faker.address().streetAddress() + ", " + faker.address().cityName();
			String[] parts = fakeAlamat.split(",");
			String city = parts[1].trim();
			gudangDTO.setNama("Gudang " + city + " " + faker.number().numberBetween(1, 20));
			gudangDTO.setAlamatGudang(fakeAlamat);
			gudangDTO.setListGudangBarang(new ArrayList<>());

			var karyawanDTO = new CreateKaryawanRequestDTO();
			karyawanDTO.setNama(faker.name().fullName());
			karyawanDTO.setJenisKelamin(faker.number().numberBetween(0, 1));
			karyawanDTO.setTanggalLahir(faker.date().birthday());

			var barangDTO = new CreateBarangRequestDTO();
			Integer tipeBarang = new Random().nextInt(5) + 1;

			String tipeBarangString = "";
			switch (tipeBarang) {
				case 1:
					tipeBarangString = "ELEC";
					break;
				case 2:
					tipeBarangString = "CLOT";
					break;
				case 3:
					tipeBarangString = "FOOD";
					break;
				case 4:
					tipeBarangString = "COSM";
					break;
				case 5:
					tipeBarangString = "TOOL";
					break;
			}

			String merk = faker.commerce().productName();
			BigDecimal hargaBarang = BigDecimal.valueOf(faker.number().randomDouble(2, 10, 1000));

			barangDTO.setTipeBarang(tipeBarang);
			barangDTO.setMerk(merk);
			barangDTO.setHargaBarang(hargaBarang);
			barangDTO.setSku(tipeBarangString + (barangDb.count()+1));
			barangDTO.setListGudangBarang(new ArrayList<>());

			var gudang = gudangMapper.createGudangRequestDTOToGudang(gudangDTO);
			gudangService.saveGudang(gudang);

			var karyawan = karyawanMapper.createKaryawanRequestDTOToKaryawan(karyawanDTO);
			karyawanService.saveKaryawan(karyawan);

			var barang = barangMapper.createBarangRequestDTOToBarang(barangDTO);
			barangService.saveBarang(barang);
		};
	}
}
