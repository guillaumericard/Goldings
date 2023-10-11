package services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import config.Api;
import config.Config;
import domain.Rates;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import services.interfaces.RateService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ApiRateService implements RateService {



  public Rates getRates() {
    Api api = Config.getInstance().getApi();
    Rates rates = null;
    ObjectMapper mapper = new ObjectMapper();
    try (CloseableHttpClient client = HttpClients.createDefault()) {

      HttpGet request = new HttpGet(api.getUrl() + api.getKey());
      Rates response = client.execute(request, httpResponse ->
          mapper.readValue(httpResponse.getEntity().getContent(), Rates.class));
      rates = response;

    } catch (IOException e){
      e.printStackTrace();
    }

    return rates;
  }

}
