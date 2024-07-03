---
title: Overview of Page Message
---
Page Message in BroadleafCommerce-demo refers to a mechanism for handling messages related to pages in the content management system. It is part of the Broadleaf Commerce Community Edition, an e-commerce framework. The 'Page Message' functionality is implemented in the 'org.broadleafcommerce.cms.page.message' package. It includes classes like 'ArchivedPagePublisher' and 'JMSArchivedPageSubscriber' that handle publishing and subscribing to page-related messages respectively. These messages are used to perform operations like invalidating cache keys for pages.

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/page/message/ArchivedPagePublisher.java" line="22">

---

# ArchivedPagePublisher Interface

The `ArchivedPagePublisher` interface defines the contract for handling page archive events. The `processPageArchive` method is called when a page is archived.

```java
/**
 * The ArchivedPagePublisher will be notified when a page has
 * been marked as archived.    This provides a convenient cache-eviction
 * point for pages in production.
 *
 * Implementers of this service could send a JMS or AMQP message so
 * that other VMs can evict the item.
 *
 * Created by bpolster.
 */
public interface ArchivedPagePublisher {
    void processPageArchive(Page page, String basePageKey);
}
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/page/message/jms/JMSArchivedPagePublisher.java" line="42">

---

# JMSArchivedPagePublisher Implementation

The `JMSArchivedPagePublisher` class is an implementation of the `ArchivedPagePublisher` interface. It uses Java Message Service (JMS) to handle the page archive event.

```java
public class JMSArchivedPagePublisher implements ArchivedPagePublisher {
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/page/message/jms/JMSArchivedPageSubscriber.java" line="44">

---

# JMSArchivedPageSubscriber Class

The `JMSArchivedPageSubscriber` class listens for page archive messages. When a message is received, the `onMessage` method is called, which retrieves the page cache key from the message.

```java
    public void onMessage(Message message) {
        String basePageCacheKey = null;
        try {
            basePageCacheKey = ((TextMessage) message).getText();
        } catch (JMSException e) {
            throw new RuntimeException(e);
        }
    }
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
