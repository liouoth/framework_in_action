import com.leo.dao.UserDao;
import com.leo.entity.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestSpringMybatis {
    @Test
    public void test(){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("/applicationContext.xml");
        UserDao userDao = (UserDao)ctx.getBean("userDao");
        userDao.save(new User("1212121","121212"));
    }
}
