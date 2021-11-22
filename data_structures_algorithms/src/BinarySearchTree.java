import java.util.*;

public class BinarySearchTree{
	Node root;
	
	public static void main(String[] args){
		BinarySearchTree bst = new BinarySearchTree();
	/*	bst.testAddNodeIterative();
		System.out.println("\nPre order traversal");
		bst.testPreOrderTraversalBst();
		System.out.println("\nPost order traversal");
		bst.testPostOrderTraversalBst();
		System.out.println("\nIn order traversal");
		bst.testInOrderTraversalBst();
		System.out.println("\nTest find node iterative");
		bst.testFindNodeIterative();
		System.out.println("\nTest find node recusive");
		bst.testFindNodeRecursive();
		System.out.println("\nTest remove node");
		bst.testRemove();
		System.out.println("\nTest max path sum");
		bst = new BinarySearchTree();
		bst.testMaxPathSum();
		System.out.println("\nTest level order traversal");
		bst = new BinarySearchTree();
		bst.testLevelOrderTraversal();
		System.out.println("\nTest calculate tree height");
		bst = new BinarySearchTree();
		bst.testGetTreeHeight();
		System.out.println("\nTest finding closest common ancestor");
		bst = new BinarySearchTree();
		bst.testFindClosestCommonAncestor();
		System.out.println("\nTest find in order predecessor");
		bst = new BinarySearchTree();
		bst.testFindInOrderPredecessor();
		System.out.println("\nTest find in order successor");
		bst = new BinarySearchTree();
		bst.testFindInOrderSuccessor();
		System.out.println("\nTest find kth smallest");
		bst = new BinarySearchTree();
		bst.testFindKthSmallest();
		System.out.println("\nTest find kth largest");
		bst = new BinarySearchTree();
		bst.testFindKthLargest();
		System.out.println("\nTest is BST");
		bst = new BinarySearchTree();
		bst.testIsBst();
		System.out.println("\nTest print tree levels");
		bst = new BinarySearchTree();
		bst.testPrintTreeLevelsRecursive();
		System.out.println("\nTest tree height is balanced");
		bst = new BinarySearchTree();
		bst.testTreeHeightIsBalanced();
		System.out.println("\nTest sorted array to balanced tree recursive");
		bst = new BinarySearchTree();
		bst.testSortedArrayToBalancedTreeRecursive();
		System.out.println("\nTest convert normal tree to BST");
		bst = new BinarySearchTree();
		bst.testConvertTreeToBST();
		System.out.println("\n");
		System.out.println("\nTest print tree levels iterative");
		bst = new BinarySearchTree();
		bst.testPrintTreeLevelsIterative();
		System.out.println("\n");
		System.out.println("Test add nodes recursively");
		bst = new BinarySearchTree();
		bst.testAddNodesRecursive();
		System.out.println("\n");
		bst = new BinarySearchTree();
		System.out.println("Test delete nodes recursively");
		bst.testDeleteNodeRecursive();		
		System.out.println("\n");
		System.out.println("Test get min value");
		bst.testMinValueRecursive();		
		System.out.println("\n");
		System.out.println("Test get max value");
		bst.testMaxValueRecursive();		
		System.out.println("\n");
		bst = new BinarySearchTree();
		System.out.println("\nTest balance tree");
		bst.testBalanceTree();		
		bst = new BinarySearchTree();
		System.out.println("\nTest get parent");
		bst.testGetParent();
		bst = new BinarySearchTree();
		System.out.println("\nTest get sibling");
		bst.testGetSibling();
		bst = new BinarySearchTree();
		bst.testZigZagLevelOrder();
		bst = new BinarySearchTree();
		bst.testLevelOrderSuccessor();
		bst = new BinarySearchTree();
		bst.testAllPathsTreePathsEqualToSum();*/
		bst = new BinarySearchTree();
		bst.testPathsEqualToSequence();
		
	}

	void testPathsEqualToSequence(){
		root = new Node(1);
		root.left = new Node(0);
		root.right = new Node(1);
		root.left.left = new Node(1);
		root.right.left = new Node(6);
		root.right.right = new Node(5);

		System.out.println("Tree has path sequence 1, 0, 7: " + pathsEqualToSequence(root, new int[] { 1, 0, 7 }, 0));
		System.out.println("Tree has path sequence 1, 1, 6: " + pathsEqualToSequence(root, new int[] { 1, 1, 6 }, 0));
	}
	
