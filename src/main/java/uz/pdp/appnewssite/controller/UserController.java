package uz.pdp.appnewssite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.appnewssite.payload.ApiResponse;
import uz.pdp.appnewssite.payload.UserDTO;
import uz.pdp.appnewssite.service.UserService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;

    @PreAuthorize(value = "hasAnyAuthority('ADD_USER')")
    @PostMapping("/add")
    public HttpEntity<?> addUser(@Valid @RequestBody UserDTO userDTO) {
        ApiResponse apiResponse = userService.addUser(userDTO);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }
}
