package gorest.configuration;

import lombok.SneakyThrows;

import java.io.InputStream;
import java.util.Properties;

public class Configuration {

    private Properties prop;

    @SneakyThrows
    private void load() {
        prop = new Properties();
        InputStream stream = getClass().getClassLoader().getResourceAsStream("property/config.properties");
        prop.load(stream);
    }

    public String getValue(Options option) {
        if (prop == null) {
            load();
        }
        return prop.getProperty(option.getValue());
    }

    public enum Options {

        BASE_PATH("base.path");

        private final String value;

        Options(String value) {
            this.value = value;
        }

        String getValue() {
            return value;
        }
    }

}
