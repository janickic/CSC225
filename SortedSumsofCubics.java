/*
* Student: Camille Janicki
* CSC 225, Summer 2016
*/


import java.io.File;
import java.util.Scanner;
import java.lang.Math; 
import java.util.ArrayList;

/*
* SortedSumsOfCubics.java
* This program  prints out all the intergers of the form a^3+b^3, where a and b are integers in the range [0, n],
* in sorted order using O(n) space.
* Input: A nonnegative integer n.
* Output: A sorted list of all integers of the form a^3+b^3, where a and b are integers in the range [0, n].
* The implemented algorithm takes O(n) space 
*/

public class SortedSumsOfCubics{

	/*
	* This method takes a nonnegative integer n as its only argument and prints out all the intergers of the form 
	* a^3+b^3, where a and b are integers in the range [0, n], in sorted order.
	* Let's denote our elements with the triples (a^3+b^3, a, b) 
	* Assume a>=b
	*/
	public static void	SortedSumsOfCubics(int n){   
		// Only storing n+1 items in the heap, therefore, only using O(n) space
		Heap heap = new Heap();
		
		//Storing (1^3)+(0^3),(1^3)+(0^3), (2^3)+(0^3), (3^3)+(0^3).... (depending on n)
		for(int a =0; a<n+1; a++)
		{
			heap.insert(a,0);
		}
		
		while(!heap.isEmpty())
		{
			int a=heap.findA();
			int b=heap.findB();
			
			if(b<a)
			{
				System.out.print(heap.top()+"  ");
				heap.delete();
				if(b<n)
				{
					heap.insert(a, b+1);
				}
			}
			else
			{
				System.out.print(heap.top()+"  ");
				heap.delete();
			}
			
		}
		
	}

	/*
	* The main function in your code should help you test your implementation by getting an integer
	* from input and sending it to the function above.
	*/
	public static void main (String[] args) throws Exception{
		Scanner s  = new Scanner(System.in);
        System.out.println("Please insert a non-negative integer: ");
		int n = s.nextInt();
		SortedSumsOfCubics(n);

	}

}



/*
* Heap.java
* This class implements a heap using and ArrayList. Methods all maintain proper heap order. 
* This class is used for SortedSumsOfCubics to sort the sums of cubics calculated by mainting proper heap order.
*/ 
class Heap {    
    private ArrayList<Element> heap = new ArrayList<Element>();
    private class Element{
        int key;
		int a;
		int b;
		
	   
		Element(int a, int b)
		{
			Double firstDoub = new Double(Math.pow(a,3));
			int first = firstDoub.intValue();
			Double secDoub = new Double(Math.pow(b,3));
			int second = secDoub.intValue();
			
			key=(first+second);
			
			this.a=a;
			this.b=b;
		}
        Element(){}
    }

	/*
	* This method return the a value of the top element in the heap
	*/
	public int findA()
	{
		//Only every need to access from top element
		Element elem=heap.get(0);
		return elem.a;
		
	}
	
	/*
	* This method return the b value of the top element in the heap
	*/
	public int findB()
	{
		//Only every need to access from top element
		Element elem=heap.get(0);
		return elem.b;
	}
	/*
	* This method returns true if the heap is empty.
	*/
    public boolean isEmpty(){
		if(heap.isEmpty())
		{
			return true;
		}
		return false;
    }
	
	/*
	* This method inserts a new item into the heap. The new item at the next available spot in the heap. 
	* Then the item is bubbled up if needed to maintain a proper heap. 
	*/
    public void insert(int a, int b){
        Element e = new Element(a, b);
		//To handle duplicates
		for(int i=0; i< heap.size(); i++)
		{
			if(heap.get(i).key==e.key)
			{
				return;
			}
		}
        heap.add(e);
		bubbleUp(e.key);
    }   
	

	/*
	* This is a private method called only by insert(). This method bubbles up the inserted item to get a proper heap.
	*/ 
	private void bubbleUp (int key) {
		int keyIndex=(heap.size()-1);

		if(keyIndex==0)
		{
			return;
		}
		int parentIndex= (keyIndex-1)/2;
		int parent=heap.get(parentIndex).key;

		while (key <= parent ) {
			Element temp=heap.get(keyIndex);
			heap.set(keyIndex, heap.get(parentIndex));
			heap.set(parentIndex, temp);
			
			keyIndex=parentIndex;
			if(keyIndex==0)
			{
				break;
			}
			parentIndex= (keyIndex-1)/2;
			parent=heap.get(parentIndex).key;
			
		}
		
	}
	
	/*
	* This method deletes the root of the heap. This is done by switching the first and last items, deleting the last item
	* and finally by bubbling down the root item to get a proper heap.
	*/
    public void delete(){
		if(heap.isEmpty())
		{
			return;
		}
        heap.set(0, heap.get(heap.size()-1));
        heap.remove(heap.size()-1);
		//check if it is empty
		if(heap.isEmpty())
		{
			return;
		}
		bubbleDown(0); 
    }
	
	/*
	* This is a private method only called by delete(). This method bubbles down the root item to it's proper place in the heap.
	*/
	private void bubbleDown(int rootIndex)
	{
		
		Element root= heap.get(rootIndex);
		int rightIndex=2*(rootIndex)+2;
		int leftIndex=2*(rootIndex)+1;
		
		int childIndex=leftIndex; 
		if(childIndex<heap.size())
		{
			Element leftKey=heap.get(leftIndex);
			int rightChildIndex=childIndex+1;
			if(rightChildIndex<heap.size())
			{
				Element rightKey=heap.get(rightIndex);
				if(rightKey.key<leftKey.key)
				{
					childIndex=rightChildIndex;
				}
			}
			int child=heap.get(childIndex).key;
			if(child<root.key) //swap
			{
			
				Element temp=heap.get(childIndex);
				heap.set(childIndex, heap.get(rootIndex));
				heap.set(rootIndex, temp);
				rootIndex=childIndex;
				bubbleDown(rootIndex);
			
			}

		}
	}
	
    /*
	* This method returns the root item of the tree without removing it.
	*/
	public int top(){
		if(heap.isEmpty())
		{
			return Integer.MAX_VALUE;
		}
		return heap.get(0).key;
    }
}
