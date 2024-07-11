---
title: Core Module Overview
---
The Core in BroadleafCommerce-demo refers to the core module of the Broadleaf Commerce framework. It contains the main functionalities and services that drive the e-commerce operations. The core module is further divided into sub-modules such as 'broadleaf-profile', 'broadleaf-framework', 'broadleaf-profile-web', and 'broadleaf-framework-web'.

Each sub-module within the core has its own responsibilities. For instance, 'broadleaf-profile' handles user profile related operations, while 'broadleaf-framework' contains the main business logic and services for the e-commerce operations.

The 'broadleaf-profile-web' and 'broadleaf-framework-web' are the web counterparts of the 'broadleaf-profile' and 'broadleaf-framework' modules respectively. They handle the web-related aspects of the user profile and the main e-commerce operations.

The core module also includes classes and interfaces that facilitate search operations. For example, the 'SolrConfiguration' class holds the Solr server configuration, and the 'SolrHelperService' interface defines methods for manipulating Solr cores and converting search criteria.

The core module is fundamental to the Broadleaf Commerce framework as it provides the essential services and functionalities required for building commerce-driven sites.

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/search/service/solr/SolrConfiguration.java" line="43">

---

# SolrConfiguration Class

The 'SolrConfiguration' class is part of the Core and it holds the Solr server configuration. It is initialized in 'SolrSearchServiceImpl' and used in 'SolrIndexServiceImpl'.

```java
/**
 * <p>
 * Provides a class that will statically hold the Solr server.
 * 
 * <p>
 * This is initialized in {@link SolrSearchServiceImpl} and used in {@link SolrIndexServiceImpl}
 * 
 * @author Andre Azzolini (apazzolini)
 */
public class SolrConfiguration implements InitializingBean {
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/search/service/solr/SolrConfiguration.java" line="236">

---

# getAdminServer Method

The 'getAdminServer' method is part of the 'SolrConfiguration' class. It returns an admin server if configured, otherwise, it returns the primary server. This method is used for connecting to Solr and swapping cores.

```java
    /**
     * The adminServer is just a reference to a SolrClient component for connecting to Solr.  In newer
     * versions of Solr, 4.4 and beyond, auto discovery of cores is  
     * provided.  When using a stand-alone server or server cluster, 
     * the admin server, for swapping cores, is a different URL. For example, 
     * one needs to use http://solrserver:8983/solr, which formerly acted as the admin server 
     * AND as the primary core.  As of Solr 4.4, one needs to specify the cores separately: 
     * http://solrserver:8983/solr/primary and http://solrserver:8983/solr/reindex, 
     * and use http://solrserver:8983/solr for swapping cores.
     * 
     * By default, this method attempts to return an admin server if configured. Otherwise, 
     * it returns the primary server if the admin server is null, which is backwards compatible 
     * with the way that BLC worked prior to this change.
     * 
     * @return
     */
    public SolrClient getAdminServer() {
        if (adminServer != null) {
            return adminServer;
        }
        //If the admin server hasn't been set, return the primary server.
```

---

</SwmSnippet>

# CustomerStateFilter and CustomerPhoneController

Understanding CustomerStateFilter and CustomerPhoneController

<SwmSnippet path="/core/broadleaf-profile-web/src/main/java/org/broadleafcommerce/profile/web/site/security/CustomerStateFilter.java" line="1">

---

## CustomerStateFilter

The CustomerStateFilter class is a filter that is configured after the RememberMe listener from Spring Security. It retrieves the Broadleaf Customer based on the authenticated user or creates an Anonymous customer and stores them in the session. It also sets certain flags on the Customer object based on the type of authentication token.

```java
/*-
 * #%L
 * BroadleafCommerce Profile Web
 * %%
 * Copyright (C) 2009 - 2024 Broadleaf Commerce
 * %%
 * Licensed under the Broadleaf Fair Use License Agreement, Version 1.0
 * (the "Fair Use License" located  at http://license.broadleafcommerce.org/fair_use_license-1.0.txt)
 * unless the restrictions on use therein are violated and require payment to Broadleaf in which case
 * the Broadleaf End User License Agreement (EULA), Version 1.1
 * (the "Commercial License" located at http://license.broadleafcommerce.org/commercial_license-1.1.txt)
 * shall apply.
 * 
 * Alternatively, the Commercial License may be replaced with a mutually agreed upon license (the "Custom License")
 * between you and Broadleaf Commerce. You may not use this file except in compliance with the applicable license.
 * #L%
 */
package org.broadleafcommerce.profile.web.site.security;

import org.broadleafcommerce.common.util.BLCRequestUtils;
import org.broadleafcommerce.common.web.filter.AbstractIgnorableOncePerRequestFilter;
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-profile-web/src/main/java/org/broadleafcommerce/profile/web/controller/CustomerPhoneController.java" line="42">

---

## CustomerPhoneController

The CustomerPhoneController class provides access and mutator functions to manage a customer's phones. It handles requests mapped to /myaccount/phone and provides methods for deleting a phone, making a phone default, saving a phone, and viewing a phone.

```java
/**
 * Provides access and mutator functions to manage a customer's phones.
 *
 * @author sconlon
 */
@Controller("blCustomerPhoneController")
@RequestMapping("/myaccount/phone")
public class CustomerPhoneController {
    private static final String prefix = "myAccount/phone/customerPhones";
    private static final String redirect = "redirect:/myaccount/phone/viewPhone.htm";

    @Resource(name="blCustomerPhoneService")
    private CustomerPhoneService customerPhoneService;
    @Resource(name="blCustomerPhoneValidator")
    private CustomerPhoneValidator customerPhoneValidator;
    @Resource(name="blCustomerState")
    private CustomerState customerState;
    @Resource(name="blEntityConfiguration")
    private EntityConfiguration entityConfiguration;
    @Resource(name="blPhoneFormatter")
    private PhoneFormatter phoneFormatter;
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
