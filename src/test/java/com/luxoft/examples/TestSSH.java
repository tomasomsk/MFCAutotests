package com.luxoft.examples;

import com.luxoft.BaseTest;
import com.luxoft.annotations.NonDriver;
import com.luxoft.mfcautotests.helpers.SSHHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

public class TestSSH extends BaseTest {

    @Autowired
    SSHHelper sshHelper;

    @Test
    @NonDriver
    public void testSsh() {
        sshHelper.restartModuleMmc();
    }

}
