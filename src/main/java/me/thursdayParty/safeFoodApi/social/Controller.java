package me.thursdayParty.safeFoodApi.social;

import me.thursdayParty.safeFoodApi.social.dto.AuthCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

/**
 * @author Bactoria
 * @since 2019-11-22 [2019.11월.22]
 */

@CrossOrigin
@RestController
public class Controller {

    @GetMapping("/")
    public Principal home(Principal principal) {
        System.out.println(principal.getName());

        return principal;
    }

    @PostMapping("/auth/google")
    public ResponseEntity home(@RequestBody AuthCode authCode) {
        System.out.println(authCode);

        return ResponseEntity.ok().build();
    }

}
