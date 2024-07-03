---
title: BroadleafCommerces handling and management of structured content
---
This document will cover the handling and management of structured content in BroadleafCommerce. We'll cover:

1. What is structured content in BroadleafCommerce
2. How structured content is defined
3. How structured content is managed

# What is structured content in BroadleafCommerce

Structured content in BroadleafCommerce refers to the content that has a defined data structure. It is managed through various classes and interfaces in the `org.broadleafcommerce.cms.structure.domain` package.

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/structure/domain/StructuredContent.java" line="18">

---

# How structured content is defined

The `StructuredContent` class in the `org.broadleafcommerce.cms.structure.domain` package is a key part of the structured content system. It represents a piece of structured content in the system.

```java
package org.broadleafcommerce.cms.structure.domain;

import org.broadleafcommerce.common.copy.MultiTenantCloneable;
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/structure/domain/StructuredContentType.java" line="18">

---

The `StructuredContentType` class represents the type of the structured content. It defines the structure of the content.

```java
package org.broadleafcommerce.cms.structure.domain;

import org.broadleafcommerce.common.copy.MultiTenantCloneable;
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/structure/service/StructuredContentService.java" line="18">

---

# How structured content is managed

The `StructuredContentService` interface provides methods for managing structured content, such as creating, retrieving, updating, and deleting structured content.

```java
package org.broadleafcommerce.cms.structure.service;

import org.broadleafcommerce.cms.structure.domain.StructuredContent;
import org.broadleafcommerce.cms.structure.domain.StructuredContentType;
import org.broadleafcommerce.common.locale.domain.Locale;
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/admin/web/controller/AdminStructuredContentController.java" line="18">

---

The `AdminStructuredContentController` class is a web controller that handles HTTP requests related to structured content management in the admin interface.

```java
package org.broadleafcommerce.cms.admin.web.controller;

import org.broadleafcommerce.cms.structure.domain.StructuredContent;
import org.broadleafcommerce.cms.structure.domain.StructuredContentType;
import org.broadleafcommerce.openadmin.web.controller.entity.AdminBasicEntityController;
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="follow-up"><sup>Powered by [Swimm](/)</sup></SwmMeta>
