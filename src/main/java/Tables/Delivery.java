package Tables;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Delivery {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("order")
    @Expose
    private Orders order;
    @SerializedName("courier")
    @Expose
    private Couriers courier;
    @SerializedName("delivery_date")
    @Expose
    private String deliveryDate;
    @SerializedName("delivery_time")
    @Expose
    private String deliveryTime;
    @SerializedName("delivery_address")
    @Expose
    private String deliveryAddress;

    public Delivery(){
        id = -1;
        order = null;
        courier = null;
        deliveryDate = "";
        deliveryTime = "";
        deliveryAddress = "";
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

    public void setCourier(Couriers courier) {
        this.courier = courier;
    }

    public Couriers getCourier() {
        return courier;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryTime(String deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public String getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    @Override
    public String toString() {
        return "Delivery{" +
                "order #" + order.getId() +
                ",\n client firstname=" + order.getClient().getFirstName() +
                ",\n client lastname=" + order.getClient().getLastName() +
                ",\n courier firstname=" + courier.getFirstName() +
                ",\n courier lastname=" + courier.getLastName() +
                ",\n courier phone=" + courier.getContactNumber() +
                ",\n delivery date=" + deliveryDate +
                ",\n delivery time=" + deliveryTime +
                ",\n address =" + deliveryAddress +
                '}';
    }
}
