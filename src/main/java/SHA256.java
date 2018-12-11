import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Enayet Hussain on 17/11/2018.
 */
public class SHA256 {

    private SHA256() {}

    public static byte[] hash(byte[] pubKey) {
        try {
            MessageDigest d = MessageDigest.getInstance("SHA-256");
            return d.digest(pubKey);
        } catch (NoSuchAlgorithmException e) { return null; }
    }
}
