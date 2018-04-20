package com.utils;

/**
 * Created by z on 2018/3/28.
 */
public class Response<T> {

    private Integer code;
    private String msg;
    private T data;

    public static Response ok(){
        Response response = new Response();
        response.setCode(0);
        response.setMsg("成功");
        return response;
    }

    public static Response ok(Object data){
        Response response = new Response();
        response.setCode(0);
        response.setMsg("成功");
        response.setData(data);
        return response;
    }

    public static Response error(){
        Response response = new Response();
        response.setCode(1);
        response.setMsg("失败");
        return response;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
