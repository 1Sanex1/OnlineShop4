package en.shop.OnlineShop.services;

import en.shop.OnlineShop.models.Product;
import en.shop.OnlineShop.models.User;
import en.shop.OnlineShop.repositories.UsersRepository;
import en.shop.OnlineShop.util.UserStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@Transactional(readOnly = true)
public class UsersService {
    private final UsersRepository usersRepository;

    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public List<User> findAll() {
        return usersRepository.findAll();
    }

    public User find(int id) {
        return usersRepository.findById(id).orElse(null);
    }

    @Transactional
    public void save(User user){
        usersRepository.save(user);
    }

    @Transactional
    public void update(int id, User user) {
        user.setId(id);
        usersRepository.save(user);
    }
    @Transactional
    public void remove(int id){
        usersRepository.deleteById(id);
    }

    public User getCurrent(){
        String login = SecurityContextHolder.getContext().getAuthentication().getName();
        return usersRepository.findByLogin(login).get();
    }

    public String getRole(){
        return getCurrent().getRole();
    }

    public boolean isVisitor(){
        return Objects.equals(getRole(), "ROLE_VISITOR");
    }

    public boolean isShopper(){
        return Objects.equals(getRole(), "ROLE_SHOPPER");
    }

    public boolean isAdmin(){
        return Objects.equals(getRole(), "ROLE_ADMIN");
    }

}
