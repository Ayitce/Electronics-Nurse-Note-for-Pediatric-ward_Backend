package enp.enp_backend.security.service;

import enp.enp_backend.security.dao.UserDao;
import enp.enp_backend.security.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserDao userDao;

    @Override
    public User save(User user){
        return userDao.save(user);
    }

    @Override
    public Page<User> getUsers(Integer pageSize, Integer page) {
        return userDao.getUsers(pageSize,page);
    }

    @Override
    public User getUser(Long id) {
        return userDao.getUsers(id);
    }

}
