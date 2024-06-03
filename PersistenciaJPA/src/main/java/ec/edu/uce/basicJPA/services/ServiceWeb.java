package ec.edu.uce.basicJPA.services;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServiceWeb {//capa controlador
    @RequestMapping(value = "/miurl", method = RequestMethod.GET)//tipo get SIEMPRE ESPECIFICAR
    public String miPrimerEndpoint(){
        return "Este es mi primer Endpoint con GET" ;
    }
    @RequestMapping(value = "/miurl2", method = RequestMethod.GET)//tipo get SIEMPRE ESPECIFICAR
    public String miSegundoEndpoint(){
        return "Este es mi segundo Endpoint con GET" ;
        //post entra cuando no tenemos que especificar get
    }
}
