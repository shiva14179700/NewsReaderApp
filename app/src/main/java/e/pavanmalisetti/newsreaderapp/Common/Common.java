package e.pavanmalisetti.newsreaderapp.Common;

import e.pavanmalisetti.newsreaderapp.Interface.IconBetterIdeaService;
import e.pavanmalisetti.newsreaderapp.Interface.NewsService;
import e.pavanmalisetti.newsreaderapp.Remote.IconBetterIdeaClient;
import e.pavanmalisetti.newsreaderapp.Remote.RetrofitClient;

public class Common {
    private static final String BASE_URL="https://newsapi.org/";

    public static final String API_KEY="b120f97cc01743e6a858b3dd1cff43a9";

    public static NewsService getNewsService()
    {
        return RetrofitClient.getClient(BASE_URL).create(NewsService.class);
    }

    public static IconBetterIdeaService getIconService()
    {
        return IconBetterIdeaClient.getClient().create(IconBetterIdeaService.class);
    }

    public static String getAPIUrl(String source,String sortBy,String apiKEY){
        StringBuilder apiUrl=new StringBuilder("https://newsapi.org/v2/top-headlines?sources=");
        return apiUrl.append(source)
                .append("&apiKey=")
                .append(apiKEY)
                .toString();
    }

}
