package config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public final class Config {

  private static Config INSTANCE;

  private Map<String, Map<String,String> > config;
  @Getter
  private Api api;
  @Getter
  private Database db;

  private Config(){
    this.config = getConfigs();
    Map<String, String> configApi = config.get("api");
    this.api = Api.builder().key(configApi.get("key")).name(configApi.get("name")).url(configApi.get("url")).build();
    Map<String, String> configDb = config.get("db");
    this.db = Database.builder().connectionUrl(configDb.get("url")).build();
  }

  public static Config getInstance(){
    if (INSTANCE == null){
      synchronized (Config.class){
        if(INSTANCE == null){
          INSTANCE = new Config();
        }
      }
    }
    return INSTANCE;
  }

  private Map<String, Map<String,String>> getConfigs() {
    Map<String, Map<String,String>> config = new HashMap<>();
    try(InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("secrets.json")){
      ObjectMapper objectMapper = new ObjectMapper();
      config = objectMapper.readValue(in, Map.class);
    }catch(Exception e){
      throw new RuntimeException(e);
    }
    return config;
  }
}
