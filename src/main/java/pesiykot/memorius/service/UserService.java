package pesiykot.memorius.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pesiykot.memorius.persistence.dao.UserRepository;
import pesiykot.memorius.persistence.model.User;
import pesiykot.memorius.validation.EmailExistsException;
import pesiykot.memorius.web.dto.UserDto;

import javax.transaction.Transactional;

/**
 * Created by danya_000 on 10/15/2017.
 */
@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

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
