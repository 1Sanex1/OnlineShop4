package en.shop.OnlineShop.repositories;

import en.shop.OnlineShop.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<User, Integer> {
    public Optional<User> findByLogin(String login);
}
