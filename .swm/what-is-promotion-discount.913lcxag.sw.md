---
title: What is Promotion Discount
---
Promotion Discount is a class in the BroadleafCommerce-demo repository that represents the discount applied to an item as part of a promotion. It is part of the offer service in the Broadleaf Commerce framework, which is responsible for handling promotional offers and discounts within the e-commerce system.

The PromotionDiscount class is used to record the usage of an item as a qualifier or target of a promotion. The discount amount will be 0 if the item was only used as a qualifier. It contains information about the promotion, the item criteria, and the quantity of the item.

PromotionDiscount is used in various parts of the codebase. For example, it is used in the PromotableOrderItemPriceDetail class to keep track of the discounts applied to an order item. It is also used in the OfferServiceUtilities class to mark related qualifiers and targets for item criteria.

The PromotionDiscount class provides methods to get and set the promotion, item criteria, and quantity. It also provides a method to check if the discount is finalized, which means the quantity is equal to the finalized quantity.

The PromotionDiscount class also provides a method to split the discount. This method is used when the quantity of an item is split into multiple parts, each with its own discount. The split method creates a new PromotionDiscount object with the split quantity and adjusts the quantity of the original object.

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/offer/service/discount/PromotionDiscount.java" line="1">

---

# PromotionDiscount Class

The PromotionDiscount class represents a discount applied to an item as part of a promotion. It contains information about the promotion, the item criteria, and the quantity of the item. The class provides methods to get and set these properties, check if the discount is finalized, and split the discount when the quantity of an item is split into multiple parts.

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
 * (the "Commercial License" located at http://license.broadleafcommerce.org/commercial_license-1.1.txt)
 * shall apply.
 * 
 * Alternatively, the Commercial License may be replaced with a mutually agreed upon license (the "Custom License")
 * between you and Broadleaf Commerce. You may not use this file except in compliance with the applicable license.
 * #L%
 */
package org.broadleafcommerce.core.offer.service.discount;

import org.broadleafcommerce.core.offer.domain.Offer;
import org.broadleafcommerce.core.offer.domain.OfferItemCriteria;
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/offer/service/discount/domain/PromotableOrderItemPriceDetailImpl.java" line="402">

---

# Usage of PromotionDiscount

The PromotionDiscount class is used in the PromotableOrderItemPriceDetail class to keep track of the discounts applied to an order item. The `clearAllNonFinalizedQuantities` method uses the `getFinalizedQuantity` method of PromotionDiscount to clear all non-finalized quantities.

```java
            PromotionQualifier promotionQualifier = promotionQualifierIterator.next();
            if (promotionQualifier.getFinalizedQuantity() == 0) {
                // If there are no quantities of this item that are finalized, then remove the item.
                promotionQualifierIterator.remove();
            } else {
                // Otherwise, set the quantity to the number of finalized items.
                promotionQualifier.setQuantity(promotionQualifier.getFinalizedQuantity());
            }
        }

        Iterator<PromotionDiscount> promotionDiscountIterator = promotionDiscounts.iterator();
        while (promotionDiscountIterator.hasNext()) {
            PromotionDiscount promotionDiscount = promotionDiscountIterator.next();
            if (promotionDiscount.getFinalizedQuantity() == 0) {
                // If there are no quantities of this item that are finalized, then remove the item.
                promotionDiscountIterator.remove();
            } else {
                // Otherwise, set the quantity to the number of finalized items.
                promotionDiscount.setQuantity(promotionDiscount.getFinalizedQuantity());
            }
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/offer/service/OfferServiceUtilitiesImpl.java" line="302">

---

# PromotionDiscount in OfferServiceUtilities

The PromotionDiscount class is also used in the OfferServiceUtilities class. The `applyAdjustmentsForItemPriceDetails` method uses the `getPromotionDiscounts` method of PromotionDiscount to apply adjustments for item price details.

```java
        for (PromotableOrderItemPriceDetail itemPriceDetail : itemPriceDetails) {
            for (PromotionDiscount discount : itemPriceDetail.getPromotionDiscounts()) {
                if (discount.getPromotion().equals(itemOffer.getOffer())) {
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
