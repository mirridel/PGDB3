package Gateways;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.hibernate.HibernateException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

public abstract class AbstractGateway<T> {

    public static <T> List<T> stringToArray(String s, Class<T[]> class_) {
        try {
            T[] arr = new Gson().fromJson(s, class_);
            return Arrays.asList(arr);
        }
        catch (Exception ignored){ }
        return null;
    }

    public T update(T data) {
        T result = null;
        try {
            GsonBuilder builder = new GsonBuilder().excludeFieldsWithoutExposeAnnotation();
            Gson gson = builder.create();
            String json = gson.toJson(data);
            //System.out.println(data);
            String jsonString = "{\"table_name\": \"" + getTableName() + "\", \"obj\": "+ json +"}";
            //System.out.println(jsonString);
            URL url = new URL ("http://localhost:5000/rest/update");
            StringBuilder response = postSearchManager(url, jsonString);

            int pos = -1;
            try{
                pos = Integer.parseInt(response.toString());
            }
            catch (NumberFormatException ignored){ }
            try {
                if (pos != -1)
                    result = getById(pos);
            }
            catch (Exception ignored) { }
        } catch (Exception ignored) { }
        return result;
    }

    public T getById(int id){
        T result = null;
        String jsonString = "{\"table_name\": \"" + getTableName() + "\", \"id\": \"" + id + "\"}";
        try {
            Gson gson = new Gson();
            URL url = new URL ("http://localhost:5000/rest/get");
            StringBuilder response = postSearchManager(url, jsonString);
            result = gson.fromJson(response.toString(), getType());
        } catch (Exception ignored) { }
        return result;
    }

    public void delete(T data) {
        try {
            Gson gson = new Gson();
            String json = gson.toJson(data);
            String jsonString = "{\"table_name\": \"" + getTableName() + "\", \"obj\": "+ json +"}";
            URL url = new URL ("http://localhost:5000/rest/delete");
            StringBuilder response = postSearchManager(url, jsonString);
        } catch (Exception ignored) { }
    }

    public Long getCount() {

        Long result = null;
        try {
            URL url = new URL ("http://localhost:5000/rest/get_size/" + getTableName());
            StringBuilder response = searchManager(url);
            try{
                result = Long.parseLong(response.toString());
            }
            catch (NumberFormatException ex){ }
        } catch (Exception ex) { }
        return result;
    }

    public List getList(int start, int size) {

        List<T> list = null;
        String jsonString = "{\"table_name\": \"" + getTableName() + "\", \"offset_value\": \" " +
                start + " \", \"limit_value\": \" " + size + " \"}";
        try {
            URL url = new URL ("http://localhost:5000/rest/list");
            StringBuilder response = postSearchManager(url, jsonString);
            list = stringToArray(response.toString(), getSH());
        } catch (Exception ex) { }
        return list;
    }

    protected StringBuilder postSearchManager (URL url, String inputData) throws IOException {
        HttpURLConnection con = (HttpURLConnection)url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json; utf-8");
        con.setRequestProperty("Accept", "application/json");
        con.setDoOutput(true);

        try(OutputStream os = con.getOutputStream()) {
            byte[] input = inputData.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        StringBuilder response = new StringBuilder();;
        try(BufferedReader br = new BufferedReader(
                new InputStreamReader(con.getInputStream(), "utf-8"))) {
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
        }
        //System.out.println("Before " + response.toString());
        con.disconnect();
        //System.out.println("After " + response.toString());
        return response;
    }

    protected StringBuilder searchManager (URL url) throws IOException {
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
        //System.out.println("Before " + response.toString());
        con.disconnect();
        //System.out.println("After " + response.toString());
        return response;
    }

    protected abstract Class<T> getType();

    protected abstract String getTableName();

    protected abstract Class<T[]> getSH();
}
