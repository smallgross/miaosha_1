package com.imooc.miaosha.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.imooc.miaosha.domain.User;
import com.imooc.miaosha.redis.KeyPrefix;
import com.imooc.miaosha.redis.RedisService;
import com.imooc.miaosha.redis.UserKey;
import com.imooc.miaosha.result.CodeMsg;
import com.imooc.miaosha.result.Result;
import com.imooc.miaosha.service.UserService;

@RestController
@RequestMapping("/demo")
public class SampleController {
	@Autowired
	UserService userservice;
	@Autowired
	RedisService redisService;

	@RequestMapping("/home")

	String home() {
		return "heill word";
	}

	@RequestMapping("/hello")

	public Result<String> hello() {
		return Result.success("hello,imooc");
	}

	@RequestMapping("/helloError")

	public Result<String> helloError() {
		return Result.error(CodeMsg.SERVER_ERROR);
		// return new Result(500102, "XXX");
	}

	@RequestMapping("/thymeleaf")
	public String thymeleaf(Model model) {
		model.addAttribute("name", "你好");
		return "hello";
	}

	@RequestMapping("/db/get")

	public Result<User> dbGet() {
		User user = userservice.getById(1);
		return Result.success(user);

	}

	@RequestMapping("/db/tx")

	public Result<Boolean> dbTx() {
		userservice.tx();
		return Result.success(true);

	}

	/**
	 * redis
	 * 
	 * @return
	 */
	@RequestMapping("/redis/get")
	public Result<User> redisgGet() {
		User user = redisService.get(UserKey.getById, "" + 1, User.class);
		return Result.success(user);

	}

	@RequestMapping("/redis/set")
	public Result<Boolean> redisgSet() {
		User user = new User();
		user.setId(1);
		user.setName("张生");
		redisService.set(UserKey.getById, "" + 1, user);// Userkeyid
		return Result.success(true);

	}
}
