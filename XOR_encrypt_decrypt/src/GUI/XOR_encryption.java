package GUI;

import code.encryption.Algorithm;
import code.util.FileUtil;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class XOR_encryption {
    private JTextArea encryptFileValue;
    private JTextArea decryptStrValue;
    private JTextArea decryptFileValue;
    private JButton openFileButton;
    private JTextArea encryptStrValue;
    private JLabel xorEncryption;
    private JLabel xorDecryption;
    private JLabel encryptionInfoLabel;
    private JLabel encryptionFileLabel;
    private JLabel decryptionInfoLabel;
    private JLabel decryptionFileLabel;
    private JTextArea keyValue;
    private JLabel keyLabel;
    private JTextArea encryptStrResult;
    private JTextArea encryptFileResult;
    private JLabel encryptStrResultLabel;
    private JLabel encryptFileResultLabel;
    private JTextArea decryptStrResult;
    private JTextArea decryptFileResult;
    private JButton chooseFileButton2;
    private JButton openFileButton2;
    private JLabel decryptStrResultLabel;
    private JLabel decryptFileResultLabel;
    private JButton encryptStrButton;
    private JButton encryptFileButton;
    private JButton decryptStrButton;
    private JLabel encryptStrInfoLabel;
    private JLabel encryptFileInfoLabel;
    private JLabel decryptStrInfoLabel;
    private JLabel decryptFileInfoLabel;

    private JPanel panel;
    private JButton chooseFileButton;
    private JButton decryptFileButton;

    public static void main(String[] args) {
        JFrame frame = new JFrame("XOR_encryption");
        frame.setContentPane(new XOR_encryption().panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }



    public XOR_encryption() {
        //加密字符串
        encryptStrButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Algorithm algorithm = new Algorithm();
                String key = keyValue.getText();
                if(key.equals(""))
                    return;
                algorithm.setKey(key);

                String encodeValue = encryptStrValue.getText();
                String encodeResult = Algorithm.encodeStr(encodeValue);

                encryptStrResult.setText(encodeResult);
            }
        });

        //加密文件
        encryptFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Algorithm algorithm = new Algorithm();
                String key = keyValue.getText();
                if(key.equals(""))
                    return;
                algorithm.setKey(key);

                String path = encryptFileValue.getText();
                if(path.equals(""))
                    return;
                File inputFile = new File(path);
                String outputPath = FileUtil.generatePath(path);
                File outputFile = new File(outputPath);
                try {
                    Algorithm.encodeFile(inputFile,outputFile);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                encryptFileResult.setText(outputPath);
            }
        });

        //打开已加密文件
        openFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String resultPath = encryptFileResult.getText();
                if(resultPath.equals(""))
                    return;
                try {
                    FileUtil.openFile(resultPath);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        //解密字符串
        decryptStrButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Algorithm algorithm = new Algorithm();
                String key = keyValue.getText();
                if(key.equals(""))
                    return;
                algorithm.setKey(key);

                String decodeValue = decryptStrValue.getText();
                String decodeResult = Algorithm.decodeStr(decodeValue);

                decryptStrResult.setText(decodeResult);
            }
        });

        //解密文件
        decryptFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Algorithm algorithm = new Algorithm();
                String key = keyValue.getText();
                if(key.equals(""))
                    return;
                algorithm.setKey(key);

                String path = decryptFileValue.getText();
                if(path.equals(""))
                    return;

                File inputFile = new File(path);
                String outputPath = FileUtil.generatePath(path);
                File outputFile = new File(outputPath);
                try {
                    Algorithm.decodeFile(inputFile,outputFile);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                decryptFileResult.setText(outputPath);
            }
        });

        //打开已解密文件
        openFileButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String resultPath = decryptFileResult.getText();
                if(resultPath.equals(""))
                    return;
                try {
                    FileUtil.openFile(resultPath);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        //选择文件
        chooseFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                chooser.showDialog(new JLabel(),"选择");
                File file = chooser.getSelectedFile();

                if(file == null){
                    return;
                }

                encryptFileValue.setText(file.getAbsolutePath());
            }
        });

        //选择文件
        chooseFileButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                chooser.showDialog(new JLabel(),"选择");
                File file = chooser.getSelectedFile();

                if(file == null){
                    return;
                }

                encryptFileValue.setText(file.getAbsolutePath());
            }
        });
    }
}
