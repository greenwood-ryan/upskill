import java.util.*;

public class AllSearches{

	public static void main(String[] args){
		AllSearches t = new AllSearches();
		t.testLinearSearch();
		t.testBinarySearch();
	}
	
	void testBinarySearch(){
		int count = 10000;
		int[] arr = generateRandomArray(count);
		Arrays.sort(arr);
		int value = arr[count/2];
		System.out.println("search value "+value);
		long startTime = System.currentTimeMillis();
		int index = binarySearch(value, arr);
		long endTime = System.currentTimeMillis();
		System.out.println("Duration to binary search value "+arr[index]+" was: "+(endTime-startTime)+" milliseconds");

	}
	//O log n
	public int binarySearch(int value, int[] arr){
		if(arr == null || arr.length < 1)
			return Integer.MIN_VALUE;
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

	void testLinearSearch(){
		int count = 10000;
		int[] arr = generateRandomArray(count);
		int value = arr[count/2];
		long startTime = System.currentTimeMillis();
		int index = linearSearch(value, arr);
		long endTime = System.currentTimeMillis();
		System.out.println("Duration to linear search value "+arr[index]+" was: "+(endTime-startTime)+" milliseconds");

	}
	
	//O n
	public int linearSearch(int value, int[] arr){
		if(arr == null || arr.length < 1)
			return Integer.MIN_VALUE;
		for(int i=0; i<arr.length; i++)
			if(arr[i] == value)
				return i;
		return Integer.MIN_VALUE;//not found
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
}