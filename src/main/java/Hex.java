import java.security.SecureRandom;

/**
 * Created by Enayet Hussain on 16/11/2018.
 */
public class Hex {

    private Hex() {}

    public static String generate(int length) {
        StringBuilder sb = new StringBuilder();
        SecureRandom rand = new SecureRandom();

        for (int x=0;x<length;x++)
            sb.append(Integer.toHexString((rand.nextInt(15) + 1)));

        return sb.toString();
    }
}
