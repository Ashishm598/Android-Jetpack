package api;

import java.util.List;

import io.reactivex.Observable;
import pojo.response.Response;
import retrofit2.http.GET;

public interface API {

    // Base URL
    String BASE_URL = "https://mp-android-challenge.herokuapp.com";

    // Search URL
    String DATA_URL = "/data";

    @GET(DATA_URL)
    Observable<List<Response>> getData();

}
