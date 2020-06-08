package ru.netology.manager;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.netology.domain.Issue;
import ru.netology.exception.IssueException;
import ru.netology.repository.IssueRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;


@Data
@AllArgsConstructor
@NoArgsConstructor

public class IssueManager {

    private IssueRepository repository;


    public Issue getById(int id) {
        for (Issue issue : repository.findAll()) {
            if (issue.getId() == id) {
                return issue;
            }
        }
        return null;
    }

    public List<Issue> findOpen() {
        return repository.findAll().stream().filter(Issue::isOpen).collect(Collectors.toList());
//        return issues.stream().filter(issue -> Boolean.parseBoolean(String.valueOf(issue.isOpen()))).collect(Collectors.toList());
//        List<Issue> result = new ArrayList<>();
//        for (Issue issue : repository.findAll()) {
//            if (issue.isOpen()) {
//                result.add(issue);
//            }
//        }
//        return result;
    }

    public List<Issue> findClose() {
        return repository.findAll().stream().filter(Issue -> !Issue.isOpen()).collect(Collectors.toList());
    }

    public List<Issue> findByAuthor(Predicate<Issue> predicate) {
        List<Issue> result = new ArrayList<>();
        for (Issue issue : repository.findAll()) {
            if (predicate.test(issue)) {
                result.add(issue);
            }
        }
        return result;
//        return repository.findAll().stream().filter(issue -> issue.getAuthor().equalsIgnoreCase(s)).collect(Collectors.toList());
    }

    public HashSet<Issue> filterByAssigned(Set<String> s) {
        HashSet<Issue> result = new HashSet<>();
        for (Issue issue : repository.findAll()) {
            if (issue.getAssigned().containsAll(s)) {
                result.add(issue);
            }
        }
        return result;
    }

    public Issue updateIssue(int id) {
        List<Issue> result = new ArrayList<>();
        for (Issue issue : repository.findAll()) {
            if (issue.getId() == id) {
                if (issue.isOpen() == false) {
                    issue.setOpen(true);

                    return issue;
                }
                if (issue.isOpen() == true) {
                    issue.setOpen(false);
                    return issue;
                }
            }
        }
        return null;
    }

//    public Issue openById(int id) throws IssueException {
//        for (Issue issue : repository.findAll()) {
//            if (issue.isOpen()) {
//                throw new IssueException("Issue with id " + id + " is already open");
//            }
//            else {
//                issue.setOpen(true);
//            }
//        }
//        return null;
//    }

    public Issue openById(int id) {
            if (!getById(id).isOpen()) {
                getById(id).setOpen(true);
                return getById(id);
            }
        return null;
    }

    public Issue closeById(int id) {
        if (getById(id).isOpen()) {
            getById(id).setOpen(false);
            return getById(id);
        }
        return null;
    }

//    public Issue closeById(int id) throws IssueException {
//        for (Issue issue : repository.findAll()) {
//            if (!issue.isOpen()) {
//                throw new IssueException("Issue with id " + id + " is already closed");
//            }
//            else {
//                issue.setOpen(false);
//            }
//        }
//        return null;
//    }


//    public Issue closeById(int id) {
//        for (Issue issue : repository.findAll()) {
//            if (issue.isOpen()) {
//                issue.setOpen(false);
//                return issue;
//            }
//        }
//        return null;
//    }


    public HashSet<Issue> filterByLabel(Set<String> s) {
        HashSet<Issue> result = new HashSet<>();
        for (Issue issue : repository.findAll()) {
            if (issue.getLabel().containsAll(s)) {
                result.add(issue);
            }
        }
        return result;
    }
}
