from flask import Flask, jsonify, request
from flask_sqlalchemy import SQLAlchemy



app = Flask(__name__)
app.config.from_object("data.config.Config")

@app.route("/")
def hello_world():
    return jsonify(hello= "world")

db = SQLAlchemy(app)
from data.model import Circle, Letter, Writer

from flask.cli import FlaskGroup
cli = FlaskGroup(app)


@app.get('/letters')
def letters():
    # show the post with the given id, the id is an integer
    letters = Letter.query.all()
    return jsonify(letters)

@app.get("/letters/<id>")
def letter(id):
    letter = Letter.query.get(id)
    return jsonify(letter)

@app.get('/writer/<id>/letters')
def writer_letter(id):
    letters = Letter.query.filter(Letter.Writer_id.in_(id))
    return letters

@app.get('/circles/')
def get_circles():
    return jsonify(Circle.query.all())

@app.get('/circle/<id>')
def get_circle(id):
    return jsonify(Circle.query.get(id))

@app.get('/writers/')
def get_writers():
    return jsonify(Writer.query.all())

@app.get('/writer/<id>')
def get_writer(id):
    return jsonify(Writer.query.get(id))


if __name__ == "__main__":
    cli()