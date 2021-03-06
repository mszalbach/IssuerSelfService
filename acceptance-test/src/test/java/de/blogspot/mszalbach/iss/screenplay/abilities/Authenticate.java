package de.blogspot.mszalbach.iss.screenplay.abilities;

import net.serenitybdd.screenplay.Ability;
import net.serenitybdd.screenplay.Actor;

public class Authenticate
    implements Ability {

    private String username;
    private String password;


    private Authenticate(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public static Authenticate with(
        String username,
        String password) {
        return new Authenticate(username, password);
    }

    public static Authenticate as(Actor actor) {

        // complain if someone's asking the impossible
        if (actor.abilityTo(Authenticate.class) == null) {
            throw new IllegalStateException(actor.getName() + " not able to authenticate.");
        }

        return actor.abilityTo(Authenticate.class);
    }

    public String username() {
        return this.username;
    }


    public String password() {
        return this.password;
    }

}

