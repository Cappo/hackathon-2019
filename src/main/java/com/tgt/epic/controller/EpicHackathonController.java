package com.tgt.epic.controller;

import com.tgt.epic.domain.User;
import com.tgt.epic.service.EpicHackathonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/epichackathonapi/v1")
public class EpicHackathonController {

    @Autowired
    private EpicHackathonService epicHackathonService;

    @GetMapping(path = "/testget")
    public ResponseEntity testGetRequest() {
        return ResponseEntity.status(HttpStatus.OK).body("HELLO WORLD");
    }

    @PutMapping(path = "/testput")
    public ResponseEntity testPutRequest(@RequestBody String input) {
        return ResponseEntity.status(HttpStatus.OK).body("HELLO WORLD");
    }

    @PostMapping(path = "/testpost")
    public ResponseEntity testPostRequest(@RequestBody String input) {
        return ResponseEntity.status(HttpStatus.OK).body("HELLO WORLD");
    }

    @GetMapping(path = "/get_all_users")
    public ResponseEntity getAllUsers() {
        List<User> users = epicHackathonService.getAllUsers();
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

    @GetMapping(path = "/get_one_user")
    public ResponseEntity getOneUser(@RequestParam("email") String email) {
        User user = epicHackathonService.getOneUser(email);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @GetMapping(path = "/get_user_address")
    public ResponseEntity getUserAddress(@RequestParam("email") String email) {
        User user = epicHackathonService.getOneUser(email);
        return ResponseEntity.status(HttpStatus.OK).body(user.getAddress());
    }

    @PostMapping(path = "/add_user")
    public ResponseEntity addUser(@RequestBody User user) {
        if(epicHackathonService.addUser(user))
            return ResponseEntity.status(HttpStatus.OK).body("User successfully added");
        else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Adding user failed. Does this user already exist?");
        }
    }

    @PostMapping(path = "/update_user")
    public ResponseEntity updateUser(@RequestBody User user, @RequestParam("email") String oldEmail) {
        if(epicHackathonService.updateUser(user, oldEmail))
            return ResponseEntity.status(HttpStatus.OK).body("User successfully updated");
        else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Updating user failed.");
        }
    }


}
