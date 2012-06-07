/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.artivisi.virtualaccount.ws.metro;

import com.artivisi.virtualaccount.domain.VirtualAccountService;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 *
 * @author endy
 */
@WebService(serviceName = "NasabahWS")
public class NasabahWS {

    
    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }
}
