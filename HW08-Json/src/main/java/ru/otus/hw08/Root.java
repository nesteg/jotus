package ru.otus.hw08;

import java.util.List;
import lombok.Data;

@Data
public class Root {
    private long Id;
    private List<Child> childs;
    private String  name;
    private Double balance;
    private short zip;
}
