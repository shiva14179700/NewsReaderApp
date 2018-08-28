package e.pavanmalisetti.newsreaderapp.Interface;

import e.pavanmalisetti.newsreaderapp.Common.Common;
import e.pavanmalisetti.newsreaderapp.Model.WebSite;
import retrofit2.Call;
import retrofit2.http.GET;

public interface NewsService {
    @GET("v2/sources?language=en&apiKey="+ Common.API_KEY)
    Call<WebSite> getSources();
}
