---
title: Exploring the Construction and Dispatch of Different Notification Types
---
This document will cover the specifics of different notification types (Email, SMS) in the BroadleafCommerce-demo repository. We'll cover:

1. How different notification types are defined
2. How notifications are constructed
3. How notifications are dispatched.

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/notification/service/type/NotificationEventType.java" line="27">

---

# Notification Types Definition

The `NotificationEventType` class defines different types of notifications. Each type is represented as a static instance of `NotificationEventType`. The type and friendly type of the notification are set in the constructor.

```java
 * @author Nick Crum ncrum
 */
@Component
public class NotificationEventType implements Serializable, BroadleafEnumerationType {
    private static final long serialVersionUID = 1L;

    private static final Map<String, NotificationEventType> TYPES = new LinkedHashMap<String, NotificationEventType>();

    public static final NotificationEventType ADMIN_FORGOT_PASSWORD = new NotificationEventType("ADMIN_FORGOT_PASSWORD", "Admin Forgot Password");
    public static final NotificationEventType ADMIN_FORGOT_USERNAME = new NotificationEventType("ADMIN_FORGOT_USERNAME", "Admin Forgot Username");
    public static final NotificationEventType ORDER_CONFIRMATION = new NotificationEventType("ORDER_CONFIRMATION", "Order Confirmation");
    public static final NotificationEventType FORGOT_PASSWORD = new NotificationEventType("FORGOT_PASSWORD", "Forgot Password");
    public static final NotificationEventType FORGOT_USERNAME = new NotificationEventType("FORGOT_USERNAME", "Forgot Username");
    public static final NotificationEventType REGISTER_CUSTOMER = new NotificationEventType("REGISTER_CUSTOMER", "Register Customer");
    public static final NotificationEventType CONTACT_US = new NotificationEventType("CONTACT_US", "Contact Us");
    public static final NotificationEventType NOTIFY_ABANDONED_CART = new NotificationEventType("NOTIFY_ABANDONED_CART", "Notify Abandoned Cart");

    public static NotificationEventType getInstance(final String type) {
        return TYPES.get(type);
    }

```

---

</SwmSnippet>

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/notification/service/type/Notification.java" line="27">

---

# Constructing Notifications

The `Notification` class is the base class for all notifications. It has a `notificationType` field and a `context` map to hold additional data. The `setType` method is used to set the type of the notification.

```java
public abstract class Notification implements Serializable {

    protected String notificationType;
    protected Map<String, Object> context = new HashMap<>();

    public Notification() {
    }

    public Notification(NotificationEventType notificationEventType, Map<String, Object> context) {
        this.context = context;
        setType(notificationEventType);
    }

    public Map<String, Object> getContext() {
        return context;
    }

    public void setContext(Map<String, Object> context) {
        this.context = context;
    }

```

---

</SwmSnippet>

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/notification/service/type/EmailNotification.java" line="30">

---

The `EmailNotification` class extends `Notification` and adds an `emailAddress` field. It has constructors to create an instance with a notification event type and context, and optionally an email address.

```java
    protected String emailAddress;
    protected List<Attachment> attachments = new ArrayList<Attachment>();

    public EmailNotification() {
        super();
    }

    public EmailNotification(NotificationEventType notificationEventType, Map<String, Object> context) {
        super(notificationEventType, context);
    }

    public EmailNotification(String emailAddress, NotificationEventType notificationEventType, Map<String, Object> context) {
        super(notificationEventType, context);
        this.emailAddress = emailAddress;
    }

    public String getEmailAddress() {
        return emailAddress;
```

---

</SwmSnippet>

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/notification/service/type/SMSNotification.java" line="30">

---

The `SMSNotification` class extends `Notification` and adds a `phoneNumber` field. It has constructors to create an instance with a notification event type and context, and optionally a phone number.

```java
        super();
    }

    public SMSNotification(NotificationEventType notificationEventType, Map<String, Object> context) {
        super(notificationEventType, context);
    }

    public SMSNotification(String phoneNumber, NotificationEventType notificationEventType, Map<String, Object> context) {
        super(notificationEventType, context);
```

---

</SwmSnippet>

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/notification/service/NotificationDispatcher.java" line="26">

---

# Dispatching Notifications

The `NotificationDispatcher` interface defines the `dispatchNotification` method, which is responsible for dispatching the given notification to any relevant services.

```java
public interface NotificationDispatcher {

    /**
     * This method is responsible for dispatching the given notification to any relevant services.
     *
     * @param notification the Notification
     */
    void dispatchNotification(Notification notification)  throws ServiceException;
}
```

---

</SwmSnippet>

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/notification/service/NotificationDispatcherImpl.java" line="32">

---

The `NotificationDispatcherImpl` class implements the `NotificationDispatcher` interface. The actual implementation of the `dispatchNotification` method would be in this class.

```java
@Service("blNotificationDispatcher")
public class NotificationDispatcherImpl implements NotificationDispatcher {
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="follow-up"><sup>Powered by [Swimm](/)</sup></SwmMeta>
