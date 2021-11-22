import java.util.*;

public class AmazonQuestions{
	Node root;
	GraphNode graphRoot;
	
	public static void main(String[] args){
		  AmazonQuestions aq = new AmazonQuestions();
		//  aq.testOneMissingNumberInArray();
		//  aq.testTwoSumOfTwoIntegersEqualToGivenValue();
		//  aq.testThreeMergeTwoLinkedLists();
		//  aq.testFiveLevelOrderTraversalOfBinaryTree();
		//  aq.testSixIsBinarySearchTree();
		//  aq.testDfsSevenStringSegmentation();
		//  aq.testEightReverseWordsString();
		//  aq.testNineMakeChange();
		//	aq.testTenFindKthPermutation();
		//	aq.testFindSubsetsOfGivenSet();
		//	aq.testBalanceBraces();//subsets
		//	aq.testCloneDirectedGraph();
		//	aq.testFindLowHighIndex();
		//	aq.testSearchRotatedArray();
		//	aq.testBinaryTreeToDLL();
		//	aq.testCanCross();//frog hops
		//	aq.testMaxPathSum();
		//	aq.testDeleteBSTNode();
		//	aq.testAddBSTNode();
		//	aq.testFindBSTNode();
		//	aq.testTowersOfHanoiIterative();
		//	aq.testFindHappyNumber();//always results in 1
		//	aq.testLoopExists();//cycle in circular array 
		//	aq.testFindAverages();//of all subsets of size 'k' - sliding window in array or linked list
		//	aq.testFindLongestSubstringWithDistinctChars();//sliding window
		//	aq.testFindPermutations();//of given int[] - subsets
		//	aq.testDiffWaysToEvaluateExpression();//e.g. 1+2*3
			aq.testCalculateFibonacci();//kth fibonacci top down with memoization
			aq.testCalculateFibonacciTab();//kth fibonacci bottom up with tabulation
			aq.testSolveKnapsack();//solve knapsack bottom up with tabulation
	}
	
	void testSolveKnapsack(){
		 int[] profits = {1, 6, 10, 16};
		int[] weights = {1, 2, 3, 5};
		int maxProfit = solveKnapsack(profits, weights, 7);
		System.out.println("Total knapsack profit ---> " + maxProfit);
		maxProfit = solveKnapsack(profits, weights, 6);
		System.out.println("Total knapsack profit ---> " + maxProfit);
	}
	
	 public int solveKnapsack(int[] profits, int[] weights, int capacity) {
		// basic checks
		if (capacity <= 0 || profits.length == 0 || weights.length != profits.length)
		  return 0;

		int n = profits.length;
		int[][] dp = new int[n][capacity + 1];

		// populate the capacity=0 columns, with '0' capacity we have '0' profit
		for(int i=0; i < n; i++)
		  dp[i][0] = 0;

		// if we have only one weight, we will take it if it is not more than the capacity
		for(int c=0; c <= capacity; c++) {
		  if(weights[0] <= c)
			dp[0][c] = profits[0];
		}

		// process all sub-arrays for all the capacities
		for(int i=1; i < n; i++) {
		  for(int c=1; c <= capacity; c++) {
			int profit1= 0, profit2 = 0;
			// include the item, if it is not more than the capacity
			if(weights[i] <= c)
			  profit1 = profits[i] + dp[i-1][c-weights[i]];
			// exclude the item
			profit2 = dp[i-1][c];
			// take maximum
			dp[i][c] = Math.max(profit1, profit2);
		  }
		}

		// maximum profit will be at the bottom-right corner.
		return dp[n-1][capacity];
	}
	
	void testCalculateFibonacciTab(){
		System.out.println("5th Fibonacci is ---> " + calculateFibonacciTab(5));
		System.out.println("6th Fibonacci is ---> " + calculateFibonacciTab(6));
		System.out.println("7th Fibonacci is ---> " + calculateFibonacciTab(7));
	}
	
	/*
		Bottom-up with Tabulation
		Tabulation is the opposite of the top-down approach and avoids recursion. In this approach, we solve the problem bottom-up  by solving all the related sub-problems first). This is typically done by filling up an n-dimensional table. Based on the results in the table, the solution to the top/original problem is then computed.

		Tabulation is the opposite of Memoization, as in Memoization we solve the problem and maintain a map of already solved sub-problems. In other words, in memoization, we do it top-down in the sense that we solve the top problem first (which typically recurses down to solve the sub-problems).

		Let’s apply Tabulation to our example of Fibonacci numbers. Since we know that every Fibonacci number is the sum of the two preceding numbers, we can use this fact to populate our table.
	*/
	
	public int calculateFibonacciTab(int n) {
		if (n==0) return 0;
		int dp[] = new int[n+1];

		//base cases
		dp[0] = 0;
		dp[1] = 1;

		for(int i=2; i<=n; i++)
		  dp[i] = dp[i-1] + dp[i-2];

		return dp[n];
	}
	
	void testCalculateFibonacci(){
		System.out.println("5th Fibonacci is ---> " + calculateFibonacci(5));
		System.out.println("6th Fibonacci is ---> " + calculateFibonacci(6));
		System.out.println("7th Fibonacci is ---> " + calculateFibonacci(7));
	}
	
	/*
		Top-down with Memoization#
		In this approach, we try to solve the bigger problem by recursively finding the solution to smaller sub-problems. Whenever we solve a sub-problem, we cache its result so that we don’t end up solving it repeatedly if it’s called multiple times. Instead, we can just return the saved result. This technique of storing the results of already solved subproblems is called Memoization.

		We’ll see this technique in our example of Fibonacci numbers. First, let’s see the non-DP recursive solution for finding the nth Fibonacci number:
	*/
	 public int calculateFibonacci(int n) {
		if(n < 2)
		  return n;
		return calculateFibonacci(n-1) + calculateFibonacci(n-2);
	}
	
	void testDiffWaysToEvaluateExpression(){
		List<Integer> result = diffWaysToEvaluateExpression("1+2*3");
		System.out.println("\nExpression evaluations: " + result);

		result = diffWaysToEvaluateExpression("2*3-4-5");
		System.out.println("Expression evaluations: " + result);
	}
	
	/*
		Given an expression containing digits and operations (+, -, *), find all possible ways in which the expression can be evaluated by grouping the numbers and operators using parentheses.
		
			We can iterate through the expression character-by-character.
			we can break the expression into two halves whenever we get an operator (+, -, *).
			The two parts can be calculated by recursively calling the function.
			Once we have the evaluation results from the left and right halves, we can combine them to produce all results.
			
			TC O(n2^n)  SC O(2^n)
	*/
	
