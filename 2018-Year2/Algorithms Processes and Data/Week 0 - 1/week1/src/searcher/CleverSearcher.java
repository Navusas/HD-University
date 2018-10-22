package searcher;

import java.util.Arrays;

/**
 * Created by u1757704 on 08/10/2018.
 */
public class CleverSearcher extends Searcher{

    /*Constructor
    * @param1 = array to be sorted
    * @param2 = index to be find
    * @return = sorted array */
    CleverSearcher (int [] array, int k) {super(array,k);}

    /*Overrides findElement method from abstract class 'Searcher'*/
    @Override
    public int findElement() throws IndexingError {
        int[] array = getArray(); /*Get randomised array*/
        int k = getIndex(); /*Get required element to look for*/
        if(k <= 0 || k > array.length) { // throw indexing error in case requested index is not within specs
            throw new IndexingError();
        }
        //Arrays.sort(array);
        //return array[array.length-k];
        //initialize small array with size of k (given size)
        int[] smallArray = Arrays.copyOfRange(array,0,k);

        /*Sort the array from smallest to highest */
        Arrays.sort(smallArray);

        /*Call function to find the k largest element from back of array */
        return findLargestNumbersArray(smallArray,array,k);
    }

    /*Finds the K largest number in array from back.
    * @param 1: Smaller array which 1st element will be element we are looking for
    * @param 2: Bigger array, where we will look for bigger elements then in our small array
    * @param 3: index of where we cutted the small and big arrays
    *
    * @return: First element of smaller array , which is the k largest element we are looking for
    * */
    public static int findLargestNumbersArray(int[] smallArray, int[] bigArray, int index) {

        /*Start looking at big array from position where arrays been divided*/
        for(int m = index; m < bigArray.length; m++)
        {
            if (smallArray[0] < bigArray[m]) // if first element if small array is bigger then element in big array
            // we are currently looking at
            {
                smallArray[0] = bigArray[m]; // assign the bigger value to smallest biggest elements array
                for (int i = 0; i < smallArray.length - 1; i++) // keep going until the end of small array
                {
                    if (smallArray[i] > smallArray[i + 1]) { // if the next element is bigger
                        int current_value = smallArray[i + 1]; // swap the values around
                        smallArray[i + 1] = smallArray[i];     // so that bigger moves to right
                        smallArray[i] = current_value;       // and smaller go to left
                    }
                }
            }
        }
        return smallArray[0]; // return the k largest element from BIGGEST elements array
    }

    /*Used for testing to output array into console*/
    public static void arrayToString(int[] array){
        for(int value: array) {
            System.out.print(value + " " );
        }
    }
}
