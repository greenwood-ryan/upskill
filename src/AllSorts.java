import java.util.*;

public class AllSorts{
	public static void main(String[] args){
		AllSorts at = new AllSorts();
		at.testBubbleSort();
		at.testSelectionSort();
		at.testInsertionSort();
		at.testShellSort();
		at.testRecFact();
		at.testMergeSort();
		at.testQuickSort();
		at.testCountingSort();
		at.testRadixSort();
		at.testReverseMergeSort();
		at.testInsertionSortRecursive();
		at.testRadixSortStrings();
		at.testBucketSort();
	}
	
	void testBucketSort(){
		int[] arr = {55, 31, 10, 44, 62, 28, 78, 91};
		long startTime = System.currentTimeMillis();
		bucketSort(arr);
		//binarySearch(value, arr);
		long endTime = System.currentTimeMillis();
		System.out.println("\n\nDuration to bucket sort "+arr.length+" items was: "+(endTime-startTime)+" milliseconds");
		for(int value: arr)
			System.out.print(value+" ");
	}

	//bucket sort using JDK list sort() method
	public void bucketSort(int[] toSort){
		if(toSort == null || toSort.length < 1)
			return;
		//1. Scatter the values as first part of sort
		List<Integer>[] buckets = new List[10];
		for(int i=0; i<buckets.length; i++)
			buckets[i] = new ArrayList<Integer>();
		//hash the values to ensure that the tens value ends up in the appropriate bucket for its value
		for(Integer value: toSort){
			int index = (int) value / 10;
			buckets[index].add(value);
		}
		//2. sort the contents of each bucket using list.sort() which is a stable, adaptive, iterative mergesort
		//3. merge bucket contents back to array toSort
		int counter = 0;
		for(List bucket: buckets){
			bucket.sort(null);
			for(int i=0; i < bucket.size(); i++){
				toSort[counter] = (Integer) bucket.get(i);
				counter++;
			}
		}
	}
	
	void testRadixSortStrings(){
	String[] arr = {"bcdef","dbaqc","abcde","omadd","bbbbb"};
	radixSortStrings(arr, 26, 5);
	System.out.println("\nRadix sort");
	for(String i: arr)
		System.out.print(i+" ");
	}
	
	public void radixSortStrings(String[] arr, int radix, int width){
		if(arr == null || arr.length < 2)
			return;
		for(int i=0; i<width; i++){
			radixSingleSortStrings(arr, i, radix);
		}
	}
	
	private void radixSingleSortStrings(String[] arr, int position, int radix){
		//countArr will hold count of occurrence values for digits 0 to 9
		int[] countArr = new int[radix];
		//this loop will result in recording in the count array all occurrences of decimal values occuring at decimal positions ones, tens, hundreds and thousands.  Once this is done the method will proceed as a normal Counting Sort
		for(String value : arr){
			countArr[getDigitStrings(position, value)]++;
		}
		//to preserve order we have to make this algorithm stable. We have to sum the counts not only of the occurrence values but also the count of values that have have the actual digit AND digits of lesser value.  So...
		for(int j=1; j < radix; j++){
			countArr[j] += countArr[j-1];
		}
		//now copy values into a temp array working right to left to preserve order
		String[] temp = new String[arr.length];
		for(int k = temp.length-1; k >= 0; k--){
			temp[--countArr[getDigitStrings(position, arr[k])]] = arr[k];
		}
		//now we have to copy the contents of temp into original arr
		for(int m=0; m < temp.length; m++){
			arr[m] = temp[m];
		}
	}
	/*
		method to set the position in the count array to increment
		e.g. arr = {"bcdef","dbaqc","abcde","omadd","bbbbb"}
		if we deal with value bcdef and we want to record what char sits in the third position from right (d)
		we use string length - 1 - position to obtain char then we retrieve the char index position in the radix 
		(alphabet) which we return
	*/
	
	private int getDigitStrings(int position, String value){
		//System.out.println("position = "+position+" value = "+value);
		String alphabet = "abcdefghijklmnopqrstuvwxyz";
		char subject = value.charAt(value.length() - 1 - position);
		return alphabet.indexOf(subject);
	}
	
	public void testInsertionSortRecursive(){
		int[] arr = {-55, 1, 10, 4, -22};
		insertionSortRecursive(arr, arr.length);
		for(int i: arr)
			System.out.print(i+" ");
		System.out.println();
	}
	
	//On2 is kak
	public void insertionSortRecursive(int[] arr, int count){
		if(arr == null || arr.length < 2)
			return;
		if(count < 2)//break point -> 1 item will mean array is sorted
			return;
		//recursively sort 
		insertionSortRecursive(arr, count - 1);
		int next = arr[count - 1];
		int j;
		for(j = count - 1; j > 0 && arr[j-1] > next; j--){
				arr[j] = arr[j-1];
		}
		arr[j] = next;
	}

	void testRadixSort(){
	int[] arr = {4725, 4586, 1330, 8792, 1594, 5729};
	radixSort(arr, 10, 4);
	System.out.println();
	for(int i: arr)
		System.out.print(i+" ");
	}
	
