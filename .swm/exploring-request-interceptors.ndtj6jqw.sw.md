---
title: Exploring Request Interceptors
---
Request Interceptors in BroadleafCommerce-demo are classes that implement the WebRequestInterceptor interface. They are used to perform actions before, after, or upon completion of web requests. For instance, the `TranslationInterceptor` class uses the `TranslationRequestProcessor` to process requests before they are handled and after they are completed. Similarly, the `BroadleafRequestInterceptor` class uses the `BroadleafRequestProcessor` to process requests before they are handled and after they are completed. These interceptors are crucial in setting up the request context and performing necessary operations like translation and theme resolution.

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/web/filter/TranslationInterceptor.java" line="31">

---

# TranslationInterceptor

The TranslationInterceptor is an example of a Request Interceptor. It uses the TranslationRequestProcessor to process the request and post-process it. The preHandle method is called before the request is handled, and the postHandle method is called after the request is handled but before the view is rendered.

```java
public class TranslationInterceptor implements WebRequestInterceptor {
    
    @Resource(name = "blTranslationRequestProcessor")
    protected TranslationRequestProcessor translationRequestProcessor;

    @Override
    public void preHandle(WebRequest request) throws Exception {
        translationRequestProcessor.process(request);
    }

    @Override
    public void postHandle(WebRequest request, ModelMap model) throws Exception {
        translationRequestProcessor.postProcess(request);
    }

    @Override
    public void afterCompletion(WebRequest request, Exception ex) throws Exception {
        // unimplemented
    }
}
```

---

</SwmSnippet>

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/web/BroadleafRequestInterceptor.java" line="37">

---

# BroadleafRequestInterceptor

The BroadleafRequestInterceptor is another example of a Request Interceptor. It uses the BroadleafRequestProcessor to process the request and post-process it. Similar to the TranslationInterceptor, the preHandle method is called before the request is handled, and the postHandle method is called after the request is handled but before the view is rendered.

```java
public class BroadleafRequestInterceptor implements WebRequestInterceptor {

    @Resource(name = "blRequestProcessor")
    protected BroadleafRequestProcessor requestProcessor;

    @Override
    public void preHandle(WebRequest request) throws Exception {
        requestProcessor.process(request);
    }

    @Override
    public void postHandle(WebRequest request, ModelMap model) throws Exception {
        //unimplemented
    }

    @Override
    public void afterCompletion(WebRequest request, Exception ex) throws Exception {
        requestProcessor.postProcess(request);
    }

}
```

---

</SwmSnippet>

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/web/controller/FrameworkMvcUriComponentsBuilder.java" line="728">

---

# ControllerMethodInvocationInterceptor

The ControllerMethodInvocationInterceptor is a more complex example of a Request Interceptor. It intercepts method invocations on controller objects and provides a way to manipulate the method invocation and its arguments.

```java
    private static class ControllerMethodInvocationInterceptor
            implements org.springframework.cglib.proxy.MethodInterceptor, MethodInterceptor {

        private static final Method getControllerMethod =
                ReflectionUtils.findMethod(MethodInvocationInfo.class, "getControllerMethod");

        private static final Method getArgumentValues =
                ReflectionUtils.findMethod(MethodInvocationInfo.class, "getArgumentValues");

        private static final Method getControllerType =
                ReflectionUtils.findMethod(MethodInvocationInfo.class, "getControllerType");

        private Method controllerMethod;

        private Object[] argumentValues;

        private Class<?> controllerType;

        ControllerMethodInvocationInterceptor(Class<?> controllerType) {
            this.controllerType = controllerType;
        }
```

---

</SwmSnippet>

# Request Interceptors

This section will cover the functions of Request Interceptors in the BroadleafCommerce-demo repository. Specifically, we will focus on the TranslationInterceptor and BroadleafRequestInterceptor classes.

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/web/filter/TranslationInterceptor.java" line="26">

---

## TranslationInterceptor

The TranslationInterceptor class is an implementation of the WebRequestInterceptor interface. It is used to intercept requests and perform translation-related operations. It has three main methods: preHandle, postHandle, and afterCompletion. The preHandle method is called before the actual handler is executed. It uses the TranslationRequestProcessor to process the request. The postHandle method is called after the handler is executed. It uses the TranslationRequestProcessor to post-process the request. The afterCompletion method is called after the complete request has finished. It is not implemented in this class.

```java
/**
 * Interceptor for use with portlets that calls the {@link TranslationRequestProcessor}.
 * 
 * @author bpolster
 */
public class TranslationInterceptor implements WebRequestInterceptor {
    
    @Resource(name = "blTranslationRequestProcessor")
    protected TranslationRequestProcessor translationRequestProcessor;

    @Override
    public void preHandle(WebRequest request) throws Exception {
        translationRequestProcessor.process(request);
    }

    @Override
    public void postHandle(WebRequest request, ModelMap model) throws Exception {
        translationRequestProcessor.postProcess(request);
    }

    @Override
```

---

</SwmSnippet>

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/web/BroadleafRequestInterceptor.java" line="27">

---

## BroadleafRequestInterceptor

The BroadleafRequestInterceptor class is another implementation of the WebRequestInterceptor interface. It is responsible for setting up the BroadleafRequestContext for the life of the request. Similar to the TranslationInterceptor, it has preHandle, postHandle, and afterCompletion methods. The preHandle method uses the BroadleafRequestProcessor to process the request. The postHandle method is not implemented. The afterCompletion method uses the BroadleafRequestProcessor to post-process the request.

```java
/**
 * <p>Interceptor responsible for setting up the BroadleafRequestContext for the life of the request. This interceptor
 * should be the very first one in the list, as other interceptors might also use {@link BroadleafRequestContext}.</p>
 * 
 * <p>Note that in Servlet applications you should be using the {@link BroadleafRequestFilter}.</p>
 * 
 * @author Phillip Verheyden
 * @see {@link BroadleafRequestProcessor}
 * @see {@link BroadleafRequestContext}
 */
public class BroadleafRequestInterceptor implements WebRequestInterceptor {

    @Resource(name = "blRequestProcessor")
    protected BroadleafRequestProcessor requestProcessor;

    @Override
    public void preHandle(WebRequest request) throws Exception {
        requestProcessor.process(request);
    }

    @Override
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
