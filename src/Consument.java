
public class Consument extends Thread{
    Monitor m;
    Consument(Monitor m){
        this.m=m;
    }
    public void run(){
        for (int i=0;i<10;i++){
            m.consume();
        }
    }
}
