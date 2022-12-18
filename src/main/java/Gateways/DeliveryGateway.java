package Gateways;

import Tables.Couriers;
import Tables.Delivery;
import Tables.Orders;

import java.net.URL;
import java.util.List;

public class DeliveryGateway extends AbstractGateway<Delivery> {

    public List getByOrder(Orders order) {
        List list = null;
        try {
            int id = order.getId();
            URL url = new URL ("http://localhost:5000/rest/delivery/search_by_order_id/" + id);
            StringBuilder response = searchManager(url);
            list = stringToArray(response.toString(), getSH());

        } catch (Exception e) { }
        return list;
    }

    public List getByCourier(Couriers courier) {
        List list = null;
        try {
            int id = courier.getId();
            URL url = new URL ("http://localhost:5000/rest/delivery/search_by_courier_id/" + id);
            StringBuilder response = searchManager(url);
            list = stringToArray(response.toString(), getSH());

        } catch (Exception e) { }
        return list;
    }

    public List searchByDeliveryDate(String date) {
        List list = null;
        try {
            URL url = new URL ("http://localhost:5000/rest/delivery/search_by_date/" + date);
            System.out.println(url);
            StringBuilder response = searchManager(url);
            list = stringToArray(response.toString(), getSH());

        } catch (Exception e) { }
        return list;
    }

    public List searchByDeliveryTime(String left, String right) {
        List list = null;
        try {
            URL url = new URL ("http://localhost:5000/rest/delivery/search_by_time/" + left.toString() + "/" + right.toString());
            System.out.println(url);
            StringBuilder response = searchManager(url);
            list = stringToArray(response.toString(), getSH());

        } catch (Exception e) { }
        return list;
    }

    public List searchByDeliveryAddress(String address) {
        List list = null;
        try {
            URL url = new URL ("http://localhost:5000/rest/delivery/search_by_delivery_address/" + address);
            StringBuilder response = searchManager(url);
            list = stringToArray(response.toString(), getSH());

        } catch (Exception e) { }
        return list;
    }

    @Override
    protected Class<Delivery> getType() {
        return Delivery.class;
    }

    @Override
    protected String getTableName() {
        return "delivery";
    }

    @Override
    protected Class<Delivery[]> getSH() {
        return Delivery[].class;
    }
}
