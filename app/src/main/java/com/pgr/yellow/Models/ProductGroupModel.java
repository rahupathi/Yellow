package com.pgr.yellow.Models;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by BMS0020 on 6/17/2015.
 */
public class ProductGroupModel implements Serializable {
    private String Category;
    private int ProviderCount;
    private ArrayList<ProductModel> ProductList = new ArrayList<ProductModel>();

    public ProductGroupModel(String mCategoryName, ArrayList<ProductModel> mProductList) {
        super();
        this.Category = mCategoryName;
        this.ProductList = mProductList;
        this.ProviderCount = mProductList.size();
    }

    public String getName() {
        return Category;
    }

    public void setLineName(String name) {
        this.Category = name;
    }

    public ArrayList<ProductModel> getProductList() {
        return ProductList;
    }

    public void setProviderList(ArrayList<ProductModel> _providerList) {
        this.ProductList = _providerList;
    };
}
