---
title: Maven Configuration in admin/broadleaf-admin-module
---
This document provides a detailed explanation of how Maven is used in the `admin/broadleaf-admin-module` of the BroadleafCommerce-demo repository.

<SwmSnippet path="/admin/broadleaf-admin-module/pom.xml" line="1">

---

# Project Information

The `pom.xml` file starts by defining the project information. It specifies the model version, parent artifact details, artifact id, packaging type, name, description, and URL of the project.

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
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-admin-module/pom.xml" line="14">

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

<SwmSnippet path="/admin/broadleaf-admin-module/pom.xml" line="17">

---

# Licenses

The license details for the project are specified under the `licenses` tag. It includes the name, URL, distribution, and comments about the license.

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

<SwmSnippet path="/admin/broadleaf-admin-module/pom.xml" line="25">

---

# Developers

The `developers` tag is used to provide information about the developers involved in the project.

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

<SwmSnippet path="/admin/broadleaf-admin-module/pom.xml" line="34">

---

# Build Configuration

The `build` tag is used to define the build settings for the project. It includes the resources to be included in the build and the plugins to be used during the build process.

```xml
    <build>
        <resources>
            <resource>
                <directory>src/main/resources/admin_style</directory>
                <filtering>false</filtering>
                <targetPath>admin_style</targetPath>
            </resource>
            <resource>
                <directory>src/main/resources</directory>
                <filtering>true</filtering>
                <excludes>
                    <exclude>open_admin_style/**</exclude>
                </excludes>
            </resource>
            <resource>
                <directory>src/main/java</directory>
                <excludes>
                    <exclude>**/*.java</exclude>
                </excludes>
            </resource>
        </resources>
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-admin-module/pom.xml" line="66">

---

# Profiles

The `profiles` tag is used to define a set of configurations that can be activated under certain conditions. Here, a profile named `blc-development` is defined with its own build plugins.

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

<SwmSnippet path="/admin/broadleaf-admin-module/pom.xml" line="89">

---

# Dependencies

The `dependencies` tag is used to define the dependencies of the project. These dependencies are required for the project to build and run.

```xml
    <dependencies>
        <dependency>
            <groupId>org.broadleafcommerce</groupId>
            <artifactId>broadleaf-framework</artifactId>
        </dependency>
        <dependency>
            <groupId>org.broadleafcommerce</groupId>
            <artifactId>broadleaf-open-admin-platform</artifactId>
        </dependency>
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
        </dependency>
        <dependency>
            <groupId>org.apache.commons</groupId>
            <artifactId>commons-lang3</artifactId>
        </dependency>
        <dependency>
            <groupId>javax.servlet</groupId>
            <artifactId>javax.servlet-api</artifactId>
        </dependency>
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="build-tool"><sup>Powered by [Swimm](/)</sup></SwmMeta>
