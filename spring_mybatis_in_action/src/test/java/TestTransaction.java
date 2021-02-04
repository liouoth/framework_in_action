import com.leo.dao.UserDao;
import com.leo.entity.User;
import com.leo.service.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestTransaction {
    @Test
    public void test(){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("/applicationContext.xml");
        UserService userService = (UserService)ctx.getBean("userService");
        try {
            userService.register(new User("1212121","121212"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
