package com.zyx.zyxojsandbox.controller;

import com.zyx.zyxojsandbox.JavaNativeCodeSandbox;
import com.zyx.zyxojsandbox.model.ExecuteCodeRequest;
import com.zyx.zyxojsandbox.model.ExecuteCodeResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zyx
 * @version 1.0
 * @date 2024/1/8 008 15:32
 */
@RestController("/")
public class MainController {

    /**
     * 请求头
     */
    private static final String AUTH_REQUEST_HEADER = "auth";
    /**
     * 密钥
     */
    private static final String AUTH_REQUEST_SECRET = "secretKey";

    @Resource
    private JavaNativeCodeSandbox javaNativeCodeSandbox;

    @GetMapping("/health")
    public String getHealth() {
        return "ok";
    }

    /**
     * 开放api
     *
     * @param executeCodeRequest
     * @return
     */
    @PostMapping("/executeCode")
    public ExecuteCodeResponse executeCode(@RequestBody ExecuteCodeRequest executeCodeRequest
            , HttpServletRequest request, HttpServletResponse response) {
        //基本认证
        String authHeader = request.getHeader(AUTH_REQUEST_HEADER);
        if (!authHeader.equals(AUTH_REQUEST_SECRET)) {
            response.setStatus(403);
            return null;
        }

        if (executeCodeRequest == null) {
            throw new NullPointerException("executeCodeRequest is null");
        }
        return javaNativeCodeSandbox.executeCode(executeCodeRequest);
    }
}
