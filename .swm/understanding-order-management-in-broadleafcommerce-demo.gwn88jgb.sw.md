---
title: Understanding Order Management in BroadleafCommerce-demo
---
Order Management in BroadleafCommerce-demo refers to the administrative operations performed on the Order entity. This includes creating, updating, and managing orders. These operations are handled by the AdminOrderController, which provides the necessary endpoints and logic for managing orders.

<SwmSnippet path="/admin/broadleaf-admin-module/src/main/java/org/broadleafcommerce/admin/web/controller/entity/AdminOrderController.java" line="34">

---

# AdminOrderController Class

The AdminOrderController class handles admin operations for the Order entity. It extends the AdminBasicEntityController class, which provides basic CRUD operations. The class is annotated with @Controller, indicating that it's a Spring MVC Controller, and @RequestMapping, which maps HTTP requests to handler methods of MVC and REST controllers.

```java
 * Handles admin operations for the {@link Order} entity. 
 * 
 * @author Andre Azzolini (apazzolini)
 */
@Controller("blAdminOrderController")
@RequestMapping("/" + AdminOrderController.SECTION_KEY)
public class AdminOrderController extends AdminBasicEntityController {
    
    public static final String SECTION_KEY = "order";
    
    @Override
    protected String getSectionKey(Map<String, String> pathVars) {
        //allow external links to work for ToOne items
        if (super.getSectionKey(pathVars) != null) {
            return super.getSectionKey(pathVars);
        }
        return SECTION_KEY;
    }
    
    @Override
    protected String showViewUpdateCollection(HttpServletRequest request, Model model, Map<String, String> pathVars,
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
