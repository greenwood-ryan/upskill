import java.util.*;

public class ChainedHashTable{
	
	private LinkedList<String>[] arr;
	
	public static void main(String[] args){
		ChainedHashTable cht = new ChainedHashTable();
		cht.test();
	}
	
	public ChainedHashTable(){
		arr = new LinkedList[10];
		for(int i=0; i<arr.length; i++)
			arr[i] = new LinkedList<String>();
	}
	
	void test(){
		String[] strs = { "100", "510", "170", "214", "268", "398",
		"235", "802", "900" };
		for(String s : strs)
			put(s);
		print();
		System.out.println("Remove 214 "+remove("214"));
		print();
		System.out.println("Get "+get("398"));
		System.out.println("Put 587 "+put("587"));
		print();
	}

	public void print(){
		System.out.println(Arrays.toString(arr));
	}

	//O(1+k)(number of items in list)
	public boolean put(String key){
		if(key == null)
			return false;
		int index = 0;
		try{
			index = hashIndex(key);
		}catch(NumberFormatException e){
			return false;
		}
		return arr[index].add(key);
	}
	
	//O(1+k)
	public String get(String key){
		if(key == null)
			return key;
		int index = 0;
		try{
			index = hashIndex(key);
		}catch(NumberFormatException e){
			return key;
		}
		return arr[index].get(arr[index].indexOf(key));	
	}
	
	//O(1+k)
	public boolean remove(String key){
		if(key == null)
			return false;
		int index = 0;
		try{
			index = hashIndex(key);
		}catch(NumberFormatException e){
			return false;
		}
		return arr[index].remove(key);	
	}

	private int hashIndex(String key) throws NumberFormatException{
		try{
			return Integer.parseInt(key) % arr.length;
		}catch(NumberFormatException e){
			throw new NumberFormatException("Input string "+key+" does not contain a valid input number");
		}
	}

}