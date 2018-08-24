package com.xapo.xapogithubtest.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.method.LinkMovementMethod;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.xapo.xapogithubtest.R;
import com.xapo.xapogithubtest.model.response.RepositoryBody;

public class GitRepositoryDetailsActivity extends AppCompatActivity {

    public ImageView ivOwner;
    public TextView tvOwner, tvOwnerUrl, tvName, tvDescription, tvLanguage, tvRating, tvWatch, tvCreateDate, tvHtmlUrl;

    private Context context = GitRepositoryDetailsActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_git_repository_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        uiInitialize();

        Intent extras = getIntent();
        if (extras != null) {
            RepositoryBody repository = (RepositoryBody) extras.getSerializableExtra("repository");
            if (repository != null) {
                setUiValues(repository);
            }
        }
    }

    private void uiInitialize() {
        ivOwner = (ImageView) findViewById(R.id.iv_owner_profile);
        tvOwner = (TextView) findViewById(R.id.tv_owner_profile);
        tvOwnerUrl = (TextView) findViewById(R.id.tv_owner_url);
        tvOwnerUrl.setMovementMethod(LinkMovementMethod.getInstance());
        tvName = (TextView) findViewById(R.id.tv_repo_name);
        tvDescription = (TextView) findViewById(R.id.tv_repo_description);
        tvLanguage = (TextView) findViewById(R.id.tv_repo_language);
        tvRating = (TextView) findViewById(R.id.tv_repo_rate_count);
        tvWatch = (TextView) findViewById(R.id.tv_repo_watch_count);
        tvCreateDate = (TextView) findViewById(R.id.tv_repo_create_date);
        tvHtmlUrl = (TextView) findViewById(R.id.tv_html_url);
        tvHtmlUrl.setMovementMethod(LinkMovementMethod.getInstance());

    }

    private void setUiValues(RepositoryBody repository) {
        Picasso.with(context).load(repository.owner.avatarUrl).into(ivOwner);
        tvOwner.setText(repository.owner.login);
        tvOwnerUrl.setText(repository.owner.url);
        tvName.setText(repository.name);
        tvDescription.setText(repository.description);
        tvLanguage.setText(repository.language);
        tvRating.setText(repository.stargazersCount);
        tvWatch.setText(repository.watchersCount);
        tvCreateDate.setText(repository.createdAt);
        tvHtmlUrl.setText(repository.htmlUrl);
    }

}
