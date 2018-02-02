//UT-EID= kjb2786
//UT-EID= ct26756


import java.util.*;
import java.util.concurrent.*;


public class PMerge implements Callable<Void>{
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
		if(A.length <= numThreads){
			sizeA =1 ;
		}
		if(B.length <= numThreads){
			sizeB =1;
		}
		
		ExecutorService pool = Executors.newFixedThreadPool(numThreads);
		int A_beg = 0;
		int A_end = 0;
		int B_beg = 0;
		int B_end = 0;
		Future<Void>[] futures = (Future<Void>[]) new Future[numThreads];
		for(int i = 1; i <= numThreads; i++){
			
			if(A.length - sizeA*(i) >=0){
				A_beg = sizeA*(i-1);
				A_end = sizeA*(i) -1;
				
			}
			else{
				A_beg = -1; 
				A_end = -1;
			}
			
			if(B.length - sizeB*(i) >= 0){
				B_beg = sizeB*(i-1);
				B_end = sizeB*(i) -1;
			}
			else{
				B_beg = -1;
				B_end = -1;
			}
			PMerge r = new PMerge(A, B, C, numThreads, A_beg, A_end, B_beg, B_end);
			futures[i-1] = pool.submit(r);
			
		}
		
		for(int i = 0; i < numThreads; i++){
			try {
				futures[i].get();
			} catch (InterruptedException | ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		pool.shutdown();
		
	}
	
	public int binarySearch(int key, int[] arr){
		int low = 0;
		int hi = arr.length - 1;
		int numLower=0;
		
		for(int i =0; i< arr.length; i++){
			if(arr[i] < key){
				numLower++;
			}
		}
		return numLower;
		/*
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
	*/
	}

	@Override
	public Void call() throws Exception {
		for(int i = startA; i <= endA; i++){
			int cIndex = i + binarySearch(A[i], B);
			/*
			if(duplicate.contains(A[i])){
				cIndex++;
			}
			*/
			C[cIndex] = A[i];
		}
		for(int j = startB; j <= endB; j++){
			int cIndex = j + binarySearch(B[j], A);
			C[cIndex] = B[j];
		}
		return null;
		
	}

	  
}