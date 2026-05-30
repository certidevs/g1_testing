package com.demo.service;

import com.demo.dto.UserStatsDTO;
import com.demo.model.User;
import com.demo.model.enums.Role;
import com.demo.repository.ReviewRepository;
import com.demo.repository.UserRepository;
import com.demo.dto.RegisterForm;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ReviewRepository reviewRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("user not found with username: " + username) );
    }

    public User register(RegisterForm form) {

        if (userRepository.existsByUsername(form.getUsername()))
            throw new IllegalArgumentException("username ya existe, elige otro username");

        if (userRepository.existsByEmail(form.getEmail()))
            throw new IllegalArgumentException("email ya existe, elige otro email");

        if (! form.getPassword().equals(form.getPasswordConfirm()))
            throw new IllegalArgumentException("Las contraseñas no coinciden");

//        if (!form.getAcceptRGPD())
//            throw new IllegalArgumentException("Debes aceptar la política de privacidad");

        User user = new User();
        user.setUsername(form.getUsername());
        user.setEmail(form.getEmail());
        user.setRole(Role.ROLE_USER);
        // user.setPassword(form.getPassword()); // password en texto plano
        String encodedPassword = passwordEncoder.encode(form.getPassword());
        user.setPassword(encodedPassword); // $2a$10$u7/W/ivh4XDB40YBjdE9o.wTRaXFitlUrXSUorudG1IdZs/mL2DHu
        return userRepository.save(user);
    }
    //Utilizamos el Service para llamar desde aqui el repository que antes lo haciamos desde el controller
    public List<User> findAll(){
        return userRepository.findAll();
    }

    //En el metodo anterior indicamos que ese metodo nos devuelve algo de TIPO lista User
    //En este metodo como es 1 usuario en particular, el metodo devuelve algo de TIPO user.
    //Es lo mismo que haciamos antes en el controller, pedirle al Repository que nos de el user por id y si no que nos de una excepcion
    public User findById(Long id){
        return userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));
    }


}
