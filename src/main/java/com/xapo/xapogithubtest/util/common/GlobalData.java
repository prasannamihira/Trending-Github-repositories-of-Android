package com.xapo.xapogithubtest.util.common;

import com.xapo.xapogithubtest.model.response.RepositoryBody;

public class GlobalData {

    private static GlobalData globalData;

    public static RepositoryBody repositoryBody;

    private GlobalData() {

    }

    /**
     * GlobalData singleton instance
     *
     * @return
     */
    public static synchronized GlobalData getInstance() {
        if (globalData == null) {
            globalData = new GlobalData();

            repositoryBody = new RepositoryBody();

        }
        return globalData;
    }

    public RepositoryBody getRepositoryBody() {
        return repositoryBody;
    }

    public void setRepositoryBody(RepositoryBody repositoryBody) {
        GlobalData.repositoryBody = repositoryBody;
    }
}
