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
import app.adib.DataModel.PersianPoem;
import app.adib.R;
import de.hdodenhof.circleimageview.CircleImageView;

public class RvPpAdapter extends RecyclerView.Adapter<RvPpAdapter.myViewHolder> {


    Context context;
    List<PersianPoem> persianPoems;

    public RvPpAdapter(Context context, List<PersianPoem> persianPoems) {

        this.context = context;
        this.persianPoems = persianPoems;
    }

    @NonNull
    @Override
    public RvPpAdapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.view_rv_list, parent, false);
        return new RvPpAdapter.myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RvPpAdapter.myViewHolder holder, int position) {


        holder.rv_tv_title.setText(persianPoems.get(position).getTitle());
        holder.rv_tv_author.setText(persianPoems.get(position).getAuthor_name());
        Glide.with(context).load(persianPoems.get(position).getAuthor_image()).fitCenter().into(holder.rv_img);

    }

    @Override
    public int getItemCount() {
        return persianPoems.size();
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
            intent.putExtra(ContentActivity.ID, persianPoems.get(getAdapterPosition()).getId());
            intent.putExtra("title", persianPoems.get(getAdapterPosition()).getTitle());
            intent.putExtra("author", persianPoems.get(getAdapterPosition()).getAuthor_name());
            intent.putExtra("content", persianPoems.get(getAdapterPosition()).getContent());
            intent.putExtra("img", persianPoems.get(getAdapterPosition()).getAuthor_image());

            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    }
}
