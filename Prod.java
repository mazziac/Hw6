import java.util.Random;

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
		cBuff = new CirBuffer();
	}		
	
	public void run(){
		int total = 0;
		while(itemCount < numItems){
			synchonized(cBuff){
				//Get date/time
				DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
				Date date = new Date();
				if(cBuff.isFull()){ //if buff is full, wait
					System.out.println("Unable to insert,buffer full, at instant: " 
									   + df.format(date));
					try {	
						bBuff.wait();
					} 
					catch(InterruptedException e){
						e.printStackTrace();
					} 					
				}//Buffer ain't full no mo', lets put a rand val in buff
				Random rand = new Random();
				int val = random.nextInt() * 100.0;
			 	itemCount++;
				total += val; //add to the total
				int loc = cBuff.getTail(); // get the tail value before pushing
				cBuff.push(value);//add the val
				//Now Print 
				System.out.println("Producer: Placed " + val + " at Location " + loc + 
								   " at instant: " + df.format(date)); 		
			}
		}
		System.out.println("Producer done Producing " + numItems + " whose sum is " + total);
	}
	
	public CirBuffer getBuffer(){
		return this.cBuff;
	}
}
