package com.daw135.dawFinalProyect.controller.admin.auth;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AuthController {
    @GetMapping("/login")
    public String login(
            @RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "logout", required = false) String logout,
            Model model) {

        if (error != null) {
            model.addAttribute("error", "Error al iniciar sesión");
        }
        if (logout != null) {
            model.addAttribute("message", "Has cerrado sesión correctamente");
        }

        // Si ya está autenticado, redirigir al dashboard
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated() && !(auth instanceof AnonymousAuthenticationToken)) {
            return "redirect:/dashboard";
        }

        return "pages/auth/login";
    }

    @GetMapping("/profile")
    public ModelAndView profile(@AuthenticationPrincipal OidcUser user, @RequestParam(required = false) boolean debug) {
        if (user == null) {
            return new ModelAndView("redirect:/login");
        }

        ModelAndView mav = new ModelAndView("pages/profile/profile");
        // mav.addObject("user", user.getAttributes());
        mav.addObject("user", user);
        mav.addObject("showAllAttributes", debug);
        return mav;
    }

    @GetMapping("/access-denied")
    public String showAccessDeniedPage() {
        return "pages/auth/access-denied";
    }

    @GetMapping("/private-page")
    public String showPrivatePage() {
        return "pages/auth/private-page";
    }
    
}