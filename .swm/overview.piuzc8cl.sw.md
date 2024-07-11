---
title: Overview
---
The BroadleafCommerce-demo repository contains the Broadleaf Commerce Community Edition (CE), an e-commerce framework written in Java and leveraging the Spring framework. It is designed to facilitate the development of enterprise-class, commerce-driven sites by providing a robust data model, services, and specialized tooling. The repository provides three editions of Broadleaf: Community Edition (CE), Enterprise Edition (EE), and Microservices Edition. Each edition has its own licensing terms and features.

## Main Components

### Core

Core refers to the central part of the BroadleafCommerce-demo application that contains the main business logic and functionalities. It is organized into different modules and includes classes and interfaces that are used across the application.

- <SwmLink doc-title="Core module overview">[Core module overview](.swm/core-module-overview.ugtybbse.sw.md)</SwmLink>
- <SwmLink doc-title="What is the store entity in core">[What is the store entity in core](.swm/what-is-the-store-entity-in-core.vy9ehcw6.sw.md)</SwmLink>
- <SwmLink doc-title="Understanding inventory management">[Understanding inventory management](.swm/understanding-inventory-management.d4o9cggo.sw.md)</SwmLink>
- <SwmLink doc-title="Getting started with product rating system">[Getting started with product rating system](.swm/getting-started-with-product-rating-system.kspvdr8v.sw.md)</SwmLink>
- **Pricing**
  - <SwmLink doc-title="Exploring the pricing mechanism">[Exploring the pricing mechanism](.swm/exploring-the-pricing-mechanism.ij0uw3jb.sw.md)</SwmLink>
  - <SwmLink doc-title="Exception handling strategies in the pricing component">[Exception handling strategies in the pricing component](.swm/exception-handling-strategies-in-the-pricing-component.jkiua2x2.sw.md)</SwmLink>
  - <SwmLink doc-title="Understanding pricing workflow">[Understanding pricing workflow](.swm/understanding-pricing-workflow.e9wdev1e.sw.md)</SwmLink>
- **Checkout**
  - <SwmLink doc-title="What is the checkout process">[What is the checkout process](.swm/what-is-the-checkout-process.di6ulljk.sw.md)</SwmLink>
  - <SwmLink doc-title="Exploring the checkout workflow">[Exploring the checkout workflow](.swm/exploring-the-checkout-workflow.o6h4fnya.sw.md)</SwmLink>
- **Catalog**
  - <SwmLink doc-title="Understanding the product catalog">[Understanding the product catalog](.swm/understanding-the-product-catalog.x7w0qho4.sw.md)</SwmLink>
  - <SwmLink doc-title="Understanding catalog service in catalog">[Understanding catalog service in catalog](.swm/understanding-catalog-service-in-catalog.aosbszpw.sw.md)</SwmLink>
  - <SwmLink doc-title="Overview of data access objects in catalog">[Overview of data access objects in catalog](.swm/overview-of-data-access-objects-in-catalog.exluyfoh.sw.md)</SwmLink>
  - <SwmLink doc-title="Sku margin calculation process">[Sku margin calculation process](.swm/sku-margin-calculation-process.cl79umhp.sw.md)</SwmLink>
  - **Domain**
    - <SwmLink doc-title="Overview of catalog domain">[Overview of catalog domain](.swm/overview-of-catalog-domain.9wis4xnv.sw.md)</SwmLink>
    - <SwmLink doc-title="Sku permutations process">[Sku permutations process](.swm/sku-permutations-process.nn0y7c1w.sw.md)</SwmLink>
- **Payment**
  - <SwmLink doc-title="Getting started with payment processing">[Getting started with payment processing](.swm/getting-started-with-payment-processing.i3lizkn9.sw.md)</SwmLink>
  - <SwmLink doc-title="Exploring the payment domain">[Exploring the payment domain](.swm/exploring-the-payment-domain.d3r2gbav.sw.md)</SwmLink>
  - <SwmLink doc-title="Overview of payment service">[Overview of payment service](.swm/overview-of-payment-service.zbuwzplt.sw.md)</SwmLink>
