---
title: Exception Handling in Broadleaf Commerce
---
This document will cover the mechanisms in place to handle exceptions in the store within the BroadleafCommerce-demo repository. We'll cover:

1. How exceptions are handled in the `setSchema` method
2. The role of the `getMethod` function in exception handling
3. The `invoke` method's part in handling exceptions
4. Exception handling in the `getSchema` method
5. How the `abort` method deals with exceptions
6. Exception handling in the `setNetworkTimeout` method
7. The `getNetworkTimeout` method's approach to handling exceptions

<SwmSnippet path="/admin/broadleaf-open-admin-platform/src/main/java/org/broadleafcommerce/openadmin/server/service/persistence/datasource/SandBoxConnection.java" line="328">

---

# Handling exceptions in the `setSchema` method

In the `setSchema` method, exceptions are caught and ignored. This includes `SecurityException`, `NoSuchMethodException`, `IllegalArgumentException`, `IllegalAccessException`, and `InvocationTargetException`.

```java
    public void setSchema(String schema) throws SQLException {
        try {
            Class<? extends Connection> delegateClass = delegate.getClass();
            Class partypes[] = new Class[1];
            partypes[0] = String.class;
            Object args[] = new Object[1];
            args[0] = schema;
            Method method;
            method = delegateClass.getMethod("setSchema", partypes);
            method.invoke(delegate, args);
        } catch (SecurityException e) {
            // ignore exceptions
        } catch (NoSuchMethodException e) {
            // ignore exceptions
        } catch (IllegalArgumentException e) {
            // ignore exceptions
        } catch (IllegalAccessException e) {
            // ignore exceptions
        } catch (InvocationTargetException e) {
            // ignore exceptions
        }
```

---

</SwmSnippet>

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/web/controller/FrameworkMvcUriComponentsBuilder.java" line="496">

---

# The role of the `getMethod` function in exception handling

The `getMethod` function is used to retrieve a method from a controller type. If multiple methods with the same name and argument length are found, or if no such method is found, an `IllegalArgumentException` is thrown.

```java
    private static Method getMethod(Class<?> controllerType, final String methodName, final Object... args) {
        MethodFilter selector = new MethodFilter() {
            @Override
            public boolean matches(Method method) {
                String name = method.getName();
                int argLength = method.getParameterTypes().length;
                return (name.equals(methodName) && argLength == args.length);
            }
        };
        Set<Method> methods = MethodIntrospector.selectMethods(controllerType, selector);
        if (methods.size() == 1) {
            return methods.iterator().next();
        }
        else if (methods.size() > 1) {
            throw new IllegalArgumentException(String.format(
                    "Found two methods named '%s' accepting arguments %s in controller %s: [%s]",
                    methodName, Arrays.asList(args), controllerType.getName(), methods));
        }
        else {
            throw new IllegalArgumentException("No method named '" + methodName + "' with " + args.length +
                    " arguments found in controller " + controllerType.getName());
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-open-admin-platform/src/main/java/org/broadleafcommerce/openadmin/web/rulebuilder/service/AbstractRuleBuilderFieldService.java" line="153">

---

# The `invoke` method's part in handling exceptions

The `invoke` method is used to call a method on a proxy object. If the method name is `add` or `addAll`, the `testFieldName` method is called on the `FieldData` argument. Any exceptions thrown during this process are propagated up the call stack.

```java
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                if (method.getName().equals("add")) {
                    FieldData fieldData = (FieldData) args[0];
                    testFieldName(fieldData);
                }
                if (method.getName().equals("addAll")) {
                    Collection<FieldData> addCollection = (Collection<FieldData>) args[0];
                    Iterator<FieldData> itr = addCollection.iterator();
                    while (itr.hasNext()) {
                        FieldData fieldData = itr.next();
                        testFieldName(fieldData);
                    }
                }
                return method.invoke(fields, args);
            }
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-open-admin-platform/src/main/java/org/broadleafcommerce/openadmin/server/service/persistence/datasource/SandBoxConnection.java" line="351">

---

# Handling exceptions in the `getSchema` method

