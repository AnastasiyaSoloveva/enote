package repositories;

import config.AppConfig;

import entities.Notebook;
import entities.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestDataSourceConfig.class, AppConfig.class})
@Transactional
public class HibernateUserRepoTest {

    private static final String NEWPASS = "newpass";
    private static final long WRONG_ID = -1L;
    private List<User> users = new ArrayList<>();
    private List<Notebook> notebooks = new ArrayList<>();

    @Qualifier("UserRepo")
    @Autowired
    UserRepo userRepo;

    @Before
    public void setUp() {
        assertNotNull(userRepo);

        setUsers();
        setNotebooks();

        Set<Notebook> notebooks = new HashSet<>();

        users.get(0).setNotebooks(notebooks);
        userRepo.save(users.get(0));

        users.get(1).setNotebooks(notebooks);

        userRepo.save(users.get(1));
    }

    @Test
    public void testFindAll() {
        List<User> userList = userRepo.findAll();
        Assert.assertTrue(userList.size() > 0);
    }

    @Test
    public void testFindAllByUserName() {
        List<User> userList = userRepo.findAllByEmail("1111@mail.de", true);
        assertTrue(userList.size() == 1);
    }

    @Test
    public void testFailFindById() {
        User user = userRepo.findById(WRONG_ID);
        assertNull(user);
    }

    @Test
    public void testCreate() {
        userRepo.save(users.get(0));
        List<User> afterSave = userRepo.findAllByEmail(users.get(0).getEmail(), true);
        assertTrue(afterSave.size() == 1);
    }

    @Test
    public void testUpdate() {
        List<User> findByEmailList = userRepo.findAllByEmail(users.get(1).getEmail(), true);
        assertNotNull(findByEmailList);
        User user = findByEmailList.get(0);
        assertNotNull(user);
        userRepo.updatePassword(user.getId(), NEWPASS);
        List<User> afterUpdateList = userRepo.findAllByEmail(users.get(1).getEmail(), true);
        assertNotNull(findByEmailList);
        user = afterUpdateList.get(0);
        assertNotNull(user);
        assertTrue(user.getPassword().equals(NEWPASS));
    }

    @Test
    public void testDelete() {
        List<User> beforeDeletion = userRepo.findAllByEmail("2222@mail.de", true);
        assertNotNull(beforeDeletion);
        User user = beforeDeletion.get(0);
        assertNotNull(user);
        userRepo.deleteById(user.getId());

        List<User> afterDeletion = userRepo.findAllByEmail("2222@mail.de", true);
        assertEquals(afterDeletion, Collections.EMPTY_LIST);
    }

    @After
    public void tearDown() {
        List<User> users = userRepo.findAll();
        for (User u : users) {
            userRepo.deleteById(u.getId());
        }
    }

    private void setNotebooks() {
        Notebook notebook = new Notebook();
        notebook.setName("FirstPadOf1111");
        notebook.setNotes(Collections.emptySet());
        notebook.setUser(users.get(0));
        notebooks.add(notebook);

        notebook = new Notebook();
        notebook.setName("SecondPadOf1111");
        notebook.setNotes(Collections.emptySet());
        notebook.setUser(users.get(0));
        notebooks.add(notebook);

        notebook = new Notebook();
        notebook.setName("FirstPadOf2222");
        notebook.setNotes(Collections.emptySet());
        notebook.setUser(users.get(1));
        notebooks.add(notebook);
    }

    private void setUsers() {
        User user = new User();
        user.setEmail("1111@mail.de");
        user.setPassword("1111");
       // user.setIsActive(1L);
        users.add(user);

        user = new User();
        user.setEmail("2222@mail.de");
        user.setPassword("2222");
       // user.setIsActive(1L);
        users.add(user);

        user = new User();
        user.setEmail("3333@mail.de");
        user.setPassword("3333");
       // user.setIsActive(1L);
        users.add(user);
    }


}
