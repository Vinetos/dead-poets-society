{ pkgs ? import <nixpkgs> {} }:
  pkgs.mkShell {
    # nativeBuildInputs is usually what you want -- tools you need to run
    nativeBuildInputs = with pkgs; [ 
      gradle
      maven
      # jetbrains.idea-ultimate
      postgresql
      # vscode

    ];
    shellHook = ''
      export JDK_HOME=${pkgs.jdk}
      export PGDATA="$HOME/Desktop/ing2/synchronizer-backend/postgres_data"
      export PGHOST="/tmp"
    '';
}
