package com.luxoft.mfcautotests.config.forhelpers;

import org.springframework.stereotype.Component;

@Component
public class ModuleOnServer {

    private String name;
    private String server;

    public ModuleOnServer() {

    }

    public ModuleOnServer(String name, String server) {
        this.name = name;
        this.server = server;
    }

    public String getName() {
        return name;
    }

    public String getServer() {
        return server;
    }
}
