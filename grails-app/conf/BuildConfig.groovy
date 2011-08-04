grails.servlet.version = "2.5"
grails.project.class.dir = "target/classes"
grails.project.test.class.dir = "target/test-classes"
grails.project.test.reports.dir = "target/test-reports"

grails.project.dependency.resolution = {

    def socialVersion = "1.0.0.RC2"

    // inherit Grails' default dependencies
    inherits("global") {
    }
    log "error"
    checksums true

    repositories {
        inherits true
        grailsPlugins()
        grailsHome()
        grailsCentral()

        mavenLocal()
        mavenCentral()

        mavenRepo "http://maven.springframework.org/release"
        mavenRepo "http://maven.springframework.org/milestone"
        mavenRepo "http://maven.springframework.org/snapshot"
    }
    dependencies {
        runtime('org.springframework.security:spring-security-crypto:3.1.0.CI-SNAPSHOT') {
            transitive = false
        }

        runtime("org.springframework.social:spring-social-core:${socialVersion}") {
            transitive = false
        }

        runtime("org.springframework.social:spring-social-web:${socialVersion}") {
            transitive = false
        }

        runtime("org.springframework.social:spring-social-twitter:${socialVersion}") {
            transitive = false
        }

        runtime("org.springframework.social:spring-social-facebook:${socialVersion}") {
            transitive = false
        }

        runtime 'org.codehaus.jackson:jackson-mapper-asl:1.8.2'
    }

    plugins {
        compile ":hibernate:$grailsVersion"
        compile ":jquery:1.6.1.1"
        compile ":resources:1.0.1"

        build ":tomcat:$grailsVersion"
    }
}
