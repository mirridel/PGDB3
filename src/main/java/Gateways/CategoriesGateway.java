package Gateways;

import Tables.Categories;
import java.net.URL;
import java.util.List;

public class CategoriesGateway extends AbstractGateway<Categories> {

    public List getByName(String name){
        List list = null;
        try {
            URL url = new URL ("http://localhost:5000/rest/categories/get_by_name/" + name);
            StringBuilder response = searchManager(url);
            list = stringToArray(response.toString(), getSH());

        } catch (Exception e) { }
        return list;
    };

    public List searchByName(String name) {
        List list = null;
        try {
            URL url = new URL ("http://localhost:5000/rest/categories/search_by_name/" + name);
            StringBuilder response = searchManager(url);
            list = stringToArray(response.toString(), getSH());

        } catch (Exception e) { }
        return list;
    }

    @Override
    protected Class<Categories> getType() {
        return Categories.class;
    }

    @Override
    protected String getTableName() {
        return "categories";
    }

    @Override
    protected Class<Categories[]> getSH() {
        return Categories[].class;
    }
}
