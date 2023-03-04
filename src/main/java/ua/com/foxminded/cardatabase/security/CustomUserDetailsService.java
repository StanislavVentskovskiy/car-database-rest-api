package ua.com.foxminded.cardatabase.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ua.com.foxminded.cardatabase.dao.repository.UserRepository;
import ua.com.foxminded.cardatabase.model.UserEntity;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByUsername(username).get();
        if (userEntity != null){

            return new UserInfoUserDetails(userEntity);
        } else {
            throw new UsernameNotFoundException("Invalid username or password");
        }
    }
}
