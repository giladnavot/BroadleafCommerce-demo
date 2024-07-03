---
title: Understanding the Broadleaf Admin Module
---
The Broadleaf admin module is a key component of the Broadleaf Commerce framework. It is responsible for providing the administrative functionalities of the e-commerce application. This includes managing products, categories, and other commerce-related entities. The module is defined in the `AdminModuleRegistration` class, which implements the `BroadleafModuleRegistration` interface. The module's name is defined as 'Admin'. The admin module is registered in the `spring.factories` file, which allows Spring to automatically discover it during the application startup.

The admin module is organized into several packages, each responsible for a specific aspect of the admin functionality. For example, the `org.broadleafcommerce.admin.server.service` package contains services related to the admin functionality, such as `AdminCatalogService` which likely handles operations related to the product catalog. The `org.broadleafcommerce.admin.web.controller.entity` package contains web controllers for handling HTTP requests related to entities like products.

The admin module is built as a Maven project, as indicated by the `pom.xml` file. This file defines the module's dependencies, build configuration, and other project-specific settings. The module depends on other Broadleaf modules like `broadleaf-framework` and `broadleaf-open-admin-platform`, as well as other common libraries.

<SwmSnippet path="/admin/broadleaf-admin-module/src/main/java/org/broadleafcommerce/admin/config/AdminModuleRegistration.java" line="22">

---

# AdminModuleRegistration Class

The `AdminModuleRegistration` class is a key part of the Broadleaf admin module. It implements the `BroadleafModuleRegistration` interface and provides the module name, which is 'Admin'. This class is used to register the admin module with the Broadleaf Commerce framework.

```java
/**
 * @author Brandon Hines
 */
public class AdminModuleRegistration implements BroadleafModuleRegistration {

    public static final String MODULE_NAME = "Admin";

    @Override
    public String getModuleName() {
        return MODULE_NAME;
    }

}
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-admin-module/src/main/resources/META-INF/spring.factories" line="0">

---

# AdminModuleRegistration in spring.factories

The `AdminModuleRegistration` class is registered as a `BroadleafModuleRegistration` in the `spring.factories` file. This allows the Broadleaf Commerce framework to recognize and load the admin module during startup.

```factories
org.broadleafcommerce.common.module.BroadleafModuleRegistration=org.broadleafcommerce.admin.config.AdminModuleRegistration
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-admin-module/pom.xml" line="1">

---

# Admin Module's pom.xml

The `pom.xml` file for the Broadleaf admin module defines the module's Maven configuration, including its parent project, artifact ID, and dependencies. This file is crucial for building and packaging the admin module.

```xml
<?xml version="1.0"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <artifactId>admin</artifactId>
        <groupId>org.broadleafcommerce</groupId>
        <version>6.2.11-SNAPSHOT</version>
    </parent>
    <artifactId>broadleaf-admin-module</artifactId>
    <packaging>jar</packaging>
    <name>BroadleafCommerce Admin Module</name>
    <description>BroadleafCommerce Admin Module</description>
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

# Admin Module's Directory Structure

The directory structure of the Broadleaf admin module includes directories for Java classes, resources, and JavaScript files for the admin interface. This structure organizes the various components of the admin module, facilitating its development and maintenance.

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
