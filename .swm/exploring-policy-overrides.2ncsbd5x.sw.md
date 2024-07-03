---
title: Exploring Policy Overrides
---
Policy Override in BroadleafCommerce-demo refers to a set of annotations that allow developers to modify the behavior of certain aspects of the application. These annotations include `ClonePolicyCollectionOverride`, `ClonePolicyMapOverride`, and `ClonePolicyAdornedTargetCollectionOverride`. They are used to override the default cloning behavior of collections, maps, and adorned target collections respectively in the Broadleaf Commerce framework. This is particularly useful when you need to customize the way data is copied or cloned in your application.

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/extensibility/jpa/clone/ClonePolicyCollectionOverride.java" line="29">

---

# ClonePolicyCollectionOverride

This annotation is used to override the cloning policy for a collection. It can be applied to a field to change how the collection is cloned.

```java
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface ClonePolicyCollectionOverride {

}
```

---

</SwmSnippet>

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/extensibility/jpa/clone/ClonePolicyMapOverride.java" line="29">

---

# ClonePolicyMapOverride

This annotation is used to override the cloning policy for a map. It can be applied to a field to change how the map is cloned.

```java
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface ClonePolicyMapOverride {

}
```

---

</SwmSnippet>

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/extensibility/jpa/clone/ClonePolicyAdornedTargetCollectionOverride.java" line="29">

---

# ClonePolicyAdornedTargetCollectionOverride

This annotation is used to override the cloning policy for an adorned target collection. It can be applied to a field to change how the adorned target collection is cloned.

```java
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface ClonePolicyAdornedTargetCollectionOverride {

}
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
