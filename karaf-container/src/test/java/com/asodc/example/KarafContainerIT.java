package com.asodc.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.ops4j.pax.exam.Configuration;
import org.ops4j.pax.exam.Option;
import org.ops4j.pax.exam.junit.PaxExam;
import org.ops4j.pax.exam.karaf.options.LogLevelOption;

import java.io.File;

import static org.junit.Assert.assertTrue;
import static org.ops4j.pax.exam.CoreOptions.*;
import static org.ops4j.pax.exam.karaf.options.KarafDistributionOption.*;

@RunWith(PaxExam.class)
public class KarafContainerIT {
    public static final String KARAF_VERSION = "4.3.2";

    @Configuration
    public Option[] config() {
        return new Option[]{
                // allows test to fail fast if a bundle can't be resolved
                systemProperty("pax.exam.osgi.unresolved.fail").value("true"),

                // karaf specific configurations
                karafDistributionConfiguration().frameworkUrl(
                                maven().groupId("org.apache.karaf")
                                        .artifactId("apache-karaf")
                                        .version(KARAF_VERSION)
                                        .type("tar.gz")
                        )
                        .karafVersion(KARAF_VERSION)
                        .name("Apache Karaf")
                        .useDeployFolder(false)
                        .unpackDirectory(new File("target/karaf")),

                // keep the folder in order to have a look at logs afterwards
                keepRuntimeFolder(),

                // deactivate the remote SSH shell
                configureConsole().ignoreRemoteShell(),

                // set the log level
                logLevel(LogLevelOption.LogLevel.INFO),
        };
    }

    @Test
    public void simpleTest() {
        System.out.println("************************* IN TEST METHOD! *************************");
        assertTrue(true);
    }
}
