import java.util.*;

public class TrieTest{
    public static void main(String args[])
    {
		new Trie().test();
    }
}

class Trie {
	private final int ALPHABET_SIZE = 26;
	private final String[] ALPHABET = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
	TrieNode root;

	public void test(){
        // Input keys (use only 'a' through 'z' and lower case)
        String keys[] = {"the", "a", "there", "answer", "any",
                         "by", "bye", "their", "be", "bee", "been", "beet", "bean", "beat", "bat", "ball"};
      
        String output[] = {"Not present in trie", "Present in trie"};
      
      
        root = new TrieNode();
      
        // Construct trie
        int i;
        for (i = 0; i < keys.length ; i++)
            insert(keys[i]);
      
        // Search for different keys
        if(search("bee") == true)
            System.out.println("bee --- " + output[1]);
        else System.out.println("bee --- " + output[0]);
 
		if(search("the") == true)
            System.out.println("the --- " + output[1]);
        else System.out.println("the --- " + output[0]);
         
        if(search("these") == true)
            System.out.println("these --- " + output[1]);
        else System.out.println("these --- " + output[0]);
         
        if(search("their") == true)
            System.out.println("their --- " + output[1]);
        else System.out.println("their --- " + output[0]);
         
        if(search("thaw") == true)
            System.out.println("thaw --- " + output[1]);
        else System.out.println("thaw --- " + output[0]);
		
		System.out.println("Result : "+(Arrays.toString(typeahead("b"))));
        	
	}
	
	//O(n)
	public String[] typeahead(String key){
		if(key == null)
			return null;
		TrieNode levelRoot = getLastNode(key);
		StringBuffer result = new StringBuffer(), word = new StringBuffer();
		getResult(levelRoot, result, word, 0, key);
		return result.toString().split(",");
	}
	
	private void getResult(TrieNode root, StringBuffer result, StringBuffer wordSuffix, int level, String prefix){
		if(root == null || result == null || wordSuffix == null || prefix == null)
			return;
		//base case : end of word has been found so...
		if(root.isEndOfWord){
			//clear any chars remaining from previous wordSuffixes inserted into the string builder
			wordSuffix.delete(level, wordSuffix.length());
			//concatenate the search string as prefix with wordSuffix that has been built and a , for the split
			//then add to result buffer
			result.append(prefix+(wordSuffix.toString())+",");
		}
		//iterate through node children finding non null nodes
		for (int i = 0; i < ALPHABET_SIZE; i++) 
		{
			if (root.children[i] != null) 
			{
				//when non null node is found 
				//retrieve the alpha char and insert it into the wordSuffix at the level index position
				wordSuffix.insert(level, ALPHABET[i]);
				//recurse down a level every cycle.  The base case will find the end of a word
				getResult(root.children[i], result, wordSuffix, level+1, prefix);
			}
		}
	}
	
	private TrieNode getLastNode(String key){
        int level, length = key.length(), index;
        TrieNode node = root;
      
        for (level = 0; level < length; level++)
        {
            index = key.charAt(level) - 'a';
      
            if (node.children[index] == null)
                return null;
      
            node = node.children[index];
        }
        return node;
    }
     
    
    // trie node
    class TrieNode
    {
        TrieNode[] children = new TrieNode[ALPHABET_SIZE];
      
        // isEndOfWord is true if the node represents
        // end of a word
        boolean isEndOfWord;
         
        TrieNode(){
            isEndOfWord = false;
            for (int i = 0; i < ALPHABET_SIZE; i++)
                children[i] = null;
        }
    };
      
	 
	 /*Recursively delete a sequence key from the Trie node by node from the bottom up
		Possible conditions to deal with:
			Key may not be in trie. Delete operation should not modify trie.
			Key present as unique key (no part of key contains another key (prefix), nor the key itself is prefix of another key in trie). Delete all the nodes.
			Key is prefix key of another long key in trie. Unmark the leaf node.
			Key present in trie, having atleast one other key as prefix key. Delete nodes from end of key until first leaf node of longest prefix key.
	 
	 */
	 
	TrieNode remove(TrieNode root, String key, int depth)
    {
        // If tree is empty
        if (root == null)
            return null;
 
        // If last character of key is being processed
        if (depth == key.length()) {
 
            // This node is no longer the end of word after removal of given key
            if (root.isEndOfWord)
                root.isEndOfWord = false;
 
            // If the input key is not the prefix of any other word
            if (isEmpty(root)) {
                root = null;
            }
 
            return root;
        }
 
        // If not last character, recurse for the child obtained using ASCII value
        int index = key.charAt(depth) - 'a';
        root.children[index] = remove(root.children[index], key, depth + 1);
 
        // If root does not have any child (its only child got
        // deleted), and it is not end of another word.
        if (isEmpty(root) && root.isEndOfWord == false){
            root = null;
        }
 
        return root;
    }
	
	boolean isEmpty(TrieNode root)
    {
        for (int i = 0; i < ALPHABET_SIZE; i++)
            if (root.children[i] != null)
                return false;
        return true;
    }
     
	 /*Iteratively look for the pre-existence of the insert char, inserting a new TrieNode when none is found.
		Every character of the input string is inserted as a new TrieNode
		children is an array to next level of TrieNodes
		a character acts as a "hash key" into the children array through calculation char - 'a' = index
		if the string is new or an extension of an existing sequence we need to create new TrieNodes for every char and mark the end of the word for the last node -> boolean isEndOfWord = true
		if input string is a prefix of an existing sequence mark the last node of the key isEndOfWord = true
		
	 */
     void insert(String key)
    {
        int level, length = key.length(), index;
      
        TrieNode node = root;
		//continue for the length of the input string
        for (level = 0; level < length; level++)
        {	
            index = key.charAt(level) - 'a';
			//if the input character doesnt exist in the children array create a new TrieNode and place it in the children array at the index
            if (node.children[index] == null)
                node.children[index] = new TrieNode();
			//if the input character does exist in the children array drill into its children array
            node = node.children[index];
        }
      
        // mark last node as leaf/end of word
        node.isEndOfWord = true;
    }
      
    // same logic as insert replacing insert with comparison of search char with children array index content
    boolean search(String key)
    {
        int level, length = key.length(), index;
        TrieNode node = root;
      
        for (level = 0; level < length; level++){
            index = key.charAt(level) - 'a';
			//if the input character doesnt exist in the children array stop looking for it and fail the search
            if (node.children[index] == null)
                return false;
			//if the input character does exist in the children array drill into its children array
            node = node.children[index];
        }
      
        return (node.isEndOfWord);
    }
}