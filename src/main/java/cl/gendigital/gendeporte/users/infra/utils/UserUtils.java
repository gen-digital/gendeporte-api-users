package cl.gendigital.gendeporte.users.infra.utils;

import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadLocalRandom;

@Component
@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class UserUtils {

    public String validationCode(){
        String banco = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        String cadena = "";
        for (int x = 0; x < 10; x++) {
            int indiceAleatorio = ThreadLocalRandom.current().nextInt(5,banco.length());
            char caracterAleatorio = banco.charAt(indiceAleatorio);
            cadena += caracterAleatorio;
        }
        return cadena;
    }
}
