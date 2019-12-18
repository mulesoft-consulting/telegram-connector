package org.mule.extension.mule.telegram.internal;

import java.io.IOException;
import javax.inject.Inject;
import org.mule.runtime.api.connection.ConnectionException;
import org.mule.runtime.extension.api.annotation.param.Parameter;
import org.mule.runtime.extension.api.annotation.param.Optional;
import org.mule.runtime.api.connection.ConnectionValidationResult;
import org.mule.runtime.api.connection.PoolingConnectionProvider;
import org.mule.runtime.api.connection.ConnectionProvider;
import org.mule.runtime.api.connection.CachedConnectionProvider;
import org.mule.runtime.extension.api.annotation.param.display.DisplayName;
import org.mule.runtime.http.api.HttpConstants;
import org.mule.runtime.http.api.HttpService;
import org.mule.runtime.http.api.client.HttpClient;
import org.mule.runtime.http.api.client.HttpClientConfiguration;
import org.mule.runtime.http.api.client.proxy.ProxyConfig;
import org.mule.runtime.http.api.domain.entity.HttpEntity;
import org.mule.runtime.http.api.domain.message.request.HttpRequest;
import org.mule.runtime.http.api.domain.message.request.HttpRequestBuilder;
import org.mule.runtime.http.api.domain.message.response.HttpResponse;

import org.mule.runtime.extension.api.annotation.param.ParameterGroup;


/**
 * This class (as it's name implies) provides connection instances and the funcionality to disconnect and validate those
 * connections.
 * <p>
 * All connection related parameters (values required in order to create a connection) must be
 * declared in the connection providers.
 * <p>
 * This particular example is a {@link PoolingConnectionProvider} which declares that connections resolved by this provider
 * will be pooled and reused. There are other implementations like {@link CachedConnectionProvider} which lazily creates and
 * caches connections or simply {@link ConnectionProvider} if you want a new connection each time something requires one.
 */
public class TelegramConnectionProvider implements PoolingConnectionProvider<TelegramConnection> {

  private HttpClient httpClient;
  private HttpRequestBuilder httpRequestBuilder;

  @ParameterGroup(name = "Connection")
  TelegramConfiguration config;

  @Inject
  private HttpService httpService;

  @Override
  public TelegramConnection connect() throws ConnectionException {
    return new TelegramConnection(httpService, config);
  }

  @Override
  public void disconnect(TelegramConnection connection) {
    try {
      connection.invalidate();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public ConnectionValidationResult validate(TelegramConnection connection) {
    ConnectionValidationResult result;
      try {
          if(connection.isConnected()){
              result = ConnectionValidationResult.success();
          } else {
              result = ConnectionValidationResult.failure("Connection Failed", new Exception());
          }
      } catch (Exception e) {
        result = ConnectionValidationResult.failure("Connection Failed", new Exception());
      }
      return result;
  }

}