	public static List<Integer> diffWaysToEvaluateExpression(String input) {
		List<Integer> result = new ArrayList<>();
		// base case: if the input string is a number, parse and add it to output.
		if (!input.contains("+") && !input.contains("-") && !input.contains("*")) {
		  result.add(Integer.parseInt(input));
		} else {
		  for (int i = 0; i < input.length(); i++) {
			char chr = input.charAt(i);
			if (!Character.isDigit(chr)) {
			  // break the equation here into two parts and make recursively calls
			  List<Integer> leftParts = diffWaysToEvaluateExpression(input.substring(0, i));
			  List<Integer> rightParts = diffWaysToEvaluateExpression(input.substring(i + 1));
			  for (int part1 : leftParts) {
				for (int part2 : rightParts) {
				  if (chr == '+')
					result.add(part1 + part2);
				  else if (chr == '-')
					result.add(part1 - part2);
				  else if (chr == '*')
					result.add(part1 * part2);
				}
			  }
			}
		  }
		}
		return result;
	}
	
	void testFindPermutations(){
		List<List<Integer>> result = findPermutations(new int[] { 1, 3, 5 });
		System.out.print("\nHere are all the permutations: " + result);
	}
	
	/*
		Given a set of distinct numbers, find all of its permutations.
		
			Approach:
				Add first digit
				Add second digit in all possible positions around first digit
				Add third digit in all possible positions between each individual result of the prior step.
				Repeat third step for as many digits in set
				
				TC O(n*n)  SC O(n*n)
	*/
	
	 public List<List<Integer>> findPermutations(int[] nums) {
		List<List<Integer>> result = new ArrayList<>();
		Queue<List<Integer>> permutations = new LinkedList<>();
		permutations.add(new ArrayList<>());
		for (int currentNumber : nums) {
		  // we will take all existing permutations and add the current number to create new permutations
		  int n = permutations.size();
		  for (int i = 0; i < n; i++) {
			List<Integer> oldPermutation = permutations.poll();//remove from queue
			// create a new permutation by adding the current number at every position
			for (int j = 0; j <= oldPermutation.size(); j++) {
			  List<Integer> newPermutation = new ArrayList<Integer>(oldPermutation);
			  newPermutation.add(j, currentNumber);
			  if (newPermutation.size() == nums.length)
				result.add(newPermutation);
			  else
				permutations.add(newPermutation);
			}
		  }
		}
		return result;
	}
	
	void testFindLongestSubstringWithDistinctChars(){
		System.out.println("\nLength of the longest substring: " + findLongestSubstringWithDistinctChars("aabccbb"));
		System.out.println("Length of the longest substring: " + findLongestSubstringWithDistinctChars("abbbb"));
		System.out.println("Length of the longest substring: " + findLongestSubstringWithDistinctChars("abccde"));
	}
	
	//sliding windows
	/*
		Find the Longest Substring with Distinct Characters within a string
		use a HashMap to remember the last index of each character we have processed. Whenever we get a duplicate character, we will shrink our sliding window to ensure that we always have distinct characters in the sliding window.
	*/
	//TC O(n)  SC O(1)
	public int findLongestSubstringWithDistinctChars(String str) {
		int windowStart = 0, maxLength = 0;
		Map<Character, Integer> charIndexMap = new HashMap<>();
		// try to extend the range [windowStart, windowEnd]
		for (int windowEnd = 0; windowEnd < str.length(); windowEnd++) {
		  char rightChar = str.charAt(windowEnd);
		  // if the map already contains the 'rightChar', shrink the window from the beginning so that
		  // we have only one occurrence of 'rightChar'
		  if (charIndexMap.containsKey(rightChar)) {
			// this is tricky; in the current window, we will not have any 'rightChar' after its previous index
			// and if 'windowStart' is already ahead of the last index of 'rightChar', we'll keep 'windowStart'
			windowStart = Math.max(windowStart, charIndexMap.get(rightChar) + 1);
		  }
		  charIndexMap.put(rightChar, windowEnd); // insert the 'rightChar' into the map
		  maxLength = Math.max(maxLength, windowEnd - windowStart + 1); // remember the maximum length so far
		}

		return maxLength;
	}
	//sliding window
	
	void testFindAverages(){
		double[] result = findAverages(5, new int[] { 1, 3, 2, 6, -1, 4, 1, 8, 2 });
		System.out.println("\nAverages of subarrays of size K: " + Arrays.toString(result));
	}
	
	/*
		In many problems dealing with an array (or a LinkedList), we are asked to find or calculate something among all the contiguous subarrays (or sublists) of a given size.
		
		Given an array, find the average of all contiguous subarrays of size ‘K’ in it.
		
		efficient way to solve this problem would be to visualize each contiguous subarray as a sliding window of ‘5’ elements. This means that we will slide the window by one element when we move on to the next subarray. To reuse the sum from the previous subarray, we will subtract the element going out of the window and add the element now being included in the sliding window. This will save us from going through the whole subarray to find the sum and, as a result, the algorithm complexity will reduce to O(N)O(N).
		algorithm for the Sliding Window approach
	*/
	//TC O(n)  SC O(1)
	public double[] findAverages(int K, int[] arr) {
		double[] result = new double[arr.length - K + 1];
		double windowSum = 0;
		int windowStart = 0;
		for (int windowEnd = 0; windowEnd < arr.length; windowEnd++) {
		  windowSum += arr[windowEnd]; // add the next element
		  // slide the window, we don't need to slide if we've not hit the required window size of 'k'
		  if (windowEnd >= K - 1) {
			result[windowStart] = windowSum / K; // calculate the average
			windowSum -= arr[windowStart]; // subtract the element going out
			windowStart++; // slide the window ahead
		  }
		}

		return result;
	}
	
