package com.suwish.welcome;

import com.suwish.welcome.model.HttpResponse;
import com.suwish.welcome.model.ModelAction;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * @author by min.su on 2016/10/11.
 */
public interface ExampleServiceTest {

    @GET(ModelAction.TEST_PATH_APIS)
    HttpResponse<Map<String, String>> queryAPIS();

    /*****  retrofit async  *****/

    @GET(ModelAction.TEST_PATH_APIS)
    Call<HttpResponse<Map<String, String>>> asyncQueryAPIS();

    @GET("/repos/{owner}/{repo}/contributors")
    Call<HttpResponse<Map<String, String>>> asyncRepoContributors(@Path("owner") String owner, @Path("repo") String repo);

    /*****  rxJava  *******/
    @GET(ModelAction.TEST_PATH_APIS)
    Observable<HttpResponse<Map<String, String>>> rxQueryAPIS();

    Observable<HttpResponse<Map<String, String>>> rxrRpoContributors(@Path("owner") String owner, @Path("repo") String repo);
}
