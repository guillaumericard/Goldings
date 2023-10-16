package controllers;

import com.google.inject.Inject;
import domain.Rates;
import services.interfaces.RateService;

public class TestController {
  @Inject
  RateService rateService;

  public Rates getAllRates(){
    return rateService.getRates();
  }
}
