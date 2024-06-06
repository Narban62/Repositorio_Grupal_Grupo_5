package ec.edu.uce.Game_Persistance.services;

import ec.edu.uce.Game_Persistance.models.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ServiceWeb {//capa controlador


    @RequestMapping(value = "/miurl", method = RequestMethod.GET)//tipo get SIEMPRE ESPECIFICAR
    public List<User> miPrimerEndpoint(){
        List<User> listUser = new ArrayList<User>();
        User user = new User(1,"XYZ",1,100);
        listUser.add(user);

        return listUser ;
    }





    @RequestMapping(value = "/miurl2", method = RequestMethod.GET)//tipo get SIEMPRE ESPECIFICAR
    public String miSegundoEndpoint(){
        return "Este es mi segundo Endpoint con GET" ;
        //post entra cuando no tenemos que especificar get
    }



}
