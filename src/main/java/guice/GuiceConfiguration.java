package guice;

import com.google.inject.AbstractModule;
import services.impl.ApiRateService;
import services.interfaces.RateService;

public class GuiceConfiguration extends AbstractModule {
  @Override
  protected void configure(){
    bind(RateService.class).to(ApiRateService.class);
  }
}
