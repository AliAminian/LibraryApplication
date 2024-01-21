package com.example.library.controller;

import com.example.library.dto.AuthResponseDTO;
import com.example.library.dto.LoginDTO;
import com.example.library.dto.RegisterDTO;
import com.example.library.service.AuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@Api(tags = "Authentication and Authorization Management")
public class AuthController {

    private final AuthService userRoleService;

    @Autowired
    public AuthController(AuthService userRoleService) {
        this.userRoleService = userRoleService;
    }

    @PostMapping("/login")
    @ApiOperation(value = "login with username and password", produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "new login with newly generated token", response = AuthResponseDTO.class),
            @ApiResponse(code = 400, message = "An error occurred")
    })
    public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginDTO loginDto){
        AuthResponseDTO token = userRoleService.getAuthToken(loginDto);
        if (token.getAccessToken().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        return new ResponseEntity<>(token, HttpStatus.OK);
    }

    @PostMapping("/register")
    @ApiOperation(value = "register new user", produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "new login with newly generated token", response = String.class),
            @ApiResponse(code = 400, message = "username is already taken"),
            @ApiResponse(code = 401, message = "unauthorized user")
    })
    public ResponseEntity<String> register(@RequestBody RegisterDTO registerDto) {
        if (userRoleService.isAuthorized(registerDto.getUsername())) {
            return new ResponseEntity<>("Username is taken!", HttpStatus.BAD_REQUEST);
        }
        if (userRoleService.registerUser(registerDto) == null) {
            return new ResponseEntity<>("Try again", HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>("User registered successfully!", HttpStatus.OK);
    }
}