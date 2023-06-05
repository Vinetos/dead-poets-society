from app import db


class Circle(db.Model):
    __tablename__ = 'circle'
    id = db.Column(db.Integer, primary_key=True)
    name = db.Column(db.String(255))

    def __repr__(self):
        return "<Circle {}>".format(self.id)

    def __dict__(self):
        return {
            'id': self.id,
            'name': self.name
        }


class Letter(db.Model):
    __tablename__ = 'letter'
    id = db.Column(db.Integer, primary_key=True)
    date = db.Column(db.DateTime)
    subject = db.Column(db.String(255))
    content = db.Column(db.String)

    def __repr__(self):
        return "<Letter {}>".format(self.id)

    def __dict__(self):
        return {
            'id': self.id,
            'date': self.date,
            'subject': self.subject,
            'content': self.content
        }


class Writer(db.Model):
    __tablename__ = 'writer'
    id = db.Column(db.Integer, primary_key=True)
    name = db.Column(db.String(255))
    penname = db.Column(db.String(255))
    title = db.Column(db.String(255))

    def __repr__(self):
        return "<Writer {}>".format(self.id)

    def __dict__(self):
        return {
            'id': self.id,
            'name': self.name,
            'penname': self.penname,
            'title': self.title
        }


class CircleWriters(db.Model):
    __tablename__ = 'circle_writers'
    circle_id = db.Column(db.Integer, db.ForeignKey('circle.id'), primary_key=True)
    writer_id = db.Column(db.Integer, db.ForeignKey('writer.id'), primary_key=True)


class CircleLetters(db.Model):
    __tablename__ = 'circle_letters'
    circle_id = db.Column(db.Integer, db.ForeignKey('circle.id'), primary_key=True)
    letter_id = db.Column(db.Integer, db.ForeignKey('letter.id'), primary_key=True)

# db.create_all()
