package org.mule.extension.mule.telegram.internal;

import org.mule.runtime.extension.api.annotation.*;
import org.mule.runtime.extension.api.annotation.Operations;
import org.mule.runtime.extension.api.annotation.connectivity.ConnectionProviders;
import org.mule.runtime.extension.api.annotation.param.Parameter;
import org.mule.runtime.extension.api.annotation.param.display.Placement;
import org.mule.runtime.extension.api.annotation.param.Optional;

/**
 * This class represents an extension configuration, values set in this class are commonly used across multiple
 * operations since they represent something core from the extension.
 */
@Operations(TelegramOperations.class)
@ConnectionProviders(TelegramConnectionProvider.class)
public class TelegramConfiguration {
  @Parameter
  private String token;

  @Parameter
  @Optional(defaultValue = "https://api.telegram.org")
  private String host;

  @Parameter
  @Optional(defaultValue = "443")
  private Integer port;

  @Parameter
  @Optional(defaultValue = "10000")
  private Integer timeout;


  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public Integer getTimeout() {
    return timeout;
  }

  public void setTimeout(Integer timeout) {
    this.timeout = timeout;
  }

  public String getHost() {
    return host;
  }

  public void setHost(String host) {
    this.host = host;
  }

  public Integer getPort() {
    return port;
  }

  public void setPort(Integer port) {
    this.port = port;
  }
}
