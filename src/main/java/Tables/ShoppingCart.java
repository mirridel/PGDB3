package Tables;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ShoppingCart {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("order")
    @Expose
    private Orders order;
    @SerializedName("product")
    @Expose
    private Products product;
    @SerializedName("amount")
    @Expose
    private Integer amount;
    public ShoppingCart(){
        id = -1;
        order = null;
        product = null;
        amount = 0;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setOrder(Orders order) {
        this.order = order;
    }

    public Orders getOrder() {
        return order;
    }

    public void setProduct(Products product) {
        this.product = product;
    }

    public Products getProduct() {
        return product;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "Shopping cart{" +
                "order # " + order.getId() +
                ", product =" + product +
                ", amount =" + amount +
                "\n}";
    }
}
