/*
 * Copyright 2010 Grails Plugin Collective
 *
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

if(System.getenv('TRAVIS_BRANCH')) {
    grails.project.repos.grailsCentral.username = System.getenv("GRAILS_CENTRAL_USERNAME")
    grails.project.repos.grailsCentral.password = System.getenv("GRAILS_CENTRAL_PASSWORD")    
}

grails.project.work.dir = 'target'

grails.project.dependency.resolver = 'maven'
grails.project.dependency.resolution = {

    inherits 'global'
    log 'warn'

    repositories {
        grailsCentral()
        mavenLocal()
        mavenCentral()
    }

    dependencies {

        compile 'org.springframework:spring-jms:3.2.8.RELEASE', {
            excludes 'spring-core', 'spring-aop','spring-beans', 'spring-context'
        }
        compile 'org.apache.geronimo.specs:geronimo-jms_1.1_spec:1.1.1'

        test 'org.apache.activemq:activemq-core:5.5.1',
             'org.apache.activemq:activeio-core:3.1.3',
             'org.apache.xbean:xbean-spring:3.9', {
            excludes 'activemq-openwire-generator'
            excludes 'commons-logging'
            excludes 'xalan' // IVY-1006 - use xalan 2.7.0 to avoid (see below)
            excludes 'xml-apis' // GROOVY-3356
            excludes 'spring-context'
            export = false
        }
    }

    plugins {

        runtime ":hibernate:3.6.10.14", {
            export = false
        }

        build ':release:3.0.1', ':rest-client-builder:2.0.1', {
            export = false
        }
    }
}
