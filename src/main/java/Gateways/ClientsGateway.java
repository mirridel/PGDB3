package Gateways;

import Tables.Clients;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class ClientsGateway extends AbstractGateway<Clients> {


    public List searchByFirstname(String firstName) {
        List list = null;
        try {
            URL url = new URL ("http://localhost:5000/rest/clients/search_by_firstname/" + firstName);
            StringBuilder response = searchManager(url);
            list = stringToArray(response.toString(), getSH());

        } catch (Exception e) { }
        return list;
    }

    public List searchByLastname(String lastName) {
        List list = null;
        try {
            URL url = new URL ("http://localhost:5000/rest/clients/search_by_lastname/" + lastName);
            StringBuilder response = searchManager(url);
            list = stringToArray(response.toString(), getSH());

        } catch (Exception e) { }
        return list;
    }

    public List searchByEmail(String email) {
        List list = null;
        try {
            URL url = new URL ("http://localhost:5000/rest/clients/search_by_email/" + email);
            StringBuilder response = searchManager(url);
            list = stringToArray(response.toString(), getSH());

        } catch (Exception e) { }
        return list;
    }

    public List searchByContactNumber(String contactNumber) {
        List list = null;
        try {
            URL url = new URL ("http://localhost:5000/rest/clients/search_by_contact_number/" + contactNumber);
            StringBuilder response = searchManager(url);
            list = stringToArray(response.toString(), getSH());

        } catch (Exception e) { }
        return list;
    }

    @Override
    protected Class<Clients> getType() {
        return Clients.class;
    }
    @Override
    protected String getTableName() {
        return "clients";
    }
    @Override
    protected Class<Clients[]> getSH() {
        return Clients[].class;
    }
}
