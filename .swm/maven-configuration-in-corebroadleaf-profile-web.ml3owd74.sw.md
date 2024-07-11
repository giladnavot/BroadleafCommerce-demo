---
title: Maven Configuration in core/broadleaf-profile-web
---
This document provides a detailed explanation of how Maven is used in the `core/broadleaf-profile-web` module of the Broadleaf Commerce project.

<SwmSnippet path="/core/broadleaf-profile-web/pom.xml" line="1">

---

# Project Information

The `pom.xml` file starts by defining the parent project, which is the `core` module of Broadleaf Commerce. The current module is identified as `broadleaf-profile-web`. The version of the parent project is `6.2.11-SNAPSHOT`. The name and description of the module are both `BroadleafCommerce Profile Web`.

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/maven-v4_0_0.xsd">
    <parent>
        <groupId>org.broadleafcommerce</groupId>
        <artifactId>core</artifactId>
        <version>6.2.11-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>
    <artifactId>broadleaf-profile-web</artifactId>
    <name>BroadleafCommerce Profile Web</name>
    <description>BroadleafCommerce Profile Web</description>
    <url>https://www.broadleafcommerce.com</url>
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-profile-web/pom.xml" line="16">

---

# Licenses and Developers

The license used in this module is the `Broadleaf Fair Use 1.0`. The main developer is identified as `architect` from `Broadleaf Commerce`.

```xml
    <licenses>
        <license>
            <name>Broadleaf Fair Use 1.0</name>
            <url>http://license.broadleafcommerce.org/fair_use_license-1.0.txt</url>
            <distribution>repo</distribution>
            <comments>Fair Use Community License</comments>
        </license>
    </licenses>
    <developers>
        <developer>
            <id>architect</id>
            <email>architect@broadleafcommerce.org</email>
            <organization>Broadleaf Commerce</organization>
            <organizationUrl>https://www.broadleafcommerce.com</organizationUrl>
            <timezone>-6</timezone>
        </developer>
    </developers>
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-profile-web/pom.xml" line="33">

---

# Profiles

A Maven profile named `blc-development` is defined. This profile includes the `jrebel-maven-plugin` which is used to generate the `rebel.xml` configuration file for JRebel, a productivity tool that allows developers to reload code changes instantly.

```xml
    <profiles>
        <profile>
            <id>blc-development</id>
            <build>
                <plugins>
                    <plugin>
                        <groupId>org.zeroturnaround</groupId>
                        <artifactId>jrebel-maven-plugin</artifactId>
                        <executions>
                            <execution>
                                <id>generate-rebel-xml</id>
                                <phase>process-resources</phase>
                                <goals>
                                    <goal>generate</goal>
                                </goals>
                            </execution>
                        </executions>
                    </plugin>
                </plugins>
            </build>
        </profile>
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-profile-web/pom.xml" line="55">

---

# Dependencies

Several dependencies are declared for this module. These include `javax.servlet-api`, `broadleaf-profile`, `spring-webmvc`, `spring-security-acl`, `spring-security-taglibs`, `spring-security-config`, and `spring-oxm`.

```xml
    <dependencies>
        <dependency>
            <groupId>javax.servlet</groupId>
            <artifactId>javax.servlet-api</artifactId>
        </dependency>
        <dependency>
            <groupId>org.broadleafcommerce</groupId>
            <artifactId>broadleaf-profile</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-webmvc</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.security</groupId>
            <artifactId>spring-security-acl</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.security</groupId>
            <artifactId>spring-security-taglibs</artifactId>
        </dependency>
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="build-tool"><sup>Powered by [Swimm](/)</sup></SwmMeta>
