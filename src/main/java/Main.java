import com.google.inject.Guice;
import com.google.inject.Injector;
import controllers.TestController;
import guice.GuiceConfiguration;

import java.util.Map;


public class Main {
  public static void main(String[] args) {
    Injector injector = Guice.createInjector(new GuiceConfiguration());
    //TestController controller = injector.getInstance(TestController.class);
    //controller.getAllCurrencies().forEach(System.out::println);
    Map<String, String> envMap = System.getenv();
    System.out.println(envMap.get("GALAXQAM"));
  }
}
