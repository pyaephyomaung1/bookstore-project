package org.example.bookapi.service;


import org.example.bookapi.dao.CustomerDao;
import org.example.bookapi.dao.RoleDao;
import org.example.bookapi.dto.LoginDto;
import org.example.bookapi.dto.RegisterDto;
import org.example.bookapi.entity.Customer;
import org.example.bookapi.entity.Role;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AuthService {

    private final CustomerDao customerDao;
    private final RoleDao roleDao;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthService( CustomerDao customerDao,
                        RoleDao roleDao,
                        PasswordEncoder passwordEncoder,
                        AuthenticationManager authenticationManager

    ) {

        this.customerDao = customerDao;
        this.roleDao = roleDao;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;


    }

    public List<Customer> getAllCustomers(){

        return customerDao.findAll();

    }

    @Transactional
    public String register( RegisterDto registerDto ) {

        Customer customer = new Customer(
                registerDto.name(),
                registerDto.username(),
                passwordEncoder.encode(registerDto.password()),
                registerDto.email()
        );
        Role userRole = roleDao.findByRoleName( "ROLE_USER" ).get();
        customer.addRole(userRole);
        Customer registeredCustomer = customerDao.save(customer);

        return registeredCustomer.getId()+" successfully registered!!";

    }

    public List<Customer> findAll(){
        return customerDao.findAll();
    }

    public String login( LoginDto loginDto ) {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsername(), loginDto.getPassword()
        ));

        SecurityContextHolder.getContext().setAuthentication( authentication );

        return "successfully login";

    }


}
