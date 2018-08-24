package com.xapo.xapogithubtest.view.activity;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;

import com.xapo.xapogithubtest.R;
import com.xapo.xapogithubtest.view.adapter.RepoRecyclerAdapter;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static junit.framework.Assert.assertTrue;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class GitRepositoryListActivityTest {

    private RecyclerView recyclerView;
    private RepoRecyclerAdapter adapter;

    @Rule
    public ActivityTestRule<GitRepositoryListActivity> activityTestRule = new ActivityTestRule<>(GitRepositoryListActivity.class);

    @Before
    public void setUp() {
        recyclerView =
                (RecyclerView) activityTestRule.getActivity().findViewById(R.id.rv_repositories);

        adapter = activityTestRule.getActivity().mAdapter;
    }

    @Test
    public void testClickGroup() {
        onView(withId(R.id.rv_repositories))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

    }

    @Test
    public void testClickItem() {
        onView(withId(R.id.rv_repositories))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, click()));
    }

    /**
     * Matches the {@link com.xapo.xapogithubtest.view.adapter.RepoRecyclerAdapter.RepoViewHolder}s in the middle of the list.
     */
    private static Matcher<RepoRecyclerAdapter.RepoViewHolder> isRecyclable() {
        return new TypeSafeMatcher<RepoRecyclerAdapter.RepoViewHolder>() {
            @Override
            protected boolean matchesSafely(RepoRecyclerAdapter.RepoViewHolder customHolder) {
                return customHolder.isRecyclable();
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("items can recycle");
            }
        };
    }

}
