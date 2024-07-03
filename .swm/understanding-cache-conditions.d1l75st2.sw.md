---
title: Understanding Cache Conditions
---
Cache Conditions in BroadleafCommerce-demo refer to the conditions that determine whether caching is enabled or not. This is primarily handled by the `OnEhCacheCondition` class, which implements the `Condition` interface from the Spring framework. The `matches` method in this class checks if the Ehcache is on the classpath, and returns a boolean value accordingly. This condition is used in the `ConditionalOnEhCache` annotation to decide whether a class should be instantiated as a bean. If Ehcache is on the classpath, the condition is met and the class is instantiated.

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/extensibility/cache/ehcache/OnEhCacheCondition.java" line="30">

---

# OnEhCacheCondition

This is the `OnEhCacheCondition` class. It implements the `Condition` interface from Spring, which means it provides a `matches` method that determines whether a condition is met. In this case, the condition is whether Ehcache is on the classpath.

```java
public class OnEhCacheCondition implements Condition {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        try {
            Class.forName("org.ehcache.jsr107.EhcacheCachingProvider");
            return true;
        } catch (Exception e) {
            return false;
        }
    }
```

---

</SwmSnippet>

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/extensibility/cache/ehcache/ConditionalOnEhCache.java" line="34">

---

# ConditionalOnEhCache

This is the `ConditionalOnEhCache` annotation. It is annotated with `@Conditional(OnEhCacheCondition.class)`, which means that it will only be applied if the `OnEhCacheCondition` returns true. This annotation can be used to annotate beans that should only be instantiated if Ehcache is on the classpath.

```java
@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Conditional(OnEhCacheCondition.class)
public @interface ConditionalOnEhCache {

}
```

---

</SwmSnippet>

# Cache Conditions Functions

This section discusses the functions of Cache Conditions in the BroadleafCommerce-demo project.

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/extensibility/cache/ehcache/OnEhCacheCondition.java" line="30">

---

## OnEhCacheCondition Class

The OnEhCacheCondition class implements the Condition interface from the Spring framework. It has a `matches` method that checks if ehcache is on the classpath. If ehcache is present, the method returns true; otherwise, it returns false.

```java
public class OnEhCacheCondition implements Condition {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        try {
            Class.forName("org.ehcache.jsr107.EhcacheCachingProvider");
            return true;
        } catch (Exception e) {
            return false;
        }
    }
```

---

</SwmSnippet>

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/extensibility/cache/ehcache/ConditionalOnEhCache.java" line="34">

---

## ConditionalOnEhCache Annotation

The ConditionalOnEhCache is an annotation used to indicate that a class should be instantiated as a bean if ehcache is on the classpath. It uses the OnEhCacheCondition class to check the condition.

```java
@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Conditional(OnEhCacheCondition.class)
public @interface ConditionalOnEhCache {
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
