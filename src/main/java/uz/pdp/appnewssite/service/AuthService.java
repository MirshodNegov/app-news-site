package uz.pdp.appnewssite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.pdp.appnewssite.entity.User;
import uz.pdp.appnewssite.exceptions.ResourceNotFoundException;
import uz.pdp.appnewssite.payload.ApiResponse;
import uz.pdp.appnewssite.payload.LoginDTO;
import uz.pdp.appnewssite.payload.RegisterDTO;
import uz.pdp.appnewssite.repository.RoleRepository;
import uz.pdp.appnewssite.repository.UserRepository;
import uz.pdp.appnewssite.security.JwtProvider;
import uz.pdp.appnewssite.utils.AppConstants;

@Service
public class AuthService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtProvider jwtProvider;

    public ApiResponse registerUser(RegisterDTO registerDTO) {
        if (!registerDTO.getPassword().equals(registerDTO.getPrePassword()))
            return new ApiResponse("Parollar mos emas!", false);
        boolean exists = userRepository.existsByUsername(registerDTO.getUsername());
        if (exists)
            return new ApiResponse("Bunday usernameli foydalanuvchi mavjud", false);
        User user = new User(
                registerDTO.getFullName(),
                registerDTO.getUsername(),
                passwordEncoder.encode(registerDTO.getPassword()),
                roleRepository.findByName(AppConstants.USER).orElseThrow(
                        () -> new ResourceNotFoundException("role", "name", AppConstants.USER)),
                true
        );
        userRepository.save(user);
        return new ApiResponse("Muvaffaqiyatli ro'yxatdan o'tdizgiz !", true);
    }


    public UserDetails loadUserByUsername(String userName) {
        return userRepository.findByUsername(userName).orElseThrow(() -> new UsernameNotFoundException(userName));
    }

    public ApiResponse loginUser(LoginDTO dto) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken
                    (dto.getUsername(), dto.getPassword()));
            User user = (User) authentication.getPrincipal();
            String token = jwtProvider.generateToken(user.getUsername(), user.getRole());
            return new ApiResponse("Token", true, token);
        } catch (BadCredentialsException badCredentialsException) {
            return new ApiResponse("Parol yoki username xato", false);
        }
    }
}
