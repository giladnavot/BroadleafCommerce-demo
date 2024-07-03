---
title: Maven Configuration in the Content Management Module
---
This document provides a detailed explanation of how Maven is used in the `admin/broadleaf-contentmanagement-module` of the BroadleafCommerce-demo repository.

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/pom.xml" line="1">

---

# Project Information

The `pom.xml` file starts by defining the project's parent, which is the `admin` artifact from the `org.broadleafcommerce` group. The version is set to `6.2.11-SNAPSHOT`. The artifactId for this module is `broadleaf-contentmanagement-module` and it is packaged as a `jar`. The name and description of the project are both `BroadleafCommerce CMS Module`.

```xml
<?xml version="1.0"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <artifactId>admin</artifactId>
        <groupId>org.broadleafcommerce</groupId>
        <version>6.2.11-SNAPSHOT</version>
    </parent>
    <artifactId>broadleaf-contentmanagement-module</artifactId>
    <packaging>jar</packaging>
    <name>BroadleafCommerce CMS Module</name>
    <description>BroadleafCommerce CMS Module</description>
    <url>https://www.broadleafcommerce.com</url>
    <properties>
        <project.uri>${project.baseUri}/../../</project.uri>
    </properties>
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/pom.xml" line="17">

---

# Licenses and Developers

The project is licensed under the `Broadleaf Fair Use 1.0` license. The main developer is identified as `architect` from the organization `Broadleaf Commerce`.

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

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/pom.xml" line="34">

---

# Build Configuration

The build configuration specifies that resources for the project are located in `src/main/resources` and `src/main/java`. All `.java` files are excluded from the latter.

```xml
    <build>
        <resources>
            <resource>
                <directory>src/main/resources</directory>
            </resource>
            <resource>
                <directory>src/main/java</directory>
                <excludes>
                    <exclude>**/*.java</exclude>
                </excludes>
            </resource>
        </resources>
    </build>
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/pom.xml" line="47">

---

# Profiles

A profile named `blc-development` is defined. This profile includes a plugin `jrebel-maven-plugin` from the `org.zeroturnaround` group. The plugin is set to execute the `generate` goal during the `process-resources` phase.

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

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/pom.xml" line="70">

---

# Dependencies

The project has several dependencies including `broadleaf-open-admin-platform`, `tika-core`, `broadleaf-common`, `easymock`, `mvel2`, `junit`, `javax.servlet-api`, and `spring-test`. Note that `broadleaf-common` and `spring-test` are scoped for `test`.

```xml
    <dependencies>
        <dependency>
            <groupId>org.broadleafcommerce</groupId>
            <artifactId>broadleaf-open-admin-platform</artifactId>
        </dependency>
        <dependency>
            <groupId>org.apache.tika</groupId>
            <artifactId>tika-core</artifactId>
        </dependency>
        <dependency>
            <groupId>org.broadleafcommerce</groupId>
            <artifactId>broadleaf-common</artifactId>
            <classifier>tests</classifier>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.easymock</groupId>
            <artifactId>easymock</artifactId>
        </dependency>
        <dependency>
            <groupId>org.mvel</groupId>
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="build-tool"><sup>Powered by [Swimm](/)</sup></SwmMeta>
