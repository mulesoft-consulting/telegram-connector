package org.mule.extension.mule.telegram.internal;

import static org.mule.runtime.extension.api.annotation.param.MediaType.ANY;
import org.mule.runtime.extension.api.annotation.param.MediaType;
import org.mule.runtime.extension.api.annotation.param.Config;
import org.mule.runtime.extension.api.annotation.param.Connection;
import org.mule.runtime.extension.api.annotation.Alias;
import java.io.InputStream;

/**
 * This class is a container for operations, every public method in this class will be taken as an extension operation.
 */
public class TelegramOperations {

  @MediaType(value = ANY, strict = false)
  public InputStream sendMessage(@Connection TelegramConnection connection,  @Alias("chatID") String chatId, @Alias("payload") String message) {
    return connection.sendMessage(chatId, message);
  }

}
