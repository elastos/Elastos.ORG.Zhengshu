/**
 * Copyright (c) 2017-2018 The Elastos Developers
 * <p>
 * Distributed under the MIT software license, see the accompanying file
 * LICENSE or https://opensource.org/licenses/mit-license.php
 */
package org.elastos.conf;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("elaservice")
public class ElaServiceConfiguration {
    private String blockAgentPrefix;
    private String didExplorerUrl;

    public String getBlockAgentPrefix() {
        return blockAgentPrefix;
    }

    public void setBlockAgentPrefix(String blockAgentPrefix) {
        this.blockAgentPrefix = blockAgentPrefix;
    }

    public String getDidExplorerUrl() {
        return didExplorerUrl;
    }

    public void setDidExplorerUrl(String didExplorerUrl) {
        this.didExplorerUrl = didExplorerUrl;
    }
}
