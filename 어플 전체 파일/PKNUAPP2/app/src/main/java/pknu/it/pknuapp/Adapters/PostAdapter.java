package pknu.it.pknuapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import pknu.it.pknuapp.Activities.PostDetailActivity;
import pknu.it.pknuapp.Models.Post;
import pknu.it.pknuapp.R;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.MyViewHolder> {
    Context mContext;
    List<Post> mData;

    public PostAdapter(Context mContext, List<Post> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public PostAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(mContext).inflate(R.layout.row_post_item,parent,false);
        return new MyViewHolder(row);
    }

    @Override
    public void onBindViewHolder(@NonNull PostAdapter.MyViewHolder holder, int position) {

        holder.tvTitle.setText(mData.get(position).getTitle());
        Glide.with(mContext).load(mData.get(position).getPicture()).into(holder.imgPost);

        holder.tvPrice.setText(mData.get(position).getPrice());
        Glide.with(mContext).load(mData.get(position).getPicture()).into(holder.imgPost);

        holder.tvMajor.setText(mData.get(position).getMajor());
        Glide.with(mContext).load(mData.get(position).getPicture()).into(holder.imgPost);

        String userImg =mData.get(position).getUserPhoto();
        if(userImg != null){
            Glide.with(mContext).load(mData.get(position).getUserPhoto()).into(holder.imgPostProfile);
        }else
            Glide.with(mContext).load(R.drawable.userphoto).into(holder.imgPostProfile);

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class  MyViewHolder extends RecyclerView.ViewHolder{
        TextView tvTitle;
        TextView tvPrice;
        TextView tvMajor;
        ImageView imgPost;
        ImageView imgPostProfile;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.row_post_title);
            tvPrice = itemView.findViewById(R.id.row_post_price);
            tvMajor = itemView.findViewById(R.id.row_post_major);
            imgPost = itemView.findViewById(R.id.row_post_img);
            imgPostProfile = itemView.findViewById(R.id.row_post_profile_img);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent postDetailActivity = new Intent(mContext, PostDetailActivity.class);
                    int position = getAdapterPosition();

                    postDetailActivity.putExtra("책제목",mData.get(position).getTitle());
                    postDetailActivity.putExtra("게시물사진",mData.get(position).getPicture());
                    postDetailActivity.putExtra("가격",mData.get(position).getPrice());
                    postDetailActivity.putExtra("전공",mData.get(position).getMajor());
                    postDetailActivity.putExtra("설명",mData.get(position).getDescription());
                    postDetailActivity.putExtra("포스트키",mData.get(position).getPostKey());
                    postDetailActivity.putExtra("사용자사진",mData.get(position).getUserPhoto());


                    long timestamp  = (long) mData.get(position).getTimeStamp();
                    postDetailActivity.putExtra("게시 날짜",timestamp) ;
                    mContext.startActivity(postDetailActivity);
                }
            });

        }


    }
}
