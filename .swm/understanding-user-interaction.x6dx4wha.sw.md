---
title: Understanding User Interaction
---
User Interaction in BroadleafCommerce-demo refers to the ways in which users interact with the e-commerce platform. This includes navigating through the site, adding items to the shopping cart, and making purchases. The platform is designed to provide a seamless and intuitive user experience, with features such as filters and search functionality to help users find products, and a secure and straightforward checkout process. User interaction also extends to any feedback or communication users may have with the platform, such as product reviews or customer service inquiries.

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/web/filter/TranslationFilter.java" line="9">

---

# Filters

The `TranslationFilter` is an example of a filter used in BroadleafCommerce-demo. It is likely used to handle language translation for user requests.

```java
 * unless the restrictions on use therein are violated and require payment to Broadleaf in which case
 * the Broadleaf End User License Agreement (EULA), Version 1.1
 * (the "Commercial License" located at http://license.broadleafcommerce.org/commercial_license-1.1.txt)
 * shall apply.
 * 
 * Alternatively, the Commercial License may be replaced with a mutually agreed upon license (the "Custom License")
 * between you and Broadleaf Commerce. You may not use this file except in compliance with the applicable license.
```

---

</SwmSnippet>

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/web/controller/BroadleafAbstractController.java" line="40">

---

# Controllers

The `BroadleafAbstractController` is an example of a controller in BroadleafCommerce-demo. It is used to handle common operations across all controllers.

```java
 * Operations that are shared between all controllers belong here.   To use composition rather than
```

---

</SwmSnippet>

# Broadleaf Commerce Endpoints

Understanding Broadleaf Commerce Endpoints

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/web/controller/FrameworkControllerHandlerMapping.java" line="37">

---

## FrameworkControllerHandlerMapping

The `FrameworkControllerHandlerMapping` class is responsible for mapping `FrameworkMapping`s inside `FrameworkController` and `FrameworkRestController` classes. It prioritizes `RequestMapping`s located inside a class annotated with `Controller` or `RestController` over `FrameworkMapping`s found within a `FrameworkController` or `FrameworkRestController`.

```java
/**
 * HandlerMapping to find and map {@link FrameworkMapping}s inside {@link FrameworkController} and {@link
 * FrameworkRestController} classes.
 * <p>
 * When framework controllers are enabled with {@link EnableAllFrameworkControllers}, {@link
 * EnableFrameworkControllers}, or {@link EnableFrameworkRestControllers} and a class is annotated with {@link
 * FrameworkController} or {@link FrameworkRestController} then this class will add {@link FrameworkMapping}s found
 * within the class to handler mappings. This class has a lower priority than the default {@link
 * org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping} so when a request comes in,
 * {@link org.springframework.web.bind.annotation.RequestMapping}s located inside a class annotated with {@link
 * org.springframework.stereotype.Controller} or {@link org.springframework.web.bind.annotation.RestController} will
 * have a higher priority and be found before {@link FrameworkMapping}s found within a {@link FrameworkController} or
 * {@link FrameworkRestController}.
 * <p>
 * The site handler mappings in play in order of precedence from highest to lowest are:
 * <ol>
 * <li>{@link RequestMappingHandlerMapping}</li>
 * <li>{@link FrameworkControllerHandlerMapping}</li>
 * </ol>
 * <p>
 * The admin handler mappings in play in order of precedence from highest to lowest are:
```

---

</SwmSnippet>

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/web/controller/FrameworkMvcUriComponentsBuilder.java" line="88">

---

## FrameworkMvcUriComponentsBuilder

The `FrameworkMvcUriComponentsBuilder` class provides URI building functionality for `FrameworkMapping` annotations. It creates instances of `UriComponentsBuilder` by pointing to Spring MVC controllers and `@RequestMapping` methods.

```java
/**
 * This class has been copied from spring-webmvc:4.3.7-RELEASE in order to provide URI building functionality for
 * {@link FrameworkMapping} annotations. Since this class isn't extensible due to heavy usage of {@code private}
 * we had to copy and modify the whole class. Spring version updates should seek to take in changes from {@link MvcUriComponentsBuilder}
 * into this class.
 *
 * Creates instances of {@link org.springframework.web.util.UriComponentsBuilder}
 * by pointing to Spring MVC controllers and {@code @RequestMapping} methods.
 *
 * <p>The static {@code fromXxx(...)} methods prepare links relative to the
 * current request as determined by a call to
 * {@link org.springframework.web.servlet.support.ServletUriComponentsBuilder#fromCurrentServletMapping()}.
 *
 * <p>The static {@code fromXxx(UriComponentsBuilder,...)} methods can be given
 * the baseUrl when operating outside the context of a request.
 *
 * <p>You can also create an MvcUriComponentsBuilder instance with a baseUrl
 * via {@link #relativeTo(org.springframework.web.util.UriComponentsBuilder)}
 * and then use the non-static {@code withXxx(...)} method variants.
 *
 * @author Oliver Gierke
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
