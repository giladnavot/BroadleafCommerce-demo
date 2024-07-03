---
title: Exploring the Resource Resolver Strategy
---
The Resource Resolver Strategy in BroadleafCommerce-demo refers to the approach used to resolve resources in the application. It is implemented in the `BLVersionResourceResolverDefaultStrategyMap` class, which extends `HashMap` and maps a string to a `VersionStrategy` object. This class is annotated with `@Component` to indicate that it is an autodetectable Spring Bean. The `initIt` method, annotated with `@PostConstruct`, initializes the map with a default `ContentVersionStrategy` for all resources, denoted by the `/**` key. This strategy is used to handle versioning of static resources, which is crucial for proper caching in web applications.

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/web/resource/resolver/BLVersionResourceResolverDefaultStrategyMap.java" line="28">

---

# Resource Resolver Strategy Implementation

The `BLVersionResourceResolverDefaultStrategyMap` class is the implementation of the Resource Resolver Strategy. It extends `HashMap<String, VersionStrategy>` to map resource paths to their corresponding version strategies. The `initIt` method is annotated with `@PostConstruct`, meaning it will be executed after dependency injection is done to perform any initialization.

```java
@Component("blVersionResourceResolverStrategyMap")
public class BLVersionResourceResolverDefaultStrategyMap<T, V> extends HashMap<String, VersionStrategy> {

    private static final long serialVersionUID = -3345223635822341852L;

    @PostConstruct
    public void initIt() throws Exception {
        this.put("/**", (VersionStrategy) new ContentVersionStrategy());
    }

}
```

---

</SwmSnippet>

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/web/resource/resolver/BLVersionResourceResolverDefaultStrategyMap.java" line="35">

---

# Version Strategy Mapping

In the `initIt` method, the `ContentVersionStrategy` is mapped to all resource paths (`/**`). This strategy appends a content-based version to the resource URL, helping in cache busting.

```java
        this.put("/**", (VersionStrategy) new ContentVersionStrategy());
```

---

</SwmSnippet>

# Resource Resolver Strategy

Understanding Resource Resolver Strategy

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/web/resource/resolver/BLVersionResourceResolverDefaultStrategyMap.java" line="28">

---

## Resource Resolver Strategy

The Resource Resolver Strategy is implemented in the class `BLVersionResourceResolverDefaultStrategyMap`. This class extends HashMap and is used to map different versions of resources. The key is a string representing the resource path and the value is a VersionStrategy object.

```java
@Component("blVersionResourceResolverStrategyMap")
public class BLVersionResourceResolverDefaultStrategyMap<T, V> extends HashMap<String, VersionStrategy> {

    private static final long serialVersionUID = -3345223635822341852L;

    @PostConstruct
    public void initIt() throws Exception {
        this.put("/**", (VersionStrategy) new ContentVersionStrategy());
    }

}
```

---

</SwmSnippet>

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/web/resource/resolver/BLVersionResourceResolverDefaultStrategyMap.java" line="33">

---

### initIt function

The `initIt` function is annotated with `@PostConstruct`, which means it is executed after the instance of the class is created. This function puts a new entry into the map with the key as `/**` and the value as a new instance of `ContentVersionStrategy`. This means that by default, all resources will use the `ContentVersionStrategy` for versioning.

```java
    @PostConstruct
    public void initIt() throws Exception {
        this.put("/**", (VersionStrategy) new ContentVersionStrategy());
    }
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
