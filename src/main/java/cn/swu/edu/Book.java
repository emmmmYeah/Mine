package cn.swu.edu;

public class Book {//网页上传元素，如果是电影：

    private int id;
    private String name;
    private String author;
    private int price;
    private String type;
    private String describe;
    private String pics;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getDescribe() {
        return describe;
    }
    public void setDescribe(String describe) {
        this.describe = describe;
    }
    public String getPics(){return this.pics;}
    public void setPics(String pics) {this.pics = pics;}

}
