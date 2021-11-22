import java.util.*;

public class Test{
	
	private Node root;

	public static void main(String[] args){
		Test t = new Test();
		//t.testMergeSortSearch();
		//t.testQuickSortSearch();
		/*System.out.println("New array size "+t.increaseArraySize());
		System.out.println("bucket position is "+t.bucketIndex(56));
		System.out.println("bucket position is "+t.bucketIndex(10));
		System.out.println("bucket position is "+t.bucketIndex(98));
		t.hashTest();
		System.out.println("\nTest add node");
		t.testAddAll();
		System.out.println("\nTest get node");
		t.testGet();
		//System.out.println("\nTest delete node");
		//t.testDelete();*/
		System.out.println("\nTest get parent");
		t.testGetParent();
	/*	System.out.println("\nTest insert -> add / rebalance");
		t.testInsert();
		System.out.println("\nTest block size");
		t.testBlockSize();
		System.out.println("\nTest int to byte string");
		t.testIntToByte();
		System.out.println("\nTest clone");
		t.testClone();
		System.out.println("\nTest ints in string buffer");
		t.testIntsInStringBuffer();
		t.testCanCross();*/
		t.testStatement();
	}
	
	void testStatement(){
		System.out.println( 3 > 2 ? 3 : 2 );
	}
	
	void testCanCross(){
		int[] stones = {0,1,3,5,6,8,12,17};
		canCross(stones);
		int[] stones2 = {0,1,2,3,4,8,9,11};
		canCross(stones2);
	}
	
	public boolean canCross(int[] stones) {
        HashMap<Integer, Set<Integer>> map = new HashMap<>();
        for (int i = 0; i < stones.length; i++) {
            map.put(stones[i], new HashSet<Integer>());
        }
        map.get(0).add(0);
        for (int i = 0; i < stones.length; i++) {
            for (int k : map.get(stones[i])) {
				System.out.println("i = "+i+" k = "+k);
                for (int step = k - 1; step <= k + 1; step++) {
                   if (step > 0 && map.containsKey(stones[i] + step)) {
                        map.get(stones[i] + step).add(step);
						System.out.println("added "+step+" to set with key "+(stones[i] + step));
                     }
                }
            }
        }
        return map.get(stones[stones.length - 1]).size() > 0;
    }
	
	
	void testIntsInStringBuffer(){
		int val = 4;
		int[] arr = {1,2,3};
		int[] newArr = Arrays.copyOf(arr, arr.length+1);
		
		int newValIndex = newArr.length - 1;
		int parentIndex =  (int) Math.floor((newValIndex - 1)/2);
		//System.out.println(parentIndex);
		//System.arraycopy(arr, 0, newArr, 0, arr.length - (arr.length - parentIndex));
		//newArr[parentIndex] = val;
		//System.arraycopy(arr, parentIndex, newArr, parentIndex + 1, arr.length - parentIndex);
		newArr[newArr.length-1] = val;
		while(val > newArr[parentIndex]){
			System.arraycopy(arr, 0, newArr, 0, arr.length - (arr.length - parentIndex));
			newArr[parentIndex] = val;
			System.arraycopy(arr, parentIndex, newArr, parentIndex + 1, arr.length - parentIndex);
			newValIndex = parentIndex;
			if(newValIndex > 0)
				parentIndex = (int) Math.floor((newValIndex - 1)/2);
		}
		System.out.println(Arrays.toString(newArr));
	}
	
	void testClone(){
		GraphNode graphRoot = null, newRoot = null;
		graphRoot = new GraphNode(0);
		GraphNode one = new GraphNode(1), two = new GraphNode(2), three = new GraphNode(3), four = new GraphNode(4);
		graphRoot.nextNodes.add(two);
		graphRoot.nextNodes.add(three);
		graphRoot.nextNodes.add(four);
		one.nextNodes.add(two);
		two.nextNodes.add(graphRoot);
		three.nextNodes.add(two);
		four.nextNodes.add(three);
		four.nextNodes.add(one);
		four.nextNodes.add(graphRoot);
		try{
		newRoot = (GraphNode)graphRoot.clone();
		}catch(Exception e){
			e.printStackTrace();
		}
		System.out.println(newRoot == graphRoot);
		System.out.println(newRoot.nextNodes.get(0) == graphRoot.nextNodes.get(0));
		
	}
	
	
	
