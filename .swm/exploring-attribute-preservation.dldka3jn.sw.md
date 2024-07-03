---
title: Exploring Attribute Preservation
---
Attribute Preservation in BroadleafCommerce-demo refers to the process of maintaining the attributes of a source node when merging it with a patch node. This is achieved by adding only the attributes from the patch node that do not exist in the source node. If an attribute is found in both the source and patch nodes, the attribute of the source node is preserved, ensuring that the original attributes are not overwritten during the merge process.

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/extensibility/context/merge/handlers/AttributePreserveInsert.java" line="30">

---

# AttributePreserveInsert Class

This is the AttributePreserveInsert class where the attribute preservation is implemented. The merge method is the main function that carries out the attribute preservation.

```java
/**
 * Merge the attributes of a source and patch node, only adding attributes from
 * the patch side. When the same attribute is encountered in the source and
 * patch children list, the source attribute is left untouched.
 * @author jfischer
 */
public class AttributePreserveInsert extends BaseHandler {

    @Override
    public Node[] merge(List<Node> nodeList1, List<Node> nodeList2, List<Node> exhaustedNodes) {
        if (CollectionUtils.isEmpty(nodeList1) || CollectionUtils.isEmpty(nodeList2)) {
            return null;
        }
        Node node1 = nodeList1.get(0);
        Node node2 = nodeList2.get(0);
        NamedNodeMap attributes2 = node2.getAttributes();

        Comparator<Object> nameCompare = new Comparator<Object>() {
            @Override
            public int compare(Object arg0, Object arg1) {
                return ((Node) arg0).getNodeName().compareTo(((Node) arg1).getNodeName());
```

---

</SwmSnippet>

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/extensibility/context/merge/handlers/AttributePreserveInsert.java" line="38">

---

# Merge Method

This is the merge method that implements the attribute preservation. It compares the attributes of the first nodes in the two input lists and adds any new attributes from the second node to the first.

```java
    @Override
    public Node[] merge(List<Node> nodeList1, List<Node> nodeList2, List<Node> exhaustedNodes) {
        if (CollectionUtils.isEmpty(nodeList1) || CollectionUtils.isEmpty(nodeList2)) {
            return null;
        }
        Node node1 = nodeList1.get(0);
        Node node2 = nodeList2.get(0);
        NamedNodeMap attributes2 = node2.getAttributes();

        Comparator<Object> nameCompare = new Comparator<Object>() {
            @Override
            public int compare(Object arg0, Object arg1) {
                return ((Node) arg0).getNodeName().compareTo(((Node) arg1).getNodeName());
            }
        };
        Node[] tempNodes = {};
        tempNodes = exhaustedNodes.toArray(tempNodes);
        Arrays.sort(tempNodes, nameCompare);
        int length = attributes2.getLength();
        for (int j = 0; j < length; j++) {
            Node temp = attributes2.item(j);
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
