---
title: Basic Concepts of Web Controllers
---
Web Controllers in BroadleafCommerce-demo are components that handle HTTP requests and responses. They are part of the MVC (Model-View-Controller) architecture, where they act as an intermediary between the Model, which deals with data, and the View, which deals with the user interface. In this repository, the package `org.broadleafcommerce.common.web.controller` contains classes like `FrameworkControllerHandlerMapping` and `FrameworkMvcUriComponentsBuilder` that are used to map requests to appropriate handlers and build URI components for MVC respectively. Annotations like `@FrameworkController` and `@EnableFrameworkControllers` are used to mark classes as web controllers and enable them in the Spring context.

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/web/controller/FrameworkMvcUriComponentsBuilder.java" line="115">

---

# FrameworkMvcUriComponentsBuilder Class

The `FrameworkMvcUriComponentsBuilder` class is a part of the Web Controllers in BroadleafCommerce-demo. It is used to create instances of `UriComponentsBuilder` by pointing to Spring MVC controllers and `@RequestMapping` methods. This class provides URI building functionality for `FrameworkMapping` annotations.

```java
public class FrameworkMvcUriComponentsBuilder {

    /**
     * Well-known name for the {@link CompositeUriComponentsContributor} object in the bean factory.
     */
    public static final String MVC_URI_COMPONENTS_CONTRIBUTOR_BEAN_NAME = "mvcUriComponentsContributor";


    private static final Log logger = LogFactory.getLog(FrameworkMvcUriComponentsBuilder.class);

    private static final SpringObjenesis objenesis = new SpringObjenesis();

    private static final PathMatcher pathMatcher = new AntPathMatcher();

    private static final ParameterNameDiscoverer parameterNameDiscoverer = new DefaultParameterNameDiscoverer();

    private static final CompositeUriComponentsContributor defaultUriComponentsContributor;

    static {
        defaultUriComponentsContributor = new CompositeUriComponentsContributor(
                new PathVariableMethodArgumentResolver(), new RequestParamMethodArgumentResolver(false));
```

---

</SwmSnippet>

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/web/controller/FrameworkMvcUriComponentsBuilder.java" line="682">

---

# withController Method

The `withController` method is an example of how Web Controllers are used in BroadleafCommerce-demo. This method creates a `UriComponentsBuilder` from the mapping of a controller class and current request information including Servlet mapping. If the controller contains multiple mappings, only the first one is used.

```java
    /**
     * An alternative to {@link #fromController(Class)} for use with an instance
     * of this class created via a call to {@link #relativeTo}.
     * @since 4.2
     */
    public UriComponentsBuilder withController(Class<?> controllerType) {
        return fromController(this.baseUrl, controllerType);
    }
```

---

</SwmSnippet>

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/web/controller/FrameworkMvcUriComponentsBuilder.java" line="165">

---

# fromController Method

The `fromController` method is another example of how Web Controllers are used in BroadleafCommerce-demo. This method creates a `UriComponentsBuilder` from the mapping of a controller class and current request information including Servlet mapping. If the controller contains multiple mappings, only the first one is used.

```java
    /**
     * Create a {@link UriComponentsBuilder} from the mapping of a controller class
     * and current request information including Servlet mapping. If the controller
     * contains multiple mappings, only the first one is used.
     * @param controllerType the controller to build a URI for
     * @return a UriComponentsBuilder instance (never {@code null})
     */
    public static UriComponentsBuilder fromController(Class<?> controllerType) {
        return fromController(null, controllerType);
    }
```

---

</SwmSnippet>

# Web Controllers Functions

In this section, we will discuss the main functions of Web Controllers in the BroadleafCommerce-demo repository. These functions play a crucial role in handling web requests and responses, mapping URLs to specific controller methods, and enabling specific controller annotations.

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/web/controller/FrameworkControllerHandlerMapping.java" line="37">

---

## FrameworkControllerHandlerMapping

The `FrameworkControllerHandlerMapping` class is responsible for mapping `FrameworkMapping` annotations inside `FrameworkController` and `FrameworkRestController` classes. When framework controllers are enabled, this class adds `FrameworkMapping` annotations found within the class to handler mappings. This class has a lower priority than the default `RequestMappingHandlerMapping`, so when a request comes in, `RequestMapping` annotations located inside a class annotated with `Controller` or `RestController` will have a higher priority and be found before `FrameworkMapping` annotations found within a `FrameworkController` or `FrameworkRestController`.

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

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/web/controller/FrameworkMvcUriComponentsBuilder.java" line="297">

