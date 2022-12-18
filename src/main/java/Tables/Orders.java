package Tables;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.HashSet;
import java.util.Set;

public class Orders {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("client")
    @Expose
    private Clients client;
    @SerializedName("store")
    @Expose
    private Stores store;
    @SerializedName("order_date")
    @Expose
    private String orderDate;
    @SerializedName("order_time")
    @Expose
    private String orderTime;
    @SerializedName("confirmation")
    @Expose
    private Boolean confirmation;
    @Expose(serialize = false, deserialize = false)
    private Set<ShoppingCart> shoppingCart = new HashSet<ShoppingCart>(0);

    public Orders(){
        id = -1;
        client = null;
        store = null;
        orderDate = "";
        orderTime = "";
        confirmation = false;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Clients getClient() {
        return client;
    }

    public void setClient(Clients client) {
        this.client = client;
    }

    public Stores getStore() {
        return store;
    }

    public void setStore(Stores store) {
        this.store = store;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public Boolean getConfirmation() {
        return confirmation;
    }

    public void setConfirmation(Boolean confirmation) {
        this.confirmation = confirmation;
    }

    public void setShoppingCart(Set<ShoppingCart> products) {
        this.shoppingCart = products;
    }
    public Set<ShoppingCart> getShoppingCart() {
        return shoppingCart;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "ORDER # " + id +
                ",\nclient_firstname=" + client.getFirstName() +
                ",\n client_lastname=" + client.getLastName() +
                ",\n store_email=" + store.getEmail() +
                ",\n order_date=" + orderDate +
                ",\n order_time=" + orderTime +
                ",\n confirmation=" + confirmation +
                ",\n products=" + shoppingCart +
                '}';
    }
}
