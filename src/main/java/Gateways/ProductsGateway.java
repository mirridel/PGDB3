package Gateways;

import Tables.Categories;
import Tables.Products;
import Tables.Vendors;
import org.hibernate.HibernateException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.List;

public class ProductsGateway extends AbstractGateway<Products> {


    @Override
    public Products update(Products data) {

        return super.update(data);
    }

    public List searchByCategory(Categories category){

        List list = null;
        try {
            int id = category.getId();
            URL url = new URL ("http://localhost:5000/rest/products/search_by_category_id/" + id);
            StringBuilder response = searchManager(url);
            list = stringToArray(response.toString(), getSH());

        } catch (Exception e) { }
        return list;
    };

    public List searchByVendor(Vendors vendor){

        List list = null;
        try {
            int id = vendor.getId();
            URL url = new URL ("http://localhost:5000/rest/products/search_by_vendor_id/" + id);
            StringBuilder response = searchManager(url);
            list = stringToArray(response.toString(), getSH());

        } catch (Exception e) { }
        return list;
    };

    public List searchByModel(String model){
        List list = null;
        try {
            URL url = new URL ("http://localhost:5000/rest/products/search_by_model/" + model);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Content-Type", "application/json; utf-8");
            con.setRequestProperty("Accept", "application/json");
            con.setDoOutput(true);

            StringBuilder response = new StringBuilder();;
            try(BufferedReader br = new BufferedReader(
                    new InputStreamReader(con.getInputStream(), "utf-8"))) {
                String responseLine = null;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
            }
            list = stringToArray(response.toString(), getSH());

        } catch (HibernateException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            throw new RuntimeException(e);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (list.isEmpty())
            return null;
        else
            return list;
    };

    public List searchBySpecs(String specs){
        List list = null;
        try {
            URL url = new URL ("http://localhost:5000/rest/products/search_by_specs/" + specs);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Content-Type", "application/json; utf-8");
            con.setRequestProperty("Accept", "application/json");
            con.setDoOutput(true);

            StringBuilder response = new StringBuilder();;
            try(BufferedReader br = new BufferedReader(
                    new InputStreamReader(con.getInputStream(), "utf-8"))) {
                String responseLine = null;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
            }
            list = stringToArray(response.toString(), getSH());

        } catch (HibernateException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            throw new RuntimeException(e);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (list.isEmpty())
            return null;
        else
            return list;
    };

    @Override
    protected Class<Products> getType() {
        return Products.class;
    }

    @Override
    protected String getTableName() {
        return "products";
    }

    @Override
    protected Class<Products[]> getSH() {
        return Products[].class;
    }
}
