package com.example.lms.utils;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Test
public class CSVUtilsTest {

    public void getCSV() {
        List<String> list1 = Arrays.asList("1", "2", "3");
        String result = CSVUtils.getCSV(list1);
        System.out.println(result);
        Assert.assertEquals(result, "1,2,3");

        long userId = 1;
        List<Long> bookIdList = Arrays.asList(1L, 2L, 3L);

        String sql = "SELECT * FROM book WHERE name IN( "+ CSVUtils.getCSV(bookIdList.stream().map(id -> String.valueOf(id)).collect(Collectors.toList()))+" )";

        List<String> list4 = bookIdList.stream().map(bookId -> "("+bookId+ ")" ).collect(Collectors.toList());

        List<String> list3 = bookIdList.stream().map(bookId -> "(" + userId + ", " + bookId + ")").collect(Collectors.toList());
//
//        List<String> list2 = Arrays.asList("(1, 1)", "(1, 2)", "(1, 3)");
        result = CSVUtils.getCSV(list3);
        System.out.println(result);
        Assert.assertEquals(result, "(1, 1),(1, 2),(1, 3)");
    }
}
