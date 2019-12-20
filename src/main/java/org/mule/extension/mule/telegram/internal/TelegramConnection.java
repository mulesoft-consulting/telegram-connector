package org.mule.extension.mule.telegram.internal;


import org.mule.runtime.http.api.HttpConstants;
import org.mule.runtime.http.api.HttpService;
import org.mule.runtime.http.api.client.HttpClient;
import org.mule.runtime.http.api.client.HttpClientConfiguration;
import org.mule.runtime.http.api.client.proxy.ProxyConfig;
import org.mule.runtime.http.api.domain.entity.HttpEntity;
import org.mule.runtime.http.api.domain.message.request.HttpRequest;
import org.mule.runtime.http.api.domain.message.request.HttpRequestBuilder;
import org.mule.runtime.http.api.domain.message.response.HttpResponse;
import org.mule.runtime.api.util.MultiMap;
import java.util.concurrent.TimeoutException;
import java.io.IOException;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class represents an extension connection just as example (there is no real connection with anything here c:).
 */
public final class TelegramConnection {

   private static final Logger LOGGER = LoggerFactory.getLogger(TelegramConnection.class);
   private TelegramConfiguration genConfig;
   private HttpClient httpClient;
   private HttpRequestBuilder httpRequestBuilder;

  public TelegramConnection(HttpService httpService, TelegramConfiguration gConfig) {
    genConfig = gConfig;
    initHttpClient(httpService);
  }

  public void initHttpClient(HttpService httpService){
    HttpClientConfiguration.Builder builder = new HttpClientConfiguration.Builder();
    builder.setName("telegram");
    httpClient = httpService.getClientFactory().create(builder.build());
    httpRequestBuilder = HttpRequest.builder();
    httpClient.start();
  }

  public void invalidate() {
    httpClient.stop();
  }

  public boolean isConnected() throws Exception{
    return true;
  }

  public InputStream sendMessage(String chatId, String message){
    HttpResponse httpResponse = null;
    String strUri = genConfig.getHost()+":"+genConfig.getPort()+"/bot"+genConfig.getToken()+"/sendMessage";

    MultiMap<String, String> qParams = new MultiMap<String, String>();
    qParams.put("chat_id", chatId);
    qParams.put("text", message);

    HttpRequest request = httpRequestBuilder
            .method("GET")
            .uri(strUri)
            .queryParams(qParams)
            .build();

    try {
      httpResponse = httpClient.send(request,genConfig.getTimeout(),false,null);
      return httpResponse.getEntity().getContent();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (TimeoutException e) {
      e.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  public InputStream getUpdates(String chatId, boolean watermark, String lastUpdateId){
      HttpResponse httpResponse = null;
      String strUri = genConfig.getHost()+":"+genConfig.getPort()+"/bot"+genConfig.getToken()+"/getUpdates";

      MultiMap<String, String> qParams = new MultiMap<String, String>();
      qParams.put("chat_id", chatId);
      if(watermark && lastUpdateId != null) {
          qParams.put("offset", lastUpdateId);
      }

      HttpRequest request = httpRequestBuilder
              .method("GET")
              .uri(strUri)
              .queryParams(qParams)
              .build();
      try {
        httpResponse = httpClient.send(request,genConfig.getTimeout(),false,null);
        return httpResponse.getEntity().getContent();
      } catch (IOException e) {
        e.printStackTrace();
      } catch (TimeoutException e) {
        e.printStackTrace();
      } catch (Exception e) {
        e.printStackTrace();
      }
      return null;
  }


}
