---
title: Overview of Extensibility in BroadleafCommerce-demo
---
Extensibility in BroadleafCommerce-demo refers to the ability of the application to be extended or customized. This is achieved through the use of packages such as 'org.broadleafcommerce.common.extensibility' and 'org.broadleafcommerce.common.extensibility.context.merge'. These packages provide the necessary tools and interfaces to allow developers to add or modify functionalities without altering the core codebase. This is particularly important in an e-commerce framework like Broadleaf, where different businesses may have unique requirements that are not covered by the default functionalities.

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/extensibility/FrameworkXmlBeanDefinitionReader.java" line="19">

---

# Extensibility Packages

This is an example of a file in the org.broadleafcommerce.common.extensibility package. This package contains classes and interfaces that can be extended or implemented to customize the core functionalities of the framework.

```java
package org.broadleafcommerce.common.extensibility;

import org.springframework.beans.factory.BeanDefinitionStoreException;
```

---

</SwmSnippet>

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/extensibility/context/merge/Merge.java" line="18">

---

# Merge Package

This is an example of a file in the org.broadleafcommerce.common.extensibility.context.merge package. This package provides classes and interfaces for merging custom configurations with the existing ones, allowing developers to modify the behavior of the framework without altering the core codebase.

```java
package org.broadleafcommerce.common.extensibility.context.merge;

import static java.lang.annotation.RetentionPolicy.RUNTIME;
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
