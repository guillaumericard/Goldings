package services.impl;

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
import java.io.IOException;
import java.sql.*;
import java.util.Optional;


public class ApiRateService implements RateService {

  @Inject
  RatesDAO ratesDAO;

  public Rates getRates() {
    return ratesDAO.getTodaysRate().get();
  }

}
