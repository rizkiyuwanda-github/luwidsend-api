package id.my.rizkiyuwanda.luwidsendapi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/latihan")
public class WelcomeController {
    @GetMapping
    public String welcome(){
        return "Welcome";
    }

}
