package com.fakenews;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fakenews.datatypes.EnumRoles;
import com.fakenews.ejb.NewsEJBLocal;
import com.fakenews.ejb.ToolsLocal;
import com.fakenews.model.Admin;
import com.fakenews.model.Checker;
import com.fakenews.model.Submitter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PushbackInputStream;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.Nullable;
import javax.annotation.Priority;

import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.Provider;

import org.apache.http.HttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.InputStreamEntity;
import org.jboss.resteasy.core.Headers;
import org.jboss.resteasy.core.ResourceMethodInvoker;
import org.jboss.resteasy.core.ServerResponse;
import org.jboss.resteasy.util.Base64;

/**
 *
 * @author rmoreno
 */
@Provider
@Priority(Priorities.AUTHENTICATION)
public class SecurityFilter implements javax.ws.rs.container.ContainerRequestFilter {
 
    @EJB 
    private ToolsLocal securityMgt;
    
    private static final String AUTHORIZATION_PROPERTY = "Authorization";
    private static final String AUTHENTICATION_SCHEME = "Basic";
    private static final ServerResponse ACCESS_DENIED = new ServerResponse("Access denied for this resource", 401, new Headers<Object>());
    private static final ServerResponse ACCESS_FORBIDDEN = new ServerResponse("Nobody can access this resource", 403, new Headers<Object>());
    private static final ServerResponse SERVER_ERROR = new ServerResponse("INTERNAL SERVER ERROR", 500, new Headers<Object>());

    @Override
    public void filter(ContainerRequestContext requestContext) {   	
        ResourceMethodInvoker methodInvoker = (ResourceMethodInvoker) requestContext.getProperty("org.jboss.resteasy.core.ResourceMethodInvoker");
        Method method = methodInvoker.getMethod();
        //Access allowed for all
        if (!method.isAnnotationPresent(PermitAll.class)) {
            //Access denied for all
            if (method.isAnnotationPresent(DenyAll.class)) {
                requestContext.abortWith(ACCESS_FORBIDDEN);
                return;
            }

            //Get request headers
            final MultivaluedMap<String, String> headers = requestContext.getHeaders();

            //Fetch authorization header
            final List<String> authorization = headers.get(AUTHORIZATION_PROPERTY);

            //If no authorization information present; block access
            UriInfo uriinfo = requestContext.getUriInfo();
            if (!uriinfo.getPath().isEmpty()) {
                System.out.println("uriinfo.getPath(): " + uriinfo.getPath());
                    if ((authorization == null || authorization.isEmpty())) {
                        requestContext.abortWith(ACCESS_DENIED);
                        return;
                    }

                    try {
                        //getToken
                        System.out.println("authorization.get(0): " + authorization.get(0));
                        String token = authorization.get(0).replaceFirst("Bearer ", "");
                        System.out.println("token: " + token);
                        Algorithm algorithm = Algorithm.HMAC256(securityMgt.getSecret());
                        JWTVerifier verifier = JWT.require(algorithm)
                                .withIssuer("fakeNews")
                                .build();

                        DecodedJWT jwt = verifier.verify(token);
                        System.out.println("jwt: " + jwt);
                        
                        DecodedJWT decodedToken = JWT.decode(token);
                        System.out.println("decodedToken: " + decodedToken);
                        
                        String encodedUserName = decodedToken.getClaim("username").asString();
                        String encodedPassword = decodedToken.getClaim("password").asString();
                        
                        System.out.println("encodedUserName: " + encodedUserName);
                        System.out.println("encodedPassword: " + encodedPassword);
                        
                        //Decode username
                        String username, password = null;
                        username = new String(Base64.decode(encodedUserName));
                        password = new String(Base64.decode(encodedPassword));

                        //Verifying Username and password
                        System.out.println(username);
                        System.out.println(password);

                        //Is user valid?
                        if (!isUserAllowed(username, password)) {
                            requestContext.abortWith(ACCESS_DENIED);
                            return;
                        }
                        
                    } catch (Exception e) {
                        System.out.println("SecurityFilter: " + e.getMessage());
                        requestContext.abortWith(ACCESS_FORBIDDEN);
                        return;
                    }
            }

        }
    }

    private boolean isUserAllowed(final String username, final String password) throws Exception {
    	return securityMgt.isUserAllowed(username,password);
    }
    
}
