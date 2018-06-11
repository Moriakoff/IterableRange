package model;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Range implements Iterable<Integer>{

    private int min;
    private int max;
    private int command;

    public Range(int min, int max) {

        this.min = min;
        this.max = max;
    }

    public Range(int min, int max, int command) {

        this.min = min;
        this.max = max;
        this.command = command;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }

    public void setCommand(int command) {
        this.command = command;
    }

    @Override
    public Iterator<Integer> iterator() {
        Iterator<Integer> iterator = new RangeNaturalIterator(this);
        switch (command) {
            case 1:iterator = new RangeEvenIterator(this);break;
            case 2:iterator = new RangePalindromIterator(this);break;
            case 3:iterator = new RangePrimeIterator(this);break;
            default:return iterator;
        }
        return iterator;
    }

    private class RangeNaturalIterator implements Iterator<Integer> {
        private int min;
        private int max;
        private int curr;

        public RangeNaturalIterator(Range range) {
            min = range.getMin();
            max = range.getMax();
            curr = min;
        }

        @Override
        public boolean hasNext() {
            return curr <= max;
        }

        @Override
        public Integer next() {
            if (!hasNext()) throw new NoSuchElementException();
            return curr++;
        }
    }

    private class RangeEvenIterator implements Iterator<Integer> {
        private int curr;
        private int max;

        public RangeEvenIterator(Range range) {
            curr = range.getMin();
            if (curr%2 !=0){
                curr++;
            }
            max = range.getMax();

        }

        @Override
        public boolean hasNext() {
            return curr <= max;
        }

        @Override
        public Integer next() {
            if (!hasNext()) throw new NoSuchElementException();
            int res = curr;
            curr += 2;
            return res;
        }
    }

    private class RangePalindromIterator implements Iterator<Integer> {
        private int curr;
        private int max;

        public RangePalindromIterator(Range range) {
            curr = getNextPalindrome(range.getMin());
            max = range.getMax();

        }

        private int getNextPalindrome(int min) {
            int res = min;
            while (!isPalindrom(res) && curr<=max)res++;
            return res;
        }

        private boolean isPalindrom(int n) {
            int num = n;
            int res = 0;

            while (num>0){
                res = res*10 + num%10;
                num=num/10;
            }

            return res==n;
        }

        @Override
        public boolean hasNext() {
            return curr <= max;
        }

        @Override
        public Integer next() {
            if (!hasNext()) throw new NoSuchElementException();
            int res = curr;
            curr = getNextPalindrome(++curr);
            return res;
        }
    }

    private class RangePrimeIterator implements Iterator<Integer> {
        private int curr;
        private int max;

        public RangePrimeIterator(Range range) {
            curr=getNextPrimeNumber(range.getMin());
            max=range.getMax();
        }

        private int getNextPrimeNumber(int min) {
            int res = min;
            while (!isPrime(res) && res<=max) res++;
            return res;
        }

        private boolean isPrime(int res) {
            if (res <= 2) return false;
            if (res < 4) return true;
            if (res %2 == 0) return false;
            if (res > max) return false;

            for (int i = 3; i*i <= res && i*i > 0; i+=2) {
                if (res%i == 0) return false;
            }
            return true;
        }

        @Override
        public boolean hasNext() {
            return curr<=max;
        }

        @Override
        public Integer next() {
            if(!hasNext()) throw new NoSuchElementException();
            int res = curr;
            curr = getNextPrimeNumber(++curr);
            return res;
        }
    }
}
