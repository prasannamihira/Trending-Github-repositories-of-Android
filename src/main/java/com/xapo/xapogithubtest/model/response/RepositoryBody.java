package com.xapo.xapogithubtest.model.response;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class RepositoryBody implements Serializable {

    @SerializedName("id")
    public String id;

    @SerializedName("node_id")
    public String nodeId;

    @SerializedName("name")
    public String name;

    @SerializedName("full_name")
    public String fullName;

    @SerializedName("owner")
    public RepositoryOwner owner;

    @SerializedName("html_url")
    public String htmlUrl;

    @SerializedName("description")
    public String description;

    @SerializedName("created_at")
    public String createdAt;

    @SerializedName("stargazers_count")
    public String stargazersCount;

    @SerializedName("watchers_count")
    public String watchersCount;

    @SerializedName("language")
    public String language;


}
