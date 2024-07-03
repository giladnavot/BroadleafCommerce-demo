---
title: Getting Started with Row Level Security
---
Row Level Security in BroadleafCommerce-demo refers to a security mechanism that controls data access at the row level in the database. It is implemented through the `RowLevelSecurityService` interface and its implementation `RowLevelSecurityServiceImpl`. The service provides methods to add restrictions to data fetch operations, validate add/update/remove requests, and check permissions for add/update/remove operations. It uses `RowLevelSecurityProvider` instances to apply these security measures. Developers can extend the `AbstractRowLevelSecurityProvider` to create custom security providers and add them to the service.

<SwmSnippet path="/admin/broadleaf-open-admin-platform/src/main/java/org/broadleafcommerce/openadmin/server/security/service/RowLevelSecurityService.java" line="22">

---

# RowLevelSecurityService Interface

The `RowLevelSecurityService` interface defines the methods that are used to apply row-level security. It extends the `RowLevelSecurityProvider` interface, meaning it inherits all its methods. It also has a method `getProviders()` that returns a list of all registered `RowLevelSecurityProvider`s.

```java
/**
 * <p>
 * Provides row-level security to the various CRUD operations in the admin
 * 
 * <p>
 * This security service can be extended by the use of {@link RowLevelSecurityProviders}, of which this service has a list.
 * To add additional providers, add this to an applicationContext merged into the admin application:
 * 
 * {@code
 *  <bean id="blCustomRowSecurityProviders" class="org.springframework.beans.factory.config.ListFactoryBean" >
 *       <property name="sourceList">
 *          <list>
 *              <ref bean="customProvider" />
 *          </list>
 *      </property>
 *  </bean>
 *  <bean class="org.broadleafcommerce.common.extensibility.context.merge.LateStageMergeBeanPostProcessor">
 *      <property name="collectionRef" value="blCustomRowSecurityProviders" />
 *      <property name="targetRef" value="blRowLevelSecurityProviders" />
 *  </bean>
 * }
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-open-admin-platform/src/main/java/org/broadleafcommerce/openadmin/server/security/service/RowLevelSecurityServiceImpl.java" line="47">

---

# RowLevelSecurityServiceImpl Class

The `RowLevelSecurityServiceImpl` class is the implementation of the `RowLevelSecurityService` interface. It uses a list of `RowLevelSecurityProvider`s to apply the appropriate restrictions. Each provider is checked and if it denies access, the operation is not allowed.

```java
@Service("blRowLevelSecurityService")
public class RowLevelSecurityServiceImpl implements RowLevelSecurityService, ExceptionAwareRowLevelSecurityProvider {
    
    private static final Log LOG = LogFactory.getLog(RowLevelSecurityServiceImpl.class);
    
    @Resource(name = "blRowLevelSecurityProviders")
    protected List<RowLevelSecurityProvider> providers;
    
    @Override
    public void addFetchRestrictions(AdminUser currentUser, String ceilingEntity, List<Predicate> restrictions, List<Order> sorts,
            Root entityRoot,
            CriteriaQuery criteria,
            CriteriaBuilder criteriaBuilder) {
        for (RowLevelSecurityProvider provider : getProviders()) {
            provider.addFetchRestrictions(currentUser, ceilingEntity, restrictions, sorts, entityRoot, criteria, criteriaBuilder);
        }
    }
    
