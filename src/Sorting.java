import java.util.*;   // for Random

public class Sorting {
	private static final Random RAND = new Random(42);   // random number generator
	
	private int array[];
    private int length;

	public static void main(String[] args) {
		int LENGTH = 10000;   // initial length of array to sort
		int RUNS   =  5;   // number of runs
		
		
		for (int i = 0; i < RUNS; i++){
			
			int[] a = createRandomArray(LENGTH);
			int[] b = a.clone();
			int[] c = a.clone();
			int[] d = a.clone();
			
			Sorting obj = new Sorting();
			
			//mergeSort(a);
			//obj.qsort(b);
			//insertionSort(c);
			//bubbleSort(d);

			// perform a sort and time how long it takes
			long startTime1 = System.currentTimeMillis();
			mergeSort(a);
			long endTime1 = System.currentTimeMillis();
			
			long startTime2 = System.currentTimeMillis();
			obj.qsort(b);
			long endTime2 = System.currentTimeMillis();
		
			long startTime3 = System.currentTimeMillis();
			insertionSort(c);
			long endTime3 = System.currentTimeMillis();
			
			long startTime4 = System.currentTimeMillis();
			bubbleSort(d);
			long endTime4 = System.currentTimeMillis();
			
			
			if (isSorted(a)) {
				System.out.println("The numbers are sorted");
				
			}else{
				throw new RuntimeException("not sorted afterward: " + Arrays.toString(a));
			}
			

			/*System.out.println("The sorted numbers: \n");
			for(int i:a){
	            System.out.print(i);
	            System.out.print("\n");
	        }*/

			System.out.printf("%10d elements  => Time elapsed for merge sort is %6d ms \n", LENGTH, endTime1 - startTime1);
			System.out.printf("%10d elements  => Time elapsed for quick sort is %6d ms \n", LENGTH, endTime2 - startTime2);
			System.out.printf("%10d elements  => Time elapsed for insertion sort is %6d ms \n", LENGTH, endTime3 - startTime3);
			System.out.printf("%10d elements  => Time elapsed for bubble sort is %6d ms \n", LENGTH, endTime4 - startTime4);
			LENGTH += 10000;   // add 5000 to size of array for next time
			
		}	
	}
	
	//function for insertion sort
	public static void insertionSort(int[] a) {
		for (int pass = 1; pass < a.length; pass++) {
			int temp = a[pass];
			int j = pass;
			while (j >= 1 && a[j - 1] > temp) {
				a[j] = a[j - 1];
				j--;
			}
			a[j] = temp;
		}
	}
	
	
	// function for selection sort
	public static void selectionSort(int[] a) {
		for (int pass = 0; pass < a.length; pass++) {
			int min = pass;
			for (int j = pass + 1; j < a.length; j++) {
				if (a[j] < a[min]) {
					min = j;
				}
			}
			
			swap(a, pass, min);
		}
	}
	
	//function for bubble sort
	public static void bubbleSort(int[] a) {
		for (int pass = 0; pass < a.length; pass++) {
			boolean changed = false;
			for (int i = 0; i < a.length - 1 - pass; i++) {
				if (a[i] > a[i + 1]) {
					swap(a, i, i + 1);
					changed = true;
				}
			}
			if (!changed) {
				return;
			}
		}
	}
	
	public void qsort(int[] inputArr) {
		if (inputArr == null || inputArr.length == 0) {
            return;
        }
        this.array = inputArr;
        length = inputArr.length;
        quickSort(0, length - 1);
	}
	
	//function for quick sort
	private void quickSort(int lowerIndex, int higherIndex){
		int i = lowerIndex;
        int j = higherIndex;
        // calculate pivot number, I am taking pivot as middle index number
        int pivot = array[lowerIndex+(higherIndex-lowerIndex)/2];
        // Divide into two arrays
        while (i <= j) {
            /**
             * In each iteration, we will identify a number from left side which
             * is greater then the pivot value, and also we will identify a number
             * from right side which is less then the pivot value. Once the search
             * is done, then we exchange both numbers.
             */
            while (array[i] < pivot) {
                i++;
            }
            while (array[j] > pivot) {
                j--;
            }
            if (i <= j) {
                exchangeNumbers(i, j);
                //move index to next position on both sides
                i++;
                j--;
            }
        }
        // call quickSort() method recursively
        if (lowerIndex < j)
            quickSort(lowerIndex, j);
        if (i < higherIndex)
            quickSort(i, higherIndex);
	}
	
	private void exchangeNumbers(int i, int j){
		int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
	}
	
	// merge sort function
	public static void mergeSort(int[] a) {
		if (a.length >= 2) {
			// split array in half
			int[] left  = Arrays.copyOfRange(a, 0, a.length / 2);
			int[] right = Arrays.copyOfRange(a, a.length / 2, a.length);
			
			// sort the halves
			mergeSort(left);
			mergeSort(right);
			
			// merge them back together
			int i1 = 0;
			int i2 = 0;
			for (int i = 0; i < a.length; i++) {
				if (i2 >= right.length ||
						(i1 < left.length && left[i1] < right[i2])) {
					a[i] = left[i1];
					i1++;
				} else {
					a[i] = right[i2];
					i2++;
				}
			}
		}
	}
	
	// Swaps the values at the two given indexes in the given array.
	private static void swap(int[] a, int i, int j) {
		if (i != j) {
			int temp = a[i];
			a[i] = a[j];
			a[j] = temp;
		}
	}
	
	
	// Returns true if the given array is in sorted ascending order.
	private static boolean isSorted(int[] a) {
		for (int i = 0; i < a.length - 1; i++) {
			if (a[i] > a[i + 1]) {
				return false;
			}
		}
		return true;
	}
	
	

	// Creates an array of the given length, fills it with random
	// non-negative integers, and returns it.
	public static int[] createRandomArray(int length) {
		int[] a = new int[length];
		for (int i = 0; i < a.length; i++) {
			a[i] = RAND.nextInt(1000000000);
		}
		return a;
	}

	
}