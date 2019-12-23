# Telegram Connector
This is a custom community connector for Mule 4 built with the Java SDK. Using this you can:
  - Send messages given a chat id
  - Receive updates from a specific chat

### Why?
Messaging applications have expanded their use in an unimaginable way, which means that more and more people choose this kind of tools to receive periodical notifications or create bots that serve different daily purposes.
The most common use for this kind of connectors is to be used in a notification framework, where you can notify from status and results of processes/integrations to business events that are of interest or require an action. Another possibility is to implement a Bot, which listens to chat commands and interacts with the sender of the message.

### How?
This connector invokes the methods exposed by the Telegram REST API (api.telegram.org) and abstracts the complexity of configuration and use exposing Mule operations.

### Usage
#### Development & publishing Commands 
The following Maven commands are required during development phase

| Task | Command |
| ------ | ------ |
| Package connector and local installation | mvn clean install |
| Publish to Exchange | mvn deploy (require maven distribution config)|

#### Development in Studio 
- Add this dependency to your application pom.xml
```
<groupId>com.mulesoft.connectors</groupId>
<artifactId>mule-telegram-connector</artifactId>
<version>1.0.0</version>
<classifier>mule-plugin</classifier>
```
- Drag and drop from the Mule Palette
- Create Telegram connection configuration
- Configure the operation

#### Operations

#### Send Message
This operation sends a message to a certain chat ID. Both parameters that this operation receives, chat id and payload (message), accept flat values and dataweave expressions. The only thing that needs to be taken into account is that the message must contain a text-based mimetype (i.e. "text/plain"), but the connector will try to parse the structure of the message sent using getters and setters that are not defined within the operation because they depend on the message.


#### Get Updates
This operation serves to obtain all the updates of a certain chat and is based on a polling source, which means that every X time, whether for a fixed polling value or a given cron expression, the connector will obtain the updates. This operation supports watermarking, simply by activating the "watermark" option in the operation. This watermark is based on the update_id provided by the Telegram API and cannot be modified to add another criterion. Unlike other watermark mechanisms, it does not retrieve all records and then filters the desired ones, but uses the paging query parameters offered by Telegram.



### Used Dependencies
This connector relies in the Mule HTTP module to perform the HTTP interactions with the Telegram API, however, other library is used: org.json. It is used to handle the HTTP response and to extract useful information for operations, such as the watermarking implemented in the get updates operation.

### Contribution

Want to contribute? Great!

Just fork the repo, make your updates and open a pull request!

### Todos

 - Write Tests
 - Provide Usage Examples
 - Add capabilities, like send voicee or send video operations.
