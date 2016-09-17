package com.sainsbury.ripefruit;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.File;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.whenNew;

/**
 * Created by chaitanyanaidu on 9/17/16.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({Jsoup.class,URL.class,RipeFruitsController.class})
public class RipeFruitsControllerTest {
    //Test case without any mocks

    @Test
    public void getJsonResponse1() throws Exception {
        RipeFruitsController ripeFruitsController =new RipeFruitsController("sample url");
        Document document=Jsoup.parse(new File("src/test/resources/Parent.html"),"UTF-8");

        Document referenceLinkDocument1=Jsoup.parse(new File("src/test/resources/ReferenceLink1.html"),"UTF-8");
        Document referenceLinkDocument2=Jsoup.parse(new File("src/test/resources/ReferenceLink2.html"),"UTF-8");
        PowerMockito.mockStatic(Jsoup.class);
        Connection mockConnection=mock(Connection.class);
        Connection mockRefDocConnection1=mock(Connection.class);
        Connection mockRefDocConnection2=mock(Connection.class);
        when(Jsoup.connect("sample url")).thenReturn(mockConnection);
        when(Jsoup.connect("http://hiring-tests.s3-website-eu-west-1.amazonaws.com/2015_Developer_Scrape/sainsburys-golden-kiwi--taste-the-difference-x4-685641-p-44.html")).thenReturn(mockRefDocConnection1);
        when(Jsoup.connect("http://hiring-tests.s3-website-eu-west-1.amazonaws.com/2015_Developer_Scrape/sainsburys-kiwi-fruit--ripe---ready-x4.html")).thenReturn(mockRefDocConnection2);
        when(mockConnection.get()).thenReturn(document);
        when(mockRefDocConnection1.get()).thenReturn(referenceLinkDocument1);
        when(mockRefDocConnection2.get()).thenReturn(referenceLinkDocument2);

        HttpURLConnection httpURLConnection=mock(HttpURLConnection.class);
        URL mockURL = PowerMockito.mock(URL.class);
        whenNew(URL.class).withArguments("http://hiring-tests.s3-website-eu-west-1.amazonaws.com/2015_Developer_Scrape/sainsburys-golden-kiwi--taste-the-difference-x4-685641-p-44.html").thenReturn(mockURL);
        whenNew(URL.class).withArguments("http://hiring-tests.s3-website-eu-west-1.amazonaws.com/2015_Developer_Scrape/sainsburys-kiwi-fruit--ripe---ready-x4.html").thenReturn(mockURL);
        when(mockURL.openConnection()).thenReturn(httpURLConnection);
        Map<String,List<String>> headerFields=new HashMap<>(1);
        headerFields.put("Content-Length", Arrays.asList("34567"));
        when(httpURLConnection.getHeaderFields()).thenReturn(headerFields);

assertEquals("{\n" +
        "  \"results\" : [ {\n" +
        "    \"title\" : \"Sainsbury's Golden Kiwi x4\",\n" +
        "    \"size\" : \"33.76kb\",\n" +
        "    \"unit_price\" : \"1.80\",\n" +
        "    \"description\" : \"Buy Sainsbury's Golden Kiwi x4 online from Sainsbury's, the same great quality, freshness and choice you'd find in store. Choose from 1 hour delivery slots and collect Nectar points.\"\n" +
        "  }, {\n" +
        "    \"title\" : \"Sainsbury's Kiwi Fruit, Ripe & Ready x4\",\n" +
        "    \"size\" : \"33.76kb\",\n" +
        "    \"unit_price\" : \"1.80\",\n" +
        "    \"description\" : \"Buy Sainsbury's Kiwi Fruit, Ripe & Ready x4 online from Sainsbury's, the same great quality, freshness and choice you'd find in store. Choose from 1 hour delivery slots and collect Nectar points.\"\n" +
        "  } ],\n" +
        "  \"total\" : \"3.60\"\n" +
        "}", ripeFruitsController.getJsonResponse());

    }
}
