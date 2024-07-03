---
title: Maven Usage in the Core Module
---
This document provides a detailed explanation of how Maven is used in the BroadleafCommerce-demo repository, specifically within the 'core' module. It will cover the Maven configuration file (pom.xml) and its role in the project.

<SwmSnippet path="/core/pom.xml" line="1">

---

# Maven Configuration in core/pom.xml

The Maven configuration file, pom.xml, is the fundamental unit of work in Maven. It is an XML file that contains information about the project and configuration details used by Maven to build the project. In the 'core' module of BroadleafCommerce-demo, the pom.xml file defines the parent project, the artifactId, the packaging type, and the name of the module. It also specifies the project's URL, properties, licenses, and developers. Furthermore, it lists the modules that are part of the 'core' module.

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/maven-v4_0_0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <artifactId>broadleaf</artifactId>
        <groupId>org.broadleafcommerce</groupId>
        <version>6.2.11-SNAPSHOT</version>
    </parent>
    <artifactId>core</artifactId>
    <packaging>pom</packaging>
    <name>BroadleafCommerce Core</name>
    <description>BroadleafCommerce Core Mid Level Project</description>
    <url>https://www.broadleafcommerce.com</url>
    <properties>
        <project.uri>${project.baseUri}/../</project.uri>
    </properties>
    <licenses>
        <license>
            <name>Broadleaf Fair Use 1.0</name>
            <url>http://license.broadleafcommerce.org/fair_use_license-1.0.txt</url>
            <distribution>repo</distribution>
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/search/service/solr/SolrConfiguration.java" line="43">

---

# SolrConfiguration and Maven

The SolrConfiguration class is part of the 'core' module and is built using Maven. This class holds the Solr server and is initialized in SolrSearchServiceImpl and used in SolrIndexServiceImpl. It contains various configurations for the Solr server, such as the primary server, reindex server, and admin server. These configurations are essential for the functioning of the Solr server in the BroadleafCommerce-demo project.

```java
/**
 * <p>
 * Provides a class that will statically hold the Solr server.
 * 
 * <p>
 * This is initialized in {@link SolrSearchServiceImpl} and used in {@link SolrIndexServiceImpl}
 * 
 * @author Andre Azzolini (apazzolini)
 */
public class SolrConfiguration implements InitializingBean {
    private static final Log LOG = LogFactory.getLog(SolrConfiguration.class);

    protected String primaryName = null;
    protected String reindexName = null;

    // this is a field to differentiate between collections of items and it must be non-blank
    protected String namespace = "d";

    protected SolrClient adminServer = null;
    protected SolrClient primaryServer = null;
    protected SolrClient reindexServer = null;
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="build-tool"><sup>Powered by [Swimm](/)</sup></SwmMeta>
