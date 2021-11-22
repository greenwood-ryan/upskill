import java.util.*;

public class AllLists{
	public static void main(String[] args){
		new MyDoublyLinkedList().test();
		new JDKLinkedList().test();
		new MySinglyLinkedList().test();
	}
}

class JDKLinkedList{
	LinkedList<String> list = new LinkedList<>();
	
		public void test(){
		System.out.println("\n\nJDKLinkedList\nList is empty? "+ (list.size() == 0));
		list.addFirst("three");
		list.addFirst("two");
		list.addFirst("one");
		print();
		System.out.println("List size is: "+ list.size());
		System.out.println("Remove "+ list.remove()+" from front of list which now looks like...");
		print();
		System.out.println("\nList size is: "+ list.size());
		System.out.println("add one to tail...");
		list.addLast("one");
		print();
		System.out.println("\nRemove "+ list.removeLast()+" one from tail of list which now looks like...");
		print();
	}
	
		public void print(){
		Iterator it = list.iterator();
		while(it.hasNext())
			System.out.print(it.next()+" ");
	}
}

class MyDoublyLinkedList{
	Node head, tail;
	int size = 1;
	
	public void test(){
		System.out.println("\n\nMyDoublyLinkedList\nList is empty? "+ isEmpty());
		Node three = add("three");
		add("two");
		add("one");
		print(head);
		System.out.println("\nList size is: "+ size);
		System.out.println("List is empty? "+ isEmpty());
		System.out.println("Remove "+ remove()+" from front of list which now looks like...");
		print(head);
		System.out.println("\nList size is: "+ size);
		System.out.println("add one to tail...");
		addLast("one");
		print(head);
		System.out.println("\nRemove "+ removeLast()+" from end of list which now looks like...");
		print(head);
		System.out.println("\nAdd "+ addBefore(three, "ten")+" before "+three+" in the list which now looks like...");
		print(head);
	}
	
	public Node addBefore(Node existing, String str){
		if(existing == null || str == null)
			return null;
		Node neww = new Node(str);
		Node previous = existing.previous;
		existing.previous = neww;
		neww.previous = previous;
		neww.next = existing;
		previous.next = neww;
		return neww;
	}
	

	
	//remove from the tail only
	public Node removeLast(){
		if(isEmpty())
			return null;
		Node node = tail;
		tail = node.previous;
		tail.next = null;
		size--;
		return node;
	}
	
	//remove from the head only
	public Node remove(){
		if(isEmpty())
			return null;
		Node node = head;
		head = head.next;
		head.previous = null;
		size--;
		return node;
	}
	
	public void print(Node n){
		if(n == null)
			return;
		System.out.print(n+" ");
		print(n.next);
	}
	
	public boolean isEmpty(){
		return head == null;
	}
	
	//add to the tail only
	public void addLast(String val){
		if(val == null)
			return;
		Node node = new Node(val);
		if(head == null){
			head = tail = node;
			return;
		}
		tail.next = node;
		node.previous = tail;
		node.next = null;
		tail = node;
		size++;
	}		

	//add to the head only
	public Node add(String val){
		if(val == null)
			return null;
		Node node = new Node(val);
		if(head == null){
			head = tail = node;
			return node;
		}
		head.previous = node;
		node.next = head;
		node.previous = null;
		head = node;
		size++;
		return node;
	}		
	
	class Node{
		Node next, previous;
		String value;
		
		public Node(String val){
			value = val;
		}
		
		public String toString(){
			return value;
		}
	}
}

class MySinglyLinkedList{
	Node head;
	IntNode headInt;
	int size = 1;
	
	public void test(){
		System.out.println("\n\nMySinglyLinkedList\nList is empty? "+ isEmpty());
		add("three");
		add("two");
		add("one");
		print(head);
		System.out.println("List size is: "+ size);
		System.out.println("List is empty? "+ isEmpty());
		System.out.println("Remove "+ remove()+" from front of list which now looks like...");
		print(head);
		System.out.println("\nList size is: "+ size);
		System.out.println("\nAdding ints in order ");
		addInOrder(2);
		addInOrder(4);
		addInOrder(6);
		addInOrder(8);
		addInOrder(10);
		System.out.println("\nReversing this singly linked list...");
		print(headInt);
		//System.out.println("\nThis singly linked list after being reversed\n");
		//print(reverse(headInt));
		//print(headInt);
		//System.out.println("\nThis singly linked list after reversing 2 - 4\n");
		//print(reverseSubList(headInt, 2, 4));
		//System.out.println("\nThe middle of this singly linked list is: "+findMiddle(headInt));
		testHasCycle();
		testFindCycleStart();
		testIsPalindrome();
	}
	
