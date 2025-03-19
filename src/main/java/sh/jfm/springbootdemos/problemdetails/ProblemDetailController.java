package sh.jfm.springbootdemos.problemdetails;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProblemDetailController {

    @GetMapping("/sample-endpoint/{num}")
    String moreThanZero(@PathVariable int num) {
        if (num <= 0) {
            throw new IllegalArgumentException("num must be more zero");
        }
        return num + " is positive";
    }
}
