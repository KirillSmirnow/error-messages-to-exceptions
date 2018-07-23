package org.kirill.api.error.test;

import org.kirill.api.error.test.error.CoreException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

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
