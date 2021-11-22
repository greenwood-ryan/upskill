import java.util.*;

public class AllStacks{
	public static void main(String[] args){
		new ArrayStack(5).test();
		new LinkedListStack().test();
	}
}

class LinkedListStack{
	private LinkedList <String> stack;
	
	public void test(){
		push("I");
		push("am");
		push("pushing");
		System.out.println("\nLinkedListStack\n"+peek());
		System.out.println(pop());
		System.out.println(pop());
		System.out.println(pop());
		push("am");
		push("pushing");
		System.out.println(peek());
	}
	
	public LinkedListStack(){
		stack = new LinkedList<String>();
	}
	
	public void push(String str){
		stack.push(str);
	}
	
	public String peek(){
		return stack.peek();
	}
	
	public String pop(){
		return stack.pop();
	}
}

class ArrayStack{
	private String[] stack;
	private int top;
	
	public void test(){
		push("I");
		push("am");
		push("pushing");
		System.out.println("\nArrayStack\n"+peek());
		System.out.println(pop());
		System.out.println(pop());
		System.out.println(pop());
		push("am");
		push("pushing");
		System.out.println(peek());
	}
	
	public ArrayStack(int capacity){
		stack = new String[capacity];
	}
	
	public void push(String str){
		if(top == stack.length-1){
			String[] arr = new String[stack.length * 2];
			System.arraycopy(stack, 0, arr, 0, stack.length);
			stack = arr;
		}
		stack[top] = str;
		top++;
	}
	
	public String peek(){
		return stack[top-1];
	}
	
	public String pop(){
		String popped = stack[top-1];
		stack[top] = null;
		top--;
		return popped;
	}
}
