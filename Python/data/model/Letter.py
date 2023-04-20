from app import db
class Letter(db.Model):
    __tablename__ = "Letter"

    id = db.Column("letter_id", db.Integer, primary_key=True)
    date = db.Column(db.DateTime)
    subject = db.Column(db.String)
    content = db.Column(db.String)
    circles = db.relationship('Circle', secondary=circles, lazy='subquery',
        backref=db.backref('circle_id', lazy=True))
    active = db.Column(db.Boolean(), default=True, nullable=False)

    def __init__(self, email):
        self.email = email