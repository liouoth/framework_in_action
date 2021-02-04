package action;

import com.opensymphony.xwork2.Action;
import service.UserService;

public class UserAction implements Action {
    private UserService userService;

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(){
        userService.login("123","123");
        return Action.SUCCESS;
    }
}
