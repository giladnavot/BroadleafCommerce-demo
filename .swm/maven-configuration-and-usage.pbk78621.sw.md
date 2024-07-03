---
title: Maven Configuration and Usage
---
This document provides a detailed explanation of how Maven is used in the BroadleafCommerce-demo project. It focuses on the configuration and usage of Maven, citing relevant snippets from the <SwmPath>[pom.xml](/pom.xml)</SwmPath> file.

<SwmSnippet path="/pom.xml" line="1">

---

# Project Information

The project information section of the <SwmPath>[pom.xml](/pom.xml)</SwmPath> file provides basic details about the project. It includes the model version, project name, description, group ID, artifact ID, version, packaging type, and URL.

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/maven-v4_0_0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <name>BroadleafCommerce</name>
    <description>BroadleafCommerce Top Level Project</description>
    <groupId>org.broadleafcommerce</groupId>
    <artifactId>broadleaf</artifactId>
    <version>6.2.11-SNAPSHOT</version>
    <packaging>pom</packaging>
    <url>https://www.broadleafcommerce.com/</url>

```

---

</SwmSnippet>

<SwmSnippet path="/pom.xml" line="13">

---

# Licenses

The licenses section specifies the licensing terms for the project. In this case, the project uses the Broadleaf Fair Use <SwmToken path="/pom.xml" pos="15:10:12" line-data="            &lt;name&gt;Broadleaf Fair Use 1.0&lt;/name&gt;">`1.0`</SwmToken> license.

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

<SwmSnippet path="/pom.xml" line="21">

---

# Developers

The developers section provides information about the developers involved in the project. It includes the developer's ID, name, email, organization, organization URL, and timezone.

```xml
    <developers>
        <developer>
            <id>blcteam</id>
            <name>Broadleaf Commerce Team</name>
            <email>info@broadleafcommerce.com</email>
            <organization>Broadleaf Commerce</organization>
            <organizationUrl>https://www.broadleafcommerce.com</organizationUrl>
            <timezone>-6</timezone>
        </developer>
    </developers>
```

---

</SwmSnippet>

<SwmSnippet path="/pom.xml" line="31">

---

# Repositories

The repositories section specifies the repositories where the project's artifacts are stored. It includes the ID, name, and URL of each repository.

```xml
    <repositories>
        <repository>
            <id>releases</id>
            <name>public releases</name>
            <url>https://nexus2.broadleafcommerce.org/nexus/content/groups/community-releases/</url>
        </repository>
        <repository>
            <id>snapshots</id>
            <name>public snapshots</name>
            <url>https://nexus2.broadleafcommerce.org/nexus/content/groups/community-snapshots/</url>
        </repository>
    </repositories>
```

---

</SwmSnippet>

<SwmSnippet path="/pom.xml" line="43">

---

# Properties

The properties section defines project-wide properties that can be referenced elsewhere in the <SwmPath>[pom.xml](/pom.xml)</SwmPath>. These properties include versions of various dependencies and plugins used in the project.

```xml
    <properties>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <ehcache3.version>3.9.0</ehcache3.version>
        <spring.version>5.3.34</spring.version>
        <spring.security.version>5.8.11</spring.security.version>
        <spring.boot.version>2.7.18</spring.boot.version>
        <jackson-bom.version>2.16.1</jackson-bom.version>
        <lombok.version>1.18.30</lombok.version>
        <maven.source.plugin.version>2.4</maven.source.plugin.version>
        <geb.version>1.0</geb.version>
        <spock.core.version>1.1-groovy-2.4</spock.core.version>
        <spock.spring.version>1.1-groovy-2.4</spock.spring.version>
        <project.uri>${project.baseUri}</project.uri>
        <groovy.version>2.4.21</groovy.version>
        <gpg.plugin.version>1.6</gpg.plugin.version>
        <broadleaf-presentation.version>1.3.7-GA</broadleaf-presentation.version>
        <hsqldb.database.starter.version>2.3.5-GA</hsqldb.database.starter.version>
        <closure.compiler.version>v20200830</closure.compiler.version>
        <hibernate.version>5.4.33.Final</hibernate.version>
        <hibernate-validator.version>6.1.7.Final</hibernate-validator.version>
        <javax.version>2.3.1</javax.version>