	void testIsPalindrome(){
		IntNode head = new IntNode(2);
		head.next = new IntNode(4);
		head.next.next = new IntNode(6);
		head.next.next.next = new IntNode(4);
		head.next.next.next.next = new IntNode(2);
		System.out.println("Is palindrome: " + isPalindrome(head));

		head.next.next.next.next.next = new IntNode(2);
		System.out.println("Is palindrome: " + isPalindrome(head));
	}
	/*
		LL is palindrome?  
			find middle of the list
			reverse the second half
			store the head of the reversed half for later reference
			compare the first half with the second node by node
			reverse the reverse of the second half i.e. restore it
			if the head of first half or the head of second half is null is a palindrome
			else isn't
	*/
	//TC O(n)  SC O(1)
		public boolean isPalindrome(IntNode head) {
		if (head == null || head.next == null)
		  return true;

		// find middle of the LinkedList
		IntNode slow = head;
		IntNode fast = head;
		while (fast != null && fast.next != null) {
		  slow = slow.next;
		  fast = fast.next.next;
		}

		IntNode headSecondHalf = reverse(slow); // reverse the second half
		IntNode copyHeadSecondHalf = headSecondHalf; // store the head of reversed part to revert back later

		// compare the first and the second half
		while (head != null && headSecondHalf != null) {
		  if (head.value != headSecondHalf.value) {
			break; // not a palindrome
		  }
		  head = head.next;
		  headSecondHalf = headSecondHalf.next;
		}

		reverse(copyHeadSecondHalf); // revert the reverse of the second half
		if (head == null || headSecondHalf == null) // if both halves match
		  return true;
		return false;
	  }

	/*  private IntNode reverse(IntNode head) {
		IntNode prev = null;
		while (head != null) {
		  IntNode next = head.next;
		  head.next = prev;
		  prev = head;
		  head = next;
		}
		return prev;
	  }*/
  
	void testFindCycleStart(){
		IntNode head = new IntNode(1);
		head.next = new IntNode(2);
		head.next.next = new IntNode(3);
		head.next.next.next = new IntNode(4);
		head.next.next.next.next = new IntNode(5);
		head.next.next.next.next.next = new IntNode(6);

		head.next.next.next.next.next.next = head.next.next;
		System.out.println("LinkedList cycle start: " + findCycleStart(head).value);

		head.next.next.next.next.next.next = head.next.next.next;
		System.out.println("LinkedList cycle start: " + findCycleStart(head).value);

		head.next.next.next.next.next.next = head;
		System.out.println("LinkedList cycle start: " + findCycleStart(head).value);
	}
	/*
		Given the head of a Singly LinkedList that contains a cycle, write a function to find the starting node of the cycle.
			Take two pointers. Let’s call them pointer1 and pointer2.
			Initialize both pointers to point to the start of the LinkedList.
			We can find the length of the LinkedList cycle using the approach discussed in LinkedList Cycle. Let’s assume that the length of the cycle is ‘K’ nodes.
			Move pointer2 ahead by ‘K’ nodes.
			Now, keep incrementing pointer1 and pointer2 until they both meet.
			As pointer2 is ‘K’ nodes ahead of pointer1, which means, pointer2 must have completed one loop in the cycle when both pointers meet. Their meeting point will be the start of the cycle.
	*/
	//first find the cycle length and then find the cycle start using the cycle length
	//TC O(n)  SC O(1)
	public static IntNode findCycleStart(IntNode head) {
		int cycleLength = 0;
		// find the LinkedList cycle
		IntNode slow = head;
		IntNode fast = head;
		while (fast != null && fast.next != null) {
		  fast = fast.next.next;
		  slow = slow.next;
		  if (slow == fast) { // found the cycle
				cycleLength = calculateCycleLength(slow);
				break;
			}
		}

		return findStart(head, cycleLength);
	}

	  private static int calculateCycleLength(IntNode slow) {
		IntNode current = slow;
		int cycleLength = 0;
		do {
		  current = current.next;
		  cycleLength++;
		} while (current != slow);
		
		return cycleLength;
	  }

	  private static IntNode findStart(IntNode head, int cycleLength) {
		IntNode pointer1 = head, pointer2 = head;
		// move pointer2 ahead 'cycleLength' nodes
		while (cycleLength > 0) {
		  pointer2 = pointer2.next;
		  cycleLength--;
		}

		// increment both pointers until they meet at the start of the cycle
		while (pointer1 != pointer2) {
		  pointer1 = pointer1.next;
		  pointer2 = pointer2.next;
		}

		return pointer1;
	  }
	
	void testHasCycle(){
		IntNode head = new IntNode(1);
		head.next = new IntNode(2);
		head.next.next = new IntNode(3);
		head.next.next.next = new IntNode(4);
		head.next.next.next.next = new IntNode(5);
		head.next.next.next.next.next = new IntNode(6);
		System.out.println("\n\nLinkedList has cycle: " + hasCycle(head));

		head.next.next.next.next.next.next = head.next.next;
		System.out.println("LinkedList has cycle: " + hasCycle(head));

		head.next.next.next.next.next.next = head.next.next.next;
		System.out.println("LinkedList has cycle: " + hasCycle(head));
	}
	
