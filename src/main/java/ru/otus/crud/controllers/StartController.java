package ru.otus.crud.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.crud.controllers.resp.HealthResponse;

@RestController
@RequestMapping("/")
public class StartController {

    @Value("${version}")
    private String version;

    @GetMapping(path = "/health", produces = "application/json")
    public ResponseEntity<?> health() {
        HealthResponse res = new HealthResponse();
        res.setResult(true);
        return ResponseEntity.ok(res);
    }

    @GetMapping(path = "/greet", produces = "application/json")
    public ResponseEntity<?> greet() {
        var host = System.getenv("HOST");
        var user = System.getenv("DB_USER");
        var pass = System.getenv("DB_PASS");
        var db = System.getenv("DB_NAME");
        return ResponseEntity.ok(
                "host: " + host+
                "user: " + user +
                "pass: " + pass +
                "db: " + db
        );
    }

    @GetMapping(path = "/version", produces = "application/json")
    public ResponseEntity<?> version() {
        return ResponseEntity.ok(version);
    }

}
