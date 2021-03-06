/*
 * Copyright (c) SiteWhere, LLC. All rights reserved. http://www.sitewhere.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package com.sitewhere.spi.resource;

/**
 * Indicates error creating resource.
 * 
 * @author Derek
 */
public interface IResourceCreateError {

    /**
     * Get path being created.
     * 
     * @return
     */
    public String getPath();

    /**
     * Get reason for failure.
     * 
     * @return
     */
    public ResourceCreateFailReason getReason();
}