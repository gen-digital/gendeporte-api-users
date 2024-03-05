package cl.gendigital.gendeporte.users.infra.utils;

import lombok.NoArgsConstructor;
import java.util.concurrent.ThreadLocalRandom;


@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class UserUtils {

    public static String validationCode(){
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
