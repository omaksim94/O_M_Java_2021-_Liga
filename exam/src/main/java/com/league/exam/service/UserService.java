package com.league.exam.service;

import com.league.exam.model.User;
import com.league.exam.model.dto.UserDetailsDto;
import com.league.exam.repository.RoleRepository;
import com.league.exam.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Transactional
    public void registerNewUser(Map<String, String> json) {
        if (userRepository.findUserByEmail(json.get("email")).isPresent()) {
            throw new IllegalStateException("User with email: " + json.get("email") + " has already been registered");
        }

        User newUser = new User();
        newUser.setFirstName(json.get("first_name"));
        newUser.setLastName(json.get("last_name"));
        newUser.setPhoneNumber(json.get("phone_number"));
        newUser.setEmail(json.get("email"));
        newUser.setPassword(passwordEncoder.encode(json.get("password")));
        newUser.setRole(roleRepository.getByRoleName(json.get("role")));
        newUser.setAccountNonExpired(true);
        newUser.setAccountNonLocked(true);
        newUser.setCredentialsNonExpired(true);
        newUser.setEnabled(true);
        newUser.setBookedAppointment(new HashSet<>());

        userRepository.save(newUser);
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findUserByEmail(email);
        User user = optionalUser
                .orElseThrow(() -> new EntityNotFoundException("User with name: " + email + " not found"));
        return new UserDetailsDto(user);
    }
}
