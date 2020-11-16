package code.encryption;

import code.util.FileUtil;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Base64;

/**
 * 加密解密算法
 */
public class Algorithm {
    private static String key;//密钥

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    /**
     * 加密字符串
     * @param strOld
     * @return
     */
    public static String encodeStr(String strOld){
        int keyIndex = 0;
        byte[] keyBytes = key.getBytes();
        byte[] oldBytes = strOld.getBytes();
        for (int i = 0; i < oldBytes.length; i++){
            oldBytes[i] = (byte) (oldBytes[i]^keyBytes[i%keyBytes.length]);
        }

        byte[] result = Base64.getEncoder().encode(oldBytes);

        return new String(result);
    }

    /**
     * 解密字符串
     * @param strOld
     * @return
     */
    public static String decodeStr(String strOld){
        int keyIndex = 0;
        byte[] keyBytes = key.getBytes();
        byte[] oldBytes = strOld.getBytes();

        oldBytes = Base64.getDecoder().decode(oldBytes);

        for (int i = 0; i < oldBytes.length; i++){
            oldBytes[i] = (byte) (oldBytes[i]^keyBytes[i%keyBytes.length]);

        }
        return new String(oldBytes);
    }

    //加密任何文件
    public static void encodeFile(File inFile, File outFile) throws Exception {
        InputStream in = null;
        OutputStream out = null;
        byte[] keyBytes = key.getBytes();

        try {
            // 文件输入流
            in = new FileInputStream(inFile);
            // 结果输出流, 异或运算时, 字节是一个一个读取和写入, 这里必须使用缓冲流包装,
            // 等缓冲到一定数量的字节（10240字节）后再写入磁盘（否则写磁盘次数太多, 速度会非常慢）
            out = new BufferedOutputStream(new FileOutputStream(outFile), 10240);

            int b = -1;
            long i = 0;

            // 每次循环读取文件的一个字节, 使用密钥字节数组循环加密或解密
            while ((b = in.read()) != -1) {
                // 数据与密钥异或, 再与循环变量的低8位异或（增加复杂度）
                b = (b ^ keyBytes[(int) (i % keyBytes.length)] ^ (int) (i & 0xFF));
                // 写入一个加密/解密后的字节
                out.write(b);
                // 循环变量递增
                i++;
            }
            out.flush();

        } finally {
            close(in);
            close(out);
        }
    }

    //解密任何文件
    public static void decodeFile(File inFile, File outFile) throws Exception {
        InputStream in = null;
        OutputStream out = null;
        byte[] keyBytes = key.getBytes();

        try {
            // 文件输入流
            in = new FileInputStream(inFile);
            // 结果输出流, 异或运算时, 字节是一个一个读取和写入, 这里必须使用缓冲流包装,
            // 等缓冲到一定数量的字节（10240字节）后再写入磁盘（否则写磁盘次数太多, 速度会非常慢）
            out = new BufferedOutputStream(new FileOutputStream(outFile), 10240);

            int b = -1;
            long i = 0;

            // 每次循环读取文件的一个字节, 使用密钥字节数组循环加密或解密
            while ((b = in.read()) != -1) {
                // 数据与密钥异或, 再与循环变量的低8位异或（增加复杂度）
                b = (b ^ keyBytes[(int) (i % keyBytes.length)] ^ (int) (i & 0xFF));
                // 写入一个加密/解密后的字节
                out.write(b);
                // 循环变量递增
                i++;
            }
            out.flush();

        } finally {
            close(in);
            close(out);
        }
    }

    /**
     * 关闭流
     * @param c
     */
    private static void close(Closeable c) {
        if (c != null) {
            try {
                c.close();
            } catch (IOException e) {
                // nothing
            }
        }
    }

}
