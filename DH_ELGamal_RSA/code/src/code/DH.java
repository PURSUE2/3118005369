package code;

import javax.crypto.Cipher;
import javax.crypto.KeyAgreement;
import javax.crypto.SecretKey;
import javax.crypto.interfaces.DHPublicKey;
import javax.crypto.spec.DHParameterSpec;
import java.math.BigInteger;
import java.security.*;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Objects;

public class DH {
    public int g;//公开原根
    public int p;//公开质数
    private int alice;//A保密数
    private int bob;//B保密数
    public int aliceY;//公开
    public int bobY;//公开
    private int aliceKey;//A计算而出的密钥
    private int bobKey;//B计算而出的密钥


    public void setP(){
        this.p = Util.generatePrimeNumber();
    }

    public void setG(){
        this.g = PrimeRoot.getPrimeRoot(this.p);
    }

    public void setAlice(){
        this.alice = (int)(Math.random()*5000);
    }

    public void setBob(){
        this.bob = (int)(Math.random()*5000);
    }

    public void setAliceY(){
        this.aliceY = (int)Util.modCal(g,alice,p);
    }

    public void setBobY(){
        this.bobY = (int)Util.modCal(g,bob,p);
    }

    public void setAliceKey(){
        this.aliceKey = (int)Util.modCal(bobY,alice,p);
    }

    public void setBobKey(){
        this.bobKey = (int)Util.modCal(aliceY,bob,p);
    }

    public int getG() {
        return g;
    }

    public int getP() {
        return p;
    }

    public int getAlice() {
        return alice;
    }

    public int getBob() {
        return bob;
    }

    public int getAliceY() {
        return aliceY;
    }

    public int getBobY() {
        return bobY;
    }

    public int getAliceKey() {
        return aliceKey;
    }

    public int getBobKey() {
        return bobKey;
    }

    @Override
    public String toString() {
        return "DH{" +
                "g=" + g +
                ", p=" + p +
                ", alice=" + alice +
                ", bob=" + bob +
                ", aliceY=" + aliceY +
                ", bobY=" + bobY +
                ", aliceKey=" + aliceKey +
                ", bobKey=" + bobKey +
                '}';
    }
}
