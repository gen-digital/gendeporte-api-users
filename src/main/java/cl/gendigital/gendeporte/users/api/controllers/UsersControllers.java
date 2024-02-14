package cl.gendigital.gendeporte.users.api.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/hola")
@RestController
public class UsersControllers {
    @GetMapping
    public String user(){
        return "hola";
    }
    
}
