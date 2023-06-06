#from flask import render_template
from app import app, db
from app.models import *
from json import dumps

def row2dict(row):
    d = {}
    for column in row.__table__.columns:
        d[column.name] = str(getattr(row, column.name))

    return d

@app.get("/")
def index():
    return "Python Flask API for the Letters Project"

@app.get("/circles")
def circles_get():
    circles = Circle.query.all()
    return dumps([row2dict(circle) for circle in circles]) if circles else [], 200

@app.get("/circles/<string:circle_name>")
def circle_get_name(circle_name):
    found = Circle.query.filter_by(name=circle_name).first()
    return dumps(row2dict(found)) if found else {"error": "Circle " + circle_name + " not found"}, 404

@app.get("/circles/<int:id>")
def circle_get_id(id):
    circle = Circle.query.get(id)
    return dumps(row2dict(circle)) if circle else {"error": f"Circle ${id} not found"}, 404

@app.get("/circles/<int:c_id>/writers")
def circle_get_writers(c_id):
    writers_ids = CircleWriters.query.filter(CircleWriters.circle_id == c_id)
    if not writers_ids:
        return {"error": "Circle not Found"}, 404
    writers = Writer.query.filter(Writer.id.in_([writer_id.writer_id for writer_id in writers_ids])).all()
    return dumps([row2dict(writer) for writer in writers]) if writers else [], 200

@app.get("/circles/<int:c_id>/letters")
def circle_get_letters(c_id):
    letters_ids = CircleLetters.query.filter(CircleLetters.circle_id == c_id).all()
    if not letters_ids:
        return {"error": "Circle not Found"}, 404
    letters = Letter.query.filter(Letter.id.in_([letter_id.letter_id for letter_id in letters_ids])).all()
    return dumps([row2dict(letter) for letter in letters]) if letters else [], 200

@app.get("/writers")
def writers_get():
    writer = Writer.query.all()
    return dumps([row2dict(writer) for writer in writer]) if writer else [], 200

@app.get("/writers/<string:writer_name>")
def writer_get_name(writer_name):
    writer = Writer.query.filter_by(name=writer_name).first()
    if not writer:
        writer = Writer.query.filter_by(penname=writer_name).first()
    return dumps(row2dict(writer)) if writer else {"error": "Writer not found"}, 404

@app.get("/writers/<int:id>")
def writer_get_id(id):
    writer = Writer.query.get(id)
    return dumps(row2dict(writer)) if writer else {"error": "Writer not found"}, 404

@app.get("/letters")
def letters_get():
    letters = Letter.query.all()
    return dumps([row2dict(letter) for letter in letters]) if letters else [], 200

@app.get("/letters/<int:id>")
def letter_get_id(id):
    letter = Letter.query.get(id)
    return dumps(row2dict(letter)) if letter else {"error": "Letter not found"}, 404

@app.get("/letter/written_by/<int:writer_id>")
def letter_get_writer(writer_name):
    letters = Letter.query.filter_by(writer_id=writer_id).all()
    return dumps([row2dict(letter) for letter in letters]) if letters else {"error": "No letters found"}, 404