	private boolean pathsEqualToSequence(Node currentNode, int[] sequence, int sequenceIndex) {
		if (currentNode == null)
		  return false;

		if (sequenceIndex >= sequence.length || currentNode.key != sequence[sequenceIndex])
		  return false;

		// if the current node is a leaf, add it is the end of the sequence, we have found a path!
		if (currentNode.left == null && currentNode.right == null && sequenceIndex == sequence.length - 1)
		  return true;

		// recursively call to traverse the left and right sub-tree
		// return true if any of the two recursive call return true
		return pathsEqualToSequence(currentNode.left, sequence, sequenceIndex + 1)
			|| pathsEqualToSequence(currentNode.right, sequence, sequenceIndex + 1);
	}
	
	void testAllPathsTreePathsEqualToSum(){
		root = new Node(12);
		root.left = new Node(7);
		root.right = new Node(1);
		root.left.left = new Node(4);
		root.right.left = new Node(10);
		root.right.right = new Node(5);
		int sum = 23;
		List<List<Integer>> result = allTreePathsEqualToSum(root, sum);
		System.out.println("Tree paths with sum " + sum + ": " + result);
	}
	
	public List<List<Integer>> allTreePathsEqualToSum(Node root, int sum){
		List<List<Integer>> allPaths = new ArrayList<>();
		List<Integer> currentPath = new ArrayList<>();
		allTreePathsEqualToSumRecursive(root, sum, currentPath, allPaths);
		return allPaths;
	}
	
	private void allTreePathsEqualToSumRecursive(Node currentNode, int sum, List<Integer> currentPath,
      List<List<Integer>> allPaths) {
		if (currentNode == null)
		  return;

		// add the current node to the path
		currentPath.add(currentNode.key);

		// if the current node is a leaf and its value is equal to sum, save the current path
		if (currentNode.key == sum && currentNode.left == null && currentNode.right == null) {
		  allPaths.add(new ArrayList<Integer>(currentPath));
		} else {
		  // traverse the left sub-tree
		  allTreePathsEqualToSumRecursive(currentNode.left, sum - currentNode.key, currentPath, allPaths);
		  // traverse the right sub-tree
		  allTreePathsEqualToSumRecursive(currentNode.right, sum - currentNode.key, currentPath, allPaths);
		}

		// remove the current node from the path to backtrack, 
		// we need to remove the current node while we are going up the recursive call stack.
		currentPath.remove(currentPath.size() - 1);
  } 

	void testLevelOrderSuccessor(){
		int[] values = {50,20,30,10,60,90,80,70,40};
		addNodesRecursive(values);
		levelOrderSuccessor(root);
	}
	
	public void levelOrderSuccessor(Node root){
		if(root==null)
			return;
		int level = 0, zzIndex = 0;
		ArrayList<Node> queue = new ArrayList<>();
		queue.add(root);
		Node node = null, predecessor = null;
		while(!queue.isEmpty()){
			int nodeCount = queue.size();//count of all nodes at same level
			//deque all nodes at one level and enqueue all nodes at next level
			while(nodeCount > 0){
				node = queue.remove(0);
				if(predecessor != null)
					predecessor.levelOrderSuccessor = node;
				System.out.print(predecessor+" ");
				predecessor = node;
				if(node.left!=null)
					queue.add(node.left);
				if(node.right!=null)
					queue.add(node.right);
				nodeCount--;
			}
			predecessor = null;
			System.out.println();
		}		
	}
	
	void testZigZagLevelOrder(){
		int[] values = {50,20,30,10,60,90,80,70,40};
		addNodesRecursive(values);
		printTreeLevelsRecursive(root);
		ArrayList<Node> nodes = zigZagLevelOrder(root);
		Node[] nodeArr = new Node[nodes.size()];
		System.out.println(Arrays.toString(nodes.toArray(nodeArr)));
	}
	
	public ArrayList<Node> zigZagLevelOrder(Node root){
		if(root==null)
			return null;
		int level = 0, zzIndex = 0;
		ArrayList<Node> queue = new ArrayList<>(), zzNodes = new ArrayList<>();
		queue.add(root);
		Node n = null;
		while(!queue.isEmpty()){
			int nodeCount = queue.size();//count of all nodes at same level
			//deque all nodes at one level and enqueue all nodes at next level
			ArrayList<Node> levelNodes = new ArrayList<>();
			while(nodeCount > 0){
				n = queue.remove(0);
				if(level%2 == 1)
					levelNodes.add(n);
				else
					levelNodes.add(0, n);
				System.out.print(""+n.key+" ");
				if(n.left!=null)
					queue.add(n.left);
				if(n.right!=null)
					queue.add(n.right);
				nodeCount--;
			}
			zzNodes.addAll(levelNodes);
			level++;
			System.out.println();
		
		}
		return zzNodes;
	}

	void testGetSibling(){
		int[] values = {50,20,30,10,60,90,80,70,40};
		addNodesRecursive(values);
		printTreeLevelsRecursive(root);
		System.out.println("sibling of "+root.left+" is "+getSibling(root.left));
	}
	
