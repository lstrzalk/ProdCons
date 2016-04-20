public class Main {
    public static void main(String[] argv){
        final int buffSize=20;
        final int n=5;
        Buffer buf = new Buffer(buffSize);
        Monitor m=new Monitor(buf,buffSize);
        Consument[] c = new Consument[n];
        Producent[] p = new Producent[n];
        for (int i = 0 ;i<n;i++) {
            c[i]=new Consument(m);
            p[i]=new Producent(m);
        }
        for (int i =0;i<n;i++){
            c[i].start();
            p[i].start();
        }
    }

}
