package domain;

public class Currency {
  String name;
  String country;
  String symbol;

  public Currency(String name, String country, String symbol){
    this.name = name;
    this.country = country;
    this.symbol = symbol;
  }

  public String toString(){
    return "Currency : { \n name : " + name + ",\n country : " + country + ",\n symbol : " + symbol + "\n}";
  }
}
