package com.eceris.memory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@SpringBootApplication
@EnableAutoConfiguration
public class Application {

    @RequestMapping("/hello")
    @ResponseBody
    String hello() {
        return "Hello World!";
    }

//    @PostMapping(value="/signin")
//    public Result signin(String email, String password, HttpServletResponse response){
//        Result result = Result.successInstance();
//        MemberMaster loginMember = memberService.signin(email, password);
//        String token = jwtService.createMember(loginMember);
//        response.setHeader("Authorization", token);
//        result.setData(loginMember);
//        return result;
//    }


    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }
}