	void testIntToByte(){
		System.out.println(1 << 3);
	}
	
	void testBlockSize(){
		//System.out.println(blockSize(3));
		System.out.println(blockSize(2));
		//System.out.println(blockSize(1));
	}
	
	private int blockSize(int inputLength){
	
		if(inputLength < 2)
			return 1;
		System.out.println("recurse"+inputLength);
		return inputLength * blockSize(inputLength - 1);
	}
	
	
	void testInsert(){
		int[] values = {50,20,30,10,60,90,80,70,40};
		insert(50);
		insert(20);
		//printInOrder(this.root);
		insert(30);
		
	}
	
	public void insert(int key){
			this.root = add(root, key);
			//printInOrder(this.root);
			//System.out.println();
			//rebalanceInsert(key);
	}
	
	private void rebalanceInsert(int key){
		if(this.root.key == key)
			this.root.colour = 'B';
		else{
			Node inserted = get(this.root, key);
			Node parent = getParent(root, inserted.key);
			Node grandParent = getParent(root, parent.key);
			Node greatGrandParent = null;
			if(grandParent != null)
				greatGrandParent = getParent(root, grandParent.key);
			System.out.println("Node inserted was "+inserted+" : its parent is "+parent+" : its grandparent is "+grandParent);
			if(parent.colour == 'R'){
				Node sibling = getSibling(parent, grandParent);
				System.out.println("Node "+inserted+"'s parent "+parent+"'s sibling is "+sibling);
				if(sibling == null || sibling.colour == 'B'){
					System.out.println("Sibling is null or black so restructure");
					restructure(inserted, parent, grandParent, greatGrandParent, sibling);
				}else if(sibling == null || sibling.colour == 'R'){
					System.out.println("Sibling is null or red so recolour and recurse if double red");
				}
			}
		}
	}
	
	private void printInOrder(Node root){
		if(root != null){
			printInOrder(root.left);
			System.out.print(root);
			printInOrder(root.right);
		}
	}
	
	private void restructure(Node inserted, Node parent, Node grandParent, Node greatGrandParent, Node sibling){
		//nothing to restructure
		if(inserted.key == parent.key)
			return;
		//assign parent as child to greatGrandParent if it exists
		if(greatGrandParent != null && greatGrandParent.left.key == grandParent.key)
			greatGrandParent.left = parent;
		else if(greatGrandParent != null && greatGrandParent.right.key == grandParent.key)
			greatGrandParent.right = parent;
		//balancing to the left i.e insertion was in right subtree 
		if(inserted.key > parent.key){
			Node temp = grandParent;
			temp.right = parent.left;
			parent.left = temp;
		//balancing to the right i.e insertion was in left subtree
		}else{
			Node temp = grandParent;
			temp.left = parent.right;
			parent.right = temp;
		}
	}
	
	
	private Node getSibling(Node parent, Node grandParent){
		if(parent == null || grandParent == null)
			return null;
		if(grandParent.right == parent)
			return grandParent.left;
		else
			return grandParent.right;
	}
	
	void testGetParent(){
		int[] values = {50,20,30,10,60,90,80,70,40};
		for(int i=0; i<values.length; i++){
			insert(values[i]);
		}
		for(int i=0; i<values.length; i++){
			System.out.println("Parent of "+values[i]+" is "+getParent(root, values[i]));
		}			

	}

	public Node getParent(Node root, int key){
		if(root == null)
			return root;
		//check if leaf if not check if left node equals value
		if(root.left != null && root.left.key == key)
			return root;
		//check if leaf if not check if right node equals value
		if(root.right != null && root.right.key == key)
			return root;
		//no match check left subtree if key smaller
		if(key < root.key)
			return getParent(root.left, key);
		//no match check right subtree if key smaller
		else
			return getParent(root.right, key);
	}
	
