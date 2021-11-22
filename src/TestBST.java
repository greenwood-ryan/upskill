import java.util.*;
import java.util.regex.*;

public class TestBST{
	Node root;
	int depthLeft, depthRight, distanceBetweenNodes, preIndex = 0;
	
	public static void main(String[] args){
		TestBST tb = new TestBST();
		tb.testAddNodesRecursive();
		tb.testPrintInOrder();
		
		System.out.println("\nTest delete node recursive");
		tb = new TestBST();
		tb.testDeleteNodeRecursive();

		System.out.println("\nTest retrieve node recursive");
		tb = new TestBST();
		tb.testRetrieveNode();

		System.out.println("\nTest retrieve node level order");
		tb = new TestBST();
		tb.testRetrieveLevelOrder();

		System.out.println("\nTest insert nodes level order");
		tb = new TestBST();
		tb.testInsertNodesLevelOrderBST();

		System.out.println("\nTest delete nodes level order");
		tb = new TestBST();
		tb.testDeleteLevelOrderBST();

		System.out.println("\nTest tree is foldable");
		tb = new TestBST();
		tb.testTreeIsFoldable();

		System.out.println("\nTest find distance from root of node");
		tb = new TestBST();
		tb.testDistanceFromRoot();

		System.out.println("\nTest find distance from root of lca of two nodes");
		tb = new TestBST();
		tb.testFindDistanceFromRootOfLCAInBST();

		System.out.println("\nTest is BST");
		tb = new TestBST();
		tb.testIsBST();

		System.out.println("\nTest find distance between two nodes in BST");
		tb = new TestBST();
		tb.testFindDistanceBetweenTwoNodesInBST();

		System.out.println("\nTest find level of node in tree");
		tb = new TestBST();
		tb.testFindLevelOfNodeInTree();

		System.out.println("\nTest is node in tree");
		tb = new TestBST();
		tb.testIsNodePresent();

		System.out.println("\nTest find lca of two nodes in tree");
		tb = new TestBST();
		tb.testFindLCAInTree();

		System.out.println("\nTest find level of node in tree");
		tb = new TestBST();
		tb.testFindLevelOfNodeInTree();

		System.out.println("\nTest find between two nodes in tree");
		tb = new TestBST();
		tb.testFindDistanceBetweenTwoNodesInTree();

		System.out.println("\nTest build tree from in and pre order definitions");
		tb = new TestBST();
		tb.testBuildTree();
	}
	
	void testBuildTree(){
		int[] in = {2,3,4,5,6,8};
		int[] pre = {5,3,2,4,8,6};
		this.root = buildTree(in, pre, 0, in.length);
		printInOrder(this.root);
	}
	
	//build tree given order and pre order definitions as arrays in and pre
	//start begins with 0 and end begins with len-1
	public Node buildTree(int[] in, int[] pre, int start, int end){
		if(start > end)
			return null;
		if(in.length < 1 || pre.length < 1)
			return null;
		//get current node key from pre order traversal using index and increment index
		Node n = null;
		if(preIndex < pre.length)
			n = new Node(pre[preIndex++]);
		//has no children return the node
		if(start == end)
			return n;
		//else find index of n in the in order traversal
		int inIndex = getKeyIndex(in, n.key);
		//use index from inorder traversal to build left and right
		n.left = buildTree(in, pre, start, inIndex-1);
		n.right = buildTree(in, pre, inIndex+1, end);
		return n;
	}
	
	public int getKeyIndex(int[] in, int key){
		int i;
		for(i=0; i<in.length; i++){
			if(in[i] == key)
				return i;
		}
		return i;
	}

	void testFindDistanceBetweenTwoNodesInTree(){
		/*int key1 = 5, key2 = 6;
		this.root = new Node(5);
		root.right = new Node(8);
		root.left = new Node(3);
		root.left.left = new Node(2);
		root.left.left.left = new Node(1);
		root.left.right = new Node(4);
		root.right.left = new Node(6);
		root.right.right = new Node(9);
		root.right.right.right = new Node(10);
		printInOrder(this.root);*/
		int[] keys = generateRandomArray(10);
		insertNodesLevelOrderBST(keys);
		int key1 = keys[new Random().nextInt(keys.length)];
		int key2 = keys[new Random().nextInt(keys.length)];

		System.out.println("\nDistance between "+key1+" and "+key2+" is "+findDistanceBetweenTwoNodesInTree(this.root, key1, key2));
		
	}

