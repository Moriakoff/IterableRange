package test;

import model.Range;
import org.junit.Test;


public class RangeTest {
    Range r;

    @org.junit.Before
    public void setUp() throws Exception {
        r = new Range(0, 100);

    }

    @Test
    public void TestPrintNatural() {
        System.out.print("Natural iterator :");
        r = new Range(0,10);
        for (Integer i :
                r) {
            System.out.print(i + "  ");
        }
        System.out.println("\n***********************************");
    }
    @Test
    public void TestPrintEven() {
        r = new Range(0,20);
        r.setCommand(1);
        System.out.print("Even iterator :");
        for (Integer i :
                r) {
            System.out.print(i + "  ");
        }
        System.out.println("\n***********************************");
    }


    @Test
    public void TestPrintPalindrome() {
        r.setCommand(2);
        System.out.print("Palindrom iterator :");
        for (Integer i :
                r) {
            System.out.print(i + "  ");
        }
        System.out.println("\n***********************************");
    }
    @Test
    public void TestPrintPrime() {
        r.setCommand(3);
        System.out.print("Prime iterator :");
        for (Integer i :
                r) {
            System.out.print(i + "  ");
        }
        System.out.println("\n***********************************");
    }
}