---
title: Getting Started with Events
---
Events in BroadleafCommerce-demo refer to specific actions or occurrences that the system recognizes and can respond to. They are used to trigger certain functionalities in the application. For instance, there are events for when an admin forgets their username or password. These events trigger notifications, which are dispatched via the NotificationDispatcher service. The events are handled asynchronously, meaning they don't block the execution of the rest of the application.

<SwmSnippet path="/admin/broadleaf-admin-module/src/main/java/org/broadleafcommerce/admin/event/AdminNotificationForgotUsernameEventListener.java" line="40">

---

# Admin Forgot Username Event

This is an example of an event listener for when an admin forgets their username. When this event occurs, a notification is dispatched to the admin's email and phone number.

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

# Admin Forgot Password Event

This is an example of an event listener for when an admin forgets their password. When this event occurs, a notification is dispatched to the admin's email and phone number.

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

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
