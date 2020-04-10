package com.linglingyi.com.model;

import com.linglingyi.com.utils.StringUtil;

import java.io.Serializable;
import java.util.List;

/**
 * @author: lilingfei
 * @description:
 * @date: 2019/8/22
 */
public class ItemModel implements Serializable {

    /**
     * freight : 0
     * id : 103F37721C1640E7835F76FD43FF0DBC
     * image : http://heitu.llyzf.cn:80/image/goods_image/50719650CCD84C6FAAAAC76893C06223.jpg?0.4983791994155038?1564392188113
     * name : S-Midea/美的 加热破壁机全自动搅拌家用多功能料理机 MJ-BL1024A
     * point : 798
     * price : 798
     * specifications : ["产品尺寸432*246*315mm"]
     * type : 2
     */

    private double freight;
    private String id;
    private String image;
    private String name;
    private double point;
    private double price;
    private String type;
    private List<String> specifications;
    private int inventory;
    private String size;
    private int goodsCount;
    private String detailImage;

    public String getDetailImage() {
        return detailImage == null ? "" : detailImage;
    }

    public void setDetailImage(String detailImage) {
        this.detailImage = detailImage;
    }

    public int getGoodsCount() {
        return goodsCount;
    }

    public void setGoodsCount(int goodsCount) {
        this.goodsCount = goodsCount;
    }

    public String getSize() {
        return size == null ? "" : size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getInventory() {
        return inventory;
    }

    public void setInventory(int inventory) {
        this.inventory = inventory;
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

    public double getPoint() {
        return point;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setPoint(double point) {
        this.point = point;
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
