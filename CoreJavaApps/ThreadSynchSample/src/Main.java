import java.util.ArrayList;
public class Main {
    public static void main(String args[]) {
        ConsumerProducer cp = new ConsumerProducer();
       
        Runnable produceable = new Runnable(){
          
            public void run(){
                while(true){
                    try{
                    cp.produce();
                    }catch(InterruptedException e){
                        
                    }
                }
            }
        };
         Runnable consumeable = new Runnable(){
          
            public void run(){
                while(true){
                    try {
                    cp.consume();
                }catch(InterruptedException e){
                        
                    }
                }
            }
        };
        
        Thread producer = new Thread(produceable);
        Thread consumer = new Thread(consumeable);
        producer.setName("Producer");
        producer.start();
        
        consumer.setName("Consumer");
        consumer.start();
        
    }
    static class ConsumerProducer {
         private int sharedIntValue = 0;
        private static final int MAX=10;
        private java.util.ArrayList<Integer> mQueue = new ArrayList<>(MAX+1);
        public  void produce()throws InterruptedException {
            synchronized(this){
                    if(mQueue.size()<MAX){
                         sharedIntValue++;
                    mQueue.add(new Integer(sharedIntValue));
                    System.out.println(Thread.currentThread().getName() + "->produced:" + sharedIntValue);
                    } else {
                        System.out.println(Thread.currentThread().getName() + "->queue is full. moving to waiting state");
                       // this.notify();
                        this.wait();
                    }
            }
        }
        public synchronized void consume() throws InterruptedException{
            synchronized(this){
                   if(mQueue.size()>0){
                    mQueue.remove(new Integer(sharedIntValue));
                    System.out.println(Thread.currentThread().getName() + "->Consumed:" + sharedIntValue);
                     sharedIntValue--;
                    } else {
                        System.out.println(Thread.currentThread().getName() + "->queue is empty. moving to waiting state");
                        this.notify();
                        this.wait();
                    }
            }
        }
    }
}