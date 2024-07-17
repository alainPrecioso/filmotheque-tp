package fr.eni.tp.filmotheque.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import fr.eni.tp.filmotheque.bll.contexte.ContexteService;
import fr.eni.tp.filmotheque.bo.Membre;

@Controller
@RequestMapping("/membres")
@SessionAttributes({"loggedMembre"})
public class MembreController {

    private final ContexteService contexteService;

    public MembreController(ContexteService contexteService) {
        this.contexteService = contexteService;
    }

    @ModelAttribute("loggedMembre")
    public Membre loggedMembre() {
        return new Membre();
    }

    @PostMapping("/session")
    public String login(@ModelAttribute("loggedMembre") Membre loggedMembre,
                        @RequestParam(name = "email", required = false, defaultValue = "jtrillard@campus-eni.fr") String email) {
        Membre membre = contexteService.charger(email);
        if (membre != null) {
            loggedMembre.setId(membre.getId());
            loggedMembre.setNom(membre.getNom());
            loggedMembre.setPrenom(membre.getPrenom());
            loggedMembre.setPseudo(membre.getPseudo());
            loggedMembre.setAdmin(membre.isAdmin());
        } else {
            loggedMembre.setId(0);
            loggedMembre.setNom(null);
            loggedMembre.setPrenom(null);
            loggedMembre.setPseudo(null);
            loggedMembre.setAdmin(false);
        }
        return "redirect:/films";
    }

    @GetMapping("/logout")
    public String findSession(SessionStatus status) {
        status.setComplete();
        return "redirect:/films";
    }

    @GetMapping({"", "/session"})
    public String membres() {
        return "membres";
    }


}
