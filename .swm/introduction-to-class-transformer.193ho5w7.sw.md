---
title: Introduction to Class Transformer
---
A Class Transformer in the BroadleafCommerce-demo repository is a concept used to modify the byte code of a class at load time. It is an interface or class that implements the `javax.persistence.spi.ClassTransformer` interface. The `BroadleafClassTransformer` interface extends this, adding a `compileJPAProperties` method. This method allows the transformer to modify JPA properties before they are sent to the persistence provider.

Class Transformers are used in various parts of the codebase. For example, the `SingleTableInheritanceClassTransformer` modifies the inheritance strategy of a class to Single Table Inheritance. The `AlterTableNameClassTransformer` changes the name of the table for an entity before Hibernate sees it. This allows for safe alteration of table names for entities on patch releases.

The `EntityMarkerClassTransformer` is a special Class Transformer that checks if a class should have been loaded by the `MergePersistenceUnitManager`. If it should have, it adds the fully qualified class name of that class to the `transformedClassNames` list. This is a validation check to ensure that the class transformers are working properly.

The `BroadleafPersistenceUnitDeclaringClassTransformer` interface extends `BroadleafClassTransformer`, adding a `getPersistenceUnitName` method. This allows a class transformer to explicitly declare which persistence unit it will influence.

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/extensibility/jpa/convert/BroadleafClassTransformer.java" line="28">

---

# BroadleafClassTransformer Interface

The `BroadleafClassTransformer` interface defines the contract for all class transformers in the BroadleafCommerce-demo repository. It extends the `ClassTransformer` interface and adds a `compileJPAProperties` method, which can be used to modify the JPA properties of the classes being transformed.

```java
public interface BroadleafClassTransformer extends ClassTransformer {

    public void compileJPAProperties(Properties props, Object key) throws Exception;
        
}
```

---

</SwmSnippet>

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/extensibility/jpa/convert/EntityMarkerClassTransformer.java" line="56">

---

# EntityMarkerClassTransformer

The `EntityMarkerClassTransformer` is an example of a class transformer. It checks if a class should have been loaded by the `MergePersistenceUnitManager` and adds the class name to a list if it should have. This is used as a validation check to ensure that the class transformers are working properly.

```java
public class EntityMarkerClassTransformer extends AbstractClassTransformer implements BroadleafClassTransformer {
    protected static final Log LOG = LogFactory.getLog(EntityMarkerClassTransformer.class);
    
    protected HashSet<String> transformedEntityClassNames = new HashSet<String>();
    
    protected HashSet<String> transformedNonEntityClassNames = new HashSet<String>();
    
    @Resource(name = "blDirectCopyIgnorePatterns")
    protected List<DirectCopyIgnorePattern> ignorePatterns = new ArrayList<DirectCopyIgnorePattern>();

    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
        // Lambdas and anonymous methods in Java 8 do not have a class name defined and so no transformation should be done
        if (className == null) {
            return null;
        }

        String convertedClassName = className.replace('/', '.');
        
        if (isIgnored(convertedClassName)) {
            return null;
```

---

</SwmSnippet>

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/extensibility/jpa/convert/inheritance/SingleTableInheritanceClassTransformer.java" line="57">

---

# SingleTableInheritanceClassTransformer

The `SingleTableInheritanceClassTransformer` is another example of a class transformer. It converts the inheritance strategy of a class to `SINGLE_TABLE`. This is useful when you want to store the hierarchy of classes in a single database table.

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

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/extensibility/jpa/convert/AlterTableNameClassTransformer.java" line="78">

---

# AlterTableNameClassTransformer

The `AlterTableNameClassTransformer` changes the name of the table for an entity before Hibernate sees it. This allows for safe alteration of table names for entities on patch releases. Note that this will add a new table in the database and does not change the name of existing tables.

```java
public class AlterTableNameClassTransformer extends AbstractClassTransformer implements BroadleafClassTransformer {

    private static final Log LOG = LogFactory.getLog(AlterTableNameClassTransformer.class);

    protected String tableName;

    protected String targetedClass;

    public AlterTableNameClassTransformer() {
        this(null, null);
    }

    public AlterTableNameClassTransformer(String tableName) {
        this(tableName, null);
    }

    public AlterTableNameClassTransformer(String tableName, String targetedClass) {
        this.tableName = tableName;
        this.targetedClass = targetedClass;
        if (LOG.isDebugEnabled()) {
            LOG.debug("Constructed table name Transformer. Targeted Class:" + targetedClass + " Table Name: " + tableName);
```

---

</SwmSnippet>

# Class Transformer Functions

This section discusses the main functions of the Class Transformer in BroadleafCommerce-demo.

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/extensibility/jpa/convert/inheritance/SingleTableInheritanceClassTransformer.java" line="64">

---

## compileJPAProperties

The `compileJPAProperties` function is used to compile JPA properties. It takes in a Properties object and a key, and modifies the properties based on the key. This function is crucial for setting up the JPA environment correctly.

```java
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
                String discriminatorName = props.getProperty("broadleaf.ejb."+keyName+".discriminator.name");
                if (discriminatorName != null) {
                    info.setDiscriminatorName(discriminatorName);
                    String type = props.getProperty("broadleaf.ejb."+keyName+".discriminator.type");
                    if (type != null) {
                        info.setDiscriminatorType(DiscriminatorType.valueOf(type));
                    }
```

---

</SwmSnippet>

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/extensibility/jpa/convert/inheritance/SingleTableInheritanceClassTransformer.java" line="96">

---

## transform

The `transform` function is used to transform the bytecode of a class. It takes in a ClassLoader, a className, a classBeingRedefined, a protectionDomain, and a classfileBuffer. It modifies the bytecode of the class and returns the modified bytecode. This function is crucial for manipulating the bytecode of classes for various purposes such as altering table names, marking entities, and handling inheritance strategies.

```java
    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
        // Lambdas and anonymous methods in Java 8 do not have a class name defined and so no transformation should be done
        if (className == null) {
            return null;
        }
        
        if (infos.isEmpty()) {
            return null;
        }
        String convertedClassName = className.replace('/', '.');
        SingleTableInheritanceInfo key = new SingleTableInheritanceInfo();
        key.setClassName(convertedClassName);
        int pos = infos.indexOf(key);
        if (pos >= 0) {
            try {
                if (LOG.isDebugEnabled()) {
                    LOG.debug("Converting " + convertedClassName + " to a SingleTable inheritance strategy."); 
                }
                SingleTableInheritanceInfo myInfo = infos.get(pos);
                ClassFile classFile = new ClassFile(new DataInputStream(new ByteArrayInputStream(classfileBuffer)));
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
