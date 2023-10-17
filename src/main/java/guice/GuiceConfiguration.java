package guice;

import com.google.inject.AbstractModule;
import dao.impl.RatesDAOImplementation;
import dao.interfaces.RatesDAO;
import services.impl.ApiRateService;
import services.impl.SQLiteService;
import services.interfaces.DbService;
import services.interfaces.RateService;

public class GuiceConfiguration extends AbstractModule {
  @Override
  protected void configure(){
    bind(RatesDAO.class).to(RatesDAOImplementation.class);
    bind(DbService.class).to(SQLiteService.class);
    bind(RateService.class).to(ApiRateService.class);
  }
}
