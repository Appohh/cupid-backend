package cupid.main.persistence.mysql;

import cupid.main.domain.Entity.Swipe;
import cupid.main.domain.adapter.SwipeAdapter;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@Profile("mysql")
public class MySQLSwipeRepository implements SwipeAdapter {
    @Override
    public Swipe createSwipe(Swipe swipe) {
        return null;
    }

    @Override
    public List<Swipe> getSwipesByUserId(Integer userId) {
        return null;
    }
}
