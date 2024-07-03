---
title: Maven Configuration in core/broadleaf-profile-web
---
This document provides a detailed walkthrough of how Maven is used in the `core/broadleaf-profile-web` module of the Broadleaf Commerce framework.

<SwmSnippet path="/core/broadleaf-profile-web/pom.xml" line="3">

---

# Parent Project Configuration

The `parent` tag is used to indicate the parent project from which this module inherits properties. Here, the parent project is `core` with version `6.2.11-SNAPSHOT`.

```xml
    <parent>
        <groupId>org.broadleafcommerce</groupId>
        <artifactId>core</artifactId>
        <version>6.2.11-SNAPSHOT</version>
    </parent>
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-profile-web/pom.xml" line="8">

---

# Project Information

The `modelVersion`, `artifactId`, `name`, `description`, and `url` provide basic information about the project. The `artifactId` is `broadleaf-profile-web`, which is the name of the jar without version.

```xml
    <modelVersion>4.0.0</modelVersion>
    <artifactId>broadleaf-profile-web</artifactId>
    <name>BroadleafCommerce Profile Web</name>
    <description>BroadleafCommerce Profile Web</description>
    <url>https://www.broadleafcommerce.com</url>
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-profile-web/pom.xml" line="13">

---

# Project Properties

The `properties` tag is used to define project-wide properties. Here, `project.uri` is defined, which can be used elsewhere in the pom file.

```xml
    <properties>
        <project.uri>${project.baseUri}/../../</project.uri>
    </properties>
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-profile-web/pom.xml" line="16">

---

# License Information

The `licenses` tag provides information about the project's license. Here, the project uses the `Broadleaf Fair Use 1.0` license.

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

<SwmSnippet path="/core/broadleaf-profile-web/pom.xml" line="24">

---

# Developer Information

The `developers` tag provides information about the developers involved in the project. Here, the developer `architect` from `Broadleaf Commerce` is mentioned.

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

<SwmSnippet path="/core/broadleaf-profile-web/pom.xml" line="33">

---

# Build Profiles

The `profiles` tag is used to provide different build settings for different environments. Here, a profile `blc-development` is defined which includes the `jrebel-maven-plugin` for hot code reloading.

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

# Project Dependencies

The `dependencies` tag is used to list all the dependencies required by the project. Here, several dependencies are listed including `javax.servlet-api`, `broadleaf-profile`, `spring-webmvc`, `spring-security-acl`, `spring-security-taglibs`, `spring-security-config`, and `spring-oxm`.

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
