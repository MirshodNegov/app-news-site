package uz.pdp.appnewssite.config;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import uz.pdp.appnewssite.entity.User;

import java.util.Optional;

public class KimYozganiBilishUchun implements AuditorAware<Long> {
    @Override
    public Optional<Long> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication!=null &&
                authentication.isAuthenticated() &&
                !authentication.getPrincipal().equals("anonymousUser")){
            User employee = (User) authentication.getPrincipal();
            return Optional.of(employee.getId());
        }
            return Optional.empty();
    }
}
