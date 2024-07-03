---
title: Getting Started with Admin Notifications
---
Admin Notifications in BroadleafCommerce-demo refer to the system's way of alerting administrators about certain events. These notifications are dispatched through the NotificationDispatcher service and can be of different types, such as EmailNotification or SMSNotification. For instance, when an administrator forgets their username or password, the system triggers an AdminForgotUsernameEvent or AdminForgotPasswordEvent respectively. These events are handled by specific listeners, which create a context and dispatch a notification. The context contains relevant information about the event, such as the active usernames in case of a forgotten username, or the reset password URL and token in case of a forgotten password.

<SwmSnippet path="/admin/broadleaf-admin-module/src/main/java/org/broadleafcommerce/admin/event/AdminNotificationForgotUsernameEventListener.java" line="40">

---

## Admin Notification Event Listeners

This is the `AdminNotificationForgotUsernameEventListener` class. It listens for `AdminForgotUsernameEvent` events. When such an event occurs, it creates an email and an SMS notification and dispatches them using the `NotificationDispatcher` service. The notifications contain the active usernames associated with the email address or phone number specified in the event.

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

<SwmSnippet path="/admin/broadleaf-admin-module/src/main/java/org/broadleafcommerce/admin/event/AdminNotificationForgotPasswordEventListener.java" line="41">

---

This is the `AdminNotificationForgotPasswordEventListener` class. It listens for `AdminForgotPasswordEvent` events. When such an event occurs, it retrieves the `AdminUser` associated with the user ID specified in the event, creates an email and an SMS notification, and dispatches them using the `NotificationDispatcher` service. The notifications contain a reset password URL and a token for resetting the password.

```java
@Component("blAdminNotificationForgotPasswordEventListener")
public class AdminNotificationForgotPasswordEventListener extends AbstractBroadleafApplicationEventListener<AdminForgotPasswordEvent> {

    protected static final String TOKEN_CONTEXT_KEY = "token";
    protected static final String RESET_PASSWORD_URL_CONTEXT_KEY = "resetPasswordUrl";
    protected static final String ADMIN_USER_CONTEXT_KEY = "adminUser";
    protected final Log LOG = LogFactory.getLog(AdminNotificationForgotPasswordEventListener.class);

    @Autowired
    @Qualifier("blAdminUserDao")
    protected AdminUserDao adminUserDao;

    @Autowired
    @Qualifier("blNotificationDispatcher")
    protected NotificationDispatcher notificationDispatcher;

    @Override
    protected void handleApplicationEvent(AdminForgotPasswordEvent event) {
        AdminUser adminUser = adminUserDao.readAdminUserById(event.getAdminUserId());

        if (adminUser != null) {
```

---

</SwmSnippet>

# Admin Notifications Functions

Let's delve into the key functions of the Admin Notifications feature.

<SwmSnippet path="/admin/broadleaf-admin-module/src/main/java/org/broadleafcommerce/admin/event/AdminNotificationForgotPasswordEventListener.java" line="58">

---

## handleApplicationEvent

The `handleApplicationEvent` function is triggered when an `AdminForgotPasswordEvent` occurs. It retrieves the admin user details, creates a context, and dispatches notifications via email and SMS. If any error occurs during the dispatch, it logs the error.

```java
    protected void handleApplicationEvent(AdminForgotPasswordEvent event) {
        AdminUser adminUser = adminUserDao.readAdminUserById(event.getAdminUserId());

        if (adminUser != null) {
            Map<String, Object> context = createContext(event, adminUser);

            try {
                notificationDispatcher.dispatchNotification(new EmailNotification(adminUser.getEmail(), NotificationEventType.ADMIN_FORGOT_PASSWORD, context));
            } catch (ServiceException e) {
                if (LOG.isDebugEnabled()) {
                    LOG.debug("Unable to send an admin forgot password email for " + adminUser.getEmail(), e);
                }
            }

            try {
                notificationDispatcher.dispatchNotification(new SMSNotification(adminUser.getPhoneNumber(), NotificationEventType.ADMIN_FORGOT_PASSWORD, context));
            } catch (ServiceException e) {
                if (LOG.isDebugEnabled()) {
                    LOG.debug("Unable to send an admin forgot password email for " + adminUser.getEmail(), e);
                }
            }
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-admin-module/src/main/java/org/broadleafcommerce/admin/event/AdminNotificationForgotPasswordEventListener.java" line="82">

---

## createContext

The `createContext` function is used to create a context map for the notification. It includes the reset password URL, the token, and the admin user details.

```java
    protected Map<String, Object> createContext(AdminForgotPasswordEvent event, AdminUser adminUser) {
        HashMap<String, Object> context = new HashMap<>();
        String resetPasswordUrl = event.getResetPasswordUrl();
        String token = event.getToken();
        context.put(TOKEN_CONTEXT_KEY, token);
        context.put(RESET_PASSWORD_URL_CONTEXT_KEY, resetPasswordUrl);
        context.put(ADMIN_USER_CONTEXT_KEY, adminUser);
        return MapUtils.unmodifiableMap(context);
    }
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-admin-module/src/main/java/org/broadleafcommerce/admin/event/AdminNotificationForgotUsernameEventListener.java" line="51">

---

## handleApplicationEvent

The `handleApplicationEvent` function is triggered when an `AdminForgotUsernameEvent` occurs. It creates a context and dispatches notifications via email and SMS. If any error occurs during the dispatch, it logs the error.

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

<SwmSnippet path="/admin/broadleaf-admin-module/src/main/java/org/broadleafcommerce/admin/event/AdminNotificationForgotUsernameEventListener.java" line="71">

---

## createContext

The `createContext` function is used to create a context map for the notification. It includes the active usernames.

```java
    protected Map<String, Object> createContext(AdminForgotUsernameEvent event) {
        HashMap<String, Object> context = new HashMap<>();
        context.put(ACTIVE_USERNAMES_CONTEXT_KEY, event.getActiveUsernames());
        return MapUtils.unmodifiableMap(context);
    }
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