	public void radixSort(int[] arr, int radix, int width){
		if(arr == null || arr.length < 2)
			return;
		for(int i=0; i<width; i++){
			radixSingleSort(arr, i, radix);
		}
	}
	
	private void radixSingleSort(int[] arr, int position, int radix){
		//countArr will hold count of occurrence values for digits 0 to 9
		int[] countArr = new int[radix];
		//this loop will result in recording in the count array all occurrences of decimal values occuring at decimal positions ones, tens, hundreds and thousands.  Once this is done the method will proceed as a normal Counting Sort
		for(int value : arr){
			countArr[getDigit(position, value, radix)]++;
		}
		//to preserve order we have to make this algorithm stable. We have to sum the counts not only of the occurrence values but also the count of values that have have the actual digit AND digits of lesser value.  So...
		for(int j=1; j < radix; j++){
			countArr[j] += countArr[j-1];
		}
		//now copy values into a temp array working right to left to preserve order
		int[] temp = new int[arr.length];
		for(int k=temp.length-1; k >= 0; k--){
			temp[--countArr[getDigit(position, arr[k], radix)]] = arr[k];
		}
		//now we have to copy the contents of temp into original arr
		for(int m=0; m < temp.length; m++){
			arr[m] = temp[m];
		}
	}
	/*
		method to set the position in the count array to increment
		e.g. arr = {4725, 4586, 1330, 8792, 1594, 5729}
		if we deal with value 4725 and we want to record what decimal number sits in the hundreds position
		we use (value / (int) Math.pow(10, position)) % radix -> hundreds position is at position 2 from right
		(4725 / 10^2) % 10 = (4725/100) % 10 = 47 % 10 = 7
		7 is the decimal at the hundreds position which we return
	*/
	
	private int getDigit(int position, int value, int radix){
		return (value / (int) Math.pow(radix, position)) % radix;
	}
	
	void testCountingSort(){
	int[] arr = {2,5,9,8,2,8,7,10,4,3};
	arr = countingSort(arr);
	for(int i: arr)
		System.out.print(i+" ");
	}
	
	public int[] countingSort(int[] arr){
		if(arr == null || arr.length < 2)
			return arr;
		int index = 0;
		int[] temp = new int[10];
		for(int i=0; i<arr.length; i++){
			temp[arr[i]-1]++;
		}
		for(int j=0; j<temp.length; j++){
			for(int k = temp[j]; k>0; k--){
				arr[index] = j+1;
				index++;
			}
		}
		return arr;
	}
	
	void testQuickSort(){
		int count = 100;
		int[] arr = generateRandomArray(count);
		int value = arr[count/3];
		long startTime = System.currentTimeMillis();
		quickSort(0, arr.length-1, arr);
		//binarySearch(value, arr);
		long endTime = System.currentTimeMillis();
		System.out.println("Duration to quick sort "+count+" items was: "+(endTime-startTime)+" milliseconds");
		

	}
	


	//0 n log n
	public void quickSort(int left, int right, int[] arr){
		//check if sorted already
		if((right - left) <= 0)
			return; //sorted
		else{//not sorted
			//set pivot value to value at far right array
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
		//create pointers.  Left starts outside the array range
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
			swap(arr, leftPointer, rightPointer);
		}
		//can't go anymore make pivot far right value and return
		swap(arr, leftPointer, right);
		return leftPointer;
	}

	void testReverseMergeSort(){
		//int count = 100;
		//int[] arr = generateRandomArray(count);
		int[] arr = {-55, 1, 10, 4, -22};
		long startTime = System.currentTimeMillis();
		reverseMergeSort(arr);
		//binarySearch(value, arr);
		long endTime = System.currentTimeMillis();
		System.out.println("\nDuration to merge sort "+arr.length+" items was: "+(endTime-startTime)+" milliseconds");
		for(int value: arr)
			System.out.print(value+" ");
	}

	
	//O n log n
	public void reverseMergeSort(int[] arr){
		if(arr == null || arr.length < 2) //base case
			return;
		int mid = arr.length/2;
		int[] left = Arrays.copyOfRange(arr, 0, mid);
		int[] right = Arrays.copyOfRange(arr, mid, arr.length);
		reverseMergeSort(right);
		reverseMergeSort(left);
		reverseMerge(arr, left, right);
	}
	
