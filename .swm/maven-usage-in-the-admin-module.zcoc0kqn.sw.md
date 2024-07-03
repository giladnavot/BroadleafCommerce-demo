---
title: Maven Usage in the Admin Module
---
This document provides a detailed explanation of how Maven is used in the admin module of the BroadleafCommerce-demo project.

<SwmSnippet path="/admin/pom.xml" line="1">

---

# Maven Project Configuration

The `pom.xml` file is the main configuration file for a Maven project. It defines the project structure, dependencies, build plugins, and other project-specific configurations. In this case, the `pom.xml` file is defining the admin module of the BroadleafCommerce-demo project. It specifies the parent project, artifact details, project description, and the modules included in the admin project.

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <artifactId>broadleaf</artifactId>
        <groupId>org.broadleafcommerce</groupId>
        <version>6.2.11-SNAPSHOT</version>
    </parent>
    <artifactId>admin</artifactId>
    <packaging>pom</packaging>
    <name>BroadleafCommerce Admin</name>
    <description>BroadleafCommerce Admin Mid Level Project</description>
    <url>https://www.broadleafcommerce.com</url>
    <properties>
        <project.uri>${project.baseUri}/../</project.uri>
    </properties>
    <licenses>
        <license>
            <name>Broadleaf Fair Use 1.0</name>
            <url>http://license.broadleafcommerce.org/fair_use_license-1.0.txt</url>
            <distribution>repo</distribution>
```

---

</SwmSnippet>

<SwmSnippet path="/admin/pom.xml" line="34">

---

# Admin Module Configuration

The `modules` element in the `pom.xml` file lists the sub-modules of the admin project. These modules include `broadleaf-admin-module`, `broadleaf-open-admin-platform`, `broadleaf-contentmanagement-module`, and `broadleaf-admin-functional-tests`. Each of these modules has its own `pom.xml` file and can be built independently.

```xml
    <modules>
        <module>broadleaf-admin-module</module>
        <module>broadleaf-open-admin-platform</module>
        <module>broadleaf-contentmanagement-module</module>
        <module>broadleaf-admin-functional-tests</module>
    </modules>
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-open-admin-platform/src/main/java/org/broadleafcommerce/openadmin/server/security/domain/AdminModule.java" line="1">

---

# Admin Module Usage

The `AdminModule` class is part of the `broadleaf-open-admin-platform` module. It defines the structure of an admin module in the BroadleafCommerce-demo project. The `AdminModule` class is used in various parts of the project to manage and manipulate admin modules.

```java
/*-
 * #%L
 * BroadleafCommerce Open Admin Platform
 * %%
 * Copyright (C) 2009 - 2024 Broadleaf Commerce
 * %%
 * Licensed under the Broadleaf Fair Use License Agreement, Version 1.0
 * (the "Fair Use License" located  at http://license.broadleafcommerce.org/fair_use_license-1.0.txt)
 * unless the restrictions on use therein are violated and require payment to Broadleaf in which case
 * the Broadleaf End User License Agreement (EULA), Version 1.1
 * (the "Commercial License" located at http://license.broadleafcommerce.org/commercial_license-1.1.txt)
 * shall apply.
 * 
 * Alternatively, the Commercial License may be replaced with a mutually agreed upon license (the "Custom License")
 * between you and Broadleaf Commerce. You may not use this file except in compliance with the applicable license.
 * #L%
 */
package org.broadleafcommerce.openadmin.server.security.domain;

import java.io.Serializable;
import java.util.List;
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-open-admin-platform/src/main/java/org/broadleafcommerce/openadmin/server/security/domain/AdminMenu.java" line="23">

---

# Admin Menu Configuration

The `AdminMenu` class is part of the `broadleaf-open-admin-platform` module. It holds the admin menus and sections for which the user has permissions to view. The `AdminMenu` class uses the `AdminModule` class to manage the admin modules.

```java
/**
 * Class to hold the admin menus and sections for which the passed in user has permissions to view.
 * @author bpolster 
 */
public class AdminMenu {

    private List<AdminModule> adminModules = new ArrayList<AdminModule>();

    public List<AdminModule> getAdminModules() {
        return adminModules;
    }

    public void setAdminModule(List<AdminModule> adminModules) {
        this.adminModules = adminModules;
    }
}
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="build-tool"><sup>Powered by [Swimm](/)</sup></SwmMeta>
