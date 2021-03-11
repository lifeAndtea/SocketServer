package com.fqy.qzdtest.socketserver.netty.websocket;


import lombok.Data;


import java.io.Serializable;

/**
 * Entity基类
 *
 * @author niexin
 * @date 2018-08-24 18:58
 */
@Data
public class BaseEntity<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    protected String id;

    protected String creator;                                   // 创建者

    protected String createDate;                                // 创建日期

    protected String modifier;                                  // 更新者

    protected String modifyDate;                                // 更新日期

    protected Integer delFlag; // 删除标记 0:正常，1-删除

    protected String applicationCode;                           // 系统编号

    protected boolean isNewRecord;                              // 是否为新记录

    protected String[] ids;                                     // ID数组

    protected String idString;                                  // ID字符串，多个用逗号隔开

    public BaseEntity() {
    }

    public BaseEntity(String id) {
        this();
        this.id = id;
    }
}