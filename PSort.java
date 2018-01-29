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
	  if(A.length <= 16){
			 A = insertionSort(A);
	  }
	  else{
		  
	  }
  }
  
}
