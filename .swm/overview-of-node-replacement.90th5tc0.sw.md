---
title: Overview of Node Replacement
---
Node Replacement in the BroadleafCommerce-demo repository refers to the functionality of replacing nodes in the source document with the same nodes from the patch document. This is done by the `NodeReplace` and `NodeReplaceInsert` classes, which are responsible for replacing nodes entirely, regardless of differences in attributes. The `replaceNode` method is used to perform the actual replacement of nodes. It checks if the node names are the same, and if so, it replaces the node in the primary document with the node from the patch document. The `checkNode` method is used to check if a node should be replaced, based on certain conditions. If the conditions are met, the node is replaced; otherwise, it is not. This functionality is crucial for maintaining the integrity of the document structure during the merge process.

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/extensibility/context/merge/handlers/NodeReplace.java" line="24">

---

# NodeReplace Class

The NodeReplace class is responsible for replacing nodes in the source document with the same nodes from the patch document. It does this by checking if a node exists in the primary nodes and if it does, it replaces it with the new node.

```java
/**
 * This handler is responsible for replacing nodes in the source document
 * with the same nodes from the patch document. This handler will replace
 * all nodes with the same name entirely, regardless of differences in
 * attributes.
 * 
 * @author jfischer
 *
 */
public class NodeReplace extends NodeReplaceInsert {

    @Override
    protected boolean checkNode(List<Node> usedNodes, Node[] primaryNodes, Node node) {
        if (replaceNode(primaryNodes, node, usedNodes)) {
            return true;
        }
        //check if this same node already exists
        if (exactNodeExists(primaryNodes, node, usedNodes)) {
            return true;
        }
        return false;
```

---

</SwmSnippet>

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/extensibility/context/merge/handlers/NodeReplaceInsert.java" line="34">

---

# NodeReplaceInsert Class

The NodeReplaceInsert class is responsible for replacing nodes in the source document with the same nodes from the patch document, and appending additional nodes from the patch document that are not present in the source document. It does this by checking if a node exists in the primary nodes and if it does, it replaces it with the new node. If the node does not exist, it simply appends the node to the source document.

```java
/**
 * This handler is responsible for replacing nodes in the source document
 * with the same nodes from the patch document. Note, additional nodes
 * from the patch document that are not present in the source document
 * are simply appended to the source document.
 * 
 * @author jfischer
 *
 */
public class NodeReplaceInsert extends BaseHandler {

    private static final Log LOG = LogFactory.getLog(NodeReplaceInsert.class);

    private static final Comparator<Node> NODE_COMPARATOR = new Comparator<Node>() {

        @Override
        public int compare(Node arg0, Node arg1) {
            int response = -1;
            if (arg0.isSameNode(arg1)) {
                response = 0;
            }
```

---

</SwmSnippet>

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/extensibility/context/merge/handlers/NodeReplace.java" line="47">

---

# replaceNode Method

The replaceNode method is used to replace a node in the primary nodes with a test node. It does this by iterating through the primary nodes and checking if the node name of the primary node is equal to the node name of the test node. If it is, it replaces the primary node with the new node and adds the test node to the used nodes.

```java
    protected boolean replaceNode(Node[] primaryNodes, Node testNode, List<Node> usedNodes) {
        boolean foundItem = false;
        for (int j=0;j<primaryNodes.length;j++){
            if (primaryNodes[j].getNodeName().equals(testNode.getNodeName())) {
                Node newNode = primaryNodes[j].getOwnerDocument().importNode(testNode.cloneNode(true), true);
                primaryNodes[j].getParentNode().replaceChild(newNode, primaryNodes[j]);
                usedNodes.add(testNode);
                foundItem = true;
            }
        }

        return foundItem;
    }
```

---

</SwmSnippet>

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/extensibility/context/merge/handlers/NodeReplace.java" line="35">

---

# checkNode Method

The checkNode method is used to check if a node should be replaced or not. It does this by calling the replaceNode method and the exactNodeExists method. If either of these methods return true, the checkNode method also returns true, indicating that the node should be replaced.

```java
    @Override
    protected boolean checkNode(List<Node> usedNodes, Node[] primaryNodes, Node node) {
        if (replaceNode(primaryNodes, node, usedNodes)) {
            return true;
        }
        //check if this same node already exists
        if (exactNodeExists(primaryNodes, node, usedNodes)) {
            return true;
        }
        return false;
    }
```

---

</SwmSnippet>

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/extensibility/context/merge/handlers/NodeReplaceInsert.java" line="161">

---

# exactNodeExists Method

