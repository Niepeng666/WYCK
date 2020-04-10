package com.linglingyi.com.model;

import java.io.Serializable;
import java.util.List;

/**
 * @作者 chenlanxin
 * @创建日期 2019/4/12 14:45
 * @功能
 **/
public class GoodsModel implements Serializable {

    /**
     * freight : 6
     * id : 11
     * image : http://120.24.154.88/image/shop_detail_1.jpg
     * name : 测试11
     * point : 1000
     * price : 0
     * specifications : ["A","C","D","S","D","FS","DSD","SDAS"]
     * type : 1
     */

    private double freight;
    private String id;
    private String image;
    private String name;
    private int point;
    private double price;
    private String type;
    private List<String> specifications;
    private String size;
    private String image1;

    public String getImage1() {
        return image1;
    }

    public void setImage1(String image1) {
        this.image1 = image1;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public double getFreight() {
        return freight;
    }

    public void setFreight(double freight) {
        this.freight = freight;
    }

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

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<String> getSpecifications() {
        return specifications;
    }

    public void setSpecifications(List<String> specifications) {
        this.specifications = specifications;
    }
}
