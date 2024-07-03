---
title: Basic Concepts of the Broadleaf Content Management Module
---
The Broadleaf content management module, as indicated by its name, is a part of the Broadleaf Commerce framework that handles content management. This module is responsible for managing structured content, which includes any content that follows a specific, predefined format. It provides services and classes that allow for the creation, modification, and retrieval of such content.

The module is organized into several packages and directories, each serving a specific purpose. For instance, the 'org.broadleafcommerce.cms.structure.service' package contains services related to structured content, while the 'org.broadleafcommerce.cms.web.processor' package contains processors for handling web content.

The module also relies on other Broadleaf and third-party libraries. For example, it uses the 'org.broadleafcommerce.presentation.model.BroadleafTemplateContext' for handling template contexts, and the 'org.broadleafcommerce.common.extension.ExtensionManager' for managing extensions.

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/structure/service/type/StructuredContentRuleType.java" line="1">

---

# Broadleaf Content Management Module Structure

The `StructuredContentRuleType.java` file is an example of the types of structured content that can be managed by the Broadleaf content management module.

```java
/*-
 * #%L
 * BroadleafCommerce CMS Module
 * %%
 * Copyright (C) 2009 - 2024 Broadleaf Commerce
 * %%
 * Licensed under the Broadleaf Fair Use License Agreement, Version 1.0
 * (the "Fair Use License" located  at http://license.broadleafcommerce.org/fair_use_license-1.0.txt)
 * unless the restrictions on use therein are violated and require payment to Broadleaf in which case
 * the Broadleaf End User License Agreement (EULA), Version 1.1
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/pom.xml" line="1">

---

# Broadleaf Content Management Module Dependency

The `pom.xml` file shows how the Broadleaf content management module is included as a dependency in a project. It also provides information about the module, such as its artifact ID, packaging type, and version.

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
    <licenses>
        <license>
            <name>Broadleaf Fair Use 1.0</name>
            <url>http://license.broadleafcommerce.org/fair_use_license-1.0.txt</url>
            <distribution>repo</distribution>
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/web/processor/ContentProcessor.java" line="34">

---

# Broadleaf Content Management Module Usage

The `ContentProcessor.java` file is an example of how the Broadleaf content management module is used in the code. It shows how the module's classes and services are imported and used to process content.

```java
import org.broadleafcommerce.common.structure.dto.StructuredContentDTO;
import org.broadleafcommerce.common.time.SystemTime;
import org.broadleafcommerce.common.web.BroadleafRequestContext;
import org.broadleafcommerce.common.web.deeplink.DeepLink;
import org.broadleafcommerce.presentation.condition.ConditionalOnTemplating;
```

---

</SwmSnippet>

# Broadleaf Commerce Content Management Module

Broadleaf Commerce Content Management Module

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/file/service/StaticAssetStorageService.java" line="33">

---

## StaticAssetStorageService

The `StaticAssetStorageService` interface defines methods for handling static assets in the Broadleaf Commerce framework. These methods include finding, creating, reading, saving, and deleting static assets. It also includes methods for creating blobs from files or input streams, and for creating static asset storage from files or input streams. The methods in this interface are not typically used since Broadleaf 3.0 as the preferred method for storing assets is on a shared-filesystem.

```java
public interface StaticAssetStorageService {

    /**
     * Returns a StaticAssetStorage object.   Assumes that the asset is stored in the Database.
     * 
     * Storing Assets in the DB is not the preferred mechanism for Broadleaf as of 3.0 so in most cases, this 
     * method would not be used by Broadleaf implementations.
     * 
     * @param id
     * @return
     */
    StaticAssetStorage findStaticAssetStorageById(Long id);

    /**
     * @deprecated   Use createStaticAssetStorageFromFile instead.
     * @return
     */
    @Deprecated
    StaticAssetStorage create();

    /**
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/file/service/StaticAssetService.java" line="32">

---

## StaticAssetService

The `StaticAssetService` interface defines methods for handling static assets in the Broadleaf Commerce framework. These methods include finding static assets by ID or URL, reading all static assets, finding the total static asset count, creating static assets from files or input streams, and adding, updating, or deleting static assets. It also includes methods for getting and converting static asset URLs.

```java
public interface StaticAssetService {


    public StaticAsset findStaticAssetById(Long id);
    
    public List<StaticAsset> readAllStaticAssets();

    public StaticAsset findStaticAssetByFullUrl(String fullUrl);

    Long findTotalStaticAssetCount();

    /**
     * <p>
     * Used when uploading a file to Broadleaf.    This method will create the corresponding 
     * asset.   
     * 
     * <p>
     * Depending on the the implementation, the actual asset may be saved to the DB or to 
     * the file system.    The default implementation {@link StaticAssetServiceImpl} has a 
     * environment properties that determine this behavior <code>asset.use.filesystem.storage</code>
     * 
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
