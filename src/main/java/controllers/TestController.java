package controllers;

import com.google.inject.Inject;
import domain.Rates;
import services.interfaces.RateService;

import java.util.List;

public class TestController {
  @Inject
  RateService currencyService;

  public Rates getAllRates(){
    return currencyService.getRates();
  }
}