	//O(log n)
	private Node getSibling(Node node){
		if(node == null)
			return node;
		Node parent = getParent(root, node.key);
		if(parent == null)
			return node;
		else
			return (node.key == parent.right.key) ? parent.left : parent.right;
	}
	
	void testGetParent(){
		int[] values = {50,20,30,10,60,90,80,70,40};
		addNodesRecursive(values);
		for(int i=0; i<values.length; i++){
			System.out.println("Parent of "+values[i]+" is "+getParent(root, values[i]));
		}			
	}
	
	//O(log n)
	public Node getParent(Node root, int key){
		if(root == null)
			return root;
		//check if left child is leaf if not check if left child equals value
		if(root.left != null && root.left.key == key)
			return root;
		//check if right child is leaf if not check if right child equals value
		if(root.right != null && root.right.key == key)
			return root;
		//no match recursively check left subtree if key is smaller
		if(key < root.key)
			return getParent(root.left, key);
		//no match recursively check right subtree if key is larger
		else
			return getParent(root.right, key);
	}
	
	void testBalanceTree(){
		int[] arr = {7,6,5,3,4,2,1,9,8,10};
		this.addNodesRecursive(arr);
		System.out.println(Arrays.toString(arr));
		inOrderTraversalBst(this.root);
		System.out.println("Tree height is balanced? "+getTreeHeightIsBalanced(root));
		balanceTree(root);
		inOrderTraversalBst(this.root);
		System.out.println("Tree height is balanced? "+getTreeHeightIsBalanced(root));
	}
	//Take an unbalanced BST and balance it
	//O(n)
	public void balanceTree(Node root){
		if(root == null)
			return;
		if(root.right == null && root.left == null)
			return; //tree balanced
		List<Integer> persisted = new ArrayList<Integer>();
		persistTreeInOrder(root, persisted);
		int[] sequence = new int[persisted.size()];
		for(int i=0; i<persisted.size(); i++)
			sequence[i] = persisted.get(i).intValue();
		this.root = sortedArrayToBalancedTreeRecursive(sequence, 0, sequence.length-1);
	}

	//merge two balanced trees to one.  Not including in order traversal of two original trees
	//to arrays and then merging them inorder.
	//O(n)
	void testSortedArrayToBalancedTreeRecursive(){
		int[] arr = {1,2,3,4,5,6,7,8,9};
		Node n = this.sortedArrayToBalancedTreeRecursive(arr, 0, arr.length-1);
		this.preOrderTraversalBst(n);
		System.out.println("\nIs BST? : "+this.isBST(n, Integer.MIN_VALUE, Integer.MAX_VALUE));
		System.out.println("Tree is height balanced? : "+this.getTreeHeightIsBalanced(this.root));
	}
	
	//if array is sorted then its midpoint should be the root to balance left and right subtrees
	//O(n)
	public Node sortedArrayToBalancedTreeRecursive(int[] arr, int start, int end){
		if(start > end || arr == null)
			return null;
		int mid = (start+end)/2;
		Node node = new Node(arr[mid]);
		node.left = sortedArrayToBalancedTreeRecursive(arr, start, mid-1);
		node.right = sortedArrayToBalancedTreeRecursive(arr, mid+1, end);
		return node;
	}
	
	//O(n)
	void testMaxValueRecursive(){
		int arr[] = {50,30,20,40,70,60,80};
		this.addNodesRecursive(arr);
		System.out.println(Arrays.toString(arr));
		System.out.println("Max value in tree built from arr is "+maxValueRecursive(this.root));
	}
	
	//O(n)
	public int maxValueRecursive(Node root){
		if(root == null)
			return Integer.MAX_VALUE;
		if(root.right == null)
			return root.key;
		return maxValueRecursive(root.right);
	}

	void testMinValueRecursive(){
		int arr[] = {50,30,20,40,70,60,80};
		this.addNodesRecursive(arr);
		System.out.println(Arrays.toString(arr));
		inOrderTraversalBst(root);
		System.out.println("Min value in tree built from arr is "+minValueRecursive(this.root));
	}
	
	//O(n)
	public int minValueRecursive(Node root){
		if(root == null)
			return Integer.MIN_VALUE;
		if(root.left == null)
			return root.key;
		return minValueRecursive(root.left);
	}
	
	void testDeleteNodeRecursive(){
		int arr[] = {50,30,20,40,70,60,80};
		this.addNodesRecursive(arr);
		this.inOrderTraversalBst(this.root);
		this.deleteKey(50);
		System.out.println();
		this.inOrderTraversalBst(this.root);
		this.deleteKey(30);
		System.out.println();
		this.inOrderTraversalBst(this.root);
		this.deleteKey(80);
		System.out.println();
		this.inOrderTraversalBst(this.root);
	}
    
