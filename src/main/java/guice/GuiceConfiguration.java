package guice;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;
import services.impl.ApiCurrencyService;
import services.interfaces.CurrencyService;

public class GuiceConfiguration extends AbstractModule {
  @Override
  protected void configure(){
    bind(CurrencyService.class).to(ApiCurrencyService.class);
  }
}
