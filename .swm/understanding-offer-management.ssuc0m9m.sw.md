---
title: Understanding Offer Management
---
Offer Management in Broadleaf Commerce refers to the administration and handling of offers within the e-commerce framework. This involves the creation, modification, and application of offers to various products. The `AdminOfferController` class is responsible for handling admin operations for the `Offer` entity. It ensures that certain Offer fields only render when specific values are set for other fields. The `AdminOfferControllerExtensionHandler` class extends the functionality of the `AdminOfferController`, providing additional UX Meta-Data to display the Rule Builders on the Offer Screen.

<SwmSnippet path="/admin/broadleaf-admin-module/src/main/java/org/broadleafcommerce/admin/web/controller/entity/AdminOfferController.java" line="43">

---

## AdminOfferController

The `AdminOfferController` class handles admin operations for the `Offer` entity. It provides methods for viewing the entity list, viewing the entity form, adding an entity, and duplicating an entity. It also modifies model attributes to handle offer field visibility based on other fields in the entity.

```java
 * Handles admin operations for the {@link Offer} entity. Certain Offer fields should only render when specific values
 * are set for other fields; we provide the support for that in this controller.
 * 
 * @author Andre Azzolini (apazzolini)
 */
@Controller("blAdminOfferController")
@RequestMapping("/" + AdminOfferController.SECTION_KEY)
public class AdminOfferController extends AdminBasicEntityController {
    
    public static final String SECTION_KEY = "offer";
    public static String[] customCriteria = {};

    @Resource(name="blOfferService")
    protected OfferService offerService;

    @Override
    protected String getSectionKey(Map<String, String> pathVars) {
        //allow external links to work for ToOne items
        if (super.getSectionKey(pathVars) != null) {
            return super.getSectionKey(pathVars);
        }
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-admin-module/src/main/java/org/broadleafcommerce/admin/web/controller/extension/AdminOfferControllerExtensionHandler.java" line="35">

---

## AdminOfferControllerExtensionHandler

The `AdminOfferControllerExtensionHandler` class extends the functionality of the `AdminOfferController` by setting additional model attributes. It provides UX Meta-Data to display the Rule Builders on the Offer Screen.

```java
 * @author Elbert Bautista (elbertbautista)
 */
@Component("blAdminOfferControllerExtensionHandler")
public class AdminOfferControllerExtensionHandler extends AbstractAdminAbstractControllerExtensionHandler {

    @Resource(name = "blAdminAbstractControllerExtensionManager")
    protected AdminAbstractControllerExtensionManager extensionManager;

    @PostConstruct
    public void init() {
        if (isEnabled()) {
            extensionManager.registerHandler(this);
        }
    }

    @Override
    public ExtensionResultStatusType setAdditionalModelAttributes(Model model, String sectionKey) {
        if (AdminOfferController.SECTION_KEY.equals(sectionKey)) {
            EntityForm form = (EntityForm) model.asMap().get("entityForm");

            if (form != null) {
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
