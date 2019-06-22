package com.dps.controllers;

import java.net.URI;
import java.util.Collections;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.dps.dtos.ApiResponse;
import com.dps.dtos.JwtAuthenticationResponse;
import com.dps.dtos.LoginRequest;
import com.dps.dtos.SignUpRequest;
import com.dps.entities.Role;
import com.dps.entities.RoleName;
import com.dps.entities.User;
import com.dps.entities.UserPrincipal;
import com.dps.exception.AppException;
import com.dps.handlers.CurrentUser;
import com.dps.handlers.JwtTokenProvider;
import com.dps.repositories.RoleRepository;
import com.dps.repositories.UserRepository;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenProvider tokenProvider;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsernameOrEmail(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        if(userRepository.existsByUsername(signUpRequest.getUsername())) {
            return new ResponseEntity(new ApiResponse(false, "Username is already taken!"), HttpStatus.BAD_REQUEST);
        }

        if(userRepository.existsByEmail(signUpRequest.getEmail())) {
            return new ResponseEntity(new ApiResponse(false, "Email Address already in use!"), HttpStatus.BAD_REQUEST);
        }

        // Creating user's account
        User user = new User(signUpRequest.getName(), signUpRequest.getUsername(), signUpRequest.getEmail(), signUpRequest.getPassword());

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        
        Role userRole = roleRepository.findByName(RoleName.ROLE_USER).orElseThrow(() -> new AppException("User Role not set."));
        user.setRoles(Collections.singleton(userRole));
        User result = userRepository.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/users/{username}").buildAndExpand(result.getUsername()).toUri();
        return ResponseEntity.created(location).body(new ApiResponse(true, "User registered successfully"));
    }	

    @PostMapping("/isusernameavailable")
    public ResponseEntity<?> checkUsernameAvailability(@RequestParam("username") String username) {
        if(null == username || "".equals(username)) {
            return new ResponseEntity(new ApiResponse(false, "Enter email address!"), HttpStatus.BAD_REQUEST);
        }
        else if(userRepository.existsByUsername(username)) {
            return new ResponseEntity(new ApiResponse(false, "Username already in use!"), HttpStatus.BAD_REQUEST);
        }
    	return new ResponseEntity(new ApiResponse(true, "Username is available"), HttpStatus.OK);
    }
    
    @PostMapping("/isemailavailable")
    public ResponseEntity<?> checkEmailAvailability(@RequestParam("email") String email) {
        if(null == email || "".equals(email)) {
            return new ResponseEntity(new ApiResponse(false, "Enter username!"), HttpStatus.BAD_REQUEST);
        }
        else if(userRepository.existsByEmail(email)) {
            return new ResponseEntity(new ApiResponse(false, "Email already in use!"), HttpStatus.BAD_REQUEST);
        }
    	return new ResponseEntity(new ApiResponse(true, "Email is available"), HttpStatus.OK);
    }

    @PostMapping("/userroles")
    public ResponseEntity<?> userroles(@CurrentUser UserPrincipal user) {
    	if(null == user || null == user.getId()) {
    		return new ResponseEntity(new ApiResponse(false, "Invalid user!"), HttpStatus.OK);
    	}
    	return new ResponseEntity(new ApiResponse(true, user.getAuthorities().stream().map((e) -> e.getAuthority()).collect(Collectors.toList()).toString()), HttpStatus.OK);
    }

}
