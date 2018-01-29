//UT-EID=


import java.util.*;
import java.util.concurrent.*;

public class PSort extends RecursiveAction{
	int[] A;
	int begin, end;
	
	public PSort(int[]A, int begin, int end){
		this.A = A;
		this.begin = begin;
		this.end = end;
	}
	
  public static void parallelSort (int[] A, int begin, int end){
    // TODO: Implement your parallel sort function 
	 
		 int processors = Runtime.getRuntime().availableProcessors();
		 PSort arr = new PSort(A, begin, end);
		 ForkJoinPool pool = new ForkJoinPool(processors);
		 pool.invoke(arr);

  }
  
  public static int[] insertionSort(int[] A){  //TODO: why static??
	  int size = A.length;
	  for(int i = 1; i < size; ++i){
		  int temp = A[i];
		  int j = i-1;
		  
		  while(j>=0 && A[j] > temp){
			  A[j+1] = A[j];
			  j = j-1;
		  }
		  A[j+1] = temp;
	  }
	return A;
	  
  }


  @Override
  protected void compute() {
	  if(A.length <= 4){
		  insertionSort(A);
	  }
	  else{
		  int index = partition(A, begin, end);
		  PSort lower = new PSort(A, begin, index-1);
		  lower.fork();
		  PSort upper = new PSort(A, index+1, end);
		  upper.fork();
		  lower.join();
		  upper.join();
	  }
  }
  
  public int partition(int[] arr, int low, int high){
	  System.out.println(high-1);
	  int pivot = arr[high-1];
	  int wall = low-1;
	  for(int current = low; current < high; current++){
		  if(arr[current]<=pivot){
			  wall++;
			  
			  int temp = arr[current];
			  arr[wall] = arr[current];
			  arr[current] = temp;
		  }
	  }
	  int temp = arr[wall+1];
	  arr[wall+1] = pivot;
	  arr[high-1]=temp;
	  
	  return wall+1;
	  
  }
  
}
