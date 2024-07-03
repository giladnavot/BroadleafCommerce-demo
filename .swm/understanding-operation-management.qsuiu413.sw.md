---
title: Understanding Operation Management
---
Operation Management in BroadleafCommerce-demo refers to the management of named operations, which are specific tasks or functions that are named for easy reference. This is implemented through the `NamedOperationManager` interface and its implementation `NamedOperationManagerImpl`. The `NamedOperationManager` interface provides methods to manage named parameters and get all registered components that perform manipulations. The `NamedOperationManagerImpl` class implements these methods and maintains a list of `NamedOperationComponent` objects, each representing a named operation.

The `manageNamedParameters` method in `NamedOperationManagerImpl` takes a map of parameters, iterates over the named operation components, and calls the `setOperationValues` method on each component. This method modifies the original parameters and returns a list of names that were utilized. The utilized names are then removed from the original parameters. The method finally returns a map of derived parameters.

The `getNamedOperationComponents` method in `NamedOperationManagerImpl` simply returns the list of named operation components. This list can be modified using the `setNamedOperationComponents` method.

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/file/service/operation/NamedOperationManager.java" line="26">

---

# NamedOperationManager Interface

This is the `NamedOperationManager` interface. It declares two methods: `manageNamedParameters` and `getNamedOperationComponents`. The `manageNamedParameters` method is used to manage named parameters, while `getNamedOperationComponents` returns all registered operation components.

```java
public interface NamedOperationManager {

    Map<String, String> manageNamedParameters(Map<String, String> parameterMap);

    /**
     * Returns all of the components that have been registered to perform manipulations
     */
    List<NamedOperationComponent> getNamedOperationComponents();
}
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/file/service/operation/NamedOperationManagerImpl.java" line="28">

---

# NamedOperationManagerImpl Class

This is the `NamedOperationManagerImpl` class, which implements the `NamedOperationManager` interface. It provides the actual implementation for the methods declared in the interface. It also maintains a list of `NamedOperationComponent` objects, which can be set using the `setNamedOperationComponents` method and retrieved using the `getNamedOperationComponents` method.

```java
public class NamedOperationManagerImpl implements NamedOperationManager {

    protected List<NamedOperationComponent> namedOperationComponents = new ArrayList<NamedOperationComponent>();

    @Override
    public Map<String, String> manageNamedParameters(Map<String, String> parameterMap) {
        List<String> utilizedNames = new ArrayList<String>();
        Map<String, String> derivedMap = new LinkedHashMap<String, String>();
        for (NamedOperationComponent namedOperationComponent : namedOperationComponents) {
            utilizedNames.addAll(namedOperationComponent.setOperationValues(parameterMap, derivedMap));
        }
        for (String utilizedName : utilizedNames) {
            parameterMap.remove(utilizedName);
        }
        derivedMap.putAll(parameterMap);

        return derivedMap;
    }

    @Override
    public List<NamedOperationComponent> getNamedOperationComponents() {
```

---

</SwmSnippet>

# Operation Management Functions

This section will explain the functions of Operation Management in the BroadleafCommerce-demo repository.

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/file/service/operation/NamedOperationManagerImpl.java" line="33">

---

## manageNamedParameters

The `manageNamedParameters` function is used to manage named parameters. It takes a map of parameters as input, iterates over the named operation components, and sets operation values for each component. It then removes utilized names from the parameter map and adds all parameters to the derived map.

```java
    public Map<String, String> manageNamedParameters(Map<String, String> parameterMap) {
        List<String> utilizedNames = new ArrayList<String>();
        Map<String, String> derivedMap = new LinkedHashMap<String, String>();
        for (NamedOperationComponent namedOperationComponent : namedOperationComponents) {
            utilizedNames.addAll(namedOperationComponent.setOperationValues(parameterMap, derivedMap));
        }
        for (String utilizedName : utilizedNames) {
            parameterMap.remove(utilizedName);
        }
        derivedMap.putAll(parameterMap);

        return derivedMap;
    }
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/file/service/operation/NamedOperationManagerImpl.java" line="48">

---

## getNamedOperationComponents

The `getNamedOperationComponents` function is used to get the list of named operation components.

```java
    public List<NamedOperationComponent> getNamedOperationComponents() {
        return namedOperationComponents;
    }
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/file/service/operation/NamedOperationManagerImpl.java" line="52">

---

## setNamedOperationComponents

The `setNamedOperationComponents` function is used to set the list of named operation components.

```java
    public void setNamedOperationComponents(List<NamedOperationComponent> namedOperationComponents) {
        this.namedOperationComponents = namedOperationComponents;
    }
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
