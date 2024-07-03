---
title: Maven Configuration in core/broadleaf-profile
---
This document provides a detailed explanation of how Maven is used in the `core/broadleaf-profile` directory of the BroadleafCommerce-demo project.

<SwmSnippet path="/core/broadleaf-profile/pom.xml" line="3">

---

# Parent Project Configuration

The `parent` tag is used to indicate the parent project from which this module inherits properties. Here, the parent project is `core` with the group ID `org.broadleafcommerce` and version `6.2.11-SNAPSHOT`.

```xml
    <parent>
        <artifactId>core</artifactId>
        <groupId>org.broadleafcommerce</groupId>
        <version>6.2.11-SNAPSHOT</version>
    </parent>
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-profile/pom.xml" line="8">

---

# Project Information

The `modelVersion`, `artifactId`, `name`, `description`, and `url` provide basic information about the project. The `artifactId` is `broadleaf-profile`, which is the name of the jar without version.

```xml
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

The `properties` tag is used to define project-wide properties that can be referenced elsewhere in the pom. Here, `project.uri` is defined.

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

The `licenses` tag provides information about the project's license. Here, the license is `Broadleaf Fair Use 1.0`.

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

The `developers` tag provides information about the developers involved in the project. Here, the developer's ID is `architect` and the organization is `Broadleaf Commerce`.

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

# Build Profile

The `profiles` tag is used to provide specific settings under certain situations. Here, a profile with the ID `blc-development` is defined. This profile includes a plugin `jrebel-maven-plugin` which is used to generate rebel.xml during the `process-resources` phase.

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

The `dependencies` tag is used to list all the dependencies required by the project. Here, dependencies like `broadleaf-common`, `commons-validator`, `junit`, `easymock`, and `easymockclassextension` are included.

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
