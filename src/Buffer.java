import java.util.Arrays;
public class Buffer {
    private int[] buffer;
    Buffer(int n){
        buffer=new int[n];
        Arrays.fill(buffer, 0);
    }
    public void setIndexBuffer(int index, int value){
        System.out.println("Producing in "+index);
        buffer[index]=value;
    }
    public void getIndexValue(int index)
    {
        System.out.println("Consuming in "+index);
        buffer[index]=0;
    }

}
