package ru.netology.repository;

import org.junit.jupiter.api.Test;
import ru.netology.domain.Issue;
import ru.netology.manager.Manager;

import java.util.*;

import static java.util.Arrays.asList;

class RepositoryTest {
    private Repository repository = new Repository();
    private Manager manager = new Manager(repository);

    @Test
    void addAll() {
        Collection<Issue> issues = new ArrayList<>();
        issues.add(new Issue(1, "Lem", "2dayAgo", "1hourAgo", new HashSet<String>(asList("label1", "label2")), new HashSet<>(asList("user1","goodMan")), new HashSet<>(asList("добавьте это.","добавил то.")), true));
        issues.add(new Issue(3, "goodMan", "2dayAgo", "1hourAgo", new HashSet<String>(asList("label3", "label2")), new HashSet<>(asList("guest","crazy")), new HashSet<>(asList("удалить.","выполнено")), true));
        issues.add(new Issue(2, "crazy", "2dayAgo", "1hourAgo", new HashSet<String>(asList("label7", "label4")), new HashSet<>(asList("user8")), new HashSet<>(asList("добавьте функционал","сделанно.")), false));
        issues.add(new Issue(5, "user1", "2dayAgo", "1hourAgo", new HashSet<String>(asList("label4", "label5")), new HashSet<>(asList("user1","guest")), new HashSet<>(asList("добавьте это.","сделанно.")), false));
        issues.add(new Issue(4, "Lem", "2dayAgo", "1hourAgo", new HashSet<String>(asList("label7", "label2")), new HashSet<>(asList("homyak","goodMan")), new HashSet<>(asList("исправить ошибки","OK")), true));

        repository.addAll(issues);


//        System.out.println(manager.findOpen());
//        System.out.println(manager.findByAuthor("Lem"));
//        System.out.println(manager.filterByAssigned("goodMan"));

        System.out.println(manager.filterByLabelMore(new HashSet<>(asList("label1"))));

    }


}