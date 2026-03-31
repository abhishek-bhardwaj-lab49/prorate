{
  description = "Prorate – Spring Boot microservices development environment";

  # ---------------------------------------------------------------------------
  # Inputs
  # ---------------------------------------------------------------------------
  inputs = {
    nixpkgs.url = "github:NixOS/nixpkgs/nixos-24.05";

    # Provides `flake-utils` helpers for multi-system support
    flake-utils.url = "github:numtide/flake-utils";
  };

  # ---------------------------------------------------------------------------
  # Outputs
  # ---------------------------------------------------------------------------
  outputs = { self, nixpkgs, flake-utils }:
    flake-utils.lib.eachDefaultSystem (system:
      let
        # -----------------------------------------------------------------------
        # Package set
        # -----------------------------------------------------------------------
        pkgs = import nixpkgs { inherit system; };

        # -----------------------------------------------------------------------
        # Re-usable sub-modules (kept in nix/)
        # -----------------------------------------------------------------------
        jdkPkg    = import ./nix/jdk.nix   { inherit pkgs; };
        mavenPkg  = import ./nix/maven.nix { inherit pkgs jdkPkg; };

        # -----------------------------------------------------------------------
        # Helper: pretty banner printed by the shellHook
        # -----------------------------------------------------------------------
        banner = ''
          echo ""
          echo "╔══════════════════════════════════════════════════════════════╗"
          echo "║          Prorate – Nix Development Environment              ║"
          echo "╠══════════════════════════════════════════════════════════════╣"
          echo "║  Java    : $(java -version 2>&1 | head -1)                  "
          echo "║  Maven   : $(mvn --version 2>&1 | head -1)                  "
          echo "║  Docker  : $(docker --version 2>/dev/null || echo 'not running')"
          echo "║  Compose : $(docker compose version 2>/dev/null || echo 'not running')"
          echo "╠══════════════════════════════════════════════════════════════╣"
          echo "║  Quick commands:                                             ║"
          echo "║    mvn package -pl prorate-eureka-server  # build module    ║"
          echo "║    mvn package -pl prorate-config-server                    ║"
          echo "║    mvn package -pl prorate-ms-doctors                       ║"
          echo "║    mvn package -pl prorate-ms-reviews                       ║"
          echo "║    mvn package                            # build all       ║"
          echo "║    cd docker && docker compose up         # start stack      ║"
          echo "╚══════════════════════════════════════════════════════════════╝"
          echo ""
        '';

      in
      {
        # -----------------------------------------------------------------------
        # Default dev shell  –  entered via `nix develop` or `direnv allow`
        # -----------------------------------------------------------------------
        devShells.default = pkgs.mkShell {
          name = "prorate-dev";

          # Packages available inside the shell
          packages = [
            # --- Java toolchain ---
            jdkPkg
            mavenPkg

            # --- Container tooling ---
            pkgs.docker
            pkgs.docker-compose

            # --- General utilities ---
            pkgs.git
            pkgs.curl
            pkgs.jq
            pkgs.gnused
            pkgs.coreutils
          ];

          # Environment variables set for every shell session
          JAVA_HOME = "${jdkPkg}";

          # Maven local repository lives inside the project so it is never
          # shared with the host's ~/.m2 and stays reproducible across machines.
          # Override with `export MAVEN_REPO=~/.m2/repository` if you prefer
          # the global cache.
          MAVEN_REPO = toString ./. + "/.m2/repository";

          MAVEN_OPTS = "-Xmx1g -XX:+TieredCompilation";

          shellHook = ''
            # Ensure the project-local Maven repo directory exists
            mkdir -p "$MAVEN_REPO"

            # Activate the project-local Maven settings so every `mvn` call
            # uses the wrapper defined in nix/maven.nix
            export MAVEN_ARGS="--settings ${toString ./nix/maven-settings.xml}"

            ${banner}
          '';
        };

        # -----------------------------------------------------------------------
        # Formatter (optional – run with `nix fmt`)
        # -----------------------------------------------------------------------
        formatter = pkgs.nixpkgs-fmt;
      }
    );
}
