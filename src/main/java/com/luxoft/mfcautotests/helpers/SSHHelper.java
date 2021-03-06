package com.luxoft.mfcautotests.helpers;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.luxoft.mfcautotests.config.annotations.Helper;
import com.luxoft.mfcautotests.config.forhelpers.ModuleOnServer;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Helper
public class SSHHelper extends BaseHelper {

    public void restartModuleMmc() {
        //If property exist and doesn't commented in property file
        if (!env.mmcRegServerUrl_1.equalsIgnoreCase("${mmcRegServerUrl_1}")) {
            log.info("Restarting mmc module on server " + env.mmcRegServerUrl_1);
            restartModule(new ModuleOnServer("mmc", env.mmcRegServerUrl_1));
        }
        if (!env.mmcRegServerUrl_2.equalsIgnoreCase("${mmcRegServerUrl_2}")) {
            log.info("Restarting mmc module on server " + env.mmcRegServerUrl_2);
            restartModule(new ModuleOnServer("mmc", env.mmcRegServerUrl_2));
        }
    }

    public void restartModule(ModuleOnServer module) {
        List<ModuleOnServer> modules = new ArrayList<>();
        modules.add(module);
        restartModules(modules);
//        String command = "/etc/init.d/" + moduleName + " stop";
//        log.info("Stopping mmc module on server " + serverUrl);
//        executeShell(command, env.sshUserName, env.sshPassword, serverUrl);
//
//        sleep(10_000);
//
//        command = "/etc/init.d/" + moduleName + " start";
//        log.info("Starting mmc module on server " + serverUrl);
//        executeShell(command, env.sshUserName, env.sshPassword, serverUrl);
//
//        checkThatModuleStarts(moduleName, serverUrl);
    }

    public void restartModules(List<ModuleOnServer> modules) {
        for (ModuleOnServer module : modules) {
            stopModule(module);
            sleep(10_000);
            startModule(module);
            checkThatModuleStarts(module);
        }
    }

    public void stopModule(ModuleOnServer module) {
        String command = "/etc/init.d/" + module.getName() + " stop";
        log.info("Stopping " +  module.getName() + " module on server " + module.getServer());
        executeShell(command, env.sshUserName, env.sshPassword, module.getServer());
    }

    public void startModule(ModuleOnServer module) {
        String command = "/etc/init.d/" + module.getName() + " start";
        log.info("Starting " + module.getName() + " module on server " + module.getServer());
        executeShell(command, env.sshUserName, env.sshPassword, module.getServer());
    }

    private void checkThatModuleStarts(ModuleOnServer module) {
        log.info("Checking that module has been started (OUTPUT should be > 0)");
        executeShell("ps aux | grep -v grep | grep \"/opt/webapps/" + module.getName() + "/\" | awk '{print $2}' | head -1",
                env.sshUserName, env.sshPassword, module.getServer());
    }

    public String executeShell(String shellScriptCommand, String userName, String password, String server) {
        List<String> result = new ArrayList<String>();
        try {
            log.info("Creating session, user: " + userName);
            JSch jSch = new JSch();
            Session session = jSch.getSession(userName, server, env.sshPort);
            session.setConfig("StrictHostKeyChecking", "no");
            session.setPassword(password);
            session.connect();
            log.info("Opening exec channel");
            ChannelExec channelExec = (ChannelExec) session.openChannel("exec");

            log.info("Creating InputStream");
            InputStream in = channelExec.getInputStream();
            channelExec.setCommand(shellScriptCommand);
            log.info("Executing the shell script: " + shellScriptCommand);
            channelExec.connect();

            log.info("Reading the output from the input stream");
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            sleep(1000);
            while (reader.ready()) {
                String line = reader.readLine();
                result.add(line);
            }
            reader.close();
            log.info("OUTPUT is " + result);

            log.info("Getting exit status");
            int exitStatus = channelExec.getExitStatus();
            log.info("Exit status is [" + exitStatus + "]");

            channelExec.disconnect();
            session.disconnect();
        } catch (JSchException | IOException e) {
            e.printStackTrace();
        }
        return String.join(", ", result);
    }

    public Date getDateFromServer(String serverUrl) {
        log.info("Getting date from server " + serverUrl);
        String serverDate = executeShell("date +%d.%m.%Y' '%R", "appsuser", "appsuser", serverUrl);
        return getDateFromString(serverDate, "dd.MM.yyyy HH:mm");
    }
}
