package apap.ti.silogistik2106751745;

import apap.ti.silogistik2106751745.dto.GudangMapper;
import apap.ti.silogistik2106751745.dto.KaryawanMapper;
import apap.ti.silogistik2106751745.dto.request.CreateBarangRequestDTO;
import apap.ti.silogistik2106751745.dto.request.CreateGudangRequestDTO;
import apap.ti.silogistik2106751745.dto.request.CreateKaryawanRequestDTO;
import apap.ti.silogistik2106751745.service.GudangService;
import apap.ti.silogistik2106751745.service.KaryawanService;
import com.github.javafaker.Faker;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Locale;
import java.util.Random;

@SpringBootApplication
public class Silogistik2106751745Application {

	public static void main(String[] args) {
		SpringApplication.run(Silogistik2106751745Application.class, args);
	}

	@Bean
	@Transactional
	CommandLineRunner run(GudangService gudangService, KaryawanService karyawanService, GudangMapper gudangMapper, KaryawanMapper karyawanMapper) {
		return args -> {
			var faker = new Faker(new Locale("in-ID"));

			var gudangDTO = new CreateGudangRequestDTO();
			var fakeAlamat = faker.address().streetAddress() + ", " + faker.address().cityName();
			String[] parts = fakeAlamat.split(",");
			String city = parts[1].trim();
			gudangDTO.setNama("Gudang " + city + " " + faker.number().numberBetween(1, 20));
			gudangDTO.setAlamatGudang(fakeAlamat);

			var karyawanDTO = new CreateKaryawanRequestDTO();
			karyawanDTO.setNama(faker.name().fullName());
			karyawanDTO.setJenisKelamin(faker.number().numberBetween(0, 1));
			karyawanDTO.setTanggalLahir(faker.date().birthday());

			var gudang = gudangMapper.createGudangRequestDTOToGudang(gudangDTO);
			gudangService.saveGudang(gudang);

			var karyawan = karyawanMapper.createKaryawanRequestDTOToKaryawan(karyawanDTO);
			karyawanService.saveKaryawan(karyawan);
		};
	}
}
