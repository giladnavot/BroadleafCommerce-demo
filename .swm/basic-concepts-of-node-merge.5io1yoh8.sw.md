---
title: Basic Concepts of Node Merge
---
Node Merge in the BroadleafCommerce-demo repository refers to the process of combining two nodes. This is done in the `merge` method of the `NodeValueMerge` class. The method takes two lists of nodes as input and merges the first node from each list. The merging process involves combining the values of the two nodes into a set, which ensures that there are no duplicate values. The combined values are then set as the new value for both nodes. The `merge` method returns a new list of nodes, which includes the merged nodes and any remaining nodes from the second input list.

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/extensibility/context/merge/handlers/NodeValueMerge.java" line="42">

---

# Node Merge in Action

This is the `merge` method, which is the entry point for the Node Merge process. It takes two lists of nodes as input, merges the first node from each list, and returns the merged nodes.

```java
    @Override
    public Node[] merge(List<Node> nodeList1, List<Node> nodeList2, List<Node> exhaustedNodes) {
        if (CollectionUtils.isEmpty(nodeList1) || CollectionUtils.isEmpty(nodeList2)) {
            return null;
        }
        Node node1 = nodeList1.get(0);
        Node node2 = nodeList2.get(0);
        Set<String> finalItems = getMergedNodeValues(node1, node2);
        StringBuilder sb = new StringBuilder();
        Iterator<String> itr = finalItems.iterator();
        while (itr.hasNext()) {
            sb.append(itr.next());
            if (itr.hasNext()) {
                sb.append(getDelimiter());
            }
        }
        node1.setNodeValue(sb.toString());
        node2.setNodeValue(sb.toString());

        Node[] response = new Node[nodeList2.size()];
        for (int j=0;j<response.length;j++){
```

---

</SwmSnippet>

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/extensibility/context/merge/handlers/NodeValueMerge.java" line="68">

---

# Merging Node Values

This is the `getMergedNodeValues` method, which is responsible for the actual merging of the node values. It splits the node values using a regular expression, adds them to a set (which automatically removes duplicates), and returns the set of merged values.

```java
    protected Set<String> getMergedNodeValues(Node node1, Node node2) {
        String[] items1 = node1.getNodeValue().split(getRegEx());
        String[] items2 = node2.getNodeValue().split(getRegEx());
        Set<String> finalItems = new LinkedHashSet<String>();
        for (String anItems1 : items1) {
            finalItems.add(anItems1.trim());
        }
        for (String anItems2 : items2) {
            finalItems.add(anItems2.trim());
        }
        return finalItems;
    }
```

---

</SwmSnippet>

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/extensibility/context/merge/handlers/CommaDelimitedNodeValueMerge.java" line="23">

---

# Customizing Node Merge

This is an example of a subclass of `NodeValueMerge` that overrides the `getDelimiter` and `getRegEx` methods to customize the Node Merge process. In this case, the nodes are merged using a comma as the delimiter.

```java
public class CommaDelimitedNodeValueMerge extends NodeValueMerge {

    @Override
    public String getDelimiter() {
        return ",";
    }

    @Override
    public String getRegEx() {
        return getDelimiter();
    }
```

---

</SwmSnippet>

# Node Merge Functions

This section covers the main Node Merge functions in the BroadleafCommerce-demo repository.

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/extensibility/context/merge/handlers/NodeValueMerge.java" line="37">

---

## NodeValueMerge

The NodeValueMerge class is a base handler for merging node values. It provides a 'merge' function that merges the text values of a source and patch node. The resulting string is a union of the two without any repeat values. The 'getMergedNodeValues' function is used to get the merged node values from two nodes.

```java
public class NodeValueMerge extends BaseHandler {

    protected String delimiter = " ";
    protected String regex = "[\\s\\n\\r]+";

    @Override
    public Node[] merge(List<Node> nodeList1, List<Node> nodeList2, List<Node> exhaustedNodes) {
        if (CollectionUtils.isEmpty(nodeList1) || CollectionUtils.isEmpty(nodeList2)) {
            return null;
        }
        Node node1 = nodeList1.get(0);
        Node node2 = nodeList2.get(0);
        Set<String> finalItems = getMergedNodeValues(node1, node2);
        StringBuilder sb = new StringBuilder();
        Iterator<String> itr = finalItems.iterator();
        while (itr.hasNext()) {
            sb.append(itr.next());
            if (itr.hasNext()) {
                sb.append(getDelimiter());
            }
        }
```

---

</SwmSnippet>

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/extensibility/context/merge/handlers/CommaDelimitedNodeValueMerge.java" line="23">

---

## CommaDelimitedNodeValueMerge

The CommaDelimitedNodeValueMerge class extends NodeValueMerge and overrides the 'getDelimiter' and 'getRegEx' functions to return a comma, indicating that the node values are comma-delimited.

```java
public class CommaDelimitedNodeValueMerge extends NodeValueMerge {

    @Override
    public String getDelimiter() {
        return ",";
    }

    @Override
    public String getRegEx() {
        return getDelimiter();
    }
}
```

---

</SwmSnippet>

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/extensibility/context/merge/handlers/SpaceDelimitedNodeValueMerge.java" line="23">

---

## SpaceDelimitedNodeValueMerge

The SpaceDelimitedNodeValueMerge class extends NodeValueMerge and overrides the 'getDelimiter' function to return a space, indicating that the node values are space-delimited.

```java
public class SpaceDelimitedNodeValueMerge extends NodeValueMerge {

    @Override
    public String getDelimiter() {
        return " ";
    }
}
```

---

</SwmSnippet>

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/extensibility/context/merge/handlers/SchemaLocationNodeValueMerge.java" line="39">

---

## SchemaLocationNodeValueMerge

The SchemaLocationNodeValueMerge class extends SpaceDelimitedNodeValueMerge and is designed to handle the merge of schemaLocation references. It sanitizes the given attribute value by stripping out the version number for the Spring XSDs.

```java
public class SchemaLocationNodeValueMerge extends SpaceDelimitedNodeValueMerge {

    @Override
    protected Set<String> getMergedNodeValues(Node node1, Node node2) {
        String node1Values = getSanitizedValue(node1.getNodeValue());
        String node2Values = getSanitizedValue(node2.getNodeValue());
        
        Set<String> finalItems = new LinkedHashSet<String>();
        for (String node1Value : node1Values.split(getRegEx())) {
            finalItems.add(node1Value.trim());
        }
        for (String node2Value : node2Values.split(getRegEx())) {
            // Only add in this new attribute value if we haven't seen it yet
            if (!finalItems.contains(node2Value.trim())) {
                finalItems.add(node2Value.trim());
            }
        }
        return finalItems;
    }
    
    /**
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
