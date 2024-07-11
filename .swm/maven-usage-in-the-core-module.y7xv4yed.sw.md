---
title: Maven Usage in the Core Module
---
This document provides a detailed explanation of how Maven is used in the BroadleafCommerce-demo repository, specifically within the 'core' module. It will cover the Maven configuration file (pom.xml) and its role in the project.

<SwmSnippet path="/core/pom.xml" line="1">

---

# Maven Project Configuration

The Maven Project Object Model (POM) file is the fundamental unit of work in Maven. It is an XML file that contains information about the project and configuration details used by Maven to build the project. In the 'core' module of BroadleafCommerce-demo, the POM file defines the parent project, the artifactId, the packaging type, and the project's name, description, and URL. It also specifies properties, licenses, and developers associated with the project.

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/maven-v4_0_0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <artifactId>broadleaf</artifactId>
        <groupId>org.broadleafcommerce</groupId>
        <version>6.2.11-SNAPSHOT</version>
    </parent>
    <artifactId>core</artifactId>
    <packaging>pom</packaging>
    <name>BroadleafCommerce Core</name>
    <description>BroadleafCommerce Core Mid Level Project</description>
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

<SwmSnippet path="/core/pom.xml" line="34">

---

# Maven Modules Configuration

The 'modules' element in the POM file is used to specify the modules of the project. In the 'core' module of BroadleafCommerce-demo, four modules are specified: 'broadleaf-profile', 'broadleaf-profile-web', 'broadleaf-framework', and 'broadleaf-framework-web'. These modules are built when the 'core' module is built.

```xml
    <modules>
        <module>broadleaf-profile</module>
        <module>broadleaf-profile-web</module>
        <module>broadleaf-framework</module>
        <module>broadleaf-framework-web</module>
  </modules>
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="build-tool"><sup>Powered by [Swimm](/)</sup></SwmMeta>
