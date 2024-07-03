---
title: Understanding Product Options
---
Product Options in BroadleafCommerce-demo refer to the different variations or customizations that a product can have. These options can be anything from size, color, to personalization text. They are used in the order process to match items and validate product options. For instance, the `itemMatches` method in `LegacyOrderServiceImpl` checks if two items match based on their SKU and product options. The `ProductOptionValidationService` interface provides methods to validate product options, check if a product option has a validation strategy, and other related functionalities. The `OrderService` interface uses product options in creating and managing orders.

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/order/service/legacy/LegacyOrderServiceImpl.java" line="400">

---

# Item Matching

The `itemMatches` method is used to check if two items match based on their SKU and options. If the items have the same SKU, they are considered a match. However, there is a TODO comment indicating that product options should also be compared if the product has product options.

```java
    protected boolean itemMatches(DiscreteOrderItem item1, DiscreteOrderItem item2) {
        // Must match on SKU and options
        if (item1.getSku() != null && item2.getSku() != null) {
            if (item1.getSku().getId().equals(item2.getSku().getId())) {
                // TODO: Compare options if product has product options
                return true;
            }
        } else {
            if (item1.getProduct() != null && item2.getProduct() != null) {
                if (item1.getProduct().getId().equals(item2.getProduct().getId())) {
                    return true;
                }
            }
        }
        return false;
    }
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/order/service/ProductOptionValidationService.java" line="27">

---

# Product Option Validation

The `ProductOptionValidationService` interface provides methods to validate product options. The `isSubmitType`, `hasProductOptionValidationStrategy`, and `isAddOrNoneType` methods are used to check the type of the product option and whether it has a validation strategy.

```java
    Boolean validate(ProductOption productOption, String value);

    boolean isSubmitType(ProductOption productOption);

    boolean hasProductOptionValidationStrategy(ProductOption productOption);

    boolean isAddOrNoneType(ProductOption productOption);
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/order/service/OrderService.java" line="552">

---

# Updating Product Options

The `updateProductOptionsForItem` method in the `OrderService` interface is used to update the product options for an item in the cart. This method is used when required product options are added after the item is already in the cart.

```java
    /**
     * Since required product option can be added after the item is in the cart, we use this method 
     * to apply product option on an existing item in the cart. No validation will happen at this time, as the validation 
     * at checkout will take care of any missing product options. 
     * 
     * @param orderId
     * @param orderItemRequestDTO
     * @param priceOrder
     * @return Order
     * @throws UpdateCartException
     */
    Order updateProductOptionsForItem(Long orderId, OrderItemRequestDTO orderItemRequestDTO, boolean priceOrder) throws UpdateCartException;
```

---

</SwmSnippet>

# Product Options Functions

This section provides an overview of the main functions related to product options in the BroadleafCommerce-demo repository.

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/order/service/ProductOptionValidationServiceImpl.java" line="66">

---

## Validate Function

The `validate` function checks if a product option is valid. It first checks if a required attribute is not provided, and if so, it throws a RequiredAttributeNotProvidedException. If the attribute is provided, it checks if the attribute value matches the validation string using a regular expression. If the attribute value does not match the validation string, it throws a ProductOptionValidationException.

```java
    @Override
    public Boolean validate(ProductOption productOption, String value) {
        String attributeName = productOption.getAttributeName();

        if (isRequiredAttributeNotProvided(productOption, value)) {
            String message = "Required attribute, " + StringUtil.sanitize(attributeName) + ", not provided";

            LOG.error(message);
            throw new RequiredAttributeNotProvidedException(message, attributeName);
        } else {
            String validationString = productOption.getValidationString();
            validationString = xssExploitProtectionEnabled ? ESAPI.encoder().decodeForHTML(validationString) : validationString;
            value = siteXssWrapperEnabled ? ESAPI.encoder().decodeForHTML(value) : value;

            if (requiresValidation(productOption, value) && !validateRegex(validationString, value)) {
                String errorMessage = productOption.getErrorMessage();
                if (StringUtils.isEmpty(errorMessage)) {
                    errorMessage = "Value [" + StringUtil.sanitize(value) + "] does not match regex string [" + validationString + "]";
                }

                LOG.error(errorMessage);
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/order/service/ProductOptionValidationServiceImpl.java" line="96">

---

## isRequiredAttributeNotProvided Function

The `isRequiredAttributeNotProvided` function checks if a required attribute is not provided for a product option. It returns true if the product option is required and the attribute value is empty.

```java
    protected boolean isRequiredAttributeNotProvided(ProductOption productOption, String attributeValue) {
        return productOption.getRequired() && StringUtils.isEmpty(attributeValue);
    }
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/order/service/ProductOptionValidationServiceImpl.java" line="100">

