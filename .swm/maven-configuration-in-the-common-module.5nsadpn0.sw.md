---
title: Maven Configuration in the Common Module
---
This document provides a detailed walkthrough of how Maven is used in the BroadleafCommerce-demo repository, specifically focusing on the `common` module. It will explain the Maven configuration file (`pom.xml`) and how it is used to manage dependencies, plugins, and build profiles.

<SwmSnippet path="/common/pom.xml" line="1">

---

# Project Information

The `pom.xml` file starts with the project information. The `parent` tag specifies the parent project from which this project inherits. The `modelVersion` is always `4.0.0` for Maven 2 and 3. The `artifactId` is the name of the jar without version, `packaging` can be `jar`, `war`, etc. The `name` and `description` provide a brief explanation of the project.

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>broadleaf</artifactId>
        <groupId>org.broadleafcommerce</groupId>
        <version>6.2.11-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>
    <artifactId>broadleaf-common</artifactId>
    <packaging>jar</packaging>
    <name>BroadleafCommerce Common Libraries</name>
    <description>A collection of classes shared by broadleaf profile, cms, admin, and core.</description>
    <url>https://www.broadleafcommerce.com</url>
```

---

</SwmSnippet>

<SwmSnippet path="/common/pom.xml" line="14">

---

# Properties

The `properties` tag is used to define project-wide properties that can be referenced elsewhere in the `pom.xml`. Here, `project.uri` is defined.

```xml
    <properties>
        <project.uri>${project.baseUri}/../</project.uri>
    </properties>
```

---

</SwmSnippet>

<SwmSnippet path="/common/pom.xml" line="17">

---

# Licenses

The `licenses` tag is used to specify the licenses for the project. This project uses the Broadleaf Fair Use 1.0 license.

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

<SwmSnippet path="/common/pom.xml" line="25">

---

# Developers

The `developers` tag is used to list the developers involved in the project. Here, the developer with id `architect` is listed.

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

<SwmSnippet path="/common/pom.xml" line="34">

---

# Build Configuration

The `build` tag is used to provide build-specific information. This includes the configuration of plugins like `maven-jar-plugin`, `gmavenplus-plugin`, and `maven-compiler-plugin`, and the resources to be included in the build.

```xml
    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-jar-plugin</artifactId>
                <executions>
                    <execution>
                        <goals>
                            <goal>test-jar</goal>
                        </goals>
                    </execution>
                </executions>
            </plugin>
            <plugin>
                <groupId>org.codehaus.gmavenplus</groupId>
                <artifactId>gmavenplus-plugin</artifactId>
            </plugin>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <configuration>
```

---

</SwmSnippet>

<SwmSnippet path="/common/pom.xml" line="80">

---

# Profiles

The `profiles` tag is used to provide specific settings for different build profiles. Here, a profile with id `blc-development` is defined, which includes the `jrebel-maven-plugin`.

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

<SwmSnippet path="/common/pom.xml" line="102">

---

# Dependencies

The `dependencies` tag is used to specify the dependencies of the project. Each `dependency` tag includes the `groupId`, `artifactId`, and `version` of the dependency. Some dependencies also include `scope` and `exclusions`.

```xml
    <dependencies>
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
        </dependency>
        <dependency>
            <groupId>org.mvel</groupId>
            <artifactId>mvel2</artifactId>
        </dependency>

        <!-- JAXB Libraries -->
        <dependency>
            <groupId>javax.annotation</groupId>
            <artifactId>javax.annotation-api</artifactId>
        </dependency>
        <dependency>
            <groupId>javax.xml.bind</groupId>
            <artifactId>jaxb-api</artifactId>
        </dependency>
        <dependency>
            <groupId>com.sun.xml.bind</groupId>
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="build-tool"><sup>Powered by [Swimm](/)</sup></SwmMeta>
