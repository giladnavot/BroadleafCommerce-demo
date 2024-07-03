---
title: Exploring Tax Detail in Broadleaf Commerce
---
TaxDetail is an interface in the Broadleaf Commerce framework that encapsulates tax-related information for an order. It includes details such as the tax type, amount, and rate. This information is crucial for calculating the total tax for an order, which includes taxes applied directly to items, fulfillment groups, and fees. The TaxDetail interface also provides methods to set and get these properties.

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/order/domain/TaxDetail.java" line="33">

---

# TaxDetail Interface

This is the TaxDetail interface. It defines methods for setting and getting tax-related details such as ID, type, amount, rate, currency, module configuration, jurisdiction name, tax name, region, and country.

```java
public interface TaxDetail extends Serializable, MultiTenantCloneable<TaxDetail> {

    /**
     * Gets the id.
     * 
     * @return the id
     */
    public Long getId();

    /**
     * Sets the id.
     * 
     * @param id the new id
     */
    public void setId(Long id);
    
    /**
     * Gets the tax type
     * 
     * @return the tax type
     */
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/pricing/service/workflow/TotalActivity.java" line="129">

---

# Usage of TaxDetail

Here, TaxDetail is used in the TotalActivity class to calculate the total tax for fulfillment groups, items, and fees. The getTaxes() method is used to retrieve the list of TaxDetail objects, and the getAmount() method is used to retrieve the tax amount from each TaxDetail object.

```java
            if (fg.getTaxes() != null) {
                for (TaxDetail tax : fg.getTaxes()) {
                    fgTotalFgTax = fgTotalFgTax.add(tax.getAmount());
                }
            }
            
            for (FulfillmentGroupItem item : fg.getFulfillmentGroupItems()) {
                Money itemTotalTax = BroadleafCurrencyUtils.getMoney(BigDecimal.ZERO, order.getCurrency());
                
                // Add in all taxes for this item
                if (item.getTaxes() != null) {
                    for (TaxDetail tax : item.getTaxes()) {
                        itemTotalTax = itemTotalTax.add(tax.getAmount());
                    }
                }
                
                item.setTotalTax(itemTotalTax);
                fgTotalItemTax = fgTotalItemTax.add(itemTotalTax);
            }
            
            for (FulfillmentGroupFee fee : fg.getFulfillmentGroupFees()) {
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/order/domain/TaxDetailImpl.java" line="51">

---

# TaxDetail Implementation

This is the TaxDetailImpl class, which is an implementation of the TaxDetail interface. It provides the actual implementation of the methods defined in the TaxDetail interface.

```java
@AdminPresentationClass(friendlyName = "TaxDetailImpl_baseTaxDetail")
public class TaxDetailImpl implements TaxDetail {
    
    private static final long serialVersionUID = -4036994446393527252L;

    @Id
    @GeneratedValue(generator = "TaxDetailId")
    @GenericGenerator(
        name="TaxDetailId",
        strategy="org.broadleafcommerce.common.persistence.IdOverrideTableGenerator",
        parameters = {
            @Parameter(name="segment_value", value="TaxDetailImpl"),
            @Parameter(name="increment_size", value="150"),
            @Parameter(name="entity_name", value="org.broadleafcommerce.core.catalog.domain.TaxDetailImpl")
        }
    )
    @Column(name = "TAX_DETAIL_ID")
    protected Long id;
    
    @Column(name = "TYPE")
    @AdminPresentation(friendlyName = "TaxDetailImpl_Tax_Type", order=1, group = "TaxDetailImpl_Tax_Detail")
```

---

</SwmSnippet>

# TaxDetail Interface

The TaxDetail interface provides methods for managing tax details associated with an order.

<SwmSnippet path="/core/broadleaf-framework/src/main/java/org/broadleafcommerce/core/order/domain/TaxDetail.java" line="33">

---

## TaxDetail Interface

The TaxDetail interface provides methods for managing tax details associated with an order. It includes methods for getting and setting the ID, tax type, tax amount, tax rate, currency, module configuration, jurisdiction name, tax name, region, and country. These methods allow for the retrieval and modification of tax details for an order.

```java
public interface TaxDetail extends Serializable, MultiTenantCloneable<TaxDetail> {

    /**
     * Gets the id.
     * 
     * @return the id
     */
    public Long getId();

    /**
     * Sets the id.
     * 
     * @param id the new id
     */
    public void setId(Long id);
    
    /**
     * Gets the tax type
     * 
     * @return the tax type
     */
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