- **Offer**
  - <SwmLink doc-title="Exploring offer management">[Exploring offer management](.swm/exploring-offer-management.ts21wi6u.sw.md)</SwmLink>
  - <SwmLink doc-title="Exploring the offer domain">[Exploring the offer domain](.swm/exploring-the-offer-domain.xoqoqklj.sw.md)</SwmLink>
  - <SwmLink doc-title="What is offer data access object">[What is offer data access object](.swm/what-is-offer-data-access-object.u3gfaezu.sw.md)</SwmLink>
  - **Service**
    - <SwmLink doc-title="Understanding offer service">[Understanding offer service](.swm/understanding-offer-service.8da75j3n.sw.md)</SwmLink>
    - <SwmLink doc-title="Exploring the role of offerservice in the order processing workflow">[Exploring the role of offerservice in the order processing workflow](.swm/exploring-the-role-of-offerservice-in-the-order-processing-workflow.a5strp0y.sw.md)</SwmLink>
    - <SwmLink doc-title="Exploring offer service types">[Exploring offer service types](.swm/exploring-offer-service-types.m9kmb5sl.sw.md)</SwmLink>
    - <SwmLink doc-title="What is promotion discount">[What is promotion discount](.swm/what-is-promotion-discount.913lcxag.sw.md)</SwmLink>
    - **Processor**
      - <SwmLink doc-title="Overview of offer processing service">[Overview of offer processing service](.swm/overview-of-offer-processing-service.qvu2ehdj.sw.md)</SwmLink>
      - <SwmLink doc-title="Impact of offer application on final order price calculation">[Impact of offer application on final order price calculation](.swm/impact-of-offer-application-on-final-order-price-calculation.ziauhdw9.sw.md)</SwmLink>
      - **Flows**
        - <SwmLink doc-title="Applying and saving fulfillment group offers to an order">[Applying and saving fulfillment group offers to an order](.swm/applying-and-saving-fulfillment-group-offers-to-an-order.av8bjsg1.sw.md)</SwmLink>
        - <SwmLink doc-title="Offer filtering process">[Offer filtering process](.swm/offer-filtering-process.pcapn8ol.sw.md)</SwmLink>
- **Order**
  - <SwmLink doc-title="Exploring order management">[Exploring order management](.swm/exploring-order-management.25rp5fv3.sw.md)</SwmLink>
  - <SwmLink doc-title="Handling order pricing taxes and discounts in broadleaf commerce">[Handling order pricing taxes and discounts in broadleaf commerce](.swm/handling-order-pricing-taxes-and-discounts-in-broadleaf-commerce.yrc8bdn3.sw.md)</SwmLink>
  - <SwmLink doc-title="Introduction to order domain">[Introduction to order domain](.swm/introduction-to-order-domain.pui85tb5.sw.md)</SwmLink>
  - **Service**
    - <SwmLink doc-title="Overview of order service">[Overview of order service](.swm/overview-of-order-service.lnir0hp1.sw.md)</SwmLink>
    - <SwmLink doc-title="Introduction to service calls">[Introduction to service calls](.swm/introduction-to-service-calls.jdzve8xn.sw.md)</SwmLink>
    - <SwmLink doc-title="Understanding order processing workflow">[Understanding order processing workflow](.swm/understanding-order-processing-workflow.r43qdxaq.sw.md)</SwmLink>
    - <SwmLink doc-title="Order item addition process">[Order item addition process](.swm/order-item-addition-process.4wuho1qt.sw.md)</SwmLink>
  - **Dao**
    - <SwmLink doc-title="Exploring the order data access object orderdao">[Exploring the order data access object orderdao](.swm/exploring-the-order-data-access-object-orderdao.z2t2wtzh.sw.md)</SwmLink>
    - <SwmLink doc-title="Usage of java persistence api in broadleaf commerce">[Usage of java persistence api in broadleaf commerce](.swm/usage-of-java-persistence-api-in-broadleaf-commerce.7z7r7mnn.sw.md)</SwmLink>
    - <SwmLink doc-title="Optimisation techniques used in broadleaf commerce for database querying">[Optimisation techniques used in broadleaf commerce for database querying](.swm/optimisation-techniques-used-in-broadleaf-commerce-for-database-querying.r23nxhdx.sw.md)</SwmLink>
