package code;

public class ELGamal {
    public int p;//公开大素数p
    public int a;//公开本原元a
    private int d;//秘密解密钥d
    public int y;//公开加密钥y
    private int M;//明文消息M
    private int k;
    private int U;
    private int C1;
    private int C2;
    private int V;


    public int getP() {
        return p;
    }

    public void setP() {
        this.p = Util.generateBigPrimeNumber();
    }

    public int getA() {
        return a;
    }

    public void setA() {
        this.a = PrimeRoot.getPrimeRoot(this.p);
    }

    public int getD() {
        return d;
    }

    public void setD() {
        this.d = (int)(Math.random()*500);
    }

    public int getY() {
        return y;
    }

    public void setY() {
        this.y = (int)Util.modCal(this.a,this.d,this.p);
    }

    public int getM() {
        return M;
    }

    public void setM(int m) {
        M = m;
    }

    public int getK() {
        return k;
    }

    public void setK() {
        int temp = 0;
        do{
            temp = (int)(Math.random()*(this.p-1));
        }while(temp<=1);
        this.k = temp;
    }

    public int getU() {
        return U;
    }

    public void setU() {
        U = (int)Util.modCal(this.y,this.k,this.p);
    }

    public int getC1() {
        return C1;
    }

    public void setC1() {
        C1 = (int)Util.modCal(this.a,this.k,this.p);
    }

    public int getC2() {
        return C2;
    }

    public void setC2() {
        C2 = (this.U*this.M)%this.p;
    }

    public int getV() {
        return V;
    }

    public void setV() {
        V = (int)Util.modCal(this.C1,this.d,this.p);
    }
}
