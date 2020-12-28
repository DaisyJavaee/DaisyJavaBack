package daisy.teaming.controller;

import daisy.teaming.bean.User;
import daisy.teaming.service.UserService;
import daisy.teaming.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user")
public class UserController
{
    @Autowired
    private UserService userService;

    @RequestMapping("/register")
    public Result register(@RequestBody User user)
    {
        return userService.register(user);
    }

    @RequestMapping("/login")
    public Result login(@RequestBody User user)
    {
        return userService.login(user);
    }

    @RequestMapping("/getUser")
    public Result getUser(HttpServletRequest request)
    {
        return userService.getUser(request);
    }

    @RequestMapping("/updateUser")
    public Result updateUser(@RequestBody User user,HttpServletRequest request)
    {
        return userService.updateUser(user,request);
    }
}


