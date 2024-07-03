---
title: Implementations of findAllURLHandlers Method in the Service Layers
---
This document will cover the usage and functionality of the `findAllURLHandlers` method in the service layers of the BroadleafCommerce-demo repository. We'll cover:

1. What is the `findAllURLHandlers` method
2. How is `findAllURLHandlers` used in the codebase
3. The functionality and purpose of `findAllURLHandlers`

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/url/service/URLHandlerService.java" line="39">

---

# What is the `findAllURLHandlers` method

The `findAllURLHandlers` method is a part of the `URLHandlerService` interface. It is designed to return a list of all URL handlers. However, caution is advised when using this method due to potential performance and memory issues if there are a large number of records.

```java
    /**
     * Be cautious when calling this.  If there are a large number of records, this can cause performance and
     * memory issues.
     *
     * @return
     */
    public List<URLHandler> findAllURLHandlers();
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/url/service/URLHandlerServiceImpl.java" line="131">

---

# How is `findAllURLHandlers` used in the codebase

`findAllURLHandlers` is implemented in the `URLHandlerServiceImpl` class. It calls the `findAllURLHandlers` method from the `urlHandlerDao` object, which is responsible for fetching all URL handlers from the database.

```java
    @Override
    public List<URLHandler> findAllURLHandlers() {
        return urlHandlerDao.findAllURLHandlers();
    }
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/url/dao/URLHandlerDao.java" line="33">

---

# The functionality and purpose of `findAllURLHandlers`

The `findAllURLHandlers` method in the `URLHandlerDao` interface is responsible for fetching all URL handlers configured in the system. This method is called by the `findAllURLHandlers` method in the `URLHandlerServiceImpl` class.

```java
    /**
     * Gets all the URL handlers configured in the system
     *
     * @return
     */
    public List<URLHandler> findAllURLHandlers();
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="follow-up"><sup>Powered by [Swimm](/)</sup></SwmMeta>
