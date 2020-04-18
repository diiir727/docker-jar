package main;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class Main {

    @Value("${version}")
    private String version;

    @GetMapping(path = "/health", produces = "application/json")
    public ResponseEntity health() {
        Response res = new Response();
        res.setResult(true);
        return ResponseEntity.ok(res);
    }

    @GetMapping(path = "/version", produces = "application/json")
    public ResponseEntity version() {
        return ResponseEntity.ok(version);
    }

}
