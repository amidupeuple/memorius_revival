package pesiykot.memorius.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pesiykot.memorius.persistence.dao.RoleRepository;
import pesiykot.memorius.persistence.dao.UserRepository;
import pesiykot.memorius.persistence.model.Role;
import pesiykot.memorius.persistence.model.User;
import pesiykot.memorius.validation.EmailExistsException;
import pesiykot.memorius.web.dto.UserDto;

import javax.transaction.Transactional;
import java.util.Arrays;

/**
 * Created by danya_000 on 10/15/2017.
 */
@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Transactional
    @Override
    public User registerNewAccount(UserDto userDto) {
        if (emailExists(userDto.getEmail())) {
            throw new EmailExistsException("There is an account with this email address: " + userDto.getEmail());
        }

        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPassword(userDto.getPassword());
        user.setEmail(userDto.getEmail());

        Role roleUser = roleRepository.findByName("ROLE_USER");
        user.setRoles(Arrays.asList(roleUser));
        roleUser.getUsers().add(user);

        roleRepository.save(roleUser);
        return userRepository.save(user);
    }

    private boolean emailExists(String email) {
        User user = userRepository.findByEmail(email);

        if (user != null) {
            return true;
        } else {
            return false;
        }
    }
}