---

## fromMappingName

The `fromMappingName` method creates a URL from the name of a Spring MVC controller method's request mapping. The configured `HandlerMethodMappingNamingStrategy` determines the names of controller method request mappings at startup. This method is primarily used in view rendering technologies and EL expressions.

```java
    /**
     * Create a URL from the name of a Spring MVC controller method's request mapping.
     * <p>The configured
     * {@link org.springframework.web.servlet.handler.HandlerMethodMappingNamingStrategy
     * HandlerMethodMappingNamingStrategy} determines the names of controller
     * method request mappings at startup. By default all mappings are assigned
     * a name based on the capital letters of the class name, followed by "#" as
     * separator, and then the method name. For example "PC#getPerson"
     * for a class named PersonController with method getPerson. In case the
     * naming convention does not produce unique results, an explicit name may
     * be assigned through the name attribute of the {@code @RequestMapping}
     * annotation.
     * <p>This is aimed primarily for use in view rendering technologies and EL
     * expressions. The Spring URL tag library registers this method as a function
     * called "mvcUrl".
     * <p>For example, given this controller:
     * <pre class="code">
     * &#064;RequestMapping("/people")
     * class PersonController {
     *
     *   &#064;RequestMapping("/{id}")
```

---

</SwmSnippet>

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/web/controller/FrameworkMvcUriComponentsBuilder.java" line="614">

---

## controller

The `controller` method returns a 'mock' controller instance. When a `RequestMapping` method on the controller is invoked, the supplied argument values are remembered and the result can then be used to create `UriComponentsBuilder` via `fromMethodCall(Object)`.

```java
    /**
     * Return a "mock" controller instance. When an {@code @RequestMapping} method
     * on the controller is invoked, the supplied argument values are remembered
     * and the result can then be used to create {@code UriComponentsBuilder} via
     * {@link #fromMethodCall(Object)}.
     * <p>This is a longer version of {@link #on(Class)}. It is needed with controller
     * methods returning void as well for repeated invocations.
     * <pre class="code">
     * FooController fooController = controller(FooController.class);
     *
     * fooController.saveFoo(1, null);
     * builder = MvcUriComponentsBuilder.fromMethodCall(fooController);
     *
     * fooController.saveFoo(2, null);
     * builder = MvcUriComponentsBuilder.fromMethodCall(fooController);
     * </pre>
     * @param controllerType the target controller
     */
    public static <T> T controller(Class<T> controllerType) {
        Assert.notNull(controllerType, "'controllerType' must not be null");
        return initProxy(controllerType, new ControllerMethodInvocationInterceptor(controllerType));
```

---

</SwmSnippet>

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/web/controller/FrameworkMvcUriComponentsBuilder.java" line="718">

---

## withMethod

The `withMethod` method is an alternative to `fromMethod(Class, Method, Object...)` for use with an instance of this class created via `relativeTo`. It creates a `UriComponentsBuilder` from the mapping of a controller method and an array of method argument values.

```java
    /**
     * An alternative to {@link #fromMethod(Class, Method, Object...)}
     * for use with an instance of this class created via {@link #relativeTo}.
     * @since 4.2
     */
    public UriComponentsBuilder withMethod(Class<?> controllerType, Method method, Object... args) {
        return fromMethod(this.baseUrl, controllerType, method, args);
```

---

</SwmSnippet>

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/web/controller/FrameworkMvcUriComponentsBuilder.java" line="731">

---

## getControllerMethod

The `getControllerMethod` constant is used to find the method `getControllerMethod` in the `MethodInvocationInfo` class. This method is used to get the controller method.

```java
        private static final Method getControllerMethod =
                ReflectionUtils.findMethod(MethodInvocationInfo.class, "getControllerMethod");
```

---

</SwmSnippet>

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/web/controller/FrameworkMvcUriComponentsBuilder.java" line="737">

---

## getControllerType

The `getControllerType` constant is used to find the method `getControllerType` in the `MethodInvocationInfo` class. This method is used to get the controller type.

```java
        private static final Method getControllerType =
                ReflectionUtils.findMethod(MethodInvocationInfo.class, "getControllerType");
```

