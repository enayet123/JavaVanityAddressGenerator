import java.util.Base64;

/**
 * Created by Enayet Hussain on 18/11/2018.
 */
public class KeyPair {

    private byte[] privKey;
    private String pubKey;

    public KeyPair(byte[] privKey, String pubKey) {
        this.privKey = privKey;
        this.pubKey = pubKey;
    }

    public PrivateKey getPrivKey() {
        return new PrivateKey(privKey);
    }

    public PublicKey getPublicKey() {
        return new PublicKey(pubKey);
    }

    @Override
    public String toString() {
        return String.format(
                "--------------------------------------------------------------------------\n" +
                "Public Key:\n\t%s\nPrivate Key (Secret Key):\n\tHex: %s\n\tWIF: %s\n\tBase64: %s\n" +
                "--------------------------------------------------------------------------\n",
                getPublicKey(),
                getPrivKey().asHex(),
                getPrivKey().asWIF(),
                getPrivKey().asBase64()
        );
    }
}

class PrivateKey {

    private byte[] key;

    public PrivateKey(byte[] key) { this.key = key; }

    public String asHex() {
        return toString();
    }

    public String asBase64() {
        return Base64.getEncoder().encodeToString(key);
    }

    public String asWIF() {

        byte[] prependedBytes = Util.prependByte((byte) 0x80, key);
        byte[] checksum = Util.getChecksum(prependedBytes);
        byte[] joined = Util.joinArrays(prependedBytes, checksum);

        return Base58CheckEncoding.convertToBase58(Util.toHexString(joined));
    }

    @Override
    public String toString() {
        return Util.toHexString(key);
    }

}

class PublicKey {

    private String key;

    public PublicKey(String key) { this.key = key; }

    public String getPublicKey() { return key; }

    @Override
    public String toString() { return key; }
}