package com.daiwf.javalearndemos.reflect;

import java.io.*;

public class MyClassLoader extends ClassLoader
{
    private String path;
    private String classLoaderName;

    public MyClassLoader(String path, String classLoaderName) {
        this.path = path;
        this.classLoaderName = classLoaderName;
    }

    //用于寻找类文件
    @Override public Class findClass(String name) throws ClassNotFoundException {
        byte[] b = loadClassData(name);
        return defineClass(name, b, 0, b.length);

    }

    //用于加载类文件
    private byte[] loadClassData(String name)  {
        name = path + name + ".class";
        InputStream in = null;
        ByteArrayOutputStream out = null;
        try {
            File file;
            in = new FileInputStream(new File(name));
            out = new ByteArrayOutputStream();
            int i = 0;
            while ((i = in.read()) != -1) {
                out.write(i);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                out.close();
                in.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        return out.toByteArray();
    }
}
