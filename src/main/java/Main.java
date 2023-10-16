import com.google.inject.Guice;
import com.google.inject.Injector;
import controllers.TestController;
import domain.Rates;
import guice.GuiceConfiguration;
import services.interfaces.DbService;

import java.sql.Connection;
import java.sql.SQLException;


public class Main {
  public static void main(String[] args) throws SQLException {
    Injector injector = Guice.createInjector(new GuiceConfiguration());
    TestController controller = injector.getInstance(TestController.class);
    Rates rates = controller.getAllRates();
  }
}
