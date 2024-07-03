---
title: Maven Configuration in Broadleaf Framework
---
This document provides a detailed explanation of how Maven is used in the `core/broadleaf-framework` directory of the BroadleafCommerce-demo repository.

<SwmSnippet path="/core/broadleaf-framework/pom.xml" line="1">

---

# Maven Project Configuration

The `pom.xml` file starts by defining the parent project, which is `core` with the version `6.2.11-SNAPSHOT`. The current project is defined as `broadleaf-framework` with the model version `4.0.0`. The project's name, description, and URL are also specified. A property `project.uri` is defined which seems to be used for project's base URI.

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/maven-v4_0_0.xsd">
    <parent>
        <artifactId>core</artifactId>
        <groupId>org.broadleafcommerce</groupId>
        <version>6.2.11-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>
    <artifactId>broadleaf-framework</artifactId>
    <name>BroadleafCommerce Framework</name>
    <description>BroadleafCommerce Framework</description>
    <url>https://www.broadleafcommerce.com</url>
    <properties>
        <project.uri>${project.baseUri}/../../</project.uri>
    </properties>
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/pom.xml" line="16">

---

# License and Developers Information

The `pom.xml` file specifies the license under which the project is distributed. It also provides information about the developers involved in the project.

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

<SwmSnippet path="/core/broadleaf-framework/pom.xml" line="33">

---

# Build Plugins

The build section of the `pom.xml` file specifies the plugins used during the build process. The `maven-jar-plugin` is used to build a jar file of the project, and the `gmavenplus-plugin` is used for integrating Groovy into the project build.

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
        </plugins>
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/pom.xml" line="52">

---

# Profiles

The `pom.xml` file defines a profile `blc-development` which seems to be used for development purposes. The `jrebel-maven-plugin` is used in this profile to integrate JRebel into the project, which allows developers to see code changes immediately without redeploying the application.

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

<SwmSnippet path="/core/broadleaf-framework/pom.xml" line="75">

---

# Dependencies

The `pom.xml` file specifies the dependencies required by the project. These include various libraries and frameworks such as Solr, Jackson, MVEL, Broadleaf common, JUnit, EasyMock, Groovy, Spock, Servlet API, CGLIB, and Spring among others. Some dependencies are scoped to `test` which means they are only required for compiling and running tests.

```xml
    <dependencies>
        <dependency>
            <groupId>org.apache.solr</groupId>
            <artifactId>solr-solrj</artifactId>
        </dependency>
        <!-- Solr still needs this dependency, we'll just use the later version -->
        <dependency>
            <groupId>com.fasterxml.jackson.dataformat</groupId>
            <artifactId>jackson-dataformat-smile</artifactId>
        </dependency>
        <dependency>
            <groupId>org.mvel</groupId>
            <artifactId>mvel2</artifactId>
        </dependency>
        <dependency>
            <groupId>org.broadleafcommerce</groupId>
            <artifactId>broadleaf-common</artifactId>
        </dependency>
        <dependency>
            <groupId>org.broadleafcommerce</groupId>
            <artifactId>broadleaf-common</artifactId>
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="build-tool"><sup>Powered by [Swimm](/)</sup></SwmMeta>
