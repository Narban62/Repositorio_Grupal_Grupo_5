package ec.edu.uce.Game_Persistance.services;

import ec.edu.uce.Game_Persistance.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class ServiceWeb {//capa controlador

    @Autowired
    UserService service;



    }

    @RequestMapping(value = "/miconsumo", method = RequestMethod.GET)//tipo get SIEMPRE ESPECIFICAR
    public Optional<User> endpointConsume(){
        Optional<User> user = service.retrivebyLastName("AND");
        return user ;
    }

    @PostMapping("/register")
    public ResponseEntity<String> receiveScore(@RequestBody User user) {
        service.save(user);
        System.out.println(user.toString());
        return ResponseEntity.ok("Datos recibidos correctamente");
    }

    @PutMapping("/updateHeroData")
    public ResponseEntity<String> updateUserState(@RequestBody User user) {
        service.updateScore(user.getLevel(), user.getScore(), user.getUser());
        return ResponseEntity.ok("Datos actualizados correctamente");
    }

    @GetMapping("/getUserData")
    public ResponseEntity<User> getUserData(@RequestParam String username) {
        Optional<User> user = service.retrivebyLastName(username);
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/DeleteHeroData")
    public ResponseEntity<String> DeleteUser(@RequestBody User user) {
        String delUser = user.toString();
        service.updateScore(user.getLevel(), user.getScore(), user.getUser());
        return ResponseEntity.ok("Datos del usuario : " + delUser +  " se han borrado correctamente");
    }


}
