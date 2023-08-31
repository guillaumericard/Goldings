import com.google.inject.Guice;
import com.google.inject.Injector;
import domain.Currency;
import guice.GuiceConfiguration;
import services.interfaces.CurrencyService;

import java.util.List;

public class Main {
  public static void main(String[] args) {
    Injector injector = Guice.createInjector(new GuiceConfiguration());
    CurrencyService currencyService = injector.getInstance(CurrencyService.class);
    List<Currency> currencies = currencyService.getCurrencies();
    currencies.forEach(System.out::println);
  }
}
