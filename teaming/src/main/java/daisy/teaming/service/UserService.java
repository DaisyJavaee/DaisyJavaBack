package daisy.teaming.service;

import com.auth0.jwt.interfaces.DecodedJWT;
import daisy.teaming.bean.User;
import daisy.teaming.mapper.UserMapper;
import daisy.teaming.util.JWTUtils;
import daisy.teaming.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

@Service
@Transactional(rollbackFor = RuntimeException.class)
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public Result register(User user)
    {
        Result result = new Result();
        result.setSuccess(false);
        result.setDetail(null);
        try {
                userMapper.register(user);

                result.setMsg("注册成功");
                result.setSuccess(true);
                result.setDetail(user);

        } catch (Exception e) {
            result.setMsg(e.getMessage());
            result.setSuccess(false);
        }
        return result;
    }

    public Result login(User user)
    {
        Result result = new Result();
        result.setSuccess(false);
        result.setDetail(null);
        try {
            User userIn=userMapper.login(user);
            if(userIn!=null){
                String token= JWTUtils.getToke(user.getAccount());
                result.setMsg("登陆成功");
                result.setSuccess(true);
                result.setDetail(token);
            }
            else
            {
                result.setMsg("登陆失败，请重新登陆");
            }

        } catch (Exception e) {
            result.setMsg(e.getMessage());
            result.setSuccess(false);
        }
        return result;
    }

    public Result getUser(HttpServletRequest request)
    {
        Result result = new Result();
        result.setSuccess(false);
        result.setDetail(null);
        try{
            String token = request.getHeader("token");
            DecodedJWT verify = JWTUtils.decode(token);
            String account = verify.getClaim("account").asString();
            User user=userMapper.getUser(account);
            result.setSuccess(true);
            result.setDetail(user);
        }catch(Exception e)
        {
            result.setSuccess(false);
            result.setMsg(e.getMessage());
        }
        return result;
    }

    public Result updateUser(User user,HttpServletRequest request)
    {
        Result result = new Result();
        result.setSuccess(false);
        result.setDetail(null);
        try{
            String token = request.getHeader("token");
            DecodedJWT verify = JWTUtils.decode(token);
            String account = verify.getClaim("account").asString();
            user.setAccount(account);

            userMapper.updateUser(user);
            result.setSuccess(true);
            result.setMsg("更新成功");
        }catch(Exception e)
        {
            result.setSuccess(false);
            result.setMsg(e.getMessage());
        }
        return result;
    }

}
