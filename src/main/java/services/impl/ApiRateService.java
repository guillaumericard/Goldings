package services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import config.Api;
import config.Config;
import dao.interfaces.RatesDAO;
import domain.Rates;
import services.interfaces.DbService;
import services.interfaces.RateService;

import javax.inject.Inject;
import java.io.IOException;
import java.sql.*;


public class ApiRateService implements RateService {

  @Inject
  DbService dbService;
  @Inject
  RatesDAO ratesDAO;

  public Rates getRates() {
    Api api = Config.getInstance().getApi();
    Rates rates = null;
    ObjectMapper mapper = new ObjectMapper();
    try {
      rates = ratesDAO.getTodaysRate().orElseThrow(IOException::new);
    } catch (IOException e) {
      e.printStackTrace();
    }
/*    try (CloseableHttpClient client = HttpClients.createDefault()) {

      HttpGet request = new HttpGet(api.getUrl() + api.getKey());
      Rates response = client.execute(request, httpResponse ->
          mapper.readValue(httpResponse.getEntity().getContent(), Rates.class));
      rates = response;

    } catch (IOException e){
      e.printStackTrace();
    }*/

    return rates;
  }

}
