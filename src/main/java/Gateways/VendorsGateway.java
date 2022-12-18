package Gateways;

import Tables.Vendors;

import java.net.URL;
import java.util.List;

public class VendorsGateway extends AbstractGateway<Vendors> {

    public List getByName(String name){
        List list = null;
        try {
            URL url = new URL ("http://localhost:5000/rest/vendors/get_by_name/" + name);
            StringBuilder response = searchManager(url);
            list = stringToArray(response.toString(), getSH());

        } catch (Exception e) { e.printStackTrace();}
        return list;
    };

    public List searchByName(String name) {
        List list = null;
        try {
            URL url = new URL ("http://localhost:5000/rest/vendors/search_by_name/" + name);
            StringBuilder response = searchManager(url);
            list = stringToArray(response.toString(), getSH());

        } catch (Exception e) { }
        return list;
    }

    @Override
    protected Class<Vendors> getType() {
        return Vendors.class;
    }

    @Override
    protected String getTableName() {
        return "vendors";
    }

    @Override
    protected Class<Vendors[]> getSH() {
        return Vendors[].class;
    }
}
