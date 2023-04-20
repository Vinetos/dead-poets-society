from app import db
class Circle(db.Model):
    __tablename__ = "Circle"

    id = db.Column("circle_id", db.Integer, primary_key=True)
    name = db.Column(db.Text)
    letters = db.relationship('Letter', lazy='subquery',
        backref=db.backref('Wetter.id', lazy=True))
    letters = db.relationship('Writer', lazy='subquery',
        backref=db.backref('Writer.id', lazy=True))