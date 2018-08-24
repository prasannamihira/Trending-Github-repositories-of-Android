package com.xapo.xapogithubtest.view.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

import com.xapo.xapogithubtest.R;
import com.xapo.xapogithubtest.model.response.RepositoryResponse;
import com.xapo.xapogithubtest.network.XapoGitService;
import com.xapo.xapogithubtest.network.messages.RepoMessage;
import com.xapo.xapogithubtest.util.MessageUtil;
import com.xapo.xapogithubtest.view.adapter.RepoRecyclerAdapter;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.xapo.xapogithubtest.util.MessageUtil.closeProgress;
import static com.xapo.xapogithubtest.util.MessageUtil.showProgress;

public class GitRepositoryListActivity extends BaseActivity implements RepoMessage {

    private static final String TAG = "GitRepositoryActivity";
    private CoordinatorLayout clRepoList;
    private RecyclerView recyclerView;
    private Subscription subscription;
    private ProgressDialog progressDialog;
    private String order, language;

    public RepoRecyclerAdapter mAdapter;

    @NonNull
    public static Intent newIntent(Context context) {
        return new Intent(context, GitRepositoryListActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_git_repository_list);

        initializeUi();

        progressDialog = new ProgressDialog(GitRepositoryListActivity.this);

        if (isConnected(GitRepositoryListActivity.this)) {
            order = "desc";
            language = "android";

            viewSubscriberRepositories(order, language);
        } else {
            MessageUtil.snackBarMessage(clRepoList, getResources().getString(R.string.error_no_connectivity), true);
        }
    }

    /**
     * initialize UI components
     */
    private void initializeUi() {
        clRepoList = (CoordinatorLayout) findViewById(R.id.cl_repos);
        recyclerView = (RecyclerView) findViewById(R.id.rv_repositories);
    }

    /**
     * RecyclerView item decoration - give equal margin around grid item
     */
    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

    /**
     * Subscribe for get git repositories by language
     *
     * @param order
     * @param language
     */
    private void viewSubscriberRepositories(String order, String language) {

        // show progress
        showProgress(progressDialog, getResources().getString(R.string.message_wait), getResources().getString(R.string.message_sync));

        try {

            // Rx request - get git repositories of Android
            Observable<RepositoryResponse> gitResponseObservable = XapoGitService.getInstance().gitRepositoryRequest(order, language);

            this.subscription = gitResponseObservable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<RepositoryResponse>() {
                        @Override
                        public void onCompleted() {

                            Log.d(TAG, "onCompleted");
                            subscription.unsubscribe();
                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.d(TAG, "onError");

                            //close progress
                            closeProgress(progressDialog);
                            e.printStackTrace();

                            // failed response
                            onFailureResponse(e);
                        }

                        @Override
                        public void onNext(RepositoryResponse jobsResponse) {
                            Log.d(TAG, "onNext");

                            //close progress
                            closeProgress(progressDialog);

                            // success response
                            onSuccessResponse(jobsResponse);

                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onSuccessResponse(RepositoryResponse repositoryResponse) {

        mAdapter = new RepoRecyclerAdapter(GitRepositoryListActivity.this, repositoryResponse.itemsList);

        // create grid layout manager to set cards in grid view
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(1, dpToPx(10), true));
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

    }

    @Override
    public void onFailureResponse(Throwable error) {

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }
}