	void testLoopExists(){
		System.out.println(loopExists(new int[] { 1, 2, -1, 2, 2 }));
		System.out.println(loopExists(new int[] { 2, 2, -1, 2 }));
		System.out.println(loopExists(new int[] { 2, 1, -1, -2 }));
	}
	/*
		We are given an array containing positive and negative numbers. Suppose the array contains a number ‘M’ at a particular index. Now, if ‘M’ is positive we will move forward ‘M’ indices and if ‘M’ is negative move backwards ‘M’ indices. You should assume that the array is circular which means two things:

		If, while moving forward, we reach the end of the array, we will jump to the first element to continue the movement.
		If, while moving backward, we reach the beginning of the array, we will jump to the last element to continue the movement.
		Write a method to determine if the array has a cycle
		
		Use Fast & Slow pointer method 
		start from each index of the array to find the cycle
		If a number does not have a cycle we will move forward to the next element. There are a couple of additional things we need to take care of:
			if the pointer points to the same element after the move, we have a one-element cycle. Therefore, we can finish our cycle search for the current element.
			the cycle should not contain both forward and backward movements. We will handle this by remembering the direction of each element while searching for the cycle. If the number is positive, the direction will be forward and if the number is negative, the direction will be backward. So whenever we move a pointer forward, if there is a change in the direction, we will finish our cycle search right there for the current element.
	*/
	//TC O(n^2) SC O(1)
	public boolean loopExists(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
		  boolean isForward = arr[i] >= 0; // if we are moving forward or not
		  int slow = i, fast = i;

		  // if slow or fast becomes '-1' this means we can't find cycle for this number
		  do {
			slow = findNextIndex(arr, isForward, slow); // move one step for slow pointer
			fast = findNextIndex(arr, isForward, fast); // move one step for fast pointer
			if (fast != -1)
			  fast = findNextIndex(arr, isForward, fast); // move another step for fast pointer
		  } while (slow != -1 && fast != -1 && slow != fast);

		  if (slow != -1 && slow == fast)
			return true;
		}

