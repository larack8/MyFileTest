package com.larack.mytest;

import com.larack.mytest.filemove.MoveFileToDir;

public class MyTest {


    public static void main(String[] args) {

        MoveFileToDir.testDeleteDuplicateFiles();

//        testLocation();

    }

    public static void testLocation() {

        int x = 0;
        int y = 930;

        // 2400
        //    int x = 1990 ;  int y = 918 ;
        //    int x = 1826 ;  int y = 982 ;
        //    int x = 2074 ;  int y = 762 ;
        //    int x = 1665 ;  int y = 948 ;

        //    int x = 0 ;  int y = 930 ;
        //    int x = 0 ;  int y = 930 ;
        //    int x = 0 ;  int y = 930 ;
        //    int x = 0 ;  int y = 930 ;
        //    int x = 0 ;  int y = 930 ;
        //    int x = 0 ;  int y = 930 ;
        //    int x = 0 ;  int y = 930 ;
        //    int x = 0 ;  int y = 930 ;

        System.out.println((1080 - y) + " " + x);
        System.out.println();

        System.out.println((1080 - 918) + " " + 1990);
        System.out.println((1080 - 982) + " " + 1826);
        System.out.println((1080 - 762) + " " + 2074);
        System.out.println((1080 - 948) + " " + 1665);
        System.out.println((1080 - 747) + " " + 1788);

        System.out.println();

        System.out.println((1080 - 629) + " " + 1982);
        System.out.println((1080 - 970) + " " + 1501);
        System.out.println((1080 - 967) + " " + 1205);
        System.out.println((1080 - 150) + " " + 2222);
        System.out.println((1080 - 235) + " " + 2222);

        System.out.println();
// x= 470 , y = 845
        // 620 ,
        System.out.println((1080 - 329) + " " + 2222);
        System.out.println((1080 - (845 - 150)) + " " + 470);
        System.out.println((1080 - (845)) + " " + (470 + 150));
        System.out.println((1080 - (845 + 150)) + " " + 470);
        System.out.println((1080 - (845)) + " " + (470 - 150));

        System.out.println();

        System.out.println((1080 - (845 - 150)) + " " + (470 - 150));
        System.out.println((1080 - (845 + 150)) + " " + (470 - 150));
        System.out.println((1080 - (845 + 150)) + " " + (470 + 150));
        System.out.println((1080 - (845 - 150)) + " " + (470 + 150));


        System.out.println();

        System.out.println((1080 - 968) + " " + 1350);

        System.out.println();


    }

}