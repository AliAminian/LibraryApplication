package com.example.library.service;

import com.example.library.dto.AuthResponseDTO;
import com.example.library.dto.LoginDTO;
import com.example.library.dto.RegisterDTO;
import com.example.library.model.UserEntity;

public interface AuthService {
    AuthResponseDTO getAuthToken(LoginDTO loginDto);
    UserEntity registerUser(RegisterDTO registerDTO);
    Boolean isAuthorized(String username);
}
