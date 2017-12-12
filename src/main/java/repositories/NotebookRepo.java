package repositories;

import entities.Note;
import entities.Notebook;
import entities.User;

import java.util.List;
import java.util.Set;

public interface NotebookRepo {
    Notebook findById(Long id);
    List findAllNotes();

    void updateName(Long notebookId, String newName);
    void deleteById(Long notebookId);
    void save(Notebook nb);

    User findUserByNotebookId(Long id);
}
