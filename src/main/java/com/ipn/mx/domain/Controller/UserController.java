package com.ipn.mx.domain.Controller;

import com.ipn.mx.domain.Entity.User;
import com.ipn.mx.domain.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.ipn.mx.domain.Entity.MD5.getMd5;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:4200", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
public class UserController {
    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@RequestBody User user) {
        // Create a new user
        System.out.println(user);
        userService.saveUser(user);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public User getUser(@PathVariable Long id) {
        // Get user by id
        return userService.getUserById(id);
    }

    @GetMapping("/correo/{correoUsuario}")
    @ResponseStatus(HttpStatus.OK)
    public User getUserByCorreoUsuario(@PathVariable String correoUsuario) {
        // Get user by email
        User usuario = userService.getUserByCorreoUsuario(correoUsuario);
        return (usuario == null) ? null : User.builder()
                .idUser(usuario.getIdUser())
                .nombreUsuario(usuario.getNombreUsuario())
                .correoUsuario(usuario.getCorreoUsuario())
                .imagenUsuario(usuario.getImagenUsuario())
                .contrasenaUsuario(getMd5(usuario.getContrasenaUsuario()))
                .build();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateUser(@PathVariable Long id, @RequestBody User user) {
        // Update an existing user
        if (!id.equals(user.getIdUser())) {
            throw new IllegalArgumentException("El ID de la URL y el ID del cuerpo no coinciden");
        }
        User existingUser = userService.getUserById(id);
        existingUser.setNombreUsuario(user.getNombreUsuario());
        existingUser.setCorreoUsuario(user.getCorreoUsuario());
        existingUser.setContrasenaUsuario(user.getContrasenaUsuario());
        existingUser.setImagenUsuario(user.getImagenUsuario());
        if(user.getLugares() != null) {

            existingUser.getLugares().clear();
            existingUser.getLugares().addAll(user.getLugares());
            user.getLugares().clear();
        }
        if(user.getPedidos() != null){
            existingUser.getPedidos().clear();
            existingUser.getPedidos().addAll(user.getPedidos());
        }
        if(user.getReviews() != null){
            existingUser.getReviews().clear();
            existingUser.getReviews().addAll(user.getReviews());
        }
        userService.saveUser(existingUser);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Long id) {
        // Delete an existing user
        userService.deleteUser(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }
}
