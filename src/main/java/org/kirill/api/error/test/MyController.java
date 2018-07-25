package org.kirill.api.error.test;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kirill.api.error.test.error.CoreException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MyController {

    private final UserRepo userRepo;

    @PostMapping("/user")
    public ResponseEntity<?> createUser(@RequestBody User user) {
        log.info("Creating user: " + user);
        user = userRepo.save(user);
        log.info("User created: " + user);
        return ResponseEntity.ok().body(user);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<?> getUser(@PathVariable String id) {
        throw CoreException.of("user.notFound");
    }

    @GetMapping("/wtf")
    public ResponseEntity<?> wtf() {
        throw new NullPointerException();
    }

    @PostMapping("/email/{email}")
    public ResponseEntity<?> registerEmail(@PathVariable String email) {
        throw CoreException.of("email.alreadyRegistered");
    }
}
