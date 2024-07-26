package com.zipcodewilmington.arrayutility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by leon on 3/6/18.
 */
public class ArrayUtility<T> {

    private final T[] array;

    public ArrayUtility(T[] array) {
        this.array = array;
    }

    //Remove all occurrences of the specified value from the array
    public T[] removeValue(T valueToRemove) {

        List<T> resultList = new ArrayList<>();
        for (T element : array) {
            if (!element.equals(valueToRemove)) {
                resultList.add(element);
            }
        }

        //Convert list back to array - fix - Array.newInstance to create an Array of th correct type - this ensure you get an array of the same type as the input
        T[] resultArray = (T[]) java.lang.reflect.Array.newInstance(array.getClass().getComponentType(), resultList.size());

//        //Copy element from the list to the new array
//        for (int i = 0; i < resultList.size(); i++) {
//            resultArray[i] = resultList.get(i);
        return resultList.toArray(resultArray);
    }

    public Integer countDuplicatesInMerge(T[] arrayToMerge, T valueToEvaluate) {
        int count = 0;

        //Check both array for occurences of valueToEvaluate
        for (T element : array) {
            if (element.equals(valueToEvaluate)) {
                count++;
            }
        }
        for (T element : arrayToMerge) {
            if (element.equals(valueToEvaluate)) {
                count++;
            }
        }
        return count;
    }

    public T getMostCommonFromMerge(T[] arrayToMerge) {
        //Create a map to count occurences of each element
        Map<T, Integer> frequencyMap = new HashMap<>();

        //Merge the interal array and the array to merge
        T[] mergedArray = mergeArrays(array, arrayToMerge);

        //Count occurences of each element in the merged array
        for (T element : mergedArray) {
            frequencyMap.put(element, frequencyMap.getOrDefault(element, 0) + 1);
        }

        //Find the element with the highest count
        T mostCommon = null;
        int maxCount = 0;
        for (Map.Entry<T, Integer> entry : frequencyMap.entrySet()) {
            if (entry.getValue() > maxCount) {
                mostCommon = entry.getKey();
                maxCount = entry.getValue();
            }
        }
        return mostCommon;
    }

    private T[] mergeArrays(T[] array, T[] arrayToMerge) {
        T[] resultArray = (T[]) new Object[array.length + arrayToMerge.length];

        System.arraycopy(array, 0, resultArray, 0, array.length);
        System.arraycopy(arrayToMerge, 0, resultArray, array.length, arrayToMerge.length);

        return resultArray;
    }

    public Integer getNumberOfOccurrences(T valueToEvaluate) {
        int count = 0;

        for (T element : array) {
            if (element.equals(valueToEvaluate)) {
                count++;
            }
        }
        return count;
    }
}
