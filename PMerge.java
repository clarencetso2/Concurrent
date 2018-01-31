//UT-EID= kjb2786
//UT-EID= ct26756


import java.util.*;
import java.util.concurrent.*;


public class PMerge implements Runnable{
	int[] A, B, C;
	int numThreads, startA, endA, startB, endB;
	static HashSet<Integer> duplicate;
	
	
	public PMerge(int[] A, int[] B, int[]C, int numThreads, int startA, int endA, int startB, int endB){
		this.A = A;
		this.B = B;
		this.C = C;
		this.numThreads = numThreads;
		this.startA = startA;
		this.startB = startB;
		this.endA = endA;
		this.endB = endB;
	}
	public static void parallelMerge(int[] A, int[] B, int[]C, int numThreads){
		HashSet<Integer> temp = new HashSet<Integer>();
		
		for(int i = 0; i < A.length; i++){
			temp.add(A[i]);
		}
		
		for(int j = 0; j < B.length; j++){
			if(!temp.add(B[j])){
				duplicate.add(B[j]);
			}
		}
		
		int sizeA = A.length/numThreads;
		int sizeB = B.length/numThreads;
		
		ExecutorService pool = Executors.newFixedThreadPool(numThreads);
		
		for(int i = 1; i <= numThreads; i++){
			PMerge r = new PMerge(A, B, C, numThreads, sizeA*(i-1), sizeA*i, sizeB*(i-1), sizeB*i);
            pool.execute(r);
		}
		
	}
	
	public int binarySearch(int key, int[] arr){
		int low = 0;
		int hi = arr.length - 1;
		int mid = (low+hi)/2;
		
		while(low<=hi){
			mid = (low+hi)/2;
			if(arr[mid] > key){
				hi = mid - 1;
			}
			if(arr[mid] < key){
				low = mid + 1;
			}
			if(arr[mid] == key){
				return mid;
			}
		}
		if (arr[mid] < key){
			return mid+1;
		}
		else{
			return mid;
		}
	}
	@Override
	public void run() {
		for(int i = startA; i < endA; i++){
			int cIndex = i + binarySearch(A[i], B);
			if(duplicate.contains(A[i])){
				cIndex++;
			}
			C[cIndex] = A[i];
		}
		for(int j = startB; j < endB; j++){
			int cIndex = j + binarySearch(B[j], A);
			C[cIndex] = B[j];
		}
		
		
	}

	  
}