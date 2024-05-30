ThisBuild / scalaVersion           := "2.13.14"
ThisBuild / organization           := "com.alejandrohdezma"
ThisBuild / versionPolicyIntention := Compatibility.None

addCommandAlias("ci-test", "fix --check; versionPolicyCheck; mdoc; test; publishLocal;")
addCommandAlias("ci-docs", "github; headerCreateAll; mdoc")
addCommandAlias("ci-publish", "versionCheck; github; ci-release")

lazy val documentation = project
  .enablePlugins(MdocPlugin)
  .dependsOn(`tapir-golden-openapi-munit`)
  .dependsOn(`tapir-golden-openapi-munit-validator`)

lazy val `tapir-golden-openapi-munit` = module
  .settings(libraryDependencies += "com.softwaremill.sttp.tapir" %% "tapir-core" % "1.9.11")
  .settings(libraryDependencies += "com.softwaremill.sttp.tapir" %% "tapir-openapi-docs" % "1.10.8")
  .settings(libraryDependencies += "com.softwaremill.sttp.apispec" %% "openapi-circe-yaml" % "0.10.0")
  .settings(libraryDependencies += "org.scalameta" %% "munit" % "0.7.29")

lazy val `tapir-golden-openapi-munit-validator` = module
  .dependsOn(`tapir-golden-openapi-munit`)
  .settings(libraryDependencies += "io.swagger.parser.v3" % "swagger-parser" % "2.1.22")
