---
title: Understanding Extension in Broadleaf Commerce
---
In the BroadleafCommerce-demo repository, 'Extension' refers to a set of interfaces and classes that allow for the modification and customization of queries, primarily for DAO usage. These extensions are used to contribute to a query, often from another module, and are particularly useful in multitenant scenarios. They provide a way to add additional restrictions, sorting, and filtering to the fetch query, and to perform setup and breakdown operations before and after executing the query.

The 'SparselyPopulatedQueryExtensionHandler' interface is an example of an extension. It is used to handle querying for sparsely populated caches in multitenant scenarios. It allows for the addition of restrictions to the fetch query, filtering of results from the database, and building of cache keys, among other things.

Another example is the 'QueryExtensionHandler' interface, which is similar to 'SparselyPopulatedQueryExtensionHandler' but does not include methods for handling sparsely populated caches.

The 'TemplateOnlyQueryExtensionHandler' interface is another type of extension that manipulates a query to not include standard site catalogs, making it useful in some caching situations where it is advantageous to only look at template catalog values.

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/extension/ExtensionManagerOperation.java" line="27">

---

# ExtensionManagerOperation Interface

The `ExtensionManagerOperation` interface defines a single method `execute()`, which is responsible for executing a method on an `ExtensionHandler`. This method takes an `ExtensionHandler` and an array of parameters as arguments, and returns an `ExtensionResultStatusType` indicating the result of the operation.

```java
public interface ExtensionManagerOperation {

    /**
     * Call a method on the handler using some params. This generally involves casting to the proper types. For example:
     * </p>
     * <pre>
     * {@code
     *  public static final ExtensionManagerOperation applyAdditionalFilters = new ExtensionManagerOperation() {
     *        @Override
     *        public ExtensionResultStatusType execute(ExtensionHandler handler, Object... params) {
     *            return ((OfferServiceExtensionHandler) handler).applyAdditionalFilters((List<Offer>) params[0], (Order) params[1]);
     *        }
     *  };
     * }
     * </pre>
     * @param handler
     * @param params
     * @return the result
     */
    ExtensionResultStatusType execute(ExtensionHandler handler, Object... params);

```

---

</SwmSnippet>

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/extension/ExtensionHandler.java" line="57">

---

# ExtensionHandler Interface

The `ExtensionHandler` interface defines methods for getting the priority of the handler and checking if it is enabled. The priority determines the execution order of handlers, with lower values indicating higher priority. The `isEnabled()` method is used to check if the handler is enabled or not.

```java
public interface ExtensionHandler {

    /**
     * Determines the priority of this extension handler.
     * @return
     */
    public int getPriority();

    /**
     * If false, the ExtensionManager should skip this Handler.
     * @return
     */
    public boolean isEnabled();
}
```

---

</SwmSnippet>

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/extension/ExtensionResultHolder.java" line="33">

---

# ExtensionResultHolder Class

The `ExtensionResultHolder` class is used to hold the result of an extension operation. It provides methods for setting and getting the result, getting a context map, and setting and getting a throwable. The context map can be used to pass additional information between the extension handler and the caller.

```java
public class ExtensionResultHolder<T> {

    protected T result;
    protected Throwable throwable;
    protected Map<String, Object> contextMap = new HashMap<String, Object>();

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public void setThrowable(Throwable throwable) {
        this.throwable = throwable;
    }
```

---

</SwmSnippet>

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/extension/ExtensionManager.java" line="44">

---

# ExtensionManager Class

The `ExtensionManager` class is responsible for managing and executing the extension handlers. It provides methods for registering handlers, getting the handlers, and executing an operation on the handlers. The `execute()` method takes an `ExtensionManagerOperation` and an array of parameters, and executes the operation on each handler until one of them handles the operation.

```java
public abstract class ExtensionManager<T extends ExtensionHandler> implements InvocationHandler {

    protected boolean handlersSorted = false;
    protected static String LOCK_OBJECT = new String("EM_LOCK");
    
    protected T extensionHandler;
    protected List<T> handlers = new ArrayList<T>();

    /**
     * Should take in a className that matches the ExtensionHandler interface being managed.
     * @param className
     */
    @SuppressWarnings("unchecked")
    public ExtensionManager(Class<T> _clazz) {
        extensionHandler = (T) Proxy.newProxyInstance(_clazz.getClassLoader(),
                new Class[] { _clazz },
                this);
    }
    
    public T getProxy() {
        return extensionHandler;
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
