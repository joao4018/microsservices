package com.microsservices.auth.endpoint.controller;

import com.microsservices.core.model.ApplicationUser;
import com.microsservices.core.repository.ApplicationUserRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

/**
 * @author joao4018
 */
@RestController
@RequestMapping("user")
@Api(value = "Endpoints to manage User's information")
public class UserInfoController {

    private final ApplicationUserRepository applicationUserRepository;

    public UserInfoController(ApplicationUserRepository userDAO) {
        this.applicationUserRepository = userDAO;
    }

    @GetMapping(path = "info", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "Will retrieve the information from the user available in the token", response = ApplicationUser.class)
    public ResponseEntity<ApplicationUser> getUserInfo(Principal principal) {
        ApplicationUser applicationUser = (ApplicationUser) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();
        return new ResponseEntity<>(applicationUser, HttpStatus.OK);
    }
    @PostMapping(path = "signup")
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<?> save(@Valid @RequestBody ApplicationUser applicationUser) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String password =  passwordEncoder.encode(applicationUser.getPassword());
        applicationUser.setPassword(password);
        ApplicationUser userSignup = new ApplicationUser(applicationUser);
        return new ResponseEntity<>(applicationUserRepository.save(userSignup), HttpStatus.CREATED);
    }

}
