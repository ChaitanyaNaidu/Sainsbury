package com.sainsbury.ripefruit;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.sainsbury.ripefruit.pojo.JSONResponse;
import com.sainsbury.ripefruit.pojo.RipeFruit;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chaitanya Naidu on 9/17/16.
 */
public class RipeFruitsController {

    private final String url;
    public RipeFruitsController(String url){
        this.url=url;
    }
    public static void main(String[] args) {
        try {
            RipeFruitsController ripeFruitsView = new RipeFruitsController(args[0]);
            String jsonResponse=ripeFruitsView.getJsonResponse();
            System.out.println(jsonResponse);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getJsonResponse()throws IOException {
        try {
            org.jsoup.nodes.Document doc = Jsoup.connect(this.url).get();
            Elements productLister = doc.select("ul[class=\"productLister listView\"]");
            Elements productDiv = productLister.select("div[class=\"product \"]");

            JSONResponse jsonResponse = new JSONResponse();
            List<RipeFruit> ripeFruits = new ArrayList<>(5);
            double total = 0.0;
            for (org.jsoup.nodes.Element element : productDiv) {
                RipeFruit ripeFruit = new RipeFruit();
                Elements hrefLink = element.select("a[href]");
                ripeFruit.setTitle(hrefLink.text());
                Elements pricePerUnit = element.select("p[class=\"pricePerUnit\"]");
                if (pricePerUnit.hasText()) {
                    double unitPrice = Double.parseDouble(pricePerUnit.text().replaceAll("[a-z/&]", ""));
                    total += unitPrice;
                    ripeFruit.setUnit_price(String.format("%.2f", unitPrice));
                }

                HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(hrefLink.attr("href")).openConnection();
                String sizeInBytes = httpURLConnection.getHeaderFields().get("Content-Length").get(0);
                double sizeInKb = Double.parseDouble(sizeInBytes) / 1024;

                ripeFruit.setSize(String.format("%.2f",sizeInKb) + "kb");
                httpURLConnection.disconnect();
                org.jsoup.nodes.Document sublinkDocument = Jsoup.connect(hrefLink.attr("href")).get();
                Elements description = sublinkDocument.select("meta[name=\"description\"]");
                ripeFruit.setDescription(description.attr("content"));
                ripeFruits.add(ripeFruit);
            }
            jsonResponse.setResults(ripeFruits);
            jsonResponse.setTotal(String.format("%.2f", total));

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            String json = objectMapper.writeValueAsString(jsonResponse);
            return json;
        } catch (IOException exception) {
            throw exception;
        }
    }
}