```

---

</SwmSnippet>

<SwmSnippet path="/pom.xml" line="73">

---

# Build Configuration

The build section configures aspects related to the build process. It includes plugin management, plugins, and their configurations. Each plugin has a specific role in the build lifecycle.

```xml
    <build>
        <pluginManagement>
            <plugins>
                <plugin>
                    <groupId>org.apache.maven.plugins</groupId>
                    <artifactId>maven-compiler-plugin</artifactId>
                    <version>3.8.1</version>
                    <configuration>
                        <source>1.8</source>
                        <target>1.8</target>
                    </configuration>
                </plugin>
                <plugin>
                    <groupId>org.zeroturnaround</groupId>
                    <artifactId>jrebel-maven-plugin</artifactId>
                    <version>1.1.10</version>
                </plugin>
                <plugin>
                    <groupId>org.codehaus.mojo</groupId>
                    <artifactId>build-helper-maven-plugin</artifactId>
                    <version>3.2.0</version>
```

---

</SwmSnippet>

<SwmSnippet path="/pom.xml" line="376">

---

# Modules

The modules section lists the modules that make up the project. Each module represents a specific project that can be built independently.

```xml
    <modules>
        <module>common</module>
        <module>core</module>
        <module>integration</module>
        <module>admin</module>
    </modules>
```

---

</SwmSnippet>

<SwmSnippet path="/pom.xml" line="382">

---

# Dependencies

The dependencies section lists the external dependencies of the project. Each dependency is identified by its group ID, artifact ID, and version.

```xml
    <dependencies>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <version>${lombok.version}</version>
            <scope>provided</scope>
        </dependency>
```

---

</SwmSnippet>

<SwmSnippet path="/pom.xml" line="390">

---

# Distribution Management

The distribution management section configures how the project artifacts are distributed. It includes the snapshot repository and repository for releases.

```xml
    <distributionManagement>
        <snapshotRepository>
            <id>snapshots</id>
            <url>https://nexus2.broadleafcommerce.org/nexus/content/repositories/framework-snapshots</url>
        </snapshotRepository>
        <repository>
            <id>releases</id>
            <url>https://nexus2.broadleafcommerce.org/nexus/content/repositories/framework-releases</url>
        </repository>
    </distributionManagement>
```

---

</SwmSnippet>

<SwmSnippet path="/pom.xml" line="400">

---

# Profiles

The profiles section defines sets of configuration values that can be activated under certain conditions. Each profile can modify the build process in various ways.

```xml
    <profiles>
        <profile>
            <id>security-check</id>
            <build>
                <plugins>
                    <plugin>
                        <groupId>org.owasp</groupId>
                        <artifactId>dependency-check-maven</artifactId>
                        <version>8.4.0</version>
                        <executions>
                            <execution>
                                <goals>
                                    <goal>check</goal>
                                </goals>
                            </execution>
                        </executions>
                    </plugin>
                </plugins>
            </build>
        </profile>
        <profile>
```

---

</SwmSnippet>

<SwmSnippet path="/pom.xml" line="535">

---

# Dependency Management

The dependency management section provides a centralized place to manage the versions of dependencies. It helps to avoid specifying versions in the dependencies section for each module.

```xml
    <dependencyManagement>
        <dependencies>
            <!--Broadleaf libraries -->
            <dependency>
                <groupId>org.broadleafcommerce</groupId>
                <artifactId>broadleaf-profile</artifactId>
                <version>${project.version}</version>
            </dependency>
            <dependency>
                <groupId>org.broadleafcommerce</groupId>
                <artifactId>broadleaf-profile-web</artifactId>
                <version>${project.version}</version>
            </dependency>
            <dependency>
                <groupId>org.broadleafcommerce</groupId>
                <artifactId>broadleaf-framework</artifactId>
                <version>${project.version}</version>
            </dependency>
            <dependency>
                <groupId>org.broadleafcommerce</groupId>
                <artifactId>broadleaf-framework-web</artifactId>
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo"><sup>Powered by [Swimm](https://app.swimm.io/)</sup></SwmMeta>
