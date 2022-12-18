package Tables;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.persistence.Column;
import javax.persistence.Id;

public class Categories {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;

    public Categories() {
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
        return "Category{" +
                "name=" + name +
                '}';
    }

    public void parseJson(String data) {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        Object obj = gson.fromJson(data, this.getClass());
        System.out.println(obj.toString());
    }
}
