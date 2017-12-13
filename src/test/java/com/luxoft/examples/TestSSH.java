package com.luxoft.examples;

import com.luxoft.BaseTest;
import com.luxoft.mfcautotests.config.annotations.NonDriver;
import com.luxoft.mfcautotests.config.forhelpers.ModuleOnServer;
import com.luxoft.mfcautotests.helpers.SSHHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestSSH extends BaseTest {

    @Autowired
    SSHHelper sshHelper;

    @Test
    @NonDriver
    public void testSsh() {

        ModuleOnServer[] modulesOnServers = {
//                new ModuleOnServer("mmc-gate", "zln-mfc-tst1-auto.luxoft.com"),
//                new ModuleOnServer("mmc-bank-service", "zln-mfc-tst1-auto.luxoft.com")
        new ModuleOnServer("mmcvvs", "zln-mfc-tst2-auto.luxoft.com"),
        new ModuleOnServer("mmc-core", "zln-mfc-tst1-auto.luxoft.com"),
        new ModuleOnServer("mmc-bank-service", "zln-mfc-tst1-auto.luxoft.com"),
//        new ModuleOnServer("mmcvvs", "lc-mfc-tst.luxoft.com")
        };

        List<ModuleOnServer> modules = new ArrayList<>(Arrays.asList(modulesOnServers));

        sshHelper.restartModules(modules);
    }

}
