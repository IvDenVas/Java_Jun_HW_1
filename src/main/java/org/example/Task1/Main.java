package org.example.Task1;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;

public class Main {
    public static void main(String[] args) {
        List<Integer> list = List.of(1,2,3,4,5,6,7,8,9,10);
        List<Integer> resList = list.stream()
                .filter(i -> i % 2 == 0)
                .toList();
        int res = resList.stream()
                .mapToInt(n -> n)
                .sum() / resList.size();
        System.out.println(res);
    }
}
