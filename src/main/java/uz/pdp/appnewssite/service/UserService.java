package uz.pdp.appnewssite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appnewssite.entity.Role;
import uz.pdp.appnewssite.entity.User;
import uz.pdp.appnewssite.payload.ApiResponse;
import uz.pdp.appnewssite.payload.UserDTO;
import uz.pdp.appnewssite.repository.RoleRepository;
import uz.pdp.appnewssite.repository.UserRepository;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;

    public ApiResponse addUser(UserDTO userDTO) {
        boolean exists = userRepository.existsByUsername(userDTO.getUsername());
        if (exists)
            return new ApiResponse("Bunday usernameli foydalanuvhci mavjud",false);
        Optional<Role> optionalRole = roleRepository.findById(userDTO.getRoleId());
        if (!optionalRole.isPresent())
            return new ApiResponse("Role id si xato berildi", false);
        User user = new User(userDTO.getFullName(), userDTO.getUsername(),
                userDTO.getPassword(), optionalRole.get(), true);
        userRepository.save(user);
        return new ApiResponse("Yangi user saqlandi", true);
    }
}
