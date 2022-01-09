package app.adib.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import app.adib.Activity.ContentActivity;
import app.adib.DataModel.Drama;
import app.adib.DataModel.TranslateStory;
import app.adib.R;
import de.hdodenhof.circleimageview.CircleImageView;

public class RvDrAdapter extends RecyclerView.Adapter<RvDrAdapter.myViewHolder> {


    Context context;
    List<Drama> dramas;

    public RvDrAdapter(Context context, List<Drama> dramas) {

        this.context = context;
        this.dramas = dramas;
    }

    @NonNull
    @Override
    public RvDrAdapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.view_rv_list, parent, false);
        return new RvDrAdapter.myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RvDrAdapter.myViewHolder holder, int position) {


        holder.rv_tv_title.setText(dramas.get(position).getTitle());
        holder.rv_tv_author.setText(dramas.get(position).getAuthor_name());
        Glide.with(context).load(dramas.get(position).getAuthor_image()).fitCenter().into(holder.rv_img);

    }

    @Override
    public int getItemCount() {
        return dramas.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        CircleImageView rv_img;
        TextView rv_tv_title, rv_tv_author;


        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            rv_img = itemView.findViewById(R.id.rv_img);
            rv_tv_title = itemView.findViewById(R.id.rv_tv_title);
            rv_tv_author = itemView.findViewById(R.id.rv_tv_author);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

            Intent intent = new Intent(context, ContentActivity.class);
            intent.putExtra(ContentActivity.ID, dramas.get(getAdapterPosition()).getId());
            intent.putExtra("title", dramas.get(getAdapterPosition()).getTitle());
            intent.putExtra("author", dramas.get(getAdapterPosition()).getAuthor_name());
            intent.putExtra("content", dramas.get(getAdapterPosition()).getContent());
            intent.putExtra("img", dramas.get(getAdapterPosition()).getAuthor_image());

            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    }
}