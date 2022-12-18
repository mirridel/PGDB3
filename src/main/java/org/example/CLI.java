package org.example;

import Gateways.*;
import Tables.*;

import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class CLI {
    StoresGateway storesGateway = new StoresGateway();
    VendorsGateway vendorsGateway = new VendorsGateway();
    CategoriesGateway categoriesGateway = new CategoriesGateway();
    ProductsGateway productsGateway = new ProductsGateway();
    ClientsGateway clientsGateway = new ClientsGateway();
    OrdersGateway ordersGateway = new OrdersGateway();
    CouriersGateway couriersGateway = new CouriersGateway();
    DeliveryGateway deliveryGateway = new DeliveryGateway();
    ShoppingCartGateway shoppingCartGateway = new ShoppingCartGateway();


    Stores storePointer = null;
    Clients clientPointer = null;
    Vendors vendorPointer = null;
    Categories categoriesPointer = null;
    Products productPointer = null;
    Orders orderPointer = null;
    Couriers courierPointer = null;
    Delivery deliveryPointer = null;
    ShoppingCart shoppingCartPointer = null;

    Scanner sc = new Scanner(System.in);
    Integer pageSize = 10;
    String regex_name = "^[A-Za-z0-9\s]*$";
    Pattern pattern_name = Pattern.compile(regex_name);
    String regex_email = "^[A-Za-z0-9+_.-]+@(.+)$";
    Pattern pattern_email = Pattern.compile(regex_email);
    String regex_date = "^(20[0-9][0-9]-[01][0-9]-[0-3][0-9])?$";
    Pattern pattern_date = Pattern.compile(regex_date);
    String regex_time = "^([0-2][0-4]:[0-5][0-9]:[0-5][0-9])$";
    Pattern pattern_time = Pattern.compile(regex_time);

    DateFormat timeFormat = new SimpleDateFormat("hh:mm:ss");

    public void begin() throws IOException {
        createCLI();
    }

    private void createCLI() throws IOException{

        boolean exitTrigger = true;
        Integer number = null;

        while (exitTrigger) {
            System.out.println("Menu:");
            System.out.println("1. Stores");
            System.out.println("2. Categories");
            System.out.println("3. Vendors");
            System.out.println("4. Products");
            System.out.println("5. Clients");
            System.out.println("6. Orders");
            System.out.println("7. Couriers");
            System.out.println("8. Delivery");
            System.out.println("9. Shopping cart");
            System.out.println("0. Exit");
            System.out.println("Enter the item:");

            while (!sc.hasNextInt()) {
                System.out.println("Invalid input!");
                sc.next();
            }
            number = sc.nextInt();

            switch (number) {
                case 1 -> {
                    beginStoresMenu();
                }
                case 2 -> {
                    beginCategoriesMenu();
                }
                case 3 -> {
                    beginVendorsMenu();
                }
                case 4 -> {
                    beginProductsMenu();
                }
                case 5 -> {
                    beginClientsMenu();
                }
                case 6 -> {
                    beginOrdersMenu();
                }
                case 7 -> {
                    beginCouriersMenu();
                }
                case 8 -> {
                    beginDeliveryMenu();
                }
                case 9 -> {
                    beginShoppingCartMenu();
                }
                case 0 -> {
                    exitTrigger = false;
                }
            }
        }
    }
    private List loadPage(AbstractGateway gateway) {

        List results = null;
        Integer number = null;
        Integer count = (int) (long) gateway.getCount();
        Integer countPages = (int) Math.ceil((float) count / (float) pageSize);
        Integer selectedPage = 1;

        System.out.println("Total number of records: " + count);
        System.out.println("Number of pages: " + countPages);
        if (count > 0) {
            Boolean localExitTrigger = true;
            while (localExitTrigger) {
                results = gateway.getList((selectedPage - 1) * pageSize, pageSize);
                int localID = 0;
                for (Object element : results) {
                    System.out.println(localID + ") " + element);
                    localID++;
                }
                System.out.print("Pages: ");
                for (int i = 1; i <= countPages; i++) {
                    if (i == selectedPage)
                        System.out.print(" (" + i + ") ");
                    else
                        System.out.print(" " + i + " ");
                }
                System.out.println();
                System.out.println("1. Previous page");
                System.out.println("2. Next page");
                System.out.println("3. Enter page");
                System.out.println("0. Exit");

                while (!sc.hasNextInt()) {
                    System.out.println("Invalid input!");
                    sc.next();
                }
                number = sc.nextInt();

                switch (number) {
                    case 0: {
                        localExitTrigger = false;
                    }
                    case 1: {
                        if (selectedPage > 1) {
                            selectedPage--;
                        }
                        break;
                    }
                    case 2: {
                        if (selectedPage < countPages) {
                            selectedPage++;
                        }
                        break;
                    }
                    case 3: {
                        while (!sc.hasNextInt()) {
                            System.out.println("Invalid input!");
                            sc.next();
                        }
                        selectedPage = sc.nextInt();
                        if (selectedPage > countPages)
                            selectedPage = countPages;
                        else if (selectedPage < 1)
                            selectedPage = 1;
                        break;
                    }
                }
            }
        }
        return results;
    }

    private List loadPageForOrders(OrdersGateway gateway) {

        List results = null;
        Integer number = null;
        Integer count = (int) (long) gateway.getCount();
        Integer countPages = (int) Math.ceil((float) count / (float) pageSize);
        Integer selectedPage = 1;

        System.out.println("Total number of records: " + count);
        System.out.println("Number of pages: " + countPages);
        if (count > 0) {
            Boolean localExitTrigger = true;
            while (localExitTrigger) {
                results = gateway.getList((selectedPage - 1) * pageSize, pageSize);
                results = gateway.updateProducts(results);
                int localID = 0;
                for (Object element : results) {
                    System.out.println(localID + ") " + element);
                    localID++;
                }
                System.out.print("Pages: ");
                for (int i = 1; i <= countPages; i++) {
                    if (i == selectedPage)
                        System.out.print(" (" + i + ") ");
                    else
                        System.out.print(" " + i + " ");
                }
                System.out.println();
                System.out.println("1. Previous page");
                System.out.println("2. Next page");
                System.out.println("3. Enter page");
                System.out.println("0. Exit");

                while (!sc.hasNextInt()) {
                    System.out.println("Invalid input!");
                    sc.next();
                }
                number = sc.nextInt();

                switch (number) {
                    case 0: {
                        localExitTrigger = false;
                    }
                    case 1: {
                        if (selectedPage > 1) {
                            selectedPage--;
                        }
                        break;
                    }
                    case 2: {
                        if (selectedPage < countPages) {
                            selectedPage++;
                        }
                        break;
                    }
                    case 3: {
                        while (!sc.hasNextInt()) {
                            System.out.println("Invalid input!");
                            sc.next();
                        }
                        selectedPage = sc.nextInt();
                        if (selectedPage > countPages)
                            selectedPage = countPages;
                        else if (selectedPage < 1)
                            selectedPage = 1;
                        break;
                    }
                }
            }
        }
        return results;
    }

    private void printLoadedData(List list){
        if (list != null) {
            int localID = 0;
            for (Object element : list) {
                System.out.println(localID + ") " + element);
                localID++;
            }
        } else {
            System.out.println("Empty!");
        }
    }

    private void beginStoresMenu() {
        boolean exitTrigger = true;
        Integer number = null;
        List results = null;

        while (exitTrigger) {
            System.out.println("Stores:");
            if (storePointer != null)
                System.out.println("Select: " + storePointer.toString());
            System.out.println("1. Load page");
            System.out.println("2. Print loaded data");
            System.out.println("3. Create");
            System.out.println("4. Get");
            System.out.println("5. Edit");
            System.out.println("6. Delete");
            System.out.println("7. Search");
            System.out.println("0. Exit");

            while (!sc.hasNextInt()) {
                System.out.println("Invalid input!");
                sc.next();
            }
            number = sc.nextInt();

            switch (number) {
                // LOAD STORES
                case 1: {
                    results = loadPage(storesGateway);
                    break;
                }
                // PRINT LOADED STORES
                case 2: {
                    printLoadedData(results);
                    break;
                }
                // CREATE STORE
                case 3: {
                    Stores store = new Stores();
                    // SET EMAIL
                    Scanner scanner_email = new Scanner(System.in);
                    String email = null;
                    while(true){
                        System.out.println("Enter store email:");
                        email = scanner_email.nextLine();
                        if (storesGateway.getByEmail(email) != null)
                            System.out.println("This email is already taken!");
                        else if (pattern_email.matcher(email).matches())
                            break;
                        System.out.println("Invalid input!");
                    }
                    if (!email.isEmpty()) {
                        store.setEmail(email);
                    }
                    // SET PAID DELIVERY
                    Scanner scanner_delivery = new Scanner(System.in);
                    Boolean paidDelivery = null;
                    System.out.println("Set paid delivery?");
                    System.out.println("true -> yes, false -> no");
                    while (!scanner_delivery.hasNextBoolean()) {
                        System.out.println("Invalid input!");
                        System.out.println("true -> yes, false -> no");
                        scanner_delivery.next();
                    }
                    paidDelivery = scanner_delivery.nextBoolean();
                    if (paidDelivery)
                        store.setPaidDelivery(true);
                    else
                        store.setPaidDelivery(false);
                    // CREATE
                    storePointer = storesGateway.update(store);
                    break;
                }
                // GET STORE
                case 4: {
                    if (results != null) {
                        System.out.println("Input index:");
                        while (!sc.hasNextInt()) {
                            System.out.println("Invalid input!");
                            sc.next();
                        }
                        number = sc.nextInt();
                        if (number < results.size() && number > -1)
                            storePointer = (Stores) results.get(number);
                    }
                    break;
                }
                // EDIT EMAIL
                case 5: {
                    if (storePointer != null) {
                        Scanner scanner = new Scanner(System.in);
                        // CHANGE EMAIL
                        String email = null;
                        while (true) {
                            System.out.println("Enter new store email:");
                            email = scanner.nextLine();
                            if (email.isEmpty()) {
                                break;
                            } else if (storesGateway.getByEmail(email) != null) {
                                System.out.println("This email is already taken!");
                            } else if (pattern_email.matcher(email).matches()) {
                                storePointer.setEmail(email);

                                break;
                            }
                        }
                        // CHANGE PAID DELIVERY
                        Boolean paidDelivery = null;
                        System.out.println("Set paid delivery?");
                        System.out.println("(true -> yes, false -> no)");
                        while (!scanner.hasNextBoolean()) {
                            System.out.println("Invalid input!");
                            System.out.println("(true -> yes, false -> no)");
                            scanner.next();
                        }
                        paidDelivery = scanner.nextBoolean();
                        if (paidDelivery)
                            storePointer.setPaidDelivery(true);
                        else
                            storePointer.setPaidDelivery(false);
                        // UPDATE
                        storePointer = storesGateway.update(storePointer);
                    }
                    break;
                }
                // DELETE STORE
                case 6: {
                    if (storePointer != null) {
                        storesGateway.delete(storePointer);
                        storePointer = null;
                    }
                    break;
                }
                // SEARCH STORE
                case 7: {
                    Boolean localExitTrigger = true;
                    while (localExitTrigger){
                        System.out.println("Store search:");
                        System.out.println("1. Get by email");
                        System.out.println("2. Search by email");
                        System.out.println("3. Get by delivery");
                        System.out.println("0. Exit");

                        while (!sc.hasNextInt()) {
                            System.out.println("Invalid input!");
                            sc.next();
                        }
                        number = sc.nextInt();
                        switch (number) {
                            case 1: {
                                Scanner scanner = new Scanner(System.in);
                                String email = null;
                                while(true){
                                    System.out.println("Enter store email:");
                                    email = scanner.nextLine();
                                    if(pattern_email.matcher(email).matches()){
                                        break;
                                    }
                                }
                                results = storesGateway.getByEmail(email);
                                if (results != null) {
                                    System.out.println(results.size() + " records were searched.");
                                }
                                break;
                            }
                            case 2: {
                                Scanner scanner = new Scanner(System.in);
                                System.out.println("Enter store email:");
                                String email = scanner.nextLine();
                                results = storesGateway.searchByEmail(email);
                                if (results != null) {
                                    System.out.println(results.size() + " records were searched.");
                                }
                                break;
                            }
                            case 3: {
                                Scanner scanner = new Scanner(System.in);
                                Boolean paidDelivery = null;
                                System.out.println("Paid delivery?");
                                System.out.println("(true -> yes, false -> no)");
                                while (!scanner.hasNextBoolean()) {
                                    System.out.println("Invalid input!");
                                    System.out.println("(true -> yes, false -> no)");
                                    scanner.next();
                                }
                                paidDelivery = scanner.nextBoolean();
                                results = storesGateway.getByPaidDelivery(paidDelivery);
                                if (results != null) {
                                    System.out.println(results.size() + " records were searched.");
                                }
                                break;
                            }
                            case 0: {
                                localExitTrigger = false;
                            }
                        }
                    }
                    break;
                }
                case 0: {
                    System.out.println("Exiting!");
                    exitTrigger = false;
                    break;
                }
            }
        }
    }
    private void beginCategoriesMenu() {
        boolean exitTrigger = true;
        Integer number = null;
        List results = null;

        while (exitTrigger) {
            System.out.println("Categories:");
            if (results != null)
                System.out.println("Data loaded: " + results.size() +  " records");
            if (categoriesPointer != null)
                System.out.println("Select: " + categoriesPointer.toString());
            System.out.println("1. Load page");
            System.out.println("2. Print loaded data");
            System.out.println("3. Create");
            System.out.println("4. Get");
            System.out.println("5. Edit");
            System.out.println("6. Delete");
            System.out.println("7. Search");
            System.out.println("0. Exit");

            while (!sc.hasNextInt()) {
                System.out.println("Invalid input!");
                sc.next();
            }
            number = sc.nextInt();

            switch (number) {
                // LOAD CATEGORIES
                case 1: {
                    results = loadPage(categoriesGateway);
                    break;
                }
                // PRINT LOADED CATEGORIES
                case 2: {
                    printLoadedData(results);
                    break;
                }
                // CREATE CATEGORY
                case 3: {
                    Scanner scanner = new Scanner(System.in);
                    Categories category = new Categories();

                    String name;
                    while(true){
                        System.out.println("Enter category name:");
                        name = scanner.nextLine();
                        if (categoriesGateway.getByName(name) != null){
                            System.out.println("This category already exists!");
                            name = null;
                            break;
                        }
                        else if (pattern_name.matcher(name).matches())
                            break;
                        else
                            System.out.println("Invalid input!");
                    }
                    if (name != null && !name.isEmpty()) {
                        category.setName(name);
                        categoriesPointer = categoriesGateway.update(category);
                    }
                    break;
                }
                // GET CATEGORY
                case 4: {
                    if (results != null) {
                        System.out.println("Input index:");
                        while (!sc.hasNextInt()) {
                            System.out.println("Invalid input!");
                            sc.next();
                        }
                        number = sc.nextInt();
                        if (number < results.size() && number > -1)
                            categoriesPointer = (Categories) results.get(number);
                    }
                    break;
                }
                // EDIT CATEGORY
                case 5:{
                    if (categoriesPointer != null) {
                        Scanner scanner = new Scanner(System.in);
                        String name = null;
                        while(true){
                            System.out.println("Enter new category name:");
                            name = scanner.nextLine();
                            if (name.isEmpty()){
                                break;
                            }
                            else if (categoriesGateway.getByName(name) != null){
                                System.out.println("This category already exists!");
                            }
                            else if(pattern_name.matcher(name).matches()){
                                categoriesPointer.setName(name);
                                categoriesPointer = categoriesGateway.update(categoriesPointer);
                                break;
                            }
                        }
                    }
                    break;
                }
                // DELETE CATEGORY
                case 6: {
                    if (categoriesPointer != null) {
                        categoriesGateway.delete(categoriesPointer);
                        categoriesPointer = null;
                    }
                    break;
                }
                // SEARCH CATEGORY
                case 7: {
                    Boolean localExitTrigger = true;
                    while (localExitTrigger){
                        System.out.println("Categories search:");
                        System.out.println("1. Get by name");
                        System.out.println("2. Search by name");
                        System.out.println("0. Exit");

                        while (!sc.hasNextInt()) {
                            System.out.println("Invalid input!");
                            sc.next();
                        }
                        number = sc.nextInt();

                        switch (number) {
                            case 1: {
                                Scanner scanner = new Scanner(System.in);
                                String name = null;
                                while(true){
                                    System.out.println("Enter category name:");
                                    name = scanner.nextLine();
                                    if(pattern_name.matcher(name).matches()){
                                        break;
                                    }
                                }

                                    results = categoriesGateway.getByName(name);
                                if (results != null) {
                                    System.out.println(results.size() + " records were searched.");
                                }
                                break;
                            }
                            case 2: {
                                Scanner scanner = new Scanner(System.in);
                                String name = null;
                                while(true){
                                    System.out.println("Enter category name:");
                                    name = scanner.nextLine();
                                    if(pattern_name.matcher(name).matches()){
                                        break;
                                    }
                                }
                                results = categoriesGateway.searchByName(name);
                                if (results != null) {
                                    System.out.println(results.size() + " records were searched.");
                                }
                                break;
                            }
                            case 0: {
                                localExitTrigger = false;
                            }
                        }
                    }
                    break;
                }
                case 0: {
                    System.out.println("Exiting!");
                    exitTrigger = false;
                    break;
                }
            }
        }
    }
    private void beginVendorsMenu() {
        boolean exitTrigger = true;
        Integer number = null;
        List results = null;

        while (exitTrigger) {
            System.out.println("Vendors:");
            if (results != null)
                System.out.println("Data loaded: " + results.size() +  " records");
            if (vendorPointer != null)
                System.out.println("Select: " + vendorPointer.toString());
            System.out.println("1. Load page");
            System.out.println("2. Print loaded data");
            System.out.println("3. Create");
            System.out.println("4. Get");
            System.out.println("5. Edit");
            System.out.println("6. Delete");
            System.out.println("7. Search");
            System.out.println("0. Exit");

            while (!sc.hasNextInt()) {
                System.out.println("Invalid input!");
                sc.next();
            }
            number = sc.nextInt();

            switch (number) {
                // LOAD VENDORS
                case 1: {
                    results = loadPage(vendorsGateway);
                    break;
                }
                // PRINT LOADED VENDORS
                case 2: {
                    printLoadedData(results);
                    break;
                }
                // CREATE VENDOR
                case 3: {
                    Scanner scanner = new Scanner(System.in);
                    Vendors vendor = new Vendors();

                    String name;
                    while(true){
                        System.out.println("Enter vendor name:");
                        name = scanner.nextLine();
                        if (vendorsGateway.getByName(name) != null){
                            System.out.println("This vendor already exists!");
                        }
                        else if (pattern_name.matcher(name).matches())
                            break;
                        System.out.println("Invalid input!");
                    }
                    if (!name.isEmpty()) {
                        vendor.setName(name);
                        vendorPointer = vendorsGateway.update(vendor);
                    }
                    break;
                }
                // GET VENDOR
                case 4: {
                    if (results != null) {
                        System.out.println("Input index:");
                        while (!sc.hasNextInt()) {
                            System.out.println("Invalid input!");
                            sc.next();
                        }
                        number = sc.nextInt();
                        if (number < results.size() && number > -1)
                            vendorPointer = (Vendors) results.get(number);
                    }
                    break;
                }
                // EDIT VENDOR
                case 5:{
                    if (vendorPointer != null) {
                        Scanner scanner = new Scanner(System.in);
                        String name = null;
                        while(true){
                            System.out.println("Enter new vendor name:");
                            name = scanner.nextLine();

                            if (name.isEmpty()){
                                break;
                            }
                            else if (vendorsGateway.getByName(name) != null){
                                System.out.println("This vendor already exists!");
                            }
                            else if(pattern_name.matcher(name).matches()){
                                vendorPointer.setName(name);
                                vendorPointer = vendorsGateway.update(vendorPointer);
                                break;
                            }
                        }
                    }
                    break;
                }

                // DELETE VENDOR
                case 6: {
                    if (vendorPointer != null) {
                        vendorsGateway.delete(vendorPointer);
                        vendorPointer = null;
                    }
                    break;
                }
                // SEARCH VENDOR
                case 7: {
                    Boolean localExitTrigger = true;
                    while (localExitTrigger){
                        System.out.println("Vendor search:");
                        System.out.println("1. Get by name");
                        System.out.println("2. Search by name");

                        while (!sc.hasNextInt()) {
                            System.out.println("Invalid input!");
                            sc.next();
                        }
                        number = sc.nextInt();

                        switch (number) {
                            case 1: {
                                Scanner scanner = new Scanner(System.in);
                                String name = null;
                                while(true){
                                    System.out.println("Enter vendor name:");
                                    name = scanner.nextLine();
                                    if(pattern_name.matcher(name).matches()){
                                        break;
                                    }
                                }
                                results = vendorsGateway.getByName(name);
                                break;
                            }
                            case 2: {
                                Scanner scanner = new Scanner(System.in);
                                String name = null;
                                while(true){
                                    System.out.println("Enter vendor name:");
                                    name = scanner.nextLine();
                                    if(pattern_name.matcher(name).matches()){
                                        break;
                                    }
                                }
                                results = vendorsGateway.searchByName(name);
                                break;
                            }
                            case 0: {
                                localExitTrigger = false;
                            }
                        }
                    }
                    break;
                }
                case 0: {
                    System.out.println("Exiting!");
                    exitTrigger = false;
                    break;
                }
            }
        }
    }
    private void beginProductsMenu() {
        boolean exitTrigger = true;
        Integer number = null;
        List results = null;

        while (exitTrigger) {
            System.out.println("Products:");
            if (results != null)
                System.out.println("Data loaded: " + results.size() +  " records");
            if (productPointer != null)
                System.out.println("Select: " + productPointer.toString());
            System.out.println("1. Load page");
            System.out.println("2. Print loaded data");
            System.out.println("3. Create");
            System.out.println("4. Get");
            System.out.println("5. Edit");
            System.out.println("6. Delete");
            System.out.println("7. Search");
            System.out.println("0. Exit");

            while (!sc.hasNextInt()) {
                System.out.println("Invalid input!");
                sc.next();
            }
            number = sc.nextInt();

            switch (number) {
                case 1: {
                    results = loadPage(productsGateway);
                    break;
                }
                case 2: {
                    printLoadedData(results);
                    break;
                }
                // CREATE PRODUCT
                case 3: {
                    if (categoriesPointer != null && vendorPointer != null) {
                        Scanner scanner = new Scanner(System.in);
                        Products product = new Products();
                        Categories category = categoriesPointer;
                        Vendors vendor = vendorPointer;
                        String model;
                        String specs;
                        Integer price;
                        Integer warranty_period;
                        String image;

                        product.setCategory(category);
                        product.setVendor(vendor);

                        while(true){
                            System.out.println("Enter model:");
                            model = scanner.nextLine();
                            if (pattern_name.matcher(model).matches())
                                break;
                            else
                                System.out.println("Invalid input!");
                        }
                        if (!model.isEmpty()) {
                            product.setModel(model);
                        }

                        System.out.println("Enter specs:");
                        specs = scanner.nextLine();
                        if (!specs.isEmpty()) {
                            product.setSpecs(specs);
                        }

                        System.out.println("Enter price:");
                        while (!sc.hasNextInt()) {
                            System.out.println("Invalid input!");
                            sc.next();
                        }
                        price = sc.nextInt();
                        product.setPrice(price);

                        System.out.println("Enter warranty period:");
                        while (!sc.hasNextInt()) {
                            System.out.println("Invalid input!");
                            sc.next();
                        }
                        warranty_period = sc.nextInt();
                        product.setWarrantyPeriod(warranty_period);

                        System.out.println("Enter image path:");
                        image = scanner.nextLine();
                        product.setImage(image);

                        productPointer = productsGateway.update(product);
                    } else {
                        System.out.println("Category and vendor are not selected!");
                    }
                    break;
                }
                // GET PRODUCT
                case 4: {
                    if (results != null) {
                        System.out.println("Input index:");
                        while (!sc.hasNextInt()) {
                            System.out.println("Invalid input!");
                            sc.next();
                        }
                        number = sc.nextInt();
                        if (number < results.size() && number > -1)
                            productPointer = (Products) results.get(number);
                    }
                    break;
                }
                // EDIT PRODUCT
                case 5:{
                    if (productPointer != null) {
                        Scanner scanner = new Scanner(System.in);
                        String model;
                        String specs;
                        Integer price;
                        Integer warranty_period;
                        String image;

                        if (categoriesPointer != null && productPointer.getCategory() != categoriesPointer){
                            Categories category = categoriesPointer;
                            System.out.println("Change category " + productPointer.getCategory().getName() + " to " + category.getName() + " ?");
                            System.out.println("(true - yes, false - no)");
                            while (!sc.hasNextBoolean()) {
                                System.out.println("Invalid input!");
                                sc.next();
                            }
                            boolean t = sc.nextBoolean();
                            if (t) {
                                productPointer.setCategory(category);
                            }
                        } else
                            System.out.println("To change category, select it.");

                        if (vendorPointer != null && productPointer.getVendor() != vendorPointer){
                            Vendors vendor = vendorPointer;
                            System.out.println("Change category " + productPointer.getVendor().getName() + " to " + vendor.getName() + " ?");
                            System.out.println("(true - yes, false - no)");
                            while (!sc.hasNextBoolean()) {
                                System.out.println("Invalid input!");
                                sc.next();
                            }
                            boolean t = sc.nextBoolean();
                            if (t) {
                                productPointer.setVendor(vendor);
                            }

                        } else
                            System.out.println("To change vendor, select it.");

                        while(true){
                            System.out.println("Enter new model:");
                            model = scanner.nextLine();
                            if (pattern_name.matcher(model).matches())
                                break;
                            else
                                System.out.println("Invalid input!");
                        }

                        if (productPointer.getModel() != model){
                            System.out.println("Change model " + productPointer.getModel() + " to " + model + " ?");
                            System.out.println("(true - yes, false - no)");
                            while (!sc.hasNextBoolean()) {
                                System.out.println("Invalid input!");
                                sc.next();
                            }
                            boolean t = sc.nextBoolean();
                            if (t)
                                productPointer.setModel(model);
                        }

                        System.out.println("Enter new specs: ");
                        specs = scanner.nextLine();
                        if (productPointer.getSpecs() != specs){
                            System.out.println("Change specs " + productPointer.getSpecs() + " to " + specs + " ?");
                            System.out.println("(true - yes, false - no)");
                            while (!sc.hasNextBoolean()) {
                                System.out.println("Invalid input!");
                                sc.next();
                            }
                            boolean t = sc.nextBoolean();
                            if (t)
                                productPointer.setSpecs(specs);
                        }

                        System.out.println("Enter new price:");
                        while (!sc.hasNextInt()) {
                            System.out.println("Invalid input!");
                            sc.next();
                        }
                        price = sc.nextInt();
                        if (productPointer.getPrice() != price){
                            System.out.println("Change price " + productPointer.getPrice() + " to " + price + " ?");
                            System.out.println("(true - yes, false - no)");
                            while (!sc.hasNextBoolean()) {
                                System.out.println("Invalid input!");
                                sc.next();
                            }
                            boolean t = sc.nextBoolean();
                            if (t)
                                productPointer.setPrice(price);
                        }

                        System.out.println("Enter new warranty period:");
                        while (!sc.hasNextInt()) {
                            System.out.println("Invalid input!");
                            sc.next();
                        }
                        warranty_period = sc.nextInt();
                        if (productPointer.getWarrantyPeriod() != warranty_period){
                            System.out.println("Change warranty " + productPointer.getWarrantyPeriod() + " to " + warranty_period + " ?");
                            System.out.println("(true - yes, false - no)");
                            while (!sc.hasNextBoolean()) {
                                System.out.println("Invalid input!");
                                sc.next();
                            }
                            boolean t = sc.nextBoolean();
                            if (t)
                                productPointer.setWarrantyPeriod(warranty_period);
                        }

                        System.out.println("Enter new image path:");
                        image = scanner.nextLine();
                        productPointer.setImage(image);

                        productPointer = productsGateway.update(productPointer);
                    }
                    else
                        System.out.println("Product are not selected!");
                    break;
                }
                // DELETE PRODUCT
                case 6: {
                    if (productPointer != null) {
                        productsGateway.delete(productPointer);
                        productPointer = null;
                    }
                    break;
                }
                // SEARCH PRODUCT
                case 7: {
                    Boolean localExitTrigger = true;
                    while (localExitTrigger){
                        System.out.println("Product search:");
                        System.out.println("1. Search by model");
                        System.out.println("2. Search by specs");
                        System.out.println("3. Search by vendor");
                        System.out.println("4. Search by category");
                        System.out.println("0. Exit");

                        while (!sc.hasNextInt()) {
                            System.out.println("Invalid input!");
                            sc.next();
                        }
                        number = sc.nextInt();

                        switch (number) {
                            case 1: {
                                Scanner scanner = new Scanner(System.in);
                                String name = null;
                                while(true){
                                    System.out.println("Enter model:");
                                    name = scanner.nextLine();
                                    if(pattern_name.matcher(name).matches()){
                                        break;
                                    }
                                }
                                results = productsGateway.searchByModel(name);
                                break;
                            }
                            case 2: {
                                Scanner scanner = new Scanner(System.in);
                                String specs = null;
                                System.out.println("Enter specs:");
                                specs = scanner.nextLine();
                                results = productsGateway.searchBySpecs(specs);
                                break;
                            }
                            case 3: {
                                Scanner scanner = new Scanner(System.in);
                                System.out.println("Enter vendor name:");
                                String name = null;
                                name = scanner.nextLine();
                                List vendors = vendorsGateway.searchByName(name);
                                for (int i = 0; i < vendors.size(); i++) {
                                    Object element = vendors.get(i);
                                    if (results != null)
                                        results.addAll(productsGateway.searchByVendor((Vendors) element));
                                    else
                                        results = productsGateway.searchByVendor((Vendors) element);
                                }
                                break;
                            }
                            case 4: {
                                Scanner scanner = new Scanner(System.in);
                                System.out.println("Enter category name:");
                                String name = null;
                                name = scanner.nextLine();
                                List categories = categoriesGateway.searchByName(name);

                                for (int i = 0; i < categories.size(); i++) {
                                    Object element = categories.get(i);
                                    if (results != null)
                                        results.addAll(productsGateway.searchByCategory((Categories) element));
                                    else
                                        results = productsGateway.searchByCategory((Categories) element);
                                }
                                break;
                            }
                            case 0: {
                                localExitTrigger = false;
                            }
                        }
                    }
                    break;
                }
                case 0: {
                    System.out.println("Exiting!");
                    exitTrigger = false;
                    break;
                }
            }
        }
    }
    private void beginClientsMenu() {
        boolean exitTrigger = true;
        Integer number = null;
        List results = null;

        while (exitTrigger) {
            System.out.println("Clients:");
            if (clientPointer != null)
                System.out.println("Select: " + clientPointer.toString());
            System.out.println("1. Load page");
            System.out.println("2. Print loaded data");
            System.out.println("3. Create");
            System.out.println("4. Get");
            System.out.println("5. Edit");
            System.out.println("6. Delete");
            System.out.println("7. Search");
            System.out.println("0. Exit");

            while (!sc.hasNextInt()) {
                System.out.println("Invalid input!");
                sc.next();
            }
            number = sc.nextInt();

            switch (number) {
                case 1: {
                    results = loadPage(clientsGateway);
                    break;
                }
                case 2: {
                    printLoadedData(results);
                    break;
                }
                // CREATE CLIENT
                case 3: {

                    Clients client = new Clients();

                    System.out.println(client.toString());

                    String firstName = null;
                    String lastName = null;
                    String email = null;
                    String contactNumber = null;

                    Scanner scanner_firstname = new Scanner(System.in);
                    do {
                        System.out.println("Enter firstname:");
                        firstName = scanner_firstname.nextLine();
                    }
                    while (firstName.isEmpty());
                    client.setFirstName(firstName);

                    Scanner scanner_lastname = new Scanner(System.in);
                    do {
                        System.out.println("Enter lastname:");
                        lastName = scanner_lastname.nextLine();
                    }
                    while (lastName.isEmpty());
                    client.setLastName(lastName);

                    System.out.println("Enter email:");
                    Scanner scanner_email = new Scanner(System.in);
                    email = scanner_email.nextLine();
                    client.setEmail(email);

                    Scanner scanner_phone = new Scanner(System.in);
                    do {
                    System.out.println("Enter contact number:");
                    contactNumber = scanner_phone.nextLine();
                    }
                    while (contactNumber.isEmpty());
                    client.setContactNumber(contactNumber);

                    clientPointer = clientsGateway.update(client);
                    break;
                }
                // GET CLIENT
                case 4: {
                    if (results != null) {
                        System.out.println("Input index:");
                        while (!sc.hasNextInt()) {
                            System.out.println("Invalid input!");
                            sc.next();
                        }
                        number = sc.nextInt();
                        if (number < results.size() && number > -1)
                            clientPointer = (Clients) results.get(number);
                    }
                    break;
                }
                // EDIT CLIENT
                case 5:{
                    if (clientPointer != null) {
                        Scanner scanner = new Scanner(System.in);

                        String firstName = null;
                        String lastName = null;
                        String email = null;
                        String contactNumber = null;

                        do {
                            System.out.println("Enter new firstname:");
                            firstName = scanner.nextLine();
                        }
                        while (firstName.isEmpty());
                        if (clientPointer.getFirstName() != firstName){
                            System.out.println("Change firstname " + clientPointer.getFirstName() + " to " + firstName + " ?");
                            System.out.println("(true - yes, false - no)");
                            while (!sc.hasNextBoolean()) {
                                System.out.println("Invalid input!");
                                sc.next();
                            }
                            boolean t = sc.nextBoolean();
                            if (t)
                                clientPointer.setFirstName(firstName);
                        }

                        do {
                        System.out.println("Enter new lastname: ");
                        lastName = scanner.nextLine();
                        }
                        while (lastName.isEmpty());
                        if (clientPointer.getLastName() != lastName){
                            System.out.println("Change lastname " + clientPointer.getLastName() + " to " + lastName + " ?");
                            System.out.println("(true - yes, false - no)");
                            while (!sc.hasNextBoolean()) {
                                System.out.println("Invalid input!");
                                sc.next();
                            }
                            boolean t = sc.nextBoolean();
                            if (t)
                                clientPointer.setLastName(lastName);
                        }

                        System.out.println("Enter email:");
                        email = scanner.nextLine();
                        if (clientPointer.getEmail() != email){
                            System.out.println("Change email " + clientPointer.getEmail() + " to " + email + " ?");
                            System.out.println("(true - yes, false - no)");
                            while (!sc.hasNextBoolean()) {
                                System.out.println("Invalid input!");
                                sc.next();
                            }
                            boolean t = sc.nextBoolean();
                            if (t)
                                clientPointer.setEmail(email);
                        }

                        do {
                            System.out.println("Enter new contact number: ");
                            contactNumber = scanner.nextLine();
                        }
                        while (contactNumber.isEmpty());
                        if (clientPointer.getContactNumber() != contactNumber){
                            System.out.println("Change contact number " + clientPointer.getContactNumber() + " to " + contactNumber + " ?");
                            System.out.println("(true - yes, false - no)");
                            while (!sc.hasNextBoolean()) {
                                System.out.println("Invalid input!");
                                sc.next();
                            }
                            boolean t = sc.nextBoolean();
                            if (t)
                                clientPointer.setContactNumber(contactNumber);
                        }

                        clientPointer = clientsGateway.update(clientPointer);
                    }
                    else
                        System.out.println("Client are not selected!");
                    break;
                }
                // delete
                case 6: {
                    if (clientPointer != null) {
                        clientsGateway.delete(clientPointer);
                        clientPointer = null;
                    }
                    break;
                }
                // search
                case 7: {
                    Boolean localExitTrigger = true;
                    while (localExitTrigger){
                        System.out.println("Clients search:");
                        System.out.println("1. Search by firstname");
                        System.out.println("2. Search by lastname");
                        System.out.println("3. Search by email");
                        System.out.println("4. Search by contact number");
                        System.out.println("0. Exit");

                        while (!sc.hasNextInt()) {
                            System.out.println("Invalid input!");
                            sc.next();
                        }
                        number = sc.nextInt();

                        switch (number) {
                            case 1: {
                                Scanner scanner = new Scanner(System.in);
                                String firstname = null;
                                System.out.println("Enter firstname:");
                                firstname = scanner.nextLine();
                                results = clientsGateway.searchByFirstname(firstname);
                                System.out.println(results.size() + " records were searched.");
                                break;
                            }
                            case 2: {
                                Scanner scanner = new Scanner(System.in);
                                String lastname = null;
                                System.out.println("Enter firstname:");
                                lastname = scanner.nextLine();
                                results = clientsGateway.searchByLastname(lastname);
                                System.out.println(results.size() + " records were searched.");
                                break;
                            }
                            case 3: {
                                Scanner scanner = new Scanner(System.in);
                                String email = null;
                                System.out.println("Enter email:");
                                email = scanner.nextLine();
                                results = clientsGateway.searchByEmail(email);
                                System.out.println(results.size() + " records were searched.");
                                break;
                            }
                            case 4: {
                                Scanner scanner = new Scanner(System.in);
                                String contactNumber = null;
                                System.out.println("Enter contact number:");
                                contactNumber = scanner.nextLine();
                                results = clientsGateway.searchByContactNumber(contactNumber);
                                System.out.println(results.size() + " records were searched.");
                                break;
                            }
                            case 0: {
                                localExitTrigger = false;
                            }
                        }
                    }
                    break;
                }
                case 0: {
                    exitTrigger = false;
                    break;
                }
            }
        }
    }
    private void beginOrdersMenu() {
        boolean exitTrigger = true;
        Integer number = null;
        List results = null;

        while (exitTrigger) {
            System.out.println("Orders:");
            if (orderPointer != null)
                System.out.println("Pointer to order: " + orderPointer.toString());
            System.out.println("1. Load page");
            System.out.println("2. Print loaded data");
            System.out.println("3. Create");
            System.out.println("4. Get");
            System.out.println("5. Edit");
            System.out.println("6. Delete");
            System.out.println("7. Search");
            System.out.println("0. Exit");

            while (!sc.hasNextInt()) {
                System.out.println("Invalid input!");
                sc.next();
            }
            number = sc.nextInt();

            switch (number) {
                case 1: {
                    results = loadPageForOrders(ordersGateway);

                    break;
                }
                case 2: {
                    printLoadedData(results);
                    break;
                }
                // CREATE ORDER
                case 3: {
                    if (clientPointer != null && storePointer != null) {
                        Orders order = new Orders();

                        Clients client = clientPointer;
                        order.setClient(client);
                        Stores store = storePointer;
                        order.setStore(store);

                        String orderDate;
                        String orderTime;

                        Scanner scanner_date = new Scanner(System.in);
                        while(true){
                            System.out.println("Enter order date (format YYYY-MM-DD):");
                            orderDate = scanner_date.nextLine();
                            if (pattern_date.matcher(orderDate).matches())
                                break;
                            System.out.println("Invalid input!");
                        }
                        order.setOrderDate(orderDate);

                        Scanner scanner_time = new Scanner(System.in);
                        while(true){
                            System.out.println("Enter order time (format HH:MM:SS):");
                            orderTime = scanner_time.nextLine();
                            if (pattern_time.matcher(orderTime).matches())
                                break;
                            System.out.println("Invalid input!");
                        }
                        order.setOrderTime(orderTime);

                        Scanner scanner_confirm = new Scanner(System.in);
                        System.out.println("Confirm the order?");
                        System.out.println("true -> yes, false -> no");
                        while (!scanner_confirm.hasNextBoolean()) {
                            System.out.println("Invalid input!");
                            System.out.println("true -> yes, false -> no");
                            scanner_confirm.next();
                        }
                        boolean t = scanner_confirm.nextBoolean();
                        order.setConfirmation(t);

                        orderPointer = ordersGateway.update(order);

                    } else {
                        System.out.println("Client and store are not selected!");
                    }
                    break;
                }
                // GET ORDER
                case 4: {
                    if (results != null) {
                        System.out.println("Input index:");
                        while (!sc.hasNextInt()) {
                            System.out.println("Invalid input!");
                            sc.next();
                        }
                        number = sc.nextInt();
                        if (number < results.size() && number > -1)
                            orderPointer = (Orders) results.get(number);
                    }
                    break;
                }
                // EDIT ORDER
                case 5: {
                    if (orderPointer != null) {

                        // CHANGE CLIENT
                        Scanner scanner_client = new Scanner(System.in);
                        if (clientPointer != null && orderPointer.getClient() != clientPointer){
                            Clients client = clientPointer;
                            System.out.println("Change client " + orderPointer.getClient()+ " to " + clientPointer + " ?");
                            System.out.println("(true - yes, false - no)");
                            while (!scanner_client.hasNextBoolean()) {
                                System.out.println("Invalid input!");
                                scanner_client.next();
                            }
                            boolean t = scanner_client.nextBoolean();
                            if (t)
                                orderPointer.setClient(client);
                        } else
                            System.out.println("To change client, select it.");

                        // CHANGE STORE
                        Scanner scanner_store = new Scanner(System.in);
                        if (storePointer != null && orderPointer.getStore() != storePointer){
                            Stores store = storePointer;
                            System.out.println("Change store " + orderPointer.getStore() + " to " + store + " ?");
                            System.out.println("(true - yes, false - no)");
                            while (!scanner_store.hasNextBoolean()) {
                                System.out.println("Invalid input!");
                                scanner_store.next();
                            }
                            boolean t = scanner_store.nextBoolean();
                            if (t)
                                orderPointer.setStore(store);
                        } else
                            System.out.println("To change store, select it.");

                        // CHANGE ORDER DATE
                        Scanner scanner_date = new Scanner(System.in);
                        String input = null;
                        while(true){
                            System.out.println("Enter new order date (format YYYY-MM-DD):");
                            input = scanner_date.nextLine();
                            if (pattern_date.matcher(input).matches())
                                break;
                            System.out.println("Invalid input!");
                        }
                        String orderDate = input;
                        if (orderPointer.getOrderDate() != orderDate){
                            System.out.println("Change order date " + orderPointer.getOrderDate() + " to " + orderDate + " ?");
                            System.out.println("(true - yes, false - no)");
                            while (!scanner_date.hasNextBoolean()) {
                                System.out.println("Invalid input!");
                                scanner_date.next();
                            }
                            boolean t = scanner_date.nextBoolean();
                            if (t)
                                orderPointer.setOrderDate(orderDate);
                        }

                        // CHANGE ORDER TIME
                        Scanner scanner_time = new Scanner(System.in);
                        while(true){
                            System.out.println("Enter order time (format HH:MM:SS):");
                            input = scanner_time.nextLine();
                            if (pattern_time.matcher(input).matches())
                                break;
                            System.out.println("Invalid input!");
                        }
                        String orderTime = input;
                        if (orderPointer.getOrderTime() != orderTime){
                            System.out.println("Change order time " + orderPointer.getOrderTime() + " to " + orderTime + " ?");
                            System.out.println("(true - yes, false - no)");
                            while (!scanner_time.hasNextBoolean()) {
                                System.out.println("Invalid input!");
                                scanner_time.next();
                            }
                            boolean t = scanner_time.nextBoolean();
                            if (t)
                                orderPointer.setOrderTime(orderTime);
                        }

                        // CHANGE CONFIRMATION
                        Scanner scanner_confirm = new Scanner(System.in);
                        System.out.println("Confirm the order?");
                        System.out.println("(true -> yes, false -> no)");
                        while (!scanner_confirm.hasNextBoolean()) {
                            System.out.println("Invalid input!");
                            System.out.println("(true -> yes, false -> no)");
                            scanner_confirm.next();
                        }
                        boolean t = scanner_confirm.nextBoolean();
                        orderPointer.setConfirmation(t);

                        // UPDATE
                        orderPointer.setShoppingCart(null);
                        orderPointer = ordersGateway.update(orderPointer);
                    }
                    else
                        System.out.println("Order are not selected!");
                    break;
                }
                // DELETE ORDER
                case 6: {
                    if (orderPointer != null) {
                        ordersGateway.delete(orderPointer);
                        orderPointer = null;
                    }
                    break;
                }
                // SEARCH ORDER
                case 7: {
                    Boolean localExitTrigger = true;
                    while (localExitTrigger){
                        System.out.println("Order search:");
                        System.out.println("1. Search by client");
                        System.out.println("2. Search by store");
                        System.out.println("3. Search by date");
                        System.out.println("4. Search by time");
                        System.out.println("5. Search by confirmation");
                        System.out.println("0. Exit");

                        while (!sc.hasNextInt()) {
                            System.out.println("Invalid input!");
                            sc.next();
                        }
                        number = sc.nextInt();

                        switch (number) {
                            case 1: {
                                try {
                                    System.out.println("Enter by client: " + clientPointer);
                                    results = ordersGateway.searchByClient(clientPointer);
                                    System.out.println(results.size() + " records were searched.");
                                } catch (Exception ex) {
                                    System.out.println(0 + " records were searched.");
                                }
                                break;
                            }
                            case 2: {
                                try {
                                    System.out.println("Enter by store: " + storePointer);
                                    results = ordersGateway.searchByStore(storePointer);
                                    System.out.println(results.size() + " records were searched.");
                                } catch (Exception ex) {
                                    System.out.println(0 + " records were searched.");
                                }
                            }
                            case 3: {
                                try {
                                    String input = null;
                                    Scanner scanner_date = new Scanner(System.in);
                                    while (true) {
                                        System.out.println("Enter date (format YYYY-MM-DD):");
                                        input = scanner_date.nextLine();
                                        if (pattern_date.matcher(input).matches())
                                            break;
                                        else
                                            System.out.println("Invalid input!");
                                    }
                                    String orderDate = input;
                                    results = ordersGateway.searchByDate(orderDate);
                                    System.out.println(results.size() + " records were searched.");
                                } catch (Exception ex) {
                                    System.out.println(0 + " records were searched.");
                                }
                                break;
                            }
                            case 4: {
                                try {
                                    String input = null;
                                    Scanner scanner_starting_time = new Scanner(System.in);
                                    while (true) {
                                        System.out.println("Enter starting time (format HH:MM:SS):");
                                        input = scanner_starting_time.nextLine();
                                        if (pattern_time.matcher(input).matches())
                                            break;
                                        else
                                            System.out.println("Invalid input!");
                                    }
                                    String startingTime = input;

                                    Scanner scanner_ending_time = new Scanner(System.in);
                                    while (true) {
                                        System.out.println("Enter ending time (format HH:MM:SS):");
                                        input = scanner_ending_time.nextLine();
                                        if (pattern_time.matcher(input).matches())
                                            break;
                                        System.out.println("Invalid input!");
                                    }
                                    String endingTime = input;

                                    results = ordersGateway.searchByTime(startingTime, endingTime);
                                    System.out.println(results.size() + " records were searched.");
                                } catch (Exception ex) {
                                    System.out.println(0 + " records were searched.");
                                }
                                break;
                            }
                            case 5: {
                                try {
                                    Scanner scanner_confirm = new Scanner(System.in);
                                    Boolean confirmation = null;
                                    System.out.println("Is the order confirmed?");
                                    System.out.println("(true -> yes, false -> no)");
                                    while (!scanner_confirm.hasNextBoolean()) {
                                        System.out.println("Invalid input!");
                                        System.out.println("(true -> yes, false -> no)");
                                        scanner_confirm.next();
                                    }
                                    confirmation = scanner_confirm.nextBoolean();
                                    results = ordersGateway.getByConfirmation(confirmation);
                                    System.out.println(results.size() + " records were searched.");
                                } catch (Exception ex) {
                                    System.out.println(0 + " records were searched.");
                                }
                                break;
                            }
                            case 0: {
                                localExitTrigger = false;
                            }
                        }
                    }
                    break;
                }
                case 0: {
                    System.out.println("Exiting!");
                    exitTrigger = false;
                    break;
                }
            }
        }
    }
    private void beginCouriersMenu() {
        boolean exitTrigger = true;
        Integer number = null;
        List results = null;

        while (exitTrigger) {
            System.out.println("Couriers:");
            if (courierPointer != null)
                System.out.println("Select: " + courierPointer.toString());
            System.out.println("1. Load page");
            System.out.println("2. Print loaded data");
            System.out.println("3. Create");
            System.out.println("4. Get");
            System.out.println("5. Edit");
            System.out.println("6. Delete");
            System.out.println("7. Search");
            System.out.println("0. Exit");

            while (!sc.hasNextInt()) {
                System.out.println("Invalid input!");
                sc.next();
            }
            number = sc.nextInt();

            switch (number) {
                // Load data
                case 1: {
                    results = loadPage(couriersGateway);
                    break;
                }
                // Print loaded data
                case 2: {
                    printLoadedData(results);
                    break;
                }
                // CREATE COURIER
                case 3: {
                    Scanner scanner = new Scanner(System.in);
                    Couriers courier = new Couriers();

                    String firstName = null;
                    String lastName = null;
                    String email = null;
                    String contactNumber = null;

                    do {
                        System.out.println("Enter firstname:");
                        firstName = scanner.nextLine();
                    }
                    while (firstName.isEmpty());
                    courier.setFirstName(firstName);

                    do {
                        System.out.println("Enter lastname:");
                        lastName = scanner.nextLine();
                    }
                    while (lastName.isEmpty());
                    courier.setLastName(lastName);

                    System.out.println("Enter email:");
                    email = scanner.nextLine();
                    courier.setEmail(email);

                    do {
                        System.out.println("Enter contact number:");
                        contactNumber = scanner.nextLine();
                    }
                    while (contactNumber.isEmpty());
                    courier.setContactNumber(contactNumber);

                    courierPointer = couriersGateway.update(courier);
                    break;
                }
                // GET COURIER
                case 4: {
                    if (results != null) {
                        System.out.println("Input index:");
                        while (!sc.hasNextInt()) {
                            System.out.println("Invalid input!");
                            sc.next();
                        }
                        number = sc.nextInt();
                        if (number < results.size() && number > -1)
                            courierPointer = (Couriers) results.get(number);
                    }
                    break;
                }
                // EDIT COURIER
                case 5:{
                    if (courierPointer != null) {
                        Scanner scanner = new Scanner(System.in);

                        String firstName = null;
                        String lastName = null;
                        String email = null;
                        String contactNumber = null;

                        do {
                            System.out.println("Enter new firstname:");
                            firstName = scanner.nextLine();
                        }
                        while (firstName.isEmpty());
                        if (courierPointer.getFirstName() != firstName){
                            System.out.println("Change firstname " + courierPointer.getFirstName() + " to " + firstName + " ?");
                            System.out.println("(true - yes, false - no)");
                            while (!sc.hasNextBoolean()) {
                                System.out.println("Invalid input!");
                                sc.next();
                            }
                            boolean t = sc.nextBoolean();
                            if (t)
                                courierPointer.setFirstName(firstName);
                        }

                        do {
                            System.out.println("Enter new lastname: ");
                            lastName = scanner.nextLine();
                        }
                        while (lastName.isEmpty());
                        if (courierPointer.getLastName() != lastName){
                            System.out.println("Change lastname " + courierPointer.getLastName() + " to " + lastName + " ?");
                            System.out.println("(true - yes, false - no)");
                            while (!sc.hasNextBoolean()) {
                                System.out.println("Invalid input!");
                                sc.next();
                            }
                            boolean t = sc.nextBoolean();
                            if (t)
                                courierPointer.setLastName(lastName);
                        }

                        System.out.println("Enter email:");
                        email = scanner.nextLine();
                        if (courierPointer.getEmail() != email){
                            System.out.println("Change email " + courierPointer.getEmail() + " to " + email + " ?");
                            System.out.println("(true - yes, false - no)");
                            while (!sc.hasNextBoolean()) {
                                System.out.println("Invalid input!");
                                sc.next();
                            }
                            boolean t = sc.nextBoolean();
                            if (t)
                                courierPointer.setEmail(email);
                        }

                        do {
                            System.out.println("Enter new contact number: ");
                            contactNumber = scanner.nextLine();
                        }
                        while (contactNumber.isEmpty());
                        if (courierPointer.getContactNumber() != contactNumber){
                            System.out.println("Change contact number " + courierPointer.getContactNumber() + " to " + contactNumber + " ?");
                            System.out.println("(true - yes, false - no)");
                            while (!sc.hasNextBoolean()) {
                                System.out.println("Invalid input!");
                                sc.next();
                            }
                            boolean t = sc.nextBoolean();
                            if (t)
                                courierPointer.setContactNumber(contactNumber);
                        }

                        courierPointer = couriersGateway.update(courierPointer);
                    }
                    else
                        System.out.println("Client are not selected!");
                    break;
                }
                // DELETE COURIER
                case 6: {
                    if (courierPointer != null) {
                        couriersGateway.delete(courierPointer);
                        courierPointer = null;
                    }
                    break;
                }
                // SEARCH COURIER
                case 7: {
                    Boolean localExitTrigger = true;
                    while (localExitTrigger){
                        System.out.println("Couriers search:");
                        System.out.println("1. Search by firstname");
                        System.out.println("2. Search by lastname");
                        System.out.println("3. Search by email");
                        System.out.println("4. Search by contact number");
                        System.out.println("0. Exit");

                        while (!sc.hasNextInt()) {
                            System.out.println("Invalid input!");
                            sc.next();
                        }
                        number = sc.nextInt();

                        switch (number) {
                            case 1: {
                                Scanner scanner = new Scanner(System.in);
                                String firstname = null;
                                System.out.println("Enter firstname:");
                                firstname = scanner.nextLine();
                                results = couriersGateway.searchByFirstname(firstname);
                                System.out.println(results.size() + " records were searched.");
                                break;
                            }
                            case 2: {
                                Scanner scanner = new Scanner(System.in);
                                String lastname = null;
                                System.out.println("Enter firstname:");
                                lastname = scanner.nextLine();
                                results = couriersGateway.searchByLastname(lastname);
                                System.out.println(results.size() + " records were searched.");
                                break;
                            }
                            case 3: {
                                Scanner scanner = new Scanner(System.in);
                                String email = null;
                                System.out.println("Enter email:");
                                email = scanner.nextLine();
                                results = couriersGateway.searchByEmail(email);
                                System.out.println(results.size() + " records were searched.");
                                break;
                            }
                            case 4: {
                                Scanner scanner = new Scanner(System.in);
                                String contactNumber = null;
                                System.out.println("Enter contact number:");
                                contactNumber = scanner.nextLine();
                                results = couriersGateway.searchByContactNumber(contactNumber);
                                System.out.println(results.size() + " records were searched.");
                                break;
                            }
                            case 0: {
                                localExitTrigger = false;
                            }
                        }
                    }
                    break;
                }
                case 0: {
                    exitTrigger = false;
                    break;
                }
            }
        }
    }
    private void beginDeliveryMenu() {
        boolean exitTrigger = true;
        Integer number = null;
        List results = null;

        while (exitTrigger) {
            System.out.println("Delivery:");
            if (deliveryPointer != null)
                System.out.println("Select: " + deliveryPointer.toString());
            System.out.println("1. Load page");
            System.out.println("2. Print loaded data");
            System.out.println("3. Create");
            System.out.println("4. Get");
            System.out.println("5. Edit");
            System.out.println("6. Delete");
            System.out.println("7. Search");
            System.out.println("0. Exit");

            while (!sc.hasNextInt()) {
                System.out.println("Invalid input!");
                sc.next();
            }
            number = sc.nextInt();

            switch (number) {
                case 1: {
                    results = loadPage(deliveryGateway);
                    break;
                }
                case 2: {
                    printLoadedData(results);
                    break;
                }
                // CREATE DELIVERY
                case 3: {
                    if (courierPointer != null && orderPointer != null) {
                        Delivery delivery = new Delivery();

                        List checkExist = deliveryGateway.getByOrder(orderPointer);

                        if (checkExist.isEmpty()){
                        Couriers courier = courierPointer;
                        delivery.setCourier(courier);
                        Orders order = orderPointer;
                        delivery.setOrder(order);

                        String deliveryDate;
                        String deliveryTime;
                        String deliveryAddress;

                        Scanner scanner_date = new Scanner(System.in);
                        while(true){
                            System.out.println("Enter delivery date (format YYYY-MM-DD):");
                            deliveryDate = scanner_date.nextLine();
                            if (pattern_date.matcher(deliveryDate).matches())
                                break;
                            System.out.println("Invalid input!");
                        }
                        delivery.setDeliveryDate(deliveryDate);

                        Scanner scanner_time = new Scanner(System.in);
                        while(true){
                            System.out.println("Enter delivery time (format HH:MM:SS):");
                            deliveryTime = scanner_time.nextLine();
                            if (pattern_time.matcher(deliveryTime).matches())
                                break;
                            System.out.println("Invalid input!");
                        }
                        delivery.setDeliveryTime(deliveryTime);

                        Scanner scanner_address = new Scanner(System.in);
                        do {
                            System.out.println("Enter delivery address:");
                            deliveryAddress = scanner_address.nextLine();
                        }
                        while (deliveryAddress.isEmpty());
                        delivery.setDeliveryAddress(deliveryAddress);

                        deliveryPointer = deliveryGateway.update(delivery);
                        }
                        else {
                            System.out.println("Delivery with such an order already exists!");
                            deliveryPointer = (Delivery) checkExist.get(0);
                        }

                    } else {
                        System.out.println("Courier and order are not selected!");
                    }
                    break;
                }
                // GET DELIVERY
                case 4: {
                    if (results != null) {
                        System.out.println("Input index:");
                        while (!sc.hasNextInt()) {
                            System.out.println("Invalid input!");
                            sc.next();
                        }
                        number = sc.nextInt();
                        if (number < results.size() && number > -1)
                            deliveryPointer = (Delivery) results.get(number);
                    } else
                        System.out.println("Loaded data is not found!");
                    break;
                }
                // EDIT DELIVERY
                case 5: {
                    if (deliveryGateway != null && deliveryPointer != null) {

                        // CHANGE COURIER
                        Scanner scanner_courier = new Scanner(System.in);
                        if (courierPointer != null && deliveryPointer.getCourier() != courierPointer){
                            Couriers courier = courierPointer;
                            System.out.println("Change courier " + deliveryPointer.getCourier()+ " to " + courierPointer + " ?");
                            System.out.println("(true - yes, false - no)");
                            while (!scanner_courier.hasNextBoolean()) {
                                System.out.println("Invalid input!");
                                scanner_courier.next();
                            }
                            boolean t = scanner_courier.nextBoolean();
                            if (t)
                                deliveryPointer.setCourier(courier);
                        } else
                            System.out.println("To change courier, select it.");
                        // CHANGE ORDER
                        Scanner scanner_order = new Scanner(System.in);
                        if (orderPointer != null && deliveryPointer.getOrder() != orderPointer){
                            Orders order = orderPointer;
                            System.out.println("Change order " + deliveryPointer.getOrder() + " to " + orderPointer + " ?");
                            System.out.println("(true - yes, false - no)");
                            while (!scanner_order.hasNextBoolean()) {
                                System.out.println("Invalid input!");
                                scanner_order.next();
                            }
                            boolean t = scanner_order.nextBoolean();
                            if (t)
                                deliveryPointer.setOrder(order);
                        } else
                            System.out.println("To change order, select it.");

                        // CHANGE DELIVERY DATE
                        Scanner scanner_date = new Scanner(System.in);
                        String input = null;
                        while(true){
                            System.out.println("Enter new delivery date (format YYYY-MM-DD):");
                            input = scanner_date.nextLine();
                            if (pattern_date.matcher(input).matches())
                                break;
                            System.out.println("Invalid input!");
                        }
                        String deliveryDate = input;
                        if (deliveryPointer.getDeliveryDate() != deliveryDate){
                            System.out.println("Change delivery date " + deliveryPointer.getDeliveryDate() + " to " + deliveryDate + " ?");
                            System.out.println("(true - yes, false - no)");
                            while (!scanner_date.hasNextBoolean()) {
                                System.out.println("Invalid input!");
                                scanner_date.next();
                            }
                            boolean t = scanner_date.nextBoolean();
                            if (t)
                                deliveryPointer.setDeliveryDate(deliveryDate.toString());
                        }

                        // CHANGE DELIVERY TIME
                        Scanner scanner_time = new Scanner(System.in);
                        while(true){
                            System.out.println("Enter new delivery time (format HH:MM:SS):");
                            input = scanner_time.nextLine();
                            if (pattern_time.matcher(input).matches())
                                break;
                            System.out.println("Invalid input!");
                        }
                        String deliveryTime = input;
                        if (deliveryPointer.getDeliveryTime() != deliveryTime){
                            System.out.println("Change delivery time " + deliveryPointer.getDeliveryTime() + " to " + deliveryTime + " ?");
                            System.out.println("(true - yes, false - no)");
                            while (!scanner_time.hasNextBoolean()) {
                                System.out.println("Invalid input!");
                                scanner_time.next();
                            }
                            boolean t = scanner_time.nextBoolean();
                            if (t)
                                deliveryPointer.setDeliveryTime(deliveryTime);
                        }

                        // CHANGE DELIVERY ADDRESS
                        Scanner scanner_address = new Scanner(System.in);
                        System.out.println("Enter new delivery address:");
                        String deliveryAddress = scanner_address.nextLine();
                        if (deliveryPointer.getDeliveryAddress() != deliveryAddress){
                            System.out.println("Change delivery address " + deliveryPointer.getDeliveryAddress() + " to " + deliveryAddress + " ?");
                            System.out.println("(true - yes, false - no)");
                            while (!scanner_address.hasNextBoolean()) {
                                System.out.println("Invalid input!");
                                scanner_address.next();
                            }
                            boolean t = scanner_address.nextBoolean();
                            if (t)
                                deliveryPointer.setDeliveryAddress(deliveryAddress);
                        }
                        // UPDATE DELIVERY
                        deliveryPointer = deliveryGateway.update(deliveryPointer);
                    }
                    else
                        System.out.println("Delivery are not selected!");
                    break;
                }
                // DELETE DELIVERY
                case 6: {
                    if (deliveryPointer != null) {
                        deliveryGateway.delete(deliveryPointer);
                        deliveryPointer = null;
                    }
                    break;
                }
                // SEARCH DELIVERY
                case 7: {
                    Boolean localExitTrigger = true;
                    while (localExitTrigger){
                        System.out.println("Couriers search:");
                        System.out.println("1. Search by courier");
                        System.out.println("2. Search by order");
                        System.out.println("3. Search by delivery date");
                        System.out.println("4. Search by delivery time");
                        System.out.println("5. Search by delivery address");
                        System.out.println("0. Exit");

                        while (!sc.hasNextInt()) {
                            System.out.println("Invalid input!");
                            sc.next();
                        }
                        number = sc.nextInt();

                        switch (number) {
                            case 1: {
                                try {
                                    System.out.println("Search by " + courierPointer.toString());
                                    results = deliveryGateway.getByCourier(courierPointer);
                                    System.out.println(results.size() + " records were searched.");
                                } catch (Exception ex) {
                                    System.out.println(0 + " records were searched.");
                                }
                                break;
                            }
                            case 2: {
                                try {
                                    System.out.println("Search by " + orderPointer.toString());
                                    results = deliveryGateway.getByOrder(orderPointer);
                                    System.out.println(results.size() + " records were searched.");
                                } catch (Exception ex) {
                                    System.out.println(0 + " records were searched.");
                                }
                                break;
                            }
                            case 3: {
                                try {
                                    Scanner scanner = new Scanner(System.in);
                                    String input = null;
                                    while(true){
                                        System.out.println("Enter starting date (format YYYY-MM-DD):");
                                        input = scanner.nextLine();
                                        if (pattern_date.matcher(input).matches())
                                            break;
                                        System.out.println("Invalid input!");
                                    }
                                    results = deliveryGateway.searchByDeliveryDate(input);
                                    System.out.println(results.size() + " records were searched.");
                                } catch (Exception e) {
                                    System.out.println(0 + " records were searched.");
                                }
                                break;
                            }
                            case 4:{
                                try {
                                    Scanner scanner = new Scanner(System.in);
                                    String input = null;
                                    while(true){
                                        System.out.println("Enter starting time (format HH:MM:SS):");
                                        input = scanner.nextLine();
                                        if (pattern_time.matcher(input).matches())
                                            break;
                                        System.out.println("Invalid input!");
                                    }
                                    String startingTime = input;
                                    while(true){
                                        System.out.println("Enter ending time (format HH:MM:SS):");
                                        input = scanner.nextLine();
                                        if (pattern_time.matcher(input).matches())
                                            break;
                                        else
                                            System.out.println("Invalid input!");
                                    }
                                    String endingTime = input;
                                    results = deliveryGateway.searchByDeliveryTime(startingTime, endingTime);
                                    System.out.println(results.size() + " records were searched.");
                                } catch (Exception e) {
                                    System.out.println(0 + " records were searched.");
                                }

                                break;
                            }
                            case 5: {
                                try {
                                    Scanner scanner = new Scanner(System.in);
                                    String deliveryAddress = null;
                                    System.out.println("Enter delivery address:");
                                    deliveryAddress = scanner.nextLine();
                                    results = deliveryGateway.searchByDeliveryAddress(deliveryAddress);
                                    System.out.println(results.size() + " records were searched.");
                                } catch (Exception ex) {
                                    System.out.println(0 + " records were searched.");
                                }
                                break;
                            }
                            case 0: {
                                localExitTrigger = false;
                            }
                        }
                    }
                    break;
                }
                case 0: {
                    exitTrigger = false; break;
                }
            }
        }
    }
    private void beginShoppingCartMenu() {
        boolean exitTrigger = true;
        Integer number = null;
        List results = null;

        while (exitTrigger) {
            System.out.println("Shopping cart:");
            if (shoppingCartPointer != null)
                System.out.println("Select: " + shoppingCartPointer);
            System.out.println("1. Load page");
            System.out.println("2. Print loaded data");
            System.out.println("3. Create");
            System.out.println("4. Get");
            System.out.println("5. Edit");
            System.out.println("6. Delete");
            System.out.println("7. Search");
            System.out.println("0. Exit");

            while (!sc.hasNextInt()) {
                System.out.println("Invalid input!");
                sc.next();
            }
            number = sc.nextInt();

            switch (number) {
                case 1: {
                    results = loadPage(shoppingCartGateway);
                    break;
                }
                case 2: {
                    printLoadedData(results);
                    break;
                }
                // ADD TO SHOPPING CART
                case 3: {
                    if (orderPointer != null && productPointer != null) {
                        Scanner scanner = new Scanner(System.in);

                        ShoppingCart shoppingCart = new ShoppingCart();

                        Integer amount = null;

                        List result = shoppingCartGateway.getByOrderAndProduct(orderPointer, productPointer);
                        if (result.isEmpty()){
                            Orders order = orderPointer;
                            shoppingCart.setOrder(order);
                            Products product = productPointer;
                            System.out.println("Enter amount: ");
                            shoppingCart.setProduct(product);
                            while (!scanner.hasNextInt()) {
                                System.out.println("Invalid input!");
                                scanner.next();
                            }
                            amount = scanner.nextInt();
                            if (amount > 0) shoppingCart.setAmount(amount);
                        }
                        else {
                            shoppingCart = (ShoppingCart) result.get(0);
                            amount = shoppingCart.getAmount();
                            amount++;
                            shoppingCart.setAmount(amount);
                        }
                        shoppingCartPointer = shoppingCartGateway.update(shoppingCart);
                    } else {
                        System.out.println("Order and product are not selected!");
                    }
                    break;
                }
                // GET SHOPPING CART
                case 4: {
                    if (results != null) {
                        System.out.println("Input index:");
                        while (!sc.hasNextInt()) {
                            System.out.println("Invalid input!");
                            sc.next();
                        }
                        number = sc.nextInt();
                        if (number < results.size() && number >= 0)
                            shoppingCartPointer = (ShoppingCart) results.get(number);
                    }
                    break;
                }
                // EDIT SHOPPING CART
                case 5: {
                    if (shoppingCartPointer != null) {
                        Scanner scanner = new Scanner(System.in);

                        // CHANGE ORDER
                        if (orderPointer != null && shoppingCartPointer.getOrder() != orderPointer){
                            System.out.println("Change order " + shoppingCartPointer.getOrder() + " to " + orderPointer + " ?");
                            System.out.println("(true - yes, false - no)");
                            while (!scanner.hasNextBoolean()) {
                                System.out.println("Invalid input!");
                                scanner.next();
                            }
                            boolean t = scanner.nextBoolean();
                            if (t)
                                shoppingCartPointer.setOrder(orderPointer);
                        } else
                            System.out.println("To change order, select it.");

                        // CHANGE PRODUCT
                        if (productPointer != null && shoppingCartPointer.getProduct() != productPointer){
                            System.out.println("Change product " + shoppingCartPointer.getProduct() + " to " + productPointer + " ?");
                            System.out.println("(true - yes, false - no)");
                            while (!scanner.hasNextBoolean()) {
                                System.out.println("Invalid input!");
                                scanner.next();
                            }
                            boolean t = scanner.nextBoolean();
                            if (t)
                                shoppingCartPointer.setProduct(productPointer);
                        } else
                            System.out.println("To change order, select it.");

                        // CHANGE AMOUNT
                        if (orderPointer != null && productPointer != null) {
                            List result = shoppingCartGateway.getByOrderAndProduct(orderPointer, productPointer);
                            if (!result.isEmpty()) {
                                shoppingCartPointer = (ShoppingCart) result.get(0);
                            }
                        }

                        Integer amount = null;
                        System.out.println("Enter amount: ");
                        while (!scanner.hasNextInt()) {
                            System.out.println("Invalid input!");
                            scanner.next();
                        }
                        amount = scanner.nextInt();
                        if (amount < 0 || amount > 100)
                            amount = 1;

                        if (amount != shoppingCartPointer.getAmount()) {
                            System.out.println("Change amount " + shoppingCartPointer.getAmount() + " to " + amount + " ?");
                            System.out.println("(true - yes, false - no)");
                            while (!scanner.hasNextBoolean()) {
                                System.out.println("Invalid input!");
                                scanner.next();
                            }
                            boolean t = scanner.nextBoolean();
                            if (t) {
                                shoppingCartPointer.setAmount(amount);
                            }
                        }

                        // UPDATE SHOPPING CART
                        shoppingCartPointer = shoppingCartGateway.update(shoppingCartPointer);
                    }
                    else
                        System.out.println("shopping cart are not selected!");
                    break;
                }
                // DELETE SHOPPING CART
                case 6: {
                    if (shoppingCartPointer != null) {
                        shoppingCartGateway.delete(shoppingCartPointer);
                        shoppingCartPointer = null;
                    }
                    break;
                }
                // SEARCH SHOPPING CART
                case 7: {
                    Boolean localExitTrigger = true;
                    while (localExitTrigger){
                        System.out.println("Couriers search:");
                        System.out.println("1. Search by order");
                        System.out.println("2. Search by product");
                        System.out.println("3. Search by amount");
                        System.out.println("0. Exit");

                        while (!sc.hasNextInt()) {
                            System.out.println("Invalid input!");
                            sc.next();
                        }
                        number = sc.nextInt();

                        switch (number) {
                            case 1: {
                                if (orderPointer != null){
                                    results = shoppingCartGateway.searchByOrder(orderPointer);
                                    System.out.println(results.size() + " records were searched.");
                                } else
                                    System.out.println("To search by order, select it.");
                                break;
                            }
                            case 2: {
                                if (productPointer != null){
                                    results = shoppingCartGateway.searchByProduct(productPointer);
                                    System.out.println(results.size() + " records were searched.");
                                } else
                                    System.out.println("To search by product, select it.");
                                break;
                            }
                            case 3: {
                                Scanner scanner = new Scanner(System.in);
                                Integer amount = null;
                                System.out.println("Enter amount:");
                                while (!scanner.hasNextInt()) {
                                    System.out.println("Invalid input!");
                                    scanner.next();
                                }
                                amount = scanner.nextInt();
                                results = shoppingCartGateway.searchByAmount(amount);
                                System.out.println(results.size() + " records were searched.");
                                break;
                            }
                            case 0: {
                                localExitTrigger = false;
                            }
                        }
                    }
                    break;
                }
                case 0: {
                    exitTrigger = false; break;
                }
            }
        }
    }
}