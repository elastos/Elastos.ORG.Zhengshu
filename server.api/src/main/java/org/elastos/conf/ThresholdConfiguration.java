/**
 * Copyright (c) 2017-2018 The Elastos Developers
 * <p>
 * Distributed under the MIT software license, see the accompanying file
 * LICENSE or https://opensource.org/licenses/mit-license.php
 */
package org.elastos.conf;

import jnr.ffi.annotations.In;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("threshold")
public class ThresholdConfiguration {
    private Integer userThreshold;

    public Integer getUserThreshold() {
        return userThreshold;
    }

    public void setUserThreshold(Integer userThreshold) {
        this.userThreshold = userThreshold;
    }
}