		return false;
	}

	  private int findNextIndex(int[] arr, boolean isForward, int currentIndex) {
		boolean direction = arr[currentIndex] >= 0;
		if (isForward != direction)
		  return -1; // change in direction, return -1

		int nextIndex = (currentIndex + arr[currentIndex]) % arr.length;
		if (nextIndex < 0)
		  nextIndex += arr.length; // wrap around for negative numbers

		// one element cycle, return -1 
		if (nextIndex == currentIndex)
		  nextIndex = -1;

		return nextIndex;
	  }

	//TC O(log n)  SC O(1)
	void testFindHappyNumber(){
		int num = 23;
		System.out.println(num+" is a happy number? : "+findHappyNumber(num));
		num = 12;
		System.out.println(num+" is a happy number? : "+findHappyNumber(num));
	}
	/*
		Any number will be called a happy number if, after repeatedly replacing it with a number equal to the sum of the square of all of its digits, leads us to number ‘1’. All other (not-happy) numbers will never reach ‘1’. Instead, they will be stuck in a cycle of numbers which does not include ‘1’.
	*/
	public boolean findHappyNumber(int num) {
		int slow = num, fast = num;
		do {
		  slow = findSquareSum(slow); // move one step
		  fast = findSquareSum(findSquareSum(fast)); // move two steps
		} while (slow != fast); // found the cycle

		return slow == 1; // see if the cycle is stuck on the number '1'
	}

	  private int findSquareSum(int num) {
			int sum = 0, digit;
			while (num > 0) {
			  digit = num % 10;
			  sum += digit * digit;
			  num /= 10;
			}
			return sum;
	  }
	
	/*
	1. Calculate the total number of moves required i.e. "pow(2, n) - 1" here n is number of disks.
	2. If number of disks (i.e. n) is even then interchange destination 
	   pole and auxiliary pole.
	3. for i = 1 to total number of moves:
		 if i%3 == 1:
		legal movement of top disk between source pole and 
			destination pole
		 if i%3 == 2:
		legal movement top disk between source pole and 
			auxiliary pole    
		 if i%3 == 0:
			legal movement top disk between auxiliary pole 
			and destination pole 
	*/
	
	void testTowersOfHanoiIterative(){
		int numDisks = 3;
		LinkedList<Integer> src = new LinkedList<Integer>();
		for(int i = numDisks; i > 0; i--)
			src.push(i);
		LinkedList<Integer> aux = new LinkedList<Integer>();
		LinkedList<Integer> dest = new LinkedList<Integer>();
		int moves = towersOfHanoiIterative(numDisks, src, aux, dest);
		System.out.println("dest populated as : "+Arrays.toString(dest.toArray(new Integer[dest.size()]))+" in "+moves+" moves");
	}
	//O (2^n) 
	public int towersOfHanoiIterative(int numDisks, LinkedList<Integer> src, LinkedList<Integer> aux, LinkedList<Integer> dest){
		//if no disks then no moves
		if(numDisks < 1)
			return 0;
		//if one disk then one move only 
		if(numDisks == 1){
			dest.push(src.pop());
			return 1;
		}
		//calculate the number of moves required to complete the task
		//by 2 ^ of number of disks - 1
		int numMoves = (int) Math.pow(2, numDisks) - 1;
		//if the number of disks is even then swap the destination and auxiliary poles
		if(numDisks % 2 == 0){
			LinkedList temp = dest;
			dest = aux;
			aux = temp;
		}
		//iterate for the number of moves moving top disk by number of moves modulus pole count logic 
		//result 1 = src & dest
		//result 2 = src & aux
		//result 3 = aux & dest
		for(int i=1; i <= numMoves; i++){
			//move smallest disk between src and dest in either direction
			if(i % 3 == 1){
				move(src, dest);
			//move smallest disk between src and aux in either direction
			}else if(i % 3 == 2){
				move(src, aux);
			//move smallest disk between aux and dest in either direction
			}else if(i % 3 == 0){
				move(aux, dest);
			}
		}
		return numMoves;
	}
	
	private void move(LinkedList<Integer> src, LinkedList<Integer> dest){
		if(src == null || dest == null)
			return;
		//if src pole is empty move disk from dest to src
		if(src.size() == 0)
			src.push(dest.pop());
		//if dest pole is empty move disk from src to dest
		else if(dest.size() == 0)
			dest.push(src.pop());
		//if src pole and dest pole both aren't empty move the smallest disk of the two poles to the other
		else if(src.peek() > dest.peek())
			src.push(dest.pop());
		else
			dest.push(src.pop());
	}
	
	void testFindBSTNode(){
		System.out.println("\nLooking for 40 in the tree: "+find(root, 40));
		System.out.println("\nLooking for 80 in the tree: "+find(root, 80));
		System.out.println("\nLooking for 140 in the tree: "+find(root, 140));
	}
	
	//O (log n)
	public Node find(Node root, int key){
		if(root == null)
			return root;
		//base case - root key matches so return the node
		if(root.key == key)
			return root;
		//if search key less than root go down left subtree looking for it
		if(key < root.key)
			return find(root.left, key);
		//if search key greater than root go down right subtree looking for it
		return find(root.right, key);
		//else not found in the tree
	}
	
	void testAddBSTNode(){
		root = new Node(50);
        root.left = new Node(30);
        root.right = new Node(70);
        root.left.left = new Node(20);
        root.left.right = new Node(40);
        root.right.right = new Node(80);
        root.right.left = new Node(60);
		inOrder(this.root);
		add(root, 10);
		System.out.println("\nNew tree in order = ");
		inOrder(root);
		add(root, 55);
		System.out.println("\nNew tree in order = ");
		inOrder(root);
	}
	
	//O(log n)
	public Node add(Node root, int key){
		//tree is empty.  Create root node
		if(root == null){
			root = new Node(key);
			return root;
		//tree isn't empty recursively check key value for positionining in left or right subtree
		}else if(key < root.key){
			root.left = add(root.left, key);
		}else if(key > root.key){
			root.right = add(root.right, key);
		}	
		return root;
	}
	
	void testDeleteBSTNode(){
		root = new Node(50);
        root.left = new Node(30);
        root.right = new Node(70);
        root.left.left = new Node(20);
        root.left.right = new Node(40);
        root.right.right = new Node(80);
        root.right.left = new Node(60);
		inOrder(this.root);
		delete(20);
		System.out.println("\nNew tree in order = ");
		inOrder(root);
	}
	
	 public Node delete(int key){
		 return delete(root, key);
	 }
	
	//delete a node in a binary search tree
	//O(log n)
	public Node delete(Node root, int key){
		if(root == null)
			return root;
		//key isn't the root key so go looking for the correct node
		if(key < root.key)
			root.left = delete(root.left, key);
		else if(key > root.key)
			root.right = delete(root.right, key);
		//key matches now determine how many children and what must be done accordingly
		else{
			//node has one child only
			if(root.left == null)
				return root.right;
			else if(root.right == null)
				return root.left;
			//node has two children
			//get in order successor's key value and assign to current node
			root.key = minValue(root.right);
			//delete the in order successor node 
			root.right = delete(root.right, root.key);
		}
		return root;
	}
	
	//O(log n)
	private int minValue(Node root){
		if(root == null)
			return -1;
		if(root.left == null)
			return root.key;
		return minValue(root.left);
	}
	
	//O(n)
	private void inOrder(Node root){
		if(root != null){
			inOrder(root.left);
			System.out.print(root);
			inOrder(root.right);
		}
	}
	
	
	//get maximum path sum between any two nodes in a binary tree
	void testMaxPathSum(){
		root = new Node(10);
        root.left = new Node(2);
        root.right = new Node(10);
        root.left.left = new Node(20);
        root.left.right = new Node(1);
        root.right.right = new Node(-25);
        root.right.right.left = new Node(3);
        root.right.right.right = new Node(4);
		this.preOrder(this.root);
		PathSum pathSum = new PathSum();
		int maxPathSumRoot = this.maxPathSum(this.root, pathSum);
		int maxPathSumNode = pathSum.value;
		System.out.println("\nMax path sum = "+Math.max(maxPathSumRoot, maxPathSumNode));
	}
	
	//O(n)
	public int maxPathSum(Node node, PathSum pathSum){
		if (node == null) 
			return 0;
		int maxPathSumRoot = Integer.MIN_VALUE, maxPathSumCurrentNode = Integer.MIN_VALUE;
		//find max left recursively seperately
        int left = maxPathSum(node.left, pathSum);
		//find max right recusively seperately
        int right = maxPathSum(node.right, pathSum);
		//we need to check if the max sum path exists via root or arbitrarily elsewhere in tree
		//max path sum from call with root
		maxPathSumRoot = Math.max(Math.max(left,right) + node.key, node.key);
		//max path sum from call with a node with no ancestors of root in max sum path
		maxPathSumCurrentNode = Math.max(maxPathSumRoot, left+right+node.key);
		pathSum.value = Math.max(pathSum.value, maxPathSumCurrentNode);
        return maxPathSumRoot;
	}
	
	
	/*Problem: 
	A frog is crossing a river. The river is divided into x units and at each unit there may or may not exist a stone. The frog can jump on a stone, but it must not jump into the water.
	Given a list of stones' positions (in units) in sorted ascending order, determine if the frog is able to cross the river by landing on the last stone. Initially, the frog is on the first stone and assume the first jump must be 1 unit.
	If the frog's last jump was k units, then its next jump must be either k - 1, k, or k + 1 units. Note that the frog can only jump in the forward direction.
	
	Approach: we make use of a hashmap map which contains key:value pairs such that key refers to the position at which a stone is present and value is a set containing the jumpsize which can lead to the current stone position. We start by making a hashmap whose keys are all the positions at which a stone is present and the values are all empty except position 0 whose value contains 0. Then, we start traversing the elements(positions) of the given stone array in sequential order. For the currentPosition, for every possible jumpsize in the value set, we check if currentPosition+newjumpsize exists in the map, where newjumpsize can be either jumpsize−1, jumpsize, jumpsize+1. If so, we append the corresponding value set with newjumpsize. We continue in the same manner. If at the end, the value set corresponding to the last position is non-empty, we conclude that reaching the end is possible, otherwise, it isn't.
	
	*/
	
	void testCanCross(){
		int[] stones = {0,1,3,5,6,8,12,17};
		System.out.println("Can cross "+Arrays.toString(stones)+"? : "+canCross(stones));
		int[] stones2 = {0,1,2,3,4,8,9,11};
		System.out.println("Can cross "+Arrays.toString(stones2)+"? : "+canCross(stones2));
	}
	
	//O(n^2)  //O(n^2)
	 public boolean canCross(int[] stones) {
		 //making a hashmap whose keys are all the positions at which a stone is present and the values are all empty except position 0 whose value contains 0
        HashMap<Integer, Set<Integer>> map = new HashMap<>();
        for (int i = 0; i < stones.length; i++) {
            map.put(stones[i], new HashSet<Integer>());
        }
        map.get(0).add(0);
		//traversing the elements(positions) of the given stone array in sequential order
        for (int i = 0; i < stones.length; i++) {
			//For the currentPosition, for every possible jumpsize in the value set, we check if currentPosition+newjumpsize exists in the map, where newjumpsize can be either jumpsize−1, jumpsize, jumpsize+1
            for (int k : map.get(stones[i])) {
				//for every possible jumpsize in the value set
                for (int step = k - 1; step <= k + 1; step++) {
					//check if currentPosition+newjumpsize exists in the map
                    if (step > 0 && map.containsKey(stones[i] + step)) {
						//If so, we append the corresponding value set with newjumpsize. We continue in the same manner.
                        map.get(stones[i] + step).add(step);
                    }
                }
            }
        }
		//If at the end, the value set corresponding to the last position is non-empty, we conclude that reaching the end is possible, otherwise, it isn't
        return map.get(stones[stones.length - 1]).size() > 0;
    }
	
	/*Given a Binary Tree (BT), convert it to a Doubly Linked List(DLL) In-Place. The left and right pointers in nodes are to be used as previous and next pointers respectively in converted DLL. The order of nodes in DLL must be the same as in Inorder for the given Binary Tree. The first node of Inorder traversal (leftmost node in BT) must be the head node of the DLL*/
	
	void testBinaryTreeToDLL(){
		Node root = new Node(10);
		root.right = new Node(7);
		root.left = new Node(2);
		root.left.left = new Node(8);
		root.left.right = new Node(4);
		LinkedList<Node> list = new LinkedList<>();
		binaryTreeToDLL(root, list);
		System.out.println(Arrays.toString(list.toArray()));
	}
	
	//O(n)
	public void binaryTreeToDLL(Node root, LinkedList<Node> list){
		if(root != null && list != null){
			binaryTreeToDLL(root.left, list);
			list.add(root);
			binaryTreeToDLL(root.right, list);
		}
	}
	
	
	
	void testSearchRotatedArray(){
		int[] arr = generateRandomArray(8);
		Arrays.sort(arr);
		int rotatePositions = 3;
		rotate(rotatePositions, arr);
		System.out.println(Arrays.toString(arr));
		System.out.println("Found "+searchRotatedArray(arr[5], arr, rotatePositions));
		System.out.println("Found "+searchRotatedArray(4587, arr, rotatePositions));
	}
	
	//O(n log n)
	public int searchRotatedArray(int value, int[] arr, int positions){
		if(arr == null || positions < 0 || positions > arr.length-1)
			return -1;
		//search in portion index 0 up to positions
		int[] left = Arrays.copyOfRange(arr, 0, positions-1);
		int leftResult = binarySearch(value, left);
		if(leftResult != -1)
			return leftResult;
		//search in portion index positions up to length - 1
		int[] right = Arrays.copyOfRange(arr, positions, arr.length-1);
		int rightResult = binarySearch(value, right);
		return rightResult;
		
	}
	
	//O(log n)
	private int binarySearch(int value, int[]arr){
		if(arr == null || arr.length == 0)
			return Integer.MIN_VALUE;
		//declare indices used in binary search
		int left = 0, mid = 0, right = arr.length-1;
		//continue looping narrowing the search using recalculated mid and repositioned left or right
		while(left <= right){
			//calculate mid
			mid = (right + left) / 2;
			//check on low end first and reset left
			if(arr[left] < value)
				left = mid + 1;
			//else check on high end and reset right
			else if(arr[right] > value)
				right = mid - 1;
			//found it
			else
				break;
		}
		//we don't know if loop terminated or break kicked in so check if found or not
		if(arr[mid] == value)
			return arr[mid];
		else
			return -1;
	}
	
	//O(n) O(2n)
	private void rotate(int positions, int[] arr){
		if(positions < 1 || positions > arr.length-1)
			return;
		int[] left = new int[positions];
		int[] right = new int[arr.length - positions];
		//counter for indices during rotation
		int j = positions-1;
		//keeping the order by reverse reading the values at the end
		//and reversing adding them into the temp array.  These are 
		//the values that will rotate to the front of the rotated array
		for(int i=arr.length-1; i > arr.length-positions-1; i--){
			left[j] = arr[i];
			j--;
		}
		//keeping the order by forward reading the remainder of the values from the offset 
		// and forward adding them into the temporary array
		//these are the values that will rotate to the right of the array
		for(int i=0; i < arr.length-positions; i++){
			right[i] = arr[i];
		}
		//reset counter for populating original array with rotated indice values
		j = 0;
		
		//populate left part of the original array with the rotated values 
		for(int i=0; i<left.length; i++){
			arr[j] = left[j];
			j++;
		}
		//populate right part of the original array with the rotated values 
		for(int i=0; i < right.length; i++){
			arr[j] = right[i];
			j++;
		}
	}
	

	
	void testFindLowHighIndex(){
		int[] arr = {2, 2, 4, 4, 4, 6, 6,};
		System.out.println("Low : high index of value 2 is "+findLowHighIndex(2, arr));
		System.out.println("Low : high index of value 3 is "+findLowHighIndex(3, arr));
		System.out.println("Low : high index of value 4 is "+findLowHighIndex(4, arr));
		System.out.println("Low : high index of value 5 is "+findLowHighIndex(5, arr));
		System.out.println("Low : high index of value 6 is "+findLowHighIndex(6, arr));
		System.out.println("Low : high index of value 7 is "+findLowHighIndex(7, arr));
	}
	
	/*Find Low/High Index - RC O(log n), MC O(1)
Given a sorted array of integers, return the low and high index of the given key. You must return -1 if the indexes are not found. The array length can be in the millions with many duplicates.
	TEST:
	For input array [2,2,4,4,4,6,6] find the low and high indices for value 4
	Iteration	0	1	2	3	4
	low			0	3	2	2
	mid			0	2	1	1
	high		0	1	2	1
	Iteration	0	1	2	3	4	
	low			0	4	4	5
	mid			0	3	5	4
	high		6	6	4	4
	
	str		2 : 4
	*/
	//O(log n)
	public String findLowHighIndex(int value, int[] arr){
		//declare indice
		int lowIndex = 0, midIndex = 0, highIndex = arr.length-1;
		// search to find the start index of the value in the array
		while(lowIndex <= highIndex){
			//initialize mid index per cycle
			midIndex = (highIndex+lowIndex)/2;
			//bring low index closer to mid index if arr value is smaller than search value
			if(arr[midIndex] < value)
				lowIndex = midIndex+1;
			//bring high index closer to mid index if arr value is larger than or equal to search value
			else if(arr[midIndex] >= value)
				highIndex = midIndex-1;
			//we've exhausted the low index search
			else
				break;
		}
		//check index position and arr value at low index for validity/match if none assign -1 to low index
		if(lowIndex > arr.length-1 || arr[lowIndex] != value)
			lowIndex = -1;
		//store low index in a temporary holder
		int low = lowIndex;
		//reset indices and search to find the high index in the array
		lowIndex = 0; midIndex = 0; highIndex = arr.length-1;
		while(lowIndex <= highIndex){
			//initialize mid index per cycle
			midIndex = (highIndex+lowIndex)/2;
			//bring low index closer to mid index if arr value is smaller than or equal to search value
			if(arr[midIndex] <= value)
				lowIndex = midIndex+1;
			//bring high index closer to mid index if arr value is larger than search value
			else if(arr[midIndex] > value)
				highIndex = midIndex-1;
			else
				break;
		}
		//check index position and arr value at high index for validity/match if none assign -1 to high index
		if(highIndex > arr.length-1 || arr[highIndex] != value)
			highIndex = -1;
		
		return low+" : "+highIndex;
	}
	/* RC O(n), MC O(logn)
	Clone a Directed Graph
	Given the root node of a directed graph, clone this graph by creating its deep copy so that the cloned graph has the same vertices and edges as the original graph.
	 We are assuming that all vertices are reachable from the root vertex, i.e. we have a connected graph.
	TEST: [{0,[2,3,4]}, {1,[2]}, {2,[0]}, {3,[2]}, {4,[3,1,0]} ]
	
	root			0	0
	root			0	0
	current			0	0
	newRoot				0
	map []				{0,[2,3,4]}
	queue 			[0]	[2,3,4]
	explored []		[]	[0]
	edges []		[]	[2,3,4]
	clonedNodes []		[0,2,3,4]
	
	*/
	void testCloneDirectedGraph(){
		this.graphRoot = new GraphNode(0);
		GraphNode one = new GraphNode(1), two = new GraphNode(2), three = new GraphNode(3), four = new GraphNode(4);
		this.graphRoot.edges.add(two);
		this.graphRoot.edges.add(three);
		this.graphRoot.edges.add(four);
		one.edges.add(two);
		two.edges.add(this.graphRoot);
		three.edges.add(two);
		four.edges.add(three);
		four.edges.add(one);
		four.edges.add(this.graphRoot);
	//	cloneDirectedGraph(this.graphRoot);
		System.out.println(cloneDirectedGraph(this.graphRoot));
	}
	
	
	public GraphNode cloneDirectedGraph(GraphNode root){
		if(root == null)
			return root;
		GraphNode current = root, newRoot = null;
		//a map to hold original vertex and edges relationships
		Hashtable<GraphNode, Collection<GraphNode>> map = new Hashtable<GraphNode, Collection<GraphNode>>();
		//a queue to store the next nodes to be explored
		List<GraphNode> queue = new ArrayList<GraphNode>();
		//a list to hold the nodes already explored
		List<GraphNode> explored = new ArrayList<GraphNode>();
		//a list to hold all cloned nodes
		Set<GraphNode> clonedNodes = new HashSet<GraphNode>();
		//root is the first node to explore
		queue.add(root);
		//iterate through the queue removing nodes to obtain their children and adding them to explored
		//so that they aren't revisited. At the same time build up the new tree
		while(!queue.isEmpty()){
			GraphNode node = queue.remove(0);
			try{
				//set clone root
				if(node.key == root.key){
					newRoot = (GraphNode)node.clone();
					clonedNodes.add(newRoot);
				}
				//add current node to explored list
				explored.add(node);
				//declare a new array list that will hold the edges (cloned next nodes)
				List<GraphNode> edges = new ArrayList<GraphNode>();
				//add all next nodes of current node to the queue if they haven't been explored yet
				for(GraphNode element : node.edges){
					if(!explored.contains(element))
						queue.add(element);
					GraphNode edge = (GraphNode) element.clone();
					edges.add(edge);
					clonedNodes.add(edge);
				}
				//store the list in the map using the node as the key
				map.put(node, edges);
			}catch(CloneNotSupportedException e){
				return null;
			}
		}
		//iterate over the set of clones and add each ones edges retrieving those from the map
		Iterator it = clonedNodes.iterator();
		while(it.hasNext()){
			GraphNode node = (GraphNode)it.next();
			node.edges = (ArrayList<GraphNode>)map.get(node);
		}
		return newRoot;
	}
	
	private void printDirectedGraph(){
		
	}
	
	/* TC O(n2^n), MC O(n2^n)
	Print all braces combinations for a given value n so that they are balanced. For this solution, we will be using recursion.
	*/
	void testBalanceBraces(){
		String[] input = {"[","[","[","]","]","]"};
		String[] result = new String[input.length];
		int leftCount = 0, rightCount = 1;
		balanceBraces(input, result, leftCount, rightCount);
		//System.out.println(Arrays.toString(result));
		for(String a: result)
			System.out.print(a);
		
	}
	
	/*
		Approach:
			Rules:
			Dont add more than n open parentheses
			Add close ] only after adding [ open
				Start empty and add [ and ]
				Each step take all combinations of previous step and add [ or ] according to rules
				
				TC O(n2^n)  SC O(n2^n)
	*/
	
	private void balanceBraces(String[] input, String[] result, int leftCount, int rightCount){
		if(input == null || input.length < 2 || input.length != result.length)
			return;
		if(leftCount >= result.length && rightCount >= result.length)
			return;
		if(leftCount < result.length){
			result[leftCount] = input[0];
			balanceBraces(input, result, leftCount + 2, rightCount);
		}
		if(rightCount < result.length){
			result[rightCount] = input[input.length-1];
			balanceBraces(input, result, leftCount, rightCount + 2);
		}
			
	}
		
	/* RC O(2^n * n),  MC O(2^n * n)
	We are given a set of integers and we have to find all the possible subsets of this set of integers. The following example elaborates on this further.
	

	*/
	
	//iterative and using bit shift
	void testFindSubsetsOfGivenSet(){
		List<List<Integer>> results = new ArrayList<>();
		int[] arr = {2,3,4};
		findSubsetsOfGivenSet(arr);//, results);
		System.out.println(Arrays.toString(results.toArray()));
		
	}
	//iterative and using bit shift
	public void findSubsetsOfGivenSet(int[] arr){//, List<List<Integer>> results){
		//base case
		if(arr == null || arr.length < 2)
			return;
		int length = arr.length;
		//permutations count by 2 ^ array length or bit shift 1 << array length
		int permutations = (int) Math.pow(2, length);
        // loop printing all 2^length subsets
        for (int i = 0; i < permutations; i++)//(1<<length); i++)
        {
            System.out.print("[");
 
            // Print current subset
            for (int j = 0; j < length; j++)
 
                // (1<<j) is a number with jth bit 1
                // so when we 'and' them with the
                // subset number we get the numbers
                // which are present in the subset and which
                // are not
                if ((i & (1 << j)) > 0)
                    System.out.print(" "+arr[j] + " ");
 
            System.out.print("],");
        }

		
	}
	
	private int getBit(int i, int j) {
  	  int temp = (1 << j);
	  temp = temp & i;
	  if (temp == 0) {
    	  return 0;
  	  }
	  return 1;
	}
	
	void testTenFindKthPermutation(){
		int[] arr = {1,2,3};
		List<Integer> input = new ArrayList<>();
		StringBuilder sb = new StringBuilder();
		for(int i: arr)
			input.add(i);
		tenFindKthPermutation(input, 4, sb);
		System.out.println(sb.toString());
	}
	
	/*
	Given a set of ‘n’ elements, find their Kth permutation
	*/
	
	public void tenFindKthPermutation(List<Integer> input, int kth, StringBuilder result){
		if(input == null || input.size() < 1)
			return;
		//get block size recursively for current digit position
		int blockSize = blockSize(input.size() - 1);
		//get the index of the block that kth will be in and select first element of the block
		int blockIndex = (kth-1)/blockSize;
		//append the value to the result build
		result.append(input.get(blockIndex));
		//remove the value in the list at position blockIndex value as we have dealt with it
		input.remove(blockIndex);
		//use the difference between kth and the product of blockSize and blockIndex to skip the necessary blocks
		kth = kth - (blockSize * blockIndex);
		//recurse to handle the remaining digit positions in input
		tenFindKthPermutation(input, kth, result);
	}
	
	
	
	private int blockSize(int inputLength){
		//base case
		if(inputLength < 2)
			return 1;
		//recursively obtain factorial set 
		return inputLength * blockSize(inputLength - 1);
	}
	
	/*
	Suppose we have coin denominations of [1, 2, 5] and the total amount is 7. We can make change in the 6 ways
	*/
	public void testNineMakeChange(){
		int[] dens = {1,2,5};
		int amt = 7;
		System.out.println("There are "+nineMakeChange(amt, dens)+" ways to make change for amount "+amt);
	}

	public int nineMakeChange(int amount, int[] dens)
    {
		//declare the permutation array with to the size of amount + 1 to allow for 0 value coin.
        int permutation[] = new int[amount+1];
		//select no coin
        permutation[0] = 1;
		//iterate over the denominations to build permutations
        for (int i = 0; i < dens.length; i++)
        {	
			//build change permutation using denomination to amount using all dens up to current den 
            for (int j = dens[i]; j <= amount; j++) {
                permutation[j] += permutation[j - dens[i]];
            }
			System.out.println("permutation[amount] = "+permutation[amount]);
        }
 
        return permutation[amount];
    }
	
	/*
	Reverse the order of words in a given sentence (an array of characters).
	*/
	public void testEightReverseWordsString(){
		String s = "Hello World";
		System.out.println(s+" reversed is: "+eightReverseWordsString(s));
	}
	
	public String eightReverseWordsString(String s){
		//check input valid
		if(s == null || s.length()< 1)
			return "";
		String reversed = "";
		//reverse entire string in buffer
		StringBuffer sb = new StringBuffer(s);
		sb = sb.reverse();
		//split string into words around whitespace
		String[] words = sb.toString().split(" ");
		for(int i=0; i<words.length; i++){
			sb = new StringBuffer(words[i]).reverse();//reverse each string to natural word
			if(!(i == words.length-1))
				reversed = reversed.concat(sb.toString()+" ");//put spaces back
			else
				reversed = reversed.concat(sb.toString());//no space at the end
		}
		
		return reversed;
	}
	
	/*
	You are given a dictionary of words and a large input string. You have to find out whether the input string can be completely segmented into the words of a given dictionary.
	*/
	public void testDfsSevenStringSegmentation(){
		ArrayList<String> dict = new ArrayList();
		dict.add("apple");
		dict.add("apple");
		dict.add("pear");
		dict.add("pie");
		System.out.println("Can segment:  "+dfsSevenStringSegmentation("applepie", dict));
	}
	
	public boolean dfsSevenStringSegmentation(String s, ArrayList<String> dict){
		//check input valid
		if(s == null || s.length()< 2 || dict == null || dict.size() < 1)
			return false;
		boolean canSegment = false;
		for(int i=0; i<s.length(); i++){
			String firstWord = s.substring(0, i);
			String secondWord = s.substring(i, s.length());
			if(dict.contains(firstWord)){
				if(dict.contains(secondWord) || secondWord.length() == 0)
				{
					canSegment = true;
					break;
				}
				dfsSevenStringSegmentation(secondWord, dict);
			}
		}
		return canSegment;
	}
	
	/*
	Given a Binary Tree, figure out whether it’s a Binary Search Tree. In a binary search tree, each node’s key value is smaller than the key value of all nodes in the right subtree, and is greater than the key values of all nodes in the left subtree.
	*/
	public void testSixIsBinarySearchTree(){
		int arr[] = {5,8,9,4,6,3,1,7,2};
		this.addNodesRecursive(arr);
		this.preOrder(this.root);
		System.out.println("Is BST?:  "+sixIsBinarySearchTree(this.root,  Integer.MIN_VALUE, Integer.MAX_VALUE));
	}
	
	public boolean sixIsBinarySearchTree(Node node, int minKey, int maxKey){
        // base case
        if (node == null) {
            return true;
        }
		System.out.println("node = "+node.key+" min = "+minKey+" max = "+maxKey);
        // if the node's value falls outside the valid range
        if (node.key < minKey || node.key > maxKey) {
            return false;
        }
         // recursively check left and right subtrees with an updated range
        return sixIsBinarySearchTree(node.left, minKey, node.key) && sixIsBinarySearchTree(node.right, node.key, maxKey);
	}
	

	/*
	Given the root of a binary tree, display the node values at each level. Node values for all levels should be displayed on separate lines
	*/
	public void testFiveLevelOrderTraversalOfBinaryTree(){
		int arr[] = {5,8,9,4,6,3,1,7,2};
		this.addNodesRecursive(arr);
		fiveLevelOrderTraversalOfBinaryTree(root);
	}
	
	public void fiveLevelOrderTraversalOfBinaryTree(Node root){
		if(root == null)
			System.out.println("Empty tree");
		//create a list to hold nodes at each level
		List<Node> nodes = new ArrayList<>();
		//add all child nodes at each level to the list if not null and print the nodes at the same level
		nodes.add(root);
		while(!nodes.isEmpty()){
			//get count of nodes at current level
			int nodeCount = nodes.size();
			//work only with nodes at current level to print them out and add their non-null children to list
			while(nodeCount > 0){
				Node node = nodes.remove(0);
				System.out.print(node);
				if(node.left != null)
					nodes.add(node.left);
				if(node.right != null)
					nodes.add(node.right);
				nodeCount--;
			}
			//current level finished print return and start next level
			System.out.println();
		}
	}
	
	/*
	Given two sorted linked lists, merge them so that the resulting linked list is also sorted.
	*/
	public void testThreeMergeTwoLinkedLists(){
		LinkedList<Integer> lone = new LinkedList(Arrays.asList(1,3,5));
		LinkedList<Integer> ltwo = new LinkedList(Arrays.asList(2,4,6));
		System.out.println("Merged : "+threeMergeTwoLinkedLists(lone, ltwo));
		lone = new LinkedList(Arrays.asList(1,3,5,7,5,2));
		ltwo = new LinkedList(Arrays.asList(2,4,6,5));
		System.out.println("Merged : "+threeMergeTwoLinkedLists(lone, ltwo));
	}	
	
	public LinkedList<Integer> threeMergeTwoLinkedLists(LinkedList<Integer> one, LinkedList<Integer> two){
		if(one == null || one.size() < 1 || two == null || two.size() < 1)
			return null;
		//collection of merged and sorted integers
		LinkedList<Integer> merged = new LinkedList<Integer>();
		//Keep adding the lesser value from the 2 lists to merged and until one list is depleted and then deal with left overs in the other.  This keeps going until the list size is zero being constantly reduced by removing the smallest element.
		while(one.size() > 0 && two.size() > 0){
			if(one.peek() > two.peek())
				merged.add(two.remove());
			else
				merged.add(one.remove());
		}
		while(one.size() > 0)
			merged.add(one.remove());
		while(two.size() > 0)
			merged.add(two.remove());
		
		return merged;
	}
	
	/*
	Given an array of integers and a value, determine if there are any two integers in the array whose sum is equal to the given value. Return true if the sum exists and return false if it does not
	*/
	public void testTwoSumOfTwoIntegersEqualToGivenValue(){
		int[] test1 = {3,7,1,2,8,4,5};
		int val = 10;
		System.out.println("Two values meet target sum of "+val+": "+twoSumOfTwoIntegersEqualToGivenValue(test1, val));		
		val = 30;
		System.out.println("Two values meet target sum of "+val+": "+twoSumOfTwoIntegersEqualToGivenValue(test1, val));		
		val = -1;
		System.out.println("Two values meet target sum of "+val+": "+twoSumOfTwoIntegersEqualToGivenValue(test1, val));		
		}
	
	public boolean twoSumOfTwoIntegersEqualToGivenValue(int[] arr, int val){
		boolean sumEquals = false;
		//check if arr is null or empty 
		if(arr == null || arr.length < 1){
			return false;
		}
		//Scan given array and store values in HashSet at same time checking set for a value which is the difference between the given value and the inserted value
		HashSet<Integer> set = new HashSet();
		for(int i=0; i<arr.length; i++){
			if(set.contains(val - arr[i])){
				sumEquals = true;
				break;
			}
			set.add(arr[i]);
		}
		return sumEquals;
	}
	
	/*
	You are given an array of positive numbers from 1 to n, such that all numbers from 1 to n are present except one number x. You have to find x.
	*/
	public int oneMissingNumberInArray(int[] arr){
		int missingNumber = 0;
		//check if arr is null or empty
		if(arr == null || arr.length < 1){
			return 0;
		}
		//find the sum of elements in the array
		int sumOfOrignalArray = 0;
		for(int i=0; i<arr.length; i++){
			sumOfOrignalArray += arr[i];
		}
		//get the expected size of the array
		int actualLengthOfArray = arr.length+1;
		//find the expected sum of n numbers using arithmetic series sum
		int sumOfTheActualArray = (actualLengthOfArray * ( actualLengthOfArray + 1 ) ) / 2;
		//the difference between sum in orginal array and sum of elements in actual array is missing number
		missingNumber = sumOfTheActualArray - sumOfOrignalArray;
		
		return missingNumber;
	}
	
	public void testOneMissingNumberInArray(){
		int[] test1 = {3,7,1,2,8,4,5};
		System.out.println("Missing number is: "+oneMissingNumberInArray(test1));
	}
	
	public void addNodesRecursive(int[] nodes){
		root = addNodeRecursive(root, nodes[0]);
		for(int i=1; i<nodes.length; i++)
			insertRecursive(nodes[i]);
	}
	
	void insertRecursive(int key) {
		root = addNodeRecursive(root, key); 
	}
	
	public Node addNodeRecursive(Node root, int key){
       /* If the tree is empty,
          return a new node */
        if (root == null) {
            root = new Node(key);
            return root;
        }
 
        /* Otherwise, recur down the tree */
        if (key < root.key)
            root.left = addNodeRecursive(root.left, key);
        else if (key > root.key)
            root.right = addNodeRecursive(root.right, key);

        /* return the (unchanged) node pointer */
        return root;
	}
	
	private void preOrder(Node node){
		if(node != null){
			System.out.print(node);
			preOrder(node.left);
			preOrder(node.right);
		}
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
	
	class Node{
		int key;
		Node left, right, previous;
		
		public Node(int k){
			key = k;
		}
		
		public String toString(){
			return key+" ";
		}
	}

	class GraphNode implements Cloneable{
		int key;
		Collection<GraphNode> edges;
		
		public GraphNode(int k){
			key = k;
			edges = new ArrayList<>();
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
			for(GraphNode n : edges)
				s += n.key;
			return key+"["+s+"] ";
		}
	}	
}