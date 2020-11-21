package GUI;

import code.DH;
import code.ELGamal;
import code.RSA;
import code.Util;
import com.sun.deploy.util.StringUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.VetoableChangeListener;

public class public_key_algorithm {
    public static void main(String[] args) {
        JFrame frame = new JFrame("public_key_algorithm");
        frame.setContentPane(new public_key_algorithm().panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private JPanel panel;
    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;
    private JButton DHButton;
    private JButton ELGamalButton;
    private JButton RSAButton;
    private JButton 生成素数和原根Button;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    private JButton 生成AliceButton;
    private JButton 生成AliceYButton;
    private JLabel label5;
    private JLabel label6;
    private JButton 生成BobButton;
    private JButton 生成BobYButton;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField6;
    private JTextField textField7;
    private JLabel label7;
    private JTextField textField8;
    private JLabel label8;
    private JButton 计算AliceKeyButton;
    private JButton 计算BobKeyButton;
    private JButton 生成素数和原根Button1;
    private JTextField textField9;
    private JTextField textField10;
    private JTextField textField11;
    private JButton 选择私钥Button;
    private JTextField textField12;
    private JButton 根据私钥计算公钥Button;
    private JTextField textField13;
    private JButton 加密明文Button;
    private JTextField textField14;
    private JTextField textField15;
    private JButton 解密密文Button;
    private JTextField textField16;
    private JTextField textField17;
    private JButton 生成pButton;
    private JTextField textField18;
    private JButton 生成qButton;
    private JTextField textField19;
    private JButton 计算nButton;
    private JTextField textField20;
    private JButton 计算f_nButton;
    private JTextField textField21;
    private JButton 生成公钥eButton;
    private JTextField textField22;
    private JButton 生成密钥dButton;
    private JTextField textField23;
    private JButton 加密明文Button1;
    private JTextField textField24;
    private JButton 解密密文Button1;
    private JTextField textField25;
    private DH dh = new DH();
    private ELGamal elGamal = new ELGamal();
    private RSA rsa = new RSA();

    public public_key_algorithm() {
        DHButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel1.setVisible(true);
                panel2.setVisible(false);
                panel3.setVisible(false);
            }
        });
        ELGamalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel1.setVisible(false);
                panel2.setVisible(true);
                panel3.setVisible(false);
            }
        });
        RSAButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel1.setVisible(false);
                panel2.setVisible(false);
                panel3.setVisible(true);
            }
        });
        生成素数和原根Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dh.setP();
                dh.setG();
                textField1.setText(Integer.toString(dh.getP()));
                textField2.setText(Integer.toString(dh.getG()));
            }
        });
        生成AliceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dh.setAlice();
                textField3.setText(Integer.toString(dh.getAlice()));
            }
        });
        生成AliceYButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dh.setAliceY();
                textField4.setText(Integer.toString(dh.getAliceY()));
            }
        });
        生成BobButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dh.setBob();
                textField5.setText(Integer.toString(dh.getBob()));
            }
        });
        生成BobYButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dh.setBobY();
                textField6.setText(Integer.toString(dh.getBobY()));
            }
        });
        计算AliceKeyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dh.setAliceKey();
                textField7.setText(Integer.toString(dh.getAliceKey()));
            }
        });
        计算BobKeyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dh.setBobKey();
                textField8.setText(Integer.toString(dh.getBobKey()));
            }
        });
        生成素数和原根Button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                elGamal.setP();
                elGamal.setA();
                textField9.setText(Integer.toString(elGamal.getP()));
                textField10.setText(Integer.toString(elGamal.getA()));
            }
        });
        选择私钥Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                elGamal.setD();
                textField11.setText(Integer.toString(elGamal.getD()));
            }
        });
        根据私钥计算公钥Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                elGamal.setY();
                textField12.setText(Integer.toString(elGamal.getY()));
            }
        });
        加密明文Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(textField13.getText().equals(""))
                    return;
                String M = textField13.getText();
                elGamal.setM(Integer.parseInt(M));
                elGamal.setK();
                elGamal.setU();
                elGamal.setC1();
                elGamal.setC2();
                elGamal.setV();
                textField14.setText(Integer.toString(elGamal.getC1()));
                textField15.setText(Integer.toString(elGamal.getC2()));
            }
        });
        解密密文Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                long V_1 = Util.modInverseShow(elGamal.getV(),elGamal.getP());
                long result = (elGamal.getC2() * V_1) % elGamal.getP();
                textField16.setText(Long.toString(result));
            }
        });
        生成pButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rsa.setP();
                textField17.setText(Integer.toString(rsa.getP()));
            }
        });
        生成qButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rsa.setQ();
                textField18.setText(Integer.toString(rsa.getQ()));
            }
        });
        计算nButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(textField17.getText().equals("") || textField18.getText().equals(""))
                    return;
                rsa.setN();
                textField19.setText(Integer.toString(rsa.getN()));
            }
        });
        计算f_nButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(textField17.getText().equals("") || textField18.getText().equals(""))
                    return;
                rsa.setF_n();
                textField20.setText(Integer.toString(rsa.getF_n()));
            }
        });

        生成公钥eButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(textField20.getText().equals(""))
                    return;
                rsa.setE();
                textField21.setText(Integer.toString(rsa.getE()));
            }
        });
        生成密钥dButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(textField20.getText().equals("") || textField21.getText().equals(""))
                    return;
                rsa.setD();
                textField22.setText(Long.toString(rsa.getD()));
            }
        });
        加密明文Button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(textField17.getText().equals("") || textField18.getText().equals("") || textField21.getText().equals("") || textField23.getText().equals("")){
                    return;
                }
                if(textField19.getText().equals("")) {
                    rsa.setN();
                    textField19.setText(Integer.toString(rsa.getN()));
                }
                if(textField20.getText().equals("")) {
                    rsa.setF_n();
                    textField20.setText(Integer.toString(rsa.getF_n()));
                }
                if(textField22.getText().equals("")) {
                    rsa.setD();
                    textField22.setText(Long.toString(rsa.getD()));
                }

                String M = textField23.getText();
                rsa.setM(Long.parseLong(M));
                rsa.setC();
                textField24.setText(Long.toString(rsa.getC()));
            }
        });
        解密密文Button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(textField24.getText().equals(""))
                    return;

                long C = rsa.getC();
                long d = rsa.getD();
                int n = rsa.getN();
                long M = Util.modCal(C, d, n);
                textField25.setText(Long.toString(M));
            }
        });

    }
}
