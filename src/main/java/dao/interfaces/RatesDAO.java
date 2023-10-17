package dao.interfaces;

import domain.Rates;

import java.util.Optional;

public interface RatesDAO {

  public Optional<Rates> getTodaysRate();

}
