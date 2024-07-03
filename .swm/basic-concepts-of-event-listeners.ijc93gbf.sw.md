---
title: Basic Concepts of Event Listeners
---
Event Listeners in BroadleafCommerce-demo are components that respond to specific events occurring within the application. They are used to handle events such as a user forgetting their username or password. When such an event occurs, the corresponding event listener is triggered, executing predefined logic. For instance, the `AdminNotificationForgotUsernameEventListener` and `AdminNotificationForgotPasswordEventListener` classes handle events related to forgotten usernames and passwords respectively. They create a context and dispatch notifications to the user via email or SMS.

<SwmSnippet path="/admin/broadleaf-admin-module/src/main/java/org/broadleafcommerce/admin/event/AdminNotificationForgotUsernameEventListener.java" line="40">

---

## Event Listener Classes

Here is an example of an Event Listener class. This class listens for `AdminForgotUsernameEvent` events and handles them by sending an email and SMS notification to the user.

```java
@Component("blAdminNotificationForgotUsernameEventListener")
public class AdminNotificationForgotUsernameEventListener extends AbstractBroadleafApplicationEventListener<AdminForgotUsernameEvent> {

    public static final String ACTIVE_USERNAMES_CONTEXT_KEY = "activeUsernames";
    protected final Log LOG = LogFactory.getLog(AdminNotificationForgotUsernameEventListener.class);

    @Autowired
    @Qualifier("blNotificationDispatcher")
    protected NotificationDispatcher notificationDispatcher;

    @Override
    protected void handleApplicationEvent(AdminForgotUsernameEvent event) {
        Map<String, Object> context = createContext(event);

        try {
            notificationDispatcher.dispatchNotification(new EmailNotification(event.getEmailAddress(), NotificationEventType.ADMIN_FORGOT_USERNAME, context));
        } catch (ServiceException e) {
            if (LOG.isDebugEnabled()) {
                LOG.debug("Unable to send an admin forgot username email for " + event.getEmailAddress(), e);
            }
        }
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-admin-module/src/main/java/org/broadleafcommerce/admin/event/AdminNotificationForgotUsernameEventListener.java" line="51">

---

## Handling Events

The `handleApplicationEvent` method is where the logic for handling the event is defined. In this case, it creates a context map and uses the `NotificationDispatcher` to send notifications.

```java
    protected void handleApplicationEvent(AdminForgotUsernameEvent event) {
        Map<String, Object> context = createContext(event);

        try {
            notificationDispatcher.dispatchNotification(new EmailNotification(event.getEmailAddress(), NotificationEventType.ADMIN_FORGOT_USERNAME, context));
        } catch (ServiceException e) {
            if (LOG.isDebugEnabled()) {
                LOG.debug("Unable to send an admin forgot username email for " + event.getEmailAddress(), e);
            }
        }

        try {
            notificationDispatcher.dispatchNotification(new SMSNotification(event.getPhoneNumber(), NotificationEventType.ADMIN_FORGOT_USERNAME, context));
        } catch (ServiceException e) {
            if (LOG.isDebugEnabled()) {
                LOG.debug("Unable to send an admin forgot username email for " + event.getEmailAddress(), e);
            }
        }
    }
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-admin-module/src/main/java/org/broadleafcommerce/admin/event/AdminNotificationForgotUsernameEventListener.java" line="78">

---

## Asynchronous Event Handling

The `isAsynchronous` method is overridden to return true, indicating that this event listener handles events asynchronously.

```java
    public boolean isAsynchronous() {
        return true;
    }
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
