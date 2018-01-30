//UT-EID= kjb2786
//UT-EID= ct26756


import java.util.*;
import java.util.concurrent.*;


public class PMerge{
  public static void parallelMerge(int[] A, int[] B, int[]C, int numThreads){
	 
	  
	  
  }
  protected void merge(int[] A, int[] B, int[]C) {
	    
     

      /* Merge the temp arrays */

      // Initial indexes of first and second subarrays
      int i = 0, j = 0;

      // Initial index of merged subarry array
      int k = 0;
      
      while (i < A.length && j < B.length)
      {
    	  //Element at A <= Element at B, put that element in merged array
          if (A[i] <= B[j])
          {
              C[k] = A[i];
              i++;
          }
          else
          {
              A[k] = B[j];
              j++;
          }
          k++;
      }

      /* Copy remaining elements of L[] if any */
      while (i < A.length)
      {
          C[k] = A[i];
          i++;
          k++;
      }

      /* Copy remaining elements of R[] if any */
      while (j < B.length)
      {
          C[k] = B[j];
          j++;
          k++;
      }
  }
	  
  
}