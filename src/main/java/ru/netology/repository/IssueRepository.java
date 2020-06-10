package ru.netology.repository;

import ru.netology.domain.Issue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class IssueRepository {
    List<Issue> issues = new ArrayList<>();

    public List<Issue> findAll() {
        return issues;
    }

    public void save(Issue issue) {
        issues.add(issue);
    }

    public void remove(Issue issue) {
        issues.remove(issue);
    }

    public void removeById(int id) {
        issues.removeIf(issue -> issue.getId() == id);
    }

    public boolean addAll(Collection<Issue> items) {
        return issues.addAll(items);
    }
}
