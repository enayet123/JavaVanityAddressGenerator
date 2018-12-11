import java.math.BigInteger;

/**
 * Created by Enayet Hussain on 16/11/2018.
 */
public class Base58CheckEncoding {
    private static final String ALPHABET = "123456789ABCDEFGHJKLMNPQRSTUVWXYZabcdefghijkmnopqrstuvwxyz";
    private static final BigInteger BIG0 = BigInteger.ZERO;
    private static final BigInteger BIG58 = BigInteger.valueOf(58);

    public static String convertToBase58(String hash) {
        return convertToBase58(hash, 16);
    }

    public static String convertToBase58(String hash, int base) {
        BigInteger x;
        if (base == 16 && hash.substring(0, 2).equals("0x")) {
            x = new BigInteger(hash.substring(2), 16);
        } else {
            x = new BigInteger(hash, base);
        }

        StringBuilder sb = new StringBuilder();
        while (x.compareTo(BIG0) > 0) {
            int r = x.mod(BIG58).intValue();
            sb.append(ALPHABET.charAt(r));
            x = x.divide(BIG58);
        }

        return sb.reverse().toString();
    }

}
