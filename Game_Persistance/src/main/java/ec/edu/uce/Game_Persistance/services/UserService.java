package ec.edu.uce.Game_Persistance.services;


import ec.edu.uce.Game_Persistance.models.User;
import ec.edu.uce.Game_Persistance.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public void save(User user){
        userRepository.save(user);

    }

    public void retrive(){
        List<User> listUser;
        listUser =  userRepository.findAll();
        for (User cadena: listUser){
            System.out.println(cadena.toString());
        }
    }

    public void retrivebyID(Long id){
        Optional<User> user = userRepository.findById(id);
        System.out.println(user.toString());

    }

    public Optional<User> retrivebyLastName(String usuario){
        Optional<User> user = Optional.ofNullable(userRepository.findByUser(usuario));
        System.out.println(user.toString());
        return user;
    }

    public void updateScore(int lvl, int score,String usuario){
        userRepository.updateScore(lvl,score,usuario);
    }

    public void deleteUser(String usuario){
        userRepository.deleteUserByName(usuario);
    }


}
