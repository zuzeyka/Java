package step.learning.ioc.service.hash;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA256 implements HashService{

    @Override
    public String hash(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            StringBuilder sb = new StringBuilder();
            for (int b: digest.digest(input.getBytes(StandardCharsets.UTF_8))){
                sb.append(String.format("%02x", b & 0xFF));
            }
            return  sb.toString();
        }
        catch (NoSuchAlgorithmException ex){
            throw new RuntimeException(ex);
        }
    }
}

