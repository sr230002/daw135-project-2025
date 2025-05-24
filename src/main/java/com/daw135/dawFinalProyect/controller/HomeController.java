package com.daw135.dawFinalProyect.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

    @RequestMapping
    public String index() {
        return "redirect:/dashboard";
    }

    // @GetMapping(value = "/")
    // @ResponseBody
    // public String home(final Authentication authentication) {
    //     TestingAuthenticationToken token = (TestingAuthenticationToken) authentication;
    //     DecodedJWT jwt = JWT.decode(token.getCredentials().toString());
    //     String email = jwt.getClaims().get("email").asString();
    //     return "Welcome, " + email + "!";
    // }

}
