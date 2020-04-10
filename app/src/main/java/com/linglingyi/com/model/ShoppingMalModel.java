package com.linglingyi.com.model;

import java.io.Serializable;

/**
 * @author: lilingfei
 * @description:
 * @date: 2019/8/20
 */
public class ShoppingMalModel implements Serializable {
    /**
     * id : F1B007E583AB4EA3BB2571C5FE140851
     * image : http://47.92.226.83:80/image/goods_image/550D17868B21461D9D8CE0D1D5A5117C.png?0.12784285408812912
     * name : 最新口子
     * price : 1000
     * stock : 40
     */

    private String id;
    private String image;
    private String name;
    private String price;
    private String stock;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

}
