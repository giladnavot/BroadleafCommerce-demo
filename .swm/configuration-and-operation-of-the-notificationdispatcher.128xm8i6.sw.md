---
title: Configuration and Operation of the NotificationDispatcher
---
This document will cover the working and configuration of the NotificationDispatcher in the BroadleafCommerce-demo repository. We'll cover:

1. What is the NotificationDispatcher
2. How the NotificationDispatcher works
3. How the NotificationDispatcher is configured

# What is the NotificationDispatcher

The NotificationDispatcher is a service in the BroadleafCommerce-demo repository that is responsible for dispatching notifications to relevant services. It is implemented in the `NotificationDispatcherImpl` class.

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/notification/service/NotificationDispatcherImpl.java" line="32">

---

# How the NotificationDispatcher works

The `NotificationDispatcherImpl` class implements the `NotificationDispatcher` interface. It contains a list of `NotificationService` objects that are used to send notifications. The `dispatchNotification` method is responsible for sending the notification. It checks if there are any notification services available, if the notification is not null, and if the notification type and context are specified. If all these conditions are met, it iterates over the notification services and sends the notification using the service that can handle the notification type. If any error occurs during this process, it is caught and rethrown as a `ServiceException`.

```java
@Service("blNotificationDispatcher")
public class NotificationDispatcherImpl implements NotificationDispatcher {

    protected final Log LOG = LogFactory.getLog(NotificationDispatcherImpl.class);

    protected final List<NotificationService> notificationServices;

    public NotificationDispatcherImpl(List<NotificationService> notificationServices) {
        this.notificationServices = notificationServices;
    }

    @Override
    public void dispatchNotification(Notification notification) throws ServiceException {
        if (CollectionUtils.isEmpty(notificationServices)) {
            throw new ServiceException("No notification services injected to handle notifications");
        }

        if (notification == null) {
            throw new ServiceException("NULL Notification provided to dispatcher, unable to send notification");
        }

```

---

</SwmSnippet>

# How the NotificationDispatcher is configured

The `NotificationDispatcher` is configured by injecting it into classes where it is needed. This is done using the `@Autowired` and `@Qualifier("blNotificationDispatcher")` annotations. The `NotificationDispatcher` is then used in these classes to dispatch notifications.

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="follow-up"><sup>Powered by [Swimm](/)</sup></SwmMeta>
