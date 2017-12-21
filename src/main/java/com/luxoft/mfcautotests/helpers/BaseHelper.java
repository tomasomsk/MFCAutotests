package com.luxoft.mfcautotests.helpers;

import com.luxoft.mfcautotests.FrameWork;
import com.luxoft.mfcautotests.config.annotations.Helper;
import com.luxoft.mfcautotests.config.annotations.InjectLogger;
import com.luxoft.mfcautotests.environment.TestEnvironment;
import com.luxoft.mfcautotests.utils.DriverUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

@Helper
public class BaseHelper<T> extends FrameWork {

    @InjectLogger
    public Logger log;
    @Autowired
    @Lazy
    DriverUtils driverUtils;
    @Autowired
    TestEnvironment env;



}
