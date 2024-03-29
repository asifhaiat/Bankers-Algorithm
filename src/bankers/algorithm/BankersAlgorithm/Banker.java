package bankers.algorithm.BankersAlgorithm;

import java.io.*;
import java.util.*;

public class Banker {

    static int safe[] = new int[20];

    static boolean safety(int a[], int al[][], int need[][], int n1, int m1) {
        int n = n1;
        int m = m1;
        int nd[][] = new int[n][m];
        int work[] = new int[m];
        int all[][] = new int[n][m];

        for (int i = 0; i < m; i++) {
            work[i] = a[i];
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                all[i][j] = al[i][j];
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                nd[i][j] = need[i][j];
            }
        }

        boolean fin[] = new boolean[n];

        for (int i = 0; i < n; i++) {
            fin[i] = false;
        }

        int check = 0;
        int check1 = 0;

        do {
            for (int i = 0; i < n; i++) {
                boolean flag = true;
                if (fin[i] == false) {
                    for (int j = 0; j < m; j++) {
                        if (work[j] < nd[i][j]) {
                            flag = false;
                        }
                    }
                    if (flag) {
                        for (int j = 0; j < m; j++) {
                            work[j] += all[i][j];
                        }
                        safe[check] = i;
                        check++;
                        fin[i] = true;
                    }
                }

            }

            check1++;
        } while (check < n && check1 < n);

        if (check > n) {
            return false;
        } else {
            return true;
        }

    }

    static boolean reqFunc(int a[], int al[][], int need[][], int req[], int pid, int n1, int m1) {
        int n = n1;
        int m = m1;
        int avail[] = new int[m];
        int alloc[][] = new int[n][m];
        int need1[][] = new int[n][m];
        int req1[] = new int[m];
        int r = pid;

        for (int i = 0; i < m; i++) {
            req1[i] = req[i];
        }

        for (int i = 0; i < m; i++) {
            avail[i] = a[i];
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                alloc[i][j] = al[i][j];
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                need1[i][j] = need[i][j];
            }
        }

        boolean flag = true;
        for (int i = 0; i < m; i++) {
            if (need1[r][i] < req1[i]) {
                flag = false;
            }
        }

        if (flag) {
            for (int i = 0; i < m; i++) {
                if (avail[i] < req1[i]) {
                    flag = false;
                }
            }

            if (flag) {
                for (int i = 0; i < m; i++) {
                    alloc[r][i] += req1[i];
                    need1[r][i] -= req1[i];
                    avail[i] -= req1[i];
                }

                if (safety(avail, alloc, need1, n, m)) {
                    return true;
                } else {
                    System.out.println("It leads to deadlock ,so request can't be granted");
                }
            } else {
                System.out.println("process p" + r + "should wait");
            }
        } else {
            System.out.println("Error:process exceeding Limit");
        }
        return false;

    }

}
