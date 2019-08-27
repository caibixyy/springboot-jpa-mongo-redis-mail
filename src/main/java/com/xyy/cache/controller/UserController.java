package com.xyy.cache.controller;

import com.xyy.cache.repository.RoleRepositoty;
import com.xyy.cache.bean.mysqljpa.User;
import com.xyy.cache.service.Impl.UserServiceImpl;
import com.xyy.cache.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    RedisTemplate<String,Object> redisTemplate;



    /**
     * 登陆首页
     * @param user
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/login")
    public String login(User user,Model model) throws Exception{
        String username = user.getUsername();
        String password = user.getPassword();
        User user1 = userService.findByUsernameAndPassword(username,password);
        if(user1!=null){
            return "success";
        }
       else {
           String msg = "账户或密码不对，请检查";
           model.addAttribute("msg",msg);
            return "index";
        }
    }
    @ResponseBody
    @GetMapping("/findByUserNameAndPassword")
    public User findByUserNameAndPassword(String username,String password){
        User user = userService.findByUsernameAndPassword(username, password);
        System.out.println(user);
        return user;
    }

    @ResponseBody
    @GetMapping("/s")
    public void getUser(){
        userService.getUser();
    }

    @ResponseBody
    @RequestMapping("/findAllUser")
    public List<User> findAll(){
        List<User> stuList = userService.findAll();
        return stuList;
    }

    @ResponseBody
    @RequestMapping("/findById/{id}")
    public User findById(@PathVariable Integer id){
        return userService.findById(id);
    }

    @ResponseBody
    @RequestMapping("/page")
    public Page<User> findAlls(){
        //Pageable是接口，PageRequest是接口实现
        // PageRequest的对象构造函数有多个，page是页数，初始值是0，size是查询结果的条数，
        // 后两个参数参考Sort对象的构造方法Pageable pageable = new PageRequest(0,3, Sort.Direction.DESC,"id");
       Page<User> page=userService.findAll(new PageRequest(0,3, Sort.Direction.DESC,"uid"));
        //查询结果总行数
        System.out.println(page.getTotalElements());
        //按照当前分页大小，总页数
        System.out.println(page.getTotalPages());
        //按照当前页数、分页大小，查出的分页结果集合
       return page;
    }


    @ResponseBody
    @RequestMapping("/gts/{id}")
    public List<User> findByUidGreaterThan(@PathVariable Integer id){
        System.out.println(id);
        return userService.findByUidGreaterThan(id);
    }

    //@Scheduled(cron = "0/5 * * * * ?")
    public void taskQuartz(){
        System.out.println("执行定时任务打印");
    }
}
