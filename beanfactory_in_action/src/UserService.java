public class UserService {
    public void use(){
        System.out.println("UserService called!");
    }

    public static void main(String[] args) {
        UserService userService = (UserService) BeanFactory.getBean("UserService");
        userService.use();
    }
}