---

</SwmSnippet>

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/web/controller/annotation/EnableFrameworkControllers.java" line="32">

---

## EnableFrameworkControllers

The `EnableFrameworkControllers` annotation enables only `FrameworkController` annotations, which are the MVC controllers. It scans all Broadleaf modules for `FrameworkController` so that their `FrameworkMapping` annotations will get included in `FrameworkControllerHandlerMapping` to provide default implementations of web endpoints.

```java
/**
 * Enables only {@link FrameworkController} annotations, which are the MVC controllers.
 * <p>
 * If you desire all of Broadleaf's default controllers, including the RESTful ones, then use
 * {@link EnableAllFrameworkControllers} instead.
 * <p>
 * Scan all Broadleaf modules for {@link FrameworkController} so that their {@link FrameworkMapping}s will get included
 * in {@link FrameworkControllerHandlerMapping} to provide default implementations of web endpoints.
 * <p>
 * If only some {@link FrameworkController}s are desired, then use {@link #excludeFilters()} to disable undesired
 * default controllers.
 * <p>
 * <b>DO NOT place this annotation on the same class as another {@link ComponentScan} or other annotations that compose
 * {@link ComponentScan} such as {@code @SpringBootApplication} and {@link EnableFrameworkRestControllers} as they will
 * conflict when Spring performs annotation composition.</b> Instead, you can create a nested class in your
 * {@code @SprintBootApplication} class like this:
 * <pre>
 * {@code
 * @literal @SpringBootApplication
 * public class MyApplication {
 *
```

---

</SwmSnippet>

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/web/controller/annotation/FrameworkController.java" line="28">

---

## FrameworkController

The `FrameworkController` annotation indicates that an annotated class is a 'Framework Controller' (default MVC controller). If `EnableFrameworkControllers` or `EnableAllFrameworkControllers` is included in the application configuration then classes annotated with `FrameworkController` will be component scanned and included in the application context.

