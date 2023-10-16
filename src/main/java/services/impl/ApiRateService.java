package services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import config.Api;
import config.Config;
import domain.Rates;
import services.interfaces.DbService;
import services.interfaces.RateService;

import javax.inject.Inject;
import java.sql.*;


public class ApiRateService implements RateService {

  @Inject
  DbService dbService;

  public Rates getRates() {
    Api api = Config.getInstance().getApi();
    Rates rates = null;
    ObjectMapper mapper = new ObjectMapper();

    Connection con = dbService.getConnection();
    try {
      PreparedStatement statement = con.prepareStatement("select * from Rates");
      ResultSet response = statement.executeQuery();
      while(response.next()){
        rates = Rates.builder()
            .base(response.getString("base"))
            .date(response.getString("date"))
            .currency(response.getString("currency"))
            .build();
        System.out.println(rates);
      }
    } catch (SQLException throwables) {
      throwables.printStackTrace();
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