In the `getSchema` method, exceptions are caught and ignored, similar to the `setSchema` method. The exceptions handled include `SecurityException`, `NoSuchMethodException`, `IllegalArgumentException`, `IllegalAccessException`, and `InvocationTargetException`.

```java
    public String getSchema() throws SQLException {
        String returnValue = null;
        try {
            Class<? extends Connection> delegateClass = delegate.getClass();
            Method method = delegateClass.getMethod("getSchema");
            returnValue = method.invoke(delegate).toString();
        } catch (SecurityException e) {
            // ignore exceptions
        } catch (NoSuchMethodException e) {
            // ignore exceptions
        } catch (IllegalArgumentException e) {
            // ignore exceptions
        } catch (IllegalAccessException e) {
            // ignore exceptions
        } catch (InvocationTargetException e) {
            // ignore exceptions
        }
        return returnValue;
    }
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-open-admin-platform/src/main/java/org/broadleafcommerce/openadmin/server/service/persistence/datasource/SandBoxConnection.java" line="371">

---

# How the `abort` method deals with exceptions

The `abort` method also catches and ignores the same set of exceptions as the `setSchema` and `getSchema` methods.

```java
    public void abort(Executor executor) throws SQLException {
        try {
            Class<? extends Connection> delegateClass = delegate.getClass();
            Class partypes[] = new Class[1];
            partypes[0] = Executor.class;
            Object args[] = new Object[1];
            args[0] = executor;
            Method method = delegateClass.getMethod("abort", partypes);
            method.invoke(delegate, args);
        } catch (SecurityException e) {
            // ignore exceptions
        } catch (NoSuchMethodException e) {
            // ignore exceptions
        } catch (IllegalArgumentException e) {
            // ignore exceptions
        } catch (IllegalAccessException e) {
            // ignore exceptions
        } catch (InvocationTargetException e) {
            // ignore exceptions
        }
    }
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-open-admin-platform/src/main/java/org/broadleafcommerce/openadmin/server/service/persistence/datasource/SandBoxConnection.java" line="393">

---

# Handling exceptions in the `setNetworkTimeout` method

The `setNetworkTimeout` method follows the same pattern of catching and ignoring exceptions as the previous methods.

```java
    public void setNetworkTimeout(Executor executor, int milliseconds) throws SQLException {
        try {
            Class<? extends Connection> delegateClass = delegate.getClass();
            Class partypes[] = new Class[2];
            partypes[0] = Executor.class;
            partypes[1] = int.class;
            Object args[] = new Object[2];
            args[0] = executor;
            args[1] = milliseconds;
            Method method = delegateClass.getMethod("setNetworkTimeout", partypes);
            method.invoke(delegate, args);
        } catch (SecurityException e) {
            // ignore exceptions
        } catch (NoSuchMethodException e) {
            // ignore exceptions
        } catch (IllegalArgumentException e) {
            // ignore exceptions
        } catch (IllegalAccessException e) {
            // ignore exceptions
        } catch (InvocationTargetException e) {
            // ignore exceptions
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-open-admin-platform/src/main/java/org/broadleafcommerce/openadmin/server/service/persistence/datasource/SandBoxConnection.java" line="418">

---

# The `getNetworkTimeout` method's approach to handling exceptions

The `getNetworkTimeout` method also catches and ignores the same set of exceptions as the other methods in this class.

```java
    public int getNetworkTimeout() throws SQLException {
        int returnValue = 0;
        try {
            Class<? extends Connection> delegateClass = delegate.getClass();
            Method method = delegateClass.getMethod("getNetworkTimeout");
            returnValue = Integer.parseInt(method.invoke(delegate).toString());
        } catch (SecurityException e) {
            // ignore exceptions
        } catch (NoSuchMethodException e) {
            // ignore exceptions
        } catch (IllegalArgumentException e) {
            // ignore exceptions
        } catch (IllegalAccessException e) {
            // ignore exceptions
        } catch (InvocationTargetException e) {
            // ignore exceptions
        }
        return returnValue;
    }
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="follow-up"><sup>Powered by [Swimm](/)</sup></SwmMeta>