```java
/**
 * Indicates that an annotated class is a "Framework Controller" (default MVC controller).
 * <p>
 * This means that if {@link EnableFrameworkControllers} or {@link EnableAllFrameworkControllers} is included in the
 * application configuration then classes annotated with {@link FrameworkController} will be component scanned and
 * included in the application context and that {@link FrameworkMapping}s will be added to handler mappings with a lower
 * priority than {@link org.springframework.web.bind.annotation.RequestMapping}s found within a class annotated with
 * {@link org.springframework.stereotype.Controller}. This priority is achieved through {@link
 * FrameworkControllerHandlerMapping} having a higher order value than {@link RequestMappingHandlerMapping}.
 * <p>
 * The intention is that you are able to specify MVC controllers and mappings within a framework module as the default
 * MVC mappings and a client application can essentially override those mappings without causing an ambiguous mapping
 * exception.
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

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/web/controller/annotation/EnableAllFrameworkControllers.java" line="31">

---

## EnableAllFrameworkControllers

The `EnableAllFrameworkControllers` annotation enables `FrameworkController` and `FrameworkRestController` annotations. It scans all Broadleaf modules for `FrameworkController` and `FrameworkRestController` so that their `FrameworkMapping` annotations will get included in `FrameworkControllerHandlerMapping` to provide default implementations of web endpoints.

```java
/**
 * Enables {@link FrameworkController} and {@link FrameworkRestController} annotations.
 * <p>
 * Scan all Broadleaf modules for {@link FrameworkController} and {@link FrameworkRestController} so that their {@link
 * FrameworkMapping}s will get included in {@link
 * FrameworkControllerHandlerMapping} to provide default implementations of web endpoints.
 * <p>
 * If only some controllers are desired, then you must individually use {@link EnableFrameworkControllers} and
 * {@link EnableFrameworkRestControllers} and utilize their excludeFilters property to disable the unwanted controllers.
 * See {@link EnableFrameworkControllers} documentation for how to properly use these two annotations together.
 * <p>
 * <b>DO NOT place this annotation on the same class as another {@link ComponentScan} or other annotations that compose
 * {@link ComponentScan} such as {@code @SpringBootApplication}, as they will conflict when Spring performs annotation
 * composition.</b> Instead, you can create a nested class in your {@code @SprintBootApplication} class like this:
 * <pre>
 * {@code
 * @literal @SpringBootApplication
 * public class MyApplication {
 *
 *     @literal @EnableAllFrameworkControllers
 *     public static class EnableAllBroadleafControllers {}
```

---

</SwmSnippet>

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/web/controller/annotation/FrameworkRestController.java" line="28">

---

## FrameworkRestController

The `FrameworkRestController` annotation indicates that an annotated class is a 'Framework Rest Controller'. If `EnableFrameworkRestControllers` or `EnableAllFrameworkControllers` is included in the application configuration then classes annotated with `FrameworkRestController` will be component scanned and included in the application context.

```java

/**
 * Indicates that an annotated class is a "Framework REST Controller" (default RESTful controller).
 * <p>
 * This means that if {@link EnableFrameworkRestControllers} or {@link EnableAllFrameworkControllers} is included in the
 * application configuration then classes annotated with {@link FrameworkRestController} will be component scanned and
 * included in the application context and that {@link FrameworkMapping}s will be added to handler mappings with a lower
 * priority than {@link org.springframework.web.bind.annotation.RequestMapping}s found within a class annotated with
 * {@link org.springframework.web.bind.annotation.RestController}. This priority is achieved through {@link
 * FrameworkControllerHandlerMapping} having a higher order value than {@link RequestMappingHandlerMapping}.
 * <p>
 * The intention is that you are able to specify RESTful controllers and mappings within a framework module as the
 * default REST endpoints and a client application can essentially override those mappings without causing an ambiguous
 * mapping exception.
 * <p>
 * The site handler mappings in play in order of precedence from highest to lowest are:
 * <ol>
 * <li>{@link RequestMappingHandlerMapping}</li>
 * <li>{@link FrameworkControllerHandlerMapping}</li>
 * </ol>
 * <p>
```

---

</SwmSnippet>

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/web/controller/annotation/EnableFrameworkRestControllers.java" line="32">

---

## EnableFrameworkRestControllers

The `EnableFrameworkRestControllers` annotation enables only `FrameworkRestController` annotations, which are the RESTful controllers. It scans all Broadleaf modules for `FrameworkRestController` so that their `FrameworkMapping` annotations will get included in `FrameworkControllerHandlerMapping` to provide default implementations of web endpoints.

```java
/**
 * Enables only {@link FrameworkRestController} annotations, which are the RESTful controllers.
 * <p>
 * If you desire all of Broadleaf's default controllers, including the MVC ones, then use {@link
 * EnableAllFrameworkControllers} instead.
 * <p>
 * Scan all Broadleaf modules for {@link FrameworkRestController} so that their {@link FrameworkMapping}s will get
 * included in {@link FrameworkControllerHandlerMapping} to provide default implementations of web endpoints.
 * <p>
 * If only some {@link FrameworkRestController}s are desired, then use {@link #excludeFilters()} to disable undesired
 * default controllers.
 * <p>
 * <b>DO NOT place this annotation on the same class as another {@link ComponentScan} or other annotations that compose
 * {@link ComponentScan} such as {@code @SpringBootApplication} and {@link EnableFrameworkControllers} as they will
 * conflict when Spring performs annotation composition.</b> Instead, you can create a nested class in your
 * {@code @SprintBootApplication} class like this:
 * <pre>
 * {@code
 * @literal @SpringBootApplication
 * public class MyApplication {
 *
```

---

</SwmSnippet>

# Broadleaf Commerce Controllers

Understanding Broadleaf Commerce Controllers

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/web/controller/FrameworkControllerHandlerMapping.java" line="37">

---

## FrameworkControllerHandlerMapping

The `FrameworkControllerHandlerMapping` class is responsible for handling mappings found within classes annotated with `FrameworkController` or `FrameworkRestController`. It has a lower priority than the default `RequestMappingHandlerMapping`, meaning that `RequestMapping`s located inside a class annotated with `Controller` or `RestController` will have a higher priority and be found before `FrameworkMapping`s found within a `FrameworkController` or `FrameworkRestController`.

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

The `FrameworkMvcUriComponentsBuilder` class is used to provide URI building functionality for `FrameworkMapping` annotations. It creates instances of `UriComponentsBuilder` by pointing to Spring MVC controllers and `@RequestMapping` methods. It also provides methods to create a `UriComponentsBuilder` from the mapping of a controller class and current request information, from the mapping of a controller method, and by invoking a 'mock' controller method.

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
