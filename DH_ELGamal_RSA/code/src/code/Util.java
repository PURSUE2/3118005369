package code;

import java.math.BigInteger;

public class Util {
    /**
     * 判断a与b是否互质
     * @param a
     * @param b
     * @return
     */
    public static boolean isRelativePrime(int a, int b){
        if(a < b) {
            int tmp = a;
            a = b;
            b = tmp;
        }
        int c;
        while((c = a % b) != 0) {
            a = b;
            b = c;
        }
        if(b==1)
            return true;
        return false;
    }

    /**
     * 求模指c = m^e mod n;
     * @param m
     * @param e
     * @param n
     * @return
     */
    public static long modCal(long m,long e,long n){
        long c = 1;
        String eStr = intToStr(e);
        for(int i=eStr.length()-1;i>=0;i--){
            c = (c*c)%n;
            if(eStr.charAt(i)=='1'){
                c = c*m%n;
            }
        }
        return c;
    }

    /**
     * 求模逆
     * @param d
     * @param n 模
     * @return d^-1
     */
    public static long modInverseShow(int d, int n){
        long up = n;
        long down = d;
        long inverseUp = 0;
        long inverse = 1;
        long times, temp;
        for(;;) {
            times = up / down;
            temp = up - down * times;
            up = down;
            down = temp;
            temp = inverseUp - inverse * times;
            inverseUp = inverse;
            inverse = temp;
            if(down == 1)return (inverse > 0) ? inverse : (inverse + n);
            if(down <= 0)return -1;
        }
    }

    /**
     * 将十进制数转为二进制字符串再反转
     * @param e
     * @return
     */
    public static String intToStr(long e){
        String temp = Long.toBinaryString(e);
        String result = new StringBuffer(temp).reverse().toString();
        return result;
    }

    /**
     * 生成随机素数
     * @return
     */
    public static int generatePrimeNumber(){
        String a = Integer.toString((int)(Math.random()*5000));
        BigInteger b = new BigInteger(a);
        BigInteger p = b.nextProbablePrime();
        return p.intValue();
    }

    /**
     * 生成随机大素数
     * @return
     */
    public static int generateBigPrimeNumber(){
        int temp = 0;
        do{
            temp = (int) (Math.random() * 5000);
        }while(temp<1000);
        String a = Integer.toString(temp);
        BigInteger b = new BigInteger(a);
        BigInteger p = b.nextProbablePrime();
        return p.intValue();
    }
}
