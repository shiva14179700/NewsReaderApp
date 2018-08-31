package e.pavanmalisetti.newsreaderapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.curioustechizen.ago.RelativeTimeTextView;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import e.pavanmalisetti.newsreaderapp.Common.IS08601Parse;
import e.pavanmalisetti.newsreaderapp.DetailArticle;
import e.pavanmalisetti.newsreaderapp.Interface.ItemClickListener;
import e.pavanmalisetti.newsreaderapp.Model.Article;
import e.pavanmalisetti.newsreaderapp.R;

class ListNewsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
{

    ItemClickListener itemClickListener;

    TextView article_title;
    RelativeTimeTextView article_time;
    CircleImageView article_image;

    public ListNewsViewHolder(View itemView) {
        super(itemView);
        article_image=(CircleImageView)itemView.findViewById(R.id.article_image);
        article_time=(RelativeTimeTextView)itemView.findViewById(R.id.article_time);
        article_title =(TextView)itemView.findViewById(R.id.article_title);

        itemView.setOnClickListener(this);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View v) {
        itemClickListener.onClick(v,getAdapterPosition(),false);
    }
}

public class ListNewsAdapter extends RecyclerView.Adapter<ListNewsViewHolder>{

    private List<Article> articleList;
    private Context context;

    public ListNewsAdapter(List<Article> articleList, Context context) {
        this.articleList = articleList;
        this.context = context;
    }

    @Override
    public ListNewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View itemView=inflater.inflate(R.layout.news_layout,parent,false);
        return new ListNewsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ListNewsViewHolder holder, int position) {

        Picasso.with(context)
                .load(articleList.get(position).getUrlToImage())
                .into(holder.article_image);

        if (articleList.get(position).getTitle().length()>65){
            holder.article_title.setText(articleList.get(position).getTitle().substring(0,65)+"...");
        }else{
            holder.article_title.setText(articleList.get(position).getTitle());
        }


        if(articleList.get(position).getPublishedAt()!= null) {
            Date date = null;
            try {
                date = IS08601Parse.parse(articleList.get(position).getPublishedAt());
            } catch (ParseException ex) {
                ex.printStackTrace();

            }

            holder.article_time.setReferenceTime(date.getTime());
        }

        //set event click
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                Intent detail=new Intent(context,DetailArticle.class);
                detail.putExtra("webURL",articleList.get(position).getUrl());
                context.startActivity(detail);
            }
        });
    }

    @Override
    public int getItemCount() {
        return articleList.size();
    }
}
