package hellostart.startspring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloConroller {

    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute("data","hello!!");
        return "hello";
        //리턴값으로 문자를 반환하면 뷰 리졸버(viewResolver)가 화면을 찾아서 처리한다
        //resource:templates/리턴값.html 으로 변환됨.
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model){
        model.addAttribute("name",name);
        return "hello-template";
    }

    //대신에 HTTP의 BODY에 문자 내용을 직접 반환(HTML BODY TAG를 말하는 것이 아님)
    @GetMapping("hello-string")
    @ResponseBody // 이전처럼 html 소스에 포함된 것이 아닌 return한것 그대로 보낸다
    public String helloString(@RequestParam("name") String name){
        return "heelo" + name;
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }
    // @ResponseBody를 사용하고, 객체를 반환하면 객체가 JSON으로 변환됨
    // 객체일때 달라진다.
    // 즉 , html의 ajax data는 json방식으로 받으니까 데이터를 json형식으로 주기위해 사용하는 것.

    static class Hello{
        private String name; //외부에서 못꺼냄
        // --> get.set으로만 불러 올수 있음 : 자바 빈 표준방식,property 접근 방식


        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }

    }
}
