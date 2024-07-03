---
title: Understanding JPA Convert in BroadleafCommerce-demo
---
JPA Convert in BroadleafCommerce-demo refers to a package that contains classes responsible for transforming or converting certain aspects of the Java Persistence API (JPA) entities. These transformations or conversions can include altering table names, handling inheritance, and managing persistence units. The package provides a set of class transformers, each designed to perform a specific type of transformation.

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/extensibility/jpa/convert/EntityMarkerClassTransformer.java" line="18">

---

# EntityMarkerClassTransformer

The EntityMarkerClassTransformer class is an example of a class transformer. It is used to mark entity classes during the transformation process.

```java
package org.broadleafcommerce.common.extensibility.jpa.convert;

import org.apache.commons.logging.Log;
```

---

</SwmSnippet>

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/extensibility/jpa/convert/AlterTableNameClassTransformer.java" line="18">

---

# AlterTableNameClassTransformer

The AlterTableNameClassTransformer class is another example of a class transformer. It is used to alter the table names of entity classes during the transformation process.

```java
package org.broadleafcommerce.common.extensibility.jpa.convert;

import org.apache.commons.lang3.StringUtils;
```

---

</SwmSnippet>

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/extensibility/jpa/convert/MaterializedClobTypeClassTransformer.java" line="18">

---

# MaterializedClobTypeClassTransformer

The MaterializedClobTypeClassTransformer class is a class transformer that is used to handle CLOB data types during the transformation process.

```java
package org.broadleafcommerce.common.extensibility.jpa.convert;

import org.apache.commons.logging.Log;
```

---

</SwmSnippet>

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/extensibility/jpa/convert/BroadleafClassTransformer.java" line="18">

---

# BroadleafClassTransformer

The BroadleafClassTransformer class is a base class for all class transformers in the BroadleafCommerce-demo repository. It implements the javax.persistence.spi.ClassTransformer interface.

```java
package org.broadleafcommerce.common.extensibility.jpa.convert;

import javax.persistence.spi.ClassTransformer;
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
