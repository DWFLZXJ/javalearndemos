package com.daiwf.javalearndemos.gmssl;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.Security;
import java.security.cert.X509Certificate;
import java.util.Enumeration;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.epoint.jsse.provider.EpointJsseProvider;
import org.epoint.jsse.provider.ProvSSLSessionContext;



public class LocalTest
{
    public static void main(String[] args) {
        try {
            new LocalTest().SecureTransport("&$GTXD$21042902$V2.E$3$2021-05-07$14:11:28$0$2$0.00$0.00$5.00$0.00$0$4.00$2.50$0.0$10.00$0$1$1$1$1$1$0$0$0$0$0$0$0$0$0$0$0$1$0$0$0$0$0$0$4.00$0.0$0$0$0.00$0.0$0$0$0.00$0.0$0$0$0.00$0.0$0$0$0.00$0.0$0$0$0.00$0.0$0$0$0.00$0.0$0$0$0.00$0.0$0$0$0.00$0.0$0$0$0.00$0.0$0$0$0.00$0.0$0$0$0.00$0.0$0$0$0.00$0.0$0$0$0.00$0.0$0$0$0.00$0.0$0$0$0.00$0.0$0$0$$$0$!");
            
            
        }
        catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public String SecureTransport(String message) throws Exception {

        String json ="{\"content\":\""+message+"\"}";
        String ur = "https://218.4.136.123:9011/IOTDeviceAPICenter/rest/gmssltransrest/trans";
        String pfxfile = "D:\\KeepLearn\\daiwfcode\\javaleardemos\\javalearndemos\\src\\test\\resources\\signbidder.pfx";
        String pwd = "12345678";
        // 初始化 SSLSocketFactory
        Security.addProvider(new BouncyCastleProvider());
        Security.addProvider(new EpointJsseProvider());
        KeyStore keyStore = KeyStore.getInstance("PKCS12", new BouncyCastleProvider());
        keyStore.load(new FileInputStream(pfxfile), pwd.toCharArray());
        SSLSocketFactory sslSocketFactory = createSocketFactory(keyStore, pwd.toCharArray());
        URL serverUrl = new URL(ur);
        StringBuilder bodyBuilder = new StringBuilder();
        InputStreamReader bis = null;
        InputStream inputStream = null;
        OutputStreamWriter printWriter = null;
        try {

            HttpsURLConnection conn = (HttpsURLConnection) serverUrl.openConnection();
            // 设置 SSLSocketFactory
            conn.setSSLSocketFactory(sslSocketFactory);
            conn.setRequestMethod("POST");// 部署了动态应用之后给改成POST
            conn.setDoOutput(true);// 由于现在服务端没有装应用，只是静态页。所以这边先false
            conn.setDoInput(true);
            conn.setConnectTimeout(30000);
            conn.setReadTimeout(30000);
            conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");

            conn.connect();
            // 开始获取数据
            // 这边注释掉，主要因为目前只有静态的。等有动态的再自定义输入。
            printWriter = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
            printWriter.write(json);
            printWriter.flush();// flush输出流的缓冲

            if (200 == conn.getResponseCode()) {
                inputStream = conn.getInputStream();
            }
            else {
                inputStream = conn.getErrorStream();
            }
            bis = new InputStreamReader(inputStream, "UTF-8");
            int len;
            char[] arr = new char[1024];

            while ((len = bis.read(arr)) != -1) {
                bodyBuilder.append(new String(arr, 0, len));
            }
        }
        catch (Exception e) {
            return "异常：" + e.getMessage();
        }
        finally {
            if (bis != null) {
                bis.close();
            }
            if (printWriter != null) {
                printWriter.close();
            }
        }
        // 注意nginx是不允许静态资源用post的。会返回405 Not Allowed
        System.out.println("服务器nginx返回数据：" + bodyBuilder.toString());
        return bodyBuilder.toString();
    }

    public SSLSocketFactory createSocketFactory(KeyStore kepair, char[] pwd) throws Exception {

        KeyManager[] kms = null;
        String alias = "";
        if (kepair != null) {
            KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
            Enumeration<String> aliases = kepair.aliases();
            alias = (String) aliases.nextElement();
            kmf.init(kepair, pwd);
            kms = kmf.getKeyManagers();
        }
        TrustAllManager[] trust = {new TrustAllManager() };
        SSLContext ctx = SSLContext.getInstance("TLSv1.2", new EpointJsseProvider());
        SecureRandom secureRandom = new SecureRandom();

        ctx.init(kms, trust, secureRandom);
        // 为了解决alias取不到的问题。
        ProvSSLSessionContext provSSLSessionContext = (ProvSSLSessionContext) ctx.getClientSessionContext();
        provSSLSessionContext.addSession("alias", alias);
        ctx.getServerSessionContext().setSessionCacheSize(8192);
        ctx.getServerSessionContext().setSessionTimeout(3600);
        SSLSocketFactory factory = ctx.getSocketFactory();
        return factory;
    }

    private class TrustAllManager implements X509TrustManager
    {
        private X509Certificate[] issuers;
        private String alias;

        public TrustAllManager() {
            this.issuers = new X509Certificate[0];
        }

        public X509Certificate[] getAcceptedIssuers() {
            return issuers;
        }

        public void checkClientTrusted(X509Certificate[] chain, String authType) {
        }

        public void checkServerTrusted(X509Certificate[] chain, String authType) {
        }
    }
}
