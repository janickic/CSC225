// CSC 225, Assignment 2
// CountInversions.java
// Student: Camille Janicki

/*
* Input: An array A of n integers in the range 1-n
* Output: An integer, corresponding to the number of inversions in A
* Runtime of implemented algorithm: O(n)
* Algorithm uses insertion sort to count inversions
* Since there is always going to be less inversions than elements in the array, then the time complexity is O(n)
*/

import java.io.File;
import java.util.*;

public class CountInversions{
	
	//Using insertion sort, goes through the array to count inversions
	public static int CountInversions(int[] arr)
	{
		int i=0;
		int j=0;
		int element=0;
		int count=0;
		
		//Goes through array
		for(i=0; i<=arr.length-1; i++)
		{
			element=arr[i];
			j=i;
			//enters this loop if left element is larger than right element
			while(j>0 && arr[j-1]>element)
			{
				arr[j]=arr[j-1];
				j=j-1;
				//counts when elements are inverted
				count++; 
			}
			arr[j]=element;
		}
		return count;
	}
	
	
/*
* This second algorithm is implemented using MergeSort and runs in O(nlogn)
* Algorithm uses mergesort to divide the array, recur then merge the array while counting for inversions.
	
	//Using recusion for divide and conquer (mergesort) algorithm
	public static int countInv(int[] arr)
	{
		
		if(arr.length<2)	//Base Case
		{
			return 0;
		}
		//dividing arr into two smaller arrays
		int[] left=divLeft(arr);
		int[] right=divRight(arr);
		
		return countInv(left)+ countInv(right)+ mergeCount(left, right, arr);
	}
	
	
	//Dividing the array into a left sub-array
	public static int[] divLeft(int[] arr)
	{
		int mid=arr.length/2;
		int[] left= new int[mid];
		for(int i=0; i<mid ; i++)
		{
			left[i]=arr[i];
		}
		return left;
	}
	
	//Dividing the array into a right sub-array
	public static int[] divRight(int[] arr) 
	{
		int mid=arr.length/2;
		int[] right= new int[arr.length-mid];
		for(int i=0; i<arr.length-mid; i++)
		{
			right[i]=arr[i+mid];
		}
		return right;
	}
	
	//Merging the two sub-arrays back into original array while counting the number of inversions
	public static int mergeCount(int[] left, int[] right, int[] arr)
	{
		int counter=0;
		int i=0;	//left array index
		int j=0;	//right array index
		int k=i+j; 	//arr array index
		while(left.length>i && right.length>j)
		{
			if(left[i]>right[j])
			{
				arr[k]=right[j];
				j++;
				k++;
				//count inversions
				counter=counter+left.length-i;
			}else 
			{
				arr[k]=left[i];
				i++;
				k++;
			}
		}
		if(i==left.length)
		{
			for(int a=j; a<right.length; a++)
			{
				arr[k]=right[j];
				j++;
				k++;
			}
		}else
		{
			for(int b=i; b<left.length; b++)
			{
				arr[k]=left[i];
				i++;
				k++;
			}
		}
		return counter;
	}	
	
	*/
		
	// Tests the implementation by getting test data 
	// or reading it from a file and handles errors (runtime not considered)
	public static void main(String[] args)
	{
		//Reads file inserted in args on the command line and catches any exceptions
		Scanner s;
		if (args.length > 0){
			try{
				s = new Scanner(new File(args[0]));
			} catch(java.io.FileNotFoundException e){
				System.out.printf("Unable to open %s\n",args[0]);
				return;
			}
			System.out.printf("Reading input values from %s.\n",args[0]);
		}else{
			s = new Scanner(System.in);
			System.out.printf("Enter a list of non-negative integers. Enter a negative value to end the list.\n");
		}
		Vector<Integer> inputVector = new Vector<Integer>();

		int v;
		while(s.hasNextInt() && (v = s.nextInt()) >= 0)
			inputVector.add(v);

		int[] array = new int[inputVector.size()];

		for (int i = 0; i < array.length; i++)
			array[i] = inputVector.get(i);

		System.out.printf("Read %d values.\n",array.length);


		long startTime = System.currentTimeMillis();

		int count = CountInversions(array);

		long endTime = System.currentTimeMillis();

		double totalTimeSeconds = (endTime-startTime)/1000.0;

		System.out.println("Count "+ count);
		
		System.out.printf("Total Time (seconds): %.4f\n",totalTimeSeconds);
	}
	
}
