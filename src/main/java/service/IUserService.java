package service;

import model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IUserService {
    Page<User> findAll(Pageable pageable);
    List<User> findAllList();
    void save(User user);
}
