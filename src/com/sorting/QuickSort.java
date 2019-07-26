package com.sorting;

import java.util.stream.Stream;

public class QuickSort {
  public static void main(String[] args) {
    //
    int[] a = {12, 11, 13, 5, 6, 7,-9,342,1,-90};
    quickSort(a,0,a.length-1);
    for (int l : a){
      System.out.println(l);
    }
  }

  private static void quickSort(int[] a, int low, int high) {
    if (low < high) {
      int p = findPivot(a, low, high);
      quickSort(a, low, p - 1);
      quickSort(a, p + 1, high);
    }
  }

  private static int findPivot(int[] a, int low, int high) {
    int p = a[high];
    int i = low - 1;
    for (int j = low; j < high; j++) {
      if (a[j] < p) {
        i++;
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
      }
    }
    int te = a[high];
    a[high] = a[i + 1];
    a[i + 1] = te;
    return i + 1;
  }
}
