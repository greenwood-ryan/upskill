import java.util.*;

public class BigONotation{
// Big O notation is a way to measure how well a
/*
The most common attributes of logarithmic running-time function are that:

the choice of the next element on which to perform some action is one of several possibilities, and
only one will need to be chosen.
or
the elements on which the action is performed are digits of n

This is why, for example, looking up people in a phone book is O(log n). You don't need to check every person in the phone book to find the right one; instead, you can simply divide-and-conquer by looking based on where their name is alphabetically, and in every section you only need to explore a subset of each section before you eventually find someone's phone number.

Of course, a bigger phone book will still take you a longer time, but it won't grow as quickly as the proportional increase in the additional size.

We can expand the phone book example to compare other kinds of operations and their running time. We will assume our phone book has businesses (the "Yellow Pages") which have unique names and people (the "White Pages") which may not have unique names. A phone number is assigned to at most one person or business. We will also assume that it takes constant time to flip to a specific page.

Here are the running times of some operations we might perform on the phone book, from fastest to slowest:

O(1) (in the worst case): Given the page that a business's name is on and the business name, find the phone number.

O(1) (in the average case): Given the page that a person's name is on and their name, find the phone number.

O(log n): Given a person's name, find the phone number by picking a random point about halfway through the part of the book you haven't searched yet, then checking to see whether the person's name is at that point. Then repeat the process about halfway through the part of the book where the person's name lies. (This is a binary search for a person's name.)

O(n): Find all people whose phone numbers contain the digit "5".

O(n): Given a phone number, find the person or business with that number.

O(n log n): There was a mix-up at the printer's office, and our phone book had all its pages inserted in a random order. Fix the ordering so that it's correct by looking at the first name on each page and then putting that page in the appropriate spot in a new, empty phone book.

For the below examples, we're now at the printer's office. Phone books are waiting to be mailed to each resident or business, and there's a sticker on each phone book identifying where it should be mailed to. Every person or business gets one phone book.

O(n log n): We want to personalize the phone book, so we're going to find each person or business's name in their designated copy, then circle their name in the book and write a short thank-you note for their patronage.

O(n2): A mistake occurred at the office, and every entry in each of the phone books has an extra "0" at the end of the phone number. Take some white-out and remove each zero.

O(n · n!): We're ready to load the phonebooks onto the shipping dock. Unfortunately, the robot that was supposed to load the books has gone haywire: it's putting the books onto the truck in a random order! Even worse, it loads all the books onto the truck, then checks to see if they're in the right order, and if not, it unloads them and starts over. (This is the dreaded bogo sort.)

O(nn): You fix the robot so that it's loading things correctly. The next day, one of your co-workers plays a prank on you and wires the loading dock robot to the automated printing systems. Every time the robot goes to load an original book, the factory printer makes a duplicate run of all the phonebooks! Fortunately, the robot's bug-detection systems are sophisticated enough that the robot doesn't try printing even more copies when it encounters a duplicate book for loading, but it still has to load every original and duplicate book that's been printed.
*/

	private int[] theArray;

	private int arraySize;

	private int itemsInArray = 0;

	static long startTime;

	static long endTime;

	public static void main(String[] args) {

		/*
		 * 0(1) Test BigONotation testAlgo = new BigONotation(10);
		 * 
		 * testAlgo.addItemToArray(10);
		 * 
		 * System.out.println(Arrays.toString(testAlgo.theArray));
		 */

		BigONotation testAlgo2 = new BigONotation(10);
		testAlgo2.generateRandomArray();

		//BigONotation testAlgo3 = new BigONotation(200000);
		//testAlgo3.generateRandomArray();

		//BigONotation testAlgo4 = new BigONotation(30000);
		//testAlgo4.generateRandomArray();

		//BigONotation testAlgo5 = new BigONotation(400000);
		//testAlgo5.generateRandomArray();

		/*
		 * O(N) Test
		 * 
		 * testAlgo2.linearSearchForValue(20);
		 * 
		 * testAlgo3.linearSearchForValue(20);
		 * 
		 * testAlgo4.linearSearchForValue(20);
		 * 
		 * testAlgo5.linearSearchForValue(20);
		 */

		// O(N^2) Test
		/*
		 * testAlgo2.bubbleSort();
		 * 
		 * testAlgo3.bubbleSort();
		 * 
		 * testAlgo4.bubbleSort();
		 * 
		 * // 0 (log N) Test
		 * 
		 * testAlgo2.binarySearchForValue(20);
		 * testAlgo3.binarySearchForValue(20);
		 */

		// O (n log n) Test

		startTime = System.currentTimeMillis();
		
		System.out.println(Arrays.toString(testAlgo2.theArray));

		testAlgo2.quickSort(0, testAlgo2.itemsInArray);
		System.out.println(Arrays.toString(testAlgo2.theArray));

		endTime = System.currentTimeMillis();

		System.out.println("Quick Sort Took " + (endTime - startTime));

	}

	// O(1) An algorithm that executes in the same
	// time regardless of the amount of data
	// This code executes in the same amount of
	// time no matter how big the array is

	public void addItemToArray(int newItem) {

		theArray[itemsInArray++] = newItem;

	}

	// 0(N) An algorithm thats time to complete will
	// grow in direct proportion to the amount of data
	// The linear search is an example of this

