package com.imooc.miaosha.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.imooc.miaosha.domain.User;
import com.imooc.miaosha.redis.RedisService;
import com.imooc.miaosha.result.CodeMsg;
import com.imooc.miaosha.result.Result;
import com.imooc.miaosha.service.UserService;

@Controller
@RequestMapping("/demo")
public class SampleController {
	@Autowired
	UserService userservice;
	@Autowired
	RedisService redisService;
	
	@RequestMapping("/home")
	@ResponseBody
	String home() {
		return "heill word";
	}
	@RequestMapping("/hello")
	 @ResponseBody
	public Result<String>hello(){
		return Result.success("hello,imooc");
	}
	@RequestMapping("/helloError")
    @ResponseBody
    public Result<String> helloError() {
 		return Result.error(CodeMsg.SERVER_ERROR);
 		//return new Result(500102, "XXX");
    }
	@RequestMapping("/thymeleaf")
    public String  thymeleaf(Model model) {
 		model.addAttribute("name", "罗立强");
 		return "hello";
    }
	@RequestMapping("/db/get")
    @ResponseBody
    public Result<User> dbGet() {
		User user=userservice.getById(1);
 		return Result.success(user);
 		
    }
	
	@RequestMapping("/db/tx")
    @ResponseBody
    public Result<Boolean> dbTx() {
		userservice.tx();
 		return Result.success(true);
 		
    }
	
	@RequestMapping("/redis/get")
    @ResponseBody
    public Result<Boolean> redisgGet() {
		redisService.get(String,Class<T>clazz);
 		return Result.success(true);
 		
    }
}
