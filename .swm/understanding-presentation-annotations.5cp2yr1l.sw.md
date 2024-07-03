---
title: Understanding Presentation Annotations
---
In the BroadleafCommerce-demo repository, 'Presentation' refers to a set of annotations and interfaces that are used to customize the admin interface. These annotations are used to define how a field or class should be presented in the admin interface, including details such as visibility, validation, grouping, and more. For example, the `AdminPresentation` annotation is used to customize the presentation of a specific field in the admin interface. Similarly, `AdminPresentationCollection` is used for collections, and `AdminPresentationMap` is used for maps. There are also annotations like `AdminPresentationMergeOverride` and `AdminPresentationMergeOverrides` that allow for overriding these presentation settings.

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/presentation/AdminPresentation.java" line="35">

---

# AdminPresentation Annotation

The @AdminPresentation annotation is used to customize the display properties of a field in the admin interface. It can be used to specify the friendly name, group, order, and other properties of the field.

```java
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface AdminPresentation {
    
    /**
```

---

</SwmSnippet>

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/presentation/AdminPresentationToOneLookup.java" line="30">

---

# AdminPresentationToOneLookup Annotation

The @AdminPresentationToOneLookup annotation is used to customize the display of a to-one relationship in the admin interface. It can be used to specify the display property, custom criteria, lookup type, and other properties of the relationship.

```java
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface AdminPresentationToOneLookup {

    /**
     * <p>Optional - only required if the display property is other than "name"</p>
     *
     * <p>Specify the property on a lookup class that should be used as the value to display to the user in
     * a form in the admin tool UI</p>
     *
     * @return the property on the lookup class containing the displayable value
     */
    String lookupDisplayProperty() default "";

    /**
     * <p>Optional - only required if you need to specially handle crud operations for this
     * specific collection on the server</p>
     *
     * <p>Custom string values that will be passed to the server during Read and Inspect operations on the
     * entity lookup. This allows for the creation of a custom persistence handler to handle both
     * inspect and fetch phase operations. Presumably, one could use this to
```

---

</SwmSnippet>

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/presentation/override/AdminPresentationMergeOverride.java" line="37">

---

# AdminPresentationMergeOverride Class

The AdminPresentationMergeOverride class is used to override the presentation properties of a field. It allows developers to specify a new configuration for a field, which will be merged with the existing configuration.

```java
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface AdminPresentationMergeOverride {

    /**
     * The name of the property whose admin presentation annotation should be overwritten
     *
     * @return the name of the property that should be overwritten
     */
    String name();

    /**
     * The array of override configuration values. Each entry correlates to a property on
     * {@link org.broadleafcommerce.common.presentation.AdminPresentation},
     * {@link org.broadleafcommerce.common.presentation.AdminPresentationToOneLookup},
     * {@link org.broadleafcommerce.common.presentation.AdminPresentationDataDrivenEnumeration},
     * {@link org.broadleafcommerce.common.presentation.AdminPresentationAdornedTargetCollection},
     * {@link org.broadleafcommerce.common.presentation.AdminPresentationCollection} or
     * {@link org.broadleafcommerce.common.presentation.AdminPresentationMap}
     *
     * @return The array of override configuration values.
```

---

</SwmSnippet>

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/presentation/override/AdminPresentationMergeOverrides.java" line="37">

---

# AdminPresentationMergeOverrides Class

The AdminPresentationMergeOverrides class is used to specify multiple merge overrides for a field. It allows developers to specify an array of AdminPresentationMergeOverride instances, each of which will be applied to the field.

```java
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface AdminPresentationMergeOverrides {

    /**
     * The new configurations for each field targeted for override
     *
     * @return field specific overrides
     */
    AdminPresentationMergeOverride[] value();

}
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
