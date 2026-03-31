# nix/jdk.nix
#
# Isolates the JDK selection so it can be swapped independently of the main
# flake.  All modules in this project declare `<java.version>17</java.version>`
# in their pom.xml, so we pin Eclipse Temurin 17 (the most widely-used
# OpenJDK distribution on NixOS).
#
# To switch to a different JDK (e.g. GraalVM CE 21) replace the single
# `pkgs.temurin-bin-17` reference below and rebuild with `nix develop`.
#
# Usage (from flake.nix):
#   jdkPkg = import ./nix/jdk.nix { inherit pkgs; };

{ pkgs }:

# Eclipse Temurin 17 – matches the java.version declared in every module pom.xml
pkgs.temurin-bin-17
