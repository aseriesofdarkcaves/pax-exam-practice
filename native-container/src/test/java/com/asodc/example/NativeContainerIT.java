package com.asodc.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.ops4j.pax.exam.Configuration;
import org.ops4j.pax.exam.CoreOptions;
import org.ops4j.pax.exam.Option;
import org.ops4j.pax.exam.junit.PaxExam;

import static org.junit.Assert.assertTrue;
import static org.ops4j.pax.exam.CoreOptions.*;

@RunWith(PaxExam.class)
public class NativeContainerIT {
    @Configuration
    public Option[] config() {
        return CoreOptions.options(
                // if you don't do this the container will try to contact maven central and fail inside MMS network
                repository("https://artifacts.mms-at-work.de/artifactory/prj-esb-mms-build-release").id("central"),
                // allows test to fail fast if a bundle can't be resolved
                systemProperty("pax.exam.osgi.unresolved.fail").value("true"),
                junit()
        );
    }

    @Test
    public void simpleTest() {
        assertTrue(true);
    }

    /**
     * TODO: figure out why this is required
     *   My stab-in-the-dark guess is that our tests are somehow injected into the OSGi container at runtime,
     *   so the container needs some bundles to run in that context?
     */
    public Option junit() {
        return composite(systemProperty("pax.exam.invoker").value("junit"),
                bundle("link:classpath:META-INF/links/org.ops4j.pax.tipi.junit.link"),
                bundle("link:classpath:META-INF/links/org.ops4j.pax.exam.invoker.junit.link"),
                mavenBundle().groupId("org.apache.servicemix.bundles")
                        .artifactId("org.apache.servicemix.bundles.hamcrest").version("1.3_1")
        );
    }
}
