---
title: Exploring Exceptions
---
Exceptions in BroadleafCommerce-demo are used to handle errors and other exceptional events that occur during the execution of the application. They are implemented as classes that extend the Java Exception class or its subclasses. For example, the `IllegalCartOperationException` is used to handle invalid operations on a shopping cart, while the `OrderServiceException` is thrown when there's an issue with the order service. The `RequiredAttributeNotProvidedException` is thrown when a required attribute for a product is not provided. Each exception class provides constructors to create an exception with a message or cause, and some have additional methods to provide more information about the error.

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/order/service/exception/OrderServiceException.java" line="20">

---

## Custom Exceptions

Here we see a custom exception `OrderServiceException` which extends `RuntimeException`. This exception might be thrown when there's an issue with the order service.

```java
public class OrderServiceException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    

    public OrderServiceException() {
        super();
    }

    public OrderServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public OrderServiceException(String message) {
        super(message);
    }

    public OrderServiceException(Throwable cause) {
        super(cause);
    }

```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/order/service/workflow/add/ValidateAddRequestActivity.java" line="130">

---

## Handling Exceptions

Here we see an example of how exceptions are handled in the code. The `RequiredAttributeNotProvidedException` is caught and handled within a `try-catch` block.

```java
            } catch (RequiredAttributeNotProvidedException e) {
                if (orderItemRequestDTO instanceof ConfigurableOrderItemRequest) {
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/order/service/workflow/add/ValidateAddRequestActivity.java" line="191">

---

## Throwing Exceptions

Here we see an example of throwing a custom exception. If the system is unable to find a non-default SKU matching given options and cannot sell the default SKU, a `RequiredAttributeNotProvidedException` is thrown.

```java
                throw new RequiredAttributeNotProvidedException("Unable to find non-default sku matching given options and cannot sell default sku", null);
            }
```

---

</SwmSnippet>

# Functions of Exceptions

This section will cover the functions of the exceptions IllegalCartOperationException, RequiredAttributeNotProvidedException, and ItemNotFoundException in the BroadleafCommerce-demo codebase.

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/order/service/exception/IllegalCartOperationException.java" line="21">

---

## IllegalCartOperationException

The IllegalCartOperationException is an abstract class that extends RuntimeException. It is thrown when an illegal operation is performed on a cart. This exception class provides several constructors to create an exception with a message, a cause, or both. It also includes an abstract method `getType` which must be implemented by any class that extends this exception.

```java
public abstract class IllegalCartOperationException extends RuntimeException {

    private static final long serialVersionUID = 5113456015951023947L;

    protected IllegalCartOperationException() {
        super();
    }
    
    public IllegalCartOperationException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public IllegalCartOperationException(String message) {
        super(message);
    }
    
    public IllegalCartOperationException(Throwable cause) {
        super(cause);
    }
    
    public abstract String getType();
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/order/service/exception/RequiredAttributeNotProvidedException.java" line="20">

---

## RequiredAttributeNotProvidedException

The RequiredAttributeNotProvidedException extends RuntimeException and is thrown when an attempt to add to cart without specifying all required product options has been made. It includes methods to get and set the attribute name and product id that caused the exception.

```java
/**
 * This runtime exception will be thrown when an attempt to add to cart without specifying
 * all required product options has been made.
 * 
 * @author apazzolini
 */
public class RequiredAttributeNotProvidedException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    protected String productId;
    public static final String ERROR_CODE = "REQUIRED_ATTRIBUTE";

    protected String attributeName;

    public RequiredAttributeNotProvidedException(String message, String attributeName, String productId) {
        super(message);
        setAttributeName(attributeName);
        setProductId(productId);
    }

```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/order/service/exception/ItemNotFoundException.java" line="20">

---

## ItemNotFoundException

The ItemNotFoundException extends Exception and is thrown when an item is not found in the order. It provides several constructors to create an exception with a message, a cause, or both.

```java
public class ItemNotFoundException extends Exception {

    private static final long serialVersionUID = 1L;

    public ItemNotFoundException() {
        super();
    }

    public ItemNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ItemNotFoundException(String message) {
        super(message);
    }

    public ItemNotFoundException(Throwable cause) {
        super(cause);
    }

}
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