	/*
		Given the head of a Singly LinkedList, write a function to determine if the LinkedList has a cycle in it or not.
		Have a slow and a fast pointer to traverse the LinkedList. In each iteration, the slow pointer moves one step and the fast pointer moves two steps. This gives us two conclusions:
		If the LinkedList doesn’t have a cycle in it, the fast pointer will reach the end of the LinkedList before the slow pointer to reveal that there is no cycle in the LinkedList.
		The slow pointer will never be able to catch up to the fast pointer if there is no cycle in the LinkedList.
	*/
	
	//TC O(n)  SC O(1)
	public boolean hasCycle(IntNode head) {
		IntNode slow = head;
		IntNode fast = head;
		while (fast != null && fast.next != null) {
		  fast = fast.next.next;
		  slow = slow.next;
		  if (slow == fast)
			return true; // found the cycle
		}
		return false;
	}
	
	
	//TC O(n)  SC O(1)
	public IntNode findMiddle(IntNode head) {
		IntNode slow = head;
		IntNode fast = head;
		while (fast != null && fast.next != null) {
		  slow = slow.next;
		  fast = fast.next.next;
		}

		return slow;
	}
	
	//TC O(n)  SC O(1)
	public IntNode reverseSubList(IntNode head, int start, int end){
		if (start == end)
			return head;

		// after skipping 'p-1' nodes, current will point to 'p'th node
		IntNode current = head, previous = null;
		for (int i = 0; current != null && i < start - 1; ++i) {
		  previous = current;
		  current = current.next;
		}

		// we are interested in three parts of the LinkedList, part before index 'p', part between 'p' and 
		// 'q', and the part after index 'q'
		IntNode lastNodeOfFirstPart = previous; // points to the node at index 'p-1'
		// after reversing the LinkedList 'current' will become the last node of the sub-list
		IntNode lastNodeOfSubList = current;
		IntNode next = null; // will be used to temporarily store the next node
		// reverse nodes between 'p' and 'q'
		for (int i = 0; current != null && i < end - start + 1; i++) {
		  next = current.next;
		  current.next = previous;
		  previous = current;
		  current = next;
		}

		// connect with the first part
		if (lastNodeOfFirstPart != null)
		  lastNodeOfFirstPart.next = previous; // 'previous' is now the first node of the sub-list
		else // this means p == 1 i.e., we are changing the first node (head) of the LinkedList
		  head = previous;

		// connect with the last part
		lastNodeOfSubList.next = current;

		return head;	
	}
	
	//TC O(n)  SC O(1)
	public IntNode reverse(IntNode head){
		if(head == null)
			return head;
		IntNode current = head, next = null, previous = null;
		while (current != null) {
			  next = current.next; // temporarily store the next node
			  current.next = previous; // reverse the current node
			  previous = current; // before we move to the next node, point previous to the current node
			  current = next; // move on the next node
			}
			// after the loop current will be pointing to 'null' and 'previous' will be the new head
		return previous;
	}
	
	public Node remove(){
		if(isEmpty())
			return null;
		Node node = head;
		head = head.next;
		size--;
		return node;
	}
	
	public void print(Node n){
		if(n == null)
			return;
		System.out.print(n+" ");
		print(n.next);
	}
	
	public void print(IntNode n){
		if(n == null)
			return;
		System.out.print(n+" ");
		print(n.next);
	}
	
	public boolean isEmpty(){
		return head == null;
	}
	
	public IntNode addInOrder(int val){
		IntNode node = new IntNode(val);
		if(headInt == null){
			headInt = node;
			return node;
		}
		if(node.value > headInt.value)
			node = addAfter(node);
		else
			node = addBefore(node);
		size++;
		return node;
	}
	
	private IntNode addBefore(IntNode node){
		node.next = headInt;
		headInt = node;
		size++;
		return node;
	}

	private IntNode addAfter(IntNode newNode){
		IntNode currentNode = headInt, previousNode = null;
		while(newNode.value > currentNode.value && currentNode.next != null ){
			previousNode = currentNode;
			currentNode = previousNode.next;
		}
		currentNode.next = newNode;
		return newNode;	
	}

	public void add(String val){
		if(val == null)
			return;
		Node node = new Node(val);
		if(head == null){
			head = node;
			return;
		}
		node.next = head;
		head = node;
		size++;
	}		
	
	class IntNode{
		IntNode next;
		int value;
		
		public IntNode(int val){
			value = val;
		}
		
		public String toString(){
			return String.valueOf(value);
		}
	}

	class Node{
		Node next;
		String value;
		
		public Node(String val){
			value = val;
		}
		
		public String toString(){
			return value;
		}
	}
}