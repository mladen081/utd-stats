package u.utd.service.impl;

import lombok.AllArgsConstructor;
import u.utd.dto.UserDto;
import u.utd.entity.User;
import u.utd.exception.ResourceNotFoundException;
import u.utd.repository.UserRepository;
import u.utd.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private ModelMapper modelMapper;

    @Override
    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteUser(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        // Clear the roles to remove associations
        user.getRoles().clear();
        userRepository.save(user); // Save changes to clear roles

        // Now delete the user
        userRepository.delete(user);
    }

}
