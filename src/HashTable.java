import java.util.*;

/*
We want to reduce collisions in hash tables.  How?
	Use prime number sized arrays
	Increase array size to above said prime numbers
	Avoid clustering
	Use double hashing with a prime number
*/
public class HashTable{
	private final int HASH_PRIME = 5;
	private int size = 0;
	private String[] arr;
	
	public void setArr(String[] elementsToAdd){
		arr = new String[getNextPrime(elementsToAdd.length)];
		putAll(elementsToAdd, arr);
	}
	
	public void print(){
		System.out.println(Arrays.toString(arr));
	}
	
	public static void main(String[] args){
		HashTable ht = new HashTable();
		String[] elementsToAdd = { "100", "510", "170", "214", "268", "398",
		"235", "802", "900", "723"};//, "699", "1", "16", "999", "890",
		//"725", "998", "978", "988", "990", "989", "984", "320", "321",
		//"400", "415", "450", "50", "660", "624" };
		ht.setArr(elementsToAdd);
		//System.out.println("set all elements");
		ht.print();
		//ht.remove("214");
		//ht.print();
		//ht.put("754", ht.getArray());
		//ht.print();
		ht.remove("900");
		ht.print();
		ht.remove("510");
		ht.print();
		System.out.println("Load factor is "+ht.loadFactor());
		ht.reduceLoadFactor();
		System.out.println("Load factor is "+ht.loadFactor());
		ht.put("754", ht.getArray());
		ht.print();
		ht.remove("802");
		ht.print();
		ht.remove("170");
		ht.print();
		ht.remove("802");
		ht.print();
	}
	
	public String[] getArray(){
		return arr;
	}

//take the hit on remove.  O2n
	public void remove(String key){
		if(key == null)
			return;
		int index = indexHash(key);//this.findKey(key);
		System.out.println("key "+key+" index "+index);
		if(index == -1)
			return;
		arr[index] = null;
		System.out.println("arr[index] "+index+" value "+arr[index]);
		print();
		buildNewArray(arr.length);
		/*size = 0;
		//maintenance: reset the array and all indices after removal
		String[] temp = arr;
		arr = new String[arr.length];
		for(int i=0; i < temp.length; i++){
			if(temp[i] != null)
				put(temp[i], arr);
		}*/
	}
	
	
	public String get(String key) {
		// Find the keys original hash key
		int index = indexHash(key);//findKey(key);
		if(index == -1)
			return null;
		else 
			return arr[index];
	}
	

	
	public int findKey(String key){
		if(key ==  null)
			return -1;
		int index = indexHash(key);
		while(arr[index] != null){
			if(arr[index] == key)
				return index;
			++index;
			index %= arr.length;
		}
		return -1;
	}
	
	public void put(String key, String[] arr){
		if(key == null)
			return;
		
		//get index key
		int index = indexHash(key);
		//get random new target bucket key to avoid clustering using prime
		int step = stepHash(key);
		//cycle through array until we find empty space
		while(arr[index] != null){
			//push index out to random new target bucket
			//linear probe
			//System.out.println("Collision at "+index+" step out");
			index += step;
			++index;
			//if index greater than array length set index back to smaller key
			index %= arr.length;
		}
		//assign key to location
		arr[index] = key;
		size++;
	}
	
	
	

	//randomize index into which we put and use randomized linear probe to avoid collisions
	//at worst O n
	private int indexHash(String key){
		return Integer.parseInt(key) % arr.length;
	}
	
	private int stepHash(String key){
		return HASH_PRIME - (indexHash(key) % HASH_PRIME);
	}
		
	
	
	//increase array size to the prime number provided
	private void reduceLoadFactor(){
		int newArrSize = new Double(increaseArraySize()).intValue();
		//get new array size
		int primeSize = getNextPrime(newArrSize);
		buildNewArray(primeSize);
	}

	private double loadFactor(){
		return (double)size/(double)arr.length;
	}

	
	private double increaseArraySize(){
		return (double)loadFactor()/0.5*arr.length;
	}

	
	//to increase the array size we will need to identify the next prime following the number
	private int getNextPrime(int minArrSizeNeeded){
		//cycle through this until you find a prime number
		for(int i=minArrSizeNeeded; true; i++){
			if(isPrime(i))
				return i;
		}
	}

	//this will be used when finding the next biggest prime number for array resizing
	private boolean isPrime(int number){
		//check if even number
		if(number % 2 == 0)
			return false;
		//check against odd numbers after 2
		for(int i=3; i*i <= number; i+=2){
			if(number % i == 0)
				return false;
		}
		return true;
	}

	//build new array 
	private void buildNewArray(int size){
		//String[] cleanArr = removeEmptySpaces();
		String[] newArr = new String[size];
		//avoid clustering with double hash - spread out the targeted buckets
		putAll(arr, newArr);
		arr = newArr;
	}
	
	private String[] removeEmptySpaces(){
		ArrayList list = new ArrayList<String>(arr.length);
		//if isn't null add to list
		for(String obj : arr)
			if(obj != null)
				list.add(obj);
		return (String[])list.toArray(new String[list.size()]);
	}
	

	
	public void putAll(String[] toInsert, String[] newArr){
		size = 0;
		for(int i=0; i<toInsert.length; i++){
			put(toInsert[i], newArr);
		}
	}
}