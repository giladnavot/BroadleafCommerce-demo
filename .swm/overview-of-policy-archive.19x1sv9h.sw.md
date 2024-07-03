---
title: Overview of Policy Archive
---
The `ClonePolicyArchive` is an annotation used in the Broadleaf Commerce framework. It is used to mark certain fields whose persistence is managed outside the admin pipeline. This is particularly useful for collection fields. When a field is marked with this annotation, the system knows to archive records appropriately during enterprise operations. It's important to note that this annotation should only be applied to sandbox aware fields.

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/extensibility/jpa/clone/ClonePolicyArchive.java" line="33">

---

# ClonePolicyArchive Annotation

This is the definition of the `ClonePolicyArchive` annotation. It is retained at runtime and can be applied to fields.

```java
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface ClonePolicyArchive {

}
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/catalog/domain/SkuImpl.java" line="356">

---

# Usage of ClonePolicyArchive

Here is an example of how the `ClonePolicyArchive` annotation is used in the `SkuImpl` class. It is applied to a field to indicate that the system should archive records for this field during enterprise operations.

```java
    @ClonePolicyArchive
    //Use a Set instead of a List - see https://github.com/BroadleafCommerce/BroadleafCommerce/issues/917
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/field/domain/FieldGroupImpl.java" line="79">

---

Another example of `ClonePolicyArchive` usage can be seen in the `FieldGroupImpl` class. The annotation is applied to the `fieldDefinitions` field.

```java
    @ClonePolicyArchive
    protected List<FieldDefinition> fieldDefinitions = new ArrayList<FieldDefinition>();
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
