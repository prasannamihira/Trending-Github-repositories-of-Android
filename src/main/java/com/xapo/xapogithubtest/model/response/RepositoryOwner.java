package com.xapo.xapogithubtest.model.response;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class RepositoryOwner implements Serializable {

    @SerializedName("login")
    public String login;

    @SerializedName("avatar_url")
    public String avatarUrl;

    @SerializedName("html_url")
    public String url;

}
