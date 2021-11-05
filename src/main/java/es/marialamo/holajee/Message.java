package es.marialamo.holajee;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class Message {

    private static final String HELLO_MESSAGE = "Jakarta EE rocks!!";

    public String get() {
        return HELLO_MESSAGE;
    }

}