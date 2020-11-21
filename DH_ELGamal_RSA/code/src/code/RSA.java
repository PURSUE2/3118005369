package code;

public class RSA {
    private int p;//大素数：保密
    private int q;//大素数：保密
    public int n;//n=pq：公开
    private int f_n;//f_n=(p-1)(q-1)：保密
    public int e;//1<e<f_n且(e,f_n)=1,将e公开
    private long d;//ed=1 mod f_n：保密
    private long M;//明文
    public long C;//密文

    public int getP() {
        return p;
    }

    public void setP() {
        this.p = Util.generateBigPrimeNumber();
    }

    public int getQ() {
        return q;
    }

    public void setQ() {
        this.q = Util.generateBigPrimeNumber();
    }

    public int getN() {
        return n;
    }

    public void setN() {
        this.n = this.p*this.q;
    }

    public int getF_n() {
        return f_n;
    }

    public void setF_n() {
        this.f_n = (p-1)*(q-1);
    }

    public int getE() {
        return e;
    }

    public void setE() {
        int temp = 0;
        do{
            temp = (int) (Math.random() * this.f_n);
        }while(temp<=1 || !Util.isRelativePrime(temp,this.f_n));

        this.e = temp;
    }

    public long getD() {
        return d;
    }

    public void setD() {
        this.d = Util.modInverseShow(this.e,this.f_n);
    }

    public long getM() {
        return M;
    }

    public void setM(long m) {
        M = m;
    }

    public long getC() {
        return C;
    }

    public void setC() {
        C = Util.modCal(this.M,this.e,this.n);
    }
}
