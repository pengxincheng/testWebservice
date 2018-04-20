package com.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;

import static org.apache.struts2.ServletActionContext.getRequest;

/**
 * Created by pxc on 2017/2/22.
 */

@Controller
public class HelloAction extends ActionSupport{
    @Override

    @Action(value = "hello", results = {@Result(location = "/success.jsp")})
    public String execute() throws Exception {

        HttpServletRequest httpServletRequest = getRequest();
        String str = "hello";
        httpServletRequest.setAttribute("msg",str);
        return SUCCESS;
    }
}