	void deleteKey(int key) {
		root = deleteNodeRecursive(root, key); 
	}
 
	//O(log n)
    Node deleteNodeRecursive(Node root, int key)
    {
		//base case
		if(root == null)
			return root;
		//root isn't null and key isn't the root key
		if(key < root.key)
			root.left = deleteNodeRecursive(root.left, key);
		else if(key > root.key)
			root.right = deleteNodeRecursive(root.right, key);
		//root isn't null and key is root key = node to be deleted
		else{
			//has one child
			if(root.left == null)
				return root.right;
			else if(root.right == null)
				return root.left;
			//has two children so get in order successor -> min value in right subtree
			root.key = minValueRecursive(root.right);
			//delete the in order successor
			root.right = deleteNodeRecursive(root.right, root.key);
		}			
		return root;
    }
	
	
	//O(log n)
	int minValueIterative(Node root)
    {
        int minv = root.key;
        while (root.left != null)
        {
            minv = root.left.key;
            root = root.left;
        }
        return minv;
    }
 
	
	void testAddNodesRecursive(){
		int arr[] = {5,8,9,4,6,3,1,7,2};
		this.addNodesRecursive(arr);
		this.preOrderTraversalBst(this.root);
	}
	
	public void addNodesRecursive(int[] nodes){
		root = addNodeRecursive(root, nodes[0]);
		for(int i=1; i<nodes.length; i++)
			insertRecursive(nodes[i]);
	}
	
	void insertRecursive(int key) {
		root = addNodeRecursive(root, key); 
	}
	
	//O(log n)
	public Node addNodeRecursive(Node root, int key){
       /* If the tree is empty,
          return a new node */
        if (root == null) {
            root = new Node(key);
            return root;
        }
 
        /* Otherwise, recurse down the tree */
        if (key < root.key)
            root.left = addNodeRecursive(root.left, key);
        else if (key > root.key)
            root.right = addNodeRecursive(root.right, key);

        /* return the (unchanged) node pointer */
        return root;
	}

	void testPrintTreeLevelsIterative(){
		Node root = new Node(10);
		root.right = new Node(7);
		root.left = new Node(2);
		root.left.left = new Node(8);
		root.left.right = new Node(4);
		root.right.left = new Node(9);
		root.right.right = new Node(5);
		this.printTreeLevelsIterative(root);
	}
	//O(n)
	public void printTreeLevelsIterative(Node root){
		if(root==null)
			return;
		List<Node> queue = new ArrayList();
		queue.add(root);
		Node n = null;
		while(!queue.isEmpty()){
			int nodeCount = queue.size();//count of all nodes at same level
			//deque all nodes at one level and enqueue all nodes at next level
			while(nodeCount > 0){
				n = queue.remove(0);
				System.out.print(""+n.key+" ");
				if(n.left!=null)
					queue.add(n.left);
				if(n.right!=null)
					queue.add(n.right);
				nodeCount--;
			}
			System.out.println();
		}
	}
	
	//convert normal tree to binary search tree maintaining the original structure
	
