import java.util.*;
import java.util.regex.*;

public class ArbTests{
	
	public static void main(String[] args){
		ArbTests at = new ArbTests();
		at.testCanSegment();
		at.testNineMakeChange();
		//at.testFindKth();
		at.testFindSubsets();
		at.testBubbleSort();
		at.testSelectionSort();
		at.testInsertionSort();
		at.testShellSort();
	}

	public void testShellSort(){
		int[] arr = {-55, 1, 10, 4, -22};
		shellSort(arr);
		for(int i: arr)
			System.out.print(i+" ");
		System.out.println();
	}
	
	//On2 is kak
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
	
	//On2 is kak
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
	
	//On2 is kak
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
	
	//On2 is kak
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
	
	public void testFindSubsets(){
       
      //Main List for storing all subsets
      List<List<Integer>> subset = new ArrayList<>();
       
      // Input ArrayList
      ArrayList<Integer> input = new ArrayList<>();
      input.add(1);
      input.add(2);
      input.add(3);
       
      findSubsets(subset, input, new ArrayList<>(), 0);
 
      // Comparator is used so that all subset get
      // sorted in ascending order of values
        Collections.sort(subset, (o1, o2) -> {
            int n = Math.min(o1.size(), o2.size());
            for (int i = 0; i < n; i++) {
                if (o1.get(i) == o2.get(i)){
                    continue;
                }else{
                    return o1.get(i) - o2.get(i);
                }
            }
            return 1;
        });
       
       
      // Printing Subset
      for(int i = 0; i < subset.size(); i++){
          for(int j = 0; j < subset.get(i).size(); j++){
              System.out.print(subset.get(i).get(j) + " ");
          }
          System.out.println();
      }
       
    }
	
	
	public static void findSubsets(List<List<Integer>> subset, ArrayList<Integer> nums, ArrayList<Integer> output, int index)
    {
      // Base Condition
        if (index == nums.size()) {
            subset.add(output);
 		System.out.println("added = "+output);
            return;
        }
       
        // Not Including Value which is at Index
 		System.out.println("here 1: index = "+index);
       findSubsets(subset, nums, new ArrayList<>(output), index + 1);
 		System.out.println("here 2: index = "+index);
        // Including Value which is at Index
        output.add(nums.get(index));
 		System.out.println("here 3: index = "+index);
       findSubsets(subset, nums, new ArrayList<>(output), index + 1);
 		System.out.println("here 4: index = "+index);
   }
	
/*	public void testFindKth(){
		int[] arr = {1,2,3};
		int k = 4;
		System.out.println(Arrays.asList(findKth(arr, k)));
	}
	
  int factorial(int n) {
    if (n == 0 || n == 1) return 1;
    return n * factorial(n -1 );
  }

  void find_kth_permutation(List<Character> v, int k, StringBuilder result) {
  
    if (v.isEmpty()) {
      return;
    }
  
    int n = v.size();
    // count is number of permutations starting with first digit
    int count = factorial(n - 1);
    int selected = (k - 1) / count;
  
    result.append(v.get(selected));
    v.remove(selected);

    k = k - (count * selected);
    find_kth_permutation(v, k, result);
  }
  
  static String get_permutation(int n, int k) {
    List<Character> v = new ArrayList<Character>();
    char c = '1';
    for (int i = 1; i <= n; ++i) {
      v.add(c);
      c++;
    }
  
    StringBuilder result = new StringBuilder();
    find_kth_permutation(v, k, result);
    return result.toString();
  }
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
				System.out.println("j = "+j+", permutation[j] = "+permutation[j]+" and dens[i] = "+dens[i]);
				System.out.println("permutation[j - dens[i]] = "+permutation[j - dens[i]]);
                 permutation[j] += permutation[j - dens[i]];
				System.out.println("now permutation[j] = "+permutation[j]+"\n");
           }
			System.out.println("outer loop next ");
		}
 
        return permutation[amount];
    }

public void testCanSegment(){
		ArrayList<String> dict = new ArrayList();
		dict.add("apple");
		dict.add("apple");
		dict.add("pear");
		dict.add("pie");
		System.out.println("Can segment applepie:  "+canSegment("applepie", dict));
		System.out.println("Can segment applepeer:  "+canSegment("applepeer", dict));
	}
	
	public boolean canSegment(String s, ArrayList<String> dict){
		//check input valid
		if(s == null || s.length()< 2 || dict == null || dict.size() < 1)
			return false;
		boolean canSegment = false;
		for(int i=0; i<s.length(); i++){
			String firstWord = s.substring(0, i);
			String secondWord = s.substring(i, s.length());
			if(dict.contains(firstWord) && dict.contains(secondWord)){
				canSegment = true;
				return canSegment;
			}
		}
		return canSegment;
	}
	

}