---
title: 'Modular Auto-detection in Broadleaf Commerce: Leveraging Spring Factories'
---
This document will cover how Broadleaf uses Spring factories for module auto-detection. We'll cover:

1. The role of Spring factories in Broadleaf
2. How Broadleaf leverages Spring's `Autowired` annotation
3. The use of `EnableBroadleafAutoConfiguration` annotation in Broadleaf
4. The `ModulePresentUtil` class and its use of `SpringFactoriesLoader`.

<SwmSnippet path="/integration/src/test/resources/META-INF/spring.factories" line="1">

---

# The Role of Spring Factories in Broadleaf

Spring factories are used in Broadleaf for auto-detection of modules. This file is an example of a Spring factories file in Broadleaf. It lists the classes that should be automatically configured by Spring Boot.

```factories
org.broadleafcommerce.common.config.FrameworkCommonClasspathPropertySource=org.broadleafcommerce.common.config.SystemPropertiesTest.SystemPropertiesTestConfig
```

---

</SwmSnippet>

<SwmSnippet path="/core/broadleaf-framework-web/src/main/java/org/broadleafcommerce/core/web/order/security/CartStateFilter.java" line="31">

---

# Leveraging Spring's Autowired Annotation

Broadleaf leverages Spring's `Autowired` annotation to automatically inject beans into classes. This is an example of how Broadleaf uses `Autowired` to inject dependencies.

```java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.Ordered;
```

---

</SwmSnippet>

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/config/EnableBroadleafAutoConfiguration.java" line="32">

---

# EnableBroadleafAutoConfiguration Annotation

`EnableBroadleafAutoConfiguration` is a custom annotation in Broadleaf that is used to bootstrap Broadleaf configuration XML using a glob import. It utilizes the `FrameworkXmlBeanDefinitionReader` so that framework XML bean definitions will not overwrite beans defined in a project.

```java
/**
 * <b>STOP. This is probably not the annotation you want currently.</b>
 * <p>
 * Broadleaf is progressing towards the Spring Framework's methodology of bean definition and priority. The following
 * are the different phases Broadleaf will progress through with this goal in mind.
 * <p>
 * Phase 1 <i>(current)</i> - Broadleaf framework XML configuration is separated out into {@code site} and {@code admin}
 * specific directories within {@code /blc-config} in at least one module. For this reason, you must use {@link
 * EnableBroadleafAdminAutoConfiguration} and {@link EnableBroadleafSiteAutoConfiguration} for admin and site
 * applications respectively.
 * <p>
 * Phase 2 - <i>Medium level of effort</i> - All Broadleaf modules have had {@link
 * org.broadleafcommerce.common.admin.condition.ConditionalOnAdmin} applied to admin specific beans and the XML
 * configuration files have been moved to just {@code /blc-config}. You can now use {@link
 * EnableBroadleafAutoConfiguration} for both site and admin applications, however it isn't a requirement to migrate
 * {@link EnableBroadleafAdminAutoConfiguration} and {@link EnableBroadleafSiteAutoConfiguration} as they also include
 * root {@code /blc-config} in addition to the {@code admin} and {@code site} specific directories.
 * <p>
 * Phase 3 - <i>Very high level of effort</i> - All framework bean definitions in Broadleaf have been migrated to
 * JavaConfig or annotation based configuration and have applied a Spring Boot Autoconfiguration {@code
 * ConditionalOn...} annotation that defines the condition on which it should be registered. The most common of these
```

---

</SwmSnippet>

<SwmSnippet path="/common/src/main/java/org/broadleafcommerce/common/module/ModulePresentUtil.java" line="43">

---

# ModulePresentUtil and SpringFactoriesLoader

`ModulePresentUtil` is a utility class in Broadleaf that uses `SpringFactoriesLoader` to load factories. This is used to auto-detect Broadleaf modules.

```java
    public static final List<BroadleafModuleRegistration> MODULE_REGISTRATIONS = SpringFactoriesLoader.loadFactories(BroadleafModuleRegistration.class, null);
```

---

</SwmSnippet>

&nbsp;

*This is an auto-generated document by Swimm AI 🌊 and has not yet been verified by a human*

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBQnJvYWRsZWFmQ29tbWVyY2UtZGVtbyUzQSUzQWdpbGFkbmF2b3Q=" repo-name="BroadleafCommerce-demo" doc-type="follow-up"><sup>Powered by [Swimm](/)</sup></SwmMeta>
