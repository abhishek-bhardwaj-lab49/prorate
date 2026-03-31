# nix/maven.nix
#
# Provides a Maven wrapper that:
#   1. Uses the JDK selected in nix/jdk.nix (passed in as `jdkPkg`).
#   2. Points the local repository at the project-local `.m2/repository/`
#      directory (set via MAVEN_REPO in the flake shellHook) so builds are
#      isolated from the host's ~/.m2 cache.
#   3. Exposes a `maven-settings.xml` file (written alongside this file) that
#      Maven picks up via MAVEN_ARGS="--settings …".
#
# Usage (from flake.nix):
#   mavenPkg = import ./nix/maven.nix { inherit pkgs jdkPkg; };

{ pkgs, jdkPkg }:

# Wrap the stock Maven package so that JAVA_HOME always points at our pinned
# JDK, regardless of what the host system has installed.
pkgs.maven.override { jdk = jdkPkg; }
