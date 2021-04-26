package com.daiwf.javalearndemos.gmssl;


import com.aliyun.gmsse.GMProvider;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.junit.Test;

import javax.net.ssl.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.security.*;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Enumeration;


/**
 * @version [版本号，2021/4/16 0016]
 * @文件名 GMTLSTest
 * @作者 daiwf
 * @创建时间 2021/4/16 0016 8:57
 * @版权 Copyright daiwf.
 * @描述 [国密TLS双向认证的测试代码]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class GMSSETest {

    @Test
    public void ClientTest() throws KeyStoreException, IOException, NoSuchAlgorithmException, CertificateException, UnrecoverableKeyException, KeyManagementException {
       /* String pfxfile = "src/test/resources/client.pfx";
        String pwd = "12345678";
        // 初始化 SSLSocketFactory
        GMProvider provider = new GMProvider();
        SSLContext sc = SSLContext.getInstance("TLS", provider);


        BouncyCastleProvider bc = new BouncyCastleProvider();
        KeyStore ks = KeyStore.getInstance("PKCS12", new BouncyCastleProvider());
        ks.load(new FileInputStream(pfxfile), pwd.toCharArray());

        CertificateFactory cf = CertificateFactory.getInstance("X.509", bc);
        FileInputStream is = new FileInputStream("src/test/resources/root.crt");
        X509Certificate cert = (X509Certificate) cf.generateCertificate(is);

        ks.setCertificateEntry("gmca", cert);
        Enumeration<String> aliases = ks.aliases();
        String alias = (String) aliases.nextElement();
        String alias1 = (String) aliases.nextElement();


        PrivateKey pk = (PrivateKey) ks.getKey(alias1, pwd.toCharArray());


        TrustManagerFactory tmf = TrustManagerFactory.getInstance("X509", provider);
        tmf.init(ks);

        KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
        kmf.init(ks, pwd.toCharArray());


        sc.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);


        SSLSocketFactory ssf = sc.getSocketFactory();

        URL serverUrl = new URL("https://139.196.50.80/");
        HttpsURLConnection conn = (HttpsURLConnection) serverUrl.openConnection();
        conn.setRequestMethod("GET");
        // 设置 SSLSocketFactory
        conn.setSSLSocketFactory(ssf);
        conn.connect();
        System.out.println("used cipher suite:");
        System.out.println(conn.getCipherSuite());*/

    }


}