	void testConvertTreeToBST(){
		Node root = new Node(10);
		root.right = new Node(7);
		root.left = new Node(2);
		root.left.left = new Node(8);
		root.left.right = new Node(4);
		this.printTreeLevelsIterative(root);
		System.out.println("Pre sort Is BST? : "+this.isBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE));
		System.out.println("Post sort Is BST? : "+this.isBST(this.convertPersistedTreeToBST(root), Integer.MIN_VALUE, Integer.MAX_VALUE));
		this.printTreeLevelsIterative(this.convertPersistedTreeToBST(root));
	}
	//sort collection O(n Log n) - add nodes O(n)
	public Node convertPersistedTreeToBST(Node root){
		if(root == null)
			return null;
		List<Integer> persisted = new ArrayList();
		persistTreeInOrder(root, persisted);
		//persisted.forEach(System.out::print);
		//in order array of non bst tree sorted ascending O(Nlog(N))
		Collections.sort(persisted);
		//persisted.forEach(System.out::print);
		arrayToBST(persisted, root, new MyCount());
		return root;
	}
	
	//O(n)
	public void arrayToBST(List<Integer> persisted, Node root, MyCount index){
		if(root == null || persisted == null || persisted.size()<1)
			return;
		arrayToBST(persisted, root.left, index);
		root.key = persisted.get(index.count);
		index.count++;
		arrayToBST(persisted, root.right, index);
	}
	
	//O(n)
	public void persistTreeInOrder(Node root, List<Integer> persisted){
		if(root == null)
			return;
		persistTreeInOrder(root.left, persisted);
		persisted.add(root.key);
		persistTreeInOrder(root.right, persisted);
	}
	
	//O(n)
	public int countNodesInTree(Node root){
		if(root == null)
			return -1;
		return countNodesInTree(root.right)+countNodesInTree(root.left)+1;
	}
	
	
	

	void testTreeHeightIsBalanced(){
		int arr[] = {50,25,20,10,75,85,100};
		this.addNodesIterative(arr);
		this.preOrderTraversalBst(this.root);
		System.out.println("Tree is height balanced? : "+this.getTreeHeightIsBalanced(this.root));
	}
	//O(n)
	public boolean getTreeHeightIsBalanced(Node focusNode){
		if(focusNode == null)
			return true;
		int left = getTreeHeight(focusNode.left);
		int right = getTreeHeight(focusNode.right);
		
		return left>right ? left-right < 2 : right-left < 2;
	}	

	void testPrintTreeLevelsRecursive(){
		root = new Node(10);
        root.left = new Node(2);
        root.right = new Node(10);
        root.left.left = new Node(20);
        root.left.right = new Node(1);
        root.right.right = new Node(-25);
    //    root.right.right.left = new Node(3);
		this.levelOrderTraversalBst(this.root);
		this.printTreeLevelsRecursive(this.root);
	}
	
	//O(n)
	public void printTreeLevelsRecursive(Node root){
		//get tree height and iterate over each level in the height calling recursive method at each level
		//add one to function return - get tree height is zero base
		int height = this.getTreeHeight(root)+1;
		for(int level=1; level<=height; level++){
			printTreeLevelRecursive(root, level);
			System.out.println(":");
		}
	}
	
	private void printTreeLevelRecursive(Node root, int level){
		if (root == null)
            return;
		//at root level
        if (level == 1)
            System.out.print(root + " ");
		//at levels other than root => recurse
        else if (level > 1) {
            printTreeLevelRecursive(root.left, level - 1);
            printTreeLevelRecursive(root.right, level - 1);
		}
	}

	
	void testIsBst(){
		//int arr[] = {100,50,200,25,75,90,350};
		this.root = new Node(100);
		root.right = new Node(200);
		root.left = new Node(50);
		root.left.left = new Node(25);
		root.left.right = new Node(75);
		root.right.left = new Node(90);
		root.right.right = new Node(350);
		System.out.println("Is BST? : "+this.isBST(this.root, Integer.MIN_VALUE, Integer.MAX_VALUE));	
	}
	
	//O(n)
	public boolean isBST(Node node, int minKey, int maxKey)
    {
        // base case
        if (node == null) {
            return true;
        }
        // if the node's value falls outside the valid range
        if (node.key < minKey || node.key > maxKey) {
            return false;
        }
         // recursively check left and right subtrees with an updated range
		 //current node.key becomes either min or max value depending whether right or left subtree is 
		 //being traversed. Obviously both left && right have to meet BST criteria
        return isBST(node.left, minKey, node.key) && isBST(node.right, node.key, maxKey);
    }

	void testFindKthLargest(){
		int arr[] = {50,25,20,10,75,85,100};
		this.addNodesIterative(arr);
		this.inOrderTraversalBst(this.root);
		int key = 1;
		System.out.println("kth"+key+" largest is: "+this.findKthLargest(this.root, new MyCount(), key));	
	}
	
	//O(n)
	public Node findKthLargest(Node root, MyCount count, int k){
		Node right = null;
		if(root == null)
			return null;
		else{
		//start the check from the right subtree
			right = findKthLargest(root.right, count, k);
			if(right != null)
				return right;
			count.count++;
			//check the count for the current node if equal to the desired size return
			if(count.count == k)
				return root;
			//continue the check in the left subtree
			return findKthLargest(root.left, count, k);
		}
	}
	
	
	void testFindKthSmallest(){
		int arr[] = {50,25,20,10,75,85,100};
		this.addNodesIterative(arr);
		this.inOrderTraversalBst(this.root);
		int key = 1;
		System.out.println("kth"+key+" smallest is: "+this.findKthSmallest(this.root, new MyCount(), key));	
	}
	
	////O(log n)
	public Node findKthSmallest(Node root, MyCount count, int k){
		Node left = null;
		if(root == null)
			return null;
		else{
		//start the check in the left subtree
			left = findKthSmallest(root.left, count, k);
			if(left != null)
				return left;
			count.count++;
			//check the count for the current node if equal to the desired size return
			if(count.count == k)
				return root;
			//continue the check in the right subtree
			return findKthSmallest(root.right, count, k);
		}
	}
	
	class MyCount{
		int count = 0, left = 0, right = 0;
	}

	void testFindInOrderSuccessor(){
		int arr[] = {50,25,20,10,75,85,100};
		this.addNodesIterative(arr);
		this.inOrderTraversalBst(this.root);
		int key = 75;
		System.out.println("Successor of "+key+": "+this.findInOrderSuccessor(key));
		key = 85;
		System.out.println("Successor of "+key+": "+this.findInOrderSuccessor(key));	}
	
	////O(log n)
	public Node findInOrderSuccessor(int key){
		Node focusNode = this.root, parentNode = null;
		//while node for search key not found continue
		while(focusNode.key != key){
			//do we search left subtree?
			if(key < focusNode.key){
				parentNode = focusNode;
				focusNode = focusNode.left;
			}else{//if not we search the right subtree
				parentNode = focusNode;
				focusNode = focusNode.right;
			}
			//focus node not found
			if(focusNode == null)
				return null;
		}	
		//depending where the node is in the tree you'll want to return the predecessor as
		//either the right child or the parent
		if(focusNode.key == root.key || focusNode.key > parentNode.key)
			return focusNode.right;
		else
			return parentNode;
	}
	
	void testFindInOrderPredecessor(){
		int arr[] = {50,25,20,10,75,85,100};
		this.addNodesIterative(arr);
		this.inOrderTraversalBst(this.root);
		int key = 75;
		System.out.println("Predecessor of "+key+": "+this.findInOrderPredecessor(key));
	}
	
	////O(log n)
	public Node findInOrderPredecessor(int key){
		Node focusNode = this.root, parentNode = null;
		//while node for search key not found continue
		while(focusNode.key != key){
			//do we search left subtree?
			if(key < focusNode.key){
				parentNode = focusNode;
				focusNode = focusNode.left;
			}else{//if not we search the right subtree
				parentNode = focusNode;
				focusNode = focusNode.right;
			}
			//focus node not found
			if(focusNode == null)
				return null;
		}	
		//depending where the node is in the tree you'll want to return the predecessor as
		//either the left child or the parent
		if(focusNode.key == root.key || focusNode.key < parentNode.key)
			return focusNode.left;
		else
			return parentNode;
	}
	
	void testFindClosestCommonAncestor(){
		int arr[] = {50,25,20,10,75,85,100};
		this.addNodesIterative(arr);
		this.inOrderTraversalBst(this.root);
		int key1 = 85, key2 = 100;
		System.out.println("Closest common ancestor of "+key1+" and "+key2+" : "+this.findClosestCommonAncestor(key1,key2));
	}
	
	//O(n)
	public Node findClosestCommonAncestor(int key1, int key2){
		Node focusNode = this.root, parentNode = this.root;
		//while node for search key not found continue
		boolean bothGoLeft = key1 < focusNode.key && key2 < focusNode.key;
		boolean bothGoRight = key1 > focusNode.key && key2 > focusNode.key;
		//while the keys go in the same direction they share common ancestors
		while(bothGoLeft || bothGoRight){
			//do we search left subtree?
			if(bothGoLeft){
				parentNode = focusNode;
				focusNode = focusNode.left;
				bothGoLeft = key1 < focusNode.key && key2 < focusNode.key;
			}else if(bothGoRight){
				parentNode = focusNode;
				focusNode = focusNode.right;
				bothGoRight = key1 > focusNode.key && key2 > focusNode.key;
			}
			if(focusNode == null)
				return null;
		}//else they split and the current focus node is the closest common ancestor	
		return parentNode;
	}
	
	void testGetTreeHeight(){
		root = new Node(10);
        root.left = new Node(2);
        root.right = new Node(10);
        root.left.left = new Node(20);
        root.left.right = new Node(1);
        root.right.right = new Node(-25);
        root.right.right.left = new Node(3);
		this.preOrderTraversalBst(this.root);
		System.out.println("Height of tree is : "+getTreeHeight(this.root));
	}

	//O(n)
	public int getTreeHeight(Node focusNode){
		if(focusNode == null)
			return -1;
		else{
			int left = getTreeHeight(focusNode.left);
			int right = getTreeHeight(focusNode.right);
			System.out.println("left = "+left+" right = "+right);
			return Math.max(left,right)+1;
		}
	}
	
	void testLevelOrderTraversal(){
		int arr[] = {18,30,15,40,50,100};
		this.addNodesIterative(arr);
		this.levelOrderTraversalBst(this.root);
	}
	
	//O(n) 
	public void levelOrderTraversalBst(Node v){
		List<Node> queue = new ArrayList(), explored = new ArrayList();
		if(v == null)
			return;
		else{
			queue.add(v);
		}
		while(!queue.isEmpty()){
			Node n = queue.remove(0);
			if(n == null)
				return;
			explored.add(n);
			System.out.print(""+n.key+" ");
			if(n.left != null && !explored.contains(n.left)){
				queue.add(n.left);
			}
			if(n.right != null && !explored.contains(n.right)){
				queue.add(n.right);
			}
		}
		System.out.println();
	}
	
	void testMaxPathSum(){
		root = new Node(10);
        root.left = new Node(2);
        root.right = new Node(10);
        root.left.left = new Node(20);
        root.left.right = new Node(1);
        root.right.right = new Node(-25);
        root.right.right.left = new Node(3);
        root.right.right.right = new Node(4);
		this.preOrderTraversalBst(this.root);
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
	
	void testRemove(){
		inOrderTraversalBst(this.root);
		System.out.println("Trying to remove 75 "+remove(75));
		inOrderTraversalBst(this.root);
	}
	
	//O(log n)
	public boolean remove(int key){
		Node focusNode = this.root;
		//we'll also declare the parent to be root value
		Node parent = this.root;
		//we need to know whether to look left or right when searching for the node to delete
		boolean isLeftChild = true;
		//while node for search key not found continue
		while(focusNode.key != key){
			parent = focusNode;
			//look left if key is smaller than focus node key
			if(key < focusNode.key){
				//flag focus node as left child
				isLeftChild = true;
				//set left child as new focus node
				focusNode = focusNode.left;
			}else{
				//flag focus node as right child
				isLeftChild = false;
				//set right child as new focus node
				focusNode = focusNode.right;
			}
			//focus node is never found
			if(focusNode == null){
				return false;
			}
			//if no child nodes are found at all i.e. leaf nodes are easy to set null and forget
			if(focusNode.left == null && focusNode.right == null){
				//check if focus node is root set root to null and end
				if(focusNode == this.root){
					root = null;
				}else if(isLeftChild){//check if its a left child delete it in its parent
					//set left child null
					parent.left = null;
				}else{
					//set right child null
					parent.right = null;
				}
			//child node exists. there is a left child but no right child
			}else if(focusNode.right == null){
				//handle if the focus node is root
				if(focusNode == root){
					//go left only option
					root = focusNode.left;
				//check if focus node is a left child and set the parent node left child to focus node left child "delete nodes"
				}else if(isLeftChild){
					parent.left = focusNode.left;
				} else{
				//focus node is a right child set the parent node right child to focus node left child "delete nodes"
					parent.right = focusNode.left;
				}
			}
			//child node exists. there is a right child but no left child
			else if(focusNode.left == null){
				//handle if the focus node is root
				if(focusNode == root){
					//go right only option
					root = focusNode.right;
				//check if focus node is a left child and set the parent node left child to focus node right child "delete nodes"
				}else if(isLeftChild){
					parent.left = focusNode.right;
				}else{
				//focus node is a right child set the parent node right child to focus node right child "delete nodes"
					parent.right = focusNode.right;//ERROR changed focusNode.left to right
				}
			}
			//both child nodes exist as non null.  Have to work out what the replacement will be.
			else{
				Node replacement = getReplacementNode(focusNode);
				//check if focus node is root if yes use replacement node
				if(focusNode == root){
					root = replacement;
				//if is left child then the left child of the parent node assigned to replacement node
				}else if(isLeftChild){
					parent.left = replacement;
				//else the right child of the parent node assigned to replacement node
				}else{
					parent.right = replacement;
				}
				//need to set left child of replacement to left of focus node so don't bust tree.
				replacement.left = focusNode.left;
			}
		}
		return true;
	}
	
	//helper method to get the node to replace the one being deleted
	//O(log n)
	private Node getReplacementNode(Node nodeToReplace){
		Node nodeToReplaceParent = nodeToReplace;
		//will be the node that ultimately replaces the node
		Node replacementNode = nodeToReplace;
		//we always replace with the right (larger) child.  Move the right child up
		Node focusNode = nodeToReplace.right;
		//keeep going while there are no more left child nodes
		while(focusNode != null){
			nodeToReplaceParent = replacementNode;
			//we always replace with the right (larger) child
			replacementNode = focusNode;
			//move the left child up
			focusNode = focusNode.left;
		}
		//if replacement is not right child
		if(replacementNode != nodeToReplace.right){
			nodeToReplaceParent.left = replacementNode.right;
			replacementNode.right = nodeToReplace.right;
		}
		
		return replacementNode;
	}
	
	//find a node in a bst
	//O(log n)
	void testFindNodeRecursive(){
		System.out.println("Search for node with key 75 found : "+this.findNodeRecursive(this.root,75));
		System.out.println("Search for node with key 63 found : "+this.findNodeRecursive(this.root,63));
	}
	
	public Node findNodeRecursive(Node root, int key){
		// Base Cases: root is null or key is present at root
		if (root == null || root.key == key)
			return root;
	 
		// Key is greater than root's key
		if (root.key < key)
		   return findNodeRecursive(root.right, key);
	 
		// Key is smaller than root's key
		return findNodeRecursive(root.left, key);
	}
	
	
	//find a node in a bst
	void testFindNodeIterative(){
		System.out.println("Search for node with key 75 found : "+this.findNodeIterative(75));
		System.out.println("Search for node with key 63 found : "+this.findNodeIterative(63));
	}
	
	//O(log n)
	public Node findNodeIterative(int key){
		Node focusNode = this.root;
		//while node for search key not found continue
		while(focusNode.key != key){
			//do we search left subtree?
			if(key < focusNode.key){
				focusNode = focusNode.left;
			}else{//if not we search the right subtree
				focusNode = focusNode.right;
			}
			//focus node not found
			if(focusNode == null)
				return null;
		}		
		return focusNode;
	}
	
	//use DFS to explore apex first, left side , then right side for pre order traversal
	void testPostOrderTraversalBst(){
		postOrderTraversalBst(this.root);
	}
	
	//O(n)
	public void postOrderTraversalBst(Node focusNode){
		if(focusNode != null){
			postOrderTraversalBst(focusNode.left);
			postOrderTraversalBst(focusNode.right);
			System.out.print(" "+focusNode);
		}
	}
	
	//use DFS to explore apex first, left side , then right side for pre order traversal
	void testPreOrderTraversalBst(){
		preOrderTraversalBst(this.root);
	}
	
	//O(n)
	public void preOrderTraversalBst(Node focusNode){
		if(focusNode != null){
			System.out.print(focusNode+" ");
			preOrderTraversalBst(focusNode.left);
			preOrderTraversalBst(focusNode.right);
		}
	}
	
	//use DFS to explore left side first, apex, then right side for in order traversal
	void testInOrderTraversalBst(){
		inOrderTraversalBst(this.root);
	}
	
	//O(n)
	public void inOrderTraversalBst(Node focusNode){
		if(focusNode != null){
			inOrderTraversalBst(focusNode.left);
			System.out.print(focusNode);
			inOrderTraversalBst(focusNode.right);
		}
	}
	

	//add node - build bst 
	//O(log n)
	void testAddNodeIterative(){
		int[] nodes = {50,2,15,18,25,30,70,75,85};
		this.addNodesIterative(nodes);
		System.out.println("root "+this.root);

		/*int[] nodess = {25,85,15,30,75,50};
		this.addNodesIterative(nodess);
		System.out.println("root "+this.root);*/
		
	}
	private void addNodesIterative(int[] nodes){
		if(nodes == null || nodes.length < 1)
			return;
		for(int i=0; i<nodes.length; i++){
			this.addNodeIterative(nodes[i]);
		}
	}
	
	public void addNodeIterative(int key){
		addNodeIterative(key, String.valueOf(key));
	}
	
	//O(log n)
	public void addNodeIterative(int key, String name){
		Node newNode = new Node(key);
		//if root is null new node is root
		if(root == null)
			root = newNode;
		else{
			//root exists so set it as the focus node
			Node focusNode = root;
			//declare a new node that will be the future parent of the focus node 
			Node parent = null;
			//continue to look for the right place to add the node until you find it
			while(true){
				//set the parent to be the focus node which is the root as well
				parent = focusNode;
				//decide which side of the tree the new node will have to go
				if(key < focusNode.key){
					//if key less than focus node key it will belong in the left subtree
					focusNode = focusNode.left;
					//if the left child of the focus node doesn't exist this is whee new node lives
					if(focusNode == null){
						parent.left = newNode;
						return;
					}
				}else{
					//if key greater than or equal to focus key it will belong in the right subtree
					focusNode = focusNode.right;
					//if the right child of the focus node doesn't exist this is where the new node lives
					if(focusNode == null){
						parent.right = newNode;
						return;
					}
				}
			}
		}
	}
	class Node{
	int key;
	Node left, right, levelOrderSuccessor;
	
	public Node(int k){
		key = k;
	}
	
	public String toString(){
		return key+"("+levelOrderSuccessor+")";
	}
}
}


class PathSum{
	int value = Integer.MIN_VALUE, left = -1, right = -1;
}

/*
class Node{
	int key;
	String value;
	Node left, right;
	
	public Node(int k){
		key = k;
		value = String.valueOf(k);
	}
	public Node(int k, String v){
		key = k;
		value = v;
	}
	
	public String toString(){
		return value+" ";
	}
}*/