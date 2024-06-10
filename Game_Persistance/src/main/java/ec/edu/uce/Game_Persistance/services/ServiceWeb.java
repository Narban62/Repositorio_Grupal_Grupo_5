package ec.edu.uce.Game_Persistance.services;

import ec.edu.uce.Game_Persistance.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class ServiceWeb {

    @Autowired
    UserService service;


    @PostMapping("/register")
    public ResponseEntity<String> receiveScore(@RequestBody User user) {
        service.save(user);
        System.out.println(user.toString());
        return ResponseEntity.ok("Datos recibidos correctamente");
    }

    @PutMapping("/updateHeroData")
    public ResponseEntity<String> updateUserState(@RequestBody User user) {
        service.updateScore(user.getLevel(), user.getScore(),user.getLife(), user.getUser());
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
        service.updateScore(user.getLevel(), user.getScore(), user.getLife(), user.getUser());
        return ResponseEntity.ok("Datos del usuario : " + delUser +  " se han borrado correctamente");
    }


}
