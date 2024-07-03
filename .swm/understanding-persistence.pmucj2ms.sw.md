---
title: Understanding Persistence
---
Persistence in the BroadleafCommerce-demo repository refers to the mechanism of storing and retrieving data between different runs of the application. It is crucial for maintaining the state of the application and ensuring data consistency. The repository uses Java Persistence API (JPA) for this purpose, as seen in the usage of annotations like `@Embeddable` and `@Column` in the `ArchiveStatus.java` and `PreviewStatus.java` files.

The repository also provides a set of helper classes and interfaces, such as `EntityDuplicationHelper`, to facilitate the process of data persistence. These helpers provide additional metadata and perform final modifications for an entity before it is persisted. They are used in conjunction with the `EntityDuplicator` class, which is responsible for duplicating entities.

The persistence mechanism is crucial for the functioning of the e-commerce framework provided by the BroadleafCommerce-demo repository. It ensures that the data related to products, orders, customers, and other e-commerce entities is consistently stored and retrieved, thereby enabling the smooth operation of the e-commerce platform.

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/persistence/EntityDuplicationHelper.java" line="25">

---

# EntityDuplicationHelper Interface

The EntityDuplicationHelper interface provides methods for handling entity duplication. It includes methods for checking if an entity can be handled, getting and adding copy hints, and modifying the initial duplicate state of an entity.

```java
/**
 * Provides additional metadata and performs final modifications for an entity before persistence.
 * 
 * In order to perform duplication using {@link EntityDuplicator}, an 
 * {@code EntityDuplicationHelper} must be made for a specific entity.
 * 
 * @author Nathan Moore (nathanmoore).
 */
public interface EntityDuplicationHelper<T> {

    boolean canHandle(MultiTenantCloneable candidate);

    /**
     * @return Hints used to fine tune copying - generally support for hints is included in 
     * {@link org.broadleafcommerce.common.copy.MultiTenantCloneable#createOrRetrieveCopyInstance(org.broadleafcommerce.common.copy.MultiTenantCopyContext)} implementations.
     */
    Map<String, String> getCopyHints();
    
    void addCopyHint(final String name, final String hint);

    void modifyInitialDuplicateState(T original, T copy, MultiTenantCopyContext context) throws CloneNotSupportedException;
```

---

</SwmSnippet>

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/persistence/PostLoaderDao.java" line="20">

---

# PostLoaderDao Interface

The PostLoaderDao interface provides methods for retrieving entities from the database. It includes methods for finding an entity by its class and primary key, and for finding a sandbox entity.

```java
/**
 * Utility class for working with proxied entities.
 *
 * The {@link DefaultPostLoaderDao} in core delegates functionally to
 * {@link javax.persistence.EntityManager}, while more interesting
 * functionality is provided by the enterprise version.
 *
 * @see DefaultPostLoaderDao
 * @author Nathan Moore (nathanmoore).
 */
public interface PostLoaderDao {
    /**
     * Find the entity by primary key and class, and, if found in
     * the persistence context, return the deproxied version.
     *
     * @param clazz entity class
     * @param id primary key
     * @return deproxied entity or null if not found
     */
    <T> T find(Class<T> clazz, Object id);

```

---

</SwmSnippet>

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/persistence/IdOverrideTableGenerator.java" line="70">

---

# IdOverrideTableGenerator Class

The IdOverrideTableGenerator class provides a method for generating unique identifiers for entities. It checks if an entity already has an identifier and if not, it generates a new one.

```java
    @Override
    public Serializable generate(final SharedSessionContractImplementor session, final Object obj) {
        /*
        This works around an issue in Hibernate where if the entityPersister is retrieved
        from the session and used to get the Id, the entity configuration can be recycled,
        which is messing with the load persister and current persister on some collections.
        This may be a jrebel thing, but this workaround covers all environments
         */
        String objName = obj.getClass().getName();
        if (!FIELD_CACHE.containsKey(objName)) {
            Field field = getIdField(obj.getClass());
            if (field == null) {
                throw new IllegalArgumentException("Cannot specify IdOverrideTableGenerator for an entity (" + objName + ") that does not have an Id field declared using the @Id annotation.");
            }
            field.setAccessible(true);
            FIELD_CACHE.put(objName, field);
        }
        Field field = FIELD_CACHE.get(objName);
        final Serializable id;
        try {
            id = (Serializable) field.get(obj);
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
