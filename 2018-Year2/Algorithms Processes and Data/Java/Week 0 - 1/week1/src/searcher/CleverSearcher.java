package searcher;

import java.util.Arrays;

/**
 * Better approach of finding element in given array at given position.
 * Takes array as argument and temporary divides it into 2
 * small and big arrays, where small array elements are being swapped if bigger value is found
 *
 * @author Domantas Giedraitis (student id: u1757704 )
 * @version 1
 * @since 2018-10-08
 */
public class CleverSearcher extends Searcher{

    /**
     * Takes the integer array and finds the element in given index (k)
     *
     * @param array to be sorted
     * @param k index of element to be find
     */
    CleverSearcher (int [] array, int k) {super(array,k);}

    /**
     * Overrides findElement method from abstract class 'Searcher'
     *
     * Get array and element to look for. Check if given index is not out of bounds
     * Create smaller array of size [given index], sorts it ASC and calls function
     * findLargestNumberInArray to find element
     *
     * @return the element which we looking for
     * @throws IndexingError if given index is not within specifications
     */
    @Override
    public int findElement() throws IndexingError {
        int[] array = getArray(); /*Get randomised array*/

        int k = getIndex(); /*Get required element to look for*/

        if(k <= 0 || k > array.length) { // throw indexing error in case requested index is not within specifications
            throw new IndexingError();
        }

        //initialize small array with size of k (given size)
        int[] smallArray = Arrays.copyOfRange(array,0,k);

        /*Sort the array from smallest to highest */
        Arrays.sort(smallArray);

        /*Call function to find the k largest element from back of array */
        return sortSmallArray(smallArray,array,k);
    }

    /**
     * Finds the K largest number in array
     *
     * @param smallArray  Array of size [K] which 1st element will be element to return
     * @param bigArray  Given array of elements. Used to compare the elements with smallArray
     * @param index  Required index to find element in array
     * @return  First element of smallArray which is the k largest element
     */

    public static int sortSmallArray(int[] smallArray, int[] bigArray, int index) {

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
}
