package tn.esprit.nephrobackend.Services.Implementations;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.nephrobackend.Entities.User;
import tn.esprit.nephrobackend.Repositories.UserRepository;
import tn.esprit.nephrobackend.Services.Interfaces.UserService;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private final UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<User> getActiveUsers() {
        return userRepository.findByActiveTrue();
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public User createUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Email already exists");
        }
        user.setActive(true);
        return userRepository.save(user);
    }

    @Override
    public User updateUser(Long id, User updatedUser) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setFirstName(updatedUser.getFirstName());
                    user.setLastName(updatedUser.getLastName());
                    user.setEmail(updatedUser.getEmail());
                    user.setRole(updatedUser.getRole());
                    user.setBloodType(updatedUser.getBloodType());
                    user.setWeight(updatedUser.getWeight());
                    user.setHeight(updatedUser.getHeight());
                    user.setDiagnosis(updatedUser.getDiagnosis());
                    user.setBloodPressure(updatedUser.getBloodPressure());
                    user.setCreatinineLevel(updatedUser.getCreatinineLevel());
                    user.setDialysisSessionsPerWeek(updatedUser.getDialysisSessionsPerWeek());
                    user.setDoctorNotes(updatedUser.getDoctorNotes());
                    return userRepository.save(user);
                })
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<User> getUsersByRole(String role) {
        return userRepository.findByRole(role);
    }
}