---
title: Overview of Page Service
---
The PageService is an interface in the Broadleaf Commerce framework that provides methods for handling operations related to pages. It includes methods for finding a page by its ID, retrieving all pages, saving a page template, and more. The PageService is implemented by the PageServiceImpl class, which interacts with the PageDao to perform the actual operations on the database. The PageService is used in various parts of the application, such as the PageHandlerMapping, BroadleafRobotsController, and PageTemplateCustomPersistenceHandler, to perform operations related to pages.

The PageService also includes methods for handling page templates, which are used to define the layout of a page. These methods allow for finding a page template by its ID, saving a page template, and retrieving all page templates. The PageService is also responsible for handling caching of pages to improve performance.

In addition to the PageService, there is also a PageServiceUtility class that provides additional utility methods for handling pages. This includes methods for building a PageDTO from a Page object, adding a PageField to a PageDTO, and building a rule expression for a page.

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/page/service/PageService.java" line="35">

---

## PageService Interface

The `PageService` interface defines the contract for the service. It declares methods for finding pages and page templates by their IDs, saving page templates, finding a page by its URI, reading all pages and page templates, and more.

```java
public interface PageService {


    /**
     * Returns the page with the passed in id.
     *
     * @param pageId - The id of the page.
     * @return The associated page.
     */
    Page findPageById(Long pageId);

    /**
     * Returns the page-fields associated with a page.
     * @param pageId
     * @return
     */
    Map<String, PageField> findPageFieldMapByPageId(Long pageId);

    /**
     * Returns the page template with the passed in id.
     *
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/page/service/PageServiceImpl.java" line="60">

---

## PageServiceImpl Class

`PageServiceImpl` is the implementation of the `PageService` interface. It uses the `PageDao` to interact with the database and retrieve the data. It also uses other services like `LocaleService`, `StaticAssetService`, and `StatisticsService` to perform its operations.

```java
@Service("blPageService")
public class PageServiceImpl implements PageService {

    protected static final Log LOG = LogFactory.getLog(PageServiceImpl.class);
    protected static String AND = " && ";

    @Resource(name="blPageDao")
    protected PageDao pageDao;

    @Resource(name="blPageRuleProcessors")
    protected List<RuleProcessor<PageDTO>> pageRuleProcessors;

    @Resource(name="blLocaleService")
    protected LocaleService localeService;

    @Resource(name="blStaticAssetService")
    protected StaticAssetService staticAssetService;

    @Resource(name="blStatisticsService")
    protected StatisticsService statisticsService;

```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/web/PageHandlerMapping.java" line="59">

---

## Usage of PageService

`PageService` is used in various parts of the application. For example, in `PageHandlerMapping`, it is injected and used to handle requests for pages.

```java
    @Resource(name = "blPageService")
    private PageService pageService;
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/page/service/PageServiceUtility.java" line="52">

---

## PageServiceUtility Class

`PageServiceUtility` is a helper class used by `PageServiceImpl` to perform common operations such as building `PageDTO` objects from `Page` objects and adding fields to `PageDTO` objects.

```java
@Service("blPageServiceUtility")
public class PageServiceUtility {

    protected static final Log LOG = LogFactory.getLog(PageServiceUtility.class);
    
    protected static String AND = " && ";
    protected static final String FOREIGN_LOOKUP = "BLC_FOREIGN_LOOKUP";

    @Resource(name="blPageDao")
    protected PageDao pageDao;
    
    @Resource(name = "blGenericEntityDao")
    protected GenericEntityDao genericDao;

    @Resource(name = "blPageServiceExtensionManager")
    protected PageServiceExtensionManager extensionManager;

    @Resource(name="blStaticAssetPathService")
    protected StaticAssetPathService staticAssetPathService;

    @Resource(name = "blSandBoxHelper")
```

---

</SwmSnippet>

# PageService Functions

Let's delve into the key functions of the PageService.

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/page/service/PageServiceImpl.java" line="105">

---

## findPageById

The `findPageById` function retrieves a page by its ID. It uses the `readPageById` function from the `PageDao` to access the data.

```java
    @Override
    public Page findPageById(Long pageId) {
        return pageDao.readPageById(pageId);
    }
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/page/service/PageServiceImpl.java" line="113">

---

## findPageFieldMapByPageId

The `findPageFieldMapByPageId` function retrieves the fields associated with a page by its ID. It uses the `readPageFieldsByPageId` function from the `PageDao` to get the fields, and then maps them into a `Map` object.

```java
    @Override
    public Map<String, PageField> findPageFieldMapByPageId(Long pageId) {
        Map<String, PageField> returnMap = new HashMap<>();
        List<PageField> pageFields = pageDao.readPageFieldsByPageId(pageId);

        for (PageField pf : pageFields) {
            returnMap.put(pf.getFieldKey(), pf);
        }

        return returnMap;
    }
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/page/service/PageServiceImpl.java" line="130">

---

## savePageTemplate

The `savePageTemplate` function saves a given page template. It uses the `savePageTemplate` function from the `PageDao` to persist the template.

```java
    @Override
    @Transactional("blTransactionManager")
    public PageTemplate savePageTemplate(PageTemplate template) {
        return pageDao.savePageTemplate(template);
    }
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/page/service/PageServiceImpl.java" line="452">

---

## readAllPages

The `readAllPages` function retrieves all pages, regardless of any sandbox they are a part of. It uses the `readAllPages` function from the `PageDao` to access the data.

```java
    @Override
    public List<Page> readAllPages() {
        return pageDao.readAllPages();
    }
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-contentmanagement-module/src/main/java/org/broadleafcommerce/cms/page/service/PageServiceImpl.java" line="457">

---

## readAllPageTemplates

The `readAllPageTemplates` function retrieves all page templates, regardless of any sandbox they are a part of. It uses the `readAllPageTemplates` function from the `PageDao` to access the data.

```java
    @Override
    public List<PageTemplate> readAllPageTemplates() {
        return pageDao.readAllPageTemplates();
    }
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
