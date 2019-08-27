package com.tspark;

public class Model {
    private String rname;
    private String rdescription;
    private String brand;
   // private int modelId;
   private String modelId;
    private String productCost;
    private String shippingCost;
   // private int serviceCharge;
  //  private int categoryId;
    private String serviceCharge;
    private String categoryId;
    private String imageUrl;
    //private int galleryId;
   // private int categoryCode;
    private String galleryId;
    private String categoryCode;

    public Model(String rname, String rdescription, String brand, String modelId, String productCost, String shippingCost,
                 String serviceCharge, String categoryId, String imageUrl, String galleryId, String categoryCode) {
        this.rname = rname;
        this.rdescription = rdescription;
        this.brand = brand;
        this.modelId = modelId;
        this.productCost = productCost;
        this.shippingCost = shippingCost;
        this.serviceCharge = serviceCharge;
        this.categoryId = categoryId;
        this.imageUrl = imageUrl;
        this.galleryId = galleryId;
        this.categoryCode = categoryCode;
    }

    @Override
    public String toString() {
        return "{" +
                "rname='" + rname  +
                ", rdescription='" + rdescription  +
                ", brand='" + brand  +
                ", modelId='" + modelId  +
                ", productCost='" + productCost  +
                ", shippingCost='" + shippingCost  +
                ", serviceCharge='" + serviceCharge  +
                ", categoryId='" + categoryId  +
                ", imageUrl='" + imageUrl  +
                ", galleryId='" + galleryId  +
                ", categoryCode='" + categoryCode  +
                '}';
    }

    public String getRname() {
        return rname;
    }

    public void setRname(String rname) {
        this.rname = rname;
    }

    public String getRdescription() {
        return rdescription;
    }

    public void setRdescription(String rdescription) {
        this.rdescription = rdescription;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModelId() {
        return modelId;
    }

    public void setModelId(String modelId) {
        this.modelId = modelId;
    }

    public String getProductCost() {
        return productCost;
    }

    public void setProductCost(String productCost) {
        this.productCost = productCost;
    }

    public String getShippingCost() {
        return shippingCost;
    }

    public void setShippingCost(String shippingCost) {
        this.shippingCost = shippingCost;
    }

    public String getServiceCharge() {
        return serviceCharge;
    }

    public void setServiceCharge(String serviceCharge) {
        this.serviceCharge = serviceCharge;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getGalleryId() {
        return galleryId;
    }

    public void setGalleryId(String galleryId) {
        this.galleryId = galleryId;
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }
}
