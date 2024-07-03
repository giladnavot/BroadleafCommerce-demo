---
title: Understanding JPA Clone
---
JPA Clone in BroadleafCommerce-demo refers to a set of functionalities provided by the Broadleaf Commerce framework to handle cloning of Java Persistence API (JPA) entities. This is particularly useful in scenarios where you need to create a copy of an entity with its state for separate manipulation. The package `org.broadleafcommerce.common.extensibility.jpa.clone` contains several classes and interfaces that define and implement these functionalities.

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/extensibility/jpa/clone/ClonePolicy.java" line="18">

---

## ClonePolicy

The @ClonePolicy annotation can be used to specify the clone behavior for a JPA entity. You can use this annotation on a class or a field.

```java
package org.broadleafcommerce.common.extensibility.jpa.clone;

import java.lang.annotation.ElementType;
```

---

</SwmSnippet>

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/extensibility/jpa/clone/ClonePolicyCollection.java" line="18">

---

## ClonePolicyCollection

The @ClonePolicyCollection annotation can be used to specify the clone behavior for a collection field in a JPA entity. This allows you to control how the elements of the collection are cloned.

```java
package org.broadleafcommerce.common.extensibility.jpa.clone;

import java.lang.annotation.ElementType;
```

---

</SwmSnippet>

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/extensibility/jpa/clone/IgnoreEnterpriseBehavior.java" line="18">

---

## IgnoreEnterpriseBehavior

The @IgnoreEnterpriseBehavior annotation can be used to indicate that a field should not be cloned. This is useful when you have fields that should not be copied to the clone, such as identifiers or fields that are managed by the application.

```java
package org.broadleafcommerce.common.extensibility.jpa.clone;

import java.lang.annotation.ElementType;
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
