package vn.edu.ptithcm.mytrip006.model;

/**
 * Created by Nguyen on 24/02/2018.
 */

public class Goods {
    private String id, name, origin, seller, promotionPolicy,  brand, category, description, image, quantity;
    private int price, promotionalPricing, sold, favorites, quality;
    private String imageList, vote;
    private byte[] imageLogo;

    public Goods(){}

    public Goods(String id, String name, String origin, String seller, String promotionPolicy, int quality, String brand, String category, String description, String image, int price, int sold, String quantity, int favorites) {
        this.id = id;
        this.name = name;
        this.origin = origin;
        this.seller = seller;
        this.promotionPolicy = promotionPolicy;
        this.quality = quality;
        this.brand = brand;
        this.category = category;
        this.description = description;
        this.image = image;
        this.price = price;
        this.sold = sold;
        this.quantity = quantity;
        this.favorites = favorites;
        this.imageList = imageList;
        this.vote = vote;
    }

    public Goods(String id, String name, int price, int promotionalPricing, int sold, String image) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.promotionalPricing = promotionalPricing;
        this.sold = sold;
        this.image = image;
    }

    public Goods(String id, String name, int price, int promotionalPricing, int sold, byte[] imageLogo) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.promotionalPricing = promotionalPricing;
        this.sold = sold;
        this.imageLogo = imageLogo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public String getPromotionPolicy() {
        return promotionPolicy;
    }

    public void setPromotionPolicy(String promotionPolicy) {
        this.promotionPolicy = promotionPolicy;
    }

    public int getQuality() {
        return quality;
    }

    public void setQuality(int quality) {
        this.quality = quality;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPromotionalPricing() {
        return promotionalPricing;
    }

    public void setPromotionalPricing(int promotionalPricing) {
        this.promotionalPricing = promotionalPricing;
    }

    public int getSold() {
        return sold;
    }

    public void setSold(int sold) {
        this.sold = sold;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public int getFavorites() {
        return favorites;
    }

    public void setFavorites(int favorites) {
        this.favorites = favorites;
    }

    public String getImageList() {
        return imageList;
    }

    public void setImageList(String imageList) {
        this.imageList = imageList;
    }

    public String getVote() {
        return vote;
    }

    public void setVote(String vote) {
        this.vote = vote;
    }

    public byte[] getImageLogo() {
        return imageLogo;
    }

    public void setImageLogo(byte[] imageLogo) {
        this.imageLogo = imageLogo;
    }
}
