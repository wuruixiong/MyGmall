package wrx.web.gmall.passport.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class TestController {

    @RequestMapping("setCookie")
    public String setCookie(HttpServletResponse response) {
        Cookie cookie=new Cookie("test", "cookie.test");
        response.addCookie(cookie);
        return "set success";
    }
    @RequestMapping("getCookie")
    public String getCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        StringBuilder builder = new StringBuilder();
        for (Cookie cookie : cookies) {
            builder.append(cookie.getName() + ":" + cookie.getValue() + "\n");
        }
        return builder.toString();
    }

}
