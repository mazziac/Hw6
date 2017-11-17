import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Cons implements Runnable{
	private int buffSize;
	private int maxWait;
	private CirBuffer cBuff;
	private int consNum; //Know which consumer you are

	public Cons(int buffSize, int maxWait,CirBuffer cBuff, int consNum){
		this.buffSize = buffSize;
		this.maxWait = maxWait;
		this.cBuff = cBuff;
		this.consNum = consNum;
	}
	
	public void run(){
		int total = 0;
		while(true){//we will break when we have waited too long
			synchronized(cBuff){
//				System.out.println("Head: " + cBuff.getHead());
//				System.out.println("Tail: " + cBuff.getTail());
				//get the time
							//set the waittime
				if(cBuff.isEmpty()){	//wait while the buffer is empty
					SimpleDateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss.SSSSS");      
					Date date = new Date();
					System.out.println("Unable to comsume, buffer empty, at Time: " +
										   df.format(date));
					long tBefore=System.currentTimeMillis();
         			try{
						cBuff.wait(maxWait * 15);
          			}
					catch (InterruptedException e){
						//nothing
					}
					if ((System.currentTimeMillis() - tBefore) > maxWait * 15) 
            			{
						System.out.println("Consumer " + consNum + " exiting after waiting for " + 
											(maxWait * 15) + ". Sum of Consumed item " + total);
						break; 
            			}

					}
				
				SimpleDateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss.SSSSS");      
				Date date = new Date();
				//get the location and value of access point
				int loc = cBuff.getHead();
				int val = cBuff.pop();
				total += val;				
				cBuff.notifyAll(); //notify buffer it has been consumed 
				System.out.println("Consumer " + consNum + ": Removed " + val 
								   + " from location: " + loc + 
								   " at Time: " +  df.format(date));
				
			}
		}
	}

}
