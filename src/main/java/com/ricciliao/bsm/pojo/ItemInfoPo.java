package com.ricciliao.bsm.pojo;

import org.apache.ibatis.type.Alias;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;

@Entity(name = "itemInfo")
@Table(name = "item_info")
@Alias(value = "itemInfo")
public class ItemInfoPo {

    @Id
    private Integer id;
    private String itemGuid;
    private Integer userId;
    private String itemName;
    private String itemPath;
    private Integer itemType;
    private String itemPassword;
    private Date itemCreateDate;
    private Date itemLastModifiedDate;
    private String itemAnnotation;
    private Integer status;

    private String itemContent;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getItemGuid() {
        return itemGuid;
    }

    public void setItemGuid(String itemGuid) {
        this.itemGuid = itemGuid;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemPath() {
        return itemPath;
    }

    public void setItemPath(String itemPath) {
        this.itemPath = itemPath;
    }

    public Integer getItemType() {
        return itemType;
    }

    public void setItemType(Integer itemType) {
        this.itemType = itemType;
    }

    public String getItemPassword() {
        return itemPassword;
    }

    public void setItemPassword(String itemPassword) {
        this.itemPassword = itemPassword;
    }

    public Date getItemCreateDate() {
        return itemCreateDate;
    }

    public void setItemCreateDate(Date itemCreateDate) {
        this.itemCreateDate = itemCreateDate;
    }

    public Date getItemLastModifiedDate() {
        return itemLastModifiedDate;
    }

    public void setItemLastModifiedDate(Date itemLastModifiedDate) {
        this.itemLastModifiedDate = itemLastModifiedDate;
    }

    public String getItemAnnotation() {
        return itemAnnotation;
    }

    public void setItemAnnotation(String itemAnnotation) {
        this.itemAnnotation = itemAnnotation;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Transient
    public String getItemContent() {
        return itemContent;
    }

    public void setItemContent(String itemContent) {
        this.itemContent = itemContent;
    }

}
