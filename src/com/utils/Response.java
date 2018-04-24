package com.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;

/**
 * webservice接口响应实体
 * Created by pxc on 15/8/19.
 */
public class Response {

    private JSONObject resultJson; // 实际的响应内容

    /**
     * 构造最终的响应内容
     * @return
     */
    public String build() {
        return resultJson.toString();
    }

    /**
     * 设置接口描述信息
     * @param message
     * @return
     */
    public Response message(String message) {
        resultJson.put("resultMessage", message);
        return this;
    }

    /**
     * 设置错误码
     * @param errorCode
     * @return
     */
    public Response errorCode(String errorCode) {
        if (StringUtils.isNotBlank(errorCode)) {
            resultJson.put("errorCode", errorCode);
        }
        return this;
    }

    /**
     * 接口调用成功
     * @return
     */
    public static Response ok() {
        return ok(null);
    }

    /**
     * 接口调用成功
     * @param result
     * @return
     */
    public static Response ok(Object result) {

        JSONObject resultJson = new JSONObject();
        resultJson.put("result", "SUCCESS");
        resultJson.put("resultMessage", "交互成功");

        if (result != null) {
            resultJson.put("content", result);
        }

        Response response = new Response();
        response.resultJson = resultJson;

        return response;
    }

    /**
     * 接口调用失败
     * @return
     */
    public static Response error() {
        return error(ErrorStatus.ERROR);
    }

    /**
     * 接口调用失败
     * @param errorMsg
     * @return
     */
    public static Response error(String errorMsg) {
        return error().message(errorMsg);
    }

    /**
     * 接口调用失败
     * @param status
     * @return
     */
    public static Response error(ErrorStatus status) {
        JSONObject resultJson = new JSONObject();
        resultJson.put("result", status.name());
        resultJson.put("resultMessage", status.getDesc());

        Response response = new Response();
        response.resultJson = resultJson;

        return response;
    }

    public JSONObject getResultJson() {
        return resultJson;
    }

    public void setResultJson(JSONObject resultJson) {
        this.resultJson = resultJson;
    }
}
