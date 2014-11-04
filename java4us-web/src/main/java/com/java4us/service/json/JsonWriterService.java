/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java4us.service.json;

import com.java4us.commons.service.JsonSerializer;
import org.apache.commons.httpclient.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author TCAN
 */
@Service
public class JsonWriterService {

    @Autowired
    private JsonSerializer jsonSerializer;

    public void writeSuccessfulResponse(HttpServletResponse response) {
        writeResponse(response, null);
    }

    public void writeFailedResponse(HttpServletResponse response, String reason) {
        writeResponse(response, HttpStatus.SC_BAD_REQUEST, reason);
    }

    public void writeResponse(HttpServletResponse response, Object object) {
        writeResponse(response, HttpStatus.SC_OK, object);
    }

    public void writeResponse(HttpServletResponse response, int httpStatus, Object object) {
        response.setContentType("application/json");
        response.setStatus(httpStatus);
        try {
            jsonSerializer.writeJson(object, response.getWriter());
        } catch (IOException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
        }
    }

    public void writeResponse(HttpServletResponse response, String key, Object message) {
        Map<String, Object> params = new HashMap<>();
        params.put(key, message);
        writeResponse(response, params);
    }
}
