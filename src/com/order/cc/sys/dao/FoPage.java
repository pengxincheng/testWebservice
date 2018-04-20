package com.order.cc.sys.dao;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

public class FoPage {

    private List dataList = null;

    private int totalRecordCount = 0;

    private int pageSize = 10;

    private int pageNum = 1;

    private String navigatorBar;

    private String URL;

    private String requestURL;

    private String formName = "PagebleFormdfads" + UUID.randomUUID().toString().replaceAll("-", "");

    private String listDiv = "listDiv";

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
        if (this.pageSize > 0)
            return;
        this.pageSize = 10;
    }

    public int getPageSize() {
        return this.pageSize;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
        if (this.pageNum <= 0)
            this.pageNum = 1;
    }

    public int getPageNum() {
        return this.pageNum;
    }

    public int getPageCount() {
        return (this.totalRecordCount - 1) / this.pageSize + 1;
    }

    public int getCurrentPageSize() {
        return (this.dataList == null) ? 0 : this.dataList.size();
    }

    public void setTotalRecordCount(int totalRecordCount) {
        this.totalRecordCount = totalRecordCount;

        if (this.totalRecordCount >= 0)
            return;
        this.totalRecordCount = 0;
    }

    public int getTotalRecordCount() {
        return this.totalRecordCount;
    }

    public void setDataList(List dataList) {
        this.dataList = dataList;
    }

    public List getDataList() {
        return this.dataList;
    }

    public int getNextPageNum() {
        return (this.pageNum >= getPageCount()) ? 0 : this.pageNum + 1;
    }

    public int getLastPageNum() {
        return (this.pageNum >= getPageCount()) ? 0 : getPageCount();
    }

    public int getFirstPageNum() {
        return (this.pageNum <= 1) ? 0 : 1;
    }

    public int getPrePageNum() {
        return this.pageNum - 1;
    }

    public String getListDiv() {
        return listDiv;
    }

    public void setListDiv(String listDiv) {
        this.listDiv = listDiv;
    }

    public String setRequestParameters(HttpServletRequest request) {
        return setRequestParameters(request, null);
    }

    public String setAjaxRequestParameters(HttpServletRequest request) {
        return setAjaxRequestParameters(request, null);
    }

    public String setRequestParameters(HttpServletRequest request, HashMap hashMap) {
        String requestParameters = this.setHiddenParams(request, hashMap);
        this.navigatorBar = getNavigatorBar(requestParameters, false);
        return this.navigatorBar;
    }

    public String setAjaxRequestParameters(HttpServletRequest request, HashMap hashMap) {
        String requestParameters = this.setHiddenParams(request, hashMap);
        this.navigatorBar = getNavigatorBar(requestParameters, true);
        return this.navigatorBar;
    }

    public String getNavigatorBar() {
        if (this.navigatorBar == null) {
            return getNavigatorBar(null, false);
        }

        return this.navigatorBar;
    }

    private String setHiddenParams(HttpServletRequest request, HashMap hashMap) {
        String requestParameters = "";

        if (request != null) {
            Enumeration<String> e = request.getParameterNames();
            String[] ignore = {"requestPageNumfdsafds", "sortField"};
            while (e.hasMoreElements()) {
                String name = (String) e.nextElement();
                String value = request.getParameter(name);

                if (name == null)
                    continue;
                if (value == null) {
                    continue;
                }

                if ((hashMap != null) && (hashMap.get(name) != null)) {
                    continue;
                }
                if(ArrayUtils.contains(ignore, name)) continue;
                requestParameters = requestParameters
                        + "<input type=\"hidden\" name=\"" + name
                        + "\" value='" + value + "'>\n";
            }
            String v = request.getParameter("requestPageNumfdsafds");
            requestParameters += ("<input type=\"hidden\" name=\"requestPageNumfdsafds\" value=\""
                    + v + "\">\n");
            String sortField = request.getParameter("sortField");
            if(sortField != null && !"".equals(sortField)) {
                requestParameters += "<input type=\"hidden\" name=\"sortField\" value=\""
                        + sortField + "\">\n";
            }


            String prefix = request.getContextPath();

            if (StringUtils.isNotBlank(this.requestURL)) {
            	this.URL = prefix + "/" + this.requestURL;
            } else {
            	String servlet = request.getServletPath();
            	this.URL = prefix + servlet;
            }
        }
        if (hashMap != null) {
            Iterator iterator = hashMap.keySet().iterator();
            while (iterator.hasNext()) {
                String name = (String) iterator.next();
                String value = (String) hashMap.get(name);

                requestParameters = requestParameters
                        + "<input type=\"hidden\" name=\"" + name
                        + "\" value=\"" + value + "\">\n";
            }
        }
        return requestParameters;
    }

    private String getNavigatorBar(String requestParameters, boolean isAjax) {
        String navigatorBar = "";
        String pagination = "<div class=\"pagination pagination-right\"><ul>";

        if (this.formName == null) {
            this.formName = "PagebleFormdfads";
        }

        if (this.URL == null) {
            navigatorBar += "<form name=\"" + this.formName + "\" method=\"post\" >\n";
        }else {
            navigatorBar += "<form name=\"" + this.formName + "\" method=\"post\" action=\"" + this.URL + "\">\n";
        }

        if (requestParameters != null) {
            navigatorBar += requestParameters;
        }

        if(getPrePageNum() > 0) {
            pagination += "<li><a href=\"" + this.getFormSubmitWay(isAjax, getPrePageNum()) + "\">&laquo;</a></li>";
        }else {
            pagination += "<li><a href=\"javascript:void(0)\">&laquo;</a></li>";
        }
        if(getPageCount() <= 7) {
            pagination += this.getPaginationNum(1, getPageCount(), this.formName, isAjax);
        }else {
            if(getPageNum()-3 > 1) {
                pagination += "<li><a href=\"" + this.getFormSubmitWay(isAjax, getFirstPageNum()) + "\">" + getFirstPageNum() + "</a></li>";
                pagination += "<li class=\"disabled\"><a href=\"javascript:void(0)\">...</a></li>";
            }
            if(getPageNum() == getPageCount()) {
                pagination += this.getPaginationNum(getPageCount()-6, getPageCount(), this.formName, isAjax);
            }else if(getPageCount()-getPageNum()<3) {
                pagination += this.getPaginationNum(getPageNum()-(6-(getPageCount()-getPageNum())), getPageNum(), this.formName, isAjax);
            }else {
                pagination += this.getPaginationNum(getPageNum()-3<=0?1:getPageNum()-3, getPageNum(), this.formName, isAjax);
            }
            if(getPageNum()+3 < getPageCount()) {
                pagination += this.getPaginationNum(getPageNum()+1, getPageNum() + 3, this.formName, isAjax);
                pagination += "<li class=\"disabled\"><a href=\"javascript:void(0)\">...</a></li>";
                pagination += "<li><a href=\"" + this.getFormSubmitWay(isAjax, getLastPageNum()) + "\">" + getLastPageNum() + "</a></li>";
            }else {
                pagination += this.getPaginationNum(getPageNum()+1, getPageCount(), this.formName, isAjax);
            }
        }
        if(getNextPageNum() > 0) {
            pagination += "<li><a href=\"" + this.getFormSubmitWay(isAjax, getNextPageNum()) + "\">&raquo;</a></li>";
        }else {
            pagination += "<li><a href=\"javascript:void(0)\">&raquo;</a></li>";
        }

        pagination += "</ul></div>";
        navigatorBar += pagination + "</form>\n ";

        return navigatorBar;
    }

    private String getPaginationNum(int start, int end, String formName, boolean isAjax) {
        String paginationNum = "";
        for(int i = start; i<=end; i++) {
            paginationNum += "<li" + (i == getPageNum()?" class=\"active\"":"") + "><a href=\"" + this.getFormSubmitWay(isAjax, i) +
                    "\">" + i + "</a></li>";
        }
        return paginationNum;
    }

    private String getFormSubmitWay(boolean isAjax, int pageNumber) {
        String formSubmit = "";
        if(isAjax) {
            formSubmit = "javascript:" + this.formName + "." + "requestPageNumfdsafds" + ".value=" +
                    pageNumber + ";$('form[name=" + this.formName + "]').ajaxSubmitFix({target: '#" + this.listDiv + "'});";
        }else {
            formSubmit = "javascript:" + this.formName + "." + "requestPageNumfdsafds" + ".value=" +
                    pageNumber + ";" + this.formName + ".submit();";
        }
        return formSubmit;
    }

    public static int getRequestPageNum(HttpServletRequest request) {
        try {
            return Integer.parseInt(request
                    .getParameter("requestPageNumfdsafds"));
        } catch (Exception e) {
        }
        return 0;
    }

    public static String getSortField(HttpServletRequest request) {
        return request.getParameter("sortField");
    }

    public String getDataListSize() {
        if (getDataList() == null) {
            return null;
        }
        if (getDataList().size() <= 0) {
            return null;
        }
        return String.valueOf(getDataList().size());
    }

	public void setRequestURL(String requestURL) {
		this.requestURL = requestURL;
	}
}