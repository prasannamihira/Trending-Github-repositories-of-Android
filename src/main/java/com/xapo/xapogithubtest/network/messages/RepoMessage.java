package com.xapo.xapogithubtest.network.messages;

import com.xapo.xapogithubtest.model.response.RepositoryResponse;

public interface RepoMessage {

    /**
     * Success repository response
     *
     * @param repositoryResponse
     */
    void onSuccessResponse(RepositoryResponse repositoryResponse);

    /**
     * @param error
     */
    void onFailureResponse(Throwable error);
}
