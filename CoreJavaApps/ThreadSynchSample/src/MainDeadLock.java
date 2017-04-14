import java.util.ArrayList;
public class MainDeadLock {
    public static void main(String args[]) {
        Keyboard keyboard = new Keyboard();
		 Display display = new Display();
		 keyboard.setDisplay(display);
		 display.setKeyboard(keyboard);
       
        Runnable keyboardTask = new Runnable(){
          
            public void run(){               
                    try{
                   keyboard.processKeyboard();
                    }catch(InterruptedException e){
                        
                    }
               
            }
        };
         Runnable displayTask = new Runnable(){
          
            public void run(){          
                    try {
                    display.processDisplay();
                }catch(InterruptedException e){
                        
                    }
                
            }
        };
        
        Thread keyboardThread = new Thread(keyboardTask);
        Thread displayThread = new Thread(displayTask);
        keyboardThread.setName("keyboardThread");
        keyboardThread.start();
        
        displayThread.setName("displayThread");
        displayThread.start();
        
    }
	
	static class Keyboard {
		private Display mDisplay;
       		
		public Keyboard(){
	
		}
		public  Display getDisplay(){
			return mDisplay;
		}
		public  void setDisplay(Display display){
			 mDisplay = display;
		}
        public  synchronized void processKeyboard()throws InterruptedException {
                    Thread.sleep(2000);  
					System.out.println(Thread.currentThread().getName() + ":from processKeyboard(),going to call showDisplay()");
					wait();
					mDisplay.showDisplay();
  
					System.out.println(Thread.currentThread().getName() + ":from processKeyboard(),returned from showDisplay()");
        }
		
        public synchronized void inputKeys() throws InterruptedException{
                            Thread.sleep(2000);
						
        }
	}
    static class Display{
		private Keyboard mKeyboard;
       		
		public Display(){
	
		}
		public  Keyboard getKeyboard(){
			return mKeyboard;
		}
		public  void setKeyboard(Keyboard keyboard){
			 mKeyboard = keyboard;
		}
        public  synchronized void processDisplay()throws InterruptedException {
                    Thread.sleep(2000);  
					System.out.println(Thread.currentThread().getName() + ":from processDisplay(),going to call inputKeys()");
					
					mKeyboard.inputKeys();
                        mKeyboard.notify();
					System.out.println(Thread.currentThread().getName() + ":from processDisplay(),returned from inputKeys()");
        }
		
        public synchronized void showDisplay() throws InterruptedException{
                            Thread.sleep(2000);
							
        }
    }
}