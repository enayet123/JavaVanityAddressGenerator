import org.bouncycastle.crypto.digests.RIPEMD160Digest;

/**
 * Created by Enayet Hussain on 17/11/2018.
 */
public class RIPEMD160 {

    private RIPEMD160() {}

    public static byte[] digest(byte[] hash) {
        RIPEMD160Digest digest = new RIPEMD160Digest();
        digest.update(hash, 0, hash.length);
        byte[] result = new byte[digest.getDigestSize()];
        digest.doFinal(result, 0);

        return result;
    }
}
