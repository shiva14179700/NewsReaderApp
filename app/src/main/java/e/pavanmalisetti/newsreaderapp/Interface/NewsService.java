package e.pavanmalisetti.newsreaderapp.Interface;

import e.pavanmalisetti.newsreaderapp.Common.Common;
import e.pavanmalisetti.newsreaderapp.Model.News;
import e.pavanmalisetti.newsreaderapp.Model.WebSite;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface NewsService {
    @GET("v2/sources?language=en&apiKey="+ Common.API_KEY)
    Call<WebSite> getSources();

    @GET
    Call<News> getNewestArticles(@Url String url);
}
