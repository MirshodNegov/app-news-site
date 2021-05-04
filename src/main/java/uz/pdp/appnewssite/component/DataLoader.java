package uz.pdp.appnewssite.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import uz.pdp.appnewssite.entity.Role;
import uz.pdp.appnewssite.entity.User;
import uz.pdp.appnewssite.entity.enums.Huquq;
import uz.pdp.appnewssite.repository.RoleRepository;
import uz.pdp.appnewssite.repository.UserRepository;
import uz.pdp.appnewssite.utils.AppConstants;

import java.util.Arrays;

import static uz.pdp.appnewssite.entity.enums.Huquq.*;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    RoleRepository roleRepository;
    @Autowired
    UserRepository userRepository;
    @Value("${spring.datasource.initialization-mode}")
    private String initialMode;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if (initialMode.equals("always")) {
            Huquq[] huquqs = Huquq.values();

            Role admin = roleRepository.save(new Role(
                    AppConstants.ADMIN,
                    Arrays.asList(huquqs),
                    "Sayt egasi"
            ));

            Role user = roleRepository.save(new Role(
                    AppConstants.USER,
                    Arrays.asList(DELETE_MY_COMMENT, EDIT_COMMENT, ADD_COMMENT),
                    "oddiy foydalanuvchi"
            ));

            userRepository.save(new User(
                    "Admin",
                    "admin",
                    passwordEncoder.encode("admin123"),
                    admin,
                    true
            ));

            userRepository.save(new User(
                    "User",
                    "user",
                    passwordEncoder.encode("user123"),
                    user,
                    true
            ));
        }
    }

}
