//package com.binsolv.politekh;


public class binsolv {
    public static String decTobin(int n) {
        int a, count = 0;
        String x = "";
        while (n > 0) {
            a = n % 2;
            if (a == 1) {
                count++;
            }
            x += a;
            n = n / 2;
        }
        while (x.length() < 4) {
            x += 0;
        }
        return new StringBuilder(x).reverse().toString();
    }

    static boolean equation1(boolean a, boolean b, boolean c, boolean d) {
        return (((d | a) & d) | (((b & ( !(b | d) ) ) & (((c & ((b ^ d) & c)) & b) & c)) & b));

    }

    static boolean equation2(boolean a, boolean b, boolean c, boolean d) {
        return (b | (((a ^ b) &  ( !(!(a & (d & ((a & (b & a)) | c))) | a) )) | (d | b)));
    }

    static int[][] truthTable(int funcNum)
    {
        int res[][] = new int[5][16];
        for (int i = 0; i < 16; i ++)
        {
            boolean a = decTobin(i).charAt(0) - 48 == 1 ;
            boolean b = decTobin(i).charAt(1) - 48 == 1 ;
            boolean c = decTobin(i).charAt(2) - 48 == 1 ;
            boolean d = decTobin(i).charAt(3) - 48 == 1 ;

            res[0][i] = ( a ? 1 : 0);
            res[1][i] = ( b ? 1 : 0);
            res[2][i] = ( c ? 1 : 0);
            res[3][i] = ( d ? 1 : 0);
            if (funcNum == 1)
                res[4][i] = equation1(a,b,c,d) ? 1 : 0;
            else if (funcNum == 2)
                res[4][i] = equation2(a,b,c,d) ? 1 : 0;


        }
        return res;
    }

    public static void showTable(int[][] arr1, int[][] arr2){
        System.out.println("x1 x2 x3 x4 result1 |  x1 x2 x3 x4 result");
        for (int i = 0; i < arr1[0].length; i ++) {
            for (int j = 0; j < arr1.length; j++)
                System.out.print(arr1[j][i] + "  ");
            System.out.print( "     |  ");
            for (int j = 0; j < arr1.length; j++)
                System.out.print(arr2[j][i] + "  ");
            System.out.println();
        }

    }
    public static void equalityChesk(int[][] arr1, int[][] arr2)
    {
        boolean all_impl = true;
        for(int i = 0; i < arr1[0].length; i ++) {
            boolean impl = (!(arr1[4][i] == 1) | (arr2[4][i] == 1));
            if(!impl)
                all_impl = false;
            System.out.println("res1 = " + arr1[4][i] + " res2 = " + arr2[4][i] + " (res1 => res2) = " + impl );
        }
        System.out.println(" All implications was " + all_impl);
    }
    public static void main(String[] args) {
        showTable(truthTable(1), truthTable(2));
        equalityChesk(truthTable(1), truthTable(2));
        }


}
