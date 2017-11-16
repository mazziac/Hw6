
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
			synchonized(cBuff){
				//get the time
				DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");      
                Date date = new Date();
				//set the waittime
				if(cBuff.isEmpty()){	//wait while the buffer is empty
					try {
						System.out.println("Unable to comsume, buffer empty, at Time: " +
										   df.format(date));	
						cBuff.wait(maxWait * 15);
					} 
					catch (InterruptedException e){
						System.out.println("Consumer " + consNum + " exiting after waiting for " + 
											(maxWait * 15) + ". Sum of Consumed item " + total);
						exit(27);
					}
				}
				//get the location and value of access point
				int loc = cBuff.getHead();
				int val = cBuff.pop();
				System.out.println("Consumer " + consNum + ": Removed " + val 
								   + " at Time: " +  df.format(date)); 
			}
		}
	}
}
