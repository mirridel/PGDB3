package Tables;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.HashSet;
import java.util.Set;

public class Products {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("category")
    @Expose
    private Categories category;
    @SerializedName("vendor")
    @Expose
    private Vendors vendor;
    @SerializedName("model")
    @Expose
    private String model;
    @SerializedName("specs")
    @Expose
    private String specs;
    @SerializedName("price")
    @Expose
    private Integer price;
    @SerializedName("warranty_period")
    @Expose
    private Integer warrantyPeriod;
    @SerializedName("image")
    @Expose
    private String image;
    @Expose(serialize = false, deserialize = false)
    private Set<ShoppingCart> shoppingCarts = new HashSet<ShoppingCart>(0);

    public Products(){
        setId(-1);
        category = null;
        vendor = null;
        model = "";
        specs = "";
        price = 0;
        warrantyPeriod = 0;
        image = "";
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setCategory(Categories category) {
        this.category = category;
    }

    public Categories getCategory() {
        return category;
    }

    public void setVendor(Vendors vendor) {
        this.vendor = vendor;
    }

    public Vendors getVendor() {
        return vendor;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getModel() {
        return model;
    }

    public void setSpecs(String specs) {
        this.specs = specs;
    }

    public String getSpecs() {
        return specs;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getPrice() {
        return price;
    }

    public void setWarrantyPeriod(Integer warrantyPeriod) {
        this.warrantyPeriod = warrantyPeriod;
    }

    public Integer getWarrantyPeriod() {
        return warrantyPeriod;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setShoppingCarts(Set<ShoppingCart> shoppingCarts) {
        this.shoppingCarts = shoppingCarts;
    }

    public Set<ShoppingCart> getShoppingCarts() {
        return shoppingCarts;
    }

    public String toString() {
        return "Product{" +
                "category=" + category +
                ", vendor=" + vendor +
                ", model=" + model +
                ", specs=" + specs +
                ", price=" + price +
                ", warranty=" + warrantyPeriod +
                '}';
    }
}
