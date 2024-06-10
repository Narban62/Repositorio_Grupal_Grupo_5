package ec.edu.uce.basicJPA.models;

import jakarta.persistence.*;

@Entity //paquete de la dependencia JPA
@Table (name = "alumno")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column //COLUMNA BASE DE DATOS
    private  String firstName;
    @Column //COLUMNA BASE DE DATOS
    private  String lastname;
    @Column //COLUMNA BASE DE DATOS
    private int age;

    public Person (){
    }

    public Person(long id, String firstName, String lastname, int age) {
        this.firstName = firstName;
        this.lastname = lastname;
        this.age = age;
        this.id = id;
    }


    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastname='" + lastname + '\'' +
                ", age=" + age +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
