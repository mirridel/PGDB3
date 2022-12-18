package Tables;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Vendors {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;

    public Vendors(){
        id = -1;
        name = "";
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Vendor{" +
                "name=" + name +
                '}';
    }
}
