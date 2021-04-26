package com.daiwf.javalearndemos.gmssl;

import org.bouncycastle.crypto.params.ECPublicKeyParameters;
import org.bouncycastle.jcajce.provider.asymmetric.x509.CertificateFactory;
import org.bouncycastle.jce.interfaces.ECPublicKey;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.math.ec.ECPoint;
import org.bouncycastle.pqc.math.linearalgebra.ByteUtils;
import org.junit.Test;
import sun.misc.BASE64Decoder;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.*;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * @description:
 * @author: daiwf
 * @time: 2021/4/19 0019
 */
public class KeyTest {

    @Test
    public void keytest() throws GeneralSecurityException, IOException {
        Path userP12Path = Paths.get("src/test/resources/client.pfx");

        PrivateKey prvKey = ReadPrvKey(userP12Path,  "12345678");

        System.out.println(prvKey.getAlgorithm());
        System.out.println(prvKey.getEncoded());
    }

    @Test
    public void verify() throws GeneralSecurityException, IOException {
        Path userP12Path = Paths.get("src/test/resources/client.pfx");
        String sign="MEYCIQDXg/5FZzC6YL2vj4bfekNLHjWxzzTrJTgpUzy6/U02AwIhANOAaolN8rQlKV3vNsf+XuqdpHZf9f0I7Dvm4ENGyi0=";

        Signature sg = Signature.getInstance( "1.2.156.10197.1.501",
                new BouncyCastleProvider());
        Certificate signCert =ReadUserCert(userP12Path,  "12345678");
        X509Certificate  x509Certificate = (X509Certificate)signCert;
        ECPublicKey pubKey = (ECPublicKey)  x509Certificate.getPublicKey();

        System.out.println("Pub Point Hex:"
                + ByteUtils.toHexString(pubKey.getQ().getEncoded(false)).toUpperCase());
        sg.initVerify(signCert);
        sg.update(new BASE64Decoder().decodeBuffer("ci2A7ZskwicmJmbcgl0ts78dRwcRA5BHoZevDQCw64s=") );

        if(sg.verify(new BASE64Decoder().decodeBuffer(sign)))
        {
            System.out.println("验签成功");
        }else {
            System.out.println("验签失败");
        }

    }


    @Test
    public void signandverify() throws GeneralSecurityException, IOException {
        Path userP12Path = Paths.get("src/test/resources/client.pfx");
        Signature sg = Signature.getInstance("SM3WithSM2", new BouncyCastleProvider());

        //获取私钥,可以自己实现
        PrivateKey prvKey = ReadPrvKey(userP12Path,  "12345678");

        sg.initSign(prvKey);
        sg.update("11222".getBytes());
        //用bc包签名，获取签名值
        byte[] sigVal = sg.sign();

        Signature sgv = Signature.getInstance( "1.2.156.10197.1.501",
                new BouncyCastleProvider());
        Certificate signCert =ReadUserCert(userP12Path,  "12345678");
        sgv.initVerify(signCert);
        sgv.update("11222".getBytes());
        if(sgv.verify(sigVal)){
            System.out.println("验证成功");
        }else {
            System.out.println("验证失败");
        }

    }

    public static PrivateKey ReadPrvKey(Path userP12, String pwd)
            throws GeneralSecurityException, IOException {
        KeyStore ks = KeyStore.getInstance("PKCS12", new BouncyCastleProvider());
        try (InputStream rootKsIn = Files.newInputStream(userP12)) {
            ks.load(rootKsIn, pwd.toCharArray());
            String alias = ks.aliases().nextElement();
            return (PrivateKey) ks.getKey(alias, pwd.toCharArray());
        }
    }
    public static Certificate ReadUserCert(Path userP12, String pwd)
            throws GeneralSecurityException, IOException {
        return ReadCertChain(userP12, pwd)[0];
    }
    public static Certificate[] ReadCertChain(Path userP12, String pwd)
            throws GeneralSecurityException, IOException {
        KeyStore ks = KeyStore.getInstance("PKCS12", new BouncyCastleProvider());
        try (InputStream rootKsIn = Files.newInputStream(userP12)) {
            ks.load(rootKsIn, pwd.toCharArray());
            String alias = ks.aliases().nextElement();
            return ks.getCertificateChain(alias);
        }
    }

}
