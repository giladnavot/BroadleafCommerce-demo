---
title: Understanding the Admin Security Service
---
The Admin Security Service in BroadleafCommerce-demo is a crucial component that manages the security aspects of the admin side of the application. It is responsible for managing admin users, roles, and permissions. It provides functionalities such as reading all admin users, roles, and permissions, saving and deleting them, and changing the password of an admin user. It also provides methods to check if a user is qualified for a certain operation on a ceiling entity and to clear the admin security cache. The service is implemented in the `AdminSecurityServiceImpl` class, and it uses various other components such as `AdminUserDao`, `AdminRoleDao`, and `AdminPermissionDao` to perform its tasks.

<SwmSnippet path="/admin/broadleaf-open-admin-platform/src/main/java/org/broadleafcommerce/openadmin/server/security/service/AdminSecurityService.java" line="34">

---

## AdminSecurityService Interface

The `AdminSecurityService` interface defines the contract for the admin security service. It declares methods for managing admin users, roles, and permissions, as well as for clearing the admin security cache.

```java
public interface AdminSecurityService {

    public static final String[] DEFAULT_PERMISSIONS = { "PERMISSION_OTHER_DEFAULT", "PERMISSION_ALL_USER_SANDBOX" };

    List<AdminUser> readAllAdminUsers();
    AdminUser readAdminUserById(Long id);
    AdminUser readAdminUserByUserName(String userName);
    AdminUser saveAdminUser(AdminUser user);
    void deleteAdminUser(AdminUser user);

    List<AdminRole> readAllAdminRoles();
    AdminRole readAdminRoleById(Long id);
    AdminRole saveAdminRole(AdminRole role);
    void deleteAdminRole(AdminRole role);

    List<AdminPermission> readAllAdminPermissions();
    AdminPermission readAdminPermissionById(Long id);
    AdminPermission saveAdminPermission(AdminPermission permission);
    void deleteAdminPermission(AdminPermission permission);

    /**
```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-open-admin-platform/src/main/java/org/broadleafcommerce/openadmin/server/security/service/AdminSecurityServiceImpl.java" line="72">

---

## AdminSecurityServiceImpl Class

The `AdminSecurityServiceImpl` class is the implementation of the `AdminSecurityService` interface. It provides the actual logic for the methods declared in the interface. For example, the `readAdminUserByUserName` method is implemented to read an admin user by their username from the `adminUserDao`.

```java
@Service("blAdminSecurityService")
public class AdminSecurityServiceImpl implements AdminSecurityService {

    private static final Log LOG = LogFactory.getLog(AdminSecurityServiceImpl.class);

    private static int TEMP_PASSWORD_LENGTH = 12;
    private static final int FULL_PASSWORD_LENGTH = 16;

    @Autowired
    @Qualifier("blApplicationEventPublisher")
    protected BroadleafApplicationEventPublisher eventPublisher;

    @Resource(name = "blAdminRoleDao")
    protected AdminRoleDao adminRoleDao;

    @Resource(name = "blAdminUserDao")
    protected AdminUserDao adminUserDao;

    @Resource(name = "blForgotPasswordSecurityTokenDao")
    protected ForgotPasswordSecurityTokenDao forgotPasswordSecurityTokenDao;

```

---

</SwmSnippet>

<SwmSnippet path="/admin/broadleaf-open-admin-platform/src/main/java/org/broadleafcommerce/openadmin/server/security/service/user/AdminUserProvisioningServiceImpl.java" line="51">

---

## Using AdminSecurityService

The `AdminSecurityService` is used in other parts of the application, such as the `AdminUserProvisioningServiceImpl` class. Here, it is used to add default permissions to admin user authorities.

```java
    protected AdminSecurityService securityService;

    @Resource(name = "blAdminExternalLoginExtensionManager")
    protected AdminExternalLoginUserExtensionManager adminExternalLoginExtensionManager;

    @Resource(name="blAdminSecurityHelper")
    protected AdminSecurityHelper adminSecurityHelper;

    protected Map<String, String[]> roleNameSubstitutions;

    @Override
    public AdminUserDetails provisionAdminUser(
            final BroadleafExternalAuthenticationUserDetails details) {
        final HashSet<AdminRole> parsedRoles = parseAdminRoles(details);
        final Set<SimpleGrantedAuthority> adminUserAuthorities = 
                extractAdminUserAuthorities(parsedRoles);
        final AdminUser adminUser = getAdminUser(details, parsedRoles);

        return createDetails(adminUser, details, adminUserAuthorities);
    }
    
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="overview"><sup>Powered by [Swimm](/)</sup></SwmMeta>
