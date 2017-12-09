package avile.service;

import avile.domain.Account;
import avile.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;


@Service
public class CustomUserDetailsService implements UserDetailsService{

    @Autowired
    AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findByEmail(username);
        if(account == null) {
            throw new UsernameNotFoundException("No such user: " + username);
        }
        return new User(account.getEmail(), account.getPassword(), new HashSet<>(Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"))));
    }
}
