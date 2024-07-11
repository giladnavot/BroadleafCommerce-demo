---
title: Overview of Offer Processing Service
---
The Processor in the Service layer of BroadleafCommerce-demo refers to a set of interfaces and classes that handle the processing of offers in the e-commerce framework. These processors are part of the offer service, which is responsible for managing and applying offers to orders.

The processors are organized in a package named 'org.broadleafcommerce.core.offer.service.processor'. This package contains different types of processors such as 'ItemOfferProcessor', 'OrderOfferProcessor', and 'BaseProcessor'. Each processor has a specific role in the offer processing flow.

For instance, 'ItemOfferProcessor' is responsible for filtering and applying item level offers. It reviews an item level offer against the list of discountable items from the order and if the offer applies, it adds it to the qualifiedItemOffers list.

On the other hand, 'OrderOfferProcessor' is responsible for filtering and applying order level offers. It checks if an offer could be applied to an order based on the restrictions (stackable and/or combinable) on that offer.

The 'BaseProcessor' interface is a common interface that both 'ItemOfferProcessor' and 'OrderOfferProcessor' extend. It provides a method 'filterOffers' that filters offers for a customer.

These processors are used throughout the codebase, particularly in the 'OfferService' and 'OfferServiceImpl' classes, where they are injected as resources and used to process offers.

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/offer/service/processor/BaseProcessor.java" line="30">

---

# BaseProcessor Interface

The `BaseProcessor` interface provides a common method `filterOffers` that filters offers for a customer. This interface is extended by other specific processors.

```java
public interface BaseProcessor {
    
    public List<Offer> filterOffers(List<Offer> offers, Customer customer);
    
}
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/offer/service/processor/ItemOfferProcessor.java" line="32">

---

# ItemOfferProcessor Interface

The `ItemOfferProcessor` interface extends `OrderOfferProcessor`. It is responsible for filtering and applying item level offers. It reviews an item level offer against the list of discountable items from the order and if the offer applies, it adds it to the qualifiedItemOffers list.

```java
public interface ItemOfferProcessor extends OrderOfferProcessor {

    /**
     * Review an item level offer against the list of discountable items from the order. If the
     * offer applies, add it to the qualifiedItemOffers list.
     * 
     * @param order the BLC order
     * @param qualifiedItemOffers the container list for any qualified offers
     * @param discreteOrderItems the order items to evaluate
     * @param offer the offer in question
     */
    public void filterItemLevelOffer(PromotableOrder order, List<PromotableCandidateItemOffer> qualifiedItemOffers, Offer offer);

    /**
     * Private method that takes a list of sorted CandidateItemOffers and determines if each offer can be
     * applied based on the restrictions (stackable and/or combinable) on that offer.  OrderItemAdjustments
     * are create on the OrderItem for each applied CandidateItemOffer.  An offer with stackable equals false
     * cannot be applied to an OrderItem that already contains an OrderItemAdjustment.  An offer with combinable
     * equals false cannot be applied to an OrderItem if that OrderItem already contains an
     * OrderItemAdjustment, unless the offer is the same offer as the OrderItemAdjustment offer.
     *
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/offer/service/processor/OrderOfferProcessor.java" line="35">

---

# OrderOfferProcessor Interface

The `OrderOfferProcessor` interface extends `BaseProcessor`. It is responsible for filtering and applying order level offers. It checks if an offer could be applied to an order based on the restrictions (stackable and/or combinable) on that offer.

```java
public interface OrderOfferProcessor extends BaseProcessor {

    public void filterOrderLevelOffer(PromotableOrder promotableOrder, List<PromotableCandidateOrderOffer> qualifiedOrderOffers, Offer offer);
    
    public Boolean executeExpression(String expression, Map<String, Object> vars);
    
    /**
     * Executes the appliesToOrderRules in the Offer to determine if this offer
     * can be applied to the Order, OrderItem, or FulfillmentGroup.
     *
     * @param offer
     * @param order
     * @return true if offer can be applied, otherwise false
     */
    public boolean couldOfferApplyToOrder(Offer offer, PromotableOrder promotableOrder);
    
    public List<PromotableCandidateOrderOffer> removeTrailingNotCombinableOrderOffers(List<PromotableCandidateOrderOffer> candidateOffers);
    
