package com.suwish.welcome;

import com.suwish.welcome.model.HttpResponse;
import com.suwish.welcome.model.ModelAction;

/**
 * @author by min.su on 2016/10/13.
 */
final class ExampleJsonHelper {

    private ExampleJsonHelper(){}

    static <T> HttpResponse<T> parse(String actionPath, String body){
        HttpResponse<T> response = new HttpResponse<>();
        switch (actionPath){
            case ModelAction.TEST_PATH_APIS:
                parseAPIS(response, body);
                break;
        }
        return response;
    }

    private static <T> HttpResponse<T> parseAPIS(HttpResponse<T> response, String body){
        try {
            response.setCode(200);
            response.setMessage(body);
        }catch (Exception ex){
            response.setCode(-1);
            response.setMessage(ex.getMessage());
        }
        return response;
    }
}
