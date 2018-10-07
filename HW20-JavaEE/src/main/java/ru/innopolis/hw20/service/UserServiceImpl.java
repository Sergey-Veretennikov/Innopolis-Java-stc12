package ru.innopolis.hw20.service;

import ru.innopolis.hw20.pojo.User;
import ru.innopolis.hw20.repository.dao.UserDao;
import ru.innopolis.hw20.service.utils.HashUtil;

public class UserServiceImpl implements UserService {
    UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public int getRole(String login) {
        User user;
        if (login != null) {
            user = userDao.getUserByLogin(login);
            if (user == null) {
                return -1;
            }
            return user.getRole();
        }
        return -2;
    }

    @Override
    public User checkAuth(String login, String password) {
        //TODO:Удалить тестовые учетки из базы
        User user;
        if ((login != null) && (password != null)) {
            user = userDao.getUserByLogin(login);
            if (user != null) {
                if (user.getPassword().equals(HashUtil.stringToMD5(password))) {
                    return user;
                }
            }
        }
        return null;
    }

    @Override
    public boolean addUser(String login, String password, int role) {
        if (checkAuth(login, password) != null) {
            return false;
        }
        User user = new User(0, login, HashUtil.stringToMD5(password), role);
        if (userDao.addUser(user)) {
            return true;
        }
        return false;
    }
}
