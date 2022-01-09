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
import app.adib.Activity.DramaActivity;
import app.adib.DataModel.PersianStory;
import app.adib.R;
import de.hdodenhof.circleimageview.CircleImageView;

public class RvPsAdapter extends RecyclerView.Adapter<RvPsAdapter.myViewHolder> {

     Context context;

    List<PersianStory> persianStories;

    public RvPsAdapter(Context context, List<PersianStory> persianStories) {
        this.context = context;
        this.persianStories = persianStories;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.view_rv_list, parent, false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {


        holder.rv_tv_title.setText(persianStories.get(position).getTitle());
        holder.rv_tv_author.setText(persianStories.get(position).getAuthor_name());
        Glide.with(context).load(persianStories.get(position).getAuthor_image()).fitCenter().into(holder.rv_img);

    }

    @Override
    public int getItemCount() {
        return persianStories.size();
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
            intent.putExtra(ContentActivity.ID, persianStories.get(getAdapterPosition()).getId());
            intent.putExtra("title", persianStories.get(getAdapterPosition()).getTitle());
            intent.putExtra("author", persianStories.get(getAdapterPosition()).getAuthor_name());
            intent.putExtra("content", persianStories.get(getAdapterPosition()).getContent());
            intent.putExtra("img", persianStories.get(getAdapterPosition()).getAuthor_image());

            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);

        }
    }
}