- **Flows**
  - <SwmLink doc-title="Zookeeper distributed queue operations">[Zookeeper distributed queue operations](.swm/zookeeper-distributed-queue-operations.71cswygb.sw.md)</SwmLink>
  - <SwmLink doc-title="Zookeeper distributed queue operations">[Zookeeper distributed queue operations](.swm/zookeeper-distributed-queue-operations.wag7yu6g.sw.md)</SwmLink>
  - <SwmLink doc-title="Persistence management flow">[Persistence management flow](.swm/persistence-management-flow.i8k9xrbj.sw.md)</SwmLink>
  - <SwmLink doc-title="Distributed queue management">[Distributed queue management](.swm/distributed-queue-management.re78b7sr.sw.md)</SwmLink>
  - <SwmLink doc-title="Process of checking for inconsistent permutations">[Process of checking for inconsistent permutations](.swm/process-of-checking-for-inconsistent-permutations.4dyjf554.sw.md)</SwmLink>
  - <SwmLink doc-title="Solr server reindexing process">[Solr server reindexing process](.swm/solr-server-reindexing-process.04hxl70r.sw.md)</SwmLink>
  - <SwmLink doc-title="Solr index rebuilding process">[Solr index rebuilding process](.swm/solr-index-rebuilding-process.uujz0cxk.sw.md)</SwmLink>
  - <SwmLink doc-title="Solr index update process">[Solr index update process](.swm/solr-index-update-process.hvz57m6z.sw.md)</SwmLink>
  - <SwmLink doc-title="Pricing fulfillment items process">[Pricing fulfillment items process](.swm/pricing-fulfillment-items-process.xil87vsm.sw.md)</SwmLink>
  - <SwmLink doc-title="One page checkout process">[One page checkout process](.swm/one-page-checkout-process.uqiz54ll.sw.md)</SwmLink>
  - <SwmLink doc-title="Purge order history process">[Purge order history process](.swm/purge-order-history-process.mujuewl4.sw.md)</SwmLink>
- **Build tools**
  - <SwmLink doc-title="Maven configuration in corebroadleaf profile web">[Maven configuration in corebroadleaf profile web](.swm/maven-configuration-in-corebroadleaf-profile-web.ml3owd74.sw.md)</SwmLink>
  - <SwmLink doc-title="Maven usage in the core module">[Maven usage in the core module](.swm/maven-usage-in-the-core-module.y7xv4yed.sw.md)</SwmLink>
  - <SwmLink doc-title="Maven configuration in corebroadleaf framework web">[Maven configuration in corebroadleaf framework web](.swm/maven-configuration-in-corebroadleaf-framework-web.kcjzw5wk.sw.md)</SwmLink>
  - <SwmLink doc-title="Maven configuration in corebroadleaf profile">[Maven configuration in corebroadleaf profile](.swm/maven-configuration-in-corebroadleaf-profile.r3g28u2j.sw.md)</SwmLink>
  - <SwmLink doc-title="Maven configuration in corebroadleaf framework">[Maven configuration in corebroadleaf framework](.swm/maven-configuration-in-corebroadleaf-framework.ytgudjgy.sw.md)</SwmLink>

### Flows

- <SwmLink doc-title="Page retrieval by uri process">[Page retrieval by uri process](.swm/page-retrieval-by-uri-process.i2z65ioe.sw.md)</SwmLink>
- <SwmLink doc-title="Populating model variables process">[Populating model variables process](.swm/populating-model-variables-process.u5n3oz3q.sw.md)</SwmLink>
- <SwmLink doc-title="Admin request handling">[Admin request handling](.swm/admin-request-handling.bq23bnyr.sw.md)</SwmLink>
- <SwmLink doc-title="Building properties from polymorphic entities">[Building properties from polymorphic entities](.swm/building-properties-from-polymorphic-entities.c57arbws.sw.md)</SwmLink>
- <SwmLink doc-title="Field validation in broadleaf commerce">[Field validation in broadleaf commerce](.swm/field-validation-in-broadleaf-commerce.hb3ij8bv.sw.md)</SwmLink>

## Classes

- <SwmLink doc-title="The abstractextensionhandler class">[The abstractextensionhandler class](.swm/the-abstractextensionhandler-class.bxmuo.sw.md)</SwmLink>
- <SwmLink doc-title="The multitenantcloneable class">[The multitenantcloneable class](.swm/the-multitenantcloneable-class.bz6xo.sw.md)</SwmLink>
- <SwmLink doc-title="The broadleafabstractcontroller class">[The broadleafabstractcontroller class](.swm/the-broadleafabstractcontroller-class.l7w5w.sw.md)</SwmLink>
- <SwmLink doc-title="The extensionmanager class">[The extensionmanager class](.swm/the-extensionmanager-class.bclk3.sw.md)</SwmLink>
- <SwmLink doc-title="The custompersistencehandleradapter class">[The custompersistencehandleradapter class](.swm/the-custompersistencehandleradapter-class.qwkzr.sw.md)</SwmLink>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="other"><sup>Powered by [Swimm](/)</sup></SwmMeta>
