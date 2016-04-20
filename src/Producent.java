
public class Producent extends Thread{
    Monitor m;
    Producent(Monitor m){
        this.m=m;
    }
    public void run(){
        m.produce();
    }
}
