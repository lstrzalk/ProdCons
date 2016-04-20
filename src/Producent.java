
public class Producent extends Thread{
    Monitor m;
    Producent(Monitor m){
        this.m=m;
    }
    public void run(){
        for (int i=0;i<10;i++){
            m.produce();
        }
    }
}
