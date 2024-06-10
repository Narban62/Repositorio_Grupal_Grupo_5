package ec.edu.uce.basicJPA.services;

import ec.edu.uce.basicJPA.models.Person;
import ec.edu.uce.basicJPA.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonServices {
    @Autowired
    PersonRepository repository;

    public void save (Person person){
        repository.save(person);
    }

    public void retrive(){
        List<Person> listPerson = new ArrayList<Person>();
        listPerson=  repository.findAll();
        for (Person cadena:listPerson ){
            System.out.println(cadena.toString());
        }
}

}
