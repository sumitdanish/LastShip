package com.dp;

public class Catalan {

  public static void main(String[] args) {
    //
    System.out.println(dpCatalan(4));
  }

  private static int catalan(int n) {
    if (n <= 1) {
      return 1;
    }
    int res = 0;
    for (int i = 0; i < n; i++) {
      res += catalan(i) * catalan(n - i - 1);
    }
    return res;
  }

  private static int dpCatalan(int n) {
    int[] c = new int[n + 1];
    c[0] = 1;
    c[1] = 1;
    for (int i = 2; i <= n; i++) {
      c[i] = 0;
      for (int j = 0; j < i; j++) {
        c[i] += c[j] * c[i - j - 1];
      }
    }
    return c[n];
  }
}
