
public class CirBuffer{
	private int bufferSize;
	private int[] buffer;
	private int head;
	private int tail;

	//CTORS
	public CirBuffer(int size){
		bufferSize = size;
		buffer = new int[size];
		head = 0;
		tail = 0; 
	}
	//Boolean checks
	
	public boolean isFull(){
		//if tail is right behind head
		if((tail == bufferSize - 1 && head == 0) || (tail + 1 == head))
			return true;
		return false;
	}	

	public boolean isEmpty(){
		//Buffer is empty if the tail and head are the same
		if(tail == head)
			return true;
		return false;
	}
	//Methods
	public void push(int value){
		//put value in and increment tail
		buffer[tail] = value; tail++;
		if(tail == bufferSize){ //circulation
			tail = 0;
		} 			
	}

	public int pop(){
		int value = buffer[head]; //get the value
		buffer[head] = new Integer(0); //set the new value
		head++; //increment where we pop from
		if(head == bufferSize){
			head = 0;
		}
		return value;
	}
	
	//GETTERS
	public int getHead(){ return head; }
	public int getTail(){ return tail; }
	
}
