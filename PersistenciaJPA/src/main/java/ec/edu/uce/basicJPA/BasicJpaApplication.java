package ec.edu.uce.basicJPA;

import ec.edu.uce.basicJPA.models.Person;
import ec.edu.uce.basicJPA.services.PersonServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

@SpringBootApplication
public class BasicJpaApplication
		//implements CommandLineRunner
{
	@Autowired
	PersonServices services;
	public static void main(String[] args) {
		SpringApplication.run(BasicJpaApplication.class, args);
	}

//	@Override
//	public void run(String... args) throws Exception {
//        services.save(new Person(1, "PEPE", "PEREZ",23));
//		services.save(new Person(2, "PAUL", "ABAD",25));
//		services.save(new Person(3, "ALEXANDER", "FREIRE",24));
//		services.save(new Person(4, "BELEN", "PACA",23));
//		services.save(new Person(5, "ANAHIS", "SILVA",22));

//		services.retrive();
	}

//	}
//
