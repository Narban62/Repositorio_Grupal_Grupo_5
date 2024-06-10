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
		id1 = 2;
//		service.save(new User(1, "XjK",2,3100,80));
//		service.save(new User(2,"AND",2,1151));
//		service.save(new Person(4,"Bruce","Diaz",21));
//		service.save(new Person(5,"Tomas","Tito",21));
		service.retrive();

//		service.retrivebyID(id1);
		service.retrivebyLastName("XjK");
		service.updateScore(2,1020,80,"XjK");
		service.retrivebyLastName("XjK");
//		service.deleteUser("ASD");



	}
}