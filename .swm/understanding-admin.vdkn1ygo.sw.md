---
title: Understanding Admin
---
Admin in BroadleafCommerce-demo refers to the administrative functionalities provided by the framework. It includes classes and interfaces that manage the admin menus, sections, and user permissions. The AdminUserAdminPresentation class defines constants used for admin user presentation, such as 'General', 'User', 'RolesAndPermissions', and 'Miscellaneous'. The AdminMenu class holds the admin menus and sections that a user has permissions to view. The AdminNavigationService interface defines methods for building the admin menu and checking if a user is authorized to view a section. The AdminUserAdminPresentation class also defines the 'TabName' class for tab names and 'GroupOrder' class for group orders.

<SwmSnippet path="/admin/broadleaf-open-admin-platform/src/main/java/org/broadleafcommerce/openadmin/server/security/domain/AdminMenu.java" line="27">

---

## AdminMenu Class

The 'AdminMenu' class holds the admin menus and sections for which the passed in user has permissions to view. It contains a list of 'AdminModule' objects, which represent the different modules in the admin interface.

```java
public class AdminMenu {

    private List<AdminModule> adminModules = new ArrayList<AdminModule>();

    public List<AdminModule> getAdminModules() {
        return adminModules;
    }

    public void setAdminModule(List<AdminModule> adminModules) {
        this.adminModules = adminModules;
    }
}
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-open-admin-platform/src/main/java/org/broadleafcommerce/openadmin/server/security/domain/AdminUserAdminPresentation.java" line="45">

---

## AdminUserAdminPresentation Class

The 'AdminUserAdminPresentation' class contains several constants that define the order and grouping of fields in the admin interface. These constants are used to customize the presentation of the admin user interface.

```java
    public static class TabName {
        public static final String General = "General";
    }

    public static class TabOrder {
        public static final int General = 1000;
    }

    public static class GroupName {
        public static final String User = "AdminUserImpl_User";
        public static final String AdditionalFields = "AdminUserImpl_AdditionalFields";

        public static final String RolesAndPermissions = "AdminUserImpl_RolesAndPermissions";
        public static final String Miscellaneous = "AdminUserImpl_Miscellaneous";
    }

    public static class GroupOrder {
        public static final int User = 1000;
        public static final int AdditionalFields = 2000;

        public static final int RolesAndPermissions = 1000;
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-open-admin-platform/src/main/java/org/broadleafcommerce/openadmin/server/security/service/navigation/AdminNavigationService.java" line="29">

---

## AdminNavigationService Interface

The 'AdminNavigationService' interface defines the methods for building the admin menu and checking if a user is authorized to view a section. The 'buildMenu' method takes an 'AdminUser' object and returns an 'AdminMenu' object.

```java
public interface AdminNavigationService {

    public AdminMenu buildMenu(AdminUser adminUser);

    public boolean isUserAuthorizedToViewSection(AdminUser adminUser, AdminSection section);
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
