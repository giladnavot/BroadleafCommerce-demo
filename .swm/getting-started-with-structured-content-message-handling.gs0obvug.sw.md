---
title: Getting Started with Structured Content Message Handling
---
Structured Content Message Handling in BroadleafCommerce-demo refers to the process of managing messages related to structured content. This is achieved through the use of Java Message Service (JMS) for asynchronous message passing. The `JMSArchivedStructuredContentPublisher` class is a key component in this process. It is designed to notify other Virtual Machines (VMs) when a structured content needs to be evicted from cache, typically when the content is archived. This is done by sending a message containing the name and type keys of the structured content to a specified destination.

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/structure/message/jms/JMSArchivedStructuredContentPublisher.java" line="43">

---

# JMSArchivedStructuredContentPublisher Class

This is the main class for Structured Content Message Handling. It implements the `ArchivedStructuredContentPublisher` interface and uses the `JmsTemplate` and `Destination` objects to send JMS messages.

```java
public class JMSArchivedStructuredContentPublisher implements ArchivedStructuredContentPublisher {

    private JmsTemplate archiveStructuredContentTemplate;

    private Destination archiveStructuredContentDestination;

    @Override
    public void processStructuredContentArchive(final StructuredContent sc, final String baseNameKey, final String baseTypeKey) {
        archiveStructuredContentTemplate.send(archiveStructuredContentDestination, new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                HashMap<String, String> objectMap = new HashMap<String,String>(2);
                objectMap.put("nameKey", baseNameKey);
                objectMap.put("typeKey", baseTypeKey);
                return session.createObjectMessage(objectMap);
            }
        });
    }

    public JmsTemplate getArchiveStructuredContentTemplate() {
        return archiveStructuredContentTemplate;
    }
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/structure/message/jms/JMSArchivedStructuredContentPublisher.java" line="50">

---

# processStructuredContentArchive Method

This method is used to send a JMS message when a structured content needs to be archived. It creates a `MessageCreator` with the details of the structured content and sends it to the configured destination.

```java
    public void processStructuredContentArchive(final StructuredContent sc, final String baseNameKey, final String baseTypeKey) {
        archiveStructuredContentTemplate.send(archiveStructuredContentDestination, new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                HashMap<String, String> objectMap = new HashMap<String,String>(2);
                objectMap.put("nameKey", baseNameKey);
                objectMap.put("typeKey", baseTypeKey);
                return session.createObjectMessage(objectMap);
            }
        });
    }
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/structure/message/jms/JMSArchivedStructuredContentPublisher.java" line="52">

---

# createMessage Method

This method is used to create a JMS message with the details of the structured content to be archived. It creates a `HashMap` with the `nameKey` and `typeKey` of the structured content and returns a `ObjectMessage` with this map.

```java
            public Message createMessage(Session session) throws JMSException {
                HashMap<String, String> objectMap = new HashMap<String,String>(2);
                objectMap.put("nameKey", baseNameKey);
                objectMap.put("typeKey", baseTypeKey);
                return session.createObjectMessage(objectMap);
            }
```

---

</SwmSnippet>

# Structured Content Message Handling

The `JMSArchivedStructuredContentPublisher` class is primarily responsible for handling structured content messages. It uses the Java Message Service (JMS) to send messages about structured content that has been archived.

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/structure/message/jms/JMSArchivedStructuredContentPublisher.java" line="50">

---

## processStructuredContentArchive

The `processStructuredContentArchive` function is used to send a message when a structured content is archived. It takes in the structured content, a base name key, and a base type key as parameters. It uses the `archiveStructuredContentTemplate` to send a message to the `archiveStructuredContentDestination`. The message is created using a `MessageCreator` which creates a `HashMap` with the base name key and base type key, and then creates an object message with this map.

```java
    public void processStructuredContentArchive(final StructuredContent sc, final String baseNameKey, final String baseTypeKey) {
        archiveStructuredContentTemplate.send(archiveStructuredContentDestination, new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                HashMap<String, String> objectMap = new HashMap<String,String>(2);
                objectMap.put("nameKey", baseNameKey);
                objectMap.put("typeKey", baseTypeKey);
                return session.createObjectMessage(objectMap);
            }
        });
    }
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
