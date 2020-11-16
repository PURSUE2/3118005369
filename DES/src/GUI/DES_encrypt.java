package GUI;

import code.CustomDES;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DES_encrypt {
    private JComboBox comboBox1;
    private JButton Win1StartButton;
    private JComboBox comboBox3;
    private JButton win2calculateButton;
    private JButton button3;
    private JButton button4;
    private JPanel panel2;
    private JPanel panel1;
    String origin = CustomDES.generateBinaryStr();
    CustomDES customDES = new CustomDES("密码学~DES加密解密",origin);

    public static void main(String[] args) {
        JFrame frame = new JFrame("DES_encrypt");
        frame.setContentPane(new DES_encrypt().panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }

    private JPanel panel;
    private JTextArea textArea1;
    private JScrollPane scrollPane2;
    private JScrollPane scrollPane1;
    private JTextArea textArea2;
    private JTextArea textArea3;
    private JScrollPane scrollPane3;
    private JTextArea textArea4;
    private JTextArea textArea5;

    public DES_encrypt() {
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel1.setVisible(true);
                panel2.setVisible(false);
            }
        });
        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel1.setVisible(false);
                panel2.setVisible(true);
            }
        });
        Win1StartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int mode;
                String selectedItem = (String) comboBox1.getSelectedItem();
                if(selectedItem.equals("密钥固定，明文改变")){
                    mode = 0;
                }else{
                    mode = 1;
                }
                String rollStr = textArea1.getText();
                int rollNum = Integer.parseInt(rollStr);

                textArea2.setText("明文："+origin+"\n\n");
                textArea3.setText("");
                float total = 0;
                for(int k=0; k<rollNum; k++) {
                    float count = 0;

                    Map<int[], Map<String[], String[]>> map = customDES.changePlaintextOrKeyAnalysis(origin, mode, 1);

                    int[] num = new int[64];
                    String[] result = new String[64];
                    String[] changedResult = new String[64];
                    Map<String[], String[]> strMap = new HashMap<>();
                    for(int[] temp : map.keySet()){
                        num = temp;
                        strMap = map.get(temp);
                    }
                    for(String[] temp : strMap.keySet()){
                        result = temp;
                        changedResult = strMap.get(temp);
                    }

                    textArea2.append("第"+(k+1)+"轮统计：\n");
                    for (int i = 0; i < 64; i++) {
                        String br = "\n";
                        String line1 = "改变" + (i + 1) + "位前密文：" + result[i];
                        String line2 = "改变" + (i + 1) + "位后密文：" + changedResult[i];
                        String line3 = "密文改变" + num[i] + "位";
                        textArea2.append(line1 + br);
                        textArea2.append(line2 + br);
                        textArea2.append(line3 + br);

                        count += num[i];
                    }
                    total += count;
                    textArea2.append("\n");
                    textArea3.append("第"+(k+1)+"次统计："+count/64+"\n");
                }
                textArea3.append("总计平均改变位数："+total/(64*rollNum)+"\n");
            }
        });
        win2calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int sboxNum = 0;
                String sbox = (String) comboBox3.getSelectedItem();
                switch (sbox){
                    case "S1": sboxNum = 1;break;
                    case "S2": sboxNum = 2;break;
                    case "S3": sboxNum = 3;break;
                    case "S4": sboxNum = 4;break;
                    case "S5": sboxNum = 5;break;
                    case "S6": sboxNum = 6;break;
                    case "S7": sboxNum = 7;break;
                    case "S8": sboxNum = 8;break;
                }
                String inDifStr = textArea5.getText();

                Map<String, List<String>> outDifPair = customDES.getOutDifPair(inDifStr, sboxNum);

                textArea4.setText("");
                for(String outDifStr : outDifPair.keySet()){
                    textArea4.append("Sbox的输出差分：" + outDifStr + ",      输入数量：" + outDifPair.get(outDifStr).size() + ",\n可能的输入对：");
                    for (String inStr : outDifPair.get(outDifStr)) {
                        textArea4.append(inStr + " ");
                    }
                    textArea4.append("\n");
                }
            }
        });
    }
}
