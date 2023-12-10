package cupid.main.persistence.mysql;

import cupid.main.domain.Entity.Swipe;
import cupid.main.domain.adapter.SwipeAdapter;
import cupid.main.persistence.iJpa.iSwipeJpa;
import cupid.main.persistence.iJpa.iUserJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@Profile("mysql")
public class MySQLSwipeRepository implements SwipeAdapter {
    private iSwipeJpa jpa;

    @Autowired
    public MySQLSwipeRepository(iSwipeJpa iSwipeJpa){this.jpa = iSwipeJpa;}

    @Override
    public Swipe createSwipe(Swipe swipe) {
        return null;
    }

    @Override
    public List<Swipe> getSwipesByUserId(Integer userId) {
        return jpa.findSwipesByOriginUserId(userId);
    }
}
