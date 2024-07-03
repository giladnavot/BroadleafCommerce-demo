---
title: Understanding Node Insertion
---
Node Insertion in this repository refers to the process of adding new nodes to the existing DOM (Document Object Model) tree. This is done using the `merge` method in the `InsertItems` and `InsertChildrenOf` classes. The `merge` method takes two lists of nodes and appends nodes from the second list to the first one. This is a crucial part of the extensibility context merge handlers, which allow for the modification of the application's configuration files at runtime.

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/extensibility/context/merge/handlers/InsertItems.java" line="35">

---

# InsertItems Class

In the `InsertItems` class, the `merge` method is used to append a list of nodes from the patch document to the same parent element in the source document. This is done by iterating over the nodes in `nodeList2`, cloning each node, and appending it to the parent node of the nodes in `nodeList1`.

```java
public class InsertItems extends BaseHandler {

    private static final Log LOG = LogFactory.getLog(InsertItems.class);

    public Node[] merge(List<Node> nodeList1, List<Node> nodeList2, List<Node> exhaustedNodes) {
        if (CollectionUtils.isEmpty(nodeList1) || CollectionUtils.isEmpty(nodeList2)) {
            return null;
        }
        List<Node> usedNodes = new ArrayList<Node>();
        Node node1Parent = nodeList1.get(0).getParentNode();
        for (Node aNodeList2 : nodeList2) {
            Node tempNode = node1Parent.getOwnerDocument().importNode(aNodeList2.cloneNode(true), true);
            if (LOG.isDebugEnabled()) {
                StringBuffer sb = new StringBuffer();
                sb.append("matching node for insertion: ");
                sb.append(tempNode.getNodeName());
                int attrLength = tempNode.getAttributes().getLength();
                for (int x = 0; x < attrLength; x++) {
                    sb.append(" : (");
                    sb.append(tempNode.getAttributes().item(x).getNodeName());
                    sb.append("/");
```

---

</SwmSnippet>

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/extensibility/context/merge/handlers/InsertChildrenOf.java" line="32">

---

# InsertChildrenOf Class

In the `InsertChildrenOf` class, the `merge` method is used to append the child nodes from a node in the patch document to the same node in the source document. This is done by iterating over the child nodes of the node in `nodeList2`, cloning each child node, and appending it to the node in `nodeList1`.

```java
public class InsertChildrenOf extends BaseHandler {

    public Node[] merge(List<Node> nodeList1, List<Node> nodeList2, List<Node> exhaustedNodes) {
        if (CollectionUtils.isEmpty(nodeList1) || CollectionUtils.isEmpty(nodeList2)) {
            return null;
        }
        Node node1 = nodeList1.get(0);
        Node node2 = nodeList2.get(0);
        NodeList list2 = node2.getChildNodes();
        for (int j = 0; j < list2.getLength(); j++) {
            node1.appendChild(node1.getOwnerDocument().importNode(list2.item(j).cloneNode(true), true));
        }

        Node[] response = new Node[nodeList2.size()];
        for (int j = 0; j < response.length; j++) {
            response[j] = nodeList2.get(j);
        }
        return response;
    }
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
