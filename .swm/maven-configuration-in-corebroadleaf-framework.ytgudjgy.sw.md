---
title: Maven Configuration in core/broadleaf-framework
---
This document provides a detailed walkthrough of how Maven is used in the `core/broadleaf-framework` directory of the BroadleafCommerce-demo repository.

<SwmSnippet path="/core/broadleaf-framework/pom.xml" line="1">

---

# Maven Project Configuration

The `pom.xml` file starts by defining the project and its parent. The parent artifact is `core` and the current artifact is `broadleaf-framework`. The version is `6.2.11-SNAPSHOT`. The `project.uri` property is defined for use in other parts of the POM.

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

# License and Developer Information

The license and developer information are specified. The license is `Broadleaf Fair Use 1.0` and the developer is `architect@broadleafcommerce.org`.

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

Two plugins are defined for the build: `maven-jar-plugin` and `gmavenplus-plugin`. The `maven-jar-plugin` is configured to create a test JAR during the build.

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

# Build Profile

A build profile `blc-development` is defined. This profile includes the `jrebel-maven-plugin` which is used for hot code reloading during development.

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

The dependencies for the project are defined. These include libraries like `solr-solrj`, `jackson-dataformat-smile`, `mvel2`, `broadleaf-common`, `broadleaf-profile`, `commons-codec`, `junit`, `easymock`, `groovy-all`, `spock-core`, `javax.servlet-api`, `cglib-nodep`, and `spring-core`.

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
