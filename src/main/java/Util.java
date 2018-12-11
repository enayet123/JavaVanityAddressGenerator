import javax.xml.bind.DatatypeConverter;
import java.math.BigInteger;
import java.util.Arrays;

/**
 * Created by Enayet Hussain on 18/11/2018.
 */
public class Util {

    private Util() {}

    public static byte[] toBytes(String hex) {
        return DatatypeConverter.parseHexBinary(hex);
    }

    public static String toHexString(byte[] bytes) {
        return DatatypeConverter.printHexBinary(bytes);
    }

    public static byte[] joinArrays(byte[] first, byte[] second) {
        byte[] joined = new byte[first.length + second.length];
        System.arraycopy(first, 0, joined, 0, first.length);
        System.arraycopy(second, 0, joined, first.length, second.length);
        return joined;
    }

    public static byte[] getChecksum(byte[] bytes) {
        return Arrays.copyOfRange(SHA256.hash(SHA256.hash(bytes)), 0, 4);
    }

    public static byte[] prependByte(byte singleByte, byte[] bytes) {
        byte[] joined = new byte[bytes.length + 1];
        joined[0] = singleByte;
        System.arraycopy(bytes, 0, joined, 1, bytes.length);
        return joined;
    }

    public static String compressPubKey(BigInteger pubKey) {
        String pubKeyYPrefix = pubKey.testBit(0) ? "03" : "02";
        String pubKeyHex = pubKey.toString(16);
        String pubKeyX = pubKeyHex.substring(0, 64);
        return pubKeyYPrefix + pubKeyX;
    }

}
