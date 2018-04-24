package com.business.student.action;

import com.business.classes.dao.ClassesDao;
import com.business.classes.po.ClassesEntity;
import com.business.classes.service.ClassesService;
import com.business.student.po.StuEntity;
import com.business.student.service.StuService;
import com.opensymphony.xwork2.ActionSupport;
import com.order.cc.sys.dao.FoPage;
import com.utils.JSONUtils;
import com.utils.Response;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

/**
 * Created by pxc on 2018/4/20.
 */
@Namespace("/")
@ParentPackage("json-default")
public class StuAntion extends ActionSupport {
    private final Logger logger = LoggerFactory.getLogger(StuAntion.class);
    @Autowired
    private StuService stuService;

    /**
     * 添加
     *
     * @return
     */
    @Action(value = "/add", results = {@Result(name = "success", type = "json", params = {"root", "response"})})
    public String addStu() {
        try {
            logger.info("addStu方法");
            stuService.saveStu(stu);
            response = Response.ok();
        } catch (Exception e) {
            response = Response.error();
            logger.error(e.getMessage(), e);
        }

        return SUCCESS;
    }

    /**
     * 分页获取所有
     *
     * @return
     */
    @Action(value = "/getAllFoPage", results = {@Result(name = "success", type = "json", params = {"root", "response"})})
    public String getAllFoPage() {
        try {
            logger.info("getAllFoPage方法");
            stu = new StuEntity();//条件查询 在这里可以设置查询条件
            FoPage page = stuService.getStuPage(stu, 1, 10);
            response = Response.ok(JSONUtils.toJSONInclude(page, "currentPageSize", "pageCount", "totalRecordCount", "dataList", "id", "name",
                    "age", "classesEntity","classesName"));
            System.out.println(response);
        } catch (Exception e) {
            response = Response.error();
            logger.error(e.getMessage(), e);
        }
        return SUCCESS;
    }

    /**
     * id查询
     *
     * @return
     */
    @Action(value = "/getBuyId", results = {@Result(name = "success", type = "json", params = {"root", "response"})})
    public String getBuyId() {
        try {
            logger.info("getBuyId方法");
            //根据id查询并加载出关联对象
            StuEntity stu = stuService.getById(stuId, ClassesEntity.class);
            response = Response.ok(stu);
        } catch (Exception e) {
            response = Response.error();
            logger.error(e.getMessage(), e);
        }
        return SUCCESS;
    }


    private Response response;//返回json

    private String result;

    private StuEntity stu;  //stu入参

    private String stuId;   //根据id查询时入参

    public StuEntity getStu() {
        return stu;
    }

    public void setStu(StuEntity stu) {
        this.stu = stu;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
