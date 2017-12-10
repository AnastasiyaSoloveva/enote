package repositories.repo;

import entities.Tag;

import java.util.List;
import java.util.Set;

public interface TagRepo {

    List findAll();

    Tag findById(Long id);

    List<Tag> findAllByName(String name, boolean exactMatch);

    String findNameById(Long id);

    long countTags();

    void save(Tag tag);



    void deleteById(Long tagId);

    void save(Set<Tag> tags);

}
