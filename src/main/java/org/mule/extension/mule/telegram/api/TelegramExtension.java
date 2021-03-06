package org.mule.extension.mule.telegram.internal;

import static org.mule.runtime.api.meta.Category.COMMUNITY;

import org.mule.runtime.extension.api.annotation.Extension;
import org.mule.runtime.extension.api.annotation.Configurations;
import org.mule.runtime.extension.api.annotation.dsl.xml.Xml;
import org.mule.runtime.extension.api.annotation.connectivity.ConnectionProviders;
import org.mule.runtime.extension.api.annotation.Operations;
import org.mule.runtime.extension.api.annotation.Sources;
import org.mule.extension.mule.telegram.internal.sources.UpdatesListener;

/**
 * This is the main class of an extension, is the entry point from which configurations, connection providers, operations
 * and sources are going to be declared.
 */
@Xml(prefix = "telegram")
@Extension(name = "Telegram", vendor = "Gaston Panizza", category = COMMUNITY)
@ConnectionProviders(TelegramConnectionProvider.class)
@Operations({TelegramOperations.class})
@Sources(UpdatesListener.class)
public class TelegramExtension {

}
