#from flask import render_template
from app import app, db
from app.models import *
from json import dumps

@app.get("/")
def index():
    return "Python Flask API for the Letters Project"

@app.get("/circles")
def circles_get():
    circles = Circle.query.all()
    return dumps([circle.__dict__ for circle in circles]) if circles else {"error": "No circles found"}, 404

@app.get("/circle/<string:circle_name>")
def circle_get_name(circle_name):
    found = Circle.query.filter_by(name=circle_name).first()
    return dumps(found.__dict__) if found else {"error": "Circle not found"}, 404

@app.get("/circle/<int:id>")
def circle_get_id(id):
    circle = Circle.query.get(id)
    return dumps(circle.__dict__) if circle else {"error": "Circle not found"}, 404

@app.get("/circle/<int:circle_id>/writers")
def circle_get_writers(circle_id):
    writers_ids = circle_writers.query.filter_by(circle_id=circle_id).all()
    if not writers_ids:
        return {"error": "Circle not Found"}, 404
    writers = Writer.query.filter(Writer.id.in_([writer_id.writer_id for writer_id in writers_ids])).all()
    return dumps([writer.__dict__ for writer in writers]) if writers else {"error": "No writers found in this circle"}, 404

@app.get("/circle/<int:circle_id>/letters")
def circle_get_letters(circle_id):
    letters_ids = circle_letters.query.filter_by(circle_id=circle_id).all()
    if not letters_ids:
        return {"error": "Circle not Found"}, 404
    letters = Letter.query.filter(Letter.id.in_([letter_id.letter_id for letter_id in letters_ids])).all()
    return dumps([letter.__dict__ for letter in letters]) if letters else {"error": "No letters found in this circle"}, 404

@app.get("/writers")
def writers_get():
    writer = Writer.query.all()
    return dumps([writer.__dict__ for writer in writer]) if writer else {"error": "No writers found"}, 404

@app.get("/writers/<string:writer_name>")
def writer_get_name(writer_name):
    writer = Writer.query.filter_by(name=writer_name).first()
    if not writer:
        writer = Writer.query.filter_by(penname=writer_name).first()
    return dumps(writer.__dict__) if writer else {"error": "Writer not found"}, 404

@app.get("/writers/<int:id>")
def writer_get_id(id):
    writer = Writer.query.get(id)
    return dumps(writer.__dict__) if writer else {"error": "Writer not found"}, 404

@app.get("/letters")
def letters_get():
    letters = Letter.query.all()
    return dumps([letter.__dict__ for letter in letters]) if letters else {"error": "No letters found"}, 404

@app.get("/letters/<int:id>")
def letter_get_id(id):
    letter = Letter.query.get(id)
    return dumps(letter.__dict__) if letter else {"error": "Letter not found"}, 404

@app.get("/letter/written_by/<int:writer_id>")
def letter_get_writer(writer_name):
    letters = Letter.query.filter_by(writer_id=writer_id).all()
    return dumps([letter.__dict__ for letter in letters]) if letters else {"error": "No letters found"}, 404


#  @app.post("/writer/create")
#  def writer_create():
    #  writer = Writer(name=request.form.get("name"), penname=request.form.get("penname"), title=request.form.get("title"))
    #  db.session.add(writer)
    #  db.session.commit()
    #  created = Writer.query.filter_by(name=request.form.get("name")).first()
    #  return make_response(f"Writer {created.name} created with id {created.id}", 200)

#  @app.post("/letter/create")
#  def letter_create():
    #  letter = Letter(date=request.form.get("date"),\
                    #  subject=request.form.get("subject"),\
                    #  content=request.form.get("content"),\
                    #  writer_id=request.form.get("writer_id"))
    #  db.session.add(letter)
    #  db.session.commit()
    #  created = Letter.query.filter_by(date=request.form.get("date")).first()
    #  return make_response(f"Letter {created.subject} created with id {created.id}", 200)
