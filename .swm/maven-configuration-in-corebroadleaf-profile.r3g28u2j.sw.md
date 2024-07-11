---
title: Maven Configuration in core/broadleaf-profile
---
This document provides a detailed walkthrough of how Maven is used in the `core/broadleaf-profile` directory of the BroadleafCommerce-demo repository.

<SwmSnippet path="/core/broadleaf-profile/pom.xml" line="1">

---

# Maven Project Configuration

The `pom.xml` file starts by defining the project and its parent. The parent artifact is `core` and the group is `org.broadleafcommerce`. The version is `6.2.11-SNAPSHOT`. The model version is `4.0.0`, which is the standard for Maven 2 and later. The artifact for this project is `broadleaf-profile`. The name and description of the project are both `BroadleafCommerce Profile`.

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/maven-v4_0_0.xsd">
    <parent>
        <artifactId>core</artifactId>
        <groupId>org.broadleafcommerce</groupId>
        <version>6.2.11-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>
    <artifactId>broadleaf-profile</artifactId>
    <name>BroadleafCommerce Profile</name>
    <description>BroadleafCommerce Profile</description>
    <url>https://www.broadleafcommerce.com</url>
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-profile/pom.xml" line="13">

---

# Project Properties

The `project.uri` property is defined, which is used to specify the base URI of the project.

```xml
    <properties>
        <project.uri>${project.baseUri}/../../</project.uri>
    </properties>
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-profile/pom.xml" line="16">

---

# License Information

The license for the project is defined as `Broadleaf Fair Use 1.0`. The URL for the license text and the distribution method are also specified.

```xml
    <licenses>
        <license>
            <name>Broadleaf Fair Use 1.0</name>
            <url>http://license.broadleafcommerce.org/fair_use_license-1.0.txt</url>
            <distribution>repo</distribution>
            <comments>Fair Use Community License</comments>
        </license>
    </licenses>
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-profile/pom.xml" line="24">

---

# Developer Information

The developer information is specified. The developer's id is `architect`, and the email, organization, organization URL, and timezone are also provided.

```xml
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

<SwmSnippet path="/core/broadleaf-profile/pom.xml" line="33">

---

# Build Profiles

A Maven profile `blc-development` is defined. This profile includes a plugin `jrebel-maven-plugin` from the group `org.zeroturnaround`. The plugin execution phase is `process-resources` and the goal is `generate`.

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
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-profile/pom.xml" line="56">

---

# Dependencies

The dependencies for the project are defined. These include `broadleaf-common` from `org.broadleafcommerce`, `commons-validator` from `commons-validator`, `junit` from `junit`, `easymock` and `easymockclassextension` from `org.easymock`.

```xml
    <dependencies>
        <dependency>
            <groupId>org.broadleafcommerce</groupId>
            <artifactId>broadleaf-common</artifactId>
        </dependency>
        <dependency>
            <groupId>commons-validator</groupId>
            <artifactId>commons-validator</artifactId>
        </dependency>
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
        </dependency>
        <dependency>
            <groupId>org.easymock</groupId>
            <artifactId>easymock</artifactId>
        </dependency>
        <dependency>
            <groupId>org.easymock</groupId>
            <artifactId>easymockclassextension</artifactId>
        </dependency>
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="build-tool"><sup>Powered by [Swimm](/)</sup></SwmMeta>
