package main;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class Main {

    @GetMapping(path = "/health", produces = "application/json")
    public ResponseEntity health() {
        Response res = new Response();
        res.setResult(true);
        return ResponseEntity.ok(res);
    }

}
