import java.util.Random;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;                                                            

public class Prod implements Runnable{
	private int maxItems;
	private int itemCount;
	private int counter;
	private CirBuffer cBuff;
	private int buffSize;
	private int maxWait;

	public Prod(int buffSize, int maxWait, int numItems){
		this.buffSize = buffSize;
		this.maxWait = maxWait;
		this.maxItems = numItems;
		this.cBuff = new CirBuffer(buffSize);
	}		
	
	public void run(){
		int total = 0;
		while(itemCount < maxItems){
			synchronized(cBuff){
				//wait if needed
//				System.out.println("Head: " + cBuff.getHead());
//				System.out.println("Tail: " + cBuff.getTail());
//				System.out.println("Is full: " + cBuff.isFull());
				if(cBuff.isFull()){
					try {
						cBuff.wait(maxWait * 15);
					}
					catch(InterruptedException e) {
						e.printStackTrace();
					}
				}
				//get date and time
				DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss.SSSSS");
				Date date = new Date();
				//place in random number
				Random rand = new Random();
				int val = rand.nextInt(99);
			 	itemCount++;
				total += val; //add to the total
				int loc = cBuff.getTail(); // get the tail value before pushing
				cBuff.push(val);//add the val
				cBuff.notifyAll();	
				//Now Print 
				System.out.println("Producer: Placed " + val + " at Location " + loc + 
								   " at instant: " + df.format(date)); 	

			}
		}
		System.out.println("Producer done Producing " + itemCount + " whose sum is " + total);
	}
	
	public CirBuffer getBuffer(){
		return this.cBuff;
	}
}
