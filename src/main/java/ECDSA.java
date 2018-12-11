import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.Sign;

import java.math.BigInteger;

public class ECDSA {

    private ECDSA() {}

    public static ECKeyPair privateKeyCreate(String hash) {
        BigInteger privKey = new BigInteger(hash, 16);
        BigInteger pubKey =  Sign.publicKeyFromPrivate(privKey);

        return new ECKeyPair(privKey, pubKey);
    }

}
