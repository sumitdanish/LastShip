package com;

import java.util.Arrays;

public class MergeSort {
  public static void main(String[] args) {
    //
    int[] a = {12, 11, 13, 5, 6, 7};
    merge(a, 0, a.length);
    Arrays.stream(a).forEach(System.out::println);
  }

  private static void merge(int[] a, int left, int right) {
    if (left < right) {
      int mid = (left + right) / 2;
      merge(a, left, mid);
      merge(a, mid + 1, right);
      mergeSort(a, left, mid, right);
    }
  }

  private static void mergeSort(int[] a, int left, int mid, int right) {
    int n1 = mid - left;
    int n2 = right - mid;
    int[] aL = new int[n1];
    int[] aR = new int[n2];
    for (int i = 0; i < n1; i++) {
      aL[i] = a[left + i];
    }
    for (int j = 0; j < n2; j++) {
      aR[j] = a[mid + j];
    }
    int k = left;
    int i = 0;
    int j = 0;
    while (i < n1 && j < n2) {
      if (aL[i] < aR[j]) {
        a[k] = aL[i];
        i++;
      } else {
        a[k] = aR[j];
        j++;
      }
      k++;
    }
    while (i < n1) {
      a[k] = aL[i];
      i++;
      k++;
    }
    while (j < n2) {
      a[k] = aR[j];
      j++;
      k++;
    }
  }
}
