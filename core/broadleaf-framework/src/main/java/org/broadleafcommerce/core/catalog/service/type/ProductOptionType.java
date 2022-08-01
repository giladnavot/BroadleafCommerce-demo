/*-
 * #%L
 * BroadleafCommerce Framework
 * %%
 * Copyright (C) 2009 - 2022 Broadleaf Commerce
 * %%
 * Licensed under the Broadleaf Fair Use License Agreement, Version 1.0
 * (the "Fair Use License" located  at http://license.broadleafcommerce.org/fair_use_license-1.0.txt)
 * unless the restrictions on use therein are violated and require payment to Broadleaf in which case
 * the Broadleaf End User License Agreement (EULA), Version 1.1
 * (the "Commercial License" located at http://license.broadleafcommerce.org/commercial_license-1.1.txt)
 * shall apply.
 * 
 * Alternatively, the Commercial License may be replaced with a mutually agreed upon license (the "Custom License")
 * between you and Broadleaf Commerce. You may not use this file except in compliance with the applicable license.
 * #L%
 */
package org.broadleafcommerce.core.catalog.service.type;

import org.broadleafcommerce.common.BroadleafEnumerationType;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * An extendible enumeration of product option types.
 * 
 * @author jfischer
 */
public class ProductOptionType implements Serializable, BroadleafEnumerationType {

    private static final long serialVersionUID = 1L;

    private static final Map<String, ProductOptionType> TYPES = new LinkedHashMap<String, ProductOptionType>();

    public static final ProductOptionType COLOR  = new ProductOptionType("COLOR","Color");
    public static final ProductOptionType SIZE  = new ProductOptionType("SIZE","Size");
    public static final ProductOptionType DATE  = new ProductOptionType("DATE","Date");
    public static final ProductOptionType TEXT  = new ProductOptionType("TEXT","Text");
    public static final ProductOptionType TEXTAREA = new ProductOptionType("TEXTAREA", "Textarea");
    public static final ProductOptionType BOOLEAN  = new ProductOptionType("BOOLEAN","Boolean");
    public static final ProductOptionType DECIMAL  = new ProductOptionType("DECIMAL","Decimal");
    public static final ProductOptionType INTEGER  = new ProductOptionType("INTEGER","Integer");
    public static final ProductOptionType INPUT  = new ProductOptionType("INPUT","Input");
    public static final ProductOptionType PRODUCT  = new ProductOptionType("PRODUCT","Product");
    public static final ProductOptionType SELECT = new ProductOptionType("SELECT", "Select");

    public static ProductOptionType getInstance(final String type) {
        return TYPES.get(type);
    }

    private String type;
    private String friendlyType;

    public ProductOptionType() {
        //do nothing
    }

    public ProductOptionType(final String type, final String friendlyType) {
        this.friendlyType = friendlyType;
        setType(type);
    }

    public String getType() {
        return type;
    }

    public String getFriendlyType() {
        return friendlyType;
    }

    private void setType(final String type) {
        this.type = type;
        if (!TYPES.containsKey(type)) {
            TYPES.put(type, this);
        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((type == null) ? 0 : type.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!getClass().isAssignableFrom(obj.getClass()))
            return false;
        ProductOptionType other = (ProductOptionType) obj;
        if (type == null) {
            if (other.type != null)
                return false;
        } else if (!type.equals(other.type))
            return false;
        return true;
    }
}
