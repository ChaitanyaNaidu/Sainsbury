package com.sainsbury.ripefruit;

import static org.junit.Assert.assertEquals;

/**
 * Created by chaitanyanaidu on 9/17/16.
 */
public class RunMe {

    @org.junit.Test
    public void getJsonResponse() throws Exception {
        RipeFruitsController ripeFruitsController =new RipeFruitsController("http://hiring-tests.s3-website-eu-west-1.amazonaws.com/2015_Developer_Scrape/5_products.html");
        String json= ripeFruitsController.getJsonResponse();
        System.out.println(json);
        assertEquals("{\n" +
                "  \"results\" : [ {\n" +
                "    \"title\" : \"Sainsbury's Apricot Ripe & Ready x5\",\n" +
                "    \"size\" : \"38.27kb\",\n" +
                "    \"unit_price\" : \"3.50\",\n" +
                "    \"description\" : \"Buy Sainsbury's Apricot Ripe & Ready x5 online from Sainsbury's, the same great quality, freshness and choice you'd find in store. Choose from 1 hour delivery slots and collect Nectar points.\"\n" +
                "  }, {\n" +
                "    \"title\" : \"Sainsbury's Avocado Ripe & Ready XL Loose 300g\",\n" +
                "    \"size\" : \"38.67kb\",\n" +
                "    \"unit_price\" : \"1.50\",\n" +
                "    \"description\" : \"Buy Sainsbury's Avocado Ripe & Ready XL Loose 300g online from Sainsbury's, the same great quality, freshness and choice you'd find in store. Choose from 1 hour delivery slots and collect Nectar points.\"\n" +
                "  }, {\n" +
                "    \"title\" : \"Sainsbury's Avocado, Ripe & Ready x2\",\n" +
                "    \"size\" : \"43.44kb\",\n" +
                "    \"unit_price\" : \"1.80\",\n" +
                "    \"description\" : \"Burgers are a summer must-have and these homemade ones are perfect for a barbecue, topped with cool avocado and served with oven-baked potato wedges. \"\n" +
                "  }, {\n" +
                "    \"title\" : \"Sainsbury's Avocados, Ripe & Ready x4\",\n" +
                "    \"size\" : \"38.68kb\",\n" +
                "    \"unit_price\" : \"3.20\",\n" +
                "    \"description\" : \"Buy Sainsbury's Avocados, Ripe & Ready x4 online from Sainsbury's, the same great quality, freshness and choice you'd find in store. Choose from 1 hour delivery slots and collect Nectar points.\"\n" +
                "  }, {\n" +
                "    \"title\" : \"Sainsbury's Conference Pears, Ripe & Ready x4 (minimum)\",\n" +
                "    \"size\" : \"38.54kb\",\n" +
                "    \"unit_price\" : \"1.50\",\n" +
                "    \"description\" : \"Buy Sainsbury's Conference Pears, Ripe & Ready x4 (minimum) online from Sainsbury's, the same great quality, freshness and choice you'd find in store. Choose from 1 hour delivery slots and collect Nectar points.\"\n" +
                "  }, {\n" +
                "    \"title\" : \"Sainsbury's Golden Kiwi x4\",\n" +
                "    \"size\" : \"38.56kb\",\n" +
                "    \"unit_price\" : \"1.80\",\n" +
                "    \"description\" : \"Buy Sainsbury's Golden Kiwi x4 online from Sainsbury's, the same great quality, freshness and choice you'd find in store. Choose from 1 hour delivery slots and collect Nectar points.\"\n" +
                "  }, {\n" +
                "    \"title\" : \"Sainsbury's Kiwi Fruit, Ripe & Ready x4\",\n" +
                "    \"size\" : \"38.98kb\",\n" +
                "    \"unit_price\" : \"1.80\",\n" +
                "    \"description\" : \"Buy Sainsbury's Kiwi Fruit, Ripe & Ready x4 online from Sainsbury's, the same great quality, freshness and choice you'd find in store. Choose from 1 hour delivery slots and collect Nectar points.\"\n" +
                "  } ],\n" +
                "  \"total\" : \"15.10\"\n" +
                "}",json);
    }
}
