//The driver class

public class HW6 extends Thread{
	
	public static void main(String[] args){

		if(args.length != 4){
			System.out.println("Provide only 4 args");
			exit(27);
		}

		int numConsumers = args[0];
		int buffSize = args[1];
		int numItems = args[2];
		int maxWait = args[3];
		//Create Objects
		Prod prod = new Prod(buffSize,maxWait,numItems); 
		Cons cons = new Cons(buffSize,maxWait,prod.getBuff());
		//Create threads
		Thread prodThread = new Thread(prod); 
		Thread consThread = new Threa(cons);
		//Start the Threads
		prodThread.start(); consThread.start();
		while(consThread.getState() != Thread.State.TERMINATED){
			//wait for threads to finish	
		}	
		// we are done now
	}

}
