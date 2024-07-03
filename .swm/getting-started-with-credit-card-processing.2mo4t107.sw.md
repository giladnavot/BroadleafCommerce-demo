---
title: Getting Started with Credit Card Processing
---
Credit Card Processing in BroadleafCommerce-demo refers to the functionality that handles the processing of credit card payments. This includes the identification of card types, the handling of transparent redirects for credit card forms, and the management of credit card extensions. The `CreditCardTypesProcessor` class, for instance, adds any Payment Gateway specific Card Type 'codes' to the model if the gateway requires that a 'Card Type' (e.g. Visa, MasterCard, etc...) be sent along with the credit card number and expiry date. This processor will put the key 'paymentGatewayCardTypes' on the model if there are any types available. The `TransparentRedirectCreditCardFormProcessor` class, on the other hand, handles the processing of credit card forms with transparent redirects.

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/web/payment/processor/CreditCardTypesProcessor.java" line="60">

---

# CreditCardTypesProcessor Class

This is the `CreditCardTypesProcessor` class. It extends `AbstractBroadleafVariableModifierProcessor` and is responsible for processing credit card types. It uses the `CreditCardTypesExtensionManager` to populate a map of credit card types.

```java
public class CreditCardTypesProcessor extends AbstractBroadleafVariableModifierProcessor {

    protected static final Log LOG = LogFactory.getLog(CreditCardTypesProcessor.class);

    @Resource(name = "blCreditCardTypesExtensionManager")
    protected CreditCardTypesExtensionManager extensionManager;

    @Override
    public String getName() {
        return "credit_card_types";
    }
    
    @Override
    public int getPrecedence() {
        return 100;
    }
    
    @Override
    public boolean useGlobalScope() {
        return false;
    }
```

---

</SwmSnippet>

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/web/payment/processor/CreditCardTypesProcessor.java" line="82">

---

# populateModelVariables Method

This is the `populateModelVariables` method. It creates a map of credit card types and populates it using the `populateCreditCardMap` method from the `CreditCardTypesExtensionManager`. If the map is not empty, it is added to the model under the key 'paymentGatewayCardTypes'.

```java
    @Override
    public Map<String, Object> populateModelVariables(String tagName, Map<String, String> tagAttributes, BroadleafTemplateContext context) {
        Map<String, String> creditCardTypes = new HashMap<>();

        try {
            extensionManager.getProxy().populateCreditCardMap(creditCardTypes);
        } catch (Exception e) {
            LOG.warn("Unable to Populate Credit Card Types Map for this Payment Module, or card type is not needed.");
        }

        if (!creditCardTypes.isEmpty()) {
            return ImmutableMap.of("paymentGatewayCardTypes", (Object) creditCardTypes);
        } else {
            return null;
        }
    }
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
