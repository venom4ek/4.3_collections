package ru.netology.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Issue;
import ru.netology.manager.Manager;

import java.lang.reflect.Array;
import java.util.*;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.*;

class RepositoryTest {
    private Repository repository = new Repository();
    private Manager manager = new Manager(repository);
    Collection<Issue> issues = new ArrayList<>();

    private Issue issue1 = new Issue(1, "Lem", "2dayAgo", "1hourAgo", new HashSet<>(asList("label1", "label2")), new HashSet<>(asList("user1", "goodMan")), new HashSet<>(asList("добавьте это.", "добавил то.")), true);
    private Issue issue2 = new Issue(3, "goodMan", "2dayAgo", "1hourAgo", new HashSet<>(asList("label3", "label2")), new HashSet<>(asList("guest", "crazy")), new HashSet<>(asList("удалить.", "выполнено")), true);
    private Issue issue3 = new Issue(2, "crazy", "2dayAgo", "1hourAgo", new HashSet<>(asList("label1", "label4")), new HashSet<>(asList("user8")), new HashSet<>(asList("добавьте функционал", "сделанно.")), false);
    private Issue issue4 = new Issue(5, "user1", "2dayAgo", "1hourAgo", new HashSet<>(asList("label4", "label5")), new HashSet<>(asList("user1", "guest")), new HashSet<>(asList("добавьте это.", "сделанно.")), false);
    private Issue issue5 = new Issue(4, "Lem", "2dayAgo", "1hourAgo", new HashSet<>(asList("label7", "label2")), new HashSet<>(asList("homyak", "goodMan")), new HashSet<>(asList("исправить ошибки", "OK")), true);


    @BeforeEach
    void setUp() {
        issues.add(issue1);
        issues.add(issue2);
        issues.add(issue3);
        issues.add(issue4);
        issues.add(issue5);

        repository.addAll(issues);
    }

    @Test
    void shouldAddedIssue() {
        List<Issue> expected = Arrays.asList(issue1, issue2, issue3, issue4, issue5);
        assertEquals(expected, repository.findAll());
    }

    @Test
    void shouldOpenIssue() {
        List<Issue> actual = manager.findOpen();
        List<Issue> expected = Arrays.asList(issue1, issue2, issue5);
        assertEquals(expected, actual);
    }

    @Test
    public void shouldCloseIssue() {
        List<Issue> actual = manager.findClose();
        List<Issue> expected = Arrays.asList(issue3, issue4);
        assertEquals(expected, actual);
    }

    @Test
    public void shouldByAuthor() {
        List<Issue> actual = manager.findByAuthor(issue -> issue.getAuthor().equals(("Lem")));
        List<Issue> expected = Arrays.asList(issue1, issue5);
        assertEquals(expected, actual);
    }

    @Test
    public void shouldByAuthorWithNoneExist() {
        List<Issue> actual = manager.findByAuthor(issue -> issue.getAuthor().equals(("Tolkin")));
        List<Issue> expected = Arrays.asList();
        assertEquals(expected, actual);
    }

    @Test
    public void shouldFilterByLabel() {
        HashSet<Issue> actual = manager.filterByLabel(new HashSet<>(asList("label1")));
        HashSet<Issue> expected = new HashSet<>(asList(issue1, issue3));
        assertEquals(expected, actual);
    }

    @Test
    public void shouldFilterByMoreOneLabel() {
        HashSet<Issue> actual = manager.filterByLabel(new HashSet<>(asList("label1","label4")));
        HashSet<Issue> expected = new HashSet<>(asList(issue3));
        assertEquals(expected, actual);
    }

    @Test
    public void shouldFilterByAssigned() {
        HashSet<Issue> actual = manager.filterByAssigned(new HashSet<>(asList("goodMan")));
        HashSet<Issue> expected = new HashSet<>(asList(issue1, issue5));
        assertEquals(expected, actual);
    }

    @Test
    public void shouldFilterByMoreOneAssigned() {
        HashSet<Issue> actual = manager.filterByAssigned(new HashSet<>(asList("goodMan","user1")));
        HashSet<Issue> expected = new HashSet<>(asList(issue1));
        assertEquals(expected, actual);
    }


    @Test
    void update() {
        Issue actual = manager.updateIssue(1);
        Issue expected = new Issue(1, "Lem", "2dayAgo", "1hourAgo", new HashSet<>(asList("label1", "label2")), new HashSet<>(asList("user1", "goodMan")), new HashSet<>(asList("добавьте это.", "добавил то.")), false);
        assertEquals(expected, actual);
    }
}