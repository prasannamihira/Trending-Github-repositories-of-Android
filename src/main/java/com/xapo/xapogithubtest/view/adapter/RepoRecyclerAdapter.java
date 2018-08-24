package com.xapo.xapogithubtest.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.xapo.xapogithubtest.R;
import com.xapo.xapogithubtest.model.response.RepositoryBody;
import com.xapo.xapogithubtest.util.common.GlobalData;
import com.xapo.xapogithubtest.view.activity.GitRepositoryDetailsActivity;

import java.util.ArrayList;

public class RepoRecyclerAdapter extends RecyclerView.Adapter<RepoRecyclerAdapter.RepoViewHolder> {

    private final Context context;
    private ArrayList<RepositoryBody> list;

    private Intent intent;

    /**
     * Constructor RepoRecyclerAdapter
     *
     * @param context
     * @param list
     */
    public RepoRecyclerAdapter(Context context, ArrayList<RepositoryBody> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RepoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.repo_list_item, parent, false);
        RepoViewHolder myHolder = new RepoViewHolder(context, view);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(RepoViewHolder holder, int position) {

        final RepositoryBody repository = list.get(position);

        Picasso.with(context).load(repository.owner.avatarUrl).into(holder.ivOwner);
        holder.tvOwner.setText(repository.owner.login);
        holder.tvName.setText(repository.name);
        holder.tvDescription.setText(repository.description);
        holder.tvLanguage.setText(repository.language);
        holder.tvRating.setText(repository.stargazersCount);
        holder.tvWatch.setText(repository.watchersCount);
        holder.tvCreateDate.setText(repository.createdAt);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                intent = new Intent(context, GitRepositoryDetailsActivity.class);
                intent.putExtra("repository", repository);
                GlobalData.getInstance().setRepositoryBody(repository);

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    /**
     * ViewHolder class
     */
    public class RepoViewHolder extends RecyclerView.ViewHolder {

        public CardView cardView;
        public ImageView ivOwner;
        public TextView tvOwner, tvName, tvDescription, tvLanguage, tvRating, tvWatch, tvCreateDate;
        private Context context;

        /**
         * Constructor RepoViewHolder
         *
         * @param context
         * @param itemView
         */
        public RepoViewHolder(Context context, View itemView) {
            super(itemView);

            this.context = context;
            cardView = (CardView) itemView.findViewById(R.id.card_view);
            ivOwner = (ImageView) itemView.findViewById(R.id.iv_owner);
            tvOwner = (TextView) itemView.findViewById(R.id.tv_owner);
            tvName = (TextView) itemView.findViewById(R.id.tv_name);
            tvDescription = (TextView) itemView.findViewById(R.id.tv_description);
            tvLanguage = (TextView) itemView.findViewById(R.id.tv_language);
            tvRating = (TextView) itemView.findViewById(R.id.tv_rate_count);
            tvWatch = (TextView) itemView.findViewById(R.id.tv_watch_count);
            tvCreateDate = (TextView) itemView.findViewById(R.id.tv_create_date);
        }

        /**
         * Bind values with repository item UI components
         *
         * @param repository
         */
        public void bind(@NonNull RepositoryBody repository) {

            Picasso.with(context).load(repository.owner.avatarUrl).into(ivOwner);
            tvOwner.setText(repository.owner.login);
            tvName.setText(repository.name);
            tvDescription.setText(repository.description);
            tvLanguage.setText(repository.language);
            tvRating.setText(repository.stargazersCount);
            tvWatch.setText(repository.watchersCount);
            tvCreateDate.setText(repository.createdAt);
        }
    }
}
