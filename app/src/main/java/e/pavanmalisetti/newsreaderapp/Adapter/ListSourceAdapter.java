package e.pavanmalisetti.newsreaderapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;
import e.pavanmalisetti.newsreaderapp.Common.Common;
import e.pavanmalisetti.newsreaderapp.Interface.IconBetterIdeaService;
import e.pavanmalisetti.newsreaderapp.Interface.ItemClickListener;
import e.pavanmalisetti.newsreaderapp.ListNews;
import e.pavanmalisetti.newsreaderapp.Model.IconBetterIdea;
import e.pavanmalisetti.newsreaderapp.Model.WebSite;
import e.pavanmalisetti.newsreaderapp.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

class ListSourceViewHolder extends RecyclerView.ViewHolder
                 implements View.OnClickListener
{

    ItemClickListener itemClickListener;

    TextView source_title;
    CircleImageView source_image;

    public ListSourceViewHolder(View itemView) {
        super(itemView);

        source_image=(CircleImageView)itemView.findViewById(R.id.source_image);
        source_title=(TextView)itemView.findViewById(R.id.source_name);

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

public class ListSourceAdapter extends RecyclerView.Adapter<ListSourceViewHolder>{

    private Context context;
    private WebSite webSite;

    private IconBetterIdeaService mService;

    public ListSourceAdapter(Context context, WebSite webSite) {
        this.context = context;
        this.webSite = webSite;

        mService= Common.getIconService();
    }

    @Override
    public ListSourceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View itemView=inflater.inflate(R.layout.source_layout,parent,false);
        return new ListSourceViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ListSourceViewHolder holder, final int position) {

        holder.source_title.setText(webSite.getSources().get(position).getName());

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                Intent intent=new Intent(context, ListNews.class);
                intent.putExtra("source",webSite.getSources().get(position).getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return webSite.getSources().size();
        //because each source website will have each icon,so we need get icon and using picasso to load icon to image view
    }
}
