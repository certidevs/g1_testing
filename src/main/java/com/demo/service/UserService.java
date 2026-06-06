package com.demo.service;

import com.demo.dto.UserStatsDTO;
import com.demo.model.Review;
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

    public User create(User user){
        if (userRepository.existsByUsername(user.getUsername())){
            throw new IllegalArgumentException("Este username ya está ocupado");
        }
        if (userRepository.existsByEmail(user.getEmail())){
            throw new IllegalArgumentException("Este email ya está ocupado");
        }
//        if (user.getPassword() == null || user.getPassword().isBlank())
//            throw new IllegalArgumentException("La contraseña es obligatoria");

        //Si usamos Spring
        if (!StringUtils.hasText(user.getPassword()))
            throw new IllegalArgumentException("Password no puede estar vacía");

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User update(User userForm, Long currentUserId) {
        //Obtenemos de BD el usuario
        User userDB = findById(userForm.getId());

        //Comprobamos si el username esta ocupado por un usuario distinto a este usuario.
        Optional<User> userOptional = userRepository.findByUsername(userForm.getUsername());

        if(userOptional.isPresent() && !userOptional.get().getId().equals(userForm.getId())){
            throw new IllegalArgumentException("Este username ya existe");
        }

        //Con el email se hace lo mismo
        userRepository.findByEmail(userForm.getEmail())
                .filter(user -> !user.getId().equals(userForm.getId()))
                .ifPresent(user -> {
                    throw new IllegalArgumentException("El email de usuario ya existe");
                });

        userDB.setUsername(userForm.getUsername());
        userDB.setEmail(userForm.getEmail());
        userDB.setRole(userForm.getRole());
        userDB.setImageUrl(userForm.getImageUrl());

        // Impedir que un admin se desactive a sí mismo
        if (userDB.getRole() == Role.ROLE_ADMIN
                && currentUserId.equals(userDB.getId())
                && Boolean.FALSE.equals(userForm.getActive())) {
            throw new IllegalArgumentException("Un administrador no puede desactivarse a sí mismo");
        }
        userDB.setActive(userForm.getActive());

        // Si se introduce una nueva contraseña se cifra y actualizamos,
        // sino la contraseña actual queda sin cambios
        if(StringUtils.hasText(userForm.getPassword()))
            userDB.setPassword(passwordEncoder.encode(userForm.getPassword()));

        return userRepository.save(userDB); // guardamos el usuario ACTUALIZADO en BD
    }

    public void deactivate(Long userId, Long currentUserId) {
        User user = findById(userId);
        if (user.getRole() == Role.ROLE_ADMIN) {
            throw new IllegalArgumentException("No se puede desactivar una cuenta de administrador");
        }
        if (currentUserId.equals(userId)) {
            throw new IllegalArgumentException("No puedes desactivar tu propia cuenta");
        }
        user.setActive(false);
        userRepository.save(user);
    }

    public void activate(Long userId) {
        User user = findById(userId);
        user.setActive(true);
        userRepository.save(user);
    }

    public UserStatsDTO findStatsById(Long id) {
        return new UserStatsDTO(
                reviewRepository.countByUser_Id(id),
                reviewRepository.findByUser_Id(id)
        );
    }

}
