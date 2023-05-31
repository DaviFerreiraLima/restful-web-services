package com.rest.webservices.restfulwebservices.user;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Component
public class UserDaoService {

    private static List<User> users = new ArrayList<>();

    private static int userCount = 0;

    static {
        users.add(new User(++userCount,"Davi", LocalDate.now().minusYears(30) ));
        users.add(new User(++userCount,"Joicy", LocalDate.now().minusYears(20)));
        users.add(new User(++userCount,"Fernando", LocalDate.now().minusYears(27)));
    }
    public List<User> findAll(){
        return users;
    }
    public User save ( User user){
        users.add(user);
        return user;
    }

    public User findOne(int id) {
        return users.stream().filter(user -> user.getId().equals(id)).findFirst().orElse(null);
    }

    public User saveUser(User user) {
        user.setId(++userCount);
        users.add(user);
        return user;
    }

    public void deleteById(int id){
        users.removeIf(user -> user.getId().equals(id));
    }
}
