package ru.babobka.nodeserials;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Random;

/**
 * Created by dolgopolov.a on 29.10.15.
 */
public class RSA implements Serializable {

    private static final long serialVersionUID = 1L;
    private final PrivateKey privateKey;
    private final PublicKey publicKey;

    public RSA(int bits) {
        if (bits >= 8) {
            BigInteger p = BigInteger.probablePrime(bits, new Random());
            BigInteger q = BigInteger.probablePrime(bits, new Random());
            BigInteger n = p.multiply(q);
            BigInteger f = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
            BigInteger e = BigInteger.probablePrime(f.bitLength() - 1, new Random());
            while(!e.gcd(f).equals(BigInteger.ONE))
            {
            	e = BigInteger.probablePrime(f.bitLength() - 1, new Random());
            }
            BigInteger d = e.modInverse(f);
            privateKey = new PrivateKey(n, d);
            publicKey = new PublicKey(n, e);
        } else throw new IllegalArgumentException("Key can not be shorter than 8 bits");
    }

    public RSA(PrivateKey privateKey, PublicKey publicKey) {
        this.privateKey = privateKey;
        this.publicKey = publicKey;
    }

    public BigInteger encrypt(BigInteger m) {
        if (publicKey != null)
            return m.modPow(publicKey.getE(), publicKey.getN());
        return null;
    }

    public BigInteger decrypt(BigInteger c) {
        if (privateKey != null)
            return c.modPow(privateKey.getD(), privateKey.getN());
        return null;
    }


    public PrivateKey getPrivateKey() {
        return privateKey;
    }

    public PublicKey getPublicKey() {
        return publicKey;
    }

}
