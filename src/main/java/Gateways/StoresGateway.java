package Gateways;

import Tables.Stores;

import java.net.URL;
import java.util.List;

public class StoresGateway extends AbstractGateway<Stores> {

    public List getByEmail(String email){
        List list = null;
        try {
            URL url = new URL ("http://localhost:5000/rest/stores/get_by_email/" + email);
            StringBuilder response = searchManager(url);
            list = stringToArray(response.toString(), getSH());

        } catch (Exception e) { }
        return list;
    };

    public List searchByEmail(String email) {
        List list = null;
        try {
            URL url = new URL ("http://localhost:5000/rest/stores/search_by_email/" + email);
            StringBuilder response = searchManager(url);
            list = stringToArray(response.toString(), getSH());

        } catch (Exception e) { }
        return list;
    }

    public List getByPaidDelivery(Boolean paidDelivery) {

        List list = null;
        try {
            URL url = new URL ("http://localhost:5000/rest/stores/search_by_paid_delivery/" + paidDelivery);
            StringBuilder response = searchManager(url);
            list = stringToArray(response.toString(), getSH());

        } catch (Exception e) { }
        return list;
    }

    @Override
    protected Class<Stores> getType() {
        return Stores.class;
    }

    @Override
    protected String getTableName() {
        return "stores";
    }

    @Override
    protected Class<Stores[]> getSH() {
        return Stores[].class;
    }
}
