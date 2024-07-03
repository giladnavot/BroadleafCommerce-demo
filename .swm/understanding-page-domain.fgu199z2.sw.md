---
title: Understanding Page Domain
---
Page Domain in BroadleafCommerce-demo refers to a package that contains classes related to the management of pages in the e-commerce framework. This includes classes for page rules, templates, fields, attributes, and more. These classes provide the functionality for creating, managing, and displaying pages on the e-commerce site.

For example, the `Page` class represents a page on the website, while the `PageTemplate` class represents the template used to render the page. The `PageRule` class is used to define rules for when a page should be displayed, and the `PageField` and `PageAttribute` classes are used to manage custom fields and attributes for pages.

Overall, the Page Domain package is a crucial part of the BroadleafCommerce-demo, as it provides the necessary functionality for managing the pages of the e-commerce site.

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/page/domain/Page.java" line="18">

---

# Page Domain Classes

The `Page` class is a part of the Page Domain. It represents a page in the e-commerce application.

```java
package org.broadleafcommerce.cms.page.domain;

import org.broadleafcommerce.common.copy.MultiTenantCloneable;
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/page/domain/PageField.java" line="18">

---

# PageField Class

The `PageField` class is also a part of the Page Domain. It represents a field in a page.

```java
package org.broadleafcommerce.cms.page.domain;

import org.broadleafcommerce.common.copy.MultiTenantCloneable;
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/page/domain/PageField.java" line="41">

---

# Using the PageField Class

You can use the `getPage` and `setPage` methods in the `PageField` class to get and set the page of a field respectively.

```java
    public Page getPage();

    public void setPage(Page page);
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
