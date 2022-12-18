package Gateways;

import Tables.Clients;
import Tables.Orders;
import Tables.ShoppingCart;
import Tables.Stores;

import java.net.URL;
import java.util.List;

public class OrdersGateway extends AbstractGateway<Orders> {

    public List updateProducts(List<Orders> list){
        ShoppingCartGateway sc = new ShoppingCartGateway();
        list.forEach(e->{
            List res = sc.searchByOrder(e);
            var something = e.getShoppingCart();
            res.forEach(el->{
                something.add((ShoppingCart) el);
            });
        });
        return list;
    }

    public List searchByClient(Clients client) {
        List list = null;
        try {
            int id = client.getId();
            URL url = new URL ("http://localhost:5000/rest/orders/search_by_client_id/" + id);
            StringBuilder response = searchManager(url);
            list = stringToArray(response.toString(), getSH());

        } catch (Exception e) { }
        return list;
    }

    public List searchByStore(Stores store) {
        List list = null;
        try {
            int id = store.getId();
            URL url = new URL ("http://localhost:5000/rest/orders/search_by_store_id/" + id);
            StringBuilder response = searchManager(url);
            list = stringToArray(response.toString(), getSH());

        } catch (Exception e) { }
        return list;
    }

    public List searchByDate(String date) {
        List list = null;
        try {
            URL url = new URL ("http://localhost:5000/rest/orders/search_by_date/" + date);
            System.out.println(url);
            StringBuilder response = searchManager(url);
            list = stringToArray(response.toString(), getSH());

        } catch (Exception e) { }
        return list;
    }

    public List searchByTime(String left, String right) {
        List list = null;
        try {
            URL url = new URL ("http://localhost:5000/rest/orders/search_by_time/" + left.toString() + "/" + right.toString());
            System.out.println(url);
            StringBuilder response = searchManager(url);
            list = stringToArray(response.toString(), getSH());

        } catch (Exception e) { }
        return list;
    }

    public List getByConfirmation(Boolean confirmation) {
        return null;
    }

    @Override
    protected Class<Orders> getType() {
        return Orders.class;
    }

    @Override
    protected String getTableName() {
        return "orders";
    }

    @Override
    protected Class<Orders[]> getSH() {
        return Orders[].class;
    }
}
