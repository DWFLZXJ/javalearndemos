package com.daiwf.javalearndemos.gmssl;


import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.epoint.jsse.provider.EpointJsseProvider;
import org.epoint.jsse.provider.ProvSSLSessionContext;
import org.junit.Test;

import javax.net.ssl.*;
import java.io.*;
import java.net.URL;
import java.security.*;

import java.util.Enumeration;





public class GMHTTPClientVerifyRequestTest {

    @Test
    public void ClientTest() throws Exception {
        String pfxfile = "src/test/resources/signbidder.pfx";
        String pwd = "12345678";
        // 初始化 SSLSocketFactory
        Security.addProvider(new BouncyCastleProvider());
        Security.addProvider(new EpointJsseProvider());
        KeyStore keyStore = KeyStore.getInstance("PKCS12", new BouncyCastleProvider());
        keyStore.load(new FileInputStream(pfxfile), pwd.toCharArray());
        SSLSocketFactory sslSocketFactory = createSocketFactory(keyStore, pwd.toCharArray());
        URL serverUrl = new URL("https://192.168.219.51/EpQzFileServer/gm/v1/test");

        StringBuilder bodyBuilder = new StringBuilder();
        InputStreamReader bis = null;
        InputStream inputStream = null;
        OutputStreamWriter printWriter = null;
        try {

            HttpsURLConnection conn = (HttpsURLConnection) serverUrl.openConnection();

            // 设置 SSLSocketFactory
            conn.setSSLSocketFactory(sslSocketFactory);
            // conn.setRequestMethod("GET");
            conn.setRequestMethod("POST");// 提交模式
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");

            conn.connect();
            String requestxml = "{\n" +
                    "    \"port\":\"2000\",\n" +
                    "    \"content\":\"hellodaiwf\"\n" +
                    "}";

            printWriter = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
            printWriter.write(requestxml);
            printWriter.flush();// flush输出流的缓冲

            // 开始获取数据

            if (200 == conn.getResponseCode()) {
                inputStream = conn.getInputStream();
            } else {
                inputStream = conn.getErrorStream();
            }
            bis = new InputStreamReader(inputStream, "UTF-8");
            int len;
            char[] arr = new char[1024];

            while ((len = bis.read(arr)) != -1) {
                bodyBuilder.append(new String(arr, 0, len));
            }
        } catch (Exception e) {
            System.out.println("异常"+e.getMessage());
        } finally {
            if (bis != null) {
                bis.close();
            }
            if (printWriter != null) {
                printWriter.close();
            }

        }

        //注意nginx是不允许静态资源用post的。会返回405 Not Allowed
        System.out.println("返回数据：" + bodyBuilder.toString());


    }

    public static SSLSocketFactory createSocketFactory(KeyStore kepair, char[] pwd) throws Exception {

        KeyManager[] kms = null;
        String alias = "";
        if (kepair != null) {
            KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
            Enumeration<String> aliases = kepair.aliases();
            alias = (String) aliases.nextElement();
            kmf.init(kepair, pwd);

            kms = kmf.getKeyManagers();
        }
        TrustAllManager[] trust = {new TrustAllManager()};
        SSLContext ctx = SSLContext.getInstance("TLSv1.2", new EpointJsseProvider());
        SecureRandom secureRandom = new SecureRandom();

        ctx.init(kms, trust, secureRandom);
        //为了解决alias取不到的问题。
        ProvSSLSessionContext provSSLSessionContext = (ProvSSLSessionContext) ctx.getClientSessionContext();
        provSSLSessionContext.addSession("alias", alias);
        ctx.getServerSessionContext().setSessionCacheSize(8192);
        ctx.getServerSessionContext().setSessionTimeout(3600);
        SSLSocketFactory factory = ctx.getSocketFactory();
        return factory;
    }


}
