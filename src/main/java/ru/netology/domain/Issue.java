package ru.netology.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Issue {
    int id;
    String author;
    String created;
    String updated;
    Set<String> label;
    Set<String> assigned;
    Set<String> comment;
    boolean isOpen;


}
