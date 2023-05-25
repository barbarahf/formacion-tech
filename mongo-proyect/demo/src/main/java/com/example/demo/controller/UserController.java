package com.example.demo.controller;

import com.example.demo.models.UserModel;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/apitechu/v2")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/users")
    public ResponseEntity<Object> getAllUsers(@RequestParam Optional<String> order) {
        if (order.isPresent()) {
            return new ResponseEntity<>(this.userService.getAllUsersOrderByAge(order.get()), HttpStatus.OK);
        }
        return new ResponseEntity<>(this.userService.getAllUsersOrderByAge(), HttpStatus.OK);
    }

    @PostMapping("/users")
    public ResponseEntity<UserModel> createUser(@RequestBody UserModel user) {
        return new ResponseEntity<>(this.userService.createUser(user), HttpStatus.CREATED);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<Object> getUserById(@PathVariable String id) {

        Optional<UserModel> result = this.userService.getUserById(id);

        return new ResponseEntity<>(
                result.isPresent() ? result.get() : "usuario no encontrado",
                result.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND
        );
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<UserModel> updateUser(@RequestBody UserModel user, @PathVariable String id) {

        Optional<UserModel> userToUpdate = this.userService.getUserById(id);

        if (!id.equalsIgnoreCase(user.getId())) {
            System.out.println("Error, el campo ID no puede ser modificado");
            return new ResponseEntity<>(
                    user, HttpStatus.BAD_REQUEST);
        }
        if (userToUpdate.isPresent()) {
            System.out.println("Se ha encontrado el usuario para ser actualizado");
            this.userService.updateUser(user, id);
        }
        return new ResponseEntity<>(
                user, userToUpdate.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable String id) {
        System.out.println("deleteProduct");

        boolean deletedProduct = this.userService.deleteUser(id);

        return new ResponseEntity<>(
                deletedProduct ? "Producto borrado" : "producto no borrado",
                deletedProduct ? HttpStatus.OK : HttpStatus.NOT_FOUND
        );
    }
}
