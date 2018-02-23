import java.util.ArrayList;
import java.util.HashMap;

public class Main {
    public static void main(String args[]) {
        runLinkedListCode();
        //runConsumerProducer();
        HashMap<String,String> map = new HashMap<String, String>();
                map.put("","");
                int a = 10  ;
                int b = a << 2;//XOR
                System.out.print("b=" + b);
                System.out.println("areEqual=" + areEqual("dinesh","dinesh"));
    }
    private static boolean areEqual(String str1, String str2){
        if(str1==null || str1.isEmpty() || str2==null || str2.isEmpty() || str1.length()!=str2.length()) return false;
        boolean match = true;
        for(int i = 0; i < str1.length(); i++){
           match =  areEqual(str1.charAt(i),str2.charAt(i));
           if(!match){
               break;
           }
        }
        return match;
    }
private static boolean areEqual(char a, char b){
        int a1 = (int)a;
        int b1 = (int)b;
        return  areEqual(a1,b1);

}
    private static boolean areEqual(int a, int b){
        if((a ^ b) ==0)return true;
        return false;
    }
    private static void runLinkedListCode() {
        MyLinkedList<Student> linkedList = new MyLinkedList<Student>();
        linkedList.add(new Student("dinesh", "123"));
        linkedList.add(new Student("ramesh", "124"));
        linkedList.add(new Student("rajesh", "125"));
        linkedList.add(new Student("shiva", "146"));
        linkedList.add(new Student("rama", "154"));
        System.out.println("***********Print All the Students******************");
        linkedList.print();
        System.out.println("***************************************************");

        linkedList.insert(new Student("Manja", "126"));

        System.out.println("***********Print All the Students******************");
        linkedList.print();
        System.out.println("***************************************************");
    }

    private static void runConsumerProducer() {
        final ConsumerProducer cp = new ConsumerProducer();

        Runnable produceable = new Runnable() {

            public void run() {
                while (true) {
                    try {
                        cp.produce();
                    } catch (InterruptedException e) {

                    }
                }
            }
        };
        Runnable consumeable = new Runnable() {

            public void run() {
                while (true) {
                    try {
                        cp.consume();
                    } catch (InterruptedException e) {

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
        private static final int MAX = 10;
        private java.util.ArrayList<Integer> mQueue = new ArrayList(MAX + 1);

        public void produce() throws InterruptedException {
            synchronized (this) {
                if (mQueue.size() < MAX) {
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

        public synchronized void consume() throws InterruptedException {
            synchronized (this) {
                if (mQueue.size() > 0) {
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