	//Dist(n1, n2) = Dist(root, n1) + Dist(root, n2) - 2*Dist(root, lca) 
	public int findDistanceBetweenTwoNodesInTree(Node root, int key1, int key2){
		//tree is empty
		if(root == null)
			return Integer.MIN_VALUE;
		//both keys must be present in tree
		if(!isNodePresent(root, key1) || !isNodePresent(root, key2))
			return Integer.MIN_VALUE;
		//get levels of keys
		int key1Level = findLevelOfNodeInTree(root, key1, 0);
		int key2Level = findLevelOfNodeInTree(root, key2, 0);
		//get lca
		Node lca = findLCAInTree(root, key1, key2);
		//get lca level
		int lcaLevel = findLevelOfNodeInTree(root, lca.key, 0);
		//calculate and return distance
		return (key1Level + key2Level) - 2 * lcaLevel;
	}
	
	void testFindLevelOfNodeInTree(){
		int key = 4;
		this.root = new Node(5);
		root.right = new Node(8);
		root.left = new Node(3);
		root.left.left = new Node(2);
		root.left.left.left = new Node(1);
		root.left.right = new Node(4);
		root.right.left = new Node(6);
		root.right.right = new Node(9);
		root.right.right.right = new Node(10);
		printInOrder(this.root);
		/*int[] keys = generateRandomArray(10);
		insertNodesLevelOrderBST(keys);
		int key1 = keys[new Random().nextInt(keys.length)];
		int key2 = keys[new Random().nextInt(keys.length)];*/
		
		System.out.println("\nLevel of  "+key+" in tree is "+findLevelOfNodeInTree(this.root, key, 0));			
	}
	
	public int findLevelOfNodeInTree(Node root, int key, int level){
		//tree is empty
		if(root == null)
			return Integer.MIN_VALUE;
		//tree has one node
		if(root.key == key)
			return level;
		//look in left subtree for key
		int lev = findLevelOfNodeInTree(root.left, key, level+1);
		if(lev != Integer.MIN_VALUE)
			return lev;
		//must be in right subtree
		return findLevelOfNodeInTree(root.right, key, level+1);
	}
	
	void testFindLCAInTree(){
		int key1 = 1, key2 = 4;
		this.root = new Node(5);
		root.right = new Node(8);
		root.left = new Node(3);
		root.left.left = new Node(2);
		root.left.left.left = new Node(1);
		root.left.right = new Node(4);
		root.right.left = new Node(6);
		root.right.right = new Node(9);
		root.right.right.right = new Node(10);
		printInOrder(this.root);
		System.out.println("\nLca of "+key1+" and "+key2+" is "+findLCAInTree(this.root, key1, key2));
		
	}
	
	public Node findLCAInTree(Node root, int key1, int key2){
		//tree is empty
		if(root == null)
			return null;
		//one of the keys matches root
		if(root.key == key1 || root.key == key2)
			return root;
		//recurse left and right looking for either of keys
		Node left = findLCAInTree(root.left, key1, key2);
		Node right = findLCAInTree(root.right, key1, key2);
		//if neither is null root is lca
		if(left != null && right != null)
			return root;
		//if both in left
		if(left != null)
			return left;
		//if both in right
		if(right != null)
			return right;
		//otherwise neither were found which shouldn't happen
		return null;
	}
	
	void testIsNodePresent(){
		int key = 4;
		this.root = new Node(5);
		root.right = new Node(8);
		root.left = new Node(3);
		root.left.left = new Node(2);
		root.left.left.left = new Node(1);
		root.left.right = new Node(4);
		root.right.left = new Node(6);
		root.right.right = new Node(9);
		root.right.right.right = new Node(10);
		printInOrder(this.root);
		/*int[] keys = generateRandomArray(10);
		insertNodesLevelOrderBST(keys);
		int key1 = keys[new Random().nextInt(keys.length)];
		int key2 = keys[new Random().nextInt(keys.length)];*/
		
		System.out.println("\nNode  "+key+" is in tree? "+isNodePresent(this.root, key));			
		
	}
	
	
	public boolean isNodePresent(Node root, int key){
		if(root == null)
			return false;
		if(root.key == key)
			return true;
		return isNodePresent(root.left, key) || isNodePresent(root.right, key);
			
	}

	
	void testFindDistanceBetweenTwoNodesInBST(){
		/*int key1 = 1, key2 = 4;
		this.root = new Node(5);
		root.right = new Node(8);
		root.left = new Node(3);
		root.left.left = new Node(2);
		root.left.left.left = new Node(1);
		root.left.right = new Node(4);
		root.right.left = new Node(6);
		root.right.right = new Node(9);
		root.right.right.right = new Node(10);
		printInOrder(this.root);*/
		int[] keys = generateRandomArray(10);
		insertNodesLevelOrderBST(keys);
		int key1 = keys[new Random().nextInt(keys.length)];
		int key2 = keys[new Random().nextInt(keys.length)];
		
		printTreeLevelsIterative(this.root);
		System.out.println("\nDistance from  "+key1+" to "+key2+" is "+findDistanceBetweenTwoNodesInBST(this.root, key1, key2));	
	}

