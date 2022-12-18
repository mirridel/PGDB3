package org.example;

import Gateways.CategoriesGateway;
import Gateways.ProductsGateway;
import Gateways.VendorsGateway;
import Tables.*;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

/*
        CategoriesGateway cg = new CategoriesGateway();
        Categories cp = new Categories();
        cp = cg.getById(1);
        System.out.println(cp);

        VendorsGateway vg = new VendorsGateway();
        Vendors vp = new Vendors();
        vp = vg.getById(1);
        System.out.println(vp);

        ProductsGateway pg = new ProductsGateway();
        Products pp = new Products();

        pp.setId(-1);
        pp.setCategory(cp);
        pp.setVendor(vp);
        pp.setModel("BBBBBBBBBBBBB");
        pp.setSpecs("AAAAAAAAAAAAA");
        pp.setPrice(199);
        pp.setWarrantyPeriod(199);
        pp.setImage("AAAAAAAAAAAAA");

        pp = pg.update(pp);

        //ll.forEach(el -> System.out.println(el));
 */

        CLI cli = new CLI();
        cli.begin();
    }
}
