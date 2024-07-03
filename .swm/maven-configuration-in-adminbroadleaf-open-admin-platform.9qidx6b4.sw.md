---
title: Maven Configuration in admin/broadleaf-open-admin-platform
---
This document provides a detailed walkthrough of how Maven is used in the `admin/broadleaf-open-admin-platform` directory of the BroadleafCommerce-demo repository.

<SwmSnippet path="/admin/broadleaf-open-admin-platform/pom.xml" line="1">

---

# Maven Project Configuration

The `pom.xml` file starts by defining the model version, parent artifact, and the artifact details of the current module. The `artifactId` is set to `broadleaf-open-admin-platform`, and the packaging type is `jar`. The project's name, description, and URL are also specified.

```xml
<?xml version="1.0"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <artifactId>admin</artifactId>
        <groupId>org.broadleafcommerce</groupId>
        <version>6.2.11-SNAPSHOT</version>
    </parent>
    <artifactId>broadleaf-open-admin-platform</artifactId>
    <packaging>jar</packaging>
    <name>BroadleafCommerce Open Admin Platform</name>
    <description>BroadleafCommerce Open Admin Platform</description>
    <url>https://www.broadleafcommerce.com</url>
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-open-admin-platform/pom.xml" line="14">

---

The `properties` section defines project-specific settings. Here, the `project.uri` property is set, which can be used elsewhere in the `pom.xml`.

```xml
    <properties>
        <project.uri>${project.baseUri}/../../</project.uri>
    </properties>
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-open-admin-platform/pom.xml" line="17">

---

The `licenses` section provides information about the project's license. In this case, the project uses the Broadleaf Fair Use 1.0 license.

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

<SwmSnippet path="/admin/broadleaf-open-admin-platform/pom.xml" line="25">

---

The `developers` section lists the developers involved in the project. Here, a developer with the id `architect` is listed.

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

<SwmSnippet path="/admin/broadleaf-open-admin-platform/pom.xml" line="34">

---

# Build Configuration

The `build` section defines how the project should be built. It includes the configuration for various plugins like `build-helper-maven-plugin`, `maven-jar-plugin`, and `gmavenplus-plugin`. It also specifies the resources to be included in the build.

```xml
    <build>
        <plugins>
            <plugin>
                <groupId>org.codehaus.mojo</groupId>
                <artifactId>build-helper-maven-plugin</artifactId>
                <executions>
                    <execution>
                        <id>timestamp-property</id>
                        <phase>generate-sources</phase>
                        <goals>
                            <goal>timestamp-property</goal>
                        </goals>
                        <configuration>
                            <name>openAdminBuildDate</name>
                            <pattern>yyyy-MM-dd HH:mm:ss</pattern>
                        </configuration>
                    </execution>
                </executions>
            </plugin>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-open-admin-platform/pom.xml" line="69">

---

The `resources` section specifies the directories that should be treated as resources. Here, it includes the `src/main/resources/open_admin_style` directory, the `src/main/resources` directory, and the `src/main/java` directory.

```xml
        <resources>
            <resource>
                <directory>src/main/resources/open_admin_style</directory>
                <filtering>false</filtering>
                <targetPath>open_admin_style</targetPath>
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

<SwmSnippet path="/admin/broadleaf-open-admin-platform/pom.xml" line="89">

---

The `pluginManagement` section provides configuration for plugins that are used in the build lifecycle. Here, it includes configuration for the `org.eclipse.m2e` lifecycle-mapping plugin.

```xml
        <pluginManagement>
            <plugins>
                <!--This plugin's configuration is used to store Eclipse
                    m2e settings only. It has no influence on the Maven build itself. -->
                <plugin>
                    <groupId>org.eclipse.m2e</groupId>
                    <artifactId>lifecycle-mapping</artifactId>
                    <version>1.0.0</version>
                    <configuration>
                        <lifecycleMappingMetadata>
                            <pluginExecutions>
                                <pluginExecution>
                                    <pluginExecutionFilter>
                                        <groupId>
                                            org.codehaus.mojo
                                        </groupId>
                                        <artifactId>
                                            build-helper-maven-plugin
                                        </artifactId>
                                        <versionRange>
                                            [1.7,)
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-open-admin-platform/pom.xml" line="128">

---

# Profiles Configuration

The `profiles` section defines a set of configurations that can be activated under certain conditions. Here, a profile with the id `blc-development` is defined, which includes the `jrebel-maven-plugin` in its build configuration.

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

<SwmSnippet path="/admin/broadleaf-open-admin-platform/pom.xml" line="150">

---

# Dependencies Configuration

The `dependencies` section lists all the dependencies required by the project. It includes dependencies like `broadleaf-common`, `junit`, `commons-pool`, `commons-fileupload`, and others.

```xml
    <dependencies>
        <dependency>
            <groupId>org.broadleafcommerce</groupId>
            <artifactId>broadleaf-common</artifactId>
        </dependency>
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
        </dependency>
        <dependency>
            <groupId>commons-pool</groupId>
            <artifactId>commons-pool</artifactId>
        </dependency>
        <dependency>
            <groupId>commons-fileupload</groupId>
            <artifactId>commons-fileupload</artifactId>
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
