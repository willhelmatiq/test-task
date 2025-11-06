package com.example.test_task;

public class HeapUtils {

    public static void siftDownMaxHeap(int[] heapArr, int i, int length) {
        while (2 * i + 1 < length) {
            int childInd = 2 * i + 1;
            if (2 * i + 2 < length && heapArr[2 * i + 2] > heapArr[2 * i + 1]) {
                childInd = 2 * i + 2;
            }
            if (heapArr[i] >= heapArr[childInd]) {
                break;
            }
            swap(heapArr, i, childInd);
            i = childInd;
        }
    }

    public static void siftUpMaxHeap(int[] heapArr, int i) {
        while (i - 1 >= 0) {
            int parent = (i - 1) / 2;

            if (heapArr[i] <= heapArr[parent]) {
                break;
            }
            swap(heapArr, i, parent);
            i = parent;
        }
    }

    private static void swap(int[] heapArr, int firstNode, int secondNode) {
        int tempVal = heapArr[firstNode];
        heapArr[firstNode] = heapArr[secondNode];
        heapArr[secondNode] = tempVal;
    }
}
