---
title: Getting Started with ClonePolicy
---
The `ClonePolicy` is an annotation in Broadleaf Commerce that allows a \*ToOne field to have sandboxable behavior. This is typically required when a field in question is not already annotated with an `@AdminPresentation` annotation. This usually happens when a Custom Persistence Handler (CPH) is trying to work with a field on an entity that is not exposed in the UI and that field needs to be sandbox aware. The `ClonePolicy` annotation is retained at runtime and can be applied to fields.

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/extensibility/jpa/clone/ClonePolicy.java" line="32">

---

# ClonePolicy Annotation

This is the definition of the ClonePolicy annotation. It is retained at runtime and can be applied to fields. It has a method 'toOneProperty' which can be used to specify a property for the \*ToOne field.

```java
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface ClonePolicy {

    String toOneProperty() default "";

}
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/catalog/domain/ProductImpl.java" line="239">

---

# Usage of ClonePolicy

Here is an example of how ClonePolicy is used in the codebase. In this case, it is applied to the 'defaultSku' field in the ProductImpl class, with 'defaultProduct' specified as the 'toOneProperty'.

```java
    @JoinColumn(name = "DEFAULT_SKU_ID")
    @ClonePolicy(toOneProperty = "defaultProduct")
    protected Sku defaultSku;
```

---

</SwmSnippet>

# ClonePolicy Functions

Understanding ClonePolicy

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/extensibility/jpa/clone/ClonePolicy.java" line="25">

---

## ClonePolicy Annotation

The `ClonePolicy` annotation is used to allow a \*ToOne field to have sandboxable behavior. This is only required when the field in question is not already annotated with an @AdminPresentation annotation. This generally happens when a CPH is trying to work with a field on an entity that is not exposed in the UI and that field needs to be sandbox aware.

```java
/**
 *
 * @ClonePolicy will allow a *ToOne field to have sandboxable behavior. This is only required when the field in question is not already
 * annotated with an @AdminPresentation annotation. Generally this happens when a CPH is trying to work with a field on an entity that
 * is not exposed in the UI and that field needs to be sandbox aware.
 * @author Jeff Fischer
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface ClonePolicy {

    String toOneProperty() default "";

}
```

---

</SwmSnippet>

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/extensibility/jpa/clone/ClonePolicy.java" line="36">

---

## toOneProperty Function

The `toOneProperty` function is a part of the `ClonePolicy` annotation. It is used to specify the property of the \*ToOne field that should be considered for sandboxable behavior.

```java
    String toOneProperty() default "";
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
