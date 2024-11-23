package u.utd.service.impl;

import lombok.AllArgsConstructor;
import u.utd.dto.JwtAuthResponse;
import u.utd.dto.LoginDto;
import u.utd.dto.RegisterDto;
import u.utd.entity.Role;
import u.utd.entity.User;
import u.utd.exception.MatchApiException;
import u.utd.repository.RoleRepository;
import u.utd.repository.UserRepository;
import u.utd.security.JwtTokenProvider;
import u.utd.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;
    private JwtTokenProvider jwtTokenProvider;

    @Override
    public String register(RegisterDto registerDto) {
        if (userRepository.existsByUsername(registerDto.getUsername())) {
            throw  new MatchApiException(HttpStatus.BAD_REQUEST, "Username is already in use");
        }
        if (userRepository.existsByEmail(registerDto.getEmail())) {
            throw new MatchApiException(HttpStatus.BAD_REQUEST, "Email is already in use");
        }

        User user = new User();
        user.setName(registerDto.getName());
        user.setUsername(registerDto.getUsername());
        user.setEmail(registerDto.getEmail());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));

        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName("ROLE_USER");
        roles.add(userRole);

        user.setRoles(roles);

        userRepository.save(user);

        return "User  Registered Successfully";
    }

    @Override
    public JwtAuthResponse login(LoginDto loginDto) {

        try {

            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsernameOrEmail(), loginDto.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);

            String token = jwtTokenProvider.generateToken(authentication);

            Optional<User> userOptional = userRepository.findByUsernameOrEmail(loginDto.getUsernameOrEmail(), loginDto.getUsernameOrEmail());

            String role = null;

            if (userOptional.isPresent()) {
                User loggedInUser = userOptional.get();
                Optional<Role> optionalRole =  loggedInUser.getRoles().stream().findFirst();

                if (optionalRole.isPresent()) {
                    Role userRole = optionalRole.get();
                    role = userRole.getName();
                }
            }

            JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
            jwtAuthResponse.setRole(role);
            jwtAuthResponse.setAccessToken(token);

            return jwtAuthResponse;
        } catch (BadCredentialsException e) {
            // Catching BadCredentialsException thrown by authenticationManager
            throw new MatchApiException(HttpStatus.UNAUTHORIZED, "Invalid username or password");
        }
    }


}
