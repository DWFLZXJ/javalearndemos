package com.daiwf.javalearndemos.gmssl;


import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.epoint.jsse.provider.EpointJsseProvider;
import org.junit.Test;

import javax.net.SocketFactory;
import javax.net.ssl.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.InetSocketAddress;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.Security;


/**
 * @version [版本号，2021/4/16 0016]
 * @文件名 GMTLSTest
 * @作者 daiwf
 * @创建时间 2021/4/16 0016 8:57
 * @版权 Copyright daiwf.
 * @描述 [国密TLS单向向认证的测试代码]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class GMTLSSignleTest {

    @Test
    public void ClientTest()  {
        SocketFactory fact = null;
        SSLSocket socket = null;

        System.out.println("Usage: java client TLS1.2");

        try {
            String addr = "139.196.50.80";
            int port = 443;
            String uri = "/";

            Security.addProvider(new BouncyCastleProvider());
            Security.addProvider(new EpointJsseProvider());

            fact = createSocketFactory(null, null);
            socket = (SSLSocket) fact.createSocket();
            socket.setEnabledCipherSuites(new String[]{"ECDHE_SM2_WITH_SMS4_GCM_SM3"});
            socket.setTcpNoDelay(true);

            socket.connect(new InetSocketAddress(addr, port), 2000);
            socket.setTcpNoDelay(true);
            socket.startHandshake();

            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            String s = "GET " + uri + " HTTP/1.1\r\n";
            s += "Accept: */*\r\n";
            s += "User-Agent: Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 5.1; Trident/4.0)\r\n";
            s += "Host: " + addr + (port == 443 ? "" : ":" + port) + "\r\n";
            s += "Connection: Close\r\n";
            s += "\r\n";
            out.write(s.getBytes());
            out.flush();

            System.out.println(socket.getSession().getCipherSuite());

            byte[] buf = new byte[8192];
            int len = in.read(buf);
            if (len == -1) {
                System.out.println("eof");
                return;
            }
            System.out.println(new String(buf, 0, len));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (Exception e) {
            }
        }


    }

    public static SSLSocketFactory createSocketFactory(KeyStore kepair, char[] pwd) throws Exception {
        TrustAllManager[] trust = {new TrustAllManager()};

        KeyManager[] kms = null;
        if (kepair != null) {
            KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
            kmf.init(kepair, pwd);
            kms = kmf.getKeyManagers();
        }

        SSLContext ctx = SSLContext.getInstance("TLSv1.2", new EpointJsseProvider());
        SecureRandom secureRandom = new SecureRandom();
        ctx.init(kms, trust, secureRandom);

        ctx.getServerSessionContext().setSessionCacheSize(8192);
        ctx.getServerSessionContext().setSessionTimeout(3600);

        SSLSocketFactory factory = ctx.getSocketFactory();
        return factory;
    }


}
