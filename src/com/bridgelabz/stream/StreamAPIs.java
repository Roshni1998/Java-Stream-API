package com.bridgelabz.stream;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class StreamAPIs {
    public static void main (String []args){
        //Creating sample Collection
        List<Integer> myNumberList = new ArrayList<Integer>();
        for(int i=0; i<5; i++) myNumberList.add(i);

        //Method 1: Traversing using Iterator
        Iterator<Integer> it = myNumberList.iterator();
        while(it.hasNext()){
            Integer i = it.next();
            System.out.println("Method 1: Iterator Value :: "+i);
        }

        //Method 2: Traversing with Explicit Consumer interface implementations
        class MyConsumer implements Consumer<Integer> {
            public void accept(Integer t){
                System.out.println("Method 2: Consumer Impl Value :: "+t);
            }
        }
        MyConsumer action = new MyConsumer();
        myNumberList.forEach(action);

        //Method 3: Traversing with Anonymous Consumer interface Implementation
        myNumberList.forEach(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) {
                System.out.println("Method 3: forEach anonymous class value :: "+integer);
            }
        });

        //Method 4: Explicit Lambda Function
        Consumer<Integer> myListAction = n -> {
            System.out.println("Method 4: forEach Lambda impl Value :: "+n);
        };
        myNumberList.forEach(myListAction);

        //Method 5: Implicit Lambda Function
        myNumberList.forEach(n -> {
            System.out.println("Method 5: forEach Lambda impl Value :: "+n);
        });

        //Method 6: Implicit Lambda Function to print double value
        Function<Integer, Double> toDoubleFunction = Integer::doubleValue;
        myNumberList.forEach(n->{
            System.out.println("Method 6: forEach Lambda double Value :: "+
                    toDoubleFunction.apply(n));
        });

        //Method 7: Implicit Lambda Function to check even
        Predicate<Integer> isEvenFunction = n -> n > 0 && n%2 == 0;
        myNumberList.forEach(n -> {
            System.out.println("Method 7: forEach Value of: "+n+"check for Even : " + isEvenFunction.test(n));
        });

        //Method 8: Processing the Stream
        myNumberList.stream().forEach(n->{
            System.out.println("Method 8: Stream forEach Value :: "+n);
        });
    }

}