    /**
     * Takes a list of sorted CandidateOrderOffers and determines if each offer can be
     * applied based on the restrictions (stackable and/or combinable) on that offer.  OrderAdjustments
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/offer/service/OfferService.java" line="167">

---

# Usage of Processors in OfferService

The `OrderOfferProcessor` and `ItemOfferProcessor` are used in the `OfferService` interface. They are declared as methods that return the respective processor, allowing the implementation of this interface to provide the actual processors.

```java
    OrderOfferProcessor getOrderOfferProcessor();

    void setOrderOfferProcessor(OrderOfferProcessor orderOfferProcessor);

    ItemOfferProcessor getItemOfferProcessor();

    void setItemOfferProcessor(ItemOfferProcessor itemOfferProcessor);
```

---

</SwmSnippet>

# Processor Functions

The processors are organized in a package named 'org.broadleafcommerce.core.offer.service.processor'. This package contains different types of processors such as 'ItemOfferProcessor', 'OrderOfferProcessor', and 'BaseProcessor'. Each processor has a specific role in the offer processing flow.

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/offer/service/processor/ItemOfferProcessorImpl.java" line="79">

---

## filterItemLevelOffer

The `filterItemLevelOffer` function is responsible for reviewing an item level offer against the list of discountable items from the order. If the offer applies, it adds it to the qualifiedItemOffers list.

```java
    @Override
    public void filterItemLevelOffer(PromotableOrder order, List<PromotableCandidateItemOffer> qualifiedItemOffers, Offer offer) {
        boolean isNewFormat = CollectionUtils.isNotEmpty(offer.getQualifyingItemCriteriaXref()) ||
                CollectionUtils.isNotEmpty(offer.getTargetItemCriteriaXref());
        boolean itemLevelQualification = false;
        boolean offerCreated = false;

        for (PromotableOrderItem promotableOrderItem : order.getDiscountableOrderItems()) {
            if(couldOfferApplyToOrder(offer, order, promotableOrderItem)) {
                if (!isNewFormat) {
                    //support legacy offers                   
                    PromotableCandidateItemOffer candidate = createCandidateItemOffer(qualifiedItemOffers, offer, order);
                   
                    if (!candidate.getLegacyCandidateTargets().contains(promotableOrderItem)) {
                        candidate.getLegacyCandidateTargets().add(promotableOrderItem);
                    }
                    offerCreated = true;
                    continue;
                }
                itemLevelQualification = true;
                break;
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/offer/service/processor/OrderOfferProcessorImpl.java" line="89">

---

## filterOrderLevelOffer

The `filterOrderLevelOffer` function checks if an offer could be applied to an order based on the restrictions (stackable and/or combinable) on that offer.

```java
    @Override
    public void filterOrderLevelOffer(PromotableOrder promotableOrder, List<PromotableCandidateOrderOffer> qualifiedOrderOffers, Offer offer) {
        if (offer.getDiscountType().getType().equals(OfferDiscountType.FIX_PRICE.getType())) {
            LOG.warn("Offers of type ORDER may not have a discount type of FIX_PRICE. Ignoring order offer (name=" + offer.getName() + ")");
            return;
        }
        boolean orderLevelQualification = false;
        //Order Qualification
        orderQualification:
        {
            if (couldOfferApplyToOrder(offer, promotableOrder)) {
                orderLevelQualification = true;
                break orderQualification;
            }
            for (PromotableOrderItem orderItem : promotableOrder.getDiscountableOrderItems(offer.getApplyDiscountToSalePrice())) {
                if (couldOfferApplyToOrder(offer, promotableOrder, orderItem)) {
                    orderLevelQualification = true;
                    break orderQualification;
                }
            }
            for (PromotableFulfillmentGroup fulfillmentGroup : promotableOrder.getFulfillmentGroups()) {
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/offer/service/processor/OrderOfferProcessorImpl.java" line="99">

---

## couldOfferApplyToOrder

The `couldOfferApplyToOrder` function is used within `filterOrderLevelOffer` to check if an offer could apply to an order.

```java
            if (couldOfferApplyToOrder(offer, promotableOrder)) {
                orderLevelQualification = true;
                break orderQualification;
            }
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/offer/service/processor/AbstractBaseProcessor.java" line="86">

---

## couldOfferApplyToOrderItems

The `couldOfferApplyToOrderItems` function is used within `filterItemLevelOffer` to check if an offer could apply to order items.

```java
    protected CandidatePromotionItems couldOfferApplyToOrderItems(Offer offer, List<PromotableOrderItem> promotableOrderItems) {
        CandidatePromotionItems candidates = new CandidatePromotionItems();
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
