---
title: Introduction to Email
---
Email in BroadleafCommerce-demo refers to the functionality that enables the system to send, track, and manage emails. It is implemented through various classes and interfaces such as `EmailInfo`, `EmailService`, `EmailTracking`, and `EmailTrackingClicks`. `EmailInfo` is a class that holds information about an email, including the email type, template, subject, and from address. `EmailService` is an interface that defines methods for sending template and basic emails. `EmailTracking` is an interface that provides methods for tracking emails, including getting and setting the email address, date sent, and type. `EmailTrackingClicks` is an interface that provides methods for tracking clicks in emails, including getting and setting the date clicked, destination URI, and query string.

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/email/service/info/EmailInfo.java" line="31">

---

# EmailInfo Class

The `EmailInfo` class is used to set up the email's basic information. It includes properties like email type, template, subject, from address, and more. It also includes methods to get and set these properties.

```java
public class EmailInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String emailType;
    private String emailTemplate;
    private String subject;
    private String fromAddress;
    private String messageBody;
    private String encoding = "UTF8";
    private List<Attachment> attachments = new ArrayList<Attachment>();
    private Map<String, String> headers = new HashMap<>();

    private String sendEmailReliableAsync;
    private String sendAsyncPriority;

    /**
     * @return the emailType
     */
    public String getEmailType() {
        return emailType;
```

---

</SwmSnippet>

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/email/domain/EmailTracking.java" line="27">

---

# EmailTracking Interface

The `EmailTracking` interface is used to manage email tracking. It includes methods to get and set the email address, date sent, and type of the email.

```java
public interface EmailTracking extends Serializable {

    public abstract Long getId();

    public abstract void setId(Long id);

    /**
     * @return the emailAddress
     */
    public abstract String getEmailAddress();

    /**
     * @param emailAddress the emailAddress to set
     */
    public abstract void setEmailAddress(String emailAddress);

    /**
     * @return the dateSent
     */
    public abstract Date getDateSent();

```

---

</SwmSnippet>

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/email/service/EmailServiceImpl.java" line="66">

---

# Usage of EmailInfo and EmailTracking

Here is an example of how `EmailInfo` and `EmailTracking` are used in the `EmailServiceImpl` class. The `sendTemplateEmail` method creates an instance of `EmailInfo` if it's null and uses it to send an email. The `EmailTracking` is used to track the sent email.

```java
    @Override
    public boolean sendTemplateEmail(EmailTarget emailTarget, EmailInfo emailInfo, Map<String, Object> props) {
        if (emailInfo == null) {
            emailInfo = new EmailInfo();
        }

        props = new HashMap<>(MapUtils.emptyIfNull(props));
        props.put(EmailPropertyType.INFO.getType(), emailInfo);
        props.put(EmailPropertyType.USER.getType(), emailTarget);
        Long emailId = emailTrackingManager.createTrackedEmail(emailTarget.getEmailAddress(), emailInfo.getEmailType(), null);
        props.put("emailTrackingId", emailId);

        return sendBasicEmail(emailInfo, emailTarget, props);
    }

    @Override
    public boolean sendTemplateEmail(String emailAddress, EmailInfo emailInfo, Map<String, Object> props) {
        if (!(emailInfo instanceof NullEmailInfo)) {
            EmailTarget emailTarget = emailReportingDao.createTarget();
            emailTarget.setEmailAddress(emailAddress);
            return sendTemplateEmail(emailTarget, emailInfo, props);
```

---

</SwmSnippet>

# Email Service Endpoints

Email Service Methods

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/email/service/NullEmailServiceImpl.java" line="34">

---

## sendTemplateEmail

The `sendTemplateEmail` method is used to send an email based on a predefined template. It takes the email address or EmailTarget object, an EmailInfo object containing the email details, and a map of properties that can be used in the email template.

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

The `sendBasicEmail` method is used to send a basic email without using a template. It takes an EmailInfo object containing the email details, an EmailTarget object, and a map of properties.

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
