package com.example.asepmentry.service;

import com.example.asepmentry.modell.Currency;
import com.example.asepmentry.repository.CurrencyRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@Service
public class SkyScannerConnector {

    @Autowired
    private CurrencyRepository currencyRepository;

    public void getCurrencies() {
        CloseableHttpClient client = HttpClients.createDefault();

        try {
            URI uriCurr = new URIBuilder()
                    .setScheme("https")
                    .setHost("skyscanner-skyscanner-flight-search-v1.p.rapidapi.com")
                    .setPath("/apiservices/reference/v1.0/currencies")
                    .build();
            HttpGet getCurrencies = new HttpGet(uriCurr);
            getCurrencies.addHeader("x-rapidapi-host",
                    "skyscanner-skyscanner-flight-search-v1.p.rapidapi.com");
            getCurrencies.addHeader("x-rapidapi-key", "f1875bf764mshd76a8b16f1bc074p18be3bjsn4e8309eedc29");
            CloseableHttpResponse response = client.execute(getCurrencies);
            System.out.println(response.getStatusLine().getStatusCode());
            String responseBody = EntityUtils.toString(response.getEntity());

            /*
            Convert JSON to Java Object
             */
            System.out.println(responseBody);
            ObjectMapper objectMapper = new ObjectMapper()
                    .configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
            JsonNode jsonNode = objectMapper.readTree(responseBody);
            System.out.println(jsonNode.size());
            System.out.println(jsonNode.fieldNames().next());
            System.out.println(jsonNode.get("Currencies").toString());
            List<Currency> currencies = objectMapper.readValue(jsonNode.get("Currencies").toString(),
             new TypeReference<List<Currency>>() {});

            System.out.println("Currencies: " + currencies.size());
            System.out.println(currencies.get(0).getDecimalDigits());
            System.out.println(currencies.get(0).getDecimalSeparator());
            System.out.println(currencies.get(0).getRoundingCoefficient());
            System.out.println(currencies.get(0).getSymbol());
            System.out.println(currencies.get(0).getCode());


            /*
            Store object into DB
             */
            Currency c1 = currencies.get(0);
            currencyRepository.save(c1);
            Optional<Currency> c1FromDB = currencyRepository.findByCode("ZAR");
            System.out.println(c1FromDB.isPresent());
            /*
            JSON Object from POJO
            */
            JSONObject c1Json = new JSONObject(c1);
            System.out.println(c1Json);

            /*
            Iterate through JSON without seralizing it
            */
            JSONArray ja = new JSONArray(jsonNode.get("Currencies").toString());
            System.out.println(ja.length());
            System.out.println(ja.get(0));
            JSONObject jo1 = ja.optJSONObject(0);
            System.out.println(jo1.get("Code"));
            jo1.keys().forEachRemaining(k -> {
                System.out.println(jo1.get(k));
            });

            for (int i=0; i<ja.length(); i++) {
                if (i % 15 == 0) {
                    System.out.println(ja.optJSONObject(i).get("Code"));
                }
            }

            /*
            Post first currency as JSON
             */
            HttpPost httpPost = new HttpPost("https://postman-echo.com/post");
            StringEntity entity = new StringEntity(ja.optJSONObject(0).toString());
            httpPost.setEntity(entity);
            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-type", "application/json");

            CloseableHttpResponse responsePost = client.execute(httpPost);
            System.out.println(responsePost.getStatusLine().getStatusCode());
            System.out.println(EntityUtils.toString(responsePost.getEntity()));


            client.close();

        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }


    }
}
