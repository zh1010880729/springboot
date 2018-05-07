package cn.linkedcare.controller;

import cn.linkedcare.entity.User;
import cn.linkedcare.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Benji on 2018/5/7.
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "me", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getLoginInfo() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findById(1));
    }

    @PostMapping(value = "register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity register(@RequestBody User user) {
        userService.insert(user);
        return ResponseEntity.status(HttpStatus.OK).body("注册成功");
    }
}