    @Override
    public Class<Serializable> getFetchRestrictionRoot(AdminUser currentUser, Class<Serializable> ceilingEntity, List<FilterMapping> filterMappings) {
        Class<Serializable> root = null;
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-open-admin-platform/src/main/java/org/broadleafcommerce/openadmin/server/security/service/RowLevelSecurityProvider.java" line="43">

---

# RowLevelSecurityProvider Interface

The `RowLevelSecurityProvider` interface defines the methods that are used to check if a user can perform certain operations. It includes methods like `canUpdate()`, `canRemove()`, and `canAdd()` that check if a user can update, remove, or add an entity respectively.

```java
/**
 * <p>
 * A component that can apply row-level security to the admin
 * 
 * <p>
 * Implementations of this class should extend from the {@link AbstractRowLevelSecurityProvider}
 * 
 * @author Phillip Verheyden (phillipuniverse)
 * @author Jeff Fischer
 * @see {@link AbstractRowLevelSecurityProvider}
 * @see {@link RowLevelSecurityService}
 */
public interface RowLevelSecurityProvider {

    /**
     * <p>
     * Used to further restrict a result set in the admin for a particular admin user. This can be done by adding additional
     * {@link Predicate}s to the given list of <b>restrictions</b>. You can also attach additional sorting from the given
     * list of <b>sorts</b>.
     * 
     * <p>
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-open-admin-platform/src/main/java/org/broadleafcommerce/openadmin/server/security/service/AbstractRowLevelSecurityProvider.java" line="36">

---

# AbstractRowLevelSecurityProvider Class

The `AbstractRowLevelSecurityProvider` class is a dummy implementation of the `RowLevelSecurityProvider` interface. It can be extended to create custom providers. By default, it allows all operations.

```java
/**
 * Dummy implementation of a {@link RowLevelSecurityProvider}. Implementors should extend this class
 * 
 * @author Phillip Verheyden (phillipuniverse)
 * @author Jeff Fischer
 */
public class AbstractRowLevelSecurityProvider implements RowLevelSecurityProvider {

    @Override
    public void addFetchRestrictions(AdminUser currentUser, String ceilingEntity, List<Predicate> restrictions, List<Order> sorts, Root entityRoot, CriteriaQuery criteria, CriteriaBuilder criteriaBuilder) {
        // intentionally unimplemented
    }

    @Override
    public Class<Serializable> getFetchRestrictionRoot(AdminUser currentUser, Class<Serializable> ceilingEntity, List<FilterMapping> filterMappings) {
        return null;
    }

    @Override
    public boolean canUpdate(AdminUser currentUser, Entity entity) {
        return true;
```

---

</SwmSnippet>

# Row Level Security Functions

Row Level Security in BroadleafCommerce-demo is implemented through several functions that provide restrictions and validations based on user roles and claims. These functions are part of the RowLevelSecurityService and are implemented in the RowLevelSecurityServiceImpl class.

<SwmSnippet path="/admin/broadleaf-open-admin-platform/src/main/java/org/broadleafcommerce/openadmin/server/security/service/RowLevelSecurityServiceImpl.java" line="55">

---

## addFetchRestrictions

The `addFetchRestrictions` function is used to add additional restrictions to the result set in the admin for a particular admin user. This is done by adding additional predicates to the given list of restrictions. You can also attach additional sorting from the given list of sorts.

```java
    @Override
    public void addFetchRestrictions(AdminUser currentUser, String ceilingEntity, List<Predicate> restrictions, List<Order> sorts,
            Root entityRoot,
            CriteriaQuery criteria,
            CriteriaBuilder criteriaBuilder) {
        for (RowLevelSecurityProvider provider : getProviders()) {
            provider.addFetchRestrictions(currentUser, ceilingEntity, restrictions, sorts, entityRoot, criteria, criteriaBuilder);
        }
    }
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-open-admin-platform/src/main/java/org/broadleafcommerce/openadmin/server/security/service/RowLevelSecurityServiceImpl.java" line="65">

---

## getFetchRestrictionRoot

The `getFetchRestrictionRoot` function is used to get the root class for fetch restrictions. It iterates over the list of providers and returns the root class for the first provider that returns a non-null root class.

```java
    @Override
    public Class<Serializable> getFetchRestrictionRoot(AdminUser currentUser, Class<Serializable> ceilingEntity, List<FilterMapping> filterMappings) {
        Class<Serializable> root = null;
        for (RowLevelSecurityProvider provider : getProviders()) {
            Class<Serializable> providerRoot = provider.getFetchRestrictionRoot(currentUser, ceilingEntity, filterMappings);
            if (providerRoot != null) {
                root = providerRoot;
            }
        }
        
        return root;
    }
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-open-admin-platform/src/main/java/org/broadleafcommerce/openadmin/server/security/service/RowLevelSecurityServiceImpl.java" line="78">

---

## canUpdate

The `canUpdate` function checks if the current user has the permission to update a given entity. It iterates over the list of providers and returns false if any provider returns false for the update permission.

```java
    @Override
    public boolean canUpdate(AdminUser currentUser, Entity entity) {
        for (RowLevelSecurityProvider provider : getProviders()) {
            if (!provider.canUpdate(currentUser, entity)) {
                return false;
            }
        }
        return true;
    }
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-open-admin-platform/src/main/java/org/broadleafcommerce/openadmin/server/security/service/RowLevelSecurityServiceImpl.java" line="88">

---

## getUpdateDenialExceptions

The `getUpdateDenialExceptions` function is used to get the exceptions for update denial. It iterates over the list of providers and collects the exceptions from each provider.

```java
    @Override
    public EntityFormModifierConfiguration getUpdateDenialExceptions() {
        EntityFormModifierConfiguration sum = new EntityFormModifierConfiguration();
        for (RowLevelSecurityProvider provider : getProviders()) {
            if (provider instanceof ExceptionAwareRowLevelSecurityProvider) {
                EntityFormModifierConfiguration response = ((ExceptionAwareRowLevelSecurityProvider) provider).getUpdateDenialExceptions();
                if (response != null) {
                    if (!CollectionUtils.isEmpty(response.getModifier())) {
                        sum.getModifier().addAll(response.getModifier());
                    }
                    if (!CollectionUtils.isEmpty(response.getData())) {
                        sum.getData().addAll(response.getData());
                    }
                }
            }
        }
        return sum;
    }
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-open-admin-platform/src/main/java/org/broadleafcommerce/openadmin/server/security/service/RowLevelSecurityServiceImpl.java" line="117">

---

## canAdd

The `canAdd` function checks if the current user has the permission to add a new entity. It iterates over the list of providers and returns false if any provider returns false for the add permission.

```java
    @Override
    public boolean canAdd(AdminUser currentUser, String sectionClassName, ClassMetadata cmd) {
        for (RowLevelSecurityProvider provider : getProviders()) {
            if (!provider.canAdd(currentUser, sectionClassName, cmd)) {
                return false;
            }
        }
        return true;
    }
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-open-admin-platform/src/main/java/org/broadleafcommerce/openadmin/server/security/service/RowLevelSecurityServiceImpl.java" line="140">

---

## validateRemoveRequest

The `validateRemoveRequest` function validates a remove request. It iterates over the list of providers and validates the remove request with each provider. If any provider returns a validation error, it adds the error message to the global validation result.

```java
    @Override
    public GlobalValidationResult validateRemoveRequest(AdminUser currentUser, Entity entity, PersistencePackage persistencePackage) {
        GlobalValidationResult validationResult = new GlobalValidationResult(true);
        for (RowLevelSecurityProvider provider : getProviders()) {
            GlobalValidationResult providerValidation = provider.validateRemoveRequest(currentUser, entity, persistencePackage);
            if (providerValidation.isNotValid()) {
                validationResult.setValid(false);
                validationResult.addErrorMessage(providerValidation.getErrorMessage());
            }
        }
        return validationResult;
    }
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-open-admin-platform/src/main/java/org/broadleafcommerce/openadmin/server/security/service/RowLevelSecurityServiceImpl.java" line="153">

---

## validateAddRequest

The `validateAddRequest` function validates an add request. It iterates over the list of providers and validates the add request with each provider. If any provider returns a validation error, it adds the error message to the global validation result.

```java
    @Override
    public GlobalValidationResult validateAddRequest(AdminUser currentUser, Entity entity, PersistencePackage persistencePackage) {
        GlobalValidationResult validationResult = new GlobalValidationResult(true);
        for (RowLevelSecurityProvider provider : getProviders()) {
            GlobalValidationResult providerValidation = provider.validateAddRequest(currentUser, entity,
                    persistencePackage);
            if (providerValidation.isNotValid()) {
                validationResult.setValid(false);
                validationResult.addErrorMessage(providerValidation.getErrorMessage());
            }
        }
        return validationResult;
    }
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
