---
title: >-
  Employment of Java Message Service for Asynchronous Messaging in Broadleaf
  Commerce
---
This document will cover the use of Java Message Service (JMS) for asynchronous messaging in Broadleaf Commerce. We'll cover:

1. The role of JMS in Broadleaf Commerce
2. How JMS is used for email service
3. How JMS is used for content management

# The role of JMS in Broadleaf Commerce

Java Message Service (JMS) is used in Broadleaf Commerce for asynchronous messaging. This allows the application to handle requests and processes in a non-blocking manner, improving the overall performance and scalability of the application.

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/email/service/jms/JMSEmailServiceProducer.java" line="18">

---

# JMS for Email Service

The `JMSEmailServiceProducer` interface is part of the email service in Broadleaf Commerce. It uses JMS to handle the sending of emails in an asynchronous manner.

```java
package org.broadleafcommerce.common.email.service.jms;

import org.broadleafcommerce.common.email.service.message.EmailServiceProducer;
```

---

</SwmSnippet>

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/email/service/jms/JMSEmailServiceProducerImpl.java" line="18">

---

`JMSEmailServiceProducerImpl` is the implementation of the `JMSEmailServiceProducer` interface. It uses JMS to produce messages that represent emails to be sent.

```java
package org.broadleafcommerce.common.email.service.jms;

import org.broadleafcommerce.common.email.service.info.EmailInfo;
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/page/message/jms/JMSArchivedPagePublisher.java" line="18">

---

# JMS for Content Management

`JMSArchivedPagePublisher` is part of the content management system in Broadleaf Commerce. It uses JMS to publish messages about archived pages.

```java
package org.broadleafcommerce.cms.page.message.jms;

import org.broadleafcommerce.cms.page.domain.Page;
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/page/message/jms/JMSArchivedPageSubscriber.java" line="18">

---

`JMSArchivedPageSubscriber` is the counterpart to `JMSArchivedPagePublisher`. It uses JMS to subscribe to and handle messages about archived pages.

```java
package org.broadleafcommerce.cms.page.message.jms;

import javax.annotation.Resource;
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="follow-up"><sup>Powered by [Swimm](/)</sup></SwmMeta>
