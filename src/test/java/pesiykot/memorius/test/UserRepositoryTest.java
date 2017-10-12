package pesiykot.memorius.test;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pesiykot.memorius.persistence.dao.UserRepository;
import pesiykot.memorius.persistence.model.User;
import pesiykot.memorius.spring.TestDbConfig;

import javax.transaction.Transactional;

import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestDbConfig.class})
@Transactional
public class UserRepositoryTest {

    private User newUser;

    @Autowired
    private UserRepository userRepository;

    @Before
    public void prepareData() {
        newUser = new User();
        newUser.setFirstName("John");
        newUser.setLastName("Smith");
        newUser.setEmail("abc@abc.com");
        newUser.setPassword("qwerty123");
    }

    @Test
    public void saveNewUser() {
        User savedUser = userRepository.save(newUser);
        System.out.println("User Id: " + savedUser.getId());
        assertTrue(savedUser.getId() > 0);
    }
}
