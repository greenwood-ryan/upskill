
/*Question
Given preorder and inorder traversal of a tree, construct the binary tree.

Solution thinking
Say we have 2 arrays, PRE and IN.
Preorder traversing implies that PRE[0] is the root node.
Then we can find this PRE[0] in IN, say it's IN[5].
Now we know that IN[5] is root, so we know that IN[0] - IN[4] is on the left side, IN[6] to the end is on the right side.
Recursively doing this on subarrays, we can build a tree out of it :)
*/
import java.util.*;

public class TreeFromPreAndInOrderArrays{
	TreeNode tn = null;
	
	public static void main(String[] args){
		new TreeFromPreAndInOrderArrays();
	}
		
	public TreeFromPreAndInOrderArrays(){
	  int[] in = {4,2,5,1,3};
	  int[] pre =  {1,2,4,5,3};
	  tn = treeBuilder(pre, in);
	  printNodes(tn);
	  
	  System.out.println("Max path sum of tree is: "+new MaxPathLengthCalculator().maxPathSum(tn));
	  
	  Codec cdc = new Codec();
	  String serializedTree = cdc.serialize(tn);
	  System.out.println("Deserialized tree -> "+serializedTree);
	  tn = cdc.deserialize(serializedTree);
	  printNodes(tn);
	}

	public TreeNode treeBuilder(int[] preOrderTraversal, int[] inOrderTraversal) {
		return helper(0, 0, inOrderTraversal.length - 1, preOrderTraversal, inOrderTraversal);
	}

	public TreeNode helper(int preStart, int inOrderTraversalStart, int inOrderTraversalEnd, int[] preOrderTraversal, int[] inOrderTraversal) {
		if (preStart > preOrderTraversal.length - 1 || inOrderTraversalStart > inOrderTraversalEnd) { //check if start point is still in bounds
			return null;
		}
		TreeNode root = new TreeNode(preOrderTraversal[preStart]);
		int inIndex = 0; // Index of current root in inOrderTraversal
		for (int i = inOrderTraversalStart; i <= inOrderTraversalEnd; i++) {
			if (inOrderTraversal[i] == root.value) {
				inIndex = i;
			}
		}
		root.left = helper(preStart + 1, inOrderTraversalStart, inIndex - 1, preOrderTraversal, inOrderTraversal);
		root.right = helper(preStart + inIndex - inOrderTraversalStart + 1, inIndex + 1, inOrderTraversalEnd, preOrderTraversal, inOrderTraversal);
		return root;
	}
	
	void printNodes(TreeNode tn){
		try{
			System.out.println("value = "+tn.value+", left = "+tn.left+", right = "+tn.right);
			printNodes(tn.left);
			printNodes(tn.right);
		}catch(NullPointerException npe){
			System.out.println("Tn "+tn.value+" is a leaf");
		}
	}
}

class TreeNode{
	public TreeNode left, right;
	public int value;
	public String id;
	
	public TreeNode(int start){
		value = start;
	}
}


/*
Question
Given a non-empty binary tree, find the maximum path sum. For this problem, a path is defined as any sequence of nodes from some starting node to any node in the tree along the parent-child connections. The path must contain at least one node and does not need to go through the root.

Solution thinking
A path from start to end, goes up on the tree for 0 or more steps, then goes down for 0 or more steps. Once it goes down, it can't go up. Each path has a highest node, which is also the lowest common ancestor of all other nodes on the path.
A recursive method maxPathDown(TreeNode node) (1) computes the maximum path sum with highest node as the input node, update maximum if necessary (2) returns the maximum sum of the path that can be extended to input node's parent.
*/

class MaxPathLengthCalculator {
    int maxValue;
    
    public int maxPathSum(TreeNode root) {
        maxValue = Integer.MIN_VALUE;
        maxPathDown(root);
        return maxValue;
    }
    
    private int maxPathDown(TreeNode node) {
        if (node == null) return 0;
        int left = Math.max(0, maxPathDown(node.left));
        int right = Math.max(0, maxPathDown(node.right));
        maxValue = Math.max(maxValue, left + right + node.value);
        return Math.max(left, right) + node.value;
    }
}

/*Question:
Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that a binary tree can be serialized to a string and this string can be deserialized to the original tree structure.

Solution thinking
For serializing, print the tree in pre-order traversal and use "X" to denote null node and split node with ",". We can use a StringBuilder for building the string on the fly. 
For deserializing, we use a Queue to store the pre-order traversal and since we have "X" as null node, we know exactly how to where to end building subtress.
*/

class Codec {
    private static final String spliter = ",";
    private static final String NN = "X";

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        buildString(root, sb);
        return sb.toString();
    }

    private void buildString(TreeNode node, StringBuilder sb) {
        if (node == null) {
            sb.append(NN).append(spliter);
        } else {
            sb.append(node.value).append(spliter);
            buildString(node.left, sb);
            buildString(node.right,sb);
        }
    }
    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        Deque<String> nodes = new LinkedList<>();
        nodes.addAll(Arrays.asList(data.split(spliter)));
        return buildTree(nodes);
    }
    
    private TreeNode buildTree(Deque<String> nodes) {
        String val = nodes.remove();
        if (val.equals(NN)) return null;
        else {
            TreeNode node = new TreeNode(Integer.valueOf(val));
            node.left = buildTree(nodes);
            node.right = buildTree(nodes);
            return node;
        }
    }
}