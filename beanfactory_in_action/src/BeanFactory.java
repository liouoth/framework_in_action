import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class BeanFactory {
    private static Properties properties = new Properties();

    static {
        try {
            InputStream inputStream = BeanFactory.class.getResourceAsStream("bean.properties");
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Object getBean(String key){
        String classPath = properties.getProperty(key);
        Object object = null;
        try {
            Class clazz = Class.forName(classPath);
            object = clazz.newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return object;
    }
}
