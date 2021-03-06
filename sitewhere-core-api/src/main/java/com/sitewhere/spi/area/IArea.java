/*
 * Copyright (c) SiteWhere, LLC. All rights reserved. http://www.sitewhere.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package com.sitewhere.spi.area;

import java.io.Serializable;
import java.util.UUID;

import com.sitewhere.spi.common.IMetadataProviderEntity;

/**
 * An entity that represents a geographical area based on a previously defined
 * area type.
 * 
 * @author Derek
 */
public interface IArea extends IMetadataProviderEntity, Serializable {

    /**
     * Get id of corresponding area type.
     * 
     * @return
     */
    public UUID getAreaTypeId();

    /**
     * Get id of parent id (null if none).
     * 
     * @return
     */
    public UUID getParentAreaId();

    /**
     * Get the area name.
     * 
     * @return
     */
    public String getName();

    /**
     * Get the description.
     * 
     * @return
     */
    public String getDescription();

    /**
     * Get the image URL.
     * 
     * @return
     */
    public String getImageUrl();

    /**
     * Get map information.
     * 
     * @return
     */
    public IAreaMapData getMap();
}