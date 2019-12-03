/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bankers.algorithm.BankersAlgorithm;

import static bankers.algorithm.BankersAlgorithm.Banker.reqFunc;
import static bankers.algorithm.BankersAlgorithm.Banker.safe;
import static bankers.algorithm.BankersAlgorithm.Banker.safety;
import java.io.*;
import java.util.*;

/**
 *
 * @author student
 */
public class java {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader obj = new BufferedReader(isr);

        int n, m;
        System.out.println("enter no. of processes:");
        n = Integer.parseInt(obj.readLine());
        System.out.println("enter no. of resources:");
        m = Integer.parseInt(obj.readLine());

        int a[] = new int[m];
        for (int i = 0; i < m; i++) {
            System.out.println("enter no. of available instances resources:" + i);
            a[i] = Integer.parseInt(obj.readLine());

        }

        System.out.println("enter allocation of resources:");
        int al[][] = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.println("enter allocation instances of resources:" + j + "for process p" + i);
                al[i][j] = Integer.parseInt(obj.readLine());

            }
        }

        System.out.println("enter maximum of resources:");
        int max[][] = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.println("enter max instances of resources:" + j + "for process p" + i);
                max[i][j] = Integer.parseInt(obj.readLine());

            }
        }

        int need[][] = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {

                need[i][j] = max[i][j] - al[i][j];

            }
        }

        if (safety(a, al, need, n, m)) {
            System.out.println("System in Safe State");
            System.out.println("System's Safe sequence:");
            for (int i = 0; i < n; i++) {
                System.out.println(safe[i] + " ");
            }
        } else {
            System.out.println("System in UnSafe State");
        }

        System.out.println("do u wanna to hav a request for any process,then enter Process no. and Requesting resources");
        int pid = Integer.parseInt(obj.readLine());
        int req[] = new int[m];
        for (int i = 0; i < m; i++) {
            req[i] = Integer.parseInt(obj.readLine());
        }
        if (reqFunc(a, al, need, req, pid, n, m)) {
            System.out.println("request can be granted");
            for (int i = 0; i < m; i++) {
                al[pid][i] += req[i];
                need[pid][i] -= req[i];
                a[i] -= req[i];

            }
            if (safety(a, al, need, n, m)) {
                System.out.println("System in Safe State");
                System.out.println("System's Safe sequence:");
                for (int i = 0; i < n; i++) {
                    System.out.println(safe[i] + " ");
                }
            } else {
                System.out.println("System in UnSafe State");
            }
        }

    }
}
