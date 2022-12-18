package Gateways;

import Tables.Couriers;

import java.net.URL;
import java.util.List;

public class CouriersGateway extends AbstractGateway<Couriers> {

    public List searchByFirstname(String firstName) {
        List list = null;
        try {
            URL url = new URL ("http://localhost:5000/rest/couriers/search_by_firstname/" + firstName);
            StringBuilder response = searchManager(url);
            list = stringToArray(response.toString(), getSH());

        } catch (Exception e) { }
        return list;
    }

    public List searchByLastname(String lastName) {
        List list = null;
        try {
            URL url = new URL ("http://localhost:5000/rest/couriers/search_by_lastname/" + lastName);
            StringBuilder response = searchManager(url);
            list = stringToArray(response.toString(), getSH());

        } catch (Exception e) { }
        return list;
    }

    public List searchByEmail(String email) {
        List list = null;
        try {
            URL url = new URL ("http://localhost:5000/rest/couriers/search_by_email/" + email);
            StringBuilder response = searchManager(url);
            list = stringToArray(response.toString(), getSH());

        } catch (Exception e) { }
        return list;
    }

    public List searchByContactNumber(String contactNumber) {
        List list = null;
        try {
            URL url = new URL ("http://localhost:5000/rest/couriers/search_by_contact_number/" + contactNumber);
            StringBuilder response = searchManager(url);
            list = stringToArray(response.toString(), getSH());

        } catch (Exception e) { }
        return list;
    }

    @Override
    protected Class<Couriers> getType() {
        return Couriers.class;
    }

    @Override
    protected String getTableName() {
        return "couriers";
    }

    @Override
    protected Class<Couriers[]> getSH() {
        return Couriers[].class;
    }
}
