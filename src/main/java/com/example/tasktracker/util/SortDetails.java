package com.example.tasktracker.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * For sorting purposes of data
 * 
 *
 * @author Olzhas Syrbek
 */
@Slf4j
public class SortDetails {

    private SortDetails () {}

    private static Sort.Direction getSortDirection(String direction) {
        if (direction.equals("desc")) {
            return Sort.Direction.DESC;
        }
        return Sort.Direction.ASC;
    }

    public static List<Sort.Order> getOrders(String[] sort) {
        log.info("Sort: {}", Arrays.toString(sort));
        if (sort == null || sort.length == 0) {
            return Collections.emptyList();
        }
        List<Sort.Order> orders = new ArrayList<>();
        for (String s : sort) {
            if (!s.isBlank()) {
                String[] split = s.split(",");
                orders.add(new Sort.Order(getSortDirection(split[1]), split[0]));
            }
        }

        log.info("Orders: {}", orders);
        return orders;
    }
}
