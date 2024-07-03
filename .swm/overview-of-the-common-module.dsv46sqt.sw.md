---
title: Overview of the 'Common' Module
---
In the BroadleafCommerce-demo repository, 'Common' refers to a module that contains shared resources and utilities used across the application. It includes classes for handling caching, configuration, file management, and more. For instance, the 'HydrationScanner' class in the 'common' module is used for managing the hydration of cached entities. The 'common' module also contains property files for configuring various aspects of the application, such as enabling internationalization or allowing circular dependencies.

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/cache/engine/HydrationScanner.java" line="43">

---

## HydrationScanner Class

The HydrationScanner class is a part of the cache management system in the application. It implements ClassVisitor, FieldVisitor, and AnnotationVisitor interfaces. It is used to scan and manage the hydration of entities in the cache.

```java
public class HydrationScanner implements ClassVisitor, FieldVisitor, AnnotationVisitor {
    
    private static final int CLASSSTAGE = 0;
    private static final int FIELDSTAGE = 1;
    
    @SuppressWarnings("unchecked")
    public HydrationScanner(Class topEntityClass, Class entityClass) {
        this.topEntityClass = topEntityClass;
        this.entityClass = entityClass;
    }
    
    private String cacheRegion;
    private Map<String, Method[]> idMutators = new HashMap<String, Method[]>();
    private Map<String, HydrationItemDescriptor> cacheMutators = new HashMap<String, HydrationItemDescriptor>();
    @SuppressWarnings("unchecked")
    private final Class entityClass;
    @SuppressWarnings("unchecked")
    private final Class topEntityClass;
    
    private int stage = CLASSSTAGE;
    @SuppressWarnings("unchecked")
```

---

</SwmSnippet>

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/config/BroadleafSharedOverrideProfileAwarePropertySource.java" line="84">

---

## BroadleafSharedOverrideProfileAwarePropertySource Interface

The BroadleafSharedOverrideProfileAwarePropertySource interface is used for configuration management. It provides a mechanism to override the properties based on the current Spring profile. It is used to manage the properties of the application in different environments.

```java
public interface BroadleafSharedOverrideProfileAwarePropertySource {

    public static final int DEFAULT_ORDER = 100;
    
    /**
     * The folder on the classpath that contains a {@code common.properties} file. Note that this cannot be prefixed with {@code "classpath:"} or any of those
     * varieties since this drives the creation of an {@link ClassPathResource} already based on this location.
     */
    public String getClasspathFolder();

}
```

---

</SwmSnippet>

## Common Resources

The 'common_style' directory contains shared resources such as fonts, templates, JavaScript, and CSS files. These resources can be used across the application to maintain a consistent look and feel.

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
