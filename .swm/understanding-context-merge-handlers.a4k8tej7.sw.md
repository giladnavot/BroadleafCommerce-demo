---
title: Understanding Context Merge Handlers
---
Context Merge Handlers in BroadleafCommerce-demo refer to a set of interfaces and classes that manage the merging of data from different sources. The main interface is `MergeHandler`, which defines the key properties and actions a MergeHandler must perform. It is responsible for the actual merge of data from the patch document to the source document based on the business rules of the implementation.

The `MergeHandler` interface is implemented by various classes like `BaseHandler`, `MergeHandlerAdapter`, and others. Each implementation provides specific merge behavior. For instance, `MergeHandlerAdapter` is an adapter class that allows developers to create a merge handler instance and only override a subset of the functionality, instead of having to provide an independent, full implementation of the `MergeHandler` interface.

The `MergeHandler` interface includes methods like `getChildren()`, `getName()`, and `setChildren()`. The `getChildren()` method retrieves any child merge handlers associated with this handler. Child merge handlers may be added to alter merge behavior for a subsection of the merge area defined by this merge handler. The `getName()` method retrieves the name associated with this merge handler. The `setChildren()` method sets the child merge handlers.

The `MergeHandler` interface and its implementations are used in various parts of the codebase, such as `MergeManager` and `MergePoint`, to perform the merging operations.

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/extensibility/context/merge/handlers/MergeHandler.java" line="33">

---

# MergeHandler Interface

This is the MergeHandler interface. It defines the methods that a MergeHandler must implement. These include methods for performing the merge, setting and retrieving the priority, XPath query, name, and child merge handlers.

```java
public interface MergeHandler {
    
    /**
     * Perform the merge using the supplied list of nodes from the source and
     * patch documents, respectively. Also, a list of nodes that have already
     * been merged is provided and may be used by the implementation when
     * necessary.
     * 
     * @param nodeList1 list of nodes to be merged from the source document
     * @param nodeList2 list of nodes to be merged form the patch document
     * @param exhaustedNodes already merged nodes
     * @return list of merged nodes
     */
    public Node[] merge(List<Node> nodeList1, List<Node> nodeList2, List<Node> exhaustedNodes);
    
    /**
     * Retrieve the priority for the handler. Priorities are used by the MergeManager
     * to establish the order of operations for performing merges.
     * 
     * @return the priority value
     */
```

---

</SwmSnippet>

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/extensibility/context/merge/handlers/BaseHandler.java" line="25">

---

# BaseHandler Class

The BaseHandler class is an abstract class that implements the MergeHandler interface. It provides the common properties required by all MergeHandler implementations.

```java
public abstract class BaseHandler implements MergeHandler, Comparable<Object> {

    protected int priority;
    protected String xpath;
    protected MergeHandler[] children = {};
    protected String name;

    public int getPriority() {
        return priority;
    }

    public String getXPath() {
        return xpath;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public void setXPath(String xpath) {
        this.xpath = xpath;
```

---

</SwmSnippet>

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/extensibility/context/merge/MergeManager.java" line="94">

---

# MergeHandler Usage

This is an example of how MergeHandlers are used in the MergeManager class. The MergeManager maintains an array of MergeHandlers and uses them to perform merges.

```java
    private MergeHandler[] handlers;

    public MergeManager() throws MergeManagerSetupException {
        try {
            Properties props = loadProperties();
            removeSkippedMergeComponents(props);
            setHandlers(props);
        } catch (IOException e) {
            throw new MergeManagerSetupException(e);
        } catch (ClassNotFoundException e) {
            throw new MergeManagerSetupException(e);
        } catch (IllegalAccessException e) {
            throw new MergeManagerSetupException(e);
        } catch (InstantiationException e) {
            throw new MergeManagerSetupException(e);
        }
    }

    private void removeSkippedMergeComponents(Properties props)
            throws UnsupportedEncodingException {
        InputStream inputStream = null;
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
