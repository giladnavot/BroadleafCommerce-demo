---
title: Maven Configuration in core/broadleaf-framework-web
---
This document provides a detailed walkthrough of how Maven is used in the `core/broadleaf-framework-web` directory of the BroadleafCommerce-demo repository.

<SwmSnippet path="/core/broadleaf-framework-web/pom.xml" line="1">

---

# Maven Project Configuration

The `pom.xml` file starts by defining the project and its parent. The parent artifact is `core` and the group is `org.broadleafcommerce`. The version is `6.2.11-SNAPSHOT`. The artifact for this project is `broadleaf-framework-web`. The name and description of the project are also defined here. A URL is provided for more information about the project. A property `project.uri` is also defined.

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/maven-v4_0_0.xsd">
    <parent>
        <artifactId>core</artifactId>
        <groupId>org.broadleafcommerce</groupId>
        <version>6.2.11-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>
    <artifactId>broadleaf-framework-web</artifactId>
    <name>BroadleafCommerce Framework Web</name>
    <description>BroadleafCommerce Framework Web</description>
    <url>https://www.broadleafcommerce.com</url>
    <properties>
        <project.uri>${project.baseUri}/../../</project.uri>
    </properties>
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework-web/pom.xml" line="16">

---

# License and Developer Information

The license under which the project is distributed is defined here. It is the `Broadleaf Fair Use 1.0` license. The distribution is set to `repo`. The developer information is also provided. The developer's id is `architect` and the email is `architect@broadleafcommerce.org`. The organization is `Broadleaf Commerce`.

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

<SwmSnippet path="/core/broadleaf-framework-web/pom.xml" line="33">

---

# Maven Profile Configuration

A Maven profile `blc-development` is defined. This profile includes a plugin `jrebel-maven-plugin` from the group `org.zeroturnaround`. The plugin has an execution with the id `generate-rebel-xml` which is bound to the `process-resources` phase. The goal of this execution is `generate`.

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

<SwmSnippet path="/core/broadleaf-framework-web/pom.xml" line="55">

---

# Dependencies

The dependencies for the project are defined here. These include `spring-webmvc`, `spring-social-web`, `broadleaf-profile`, `broadleaf-profile-web`, `javax.servlet-api`, `broadleaf-framework`, `broadleaf-common`, `commons-collections`, `junit`, `easymock`, and `easymockclassextension`.

```xml
    <dependencies>

        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-webmvc</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.social</groupId>
            <artifactId>spring-social-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.broadleafcommerce</groupId>
            <artifactId>broadleaf-profile</artifactId>
        </dependency>
        <dependency>
            <groupId>org.broadleafcommerce</groupId>
            <artifactId>broadleaf-profile-web</artifactId>
        </dependency>
        <dependency>
            <groupId>javax.servlet</groupId>
            <artifactId>javax.servlet-api</artifactId>
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="build-tool"><sup>Powered by [Swimm](/)</sup></SwmMeta>
