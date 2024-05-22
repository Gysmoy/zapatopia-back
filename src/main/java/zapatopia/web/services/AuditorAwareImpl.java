package zapatopia.web.services;

import org.springframework.data.domain.AuditorAware;
import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//
//        if (authentication != null && authentication.getPrincipal() instanceof User) {
//            User user = (User) authentication.getPrincipal();
//            return Optional.of(user.getUsername());
//        }

        return Optional.empty();

    }
}
