import org.web3j.crypto.ECKeyPair;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import java.util.concurrent.*;

/**
 * Created by Enayet Hussain on 16/11/2018.
 */
public class Generator implements Callable<KeyPair> {

    private String searchFor;

    public Generator(String searchFor) {
        this.searchFor = "1" + searchFor;
    }
    public Generator() { this.searchFor = "1"; }

    public KeyPair call() throws Exception {
        if (searchFor.equals("1")) return generate(Network.MAIN_NETWORK);
        KeyPair kp = null;
        do {
            kp = generate(Network.MAIN_NETWORK);
        } while (!kp.getPublicKey().toString().startsWith(searchFor));
        return kp;
    }

    public static KeyPair generate(Network type) {
        String hex = Hex.generate(64);
        ECKeyPair ecP = ECDSA.privateKeyCreate(hex);

        byte[] compressed = Util.toBytes(Util.compressPubKey(ecP.getPublicKey()));
        byte[] sha256 = SHA256.hash(compressed);
        byte[] ripemd160 = RIPEMD160.digest(sha256);
        byte[] versionedRipe = addVersionByte(ripemd160, type);
        byte[] concat = Util.joinArrays(versionedRipe, Util.getChecksum(versionedRipe));
        String publicAddress = "1" + Base58CheckEncoding.convertToBase58(Util.toHexString(concat));

        return new KeyPair(Util.toBytes(hex), publicAddress);
    }


    private static byte[] addVersionByte(byte[] hash, Network type) {
        switch (type) {
            case MAIN_NETWORK:
                return Util.prependByte((byte) 0x00, hash);
            case TEST_NETWORK:
                throw new NotImplementedException();
                //return Util.prependByte((byte) 0x6f, hash);
            case NAMECOIN_NETWORK:
                throw new NotImplementedException();
                //return Util.prependByte((byte) 0x34, hash);
            default:
                return null;
        }
    }

}

enum Network {
    MAIN_NETWORK,
    TEST_NETWORK,
    NAMECOIN_NETWORK;
}