	void testDelete(){
		inOrder(root);
		System.out.println();
		delete(root, 9);
		delete(root, 3);
		delete(root, 5);
		inOrder(root);
	}
	
	
	
	public Node delete(Node root, int value){
		if(root == null)
			return root;
		//if value matches root key apply 1 of 3 cases
		if(root.key == value){
			//check if there are node duplicates
			if(root.count > 1)
				root.count--;
			else{
				//case 2: one child only in the node
				if(root.right == null)
					return root.left;
				if(root.left == null)
					return root.right;
				//case 3: two children
				//get the key value of the in order successor of the node to delete
				root.key = min(root.right);
				//delete the in order successor
				delete(root.right, root.key);
			}
		}else if(value < root.key){
			//look for value in left subtree
			root.left = delete(root.left, value);
		}else 
			//look for value in right subtree
			root.right = delete(root.right, value);
		//case 1:  leaf
		return root;
	}
	
	private int min(Node root){
		if(root == null)
			return Integer.MIN_VALUE;
		if(root.left == null)
			return root.key;
		return min(root.left);
	}
	
	void testGet(){
		System.out.println("\nNode with key 4 is: "+get(root, 4));
		System.out.println("\nNode with key 9 is: "+get(root, 9));
	}
	
	public Node get(Node root, int value){
		if(root == null || root.key == value)
			return root;
		if(root.key > value)
			return get(root.left, value);
		else
			return get(root.right, value);
	}
	
	void testAddAll(){
		int[] values = {50,20,30,10,60,90,80,70,40};
		addAll(values);
		inOrder(root);
	}
	
	public void addAll(int[] values){
		root = add(root, values[0]);
		if(values == null || values.length < 1)
			return;
		for(int i=1; i<values.length; i++)
			add(root, values[i]);
	}
	
	void testAdd(){
		root = add(root, 5);
		add(root, 4);
		add(root, 2);
		add(root, 3);
		add(root, 6);
		add(root, 9);
		add(root, 8);
		add(root, 5);
		inOrder(root);
	}
	
	public Node add(Node root, int value){
		if(root == null){
			root = new Node(value);
			return root;
		}else if(root.key == value){
			root.count++;
			return root;
		}else if(value < root.key)
			root.left = add(root.left, value);
		else
			root.right = add(root.right, value);
		return root;
	}
	
	public void hashTest(){
		int[] nums = new int[10];
		int[] add = {59382, 43, 6894, 500, 99, -58};
		for(int i=0; i<add.length; i++)
			//System.out.println(" "+hash(add[i]));
			nums[hash(add[i])] = add[i];
		System.out.println(Arrays.toString(nums));
	}
	
	private int hash(int value){
		
		return Math.abs(value % 10) ;
	}
	
	private int bucketIndex(int value){
		return (int) value / 10;
	}
	
	private double increaseArraySize(){
		return (double)0.71/0.5*31;
	}
	
	void testMergeSortSearch(){
		int count = 10000000;
		int[] arr = generateRandomArray(count);
		int value = arr[count/3];
		long startTime = System.currentTimeMillis();
		mergeSort(arr);
		binarySearch(value, arr);
		long endTime = System.currentTimeMillis();
		System.out.println("Duration to merge sort "+count+" items and find value "+value+" in them was: "+(endTime-startTime)+" milliseconds");
	}

	void testQuickSortSearch(){
		int count = 100000000;
		int[] arr = generateRandomArray(count);
		int value = arr[count/3];
		long startTime = System.currentTimeMillis();
		quickSort(0, arr.length-1, arr);
		binarySearch(value, arr);
		long endTime = System.currentTimeMillis();
		System.out.println("Duration to quick sort "+count+" items and find value "+value+" in them was: "+(endTime-startTime)+" milliseconds");
		

	}
	
	//O n log n
	//preferred for linked lists
	//stable - items of same value will appear in the same order
	public void mergeSort(int[] arr){
		if(arr == null)
			return;
		int length = arr.length;
		if(length < 2)
			return;
		int mid = length/2;
		int[] left = Arrays.copyOfRange(arr, 0, mid);
		int[] right = Arrays.copyOfRange(arr, mid, length);
		mergeSort(left);
		mergeSort(right);
		merge(arr, left, right);
	}
	
