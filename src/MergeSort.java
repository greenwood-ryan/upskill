import java.util.*;
public class MergeSort{
	
	public static void main(String[] args){
		MergeSort ms = new MergeSort();
		ms.testSort();
	}
	
	void testSort(){
		int[] arr = {7,2,4,6,1,3,5};
		System.out.println(Arrays.toString(arr));
		sort(arr);
		System.out.println(Arrays.toString(arr));
	}
	
	public void sort(int[] arr){
		int length;
		if(arr == null)
			return;
		length = arr.length;
		if(length < 2)
			return;
			
		int[] left, right;
		int mid = length/2;
		left = Arrays.copyOfRange(arr, 0, mid);
		right = Arrays.copyOfRange(arr, mid, length);
		sort(left);
		sort(right);
		merge(left, right, arr);
	}
	
	void merge(int[] left, int[] right, int[] arr){
		int lenLeft = left.length, lenRight = right.length, len = arr.length;
		int i, j, k;
		i = j = k = 0;
		//progress through both left and right arrays until one or both end
		while(i<lenLeft && j<lenRight){
			if(left[i] <= right[j]){
				arr[k] = left[i];
				i += 1;
			}else{
				arr[k] = right[j];
				j += 1;
			}
			k += 1;
		}
		//if arr length odd number and more in left split
		while(i<lenLeft){
			arr[k] = left[i];
			i += 1;
			k += 1;
		}
		//if arr length odd number and more in left split
		while(j<lenRight){
			arr[k] = right[j];
			j += 1;
			k += 1;
		}
	}
}