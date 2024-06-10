package ec.edu.uce.Game_Persistance;

import ec.edu.uce.Game_Persistance.models.User;
import ec.edu.uce.Game_Persistance.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GamePersistanceApplication implements CommandLineRunner {

	@Autowired
	UserService service;
	long id1;

	public static void main(String[] args) {
		SpringApplication.run(GamePersistanceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

	}
}
