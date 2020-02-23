package wrx.web.gmall.interceptors;

import com.alibaba.fastjson.JSON;
import org.springframework.web.servlet.ModelAndView;
import wrx.web.gmall.annotations.LoginRequired;
import wrx.web.gmall.util.CookieUtil;
import wrx.web.gmall.util.HttpclientUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Component
public class AuthInterceptor extends HandlerInterceptorAdapter {

    /**
     * 拦截器测试认证中心功能：
     * 访问：8013/toTrade，代码走必须登录的分支，重定向到认证中心的登录页面：8015/index，index.html点击登录，走认证中心的login，
     * 确认登录成功之后代码中返回一个token，index.html的window.location.href再次重定向到8013/toTrade，
     * 再次被拦截，走验证登录分支8015/verify验证token(get请求)，拦截器中修改success的状态，
     * 如果loginSuccess中判断登录成功，return true放行
     *
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 拦截代码
        // 判断被拦截的请求的访问的方法的注解(是否时需要拦截的)
        HandlerMethod hm = (HandlerMethod) handler;
        LoginRequired methodAnnotation = hm.getMethodAnnotation(LoginRequired.class);

        StringBuffer url = request.getRequestURL();
        System.out.println(url);

        // 是否拦截
        if (methodAnnotation == null) {
            return true;
        }

        String token = "";

        String oldToken = CookieUtil.getCookieValue(request, "oldToken", true);
        if (StringUtils.isNotBlank(oldToken)) {
            token = oldToken;
        }

        String newToken = request.getParameter("token");
        if (StringUtils.isNotBlank(newToken)) {
            token = newToken;
        }

        // 是否必须登录
        boolean loginSuccess = methodAnnotation.loginSuccess();// 获得该请求是否必登录成功

        // 调用认证中心进行验证
        String success = "fail";
        Map<String,String> successMap = new HashMap<>();
        if(StringUtils.isNotBlank(token)){
            String ip = request.getHeader("x-forwarded-for");// 通过nginx转发的客户端ip
            if(StringUtils.isBlank(ip)){
                ip = request.getRemoteAddr();// 从request中获取ip
                if(StringUtils.isBlank(ip)){
                    ip = "127.0.0.1";
                }
            }
            // 以下的地址是 认证中心的地址和端口，拦截需要登录的请求
            String successJson  = HttpclientUtil.doGet("http://192.168.58.1:8015/verify?token=" + token+"&currentIp="+ip);

            successMap = JSON.parseObject(successJson,Map.class);

            success = successMap.get("status");

        }

        if (loginSuccess) {
            // 必须登录成功才能使用
            if (!success.equals("success")) {
                //重定向会passport登录
                StringBuffer requestURL = request.getRequestURL();
                response.sendRedirect("http://192.168.58.1:8015/index?ReturnUrl="+requestURL);
                return false;
            }

            // 需要将token携带的用户信息写入
            request.setAttribute("memberId", successMap.get("memberId"));
            request.setAttribute("nickname", successMap.get("nickname"));
            //验证通过，覆盖cookie中的token
            if(StringUtils.isNotBlank(token)){
                CookieUtil.setCookie(request,response,"oldToken",token,60*60*2,true);
            }

        } else {
            // 没有登录也能用，但是必须验证
            if (success.equals("success")) {
                // 需要将token携带的用户信息写入
                request.setAttribute("memberId", successMap.get("memberId"));
                request.setAttribute("nickname", successMap.get("nickname"));

                //验证通过，覆盖cookie中的token
                if(StringUtils.isNotBlank(token)){
                    CookieUtil.setCookie(request,response,"oldToken",token,60*60*2,true);
                }

            }
        }


        return true;
    }



}
