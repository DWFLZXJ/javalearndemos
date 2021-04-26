package com.daiwf.javalearndemos.gmssl;


import com.aliyun.gmsse.GMProvider;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.epoint.jsse.provider.EpointJsseProvider;
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
 * @描述 [国密TLShttp单向认证请求代码]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class GMHTTPSingleRequestTest {

    @Test
    public void ClientTest() throws KeyStoreException, IOException, NoSuchAlgorithmException, CertificateException, UnrecoverableKeyException, KeyManagementException {
        String pfxfile = "src/test/resources/client.pfx";
        String pwd = "12345678";
        // 初始化 SSLSocketFactory
        Security.addProvider(new BouncyCastleProvider());
        Security.addProvider(new EpointJsseProvider());
        SSLContext sslContext = SSLContext.getInstance("TLSv1.2", new EpointJsseProvider());
        java.security.SecureRandom secureRandom = new java.security.SecureRandom();
        TrustAllManager[] trust = { new TrustAllManager() };
        sslContext.init(null, trust, secureRandom);
        sslContext.getServerSessionContext().setSessionCacheSize(8192);
        sslContext.getServerSessionContext().setSessionTimeout(3600);
        SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
        URL serverUrl = new URL("https://139.196.50.80/");
        HttpsURLConnection conn = (HttpsURLConnection) serverUrl.openConnection();
        conn.setRequestMethod("GET");
        // 设置 SSLSocketFactory
        conn.setSSLSocketFactory(sslSocketFactory);
        conn.connect();
        System.out.println("used cipher suite:");
        System.out.println(conn.getCipherSuite());

    }






}
