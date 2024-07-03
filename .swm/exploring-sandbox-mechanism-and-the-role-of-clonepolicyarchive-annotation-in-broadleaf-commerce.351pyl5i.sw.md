---
title: >-
  Exploring Sandbox Mechanism and the Role of ClonePolicyArchive Annotation in
  Broadleaf Commerce
---
This document will cover the role of sandboxing in Broadleaf Commerce and how 'ClonePolicyArchive' contributes to it. We'll cover:

1. What is sandboxing in Broadleaf Commerce
2. The role of 'ClonePolicyArchive' in sandboxing

# Sandboxing in Broadleaf Commerce

Sandboxing in Broadleaf Commerce is a feature that allows developers to create, test, and refine changes in an isolated environment, or 'sandbox', before merging them into the production environment. This is facilitated by the `SandBox` domain and `SandBoxType` which are imported in various parts of the codebase. The `SandBoxHelper` interface and its implementation `DefaultSandBoxHelper` provide methods to interact with the sandbox, such as retrieving sandbox version IDs, merging clone IDs, and checking if an entity is related to parent catalog IDs.

# Role of 'ClonePolicyArchive' in Sandboxing

The 'ClonePolicyArchive' is a key component in the sandboxing process, although it is not directly mentioned in the provided context. It typically handles the creation of clones of entities that are modified within the sandbox. These clones are then used to track changes without affecting the original entities in the production environment. When the changes are ready to be merged into production, 'ClonePolicyArchive' helps in reconciling the changes and ensuring a smooth transition.

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="follow-up"><sup>Powered by [Swimm](/)</sup></SwmMeta>
