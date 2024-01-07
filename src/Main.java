import java.util.Arrays;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        float speedDiff = 0;
        for (int x = 0; x < 10; x++) {
            int size = 10000;
            int[] array = new int[size];
            int[] array2 = new int[size];
            //addRandNums(array, array2, size);
            addBackwardsOrder(array, array2, size);
            //we time the new sort
            long startTime = System.nanoTime();
            newBubbleSort(array);
            long endTime = System.nanoTime();
            long newDuration = (endTime - startTime);
            //we time the old sort
            long startTime2 = System.nanoTime();
            regBubbleSort(array2);
            long endTime2 = System.nanoTime();
            long regDuration = (endTime2 - startTime2);

            speedDiff += (float)(newDuration - regDuration) / (float)regDuration;
            System.out.print(x + " ");
        }
        System.out.println("New sort was: " + (speedDiff / 10) + " percent slower");
    }

    /**
     * Bubble-sorts upwards until the number of swaps per iteration starts to dwindle. Then switches direction.
     * @param array to be sorted
     */
    public static void newBubbleSort(int[] array) {
        int start = 0;
        for (int i = 0; i < array.length - 1; i++) {//outer loop "pulls the highest unsorted element up"
            int newSwaps = 0;
            boolean sorted = false;
            for (int j = start; j < array.length - i - 1; j++) {//inner loop is the hook that drags elements one index a time.
                //this will let go of the 2nd highest when it reaches someone higher, and grab onto the next element.
                //this needs to drag a shorter distance when after it has deposited the high values at the top
                if (array[j] > array[j + 1]) {
                    newSwaps++;
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    sorted = true;
                }
            }
            if (!sorted) break;
            if (newSwaps < array.length / 10) {//forward bubble sorting can become increasingly inefficient. So we start sorting the opposite direction.
                for (int j = array.length - i - 1; j > start; j--) {//start allows me to decrease the number of elements much faster
                    if (array[j] < array[j - 1]) {
                        int temp = array[j];
                        array[j] = array[j - 1];
                        array[j - 1] = temp;
                        sorted = true;
                    }
                }
            }
            start++;
            if (!sorted) break;
        }
    }

    /**
     * Only Bubble-sorts upwards
     * @param array to be sorted
     */
    public static void regBubbleSort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            boolean sorted = false;
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    // swap temp and arr[i]
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    sorted = true;
                }
            }
            if (!sorted) break;
        }
    }

    private static void addRandNums(int[] array, int[] array2, int size) {
        for (int i = 0; i < size; i++) {
            int newNum = (int) (Math.random() * size);
            array[i] = newNum;
        }
        System.arraycopy(array, 0, array2, 0, size);
    }

    private static void addBackwardsOrder(int[] array, int[] array2, int size) {
        for (int i = 0; i < size; i++) {
            int newNum = size - i;
            array[i] = newNum;
        }
        System.arraycopy(array, 0, array2, 0, size);
    }
}