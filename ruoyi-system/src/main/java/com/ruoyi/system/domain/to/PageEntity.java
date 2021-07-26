package com.ruoyi.system.domain.to;

import lombok.Data;

@Data
public class PageEntity {
    private Integer total;
    private Integer currPage=1;//当前页
    private Integer pageSize;//总页数
    private Integer pageNumber=10;//每页条数
    private Object obj;//分页对象

    public Integer getLimitFront(){
        return (currPage-1)*pageNumber;
    }
    public Integer getLimitAfter(){
        return pageNumber;
    }

    public Integer getPageSize(){
        return (int)Math.ceil((double)total/(double)pageNumber) ;
    }

    public String orderby(Integer sort,String orderBySql) {
        StringBuffer sb=new StringBuffer();
        sb.append("ORDER BY ");
        if(sort==1)sb.append(orderBySql+"  asc");
        else sb.append(orderBySql+"  desc");
        return sb.toString();
    }

}