	void merge(int[] arr, int[] left, int[] right){
		if(arr == null || left == null || right == null)
			return;
		//array lengths
		int lenLeft = left.length, lenRight = right.length, len = arr.length;
		//declare and initialize pointers
		int i, j, k;
		i = j = k = 0;
		//cycle through left and right to start merging into array while both have values
		while(i < lenLeft && j < lenRight){
			if(left[i] < right[j]){
				arr[k] = left[i];
				i++;
			}else{
				arr[k] = right[j];
				j++;
			}
			k++;
		}
		//deal with left overs in left
		while(i < lenLeft){
			arr[k] = left[i];
			i++;
			k++;
		}
		//deal with left overs in right
		while(j < lenRight){
			arr[k] = right[j];
			j++;
			k++;
		}
	}
	
	//O log n
	public int binarySearch(int value, int[] arr){
		//declare indice
		int lowIndex = 0, midIndex = 0, highIndex = arr.length-1;
		//loop to bring outer indices closer to mid index
		while(lowIndex <= highIndex){
			//initialize mid index per cycle
			midIndex = (highIndex+lowIndex)/2;
			//bring left index closer to mid index if value is smaller
			if(arr[lowIndex] < value)
				lowIndex = midIndex+1;
			//opposite from the high index
			else if(arr[highIndex] > value)
				highIndex = midIndex-1;
			else
				break;
		}
		return midIndex;
	}
	
	//0 n log n
	//preferred for arrays 
	//not stable - items of same value won't necessarily appear in the same order
	public void quickSort(int left, int right, int[] arr){
		//check if sorted already
		if((right - left) <= 0)
			return; //sorted
		else{//not sorted
			//set pivot value to right value at far right array
			int pivot = arr[right];
			//use the pivot value to sort left and right of the pivot and return new pivot location when you can't go anymore
			int pivotLocation = partition(left, right, pivot, arr);
			//sort left recursive
			quickSort(left, pivotLocation-1, arr);
			//sort right recursive
			quickSort(pivotLocation+1, right, arr);
		}
	}
	
	int partition(int left, int right, int pivot, int[] arr){
		//create pointers
		int leftPointer = left-1, rightPointer = right;
		//create endless loop to move pointers around with break condition to exit
		while(true){
			//shift left pointer towards pivot value
			while(arr[++leftPointer] < pivot)
				;
			//shift right pointer towards pivot value
			while(rightPointer > 0 && arr[--rightPointer] > pivot)
				;
			//pointers cannot pass each other break loop
			if(leftPointer >= rightPointer)
				break;
			//pivot - swap values at left and right pointers and continue while you can
			swap(leftPointer, rightPointer, arr);
		}
		//can't go anymore make pivot far right value and return
		swap(leftPointer, right, arr);
		return leftPointer;
	}
	
	void swap(int one, int two, int[] arr){
		int temp = arr[one];
		arr[one] = arr[two];
		arr[two] = temp;
	}
	
	public int[] generateRandomArray(int size) {
		int[] arr = new int[size];
		for (int i = 0; i < size; i++) {
			// Generate a random array with values between
			// int min and int max
			arr[i] = (int) (Math.random() * 1000) + 10;
		}
		return arr;
	}
	
	public void inOrder(Node root){
		if(root == null)
			return;
		inOrder(root.left);
		System.out.print(root);
		inOrder(root.right);
	}

	class GraphNode implements Cloneable{
		int key;
		ArrayList<GraphNode> nextNodes;
		
		public GraphNode(int k){
			key = k;
			nextNodes = new ArrayList<>();
		}
		
		protected Object clone() throws CloneNotSupportedException{
			try{
				return super.clone();
			}catch(CloneNotSupportedException e){
				throw e;
			}
		}
		
		public String toString(){
			String s = "";
			for(GraphNode n : nextNodes)
				s += n.key;
			return key+"["+s+"] ";
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
			return ""+key+"("+count+")"+colour+" ";
		}
	}
}

