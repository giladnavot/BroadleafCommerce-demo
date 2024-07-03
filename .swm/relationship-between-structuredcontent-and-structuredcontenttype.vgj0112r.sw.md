---
title: Relationship between StructuredContent and StructuredContentType
---
This document will cover the relationship between StructuredContent and StructuredContentType in the BroadleafCommerce-demo repository. We'll cover:

1. What is StructuredContent and StructuredContentType
2. How StructuredContent and StructuredContentType are related
3. How StructuredContent and StructuredContentType are used in the codebase.

# What is StructuredContent and StructuredContentType

StructuredContent and StructuredContentType are key components in the BroadleafCommerce-demo repository. StructuredContent represents a generic content item with a set of predefined fields. The fields associated with an instance of StructuredContent are defined by its associated StructuredContentType. StructuredContentType defines the type of the StructuredContent.

# How StructuredContent and StructuredContentType are related

StructuredContent has a field called `structuredContentType` which is of type `StructuredContentType`. This field represents the type of the content. There are methods in StructuredContent to get and set this field, namely `getStructuredContentType()` and `setStructuredContentType()`. These methods are used to associate a StructuredContentType with a StructuredContent.

# How StructuredContent and StructuredContentType are used in the codebase

StructuredContent and StructuredContentType are used throughout the codebase. For example, in `StructuredContentService.java` and `StructuredContentDao.java`, there are methods to save a StructuredContentType. In `StructuredContentImpl.java`, the `setStructuredContentType()` method is used to set the StructuredContentType of a StructuredContent. In `StructuredContentItemCriteria.java`, the `getStructuredContent()` method is used to get the StructuredContent to which a field belongs.

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="follow-up"><sup>Powered by [Swimm](/)</sup></SwmMeta>