	void reverseMerge(int[] arr, int[] left, int[] right){
		if(arr == null || left == null || right == null)
			return;
		if(left[left.length-1] >= right[0])
			return;
		//array lengths
		int lenLeft = left.length, lenRight = right.length, len = arr.length;
		//declare and initialize pointers
		int i, j, k;
		i = j = k = 0;
		
		//cycle through left and right to start merging into array while both have values
		while(i < lenLeft && j < lenRight){
			if(left[i] >= right[j]){
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
	
	
	void testMergeSort(){
		int count = 100;
		int[] arr = generateRandomArray(count);
		int value = arr[count/3];
		long startTime = System.currentTimeMillis();
		mergeSort(arr);
		//binarySearch(value, arr);
		long endTime = System.currentTimeMillis();
		System.out.println("Duration to merge sort "+count+" items was: "+(endTime-startTime)+" milliseconds");
	}

	
	//O n log n
	public void mergeSort(int[] arr){
		//base case
		if(arr == null || arr.length < 2) 
			return;
		//split input array into about equal left and right halves
		int mid = arr.length/2;
		int[] left = Arrays.copyOfRange(arr, 0, mid);
		int[] right = Arrays.copyOfRange(arr, mid, arr.length);
		//recurse to repeat the split of splits
		mergeSort(left);
		mergeSort(right);
		//iteratively merge the splits and sort whilst doing this 
		merge(arr, left, right);
	}
	
	void merge(int[] arr, int[] left, int[] right){
		//check inputs
		if(arr == null || left == null || right == null)
			return;
		//if far right of left is less than or equal to far left of right then they are sorted
		if(left[left.length-1] <= right[0])
			return;
		//declare and initialize variables for array lengths
		int lenLeft = left.length, lenRight = right.length, len = arr.length;
		//declare and initialize pointer variables
		int i, j, k;
		i = j = k = 0;
		//cycle through left and right to start merging into array while both have values
		while(i < lenLeft && j < lenRight){
			//for each split compare each value in sequence to value in the other split add lowest first
			//for sort ascending
			if(left[i] < right[j]){
				arr[k] = left[i];
				i++;
			}else{
				arr[k] = right[j];
				j++;
			}
			k++;
		}
		//deal with any left overs in left
		while(i < lenLeft){
			arr[k] = left[i];
			i++;
			k++;
		}
		//deal with any left overs in right
		while(j < lenRight){
			arr[k] = right[j];
			j++;
			k++;
		}
	}
	
	public void testRecFact(){
		int num = 3;
		System.out.println("The recursive factorial of "+num+" is "+recFact(num));
	}
	
	
	/*	recursive method calls are put onto the call stack
		if we use num = 3 then first call is executed and waits until all subsequent calls have returned
		before it can complete.  Calls return in the reverse order of call.
		NOTE: ITERATIVE APPROACH USES LESS MEMORY
	*/
	
	public int recFact(int num){
		//condition to end the recursion AKA base case.  The point of return or unwind
		if(num == 0)
			return 1;
		
		return num * recFact(num - 1);
	}

	public void testShellSort(){
		int[] arr = {-55, 1, 10, 4, -22};
		shellSort(arr);
		for(int i: arr)
			System.out.print(i+" ");
		System.out.println();
	}
	
	//On^2 is kak
	public void shellSort(int[] arr){
		if(arr == null || arr.length < 2)
			return;
		int temp = 0;
		int j = 0, k = 0;
		for(int i = arr.length/2; i > 0; i /= 2){
			for(j = i; j < arr.length; j++){
				temp = arr[j];
				k = j;
				while(k >= i && arr[k - i] > temp){
					arr[k] = arr[k-i];
					k -= i;
				}
				arr[k] = temp;
			}
		}
	}
	
	public void testInsertionSort(){
		int[] arr = {-55, 1, 10, 4, -22};
		insertionSort(arr);
		for(int i: arr)
			System.out.print(i+" ");
		System.out.println();
	}
	
	//On^2 is kak
	public void insertionSort(int[] arr){
		if(arr == null || arr.length < 2)
			return;
		int temp = 0;
		int j = 0;
		for(int i = 1; i < arr.length; i++){
			temp = arr[i];
			for(j = i; j > 0 && arr[j-1] > temp; j--){
					arr[j] = arr[j-1];
			}
			arr[j] = temp;
		}
	}
	
	public void testSelectionSort(){
		int[] arr = {-55, 1, 10, 4, -22};
		selectionSort(arr);
		for(int i: arr)
			System.out.print(i+" ");
		System.out.println();
	}
	
	//On^2 is kak
	public void selectionSort(int[] arr){
		if(arr == null || arr.length < 2)
			return;
		int unsortedLength = arr.length;
		int largestValIndex = 0;
		while(unsortedLength > 1){
			for(int i=0; i < unsortedLength - 1; i++){
				if(arr[i] < arr[i+1])
					largestValIndex = i+1;
			}
			swap(arr, largestValIndex, unsortedLength - 1);
			unsortedLength--;
		}
	}

	public void testBubbleSort(){
		int[] arr = {-55, 1, 10, 4, -22};
		bubbleSort(arr);
		for(int i: arr)
			System.out.print(i+" ");
		System.out.println();
	}
	
	//On^2 is kak
	public void bubbleSort(int[] arr){
		if(arr == null || arr.length < 2)
			return;
		int unsortedLength = arr.length;
		while(unsortedLength > 1){
			for(int i=0; i < unsortedLength - 1; i++){
				if(arr[i] > arr[i+1])
					swap(arr, i, i+1);
			}
			unsortedLength--;
		}
	}
	
	private void swap(int[] arr, int one, int two){
		int temp = arr[two];
		arr[two] = arr[one];
		arr[one] = temp;
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