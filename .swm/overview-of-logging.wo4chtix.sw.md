---
title: Overview of Logging
---
Logging in BroadleafCommerce-demo is implemented using the Apache Commons Logging library, which provides a simple and flexible logging framework. The `SupportLogger` class is a custom logger that provides support for a new SUPPORT log level, independent of any configured logging framework. It is used throughout the codebase to log various events and states. The `ProcessDetailLogger` class is another specialized logger designed to handle detailed production logging for complex interactions. It is separate from the standard system logging to avoid noise in the system logs. The `ModuleLifecycleLoggingBean` class is used to log lifecycle events of modules. The logging configuration is typically done in the logback.xml file or other logging system config files.

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/logging/SupportLogger.java" line="24">

---

# SupportLogger Class

The SupportLogger class provides support for a new SUPPORT log level, in addition to the standard log levels. It can be instantiated with a module name and logger name, and used to log messages at various levels. The support method is used to log support level messages.

```java
/**
 * <p>SupportLogger class that provides support for the new SUPPORT log level type.
 * The SUPPORT log level is independent of any configured logging framework and should be able to be configured independently.</p>
 *
 * <p>This Logger was originally built as an extension to Log4j's {@link org.apache.log4j.Logger}. As a result,
 * other levels must be supported to maintain backwards compatibility.</p>
 *
 * <p>It is important to note that the SupportLogger can be called outside a Spring Context.
 * Therefore, it is possible to instantiate a different SupportLogger adapter using
 * the fully qualified class name of an implementation using a System Property.
 * By default, it will instantiate a {@link SystemSupportLoggerAdapter} if none is specified.
 * For example, you may wish to disable all logs made to the Support Logger by setting the following System Property:
 * </p>
 *
 * <ul>
 * <li><code>-DSupportLogger.adapter.fqcn=org.broadleafcommerce.common.logging.DisableSupportLoggerAdapter</code></li>
 * </ul>
 *
 * <p>
 * The main requirements for SUPPORT level logging are to:
 * <ul>
```

---

</SwmSnippet>

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/logging/SupportLogManager.java" line="38">

---

# Using SupportLogger

The SupportLogManager class provides a getLogger method to retrieve a SupportLogger instance. This method takes a module name and logger name as parameters.

```java
    public static SupportLogger getLogger(final String moduleName, String name) {
        return new SupportLogger(moduleName, name);
    }
```

---

</SwmSnippet>

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/logging/ProcessDetailLogger.java" line="71">

---

# ProcessDetailLogger Class

The ProcessDetailLogger class is a specialized logger for detailed production logging of complex interactions. It uses a SupportLogger instance for logging.

```java
public class ProcessDetailLogger {

    protected static final SupportLogger LOGGER = SupportLogManager.getLogger("ProcessLogging", ProcessDetailLogger.class);

    protected Log processDetailLog;

    protected String logIdentifier;

    /**
     * Max number of members that will output in the log for a collection or array member passed as a template variable
     */
    protected int listTemplateVariableMaxMemberCount = 30;

    /**
     * Max length of any String passed as a template variable
     */
    protected int stringTemplateVariableMaxLength = 200;

    @Value("${ignore.no.process.detail.logger.configuration:false}")
    protected boolean ignoreNoProcessDetailLoggerConfiguration;

```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
