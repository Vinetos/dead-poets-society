from app import db
class Letter(db.Model):
    __tablename__ = "Letter"

    id = db.Column("letter_id", db.Integer, primary_key=True)
    date = db.Column(db.DateTime)
    subject = db.Column(db.String)
    content = db.Column(db.String)
    writerId = db.Column(db.Integer, db.ForeignKey("Writer.id"))
    #circles = db.relationship('Circle', lazy='subquery',
     #   backref=db.backref('circle_id', lazy=True))
