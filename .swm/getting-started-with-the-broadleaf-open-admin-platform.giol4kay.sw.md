---
title: Getting Started with the Broadleaf Open Admin Platform
---
The Broadleaf Open Admin Platform is a part of the Broadleaf Commerce framework, designed to provide a robust and customizable administrative interface for managing e-commerce operations. It is implemented in Java and is part of the broader Broadleaf Commerce framework. The platform includes features for managing products, customers, orders, and other aspects of an e-commerce system.

The platform's codebase is organized into several packages and directories, including `org.broadleafcommerce.openadmin.web.filter`, `org.broadleafcommerce.openadmin.server.security.domain`, and `org.broadleafcommerce.openadmin.server.security.service`, among others. These packages contain classes and resources that implement various functionalities of the admin platform.

The `BroadleafAdminRequestProcessor.java` file, for example, contains constants used across the platform, such as `SANDBOX_REQ_PARAM`, `CATALOG_REQ_PARAM`, and `ADMIN_STRICT_VALIDATE_PRODUCTION_CHANGES_KEY`. These constants are used to manage request parameters and configuration settings within the platform.

The platform also includes a `pom.xml` file, which is a Project Object Model file used by Maven to manage the project's build, report, and documentation. This file specifies the project structure, dependencies, plugins, and other configuration details.

The `OpenAdminMessages.properties` file is a resource bundle that contains localized messages used in the admin interface. For example, `Admin_Title` is the key for the title of the admin interface, which is 'Broadleaf Admin'.

<SwmSnippet path="/admin/broadleaf-open-admin-platform/pom.xml" line="1">

---

# Broadleaf Open Admin Platform Structure

The `pom.xml` file for the Broadleaf open admin platform module provides an overview of the module's structure and dependencies. It includes the module's artifact ID, name, description, and dependencies on other modules such as `broadleaf-common`.

```xml
<?xml version="1.0"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <artifactId>admin</artifactId>
        <groupId>org.broadleafcommerce</groupId>
        <version>6.2.11-SNAPSHOT</version>
    </parent>
    <artifactId>broadleaf-open-admin-platform</artifactId>
    <packaging>jar</packaging>
    <name>BroadleafCommerce Open Admin Platform</name>
    <description>BroadleafCommerce Open Admin Platform</description>
    <url>https://www.broadleafcommerce.com</url>
    <properties>
        <project.uri>${project.baseUri}/../../</project.uri>
    </properties>
    <licenses>
        <license>
            <name>Broadleaf Fair Use 1.0</name>
            <url>http://license.broadleafcommerce.org/fair_use_license-1.0.txt</url>
            <distribution>repo</distribution>
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-open-admin-platform/src/main/java/org/broadleafcommerce/openadmin/web/filter/BroadleafAdminRequestProcessor.java" line="46">

---

# Admin User Security

The `BroadleafAdminRequestProcessor` class in the `org.broadleafcommerce.openadmin.web.filter` package is an example of how the Broadleaf open admin platform handles security. It imports the `AdminUser` class from the `org.broadleafcommerce.openadmin.server.security.domain` package, which represents an admin user in the system.

```java
import org.broadleafcommerce.common.web.DeployBehavior;
import org.broadleafcommerce.common.web.ValidateProductionChangesState;
import org.broadleafcommerce.openadmin.server.security.domain.AdminUser;
import org.broadleafcommerce.openadmin.server.security.remote.SecurityVerifier;
import org.broadleafcommerce.openadmin.server.security.service.AdminSecurityService;
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-open-admin-platform/src/main/resources/messages/OpenAdminMessages.properties" line="193">

---

# Admin Interface Localization

The `OpenAdminMessages.properties` file in the `resources/messages` directory is used for localizing the admin interface. For example, the `Admin_Title` key is used to set the title of the admin interface.

```ini
Admin_Title=Broadleaf Admin
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
