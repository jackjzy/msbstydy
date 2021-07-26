package com.ruoyi.app.intercepts;

import cn.hutool.core.util.ObjectUtil;
import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ruoyi.common.enums.BizCodeEnum;
import com.ruoyi.common.utils.jwt.JWTUtils;
import com.ruoyi.system.domain.admin.FfwyUser;
import com.ruoyi.system.service.IFfwyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.SignatureException;
import java.util.HashMap;
import java.util.Map;
@Component
public class JWTintercepts implements HandlerInterceptor {
    @Autowired
    private IFfwyUserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取请求头信息
        String token = request.getHeader(JWTUtils.TOKRN);
        FfwyUser ffwyUser = userService.selectFfwyUserByToken(token);


        Map<String,Object> map =new HashMap<>();
        try{
            if (!ObjectUtil.isEmpty(ffwyUser)){
                if (!ffwyUser.getToken().equals(token)){
                    new Exception("token无效");
                }

            }
            JWTUtils.verifyy(token);

            return true;
        }catch (SignatureVerificationException e){
            e.printStackTrace();
            map.put("msg", BizCodeEnum.TOKEN_SIGN_EXCEPTION.getMessage());
            map.put("code",BizCodeEnum.TOKEN_SIGN_EXCEPTION.getCode());

        }catch (TokenExpiredException e){
            e.printStackTrace();
            map.put("msg",BizCodeEnum.TOKEN_EXPIRED_EXCEPTION.getMessage());
            map.put("code",BizCodeEnum.TOKEN_EXPIRED_EXCEPTION.getCode());
        }catch (AlgorithmMismatchException e){
            e.printStackTrace();
            map.put("msg",BizCodeEnum.ALGORITHM_MISMATCH_EXCEPTION.getMessage());
            map.put("code",BizCodeEnum.ALGORITHM_MISMATCH_EXCEPTION.getCode());
        }catch (Exception e){
            e.printStackTrace();
            map.put("msg",BizCodeEnum.TOEKN_EXCTPTION.getMessage());
            map.put("code",BizCodeEnum.TOEKN_EXCTPTION.getCode());
        }
        String json = new ObjectMapper().writeValueAsString(map);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().println(json);
        return false;
    }
}
