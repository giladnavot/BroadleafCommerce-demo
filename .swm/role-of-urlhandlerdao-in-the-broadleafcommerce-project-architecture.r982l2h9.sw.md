---
title: Role of URLHandlerDao in the BroadleafCommerce Project Architecture
---
This document will cover the role and functionality of the URLHandlerDao within the larger project architecture. We'll cover:

1. What is URLHandlerDao?
2. How is URLHandlerDao implemented?
3. How does URLHandlerDao fit within the larger project architecture?

# What is URLHandlerDao?

URLHandlerDao is a Data Access Object (DAO) in the Broadleaf Commerce framework. DAOs are a common pattern in Java applications to abstract and encapsulate all access to the data source. In this case, URLHandlerDao is responsible for handling URL-related data operations.

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/url/dao/URLHandlerDao.java" line="1">

---

# How is URLHandlerDao implemented?

URLHandlerDao is an interface that defines the contract for URL-related data operations. It declares methods for creating, reading, updating, and deleting URL handlers.

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
 * (the "Commercial License" located at http://license.broadleafcommerce.org/commercial_license-1.1.txt)
 * shall apply.
 * 
 * Alternatively, the Commercial License may be replaced with a mutually agreed upon license (the "Custom License")
 * between you and Broadleaf Commerce. You may not use this file except in compliance with the applicable license.
 * #L%
 */
package org.broadleafcommerce.cms.url.dao;

import org.broadleafcommerce.cms.url.domain.URLHandler;

```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/url/dao/URlHandlerDaoImpl.java" line="1">

---

URLHandlerDaoImpl is the implementation of the URLHandlerDao interface. It provides the concrete implementation of the methods declared in the URLHandlerDao interface.

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
 * (the "Commercial License" located at http://license.broadleafcommerce.org/commercial_license-1.1.txt)
 * shall apply.
 * 
 * Alternatively, the Commercial License may be replaced with a mutually agreed upon license (the "Custom License")
 * between you and Broadleaf Commerce. You may not use this file except in compliance with the applicable license.
 * #L%
 */
package org.broadleafcommerce.cms.url.dao;

import org.broadleafcommerce.cms.url.domain.URLHandler;
import org.broadleafcommerce.cms.url.domain.URLHandlerImpl;
```

---

</SwmSnippet>

# How does URLHandlerDao fit within the larger project architecture?

In the larger project architecture, URLHandlerDao plays a crucial role in managing URL-related data. It interacts with the database to perform CRUD operations on URL handlers. Other components of the system, such as services and controllers, use URLHandlerDao to interact with URL-related data, thus keeping the data access logic encapsulated and separate from the business logic.

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="follow-up"><sup>Powered by [Swimm](/)</sup></SwmMeta>
