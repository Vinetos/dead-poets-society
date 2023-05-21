{ pkgs ? import <nixpkgs> {} }:

pkgs.mkShell {
  name = "python-env";
  buildInputs = with pkgs; [
    python310Packages.flask
    python310Packages.flask-cors
    python310Packages.flask-sqlalchemy
    python310Packages.click
    python310Packages.itsdangerous
    python310Packages.jinja2
    python310Packages.markupsafe
    python310Packages.pyparsing
    python310Packages.six
    python310Packages.sqlalchemy
    python310Packages.werkzeug
    python310Packages.psycopg2
    python310Packages.numpy
  ];
}
