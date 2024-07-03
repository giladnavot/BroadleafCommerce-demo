---
title: Introduction to Web Filters
---
Web Filters in BroadleafCommerce-demo are components that intercept HTTP requests and responses in your application. They are used to perform a variety of tasks such as authentication, logging, or adding headers. The filters are located in the package `org.broadleafcommerce.common.web.filter`. Some of the filters include `TranslationFilter`, `AbstractIgnorableFilter`, and `SecurityBasedIgnoreFilter`. For instance, `TranslationFilter` is responsible for setting up the translation context, which is used in both typical web applications and servlet filters.

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/web/filter/TranslationFilter.java" line="18">

---

# TranslationFilter

The `TranslationFilter` is an example of a Web Filter. It is used to set up the translation context for requests.

```java
package org.broadleafcommerce.common.web.filter;

import org.broadleafcommerce.common.admin.condition.ConditionalOnNotAdmin;
```

---

</SwmSnippet>

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/web/filter/AbstractIgnorableFilter.java" line="18">

---

# AbstractIgnorableFilter

`AbstractIgnorableFilter` is a base class for filters that can be ignored under certain conditions.

```java
package org.broadleafcommerce.common.web.filter;

import org.apache.commons.logging.Log;
```

---

</SwmSnippet>

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/web/filter/TranslationRequestProcessor.java" line="29">

---

# TranslationRequestProcessor

`TranslationRequestProcessor` is a component that sets up the translation context. It can be used by Web applications and called from a ServletFilter such as `TranslationFilter`.

```java
/**
 * This processor is responsible for setting up the translation context.   It is intended to be used
 * by both typical Web applications and called from a ServletFilter (such as "TranslationFilter") or 
 * from a portletFilter (such as "TranslationInterceptor")
 * 
 * @author bpolster
 */
@Component("blTranslationRequestProcessor")
public class TranslationRequestProcessor extends AbstractBroadleafWebRequestProcessor {
    
    @Resource(name = "blTranslationService")
    protected TranslationService translationService;
    
    protected boolean getTranslationEnabled() {
        return BLCSystemProperty.resolveBooleanSystemProperty("i18n.translation.enabled");
    }

    @Override
    public void process(WebRequest request) {
        TranslationConsiderationContext.setTranslationConsiderationContext(getTranslationEnabled());
        TranslationConsiderationContext.setTranslationService(translationService);
```

---

</SwmSnippet>

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/web/filter/EntityManagerFindValidationFilter.java" line="36">

---

# EntityManagerFindValidationFilter

`EntityManagerFindValidationFilter` is a filter used to validate usages of `em.find()` when querying for a primary key specifically across sibling Multi-Tenant sites. It is activated only under certain conditions.

```java
/**
 * <p>
 * Used to validate usages of em.find() when querying for a primary key specifically across sibling Multi-Tenant sites.
 * This Servlet filter should only turned on if you often query an entity by ID. Generally this only happens in
 * API-based use cases since most other use cases rely on querying by name, url, etc and not directly on a primary key.
 * 
 * <p>
 * This is intentionally not activated by default but is included here for convenience within other projects. If you are
 * in Spring Boot, this filter can be activated simply in an @Bean method. If you are not using Spring Boot, this
 * filter must come <i>after</i> the {@link BroadleafRequestFilter} and is generally initialized in {@code applicationContext-filter.xml}.
 * 
 * @author Phillip Verheyden (phillipuniverse)
 * @since 5.2
 * @see BroadleafRequestContext#setInternalIgnoreFilters(Boolean)
 */
@Order(FilterOrdered.POST_SECURITY_MEDIUM)
public class EntityManagerFindValidationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
