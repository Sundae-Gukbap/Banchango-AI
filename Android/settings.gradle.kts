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

rootProject.name = "banchango"
include(":app")
include(":core:domain")
include(":core:model")
include(":core:designsystem")
include(":feature:reciperecommend")
include(":feature:main")
include(":core:data-api")
include(":core:navigation")
include(":feature:home")
