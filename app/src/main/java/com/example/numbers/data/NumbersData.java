package com.example.numbers.data;

import com.example.numbers.R;
import java.util.ArrayList;
import java.util.List;

public class NumbersData {

    private static final List<Integer> numbers = new ArrayList<Integer>() {{
        add(R.drawable.ic_looks_1);
        add(R.drawable.ic_looks_2);
        add(R.drawable.ic_looks_3);
        add(R.drawable.ic_looks_4);
        add(R.drawable.ic_looks_5);
        add(R.drawable.ic_looks_6);
    }};

    private static final List<Integer> actions = new ArrayList<Integer>() {{
        add(R.drawable.ic_add);
        add(R.drawable.ic_remove);
    }};

    private static final List<Integer> all = new ArrayList<Integer>() {{
        addAll(numbers);
        addAll(actions);
        addAll(numbers);
    }};

    public static List<Integer> getNumbers() {
        return numbers;
    }

    public static List<Integer> getActions() {
        return actions;
    }

    public static List<Integer> getAll() {
        return all;
    }
}
