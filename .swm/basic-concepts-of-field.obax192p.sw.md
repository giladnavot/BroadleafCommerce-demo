---
title: Basic Concepts of Field
---
In the BroadleafCommerce-demo repository, a Field represents a String-based mapping of entities and properties. It is used in various places, including search facets and report fields. Each Field has an ID, a friendly name for use by the admin or other UI, an entityType, a propertyName, an abbreviation, and a list of searchConfigs. It also has a qualifiedFieldName, which is the entityType joined with the propertyName by a '.'. A Field can also be considered translatable.

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/search/domain/Field.java" line="31">

---

# Field Interface

This is the Field interface. It declares methods for getting and setting the ID, friendly name, entity type, property name, abbreviation, and translatable status of a field. It also declares a method for getting the qualified field name, which is the entity type joined with the property name by a '.'.

```java
public interface Field extends Serializable, MultiTenantCloneable<Field> {
    
    /**
     * Gets the id
     * @return the id
     */
    public Long getId();

    /**
     * Sets the id
     * @param id 
     */
    public void setId(Long id);

    /**
     * The friendly name of the field, for use by admin or other UI.
     * 
     * @param friendlyName
     */
    public void setFriendlyName(String friendlyName);

```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/search/service/DatabaseSearchServiceImpl.java" line="160">

---

# Usage of Field Interface

Here is an example of how the Field interface is used. In the DatabaseSearchServiceImpl class, the Field interface is used to represent the properties of entities that are being searched. The readFieldByAbbreviation method of the fieldDao object is used to get a Field object for a given abbreviation. The getDatabaseQualifiedFieldName method is then used to get the qualified field name for the Field object.

```java
        for (Entry<String, String[]> entry : criteria.getFilterCriteria().entrySet()) {
            Field field = fieldDao.readFieldByAbbreviation(entry.getKey());
            if (field != null) {
                String qualifiedFieldName = getDatabaseQualifiedFieldName(field.getQualifiedFieldName());
                convertedFilterCriteria.put(qualifiedFieldName, entry.getValue());
            }
        }
        criteria.setFilterCriteria(convertedFilterCriteria);
        
        // Convert the sort criteria url keys
        if (StringUtils.isNotBlank(criteria.getSortQuery())) {
            StringBuilder convertedSortQuery = new StringBuilder();
            for (String sortQuery : criteria.getSortQuery().split(",")) {
                String[] sort = sortQuery.split(" ");
                if (sort.length == 2) {
                    String key = sort[0];
                    Field field = fieldDao.readFieldByAbbreviation(key);
                    String qualifiedFieldName = getDatabaseQualifiedFieldName(field.getQualifiedFieldName());
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
