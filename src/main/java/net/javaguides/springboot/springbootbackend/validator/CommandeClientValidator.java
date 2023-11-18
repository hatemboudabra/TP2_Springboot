package net.javaguides.springboot.springbootbackend.validator;

import net.javaguides.springboot.springbootbackend.Request.CommandeClientRequest;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class CommandeClientValidator {
    public static List<String> validate(CommandeClientRequest commandeClientRequest) {
        List<String> errors = new ArrayList<>();
        if (commandeClientRequest == null) {
            errors.add("Veuillez renseigner le code de la commande");
            errors.add("Veuillez renseigner la date de la commande");
            errors.add("Veuillez renseigner l'etat de la commande");
            errors.add("Veuillez renseigner le client");
            return errors;
        }

        if (!StringUtils.hasLength(commandeClientRequest.getCode())) {
            errors.add("Veuillez renseigner le code de la commande");
        }
        if (commandeClientRequest.getDateCommande() == null) {
            errors.add("Veuillez renseigner la date de la commande");
        }
        if (!StringUtils.hasLength(commandeClientRequest.getEtatCommande().toString())) {
            errors.add("Veuillez renseigner l'etat de la commande");
        }
        if (commandeClientRequest.getClient() == null || commandeClientRequest.getClient().getId() == null) {
            errors.add("Veuillez renseigner le client");
        }

        return errors;
    }
}
