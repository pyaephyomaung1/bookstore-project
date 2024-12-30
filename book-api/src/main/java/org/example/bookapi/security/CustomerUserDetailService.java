package org.example.bookapi.security;

import org.example.bookapi.dao.CustomerDao;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomerUserDetailService implements UserDetailsService {

    public final CustomerDao customerDao;

    public CustomerUserDetailService(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return customerDao.findCustomerByUsername(username)
                .map(SecurityCustomer :: new)
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }
}
