
public class Consument extends Thread{
    Monitor m;
    Consument(Monitor m){
        this.m=m;
    }
    public void run(){
        m.consume();
    }
}
