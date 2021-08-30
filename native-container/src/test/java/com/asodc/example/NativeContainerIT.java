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
                // if you want to get the container to use a different repo
                // repository("someUrl").id("central"),
                // allows test to fail fast if a bundle can't be resolved
                systemProperty("pax.exam.osgi.unresolved.fail").value("true"),
                junit()
        );
    }

    @Test
    public void simpleTest() {
        System.out.println("************************* IN TEST METHOD! *************************");
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
