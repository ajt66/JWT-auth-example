package com.example.jwt_auth_example.Service;

import com.example.jwt_auth_example.dto.AuthRequest;
import com.example.jwt_auth_example.dto.AuthResponse;
import com.example.jwt_auth_example.dto.RegisterRequest;
import com.example.jwt_auth_example.Entity.User;
import com.example.jwt_auth_example.Repository.UserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

        private final UserRepository userRepository;
        private final PasswordEncoder passwordEncoder;
        private final JwtService jwtService;
        private final AuthenticationManager authenticationManager;

        public AuthResponse register(RegisterRequest request) {
                // Create new user with encoded password
                var user = User.builder()
                                .username(request.getUsername())
                                .email(request.getEmail())
                                .password(passwordEncoder.encode(request.getPassword()))
                                .role("USER")
                                .build();

                // Save to database
                userRepository.save(user);

                // Convert to UserDetails for Spring Security
                var userDetails = user.convertToUserDetails();

                // Generate JWT for immediate login after registration
                var jwt = jwtService.generateToken(userDetails);

                return AuthResponse.builder()
                                .token(jwt)
                                .build();
        }

        public AuthResponse authenticate(AuthRequest request) {
                // Let Spring Security validate credentials
                authenticationManager.authenticate(
                                new UsernamePasswordAuthenticationToken(
                                                request.getUsername(),
                                                request.getPassword()));

                // If we get here, credentials are valid
                var user = userRepository.findByUsername(request.getUsername())
                                .orElseThrow();

                // Generate and return JWT
                var userDetails = user.convertToUserDetails();
                var jwt = jwtService.generateToken(userDetails);

                return AuthResponse.builder()
                                .token(jwt)
                                .build();
        }
}
