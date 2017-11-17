//The driver class

public class HW6 extends Thread{
	
	public static void main(String[] args){

		if(args.length != 4){
			System.out.println("Provide only 4 args");
			System.exit(0);
		}

		int numConsumers = Integer.parseInt(args[0]);
		int buffSize = Integer.parseInt(args[1]);
		int numItems = Integer.parseInt(args[2]);
		int maxWait = Integer.parseInt(args[3]);
		//Create Objects
		Prod prod = new Prod(buffSize,maxWait,numItems);
		//Possible multiple consumers
		Cons[] cons = new Cons[numConsumers];
		for(int i = 1; i <= numConsumers; i++) 
			cons[i-1] = new Cons(buffSize,maxWait,prod.getBuffer(),i);
		//Create threads
		Thread prodThread = new Thread(prod);
		//account for mult threads
		Thread[] consThread = new Thread[numConsumers] ;
		for(int i = 0; i < numConsumers; i++)
			consThread[i] = new Thread(cons[i]);
		//Start the Threads 
		for(int i = 0; i < numConsumers; i++)
			consThread[i].start();
		prodThread.start();	
		while(true){ //wait for the threads to die
			try{
				prodThread.join();
				for(int i = 0; i < numConsumers; i++)
					consThread[i].join();
			} catch (InterruptedException e){
				e.printStackTrace();
			}
		}	
		// we are done now
	}

}
