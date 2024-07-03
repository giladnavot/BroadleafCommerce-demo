---
title: >-
  Handling HTTP Requests in Broadleaf Admin Module: Deep Dive into Entity
  Controllers
---
This document will cover how controllers in the `org.broadleafcommerce.admin.web.controller.entity` package handle HTTP requests to entities. We'll cover:

1. The role of the `AdminBasicEntityController` class.
2. How the `resolveAppropriateEntityView` method works.
3. The function of extension handlers in the process.

<SwmSnippet path="/admin/broadleaf-open-admin-platform/src/main/java/org/broadleafcommerce/openadmin/web/controller/entity/AdminBasicEntityController.java" line="18">

---

# AdminBasicEntityController

`AdminBasicEntityController` is a central class in the `org.broadleafcommerce.admin.web.controller.entity` package. It imports various utilities and DTOs from the Broadleaf framework, which are used to handle HTTP requests to entities.

```java
package org.broadleafcommerce.openadmin.web.controller.entity;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.broadleafcommerce.common.exception.SecurityServiceException;
import org.broadleafcommerce.common.exception.ServiceException;
import org.broadleafcommerce.common.extension.ExtensionResultHolder;
import org.broadleafcommerce.common.extension.ExtensionResultStatusType;
import org.broadleafcommerce.common.persistence.EntityDuplicator;
import org.broadleafcommerce.common.presentation.client.AddMethodType;
import org.broadleafcommerce.common.presentation.client.SupportedFieldType;
import org.broadleafcommerce.common.sandbox.SandBoxHelper;
import org.broadleafcommerce.common.service.GenericEntityService;
import org.broadleafcommerce.common.util.BLCArrayUtils;
import org.broadleafcommerce.common.util.BLCMessageUtils;
import org.broadleafcommerce.common.web.BroadleafRequestContext;
import org.broadleafcommerce.common.web.JsonResponse;
import org.broadleafcommerce.openadmin.dto.AdornedTargetCollectionMetadata;
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-open-admin-platform/src/main/java/org/broadleafcommerce/openadmin/web/controller/entity/AdminBasicEntityController.java" line="726">

---

# resolveAppropriateEntityView Method

The `resolveAppropriateEntityView` method is used to determine the appropriate view for an entity based on the request type. If the request is an AJAX request, the entity form is set to read-only and the view type is set to 'modal/entityView'. Otherwise, the view type is set to 'entityEdit'.

```java
    protected String resolveAppropriateEntityView(final HttpServletRequest request,
            final Model model,
            final @ModelAttribute(value = "entityForm") EntityForm entityForm) {
        if (isAjaxRequest(request)) {
            entityForm.setReadOnly();
            model.addAttribute("viewType", "modal/entityView");
            model.addAttribute("modalHeaderType", ModalHeaderType.VIEW_ENTITY.getType());
            return MODAL_CONTAINER_VIEW;
        } else {
            model.addAttribute("useAjaxUpdate", true);
            model.addAttribute("viewType", "entityEdit");
            return DEFAULT_CONTAINER_VIEW;
        }
    }
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-admin-module/src/main/java/org/broadleafcommerce/admin/web/controller/extension/TypedEntityBasicEntityExtensionHandler.java" line="18">

---

# Extension Handlers

Extension handlers like `TypedEntityBasicEntityExtensionHandler` provide additional functionality to the controllers. They can be used to modify the behavior of the controllers based on specific conditions.

```java
package org.broadleafcommerce.admin.web.controller.extension;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.broadleafcommerce.common.admin.domain.TypedEntity;
import org.broadleafcommerce.common.dao.GenericEntityDao;
import org.broadleafcommerce.common.extension.ExtensionResultStatusType;
import org.broadleafcommerce.openadmin.dto.ClassMetadata;
import org.broadleafcommerce.openadmin.server.dao.DynamicEntityDao;
import org.broadleafcommerce.openadmin.server.service.persistence.PersistenceManagerFactory;
import org.broadleafcommerce.openadmin.web.controller.AbstractAdminAbstractControllerExtensionHandler;
import org.broadleafcommerce.openadmin.web.controller.AdminAbstractControllerExtensionManager;
import org.broadleafcommerce.openadmin.web.form.entity.EntityForm;
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="follow-up"><sup>Powered by [Swimm](/)</sup></SwmMeta>
