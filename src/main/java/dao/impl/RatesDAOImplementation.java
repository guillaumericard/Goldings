package dao.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import config.Api;
import config.Config;
import dao.interfaces.RatesDAO;
import domain.Rates;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import services.interfaces.DbService;
import services.interfaces.RateService;

import javax.inject.Inject;
import javax.xml.transform.Result;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Optional;

public class RatesDAOImplementation implements RatesDAO {

  @Inject
  DbService dbService;
  @Inject
  RateService rateService;

  @Override
  public Optional<Rates> getTodaysRate() {
    Optional<Rates> rates = Optional.empty();
    Connection con = dbService.getConnection();
    try {
      PreparedStatement statement = con.prepareStatement("select * from Rates where date = DATE();");
      ResultSet response = statement.executeQuery();

      if (!response.next()) {
        rates = getTodaysRateAndInsertDb(rates, con);
      } else {
          do {
            if (response.isFirst()) {
              rates = Optional.of(Rates.builder()
                .base(response.getString("base"))
                .date(response.getString("date"))
                .rates(new HashMap<>())
                .build());
            }
            if(rates.isPresent()) {
              rates.get().getRates().put(response.getString("currency"), response.getString("value"));
            }
          } while (response.next());
      }
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
    return rates;
  }

  // privates methods

  private Optional<Rates>getTodaysRateAndInsertDb(Optional<Rates> rates, Connection con){
    Api api = Config.getInstance().getApi();
    ObjectMapper mapper = new ObjectMapper();

    try (CloseableHttpClient client = HttpClients.createDefault()) {

      HttpGet request = new HttpGet(api.getUrl() + api.getKey());
      rates = Optional.of(client.execute(request, httpResponse ->
          mapper.readValue(httpResponse.getEntity().getContent(), Rates.class)));
      Rates ratesFromApi = rates.get();
      ratesFromApi.getRates().forEach((currency,value) -> {
        try {
          PreparedStatement insertStatement = con.prepareStatement("insert into Rates(base,date,currency,value) values(?,?,?,?)");
          insertStatement.setString(1,ratesFromApi.getBase());
          insertStatement.setString(2,ratesFromApi.getDate());
          insertStatement.setString(3,currency);
          insertStatement.setString(4,value);
          insertStatement.executeUpdate();
        } catch (SQLException throwables) {
          throwables.printStackTrace();
        }
      });

    } catch (IOException e){
      e.printStackTrace();
    }
    return rates;
  }
}
