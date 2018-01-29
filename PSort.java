//UT-EID=


import java.util.*;
import java.util.concurrent.*;

public class PSort extends RecursiveTask{
  public static void parallelSort (int[] A, int begin, int end){
    // TODO: Implement your parallel sort function 
	 if(A.length <= 16){
		 A = insertionSort(A);
	 }
	 else{
		 
	 }
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
  protected Object compute() {
	  // TODO Auto-generated method stub
	  return null;
  }
  
}
