import java.util.*;

public class AllHeaps{
	
	public static void main(String[] args){
		new NodeArrayMaxHeap().test();
	//	new MinHeapPriorityQueue().test();
	}
}

class MinHeapPriorityQueue{
	PriorityQueue<Integer> heap = new PriorityQueue<Integer>();
	
	void test(){
		add(-23423);
		add(0);
		add(9879);
		add(-22);
		System.out.println(Arrays.toString(heap.toArray()));
		System.out.println(heap.peek());
		System.out.println(heap.remove());
		System.out.println(Arrays.toString(heap.toArray()));
		heap.add(-343);
		System.out.println(Arrays.toString(heap.toArray()));
		System.out.println(heap.remove(-22));
		System.out.println(Arrays.toString(heap.toArray()));
	}
	
	public boolean remove(int value){
		return heap.remove(value);
	}
	
	public Integer remove(){
		return heap.remove();
	}
	
	public void add(int value){
		heap.add(value);
	}
	
	public Integer peek(){
		return heap.peek();
	}
}

class NodeArrayMaxHeap{
	
	Node[] heap = new Node[20];
	int size = 0;
	
	public void test(){
		Node one = new Node(1), two = new Node(2), three = new Node(3), nine = new Node(9), four = new Node(4), seven = new Node(7);
		insert(two);
		printHeap();
		insert(three);
		printHeap();
		insert(one);
		printHeap();
		 insert(four);
		printHeap();
		insert(nine);
		printHeap();
		insert(seven);
		printHeap();
	/*	System.out.println("Deleted node "+delete(one));
		printHeap();
		System.out.println("Deleted node "+delete(nine));
		printHeap();
		System.out.println("Deleted node "+delete(three));
		printHeap();*/
		System.out.println("Sort heap ");
		sort();
		printHeap();
	}
	
	
	/*public void sort(){
		if(heap == null || size < 2)
			return;
		int counter = size-1;
		for(int i=0; i<counter; i++){
			Node temp = heap[0];
			heap[0] = heap[counter - i];
			heap[counter - i] = temp;
			
			fixHeapBelow(0, counter - i - 1);
		}
	}*/
	
	//O(nlogn)
	public void sort(){
		if(heap == null || size < 2)
			return;
		int counter = size-1;
		//continue until the entire heap is dealt with
		while(counter > 0){
			//swap largest value (root) with last element in heap
			Node temp = heap[0];
			heap[0] = heap[counter];
			heap[counter] = temp;
			//decrement counter to exclude the last node when we heapify
			counter--;
			fixHeapBelow(0, counter);
			//after heapify the next largest node is at root.
			//continue until entire heap is rearranged
		}
	}
	
	private void printHeap(){
		for(int i=0; i<size; i++){
		System.out.print(heap[i]+" ");
		}
		System.out.println();
	}
	
	public Node delete(Node node){
		if(node == null || size == 0)
			return null;
		int nodeIndex = getNodeIndex(node);
		int rightChildIndex = 0, leftChildIndex = 0, largestChildIndex = rightChildIndex;
		
		if(nodeIndex == Integer.MAX_VALUE)
			return null;
		int parentIndex = getParentIndex(nodeIndex);
		//replace heap[nodeIndex] with the value at the position that is right most in heap
		heap[nodeIndex] = heap[size-1];
		//if root or the node is less than its parent fix the heap below
		if(nodeIndex == 0 || nodeIndex < parentIndex)
			fixHeapBelow(nodeIndex, size);
		//else fix the heap above the current node
		else
			fixHeapAbove(node, nodeIndex);
		//resize
		//heap = Arrays.copyOf(heap, heap.length-2);
		decrementHeapSize();
		return node;
	} 
	
	private void fixHeapBelow(int index, int lastHeapIndex){
		int largestChildIndex;
		while(index <= lastHeapIndex){
			largestChildIndex = getLargestChildIndex(getLeftChildIndex(index), getRightChildIndex(index));
			//has a largest child
			if(largestChildIndex < size - 1){
				//swap if child is larger than parent
				if(heap[index].key < heap[largestChildIndex].key){
					Node temp = heap[index];
					heap[index] = heap[largestChildIndex];
					heap[largestChildIndex] = temp;
				}else
					//break out of the loop if the child is less than parent
					break;
				//repeat the heapify process by reassigning pointers to look further down the array
				index = largestChildIndex;
			}else
				//has no children so no need to fix anything - break out of the loop
				break;
		}
	}
	
	private int getNodeIndex(Node node){
		if(node == null)
			return Integer.MAX_VALUE;
		int nodeIndex = 0;
		for(int i=0; i < size; i++){
			if(node.key == heap[i].key){
				 nodeIndex = i;
				 break;
			}
		}
		return nodeIndex;
	}
	
	private int getLargestChildIndex(int leftChildIndex, int rightChildIndex){
		//both indices are in array bounds return max integer
		if(leftChildIndex < size - 1 && rightChildIndex < size - 1)
			return (heap[leftChildIndex].key > heap[rightChildIndex].key ? leftChildIndex : rightChildIndex);
		//one child only as right is out of array bounds return left.  
		//It is not possible to have a complete tree with a right child without a left child
		else if(rightChildIndex > size - 1)
			return leftChildIndex;
		else
			//no children both indices are out of bounds
			return Integer.MAX_VALUE;
		
	}
	
	private int getParentIndex(int nodeIndex){
		if(nodeIndex > 0)
			return (int) Math.floor((nodeIndex - 1)/2);
		else
			return 0;
		
	}
	
	private int getLeftChildIndex(int parentIndex){
		return 2 * parentIndex + 1;
	}

	private int getRightChildIndex(int parentIndex){
		return 2 * parentIndex + 2;
	}
	
	private void incrementHeapSize(){
		//heap = Arrays.copyOf(heap, heap.length+1);
		size++;
	}
	
	private void decrementHeapSize(){
		//heap = Arrays.copyOf(heap, heap.length+1);
		size--;
	}

	private void fixHeapAbove(Node node, int newNodeIndex){
		//look up the heap to compare parent value with child value
		int parentIndex =  getParentIndex(newNodeIndex);
		//continue while child is greater than parent and not the root
		while(node.key > heap[parentIndex].key && newNodeIndex != 0){
			//move lesser values to the right to accomodate the larger new node
			System.arraycopy(heap, parentIndex, heap, parentIndex + 1, newNodeIndex - parentIndex);
			heap[parentIndex] = node;
			//reassign pointers to continue the process if necessary
			newNodeIndex = parentIndex;
			if(newNodeIndex > 0)
				parentIndex = getParentIndex(newNodeIndex);
		}		
	}
	
	//2,3,1,4,9,7
	public void insert(Node node){
		if(heap == null || node == null) 
			return;
		if(size < 1){
			//add one to the heap length
			incrementHeapSize();
			heap[0] = node;
		}else{
			incrementHeapSize();	
			//add one to the heap length
			//add new node to right end of the heap
			int newNodeIndex = size - 1;
			heap[newNodeIndex] = node;
			//manipulate the heap to enforce heap rules if the new addition has broken them
			fixHeapAbove(node, newNodeIndex);
		}
	}
	

	
	class Node{
		int key, count = 1;
		Node left, right;
		char colour = 'R';
		
		public Node(int k){
			key = k;
		}

		public Node(int k, char col){
			this(k);
			colour = col;
		}
		
		public String toString(){
			return ""+key+"";
		}
	}
		
}