	// To find all values that match what we
	// are searching for we will have to look in
	// exactly each item in the array

	// If we just wanted to find one match the Big O
	// is the same because it describes the worst
	// case scenario in which the whole array must
	// be searched

	public void linearSearchForValue(int value) {

		boolean valueInArray = false;
		String indexsWithValue = "";

		startTime = System.currentTimeMillis();

		for (int i = 0; i < arraySize; i++) {

			if (theArray[i] == value) {
				valueInArray = true;
				indexsWithValue += i + " ";
			}

		}

		System.out.println("Value Found: " + valueInArray);

		endTime = System.currentTimeMillis();

		System.out.println("Linear Search Took " + (endTime - startTime));

	}

	// O(N^2) Time to complete will be proportional to
	// the square of the amount of data (Bubble Sort)
	// Algorithms with more nested iterations will result
	// in O(N^3), O(N^4), etc. performance

	// Each pass through the outer loop (0)N requires us
	// to go through the entire list again so N multiplies
	// against itself or it is squared

	public void bubbleSort() {

		startTime = System.currentTimeMillis();

		for (int i = arraySize - 1; i > 1; i--) {

			for (int j = 0; j < i; j++) {

				if (theArray[j] > theArray[j + 1]) {

					swapValues(j, j + 1);

				}
			}
		}

		endTime = System.currentTimeMillis();

		System.out.println("Bubble Sort Took " + (endTime - startTime));
	}

	// O (log N) Occurs when the data being used is decreased
	// by roughly 50% each time through the algorithm. The
	// Binary search is a perfect example of this.

	// Pretty fast because the log N increases at a dramatically
	// slower rate as N increases

	// O (log N) algorithms are very efficient because increasing
	// the amount of data has little effect at some point early
	// on because the amount of data is halved on each run through

	public void binarySearchForValue(int value) {

		startTime = System.currentTimeMillis();

		int lowIndex = 0;
		int highIndex = arraySize - 1;

		int timesThrough = 0;

		while (lowIndex <= highIndex) {

			int middleIndex = (highIndex + lowIndex) / 2;

			if (theArray[middleIndex] < value)
				lowIndex = middleIndex + 1;

			else if (theArray[middleIndex] > value)
				highIndex = middleIndex - 1;

			else {

				System.out.println("\nFound a Match for " + value
						+ " at Index " + middleIndex);

				lowIndex = highIndex + 1;

			}

			timesThrough++;

		}

		// This doesn't really show anything because
		// the algorithm is so fast

		endTime = System.currentTimeMillis();

		System.out.println("Binary Search Took " + (endTime - startTime));

		System.out.println("Times Through: " + timesThrough);

	}

	// O (n log n) Most sorts are at least O(N) because
	// every element must be looked at, at least once.
	// The bubble sort is O(N^2)
	// To figure out the number of comparisons we need
	// to make with the Quick Sort we first know that
	// it is comparing and moving values very
	// efficiently without shifting. That means values
	// are compared only once. So each comparison will
	// reduce the possible final sorted lists in half.
	// Comparisons = log n! (Factorial of n)
	// Comparisons = log n + log(n-1) + .... + log(1)
	// This evaluates to n log n
	
	//Partitions array so smaller numbers on on the left and larger on the right
	//Recursively sends small parts of larger arrays to itself and partitions again
	//It halves the search with every partition.

	public void quickSort(int left, int right) {
		//everything is sorted so return.
		if (right - left <= 0)
			return;
		else {
			//some versions look for a central point in the array for a pivot.  Questionable improvement.
			int pivot = theArray[right];
			int pivotLocation = partitionArray(left, right, pivot);//partition and get new pivot position.
			quickSort(left, pivotLocation - 1);//sort left side
			quickSort(pivotLocation + 1, right);//sort right side
		}
	}
	//each time you partition you are halving the volume to be checked
	//left pointer looks for item greater than pivot, stops waits for right pointer to find
	//value less than pivot and then those two values are swapped.
	public int partitionArray(int left, int right, int pivot) {
		int leftPointer = left - 1;
		int rightPointer = right;
		while (true) {
			while (theArray[++leftPointer] < pivot)//search from left for value greater than or equal to pivot value stop when found
				;
			while (rightPointer > 0 && theArray[--rightPointer] > pivot)//search from right for value less than pivot value stop when found
				;
			if (leftPointer >= rightPointer) {//pointers have met can't go past each other so break loop in order to get another pivot point to continue the sort.
				break;
			} else {
				swapValues(leftPointer, rightPointer);//swap values at left and right pointers
			}
		}
		swapValues(leftPointer, right);//pointers have met so make far right position value of array the left pointer position value and return left pointer position
		return leftPointer;
	}
	
	public void swapValues(int indexOne, int indexTwo) {
		int temp = theArray[indexOne];
		theArray[indexOne] = theArray[indexTwo];
		theArray[indexTwo] = temp;
	}
	
	BigONotation(int size) {

		arraySize = size;

		theArray = new int[size];

	}

	public void generateRandomArray() {

		for (int i = 0; i < arraySize; i++) {

			theArray[i] = (int) (Math.random() * 1000) + 10;

		}

		itemsInArray = arraySize - 1;

	}


}