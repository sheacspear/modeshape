/*
 * ModeShape (http://www.modeshape.org)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.modeshape.jcr;

import java.net.URL;
import org.modeshape.schematic.document.ParsingException;
import org.junit.Rule;
import org.junit.rules.TestRule;
import org.modeshape.common.junit.SkipTestRule;

/**
 * Abstract test class for tests that repeatedly starting/stopping repositories.
 * <p>
 * Subclasses should call {@link #startRunStop(RepositoryOperation, String)} one or more times in each test, where the supplied
 * {@link RepositoryOperation} implementation does the work on a running repository.
 * </p>
 * 
 * @author rhauch
 * @author hchiorean
 */
public abstract class MultiPassAbstractTest {
    @Rule
    public TestRule skipTestRule = new SkipTestRule();

    protected void startRunStop( RepositoryOperation operation,
                                 String repositoryConfigFile ) {
        URL configUrl = getClass().getClassLoader().getResource(repositoryConfigFile);
        RepositoryConfiguration config = null;
        try {
            config = RepositoryConfiguration.read(configUrl);
        } catch (ParsingException e) {
            throw new RuntimeException(e);
        }
        startRunStop(operation, config);
    }

    protected void startRunStop( RepositoryOperation operation,
                                 RepositoryConfiguration config )  {
        JcrRepository repository = null;

        try {
            repository = new JcrRepository(config);
            repository.start();
            operation.execute(repository);
        } catch (RuntimeException re) {
            throw re;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (repository != null) {
                TestingUtil.killRepository(repository);
            }
        }
    }

    @FunctionalInterface
    protected interface RepositoryOperation {
        void execute(JcrRepository repository) throws Exception;
    }
}
