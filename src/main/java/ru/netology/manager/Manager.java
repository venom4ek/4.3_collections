package ru.netology.manager;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import ru.netology.domain.Issue;
import ru.netology.repository.Repository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;


@Data
@NoArgsConstructor
@AllArgsConstructor

public class Manager {

    private Repository repository;

    public Issue getById(int id) {
        for (Issue issue : repository.findAll()) {
            if (issue.getId() == id) {
                return issue;
            }
        }
        return null;
    }

    public List<Issue> findOpen() {
//        return issues.stream().filter(issue -> Boolean.parseBoolean(String.valueOf(issue.isOpen()))).collect(Collectors.toList());
        List<Issue> result = new ArrayList<>();
        for (Issue issue : repository.findAll()) {
            if (issue.isOpen()) {
                result.add(issue);
            }
        }
        return result;
    }

    public List<Issue> findClose() {
//        return issues.stream().filter(issue -> !Boolean.parseBoolean(String.valueOf(issue.isOpen()))).collect(Collectors.toList());
        List<Issue> result = new ArrayList<>();
        for (Issue issue : repository.findAll()) {
            if (!issue.isOpen()) {
                result.add(issue);
            }
        }
        return result;
    }

    public List<Issue> findByAuthor(String s) {
        return repository.findAll().stream().filter(issue -> issue.getAuthor().equalsIgnoreCase(s)).collect(Collectors.toList());

    }

    public HashSet<Issue> filterByLabel(String s) {
        HashSet<Issue> result = new HashSet<>();
        for (Issue issue : repository.findAll()) {
            if (issue.getLabel().contains(s) == true) {
                result.add(issue);
            }
        }
        return result;
    }

    public HashSet<Issue> filterByAssigned(String s) {
        HashSet<Issue> result = new HashSet<>();
        for (Issue issue : repository.findAll()) {
            if (issue.getAssigned().contains(s) == true) {
                result.add(issue);
            }
        }
        return result;
    }

    public Predicate<Set> filterByLabelMore(Set<String> s) {
        Predicate<Set> equalLabels = t -> t.equals(s);
//        Predicate<Issue> result = issue -> issue.getLabel().contains(s);
        return equalLabels;
    }

}
