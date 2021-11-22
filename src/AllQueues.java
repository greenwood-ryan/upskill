import java.util.*;

public class AllQueues{
	public static void main(String[] args){
		new ArrayQueue(5).test();
		new LinkedListQueue().test();
		new CircularQueue(3).test();
	}
}

class CircularQueue{
	private String[] queue;
	private int front, back;
	
	public void test(){
		System.out.println("\nCircularQueue");
		add("I");
		print(queue);
		add("am");
		print(queue);
		add("adding");
		print(queue);
		add("again");
		print(queue);
	/*	remove();
		print(queue);
		add("dog");
		print(queue);
		remove();
		print(queue);
		add("cat");
		print(queue);
		remove();
		print(queue);
		add("snake");
		print(queue);
		add("hen");
		print(queue);
		add("duck");
		print(queue);
		add("goat");
		print(queue);*/
	}
	
	public CircularQueue(int capacity){
		queue = new String[capacity];
	}
	
	public int size(){
		if(front <= back)
			return back - front;
		else
			return back - front + queue.length;
	}
	//changes from the array implementation for the circular implementation 
	public void add(String str){
			System.out.println("front = "+front+" back = "+back+" size = "+size());
		if(size() == queue.length - 1){
			int numItems = size();//use to set back
			String[] arr = new String[queue.length * 2];
			
			//when we resize the queue we unwrap the queue using back as seperator for unwrap.
			//this has to happen when there is one less item in the array than array size
			System.arraycopy(queue, front, arr, 0, queue.length - front);
			System.arraycopy(queue, 0, arr, queue.length - front, back);
			
			queue = arr;
			front = 0;
			back = numItems;
		}
		queue[back] = str;
		if(back < queue.length-1)
			back++;
		else
			back = 0;
	}
	
	private void print(String[] arr){
		for(String s : arr)
			System.out.print(s+" ");
		System.out.println();
	}
	

	public String peek(){
		return queue[front];
	}
	
	public String remove(){
		String temp = queue[front];
		queue[front] = null;
		front++;
		if(size() == 0)
			front = back = 0;
		else if(front == queue.length)
			front = 0;
		return temp;
	}
}


class LinkedListQueue{
	private LinkedList <String> queue;
	
	public void test(){
		add("I");
		add("am");
		add("adding");
		System.out.println("\nLinkedListQueue\n"+peek());
		System.out.println(remove());
		System.out.println(remove());
		System.out.println(remove());
		add("am");
		add("adding");
		System.out.println(peek());
	}
	
	public LinkedListQueue(){
		queue = new LinkedList<String>();
	}
	
	public void add(String str){
		queue.add(str);
	}
	
	public String peek(){
		return queue.peek();
	}
	
	public String remove(){
		return queue.remove();
	}
}

class ArrayQueue{
	private String[] queue;
	private int front, back;
	
	public void test(){
		add("I");
		add("am");
		add("adding");
		System.out.println("\nArrayQueue\n"+peek());
		System.out.println(remove());
		System.out.println(remove());
		System.out.println(remove());
		add("am");
		add("adding");
		System.out.println(peek());
	}
	
	public ArrayQueue(int capacity){
		queue = new String[capacity];
	}
	
	public int size(){
		return back - front;
	}
	
	public void add(String str){
		if(back == queue.length){
			String[] arr = new String[queue.length * 2];
			System.arraycopy(queue, 0, arr, 0, queue.length);
			queue = arr;
		}
		queue[back] = str;
		back++;
	}
	
	public String peek(){
		return queue[front];
	}
	
	public String remove(){
		String temp = queue[front];
		queue[front] = null;
		front++;
		if(size() == 0)
			front = back = 0;
		return temp;
	}
}
