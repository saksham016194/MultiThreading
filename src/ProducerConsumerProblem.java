
import java.util.Vector;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author saksham
 */
class Producer implements Runnable{
    Vector<Integer> sQueue;
    public Producer(Vector<Integer> queue) {
        this.sQueue=queue;
    }
    public void run(){
        int i=0;
        try{
        
        for(i=0;i<100;i++){
            if(sQueue.size()<15){
                synchronized(sQueue){
            System.out.println("Producer producing the item:-"+i);
            sQueue.add(i);
                
            sQueue.notifyAll();
                }
            
            }
            else
            {   synchronized(sQueue){
                sQueue.notifyAll();
                sQueue.wait();
            }
            
            }
        }
    
    }
        catch(Exception e){
            e.printStackTrace();
        }
    }

}
class Consumer implements Runnable{
    Vector<Integer> sQueue;
    public Consumer(Vector<Integer> queue) {
        this.sQueue=queue;
    }
    public void run(){
        int i=0;
        try{
      
        while(true){
            if(sQueue.size()>0){
                  synchronized(sQueue){
            System.out.println("Consumer is removing the item:-");
            int item=sQueue.remove(0);
                System.out.println(item);
                  
            sQueue.notifyAll();
                  }
          Thread.sleep(100);
            }
            else
            {     synchronized(sQueue){
                sQueue.wait();
            }
            }
        }
   
    }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
public class ProducerConsumerProblem {
    public static void main(String [] arg){
        Vector<Integer> sQueue;
        sQueue = new Vector<>(15);
        Producer p=new Producer(sQueue);
        Consumer c=new Consumer(sQueue);
        Thread producer=new Thread(p);
        Thread consumer=new Thread(c);
        producer.start();
        consumer.start();
      
        
    }
}
