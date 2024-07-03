---
title: Understanding the Service Component
---
In the BroadleafCommerce-demo repository, a Service is a component that encapsulates business logic and operations. For instance, the `EmailService` interface provides methods for sending template and basic emails. Implementations of this interface, like `EmailServiceImpl`, provide the actual logic for these operations. Services are typically used throughout the application to perform operations that require interaction with the underlying data model.

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/email/service/EmailService.java" line="28">

---

# EmailService Interface

This is the EmailService interface. It declares three methods: `sendTemplateEmail` and `sendBasicEmail`. These methods are used to send emails in different formats and to different targets. Note that this interface is marked as deprecated, meaning it is no longer recommended for use and may be removed in future versions of the application.

```java
 * @deprecated in favor of {@link org.broadleafcommerce.common.notification.service.NotificationDispatcher}
 */
@Deprecated
public interface EmailService {

    public boolean sendTemplateEmail(String emailAddress, EmailInfo emailInfo,  Map<String,Object> props);

    public boolean sendTemplateEmail(EmailTarget emailTarget, EmailInfo emailInfo, Map<String,Object> props);

    public boolean sendBasicEmail(EmailInfo emailInfo, EmailTarget emailTarget, Map<String,Object> props);

}
```

---

</SwmSnippet>

# Email Service Endpoints

Email Service Endpoints

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/email/service/NullEmailServiceImpl.java" line="34">

---

## sendTemplateEmail

The `sendTemplateEmail` method is an endpoint of the EmailService. It is designed to send an email based on a predefined template. The method is overloaded to accept either a simple email address or an EmailTarget object, along with an EmailInfo object that contains the email content and a map of additional properties.

```java
    public boolean sendTemplateEmail(String emailAddress, EmailInfo emailInfo, Map<String, Object> props) {
        return true;
    }

    @Override
    public boolean sendTemplateEmail(EmailTarget emailTarget, EmailInfo emailInfo, Map<String, Object> props) {
        return true;
    }
```

---

</SwmSnippet>

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/email/service/NullEmailServiceImpl.java" line="44">

---

## sendBasicEmail

The `sendBasicEmail` method is another endpoint of the EmailService. It is designed to send a basic email without using a template. It accepts an EmailInfo object that contains the email content, an EmailTarget object that represents the recipient, and a map of additional properties.

```java
    public boolean sendBasicEmail(EmailInfo emailInfo, EmailTarget emailTarget, Map<String, Object> props) {
        return true;
    }
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
