package code;

import java.util.Scanner;

public class PrimeRoot {
    private static boolean[] primes = new boolean[32170];

    /**
     * 取p的某个原根
     * @param p
     * @return
     */
    public static int getPrimeRoot(int p){
        int tempG = 0;
        do{
            tempG = (int)(Math.random()*p);
        }while(tempG<2 && !isPrimeRoot(tempG,p));
        return tempG;
    }

    private static boolean isPrimeRoot(int g, int P) {
        for (int i = 2; i < P; i++) {
            if (!primes[i] && quickPow(P, 1, i) == 0)
                if (quickPow(g, P / i, P + 1) == 1)
                    return false;
            while (P % i == 0) P /= i;
        }
        return true;
    }

    private static int quickPow(int a, int b, int c) {
        int ans = 1;
        for (; b != 0; b /= 2) {
            if (b % 2 == 1)
                ans = (ans * a) % c;
            a = (a * a) % c;
        }
        return ans;
    }
}

