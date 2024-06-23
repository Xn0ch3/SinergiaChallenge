package Sinergia.Challenger.JAVA.Xavier.Nochelli;

import Sinergia.Challenger.JAVA.Xavier.Nochelli.Models.RoleType;
import Sinergia.Challenger.JAVA.Xavier.Nochelli.Models.Usuary;
import Sinergia.Challenger.JAVA.Xavier.Nochelli.Repository.UsuaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class ChallengerJavaXavierNochelliApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChallengerJavaXavierNochelliApplication.class, args);
	}

	@Autowired
	private PasswordEncoder passwordEncoder;
	@Bean
	public CommandLineRunner initData(UsuaryRepository usuaryRepository) {
		return args -> {
			Usuary usuary = new Usuary("Xavier","Nochelli","nochellixavier@gmail.com", passwordEncoder.encode("Xavier10$"), RoleType.USUARY );
			usuaryRepository.save(usuary);

		};
	}
}
