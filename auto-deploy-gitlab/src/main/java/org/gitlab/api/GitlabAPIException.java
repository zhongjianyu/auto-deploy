package org.gitlab.api;

import java.io.IOException;

/**
 * Gitlab API Exception
 */
public class GitlabAPIException extends IOException {

    /**
	 * @描述：TODO
	 */
	private static final long serialVersionUID = 2141938680478082113L;
	private int responseCode;

    public GitlabAPIException(String message, Integer responseCode, Throwable cause) {
        super(message, cause);
        this.responseCode = responseCode;
    }

    public int getResponseCode() {
        return responseCode;
    }
}
