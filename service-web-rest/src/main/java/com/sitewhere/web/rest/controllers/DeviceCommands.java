/*
 * Copyright (c) SiteWhere, LLC. All rights reserved. http://www.sitewhere.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package com.sitewhere.web.rest.controllers;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sitewhere.rest.model.device.request.DeviceCommandCreateRequest;
import com.sitewhere.spi.SiteWhereException;
import com.sitewhere.spi.SiteWhereSystemException;
import com.sitewhere.spi.device.IDeviceManagement;
import com.sitewhere.spi.device.command.IDeviceCommand;
import com.sitewhere.spi.error.ErrorCode;
import com.sitewhere.spi.error.ErrorLevel;
import com.sitewhere.spi.user.SiteWhereRoles;
import com.sitewhere.web.annotation.SiteWhereCrossOrigin;
import com.sitewhere.web.rest.RestControllerBase;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * Controller for device command operations.
 * 
 * @author Derek Adams
 */
@RestController
@SiteWhereCrossOrigin
@RequestMapping(value = "/commands")
@Api(value = "commands")
public class DeviceCommands extends RestControllerBase {

    /** Static logger instance */
    @SuppressWarnings("unused")
    private static Log LOGGER = LogFactory.getLog(DeviceCommands.class);

    /**
     * Update an existing device command.
     * 
     * @param token
     * @param request
     * @return
     * @throws SiteWhereException
     */
    @RequestMapping(value = "/{token}", method = RequestMethod.PUT)
    @ApiOperation(value = "Update an existing device command")
    @Secured({ SiteWhereRoles.REST })
    public IDeviceCommand updateDeviceCommand(@ApiParam(value = "Token", required = true) @PathVariable String token,
	    @RequestBody DeviceCommandCreateRequest request) throws SiteWhereException {
	IDeviceCommand command = assertDeviceCommandByToken(token);
	return getDeviceManagement().updateDeviceCommand(command.getId(), request);
    }

    /**
     * Get a device command by unique token.
     * 
     * @param token
     * @return
     * @throws SiteWhereException
     */
    @RequestMapping(value = "/{token}", method = RequestMethod.GET)
    @ApiOperation(value = "Get device command by unique token")
    @Secured({ SiteWhereRoles.REST })
    public IDeviceCommand getDeviceCommandByToken(
	    @ApiParam(value = "Token", required = true) @PathVariable String token) throws SiteWhereException {
	return assertDeviceCommandByToken(token);
    }

    /**
     * Delete an existing device command.
     * 
     * @param token
     * @param force
     * @return
     * @throws SiteWhereException
     */
    @RequestMapping(value = "/{token}", method = RequestMethod.DELETE)
    @ApiOperation(value = "Delete device command by unique token")
    @Secured({ SiteWhereRoles.REST })
    public IDeviceCommand deleteDeviceCommand(@ApiParam(value = "Token", required = true) @PathVariable String token,
	    @ApiParam(value = "Delete permanently", required = false) @RequestParam(defaultValue = "false") boolean force,
	    HttpServletRequest servletRequest) throws SiteWhereException {
	IDeviceCommand command = assertDeviceCommandByToken(token);
	return getDeviceManagement().deleteDeviceCommand(command.getId(), force);
    }

    /**
     * Gets a device command by token and throws an exception if not found.
     * 
     * @param token
     * @param servletRequest
     * @return
     * @throws SiteWhereException
     */
    protected IDeviceCommand assertDeviceCommandByToken(String token) throws SiteWhereException {
	IDeviceCommand result = getDeviceManagement().getDeviceCommandByToken(token);
	if (result == null) {
	    throw new SiteWhereSystemException(ErrorCode.InvalidDeviceCommandToken, ErrorLevel.ERROR);
	}
	return result;
    }

    private IDeviceManagement getDeviceManagement() {
	return getMicroservice().getDeviceManagementApiDemux().getApiChannel();
    }
}