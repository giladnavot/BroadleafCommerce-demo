---
title: Introduction to Inheritance
---
Inheritance is a fundamental concept in object-oriented programming where a class (child class) inherits properties and methods from another class (parent class). In the BroadleafCommerce-demo repository, inheritance is used to extend the functionality of classes and to promote code reusability. For instance, the `SingleTableInheritanceInfo` class is used to store information about classes that use single table inheritance strategy. This class has properties like `className`, `discriminatorName`, `discriminatorType`, and `discriminatorLength` that are common to all classes using single table inheritance. The `SingleTableInheritanceClassTransformer` class uses instances of `SingleTableInheritanceInfo` to transform classes to use single table inheritance strategy.

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/extensibility/jpa/convert/inheritance/SingleTableInheritanceInfo.java" line="27">

---

# SingleTableInheritanceInfo Class

The `SingleTableInheritanceInfo` class is an example of a simple Java class that can be extended by other classes. It has several protected fields (`className`, `discriminatorName`, `discriminatorType`, `discriminatorLength`), which can be accessed directly by any class that extends it. It also has getter and setter methods for these fields, which can be overridden by child classes if necessary.

```java
public class SingleTableInheritanceInfo {

    protected String className;
    protected String discriminatorName;
    protected DiscriminatorType discriminatorType;
    protected int discriminatorLength;
    
    public String getClassName() {
        return className;
    }
    
    public void setClassName(String className) {
        this.className = className;
    }
    
    public String getDiscriminatorName() {
        return discriminatorName;
    }
    
    public void setDiscriminatorName(String discriminatorName) {
        this.discriminatorName = discriminatorName;
```

---

</SwmSnippet>

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/extensibility/jpa/convert/inheritance/SingleTableInheritanceClassTransformer.java" line="57">

---

# SingleTableInheritanceClassTransformer Class

The `SingleTableInheritanceClassTransformer` class demonstrates how inheritance can be used to extend the functionality of a class. This class extends the `AbstractClassTransformer` class and implements the `BroadleafClassTransformer` interface, inheriting fields and methods from both. It also defines its own fields and methods, and overrides some of the inherited methods to provide its own implementation.

```java
public class SingleTableInheritanceClassTransformer extends AbstractClassTransformer implements BroadleafClassTransformer {
    
    public static final String SINGLE_TABLE_ENTITIES = "broadleaf.ejb.entities.override_single_table";
    
    private static final Log LOG = LogFactory.getLog(SingleTableInheritanceClassTransformer.class);
    protected List<SingleTableInheritanceInfo> infos = new ArrayList<SingleTableInheritanceInfo>();

    @Override
    public void compileJPAProperties(Properties props, Object key) throws Exception {
        if (((String) key).equals(SINGLE_TABLE_ENTITIES)) {
            String[] classes = StringUtils.tokenizeToStringArray(props.getProperty((String) key), ConfigurableApplicationContext.CONFIG_LOCATION_DELIMITERS);
            for (String clazz : classes) {
                String keyName;
                int pos = clazz.lastIndexOf(".");
                if (pos >= 0) {
                    keyName = clazz.substring(pos + 1, clazz.length());
                } else {
                    keyName = clazz;
                }
                SingleTableInheritanceInfo info = new SingleTableInheritanceInfo();
                info.setClassName(clazz);
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
