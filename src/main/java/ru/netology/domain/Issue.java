package ru.netology.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor


public class Issue {
    private int id;
    private String author;
    private String dateCreated;
    private String dateUpdated;
    private Set<String> label;
    private Set<String> assigned;
    private Set<String> comment;
    private boolean isOpen;

}
