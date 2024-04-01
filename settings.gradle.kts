pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "SeekDemo"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

include(":app")
include(":storage")
include(":theme")
include(":network")

include(":common:data")
include(":common:domain")
include(":common:utils")

include(":feature:auth:presentation")
include(":feature:auth:data")
include(":feature:auth:domain")

include(":feature:job:collection:presentation")
include(":feature:job:collection:domain")
include(":feature:job:detail:presentation")
include(":feature:job:detail:domain")
include(":feature:job:data")
include(":feature:profile")
