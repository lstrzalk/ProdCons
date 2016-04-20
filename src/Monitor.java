import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Monitor {
	ReentrantLock[] lock;
	LinkedList<Integer> full = new LinkedList<Integer>();
	LinkedList<Integer> free = new LinkedList<Integer>();
    Condition cond[];
    Buffer buf;
	Monitor(Buffer buf, int n){
        this.buf=buf;
        lock=new ReentrantLock[n];
        cond=new Condition[n];
		for(int i = 0 ; i < n ; i++){
			free.add(i);
            lock[i]=new ReentrantLock();
            cond[i]=lock[i].newCondition();
		}
	}
	public void produce(){
		int index;
        while (free.isEmpty()){
            try {
                synchronized (this){
                    this.wait();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        index=free.removeFirst();
        while (lock[index].isLocked()){
            try {
                cond[index].await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        lock[index].lock();
        buf.setIndexBuffer(index,1);
        synchronized (this){
            this.notify();
        }
        full.addLast(index);
        cond[index].signal();
        lock[index].unlock();
	}
	public void consume(){
		int index;
        while(full.isEmpty()){
            try {
                synchronized (this){
                    this.wait();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        index=full.removeFirst();
        while (lock[index].isLocked()){
            try {
                cond[index].await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        lock[index].lock();
        buf.getIndexValue(index);
        synchronized (this){
            this.notify();
        }
        free.addLast(index);
        cond[index].signal();
        lock[index].unlock();
	}
}
