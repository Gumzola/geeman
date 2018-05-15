package twitterfeed.core.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by gumani on 2018/05/14.
 */

@Entity
@Table(name = "twitt_config")
public class TwittConfig {

    private String configName;

    public TwittConfig() {
    }

    private String configuration;

    public TwittConfig(String configName, String configuration) {
        this.configName = configName;
        this.configuration = configuration;
    }

    @Id
    @Column(name = "config_name" )
    public String getConfigName() {
        return configName;
    }

    public void setConfigName(String configName) {
        this.configName = configName;
    }

    @Column(name = "configuration" )
    public String getConfiguration() {
        return configuration;
    }

    public void setConfiguration(String configuration) {
        this.configuration = configuration;
    }
}