	//Dist(n1, n2) = Dist(root, n1) + Dist(root, n2) - 2*Dist(root, lca) 
	public int findDistanceBetweenTwoNodesInBST(Node root, int key1, int key2){
		if(root == null)
			return Integer.MIN_VALUE;
		if(key1 == key2)
			return 0;
		int lca = findDistanceFromRootOfLCAInBST(root, key1, key2);
		int rootToKey1 = findDistanceFromRoot(root, key1);
		int rootToKey2 = findDistanceFromRoot(root, key2);
		
		return rootToKey1 + rootToKey2 - 2 * lca;
	}
	
	void testIsBST(){
		int[] keys = generateRandomArray(10);
		insertNodesLevelOrderBST(keys);
		int key1 = new Random().nextInt(keys.length);
		int key2 = new Random().nextInt(keys.length);
		
		System.out.println("\nIs BST "+isBST(this.root, Integer.MIN_VALUE, Integer.MAX_VALUE));	
	}
	
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
        return isBST(node.left, minKey, node.key) &&  isBST(node.right, node.key, maxKey);
    }
	
	void testFindDistanceFromRootOfLCAInBST(){
		int key1 = 6, key2 = 9;
		this.root = new Node(5);
		root.right = new Node(8);
		root.left = new Node(3);
		root.left.left = new Node(2);
		root.left.right = new Node(4);
		root.right.left = new Node(6);
		root.right.right = new Node(9);
		printInOrder(this.root);
		System.out.println("\nDistance from root of lca of "+key1+" and "+key2+" is "+findDistanceFromRootOfLCAInBST(this.root, key1, key2));
		
	}
	
	
	public Node retrieveNode(Node root, int key){
		if(root == null)
			return root;
		if(key < root.key)
			root = retrieveNode(root.left, key);
		else if(key > root.key)
			root = retrieveNode(root.right, key);
		return root;
	}
	
	
	//find lowest common ancestor of 2 nodes in BST
	public int findDistanceFromRootOfLCAInBST(Node root, int key1, int key2){
		if(root == null)
			return Integer.MIN_VALUE;
		if(key1 == key2)
			return 0;
		int levelCount = 0;
		Node parent = root, focus = root;
		//find lowest common ancestor
		boolean bothGoLeft = key1 < focus.key && key2 < focus.key;
		boolean bothGoRight = key1 > focus.key && key2 > focus.key;
		boolean notNull = focus.left != null && focus.right != null;
		while((bothGoLeft || bothGoRight) && notNull){
			parent = focus;
			if(bothGoLeft && parent.left != null){
				focus = parent.left;
				bothGoLeft = key1 < focus.key && key2 < focus.key;
				levelCount++;
			}else if(bothGoRight && parent.right != null){
				focus = parent.right;
				bothGoRight = key1 > focus.key && key2 > focus.key;
				levelCount++;
			}
			notNull = focus.left != null && focus.right != null;
		}//else they split and focus node is LCA
		System.out.println("\nlowest common ancestor is "+focus);
		return levelCount;
	}
	

	void testDistanceFromRoot(){
		int key = 8;
		this.root = new Node(5);
		root.right = new Node(8);
		root.left = new Node(3);
		root.left.left = new Node(2);
		root.left.right = new Node(4);
		root.right.left = new Node(6);
		root.right.right = new Node(9);
		printInOrder(this.root);
		System.out.println("\nDistance from root of "+key+" is "+findDistanceFromRoot(this.root, key));
		
	}
	
	public int findDistanceFromRoot(Node root, int key){
		int count = 0;
		boolean foundKey = false;
		//retrieve level order left
		List<Node> queue = new ArrayList();
		queue.add(root);
		Node n = null;
		while(!queue.isEmpty()){
			int nodeCount = queue.size();//count of all nodes at same level
			//deque all nodes at one level and enqueue all nodes at next level
			while(nodeCount > 0){
				n = queue.remove(0);
				if(n.key == key)
					foundKey = true;
				if(n.left!=null)
					queue.add(n.left);
				if(n.right!=null)
					queue.add(n.right);
				nodeCount--;
			}
			if(foundKey)
				break;
			count++;
		}
		return count;
	}
	
	void testTreeIsFoldable(){
		this.root = new Node(10);
		root.right = new Node(7);
		root.left = new Node(2);
		root.left.left = new Node(8);
		root.left.right = new Node(4);
		root.right.left = new Node(9);
		root.right.right = new Node(5);
		printInOrder(root);
		System.out.println("Tree is foldable? : "+treeIsFoldable(root));
		root.right.right.right = new Node(10);
		printInOrder(root);
		System.out.println("\nTree is foldable? : "+treeIsFoldable(root));
		root.left.left.left = new Node(11);
		printInOrder(root);
		System.out.println("\nTree is foldable? : "+treeIsFoldable(root));
	}
	
	//test shortest path between two nodes
	void testPathSumBetween(){
		//int[] keys = {5,8,9,4,6,3,1,7,2,5};
		//int[] keys = {2,3,1,4,5,6,7};
		//int[] keys = generateRandomArray(3000);
		//insertNodesLevelOrderBST(keys);
		//int key1 = new Random().nextInt(keys.length);
		//int key2 = new Random().nextInt(keys.length);
		int key1 = 2, key2 = 9;
		this.root = new Node(5);
		root.right = new Node(8);
		root.left = new Node(3);
		root.left.left = new Node(2);
		root.left.right = new Node(4);
		root.right.left = new Node(6);
		root.right.right = new Node(9);

		printInOrder(this.root);
		System.out.println("\nShortest path between "+key1+" and "+key2+" is ");//+pathSumBetween(this.root, key1, key2));
	}

	
	class Count{
		boolean keepCounting = false;
		int count, level, key1Depth, key2Depth;
	}
	
	public boolean treeIsFoldable(Node root){
		if(root == null)
			return false;
		boolean isFoldable = true;
		List<Node> queue = new ArrayList();
		queue.add(root);
		Node n = null;
		while(!queue.isEmpty()){
			int nodeCount = queue.size();//count of all nodes at same level
			//deque all nodes at one level and enqueue all nodes at next level
			int countOfLefts  = 0, countOfRights = 0;
			while(nodeCount > 0){
				n = queue.remove(0);
				if(n.left != null){
					queue.add(n.left);
					countOfLefts++;
				}
				if(n.right != null){
					queue.add(n.right);
					countOfRights++;
				}
				nodeCount--;
			}
			isFoldable &= (countOfLefts == countOfRights);
		}
		return isFoldable;
	}
	
	void testDeleteLevelOrderBST(){
		int[] keys = {5,8,9,4,6,3,1,7,2,5};
		insertNodesLevelOrderBST(keys);
		int key = new Random().nextInt(keys.length);
		printInOrder(root);
		System.out.println("\nDelete "+key);
		deleteLevelOrderBST(this.root, key);
		printInOrder(this.root);	
	}
	
	public void deleteLevelOrderBST(Node root, int key){
		if(root == null)
			return;
		List<Node> queue = new ArrayList();
		queue.add(root);
		while(!queue.isEmpty()){
			Node n = queue.remove(0);
			if(key != n.key){
				if(n.left != null)
					queue.add(n.left);
				if(n.right != null)
					queue.add(n.right);
			}
			//found node to delete now check duplicates
			else{
				//no duplicates so have to "delete" node
				if(n.count < 2){
					//is a leaf set it null
					if(n.right == null && n.left == null)
						n = null;
					//if one child null use other child as replacement node
					else if(n.right == null){
						n = n.left;
					}
					else if(n.left == null){
						n = n.right;
					}
					else{//has both children use successor from right subtree
						int minValue = minValue(n.right);
						n.key = minValue;
						deleteLevelOrderBST(n.right, minValue);
					}
				}else//duplicates so just reduce count
					n.count--;
			}
		}
	}
	
	void testInsertNodesLevelOrderBST(){
		int[] keys = {5,8,9,4,6,3,1,7,2,5};
		insertNodesLevelOrderBST(keys);
		printInOrder(root);
	}
	
	public void insertNodesLevelOrderBST(int[] keys){
		insertLevelOrderBST(root, keys[0]);
		for(int i=1; i<keys.length; i++)
			insertLevelOrderBST(root, keys[i]);
	}
	
	public void insertLevelOrderBST(Node root, int key){
		if(root == null){
			this.root = new Node(key);
			this.root.count++;
			return;
		}
		List<Node> queue = new ArrayList();
		queue.add(root);
		while(!queue.isEmpty()){
			root = queue.remove(0);
			if(key < root.key){
				if(root.left == null){
					root.left = new Node(key);
					root.left.count++;
					break;
				}else
					queue.add(root.left);
			}else if(key > root.key){
				if(root.right == null){
					root.right = new Node(key);
					root.right.count++;
					break;
				}else
					queue.add(root.right);
			}else
				root.count++;
		}
	}
	
	void testRetrieveLevelOrder(){
		int[] keys = {5,8,9,4,6,3,1,7,2,5};
		addNodesRecursive(keys);
		int key = new Random().nextInt(keys.length);
		printInOrder(root);
		System.out.println("\nTrying to retrieve "+key);
		System.out.println("Retrieved "+retrieveLevelOrder(root, key));
		System.out.println("\nTrying to retrieve non existent key 0");
		System.out.println("Retrieved "+retrieveLevelOrder(root, 0));
	}
	
	public Node retrieveLevelOrder(Node root, int key){
		if(root == null)
			return root;
		List<Node> queue = new ArrayList();
		queue.add(root);
		while(!queue.isEmpty()){
			root = queue.remove(0);
			if(root.key == key)
				return root;
			else{
				if(root.right != null)
					queue.add(root.right);
				if(root.left != null)
					queue.add(root.left);
			}
		}
		return null;
	}
	

	void testRetrieveNode(){
		int[] keys = {5,8,9,4,6,3,1,7,2,5};
		addNodesRecursive(keys);
		int key = new Random().nextInt(keys.length);
		printInOrder(root);
		System.out.println("\nTrying to retrieve "+key);
		System.out.println("Retrieved "+retrieveNode(root, key));
	}
	
	void testDeleteNodeRecursive(){
		int[] keys = {5,8,9,4,6,3,1,7,2,5};
		addNodesRecursive(keys);
		int key = new Random().nextInt(keys.length);
		printInOrder(root);
		System.out.println("\nDelete "+key);
		deleteNodeRecursive(root, key);
		printInOrder(root);
	}

	
	public Node deleteNodeRecursive(Node root, int key){
		if(root == null)
			return root;
		if(key < root.key)
			root.left = deleteNodeRecursive(root.left, key);
		else if(key > root.key)
			root.right = deleteNodeRecursive(root.right, key);
		else{
			if(root.count < 2){
				if(root.left == null)
					return root.right;
				else if(root.right == null)
					return root.left;
				else{
					int minValue = minValue(root.right);
					root.key = minValue;
					deleteNodeRecursive(root.right, minValue);
				}
			}else
				root.count--;
		}
		return root;
	}
	
	int minValue(Node root){
		int minValue = root.key;
		while(root.left != null){
			minValue = root.left.key;
			root = root.left;
		}
		return minValue;
	}
	
	
	void testPrintInOrder(){
		printInOrder(this.root);
	}
	
	public void printPreOrder(Node root){
		if(root != null){
			System.out.print(root);
			printPreOrder(root.left);
			printPreOrder(root.right);
		}
	}
	
	public void printInOrder(Node root){
		if(root != null){
			printInOrder(root.left);
			System.out.print(root);
			printInOrder(root.right);
		}
	}
	
	void testAddNodesRecursive(){
		int[] keys = {5,8,9,4,6,3,1,7,2,5};
		addNodesRecursive(keys);
	}
	
	public void addNodesRecursive(int[] keys){
		root = addNodeRecursive(root, keys[0]);
		for(int i=1; i<keys.length; i++)
			addNodeRecursive(root, keys[i]);
	}	
	
	public Node addNodeRecursive(Node root, int key){
		if(root == null){
			root = new Node(key);
			root.count++;
			return root;
		}
		//root exists and key is smaller go left subtree
		if(key < root.key)
			root.left = addNodeRecursive(root.left, key);
		//root exists and key is larger go right subtree
		else if(key > root.key)
			root.right = addNodeRecursive(root.right, key);
		else
		//node already exists with the same key so is multiplicate
			root.count++;
		return root;
	}
	
	
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
	
	public int[] generateRandomArray(int size) {
		int[] arr = new int[size];
		for (int i = 0; i < size; i++) {
			// Generate a random array with values between
			// int min and int max
			arr[i] = (int) (Math.random() * 10000) + 10;
		}
		return arr;
	}
	
	class Node{
		int key, count;
		Node right, left;
		
		public String toString(){
			return key+"("+count+") ";
		}
		
		public Node(int key){
			this.key = key;
		}
	}
}