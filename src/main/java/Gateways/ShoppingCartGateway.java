package Gateways;

import Tables.Orders;
import Tables.Products;
import Tables.ShoppingCart;

import java.net.URL;
import java.util.List;

public class ShoppingCartGateway extends AbstractGateway<ShoppingCart> {

    public List getByOrderAndProduct(Orders order, Products product){
        List list = null;
        try {
            int order_id = order.getId();
            int product_id = product.getId();
            URL url = new URL ("http://localhost:5000/rest/shopping_cart/search_by_order_and_amount/" + order_id + "/" + product_id);
            StringBuilder response = searchManager(url);
            list = stringToArray(response.toString(), getSH());

        } catch (Exception e) { }
        return list;
    };

    public List searchByOrder(Orders order){
        List list = null;
        try {
            int id = order.getId();
            URL url = new URL ("http://localhost:5000/rest/shopping_cart/search_by_order_id/" + id);
            StringBuilder response = searchManager(url);
            list = stringToArray(response.toString(), getSH());

        } catch (Exception e) { }
        return list;
    };

    public List searchByProduct(Products product){
        List list = null;
        try {
            int id = product.getId();
            URL url = new URL ("http://localhost:5000/rest/shopping_cart/search_by_product_id/" + id);
            StringBuilder response = searchManager(url);
            list = stringToArray(response.toString(), getSH());

        } catch (Exception e) { }
        return list;
    };

    public List searchByAmount(Integer amount){
        List list = null;
        try {
            URL url = new URL ("http://localhost:5000/rest/shopping_cart/search_by_amount/" + amount);
            StringBuilder response = searchManager(url);
            list = stringToArray(response.toString(), getSH());

        } catch (Exception e) { }
        return list;
    };

    @Override
    protected Class<ShoppingCart> getType() {
        return ShoppingCart.class;
    }

    @Override
    protected String getTableName() {
        return "shopping_cart";
    }

    @Override
    protected Class<ShoppingCart[]> getSH() {
        return ShoppingCart[].class;
    }
}
