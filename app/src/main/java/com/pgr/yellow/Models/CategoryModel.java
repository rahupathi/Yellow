package com.pgr.yellow.Models;

import java.io.Serializable;

/**
 * Created by BMS0020 on 6/24/2015.
 */
public class CategoryModel {

    private String categoryName;
    private  int categoryCount;
    private String objectId;
    public CategoryModel(String _CategoryName, int _CategoryCount,String _objectId){
        categoryCount=_CategoryCount;
        categoryName=_CategoryName;
        objectId=_objectId;
    }
    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String _objectId) {
        this.objectId = _objectId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getCompanyCount() {
        return categoryCount;
    }

    public void setCompanyCount(int categoryCount) {
        this.categoryCount = categoryCount;
    }



}