The exactNodeExists method is used to check if a node exactly exists in the primary nodes. It does this by iterating through the primary nodes and checking if the primary node is equal to the test node. If it is, it adds the primary node to the used nodes and returns true, indicating that the node exactly exists.

```java
    protected boolean exactNodeExists(Node[] primaryNodes, Node testNode, List<Node> usedNodes) {
        for (int j = 0; j < primaryNodes.length; j++) {
            if (primaryNodes[j].isEqualNode(testNode)) {
                usedNodes.add(primaryNodes[j]);
                return true;
            }
        }
        return false;
    }
```

---

</SwmSnippet>

# Node Replacement Functions

The Node Replacement functionality in Broadleaf Commerce is designed to handle the replacement of nodes in the source document with the same nodes from the patch document. This is achieved through several methods implemented in different classes.

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/extensibility/context/merge/handlers/NodeReplaceInsert.java" line="73">

---

## Merge Function

The `merge` function is responsible for merging two lists of nodes. If either of the lists is empty, the function returns null. Otherwise, it creates a new list of nodes, matches them, and returns the used nodes.

```java
    @Override
    public Node[] merge(List<Node> nodeList1, List<Node> nodeList2, List<Node> exhaustedNodes) {
        if (CollectionUtils.isEmpty(nodeList1) || CollectionUtils.isEmpty(nodeList2)) {
            return null;
        }
        Node[] primaryNodes = new Node[nodeList1.size()];
        for (int j = 0; j < primaryNodes.length; j++) {
            primaryNodes[j] = nodeList1.get(j);
        }

        ArrayList<Node> list = new ArrayList<Node>();
        for (int j = 0; j < nodeList2.size(); j++) {
            list.add(nodeList2.get(j));
        }

        List<Node> usedNodes = matchNodes(exhaustedNodes, primaryNodes, list);

        Node[] response = {};
        response = usedNodes.toArray(response);
        return response;
    }
```

---

</SwmSnippet>

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/extensibility/context/merge/handlers/NodeReplaceInsert.java" line="141">

---

## CheckNode Function

The `checkNode` function checks if a node should be replaced based on certain conditions. It calls other functions like `replaceNode` and `exactNodeExists` to determine if a node should be replaced.

```java
    protected boolean checkNode(List<Node> usedNodes, Node[] primaryNodes, Node node) {
        //find matching nodes based on id
        if (replaceNode(primaryNodes, node, "id", usedNodes)) {
            return true;
        }
        //find matching nodes based on name
        if (replaceNode(primaryNodes, node, "name", usedNodes)) {
            return true;
        }
        //find matching nodes based on alias (required for EhCache 3 cache definitions)
        if (replaceNode(primaryNodes, node, "alias", usedNodes)) {
            return true;
        }
        //check if this same node already exists
        if (exactNodeExists(primaryNodes, node, usedNodes)) {
            return true;
        }
        return false;
    }
```

---

</SwmSnippet>

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/extensibility/context/merge/handlers/NodeReplaceInsert.java" line="171">

---

## ReplaceNode Function

The `replaceNode` function is responsible for replacing nodes in the primary nodes array with the test node if they have the same attribute. It uses a comparator to compare nodes and replaces the found node with a new node if a match is found.

```java
    protected boolean replaceNode(Node[] primaryNodes, Node testNode, final String attribute, List<Node> usedNodes) {
        if (testNode.getAttributes().getNamedItem(attribute) == null) {
            return false;
        }

        Node[] filtered = NodeUtil.filterByAttribute(primaryNodes, attribute);

        int pos = NodeUtil.findNode(filtered, testNode, attribute, true);

        if (pos >= 0) {
            Node foundNode = filtered[pos];

            Node newNode = foundNode.getOwnerDocument().importNode(testNode.cloneNode(true), true);
            foundNode.getParentNode().replaceChild(newNode, foundNode);
            usedNodes.add(testNode);
            return true;
        }
        return false;

    }
```

---

</SwmSnippet>

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/extensibility/context/merge/handlers/NodeReplaceInsert.java" line="161">

---

## ExactNodeExists Function

The `exactNodeExists` function checks if an exact node already exists in the primary nodes array. If it does, it adds the node to the used nodes list and returns true.

```java
    protected boolean exactNodeExists(Node[] primaryNodes, Node testNode, List<Node> usedNodes) {
        for (int j = 0; j < primaryNodes.length; j++) {
            if (primaryNodes[j].isEqualNode(testNode)) {
                usedNodes.add(primaryNodes[j]);
                return true;
            }
        }
        return false;
    }
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
