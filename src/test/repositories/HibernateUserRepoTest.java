package repositories;

import config.AppConfig;
import config.TestDataSourceConfig;
import entities.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import repositories.repo.UserRepo;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestDataSourceConfig.class, AppConfig.class})
@Transactional
public class HibernateUserRepoTest {

    @Autowired
    private UserRepo hr;

    @Test
    public void checkFindById() {
        User u = hr.findById(1L);
        assertThat(u.getEmail(), is("123456"));
    }
}
