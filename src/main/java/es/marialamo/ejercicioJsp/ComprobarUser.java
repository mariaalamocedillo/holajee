package es.marialamo.ejercicioJsp;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

public class ComprobarUser {

    public boolean check(String user, String clave) {
        //se comparan los datos con los usuarios existentes
        Map<String, String > users = new HashMap<String, String>();
        users.put("ana", "ana1");
        users.put("jon", "jon1");
        users.put("mel", "mel1");
        //validamos que existe en el array de usuarios y contraseñas
        if (users.containsKey(user) && Objects.equals(users.get(user), clave)){
            return true;
        } else {
            return false;
        }
    }

    public String checkCookies(Cookie[] cookies, String username){
        if (cookies!=null && cookies.length>0) {
            for (Cookie cookie : cookies) {
                String nombreCookie = cookie.getName();

                if (nombreCookie.equals(username)) {
                    String contenidoCookie = cookie.getValue();
                    return contenidoCookie;
                } else {//si no tiene cookie de usuario
                    return "1-PT";
                }
            }
        }
        //si no hay ninguna cookie en el sistema
        return "1-PT";
    }

}