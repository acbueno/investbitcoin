package br.com.acbueno.bitcoinbuy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.acbueno.bitcoinbuy.model.User;
import br.com.acbueno.bitcoinbuy.service.impl.UserServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/user")
@Api(value = "User")
public class UserController {

  @Autowired
  UserServiceImpl userService;
  
  @ApiOperation(value = "Cadastra um novo usuário no banco de dados")
  @PostMapping("/create")
  public ResponseEntity<User> createUser(@RequestBody User user) {

    try {

      User userData = userService.createUser(new User(user.getName(), user.getCpf()));

      return new ResponseEntity<>(userData, HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