---

## requiresValidation Function

The `requiresValidation` function checks if a product option requires validation. It returns true if the product option requires validation and the validation string exists.

```java
    protected boolean requiresValidation(ProductOption productOption, String value) {
        ProductOptionValidationType validationType = productOption.getProductOptionValidationType();
        boolean typeRequiresValidation = validationType == ProductOptionValidationType.REGEX;
        boolean validationStringExists = StringUtils.isNotEmpty(productOption.getValidationString());
        boolean isRequired = productOption.getRequired();
        boolean hasValue = StringUtils.isNotEmpty(value);

        return (isRequired || hasValue) && typeRequiresValidation && validationStringExists;
    }
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/order/service/ProductOptionValidationServiceImpl.java" line="114">

---

## hasProductOptionValidationStrategy Function

The `hasProductOptionValidationStrategy` function checks if a product option has a validation strategy. It returns true if the product option has a validation strategy.

```java
    @Override
    public boolean hasProductOptionValidationStrategy(ProductOption productOption) {
        return productOption.getProductOptionValidationStrategyType() != null;
    }
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/order/service/ProductOptionValidationServiceImpl.java" line="119">

---

## isSubmitType Function

The `isSubmitType` function checks if a product option is of submit type. It returns true if the product option is of submit type.

```java
    @Override
    public boolean isSubmitType(ProductOption productOption) {
        boolean hasStrategy = hasProductOptionValidationStrategy(productOption);

        return hasStrategy && productOption.getProductOptionValidationStrategyType().getRank().equals(SUBMIT_TYPE_RANK);
    }
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/order/service/ProductOptionValidationServiceImpl.java" line="126">

---

## isAddOrNoneType Function

The `isAddOrNoneType` function checks if a product option is of add or none type. It returns true if the product option is of add or none type.

```java
    @Override
    public boolean isAddOrNoneType(ProductOption productOption) {
        boolean hasStrategy = hasProductOptionValidationStrategy(productOption);

        return hasStrategy && productOption.getProductOptionValidationStrategyType().getRank() <= ADD_TYPE_RANK;
    }
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/order/service/ProductOptionValidationServiceImpl.java" line="134">

---

## validateWithoutException Function

The `validateWithoutException` function validates a product option without throwing an exception. If validation fails, it adds an error message to the activity messages instead of throwing an exception.

```java
    public void validateWithoutException(ProductOption productOption, String attributeValue, ActivityMessages messages) {
        try {
            validate(productOption, attributeValue);
        } catch (ProductOptionValidationException | RequiredAttributeNotProvidedException e) {
            ActivityMessageDTO msg = new ActivityMessageDTO(MessageType.PRODUCT_OPTION.getType(), 1, e.getMessage());

            if (e instanceof ProductOptionValidationException) {
                msg.setErrorCode(productOption.getErrorCode());
            } else {
                msg.setErrorCode(RequiredAttributeNotProvidedException.ERROR_CODE);
            }

            messages.getActivityMessages().add(msg);
        }
    }

    @Override
    public List<Long> findSkuIdsForProductOptionValues(Long productId, String attributeName, String attributeValue, List<Long> possibleSkuIds) {
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/order/service/ProductOptionValidationServiceImpl.java" line="150">

---

## findSkuIdsForProductOptionValues Function

The `findSkuIdsForProductOptionValues` function finds SKU IDs for specific product option values. It returns a list of SKU IDs that match the given product ID, attribute name, attribute value, and possible SKU IDs.

```java
    @Override
    public List<Long> findSkuIdsForProductOptionValues(Long productId, String attributeName, String attributeValue, List<Long> possibleSkuIds) {
        return productOptionDao.readSkuIdsForProductOptionValues(productId, attributeName, attributeValue, possibleSkuIds);
    }
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
