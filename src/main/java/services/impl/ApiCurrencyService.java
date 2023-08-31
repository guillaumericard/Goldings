package services.impl;

import domain.Currency;
import services.interfaces.CurrencyService;

import java.util.ArrayList;
import java.util.List;

public class ApiCurrencyService implements CurrencyService {

  public List<Currency> getCurrencies(){
    List<Currency> currencies = new ArrayList<Currency>();
    Currency currency = new Currency("Dollar", "Canada", "CAD");
    currencies.add(currency);
    return currencies;
  }

}
