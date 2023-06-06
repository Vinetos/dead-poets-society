from app import db


class Circle(db.Model):
    __tablename__ = 'circle'
    id = db.Column(db.Integer, primary_key=True)
    name = db.Column(db.String(255))

    def __repr__(self):
        return "<Circle {}>".format(self.id)


class Letter(db.Model):
    __tablename__ = 'letter'
    id = db.Column(db.Integer, primary_key=True)
    date = db.Column(db.DateTime)
    content = db.Column(db.String)
    subject = db.Column(db.String(255))
    writer_id = db.Column(db.BigInteger, db.ForeignKey('writer.id'))
    writer = db.relationship('Writer', backref='letters')

    def __repr__(self):
        return "<Letter {}>".format(self.id)



class Writer(db.Model):
    __tablename__ = 'writer'
    id = db.Column(db.Integer, primary_key=True)
    name = db.Column(db.String(255))
    penname = db.Column(db.String(255))
    title = db.Column(db.String(255))

    def __repr__(self):
        return "<Writer {}>".format(self.id)



class CircleWriters(db.Model):
    __tablename__ = 'circle_writers'
    circle_id = db.Column(db.Integer, db.ForeignKey('circle.id'), primary_key=True)
    writer_id = db.Column(db.Integer, db.ForeignKey('writer.id'), primary_key=True)


class CircleLetters(db.Model):
    __tablename__ = 'circle_letters'
    circle_id = db.Column(db.Integer, db.ForeignKey('circle.id'), primary_key=True)
    letter_id = db.Column(db.Integer, db.ForeignKey('letter.id'), primary_key=True)

# db.create_all()
