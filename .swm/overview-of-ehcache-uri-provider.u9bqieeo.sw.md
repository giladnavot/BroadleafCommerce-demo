---
title: Overview of EhCache URI Provider
---
The EhCache URI Provider in BroadleafCommerce-demo refers to the `DefaultEhCacheUriProvider` class. This class is responsible for providing the URI for the EhCache configuration file. It extends the `DefaultJCacheUriProvider` and implements `ApplicationContextAware` and `InitializingBean` interfaces. The `getJCacheUri` method returns the URI of the cache manager. The `afterPropertiesSet` method is used to merge cache locations after the properties are set. The `mergeCacheLocations` method merges the cache locations and creates a temporary merged XML file. The `setApplicationContext` method sets the application context, and the `setConfigLocations` method sets the configuration locations.

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/extensibility/cache/ehcache/DefaultEhCacheUriProvider.java" line="41">

---

# EhCache URI Provider Component

The `DefaultEhCacheUriProvider` class is annotated with `@Component("blJCacheUriProvider")` which allows it to be automatically detected and managed by the Spring framework. It also implements the `ApplicationContextAware` and `InitializingBean` interfaces.

```java
@Component("blJCacheUriProvider")
@ConditionalOnEhCache
public class DefaultEhCacheUriProvider extends DefaultJCacheUriProvider implements ApplicationContextAware, InitializingBean {
```

---

</SwmSnippet>

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/extensibility/cache/ehcache/DefaultEhCacheUriProvider.java" line="64">

---

# Merging Cache Configurations

The `mergeCacheLocations` method is responsible for merging the cache configurations from different locations. It first collects the resources from the `mergedCacheConfigLocations` and `configLocations`, then merges them using the `MergeXmlConfigResource` class. The merged resource is then written to a temporary file.

```java
    protected void mergeCacheLocations() throws IOException {
        List<Resource> resources = new ArrayList<>();
        if (mergedCacheConfigLocations != null && !mergedCacheConfigLocations.isEmpty()) {
            for (String location : mergedCacheConfigLocations) {
                resources.add(applicationContext.getResource(location));
            }
        }
        if (configLocations != null && !configLocations.isEmpty()) {
            resources.addAll(configLocations);
        }
        MergeXmlConfigResource merge = new MergeXmlConfigResource();
        ResourceInputStream[] sources = new ResourceInputStream[resources.size()];
        int j = 0;
        for (Resource resource : resources) {
            sources[j] = new ResourceInputStream(resource.getInputStream(), resource.getURL().toString());
            j++;
        }

        Resource mergeResource = merge.getMergedConfigResource(sources);
        createTemporaryMergeXml(mergeResource);
    }
```

---

</SwmSnippet>

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/extensibility/cache/ehcache/DefaultEhCacheUriProvider.java" line="59">

---

# Providing the EhCache URI

The `getJCacheUri` method returns the URI of the EhCache configuration file. This URI is used by the application to load the EhCache configurations.

```java
    @Override
    public URI getJCacheUri() {
        return cacheManagerUri;
    }
```

---

</SwmSnippet>

# Functions of EhCache URI Provider

This section discusses the main functions of the DefaultEhCacheUriProvider class.

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/extensibility/cache/ehcache/DefaultEhCacheUriProvider.java" line="64">

---

## mergeCacheLocations

The `mergeCacheLocations` function is responsible for merging cache locations. It collects resources from mergedCacheConfigLocations and configLocations, merges them using MergeXmlConfigResource, and then creates a temporary merge XML file using the createTemporaryMergeXml function.

```java
    protected void mergeCacheLocations() throws IOException {
        List<Resource> resources = new ArrayList<>();
        if (mergedCacheConfigLocations != null && !mergedCacheConfigLocations.isEmpty()) {
            for (String location : mergedCacheConfigLocations) {
                resources.add(applicationContext.getResource(location));
            }
        }
        if (configLocations != null && !configLocations.isEmpty()) {
            resources.addAll(configLocations);
        }
        MergeXmlConfigResource merge = new MergeXmlConfigResource();
        ResourceInputStream[] sources = new ResourceInputStream[resources.size()];
        int j = 0;
        for (Resource resource : resources) {
            sources[j] = new ResourceInputStream(resource.getInputStream(), resource.getURL().toString());
            j++;
        }

        Resource mergeResource = merge.getMergedConfigResource(sources);
        createTemporaryMergeXml(mergeResource);
    }
```

---

</SwmSnippet>

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/extensibility/cache/ehcache/DefaultEhCacheUriProvider.java" line="86">

---

## createTemporaryMergeXml

The `createTemporaryMergeXml` function is used to create a temporary XML file that contains the merged cache configuration. It first checks if the file exists, if not, it creates a new one. Then it copies the content of the merged cache resource into the file.

```java
    protected File createTemporaryMergeXml(Resource mergedJcacheResource) throws FileNotFoundException, IOException {
        File file = new File(getJCacheUri());
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
        }
        try (OutputStream outputStream = new FileOutputStream(file)) {
            IOUtils.copy(mergedJcacheResource.getInputStream(), outputStream);
        }
        return file;
    }
```

---

</SwmSnippet>

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/extensibility/cache/ehcache/DefaultEhCacheUriProvider.java" line="59">

---

## getJCacheUri

The `getJCacheUri` function returns the URI of the cache manager. This URI is used to locate the cache configuration file.

```java
    @Override
    public URI getJCacheUri() {
        return cacheManagerUri;
    }
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
