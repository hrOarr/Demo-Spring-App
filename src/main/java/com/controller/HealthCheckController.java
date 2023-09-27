package com.controller;

import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;
import io.swagger.annotations.Api;

import javax.inject.Named;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/test")
@Api(value = "/test", description = "Actuator Api")
@Named
public class HealthCheckController {

    @GET
    @Path("/check")
    @ApiOperation(value = "Check health", response=String.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "success"),
            @ApiResponse(code = 500, message = "Internal Server Error"),
            @ApiResponse(code = 400, message = "Bad Request")})
    @Produces({MediaType.APPLICATION_JSON})
    public String healthCheck(){
        return "I am Health Check!";
    }
}