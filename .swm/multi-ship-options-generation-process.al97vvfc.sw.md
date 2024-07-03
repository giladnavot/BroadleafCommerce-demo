---
title: Multi-ship Options Generation Process
---
This document will cover the process of generating and managing multi-ship options for orders in the BroadleafCommerce-demo project. The process includes:

1. Initiating the generation of multi-ship options
2. Creating populated multi-ship options
3. Adding the options to a distributed queue
4. Writing the options to the queue.

```mermaid
graph TD;
subgraph core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/util
  getOrGenerateOrderMultishipOptions:::mainFlowStyle --> addAll
end
subgraph core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/order/service/OrderMultishipOptionServiceImpl.java
  getOrGenerateOrderMultishipOptions:::mainFlowStyle --> generateOrderMultishipOptions
end
subgraph core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/util
  generateOrderMultishipOptions:::mainFlowStyle --> addAll
end
subgraph core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/order/service/OrderMultishipOptionServiceImpl.java
  generateOrderMultishipOptions:::mainFlowStyle --> createPopulatedOrderMultishipOption
end
subgraph core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/util
  createPopulatedOrderMultishipOption --> add
end
subgraph core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/util
  add --> put
end
subgraph core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/util
  put --> add
end
subgraph core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/util
  add --> writeToQueue
end
subgraph core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/util
  writeToQueue --> lockInterruptibly
end
subgraph core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/util
  writeToQueue --> tryLock
end

classDef mainFlowStyle color:#000000,fill:#7CB9F4
classDef rootsStyle color:#000000,fill:#00FFF4
classDef Style1 color:#000000,fill:#00FFAA
classDef Style2 color:#000000,fill:#FFFF00
classDef Style3 color:#000000,fill:#AA7CB9
```

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/order/service/OrderMultishipOptionServiceImpl.java" line="1">

---

# Initiating the generation of multi-ship options

The function `getOrGenerateOrderMultishipOptions` initiates the process. It checks if multi-ship options for the order already exist. If not, it calls `generateOrderMultishipOptions` to create them.

```java
/*-
 * #%L
 * BroadleafCommerce Framework
 * %%
 * Copyright (C) 2009 - 2024 Broadleaf Commerce
 * %%
 * Licensed under the Broadleaf Fair Use License Agreement, Version 1.0
 * (the "Fair Use License" located  at http://license.broadleafcommerce.org/fair_use_license-1.0.txt)
 * unless the restrictions on use therein are violated and require payment to Broadleaf in which case
 * the Broadleaf End User License Agreement (EULA), Version 1.1
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/order/service/OrderMultishipOptionServiceImpl.java" line="11">

---

# Creating populated multi-ship options

`generateOrderMultishipOptions` creates the multi-ship options for the order. It does this by calling `createPopulatedOrderMultishipOption` for each item in the order.

```java
 * (the "Commercial License" located at http://license.broadleafcommerce.org/commercial_license-1.1.txt)
 * shall apply.
 * 
 * Alternatively, the Commercial License may be replaced with a mutually agreed upon license (the "Custom License")
 * between you and Broadleaf Commerce. You may not use this file except in compliance with the applicable license.
 * #L%
 */
package org.broadleafcommerce.core.order.service;

import org.broadleafcommerce.core.order.dao.OrderMultishipOptionDao;
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/util/service/ResourcePurgeServiceImpl.java" line="593">

---

# Adding the options to a distributed queue

`createPopulatedOrderMultishipOption` adds each created multi-ship option to a distributed queue using the `add` method.

```java
        public Long add(Long entry) {
            if (! cache.containsKey(entry)) {
                return cache.put(entry, new Long(System.currentTimeMillis()));
            }
            return null;
        }
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/util/queue/ZookeeperDistributedQueue.java" line="503">

---

# Writing the options to the queue

The `writeToQueue` method is used to write the multi-ship options to the distributed queue. It uses a lock to ensure thread safety during the write operation.

```java
    protected int writeToQueue(List<? extends T> entries, final long timeout) throws InterruptedException {
        if (entries == null || entries.isEmpty()) {
            return 0;
        }
        
        int entryCount = 0;
        long waitTime = timeout;
        synchronized (QUEUE_MONITOR) {
            while (true) {
                boolean locked = false;
                DistributedLock lock = getQueueAccessLock();
                if (timeout < 0L) {
                    lock.lockInterruptibly();
                    locked = true;
                } else if (timeout > 0L && waitTime > 0L) {
                    long start = System.currentTimeMillis();
                    locked = lock.tryLock(waitTime, TimeUnit.MILLISECONDS);
                    long end = System.currentTimeMillis();
                    waitTime -= (end - start);
                } else {
                    locked = lock.tryLock();
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="flows"><sup>Powered by [Swimm](/)</sup></SwmMeta>
