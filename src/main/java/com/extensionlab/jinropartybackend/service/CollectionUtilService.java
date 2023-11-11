package com.extensionlab.jinropartybackend.service;

import java.util.List;
import java.util.Random;
import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

@Service
public class CollectionUtilService {

    public <T> List<T> getConcatList(List<T> listA, List<T> listB) {
        List<T> immutableConcatList = Stream.concat(listA.stream(), listB.stream()).toList();
        List<T> mutableConcatList = new ArrayList<>(immutableConcatList);
        return mutableConcatList;
    }

    public <T> List<T> getShuffleList(List<T> list) {
        List<T> copyList = new ArrayList<>(list);
        Collections.shuffle(copyList);
        return copyList;
    }

    public <T> T getRandomElement(List<T> list) {
        Random random = new Random();
        int randomIndex = random.nextInt(list.size());
        return list.get(randomIndex);
    }

    public <T> List<T> getSubList(List<T> list, int fromIndex, int toIndex) {
        List<T> subList = new ArrayList<>(list.subList(fromIndex, toIndex));
        return subList;
    }

    public <T> List<T> getMinusList(List<T> firstList, List<T> secondList) {
        List<T> minusList = firstList.stream().filter(e -> !secondList.contains(e)).collect(Collectors.toList());
        return minusList;
    }
}
