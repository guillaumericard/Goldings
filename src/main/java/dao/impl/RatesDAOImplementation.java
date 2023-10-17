package dao.impl;

import dao.interfaces.RatesDAO;
import domain.Rates;
import services.interfaces.DbService;

import javax.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Optional;

public class RatesDAOImplementation implements RatesDAO {

  @Inject
  DbService dbService;

  @Override
  public Optional<Rates> getTodaysRate() {
    Optional<Rates> rates = Optional.empty();
    Connection con = dbService.getConnection();
    try {
      PreparedStatement statement = con.prepareStatement("select * from Rates where date = DATE();");
      ResultSet response = statement.executeQuery();
      while(response.next()){
        if(response.isFirst()){
          rates = Optional.of(Rates.builder()
              .base(response.getString("base"))
              .date(response.getString("date"))
              .rates(new HashMap<>())
              .build());
        }
        rates.get().getRates().put(response.getString("currency"), response.getString("value"));
      }
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
    return rates;
  }
}
