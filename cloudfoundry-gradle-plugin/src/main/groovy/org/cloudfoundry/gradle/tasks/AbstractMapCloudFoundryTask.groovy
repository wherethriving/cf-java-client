/*
 * Copyright 2012 the original author or authors.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.cloudfoundry.gradle.tasks

abstract class AbstractMapCloudFoundryTask extends AbstractCloudFoundryTask {
    AbstractMapCloudFoundryTask() {
        super()
    }

    protected def modifyUris(Closure c) {
        def app = client.getApplication(application)
        def applicationUris = c.call(app.uris, allUris.collect { it as String }).unique()
        client.updateApplicationUris(application, applicationUris)
        applicationUris
    }

    protected void listUriMappings(def uris) {
        if (verboseEnabled) {
            StringBuilder sb = new StringBuilder("Current uri mappings for ${application}\n")

            if (uris.isEmpty()) {
                sb << '  none\n'
            }
            for (uri in uris) {
                sb << "  ${uri}\n"
            }

            logVerbose sb.toString()
        }
    }
}
