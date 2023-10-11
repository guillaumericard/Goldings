import com.google.inject.Guice;
import com.google.inject.Injector;
import controllers.TestController;
import guice.GuiceConfiguration;



public class Main {
  public static void main(String[] args) {
    Injector injector = Guice.createInjector(new GuiceConfiguration());
    TestController controller = injector.getInstance(TestController.class);
    System.out.println(controller.getAllRates().toString());

  }
}
