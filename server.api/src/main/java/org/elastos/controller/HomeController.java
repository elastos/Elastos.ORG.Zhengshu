/**
 * Copyright (c) 2017-2018 The Elastos Developers
 * <p>
 * Distributed under the MIT software license, see the accompanying file
 * LICENSE or https://opensource.org/licenses/mit-license.php
 */
package org.elastos.controller;

import org.springframework.web.bind.annotation.*;

/**
 * clark
 * <p>
 * 9/26/18
 */
@RestController
@RequestMapping("/")
public class HomeController {
    @RequestMapping(value = "/",method = {RequestMethod.GET})
    public String welcome(){
        return "<!DOCTYPE html>\n" +
                "<html>\n" +
                "\n" +
                "<head>\n" +
                "  <title>Welcome to elastos service</title>\n" +
                "</head>\n" +
                "\n" +
                "<body>\n" +
                "\n" +
                "  <h2>using Nodela Api to access Elastos blockchain within a second</h2>\n" +
                "</body>\n" +
                "</html>";
    }
}
