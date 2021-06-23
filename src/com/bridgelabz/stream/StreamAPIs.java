package com.bridgelabz.stream;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.util.Comparator.*;

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

        //Method 9: Process the Stream, Apply Operations on the Stream and then
        // Store the Result
        List<Double> streamList = myNumberList.stream().filter(isEvenFunction).map(toDoubleFunction).collect(Collectors.toList());
        System.out.println("Method 9: Printing Double List: "+streamList);

        //Method 10: Listing the first Even
        Integer first = myNumberList.stream()
                        .filter(isEvenFunction)
                        .peek(n-> System.out.println("Peek Even Number: " +n))
                        .findFirst()
                        .orElse(null);
        System.out.println("Method 10: First Even Number :"+first);

        //Method 11: Minimum Even Numbers
        Integer min = myNumberList.stream()
                      .filter(isEvenFunction)
                      .min((n1, n2) -> n1-n2)
                      .orElse(null);
        System.out.println("Method 11: Min Even Number : "+min);

        //Method 12: Maximum Even Numbers
        Integer max = myNumberList.stream()
                      .filter(isEvenFunction)
                      .max(Comparator.comparing(Integer::intValue))
                      .orElse(null);
        System.out.println("Method 12: Max Even Number : "+max);

        //Method 13: Sum, Count and Average of numbers
        Integer sum = myNumberList.stream()
                      .reduce(0, Integer::sum);
        long count = myNumberList.stream().count();
        System.out.println("Method 13: Average of " +sum+"/"+count+" = "+sum/count);
    }

}
