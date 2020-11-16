package code.util;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileUtil {
    /**
     * 根据路径打开文件
     * @param path
     * @throws IOException
     */
    public static void openFile(String path) throws IOException {
            Desktop.getDesktop().open(new File(path));
    }

    /**
     * 将文件转换为字符串
     * @param path
     * @return
     */
    public static String getFileStr(String path){
        String fileStr = "";
        File file=new File(path);
        try {
            FileInputStream in=new FileInputStream(file);
            // size  为字串的长度
            int size=in.available();
            byte[] buffer=new byte[size];
            in.read(buffer);
            in.close();
            fileStr=new String(buffer,"utf-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileStr;
    }

    /**
     * 将字符串写入文件中
     * @param str
     * @param path
     * @throws IOException
     */
    public static void writeStrToFile(String str, String path) throws IOException {
        File file = new File(path);
        if(!file.exists()){
            file.createNewFile();
        }
        try {
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(str.getBytes());
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 生成文件路径
     * @param sourcePath
     * @return
     */
    public static String generatePath(String sourcePath){
        File file = new File(sourcePath);
        String parentPath = file.getParent();
        int ch = sourcePath.lastIndexOf(".");
        String suffix = sourcePath.substring(ch);

        String generatePath = parentPath + "\\" + (int)(Math.random()*1000) + suffix;
        return generatePath;
    }
}
